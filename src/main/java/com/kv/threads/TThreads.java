package com.kv.threads;

import java.util.HashMap;
import java.util.Map;

public class TThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread t = new Thread(()-> System.out.println("Hello Thread"));
		
		t.start();
		
		HashMap<String, String> h = new HashMap<>();
		

	}

}
