/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model
{
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    boolean east = true;
    boolean north = false;
    int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;
    int direction;
    
    
    public Model(int width, int height, int imgw, int imgh)
    {
       frameWidth = width;
       frameHeight = height;
       imgWidth = imgw;
       imgHeight = imgh;
    }
    public Model()
    {
       frameWidth = 500;
       frameHeight = 300;
       imgWidth = 165;
       imgHeight = 165;
    }
    public void updateLocationAndDirection()
    {
      if(xloc + imgWidth > frameWidth)
      {
         east = false;
      }
      else if( xloc < 0)
      {
         east = true;
      }
      
      if(yloc + imgHeight > frameHeight)
      {
         north = true;
      }
      else if( yloc < 0)
      {
         north = false;
      }
      
      if((east == true) && (north == false) )
      {
         xloc+=xIncr;
         yloc+=yIncr;
         direction = 0;
      }
      else if((east == false) && (north == true))
      {
    	   xloc-=xIncr;
         yloc-=yIncr;
         direction = 2;
    	}
      else if((east == true) && (north == true))
      {
         xloc+=xIncr;
         yloc-=yIncr;
         direction = 1;
      }
      else if((east == false) && (north == false))
      {
         xloc-=xIncr;
         yloc+=yIncr;
         direction = 3;
      }
      //System.out.println("XY(" +xloc+", " +yloc+")");
    }
    public int getX()
    {
      return xloc;
    }
    public int getY()
    {
      return yloc;
    }
    public int getDirect()
    {
      return direction;
    }
}