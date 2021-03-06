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
        win = FileUtil.loadImage("/Users/nguyenle/pigeon-game/pigeon game graphics/win.png");
    }
    
    
    public void onFrame() {
    	//Draw the Background 
        screen.drawImage(bg, 0, 0);
        
        //While Pigeon is alive, let it fly in random movements
    	if(pigeonDead == false)
    	{
	        //Draw the Pigeon at random position
	        screen.drawImage(pigeon,
	        				(int) x,(int) y, 
	        				0, (frameNumber / 2) * 128, 
	        				192, 128);
	        
	        frameNumber = (frameNumber + 1) % 18;
    	}
    	
    	//Explanation of the screen.drawImage() parameters
    	
        /*screen.drawImage(
         				image,
        				x-coord of where to draw pigeon on screen,y-coord of where to draw pigeon on screen,
        				x-coord of where to take it from the pic, y-coord of where to take it from the pic,
        				*each pigeon is at x-coord 0 and is 128 tall in height
        				width, height)*/
           
        
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
        
        /*
         * To get rid of the pigeon, we aren't going to pause the frames
         * what we can do is let the frames continue running
         * when pigeon is hit, make it fall down
         * 
         * screen.drawImage(pigeon,
        				(int) x,(int) (y-countFrame*10), 
        				0, (frameNumber / 2) * 128, 
        				192, 128);
        				
         * The pigeon falls instantaneously 
         * The pigeon falls below the screen
         * The frames still run
         * 
         * 
         * 
         * Also create a var to count num of pigeons hit
         * If 5 pigeon hit --> throw in a pic of a trophy --> win
         */
        
        //If explosion hits pigeon, pigeon is dead. Which stops the game (within 30 pixels radius)
        if(Math.abs((int)(explosionX-x)) < 30 && Math.abs((int)(explosionY-y)) < 30) 
        {
        	//If it hits the pigeon, make the pigeon fall down countFramex10 units down
        	y -= countFrame*10;
        	//Stop the game
        	pigeonDead = true;
        	
        }    	
        
        //When pigeon is killed, display "winner"
        if(pigeonDead == true)
        	screen.drawImage(win, explosionX, explosionY);
        
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