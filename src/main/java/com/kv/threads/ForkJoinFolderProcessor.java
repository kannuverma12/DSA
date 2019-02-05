package com.kv.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


/*
 * program that will search for files with a determined extension inside a folder and its subfolders
 */
public class ForkJoinFolderProcessor {

	public static void main(String[] args) {
		
		
		
		ForkJoinPool pool = new ForkJoinPool();
		
		
		MyFolderProcessor hadoop = new MyFolderProcessor("/Users/", "log");
		//MyFolderProcessor t8 = new MyFolderProcessor("/Users/karan.verma/Documents/apache-tomcat-9.0.2", "log");
		//MyFolderProcessor t9 = new MyFolderProcessor("/Users/karan.verma/Documents/apache-tomcat-8.5.20", "log");
		
		pool.execute(hadoop);
		//pool.execute(t8);
		//pool.execute(t9);
		
		do {
			System.out.println("---------------------");
			System.out.println("Parallelism : "+pool.getParallelism());
			System.out.println("Active Threads : "+pool.getActiveThreadCount());
			System.out.println("Task Count : "+pool.getQueuedTaskCount());
			System.out.println("Steal Count : "+pool.getStealCount());
			
			System.out.println("---------------------");
			
			try
	         {
	            TimeUnit.SECONDS.sleep(1);
	         } catch (InterruptedException e)
	         {
	            e.printStackTrace();
	         }
		}while((!hadoop.isDone()));// || (!t8.isDone()) || (!t9.isDone()));
		
		pool.shutdown();

		List<String> results = hadoop.join();
		System.out.println("Hadoop: Files found  : " + results.size()+" "+results.toString());
		//results = t8.join();
		System.out.println("T8: Files found  : " + results.size()+" "+results.toString());
		//results = t9.join();
		System.out.println("T9: Files found  : " + results.size()+" "+results.toString());
		 
	}

}



class MyFolderProcessor extends RecursiveTask<List<String>>{

	private static final long serialVersionUID = 1L;
	
	private final String filepath;
	private final String fileExt;

	public MyFolderProcessor(String path, String extension) {
		this.filepath = path;
		this.fileExt = extension;
	}
	
	@Override
	protected List<String> compute() {

		List<String> list = new ArrayList<String>();
		List<MyFolderProcessor> tasks = new ArrayList<MyFolderProcessor>();
		
		File file = new File(filepath);
	    File content[] = file.listFiles();
	    
	    if(content != null) {
	    		for(File f : content) {
	    			if(f.isDirectory()) {
	    				MyFolderProcessor task = new MyFolderProcessor(f.getAbsolutePath(), fileExt);
	    				task.fork();
	    				tasks.add(task);
	    			}else {
	    				if(checkFile(f.getName()))
	    					list.add(f.getAbsolutePath());
	    			}
	    		}
	    }
		if (tasks.size() > 50) {
			System.out.println("tasks ran."+ file.getAbsolutePath()+" "+ tasks.size());
		}
		addResultsFromTasks(list, tasks);

		return list;
	}
	
	private void addResultsFromTasks(List<String> list, List<MyFolderProcessor> tasks) {
		for (MyFolderProcessor item : tasks) {
			list.addAll(item.join());
		}
	}

	private boolean checkFile(String name) {
		return name.endsWith(fileExt);
	}
	
}
