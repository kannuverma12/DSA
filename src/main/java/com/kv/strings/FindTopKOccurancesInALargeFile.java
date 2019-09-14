package com.kv.strings;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import com.kv.strings.FindTopKOccurancesInALargeFile.WordCount;

/*
 * 		Pseudo code for the algorithm

		1. 	Finding the Word Occurrence Count - Stream the words into a HashMap (put operation is Big O(1)) 
			keeping the value as word occurrence count. On every word occurrence, update the word count.
		2. 	Track Top K occurring Words Using Binary Min Heap (PriorityQueue with Natural ordering) - This can be 
			achieved by maintaining a binary min heap of max size K, and then for each word count in hashmap - 
			i. 	Check if the heap size if less than K - then add the new word count to min heap. Otherwise 
			ii.	Check if the peek element (that is minimum value in binary min heap) is less than the new word count, 
				if it is, then remove the existing number and insert the new word count into min heap. 
			iii.	When we are done traversing the entire word-counts then we will have heap containing the top K frequently
		 		occurring words.
 * 
 */
public class FindTopKOccurancesInALargeFile {

	public static void main(String[] args) {
		
		
		//getWordCount("/Users/karan.verma/Downloads/bigFile.txt");
		
		//getWordCountUsingJ8FileAndPath("/Users/karan.verma/Downloads/bigFile.txt");
		
		//getWordCountAdhoc();
		
		
	}
	
//	private static void getWordCountAdhoc() {
//		long curtime = System.currentTimeMillis();
//		List<String> terms = Arrays.asList("Coding is great", "Search Engine are engine",
//											"Google is nice search engine", "Bing is also a nice engine");
//
//		TopOccurrence topOccurrence = new TopOccurrence(10);
//
//		terms.parallelStream().flatMap(a -> Arrays.asList(a.split(" ")).stream())
//				.collect(Collectors.toConcurrentMap(w -> w.toString().toLowerCase(), w -> 1, Integer::sum))
//				.forEach((s, coun) -> topOccurrence.add(new WordCount(s, coun)));
//
//		System.out.println(topOccurrence);
//		System.out.println("Time taken = "+((System.currentTimeMillis() - curtime)/1000)+" sec.");
//
//	}
//
//	public static void getWordCountUsingJ8FileAndPath(String fileName) {
//		long curtime = System.currentTimeMillis();
//		try {
//			TopOccurrence topOccurrence = new TopOccurrence(10);
//
//			Stream<String> fstream = Files.lines(Paths.get(fileName));
//			if(fstream == null) {
//				System.out.println("Lines Empty -> waiting");
//				Thread.sleep(1*1000);
//			}
//
//			fstream.parallel().flatMap(a -> Arrays.asList(a.split(" ")).stream())
//					.collect(Collectors.toConcurrentMap(w -> w.toString().toLowerCase(), w -> 1, Integer::sum))
//					.forEach((s, coun) -> topOccurrence.add(new WordCount(s, coun)));
//			System.out.println(topOccurrence);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("Time taken = "+((System.currentTimeMillis() - curtime)/1000)+" sec.");
//	}
//
//	public static void getWordCount(String fileName) {
//		long curtime = System.currentTimeMillis();
//		FileInputStream fis = null;
//		DataInputStream dis = null;
//		BufferedReader br = null;
//		Map<String, Integer> wordMap = new HashMap<String, Integer>();
//		try {
//
//			fis = new FileInputStream(fileName);
//			dis = new DataInputStream(fis);
//			br = new BufferedReader(new InputStreamReader(dis));
//			String line = null;
//			TopOccurrence topOccurrence = new TopOccurrence(10);
//
//			while ((line = br.readLine()) != null) {
//
//				List<String> terms = Arrays.asList(line.split(" "));
//				terms.parallelStream()//.flatMap(a -> Arrays.asList(a.split(" ")).stream())
//									.collect(Collectors.toConcurrentMap(w -> w.toString().toLowerCase(), w -> 1, Integer::sum))
//									.forEach((s, coun) -> topOccurrence.add(new WordCount(s, coun)));
//			}
//			System.out.println(topOccurrence);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (br != null)
//					br.close();
//			} catch (Exception ex) {}
//		}
//		System.out.println("Time taken = "+((System.currentTimeMillis() - curtime)/1000)+" sec.");
//	}
//
//	static class TopOccurrence{
//
//		private final PriorityQueue<WordCount> minHeap;
//        private final int maxSize;
//        public TopOccurrence(int maxSize) {
//            this.maxSize = maxSize;
//            this.minHeap = new PriorityQueue<WordCount>(Comparator.comparingInt((WordCount wc) -> wc.count));
//            //this.minHeap = new PriorityQueue<WordCount>(new WordComparator().reversed());
//
//         /*
//          * 	This constructs a min heap (when order of elements is natural i.e. ascending order).
//          	We are using Natural order for integers (wc.count)
//          	In order to create a max-heap, we just need to provide reversed comparator i.e. that sorts in descending order,as shown below
//          	this.minHeap = new PriorityQueue<WordCount>(Comparator.comparingInt((WordCount wc) -> wc.count).reversed());
//         */
//        }
//
//
//        public void add(WordCount data) {
//            if (minHeap.size() < maxSize) { 		// size() is Big O(1)
//                minHeap.offer(data); 			// Big O(log(k)) where k is the number of top occurrences required
//            } else if (minHeap.peek().count < data.count) { // peek() is Big O(1)
//                minHeap.poll(); 					// Big O(log(k))
//                minHeap.offer(data); 			// Big O(log(k))
//            }
//        }
//
//        @Override
//        public String toString() {
//            return "TopOccurrence {" + "minHeap=" + minHeap + ", maxSize=" + maxSize + '}';
//        }
//
//	}
//
//	static class WordCount{
//        protected final String word;
//        protected final int count;
//        WordCount(String word, int count) {
//            this.word = word;
//            this.count = count;
//        }
//        @Override
//        public String toString() {
//            return "{" + "word='" + word + '\'' + ", count=" + count + '}'+"\r\n";
//        }
//
//
//    }
//
//	static class WordComparator implements Comparator<WordCount>{
//
//		@Override
//		public int compare(WordCount o1, WordCount o2) {
//			// TODO Auto-generated method stub
//			return o1.count-o2.count;
//		}
//
//	}
	
	

}
