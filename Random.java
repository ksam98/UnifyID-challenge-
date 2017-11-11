import java.io.*;
import java.net.*;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/* The following method created a 128*128 RGB bitmap derived from truly random numbers
 * based on physical sensors 
 * */
public class Random {
	public static void main(String[] args)
	  {
		//dimension of the bitmap
		int width = 128;
		int height = 128;
		
	    try{
	    	//Opening a Http connection to receive a truly random number (min = 0, max = 255)
	    	String url = "https://www.random.org/integers/?num=1&min=0&max=255&col=1&base=10&format=plain&rnd=new";
	      	URL rand = new URL(url);
	      	HttpURLConnection connection = (HttpURLConnection) rand.openConnection();
	      	connection.setRequestMethod("GET");
	      
	      	//reading response from HttpConnection and storing the integer
	      	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      	int randNum = reader.read();
	      	
	      	//print out the random number to output
	      	System.out.println("Random number received: " + randNum);
	      	reader.close();
	      	
	      	//creating a 128*128 RGB bitmap based on the random number
	      	BufferedImage bmp = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	      	for(int i = 0; i < width; i++) {
	      		for(int j = 0; j < height; j++) {
	      			int r = ((int) (Math.random()*256 + 256)) % randNum;
	      			int g = ((int) (Math.random()*256 + 256)) % randNum;
	      			int b = ((int) (Math.random()*256 + 256)) % randNum;
	      			
	      			int pixel = (r << 16) | (g << 8) | b;
	      			bmp.setRGB(i, j, pixel);
	      		}
	      	}
	      	
	      	//displaying created bitMap
	      	JLabel jLabel = new JLabel(new ImageIcon(bmp));
	      	JPanel jPanel = new JPanel();
	      	jPanel.add(jLabel);
	      	JFrame frame = new JFrame();
	      	frame.add(jPanel);
	      	frame.setVisible(true);;
	      	
	    }catch(IOException e){
	    	System.out.println("The following IO exception was received: " + e);
	    }
	    
	    
	  }
}

