import java.awt.Image;
import java.util.Random;

import tbs.simpleapp.SimpleApp;
import util.FileUtil;

public class PigeonGame extends SimpleApp {
	//create random object
	Random random = new Random();
	
	
	//This generates random starting position of pigeon
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
    //Boolean to start explosion effect
    boolean explosionStatus = false;
    
    
    //Declare win image
    Image win;
    
    //Boolean to check whether pigeon is dead
    boolean pigeonDead = false;
    
    
    int frameNumber;
    
    //Var to count number of frames
    //Initialized --> countFrame = 0
    int countFrame;
    
    
    //Var to store gradient of pigeon's movement
    double gradient = 0;
 
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        new PigeonGame();
    }

    
    //Importing images
    public void main() {
    	//Import background image
        bg = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/background.png");
        //Import pigeon image
        pigeon = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/bird.png");
        //Import explosion image
        explosion = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/explosion.png");
        //Import win image
        //win = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/win.png");
    }
    
    
    public void onFrame() {
    	//While Pigeon is alive
    	if(pigeonDead == false)
    	{
	    	//Draw the Background 
	        screen.drawImage(bg, 0, 0);
	        
	        
	        //Draw the Pigeon at random position
	        screen.drawImage(pigeon,
	        				(int) x,(int) y, 
	        				0, (frameNumber / 2) * 128, 
	        				192, 128);
	        
	        /*screen.drawImage(
	         				image,
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
	        //Move right
	        if(x < targetDestinationX) 
	        {
	        	x += 5;
	        	y += gradient;
	        }
	        //Move left
	        else if (x > targetDestinationX) 
	        {
	        	x -= 10;
	        	y -= gradient;
	        }
	        
	        //Loop for the explosion effect to stay on for a few sec and disappear
	        //Do if instead of while because it is on frame (equivalent to while)
	        if(explosionStatus == true)
	        {
	        	screen.drawImage(explosion, explosionX, explosionY);
	        	//Count number of frames passed
	        	countFrame += 1;
	        	
	        	//If 30 frames already passed
	        	if(countFrame > 30) {
	        		//Get rid of explosion effect
	        		explosionStatus = false;
	        		//reset counter
	        		countFrame = 0;
	        	}
	        		
	        }
	        
	        //If explosion hits pigeon, pigeon is dead. Which stops the game
	        if(Math.abs((int)(explosionX-x)) < 20 && Math.abs((int)(explosionY-y)) < 20) 
	        {
	        	pigeonDead = true;
	        	System.out.println("Good game");
	        	//screen.drawImage(win, explosionX, explosionY);
	        }
    	}
        
    }
    
    
    
    //When mouse clicked, trigger explosion
    public void onMouseClick(int x, int y)
    {
    	//Position of explosion
    	explosionX = x;
    	explosionY = y;
    	
    	//When status true --> trigger explosion
    	explosionStatus = true;
    }
}