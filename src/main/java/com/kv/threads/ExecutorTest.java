package com.kv.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ExecutorTest {

	public static void main(String[] args) {

		ExecutorService ex = Executors.newFixedThreadPool(10);
		for(int i=0;i<500;i++) {
			Runnable worker = new MyRunnable1(1000000L+i);
			ex.execute(worker);
		}
		ex.shutdown();
		try {
			ex.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished all threads");
		
	}

}

class MyRunnable1 implements Runnable{

	long countUntil;
	
	public MyRunnable1(long countUntil) {
		this.countUntil = countUntil;
	}
	@Override
	public void run() {

		long sum = 0;
		for(long i=1; i<countUntil;i++)
			sum =+ i;
		System.out.println("Sum : "+sum);
		
	}
	
}
