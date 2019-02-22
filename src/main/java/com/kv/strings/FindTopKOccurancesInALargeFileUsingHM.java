package com.kv.strings;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class FindTopKOccurancesInALargeFileUsingHM {

    public static void main(String a[]) {
        FindTopKOccurancesInALargeFileUsingHM mdc = new FindTopKOccurancesInALargeFileUsingHM();
        Map<String, Integer> wordMap = mdc.getWordCount("/Users/karan.verma/Downloads/bigFile.txt");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        for (int i = 0; i < 10; i++) {
            // for (Map.Entry<String, Integer> entry : list) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println(entry.getKey() + " ==== " + entry.getValue());
        }
    }

    public Map<String, Integer> getWordCount(String fileName) {
        long curtime = System.currentTimeMillis();
        FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            System.out.println("File Size in start : " + fis.getChannel().size());
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            // String line = null;
            // while ((line = br.readLine()) != null) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    System.out.println("Read All Lines. Waiting for new lines.");
                    System.out.println("File Size = " + fis.getChannel().size());
                    Thread.sleep(1 * 1000);
                } else {

                    StringTokenizer st = new StringTokenizer(line, " ");
                    while (st.hasMoreTokens()) {
                        String tmp = st.nextToken().toLowerCase();
                        if (wordMap.containsKey(tmp)) {
                            wordMap.put(tmp, wordMap.get(tmp) + 1);
                        } else {
                            wordMap.put(tmp, 1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (Exception ex) {
            }
        }
        System.out.println("Time taken = " + ((System.currentTimeMillis() - curtime) / 1000) + " sec.");
        return wordMap;
    }

    public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);

        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        return list;
    }

}
