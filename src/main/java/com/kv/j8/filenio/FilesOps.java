package com.kv.j8.filenio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesOps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		try(Stream<Path> pathStream = Files.list(Paths.get("/Users/bng/Documents/server_pull"))){
			
			String joined = pathStream
								.map(String::valueOf)
								.filter(path -> !path.startsWith("."))
								.sorted()
								.collect(Collectors.joining(";\n "));
			System.out.println("Joined = "+joined);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		
		
		// Finding a file upto x level in File Directory using NIO Files.find
		Path start = Paths.get("/Users/bng/Documents/server_pull");
		int maxDepth = 5;
		try(Stream<Path> stream = Files.find(start, 
											maxDepth, 
											(path, attr) -> String.valueOf(path).endsWith(".json"))){
			String fileName = stream
								.sorted()
								.map(String::valueOf)
								.filter((path) -> {
									//System.out.println("In Filter : "+path);
									return String.valueOf(path).endsWith("system_health_12_9_TestServer_India_172_20_12_2.json");
								})
								.collect(Collectors.joining());
			System.out.println("fileName : "+fileName);
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		// Finding a file upto x level in File Directory using NIO Files.walk
		
		Path startWalk = Paths.get("/Users/bng/Documents/server_pull");
		int depth = 5;
		try( Stream<Path> stream1 = Files.walk(startWalk, 
												depth)){
			String walkedFile = stream1
								.map(String::valueOf)
								.filter(path -> {
									return String.valueOf(path).endsWith("system_health_12_9_TestServer_India_172_20_12_2.json");
								})
								.sorted()
								.collect(Collectors.joining());
			System.out.println("walkedFile = "+walkedFile);
			
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		
		try(Stream<Path> stream = Files.find(start, 
											maxDepth, 
											(path, attr) -> path.getFileName().toString().equals("system_health_12_9_TestServer_India_172_20_12_2.json"))){
			
			System.out.println("fileName FindAny : "+stream.findAny().isPresent());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		// Reading a file using Future and AsynchronousFileChannel
		
		try(AsynchronousFileChannel afileChannel = AsynchronousFileChannel.open(Paths.get("/Users/bng/Documents/server_pull/system_health_12_9_TestServer_India_172_20_12_2.json"), StandardOpenOption.READ)) {
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			long position = 0;

			Future<Integer> operation = afileChannel.read(buffer, position);
			
			

			while(!operation.isDone()){
				//do something
			}

			buffer.flip();
			byte[] data = new byte[buffer.limit()];
			buffer.get(data);
			System.out.println(new String(data));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try(AsynchronousFileChannel asyncfileChannel = AsynchronousFileChannel.open(Paths.get("/Users/***/Documents/server_pull/system_health_12_9_TestServer.json"), StandardOpenOption.READ)){
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			ByteBuffer attachment = ByteBuffer.allocate(1024);
			asyncfileChannel.read(buffer, 0, attachment, new CompletionHandler<Integer, ByteBuffer>() {
			    @Override
			    public void completed(Integer result, ByteBuffer attachment) {
			        System.out.println("result = " + result);
	
			        attachment.flip();
			        byte[] data = new byte[attachment.limit()];
			        attachment.get(data);
			        System.out.println(new String(data));
			        attachment.clear();
			    }
	
			    @Override
			    public void failed(Throwable exc, ByteBuffer attachment) {
	
			    }
			});
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}

