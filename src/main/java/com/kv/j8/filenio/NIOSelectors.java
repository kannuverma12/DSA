package com.kv.j8.filenio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOSelectors {
	public static void main(String[] args){
		
		try {
			Selector selector = Selector.open();
			ServerSocketChannel channel = ServerSocketChannel.open();
			//FileChannel channel = new FileInputStream("").getChannel();
			
			channel.configureBlocking(false);

			SelectionKey key1 = channel.register(selector, SelectionKey.OP_READ);
			SelectionKey key2 = channel.register(selector, SelectionKey.OP_WRITE);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
