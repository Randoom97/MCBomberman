package Objects;

import java.awt.Rectangle;

import Classes.Main;

public class powerUp {
	public int xPos;
	public int yPos;
	public boolean exists = false;
	
	public Rectangle getBounds(){
		int W = Main.window.panel.getWidth();
		int H = Main.window.panel.getHeight();
		if(exists){
			return new Rectangle(xPos*W/31, yPos*H/17, W/31, H/17);
		}else{
			return new Rectangle(0,0,0,0);
		}
	}
}
