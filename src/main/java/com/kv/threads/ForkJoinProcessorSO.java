package com.kv.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinProcessorSO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSearchRecursiveTask task = new FileSearchRecursiveTask("/Users");
        //ForkJoinPool pool = (ForkJoinPool) Executors.newWorkStealingPool(5);
        ForkJoinPool pool = new ForkJoinPool();
        List<String> files = pool.invoke(task);
        
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
		}while((!task.isDone()));
        
        pool.shutdown();
        System.out.println("Total  no of files with hello" + files.size());
	}

}


class FileSearchRecursiveTask extends RecursiveTask<List<String>> {
    private String path;
    public FileSearchRecursiveTask(String path) {
        this.path = path;
    }

    @Override
    protected List<String> compute() {
        File mainDirectory = new File(path);
        List<String> filetedFileList = new ArrayList<>();
        List<FileSearchRecursiveTask> recursiveTasks = new ArrayList<FileSearchRecursiveTask>();
        if(mainDirectory.isDirectory()) {
            System.out.println(Thread.currentThread() + " - Directory is " + mainDirectory.getName());
            if(mainDirectory.canRead()) {
                File[] fileList = mainDirectory.listFiles();
                for(File file : fileList) {
                    System.out.println(Thread.currentThread() + "Looking into:" + file.getAbsolutePath());
                    if(file.isDirectory()) {
                        FileSearchRecursiveTask task = new FileSearchRecursiveTask(file.getAbsolutePath());
                        task.fork();
                        recursiveTasks.add(task);
                        
                    } else {
                        if (file.getName().contains("hello")) {
                            System.out.println(file.getName());
                            filetedFileList.add(file.getName());
                        }
                    }
                }
            }

            for(FileSearchRecursiveTask task : recursiveTasks) {
              filetedFileList.addAll(task.join());
            }

    }
    return filetedFileList;
    }
}
