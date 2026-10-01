package com.kv.lcoptimized.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Runs the main() of every class under com.kv.lcoptimized and reports failed checks.
 * mvn compile && java -cp target/classes com.kv.lcoptimized.common.RunAll
 */
public final class RunAll {

    private static final String ROOT = "com/kv/lcoptimized";

    public static void main(String[] args) throws Exception {
        URL url = RunAll.class.getClassLoader().getResource(ROOT);
        if (url == null || !"file".equals(url.getProtocol())) {
            throw new IllegalStateException("Run from a compiled classes directory");
        }
        Path base = Paths.get(url.toURI()).getParent().getParent().getParent();
        List<String> classes;
        try (Stream<Path> files = Files.walk(Paths.get(url.toURI()))) {
            classes = files.map(p -> base.relativize(p).toString())
                    .filter(s -> s.endsWith(".class") && !s.contains("$"))
                    .map(s -> s.substring(0, s.length() - 6).replace('/', '.').replace('\\', '.'))
                    .filter(s -> !s.endsWith(".RunAll"))
                    .sorted()
                    .collect(Collectors.toList());
        }
        int broken = 0;
        int ran = 0;
        for (String name : classes) {
            Method main;
            try {
                main = Class.forName(name).getMethod("main", String[].class);
            } catch (NoSuchMethodException e) {
                continue;
            }
            if (!Modifier.isStatic(main.getModifiers())) {
                continue;
            }
            int before = Check.failed();
            try {
                main.invoke(null, (Object) new String[0]);
            } catch (Exception e) {
                Throwable cause = e.getCause() != null ? e.getCause() : e;
                System.out.println("  EXCEPTION in " + name + ": " + cause);
                broken++;
                continue;
            }
            ran++;
            if (Check.failed() > before) {
                broken++;
            }
        }
        System.out.printf("%nRan %d programs: %d checks passed, %d checks failed, %d programs broken%n",
                ran, Check.passed(), Check.failed(), broken);
        if (broken > 0) {
            System.exit(1);
        }
    }
}
