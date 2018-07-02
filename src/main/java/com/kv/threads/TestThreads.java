package com.kv.threads;

import java.util.ArrayList;
import java.util.List;

public class TestThreads {

	public static void main(String[] args) {

		List<Thread> list = new ArrayList<Thread>();
		
		for(int i=0;i<500;i++) {
			MyRunnable mr = new MyRunnable(1000000L+i);
			Thread worker = new Thread(mr);
			
			worker.setName(String.valueOf(i));
			worker.start();
			
		}
		int running = 0;
		do {
			running = 0;
			for(Thread t : list) {
				if(t.isAlive())
					running++;
			}
			System.out.println("We have " + running + " running threads. ");
		}while(running>0);
		
	}
	
	

}

class MyRunnable implements Runnable{

	long countUntil;
	
	public MyRunnable(long countUntil) {
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
