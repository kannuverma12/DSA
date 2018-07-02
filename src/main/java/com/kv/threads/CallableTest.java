package com.kv.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		List<Future<Long>> l = new ArrayList<Future<Long>>();
		for(int i=0;i<500;i++) {
			Callable<Long> c = new MyCallable();
			Future<Long> ft = es.submit(c);
			l.add(ft);
		}
		long sum = 0;
        System.out.println(l.size());
        // now retrieve the result
        for (Future<Long> future : l) {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
        es.shutdown();
	}

}

class MyCallable implements Callable<Long>{

	@Override
	public Long call() throws Exception {
		long sum = 0;
        for (long i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
	}
	
}
