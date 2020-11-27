import java.awt.Image;
import java.util.Random;

import tbs.simpleapp.SimpleApp;
import util.FileUtil;

public class PigeonGame extends SimpleApp {
	//create random object
	Random random = new Random();
	
	//Declare random x value
	//-200 because the screen goes from -200 to 200 not 0 to 400
	int x = random.nextInt(400) - 200;
	
	//Declare random y value
	int y = random.nextInt(400) - 200;
	
    //Declare background image
	Image bg;
	//Declare pigeon image
    Image pigeon;
    //Declare explosion image
    Image explosion;
    int explosionX = 0;
    int explosionY = 0;
    
    int frameNumber;
    
    double gradient = 0;
 
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        new PigeonGame();
    }

    public void main() {
    	//Import background image
        bg = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/background.png");
        //Import pigeon image
        pigeon = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/bird.png");
        //Import explosion image
        explosion = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/explosion.png");
    }
    
    public void onFrame() {
    	//Draw the Background 
        screen.drawImage(bg, 0, 0);
        
        //Draw the Pigeon at random position
        screen.drawImage(pigeon,(int) x,(int) y, 0, (frameNumber / 2) * 128, 192, 128);
        /*screen.drawImage(image,
        				x-coord of where to draw pigeon on screen,y-coord of where to draw pigeon on screen,
        				x-coord of where to take it from the pic, y-coord of where to take it from the pic,
        				*each pigeon is at x-coord 0 and is 128 tall in height
        				width, height)*/
        frameNumber = (frameNumber + 1) % 18;
        
        //Code to get pigeon to move around randomly
        int targetDestinationX = random.nextInt(400)-random.nextInt(300);
        while(targetDestinationX == x)
        	targetDestinationX = random.nextInt(400)-random.nextInt(300);
        int targetDestinationY = random.nextInt(400)-random.nextInt(300);
        gradient = (targetDestinationY - y)/(targetDestinationX - x);
        if(x < targetDestinationX) 
        {
        	x += 5;
        	y += gradient;
        }
        else if (x > targetDestinationX) 
        {
        	x -= 10;
        	y -= gradient;
        }
        
        screen.drawImage(explosion, explosionX, explosionY);
        
        
    }
    
    public void onMouseClick(int x, int y)
    {
    	explosionX = x;
    	explosionY = y;
    }
}