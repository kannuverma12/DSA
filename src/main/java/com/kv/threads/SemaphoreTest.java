package com.kv.threads;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	static Semaphore semaphore = new Semaphore(4, true);
	
	static class MyThread extends Thread{
		
		String  name = "";
		
		public MyThread(String name){
			this.name = name;
			
		}
		
		public void run(){
			
			System.out.println(name+" going to acquire lock...");
			System.out.println("Available Permits = "+semaphore.availablePermits());
			
			try {
				semaphore.acquire();
				System.out.println(name+" got permit.");
				
				try{
					for(int i=1;i<=1;i++){
						System.out.println(name+" is performing operation "+i+". Available Semaphore permits are : "+semaphore.availablePermits());
						Thread.sleep(1000);
					}
						
				}finally{
					System.out.println(name+" Releasing lock...");
					semaphore.release();
					System.out.println("Available permits after releasing "+"name"+" = "+semaphore.availablePermits());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public static void main(String[] args){
		Thread t1 = new MyThread("A");
		t1.start();
		
		Thread t2 = new MyThread("B");
		t2.start();
		
		Thread t3 = new MyThread("C");
		t3.start();
		
		Thread t4 = new MyThread("D");
		t4.start();
		
		Thread t5 = new MyThread("E");
		t5.start();
		
		Thread t6 = new MyThread("F");
		t6.start();
	}

}
