package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * `tail -n N` - last N lines of a (possibly huge) file     [was: lc/Q24_2_ImplementTailF]
 *
 * Read BACKWARDS in fixed-size blocks (e.g. 8 KB), counting '\n', until N+1 newlines are seen
 * or the file start is reached; then decode just that suffix once.
 * Cost: O(bytes in the last N lines) I/O, independent of file size; O(1) seeks per block.
 *
 * Changed : BUG FIX - the original's `break` inside `switch` only left the switch, never the
 *           for-loop, so it scanned the WHOLE file and returned every line, not the last N.
 *           It also did one seek() + read() syscall per BYTE and cast bytes to char, which
 *           corrupts multi-byte UTF-8.
 *
 * Follow-up (`tail -f`): remember the file length, poll (or use WatchService), and read from
 * the old length to the new length. Handle truncation/rotation (length shrinks or inode changes).
 */
public class TailF {

    private static final int BLOCK = 8192;

    public static List<String> lastLines(File file, int n) throws IOException {
        if (n <= 0) {
            return Collections.emptyList();
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long end = raf.length();
            // Ignore one trailing newline so "a\nb\n" has last line "b", like tail.
            if (end > 0) {
                raf.seek(end - 1);
                if (raf.read() == '\n') {
                    end--;
                }
            }
            long pos = end;
            int newlines = 0;
            long start = 0;
            byte[] buf = new byte[BLOCK];
            outer:
            while (pos > 0) {
                int len = (int) Math.min(BLOCK, pos);
                pos -= len;
                raf.seek(pos);
                raf.readFully(buf, 0, len);
                for (int i = len - 1; i >= 0; i--) {
                    if (buf[i] == '\n' && ++newlines == n) {
                        start = pos + i + 1;
                        break outer;
                    }
                }
            }
            byte[] tail = new byte[(int) (end - start)];
            raf.seek(start);
            raf.readFully(tail);
            String text = new String(tail, StandardCharsets.UTF_8);
            return text.isEmpty() ? Collections.<String>emptyList() : Arrays.asList(text.split("\r?\n", -1));
        }
    }

    public static void main(String[] args) throws IOException {
        File f = File.createTempFile("tailf", ".txt");
        f.deleteOnExit();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        List<String> all = new ArrayList<>();
        for (int i = 1; i <= 20_000; i++) {
            String line = "line " + i + (i % 1000 == 0 ? " héllo" : "");
            all.add(line);
            bytes.write((line + "\n").getBytes(StandardCharsets.UTF_8));
        }
        Files.write(f.toPath(), bytes.toByteArray());
        Check.eq(lastLines(f, 3), all.subList(all.size() - 3, all.size()));
        Check.eq(lastLines(f, 1500), all.subList(all.size() - 1500, all.size()));
        Check.eq(lastLines(f, 50_000).size(), 20_000);

        Files.write(f.toPath(), "only".getBytes(StandardCharsets.UTF_8));
        Check.eq(lastLines(f, 5), Arrays.asList("only"));
        Files.write(f.toPath(), new byte[0]);
        Check.eq(lastLines(f, 5).size(), 0);
    }
}
