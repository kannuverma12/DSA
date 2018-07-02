package com.kv.threads;

import java.util.concurrent.Semaphore;


//this can be done by using semaphores

public class ThreadevenOdd implements Runnable {
	private boolean isEven;
	private int count;
	static Semaphore s = new Semaphore(1);
	static Semaphore t = new Semaphore(0);
	ThreadevenOdd(boolean flag, int c){
		isEven = flag;
		count = c;
	}
	
	@Override
	public void run() {
		if (isEven){
			try {
				printEven(count);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}else{
			try {
				printOdd(count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void printOdd(int count) throws InterruptedException{
		int c = 1;
		for(int i=0;i<count;i++){
			
			s.acquire(1);
			System.out.println("Odd : "+c);
			c = c+2;
			t.release(1);
			
		}
		
	}
	
	private void printEven(int count) throws InterruptedException{
		int c = 2;
		for(int i=0;i<count;i++){
			
			t.acquire(1);
			System.out.println("Even : "+c);
			c = c+2;
			s.release(1);
		}
		
		
	}
	
	public static void main(String[] args){
		
		
		Thread a = new Thread(new ThreadevenOdd(true,20));
		Thread b = new Thread(new ThreadevenOdd(false,20));
		a.start();
		b.start();
					
	}

}