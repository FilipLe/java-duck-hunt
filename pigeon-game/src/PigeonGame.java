import java.awt.Image;

import tbs.simpleapp.SimpleApp;
import util.FileUtil;

public class PigeonGame extends SimpleApp {
    Image bg;
    Image image;
    int frameNumber;
 
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        new PigeonGame();
    }

    public void main() {
        bg = FileUtil.loadImage("C:/Users/lewis/Desktop/worksheets/background.png");
        image = FileUtil.loadImage("C:/Users/lewis/Desktop/worksheets/bird.png");
    }
    
    public void onFrame() {
        screen.drawImage(bg, 0, 0);
        screen.drawImage(image, 0, 0, 0, (frameNumber / 2) * 128, 192, 128);
        frameNumber = (frameNumber + 1) % 18;
    }
}