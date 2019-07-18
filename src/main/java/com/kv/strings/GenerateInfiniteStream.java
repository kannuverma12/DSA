package com.kv.strings;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GenerateInfiniteStream {

	public static void main(String[] args) {
		try {
			for(int i=0;;i++) {
				
				String content = "2018-06-12 09:39:35,043 INFO org.apache.hadoop.ipc.Client: Retrying connect to server: localhost/192.168.202.29:8020. Already tried 4 time(s); retry policy is RetryUpToMaximumCountWithFixedSleep(maxRetries=10, sleepTime=1000 MILLISECONDS)\n" + 
						"2018-06-12 09:39:35,146 INFO org.apache.hadoop.hdfs.server.datanode.DataNode: DatanodeCommand action : DNA_REGISTER from localhost/192.168.202.29:8020 with active state\n" + 
						"2018-06-12 09:39:35,259 INFO org.apache.hadoop.hdfs.server.datanode.DataNode: Block pool BP-1374889709-192.168.202.29-1527584754887 (Datanode Uuid 6b860f93-da36-4a06-9b40-e9d825a01030) service to localhost/192.168.202.29:8020 beginning handshake with NN\n" + 
						"2018-06-12 09:39:35,291 INFO org.apache.hadoop.hdfs.server.datanode.DataNode: Block pool Block pool BP-1374889709-192.168.202.29-1527584754887 (Datanode Uuid 6b860f93-da36-4a06-9b40-e9d825a01030) service to localhost/192.168.202.29:8020 successfully registered with NN\n" + 
						"2018-06-12 09:39:35,306 INFO org.apache.hadoop.hdfs.server.datanode.DataNode: Successfully sent block report 0x1c9a41b195a00,  containing 1 storage report(s), of which we sent 1. The reports had 4 total blocks and used 1 RPC(s). This took 1 msec to generate and 11 msecs for RPC and NN processing. Got back one command: FinalizeCommand/5.\n" + 
						"2018-06-12 09:39:35,307 INFO org.apache.hadoop.hdfs.server.datanode.DataNode: Got finalize command for block pool BP-1374889709-192.168.202.29-1527584754887\n" + 
						"2018-06-12 10:31:38,014 INFO org.apache.hadoop.util.JvmPauseMonitor: Detected pause in JVM or host machine (eg GC): pause of approximately 1035ms\n" + 
						"No GCs detected"+ i;
				
				Files.write(Paths.get("/Users/karanverma/Downloads/bigFile.txt"), content.getBytes(), StandardOpenOption.APPEND);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
