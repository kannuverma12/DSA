package com.kv.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCompleteableFuture {
	
	private static ExecutorService service = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			test_run_after_both();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void test_then_compose() throws Exception {
/*
	 Function<Integer,Supplier<List<Integer>>> getFirstTenMultiples = num ->
	                ()->Stream.iterate(num, i -> i + num).limit(10).collect(Collectors.toList());

	 Supplier<List<Integer>> multiplesSupplier = getFirstTenMultiples.apply(13);

	 //Original CompletionStage
	 CompletableFuture<List<Integer>> getMultiples = CompletableFuture.supplyAsync(multiplesSupplier, service);

	 //Function that takes input from orignal CompletionStage
	 Function<List<Integer>, CompletableFuture<Integer>> sumNumbers = multiples ->
	            CompletableFuture.supplyAsync(() -> multiples.stream().mapToInt(Integer::intValue).sum());

	  //The final CompletableFuture composed of previous two.
	  CompletableFuture<Integer> summedMultiples = getMultiples.thenComposeAsync(sumNumbers, service);

	   assertThat(summedMultiples.get(), is(715));*/
	  }
	
	public static void test_run_after_both() throws Exception {
		List<String> results = new ArrayList<String>();

		CompletableFuture<Void> run1 = CompletableFuture.runAsync(() -> {
		        pauseSeconds(2);
		        results.add("first task");
		    }, service);

		CompletableFuture<Void> run2 = CompletableFuture.runAsync(() -> {
		        pauseSeconds(3);
		        results.add("second task");
		    }, service);

		CompletableFuture<Void> finisher = run1.runAfterBothAsync(run2,
											() -> results.add(results.get(0)+ "&"+results.get(1)),service);
		 pauseSeconds(4);
		 System.out.println("finisher.isDone() = "+finisher.isDone());
		 System.out.println("results.get(2) = "+results.get(2));
//		 assert(finisher.isDone(), is(true));
//		 assertThat(results.get(2),is("first task&second task"));
		}
	
	public static void pauseSeconds(int num){
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
