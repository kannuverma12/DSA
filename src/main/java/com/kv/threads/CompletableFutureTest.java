package com.kv.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveAction;

/*
	 * Future allows a client to query a Callable task for its result. It does not provide the option
	 * to register a callback method. A callback method would allow you to get a callback once a task is 
	 * done. In Java 5 you could use ExecutorCompletionService for this purpose but as of Java 8 you can 
	 * use the CompletableFuture interface which allows to provide a callback interface which is called once 
	 * a task is completed.


 */
public class CompletableFutureTest {

	public static void main(String[] args) {

		long started = System.currentTimeMillis();
		
		CompletableFuture<Integer> futureCount = createCompletableFuture();
		
		System.out.println("Took " + (started - System.currentTimeMillis()) + " milliseconds" );

        // now its time to get the result
        try {
          int count = futureCount.get();
            System.out.println("CompletableFuture took " + (started - System.currentTimeMillis()) + " milliseconds" );

           System.out.println("Result " + count);
         } catch (InterruptedException | ExecutionException ex) {
            // Exceptions from the future should be handled here
        }
        
        started = System.currentTimeMillis();
        
        CompletableFuture<String> data = createCompletableFuture().thenApply((Integer count) ->{
        											int tvalue = count*10;
        											return tvalue;
        										}).thenApply(transformed -> "Finally String val "+transformed);
        try {
        		System.out.println("thenApply : "+data.get());
        }catch(Exception e) {}

	}

	private static CompletableFuture<Integer> createCompletableFuture() {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
																			try{
																				Thread.sleep(50);
																			}catch (Exception e){}
																			return new Integer(20);
																		});
		return cf;
	}
	
	

}
