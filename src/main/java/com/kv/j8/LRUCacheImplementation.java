package com.kv.j8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


/*
 * The implementation seems good for Cache, however I think is this not Least Recently Used Cash. For example, 
 * in a multithreaded env, Thread1 added one entry and continuously reading the value by get(). 
 * On the other hand Other threads are putting the value in the cash, and probably not reading them 
 * back at all. Now the Cash reaches its Maximum limit and oldest entry needs to be deleted. Here Oldest 
 * means the entry which has been not used since longest time, and not which has been in the Cache for longest 
 * time. Since the first ( thought oldest ) is being used very frequently for read operation, it should not be 
 * deleted from cache
 */
public class LRUCacheImplementation<Key, Value> {
	
	private final int maxSize;
	private ConcurrentHashMap<Key, Value> map;
	private ConcurrentLinkedQueue<Key> queue;
	
	public LRUCacheImplementation(final int maxSize) {
		this.maxSize = maxSize;
		map = new ConcurrentHashMap<>(maxSize);
		queue = new ConcurrentLinkedQueue<>();
	}
	
	
	public void put(final Key key, final Value value) {
		if(map.get(key) != null) {
			queue.remove(key);
		}
		while(queue.size() >= maxSize) {
			Key oldestKey = queue.poll();
			if(null != oldestKey) {
				queue.remove(key);
			}
		}
		queue.add(key);
		map.put(key, value);
		
	}
	
	public Value get(Key key) {
	    // update the least recently used key here as well
		return map.get(key);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
