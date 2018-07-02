package com.kv.priority.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class PQExample {

	public static void main(String[] args) {
		//PriorityQueue with Comparator
		Queue<Customer> cpq = new PriorityQueue<>(7, idComp);
		addToQueue(cpq);
		pollFromQueue(cpq);
	}
	
	public static Comparator<Customer> idComp = new Comparator<Customer>(){

		@Override
		public int compare(Customer o1, Customer o2) {
			return (int) (o1.getId() - o2.getId());
		}
		
	};
	
	//utility method to add random data to Queue
	private static void addToQueue(Queue<Customer> cq){
		Random rand = new Random();
		for(int i=0;i<7;i++){
			int id = rand.nextInt(100);
			cq.add(new Customer(id, "KV"+id));
		}
	}
	
	
	private static void pollFromQueue(Queue<Customer> cq){
		while(true){
			Customer c = cq.poll();
			if(c == null) break;
			System.out.println("Customer Polled : "+c.getId() + " "+ c.getName());
		}
	}

}
