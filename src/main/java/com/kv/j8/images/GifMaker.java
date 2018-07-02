package com.kv.j8.images;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.concurrent.CompletableFuture;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class GifMaker {

	
	
	public static void main(String[] args) throws Exception 
	{  
		
		CompletableFuture<A> promiseofA = CompletableFuture.supplyAsync(() -> new B());
		CompletableFuture<? extends A> promiseOfA1 = CompletableFuture.supplyAsync(() -> new B())
                .exceptionally(ex -> new B());
		
		
		GregorianCalendar gc = new GregorianCalendar();
		
		long minutesDiffdd = ChronoUnit.MINUTES.between(gc.toInstant(), gc.toInstant());
		
		    LocalDateTime timeNow = LocalDateTime.now();
		    LocalDateTime timeAfterSometime = timeNow.plusHours(4).plusMinutes(11);

		    System.out.println("timeNow = "+timeNow);
		    System.out.println("timeAfterSometime = "+timeAfterSometime);

		    long minutesDiff = ChronoUnit.MINUTES.between(timeNow, timeAfterSometime); // 251
		    long hoursDiff = ChronoUnit.HOURS.between(timeNow, timeAfterSometime); // 4

		    System.out.println("minutesDiff = "+minutesDiff);
		    System.out.println("hoursDiff = "+hoursDiff);
		
		
	  if (args.length > 1) 
	  {    
	    // grab the output image type from the first image in the sequence 
	    BufferedImage firstImage = ImageIO.read(new File(args[0]));   
	 
	    // create a new BufferedOutputStream with the last argument
	    ImageOutputStream output = new FileImageOutputStream(new File(args[args.length - 1]));    
	 
	    // create a gif sequence with the type of the first image, 1 second    
	    // between frames, which loops continuously    
	    GifSequenceWriter writer =  new GifSequenceWriter(output, firstImage.getType(), 1, false);    
	 
	    // write out the first image to our sequence...
	    writer.writeToSequence(firstImage);    
	    for(int i=1; i<args.length-1; i++) 
	    {      
	      BufferedImage nextImage = ImageIO.read(new File(args[i]));      
	      writer.writeToSequence(nextImage);    
	    }    
	    writer.close();    
	    output.close();  
	  } 
	  else 
	  {    
	    System.out.println("Usage: java GifSequenceWriter [list of gif files] [output file]");  
	  }
	}

}

class A{
	
}

class B extends A{
	
}

class GifSequenceWriter {
    public GifSequenceWriter(
        ImageOutputStream outputStream,
        int imageType,
        int timeBetweenFramesMS,
        boolean loopContinuously){}

    public void writeToSequence(RenderedImage img){}

    public void close(){}
}
