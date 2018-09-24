/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
 
 /*
 WHat I had to do was create a subclass within this for animation, because the creation of the frame(everything left in the animaton.java)
 could not go in the constructor because it makes a call to the constructor, without the subclass it kept calling itself until it ran out
 of memory.
 basically the subclass is what extends jpanel and paint, createimage, and the constructor for that class got moved there.
 That made the frame load and the orc draw but it would go really fast then stop.
 I then added the try/catch statement that was in the for loop in the main for the original animation.java and moved that to update.
 This fixed the animation moving to fast until it froze.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class View
{
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    final int frameCount = 10;
    int picNum = 0;
    //BufferedImage[] pics;
    BufferedImage[][] choices = new BufferedImage[8][];
    int xloc = 0;
    int yloc = 0;
    int direction = 0;
    JFrame frame = new JFrame();
    
    public void update(int x, int y, int d)
    {
      xloc = x;
      yloc = y;
      direction = d;
      frame.repaint();
      try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    }
    //Constructor: get image, segment and store in array
    public View(){
      frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    }  
    
    public int getWidth()
    {
      return frameWidth;
    }
    public int getHeight()
    {
      return frameHeight;
    }
    public int getImageWidth()
    {
      return imgWidth;
    }
    public int getImageHeight()
    {
      return imgHeight;
    }
    
    class Animation extends JPanel
    {
      public Animation()
      {
         String[] addresses= {"images/orc/orc_forward_southeast.png","images/orc/orc_forward_northeast.png","images/orc/orc_forward_northwest.png",
          "images/orc/orc_forward_southwest.png","images/orc/orc_forward_south.png","images/orc/orc_forward_north.png",
           "images/orc/orc_forward_east.png","images/orc/orc_forward_west.png"};
         BufferedImage img;
          for(int j =0; j < 8; j++)
          {
            img = createImage(addresses[j]);
          	choices[j] = new BufferedImage[10];
         	for(int i = 0; i < frameCount; i++)
    	      	choices[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
           }
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
      }
      
          //Read image from file and return
      private BufferedImage createImage(String s){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(s));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
      
          //Override this JPanel's paint method to cycle through picture array and draw images
     public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
      g.drawImage(choices[direction][picNum], xloc, yloc, Color.gray, this);
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }
    
    }
}