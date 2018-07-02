package com.kv.j8.images;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This application renders a color image on a pure black-and-white image
 * created from scratch. The JAI API is not used in this example.
 */
public class CreateBWImageNoJAIRender {
	
	
	public static void main(String[] args) throws IOException {
		// Read an image.
		BufferedImage input = ImageIO.read(new File("/Users/bng/Documents/Photograph.jpg"));
		// Create a black-and-white image of the same size.
		BufferedImage im = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		// Get the graphics context for the black-and-white image.
		Graphics2D g2d = im.createGraphics();
		// Render the input image on it.
		g2d.drawImage(input, 0, 0, null);
		// Store the resulting image using the PNG format.
		ImageIO.write(im, "PNG", new File("/Users/bng/Documents/rendered.png"));
		
		
		
		ImageIO.write(new BufferedImage(
							ImageIO.read(new File("/Users/bng/Documents/Photograph.jpg")).getWidth(), 
							ImageIO.read(new File("/Users/bng/Documents/Photograph.jpg")).getHeight(), 
							BufferedImage.TYPE_BYTE_BINARY),
				"PNG", 
				new File("/Users/bng/Documents/renderedX.png")
				);
		
		
		
		
		
		
		
		BufferedImage bi = input; //Your BufferedImage goes here. null for compiler
		ColorConvertOp op = 
		    new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		ImageIO.write(bi, "PNG", new File("/Users/bng/Documents/rendered2.png"));
		op.filter(bi, bi);
		
		
		// SO Answer
		BufferedImage destImage = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = destImage.createGraphics();
	    g.drawImage(input, null, 0, 0);
	    g.dispose();


	    for (int width = 0; width < input.getWidth(); width++)
	    {
	        for (int height = 0; height < input.getHeight(); height++)
	        {

	           if (destImage.getRGB(width, height) == 255)
	            {
	               destImage.setRGB(width, height, 0);
	            }

	        }
	    }
	    ImageIO.write(destImage, "PNG", new File("/Users/bng/Documents/rendered3.png"));
	    
	    ImageIO.write(new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null)
	    		.filter(ImageIO.read(new File("/Users/bng/Documents/Photograph.jpg")), null), "PNG", new File("/Users/bng/Documents/rendered4.png"));
	}
}

class EntityDetail implements Comparable<EntityDetail>{
	
	private String name;
	private int id;
	
	public int getId(){
		return id;
	}
	
	public int hashcode(){
		int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + id;
        return result;
	}

	@Override
	public boolean equals(Object o){
		if (o == this) return true;
        if (!(o instanceof EntityDetail)) {
            return false;
        }

        EntityDetail ed = (EntityDetail) o;

        return ed.name.equals(name) &&
                ed.id == id ;
		
	}
	
	@Override
	public int compareTo(EntityDetail ed) {
		int compareId = ((EntityDetail) ed).getId();

		//ascending order
		return this.id - compareId;

		//descending order
		//return compareId - this.id;
	}
	
}
