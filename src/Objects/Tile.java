package Objects;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Classes.Main;


public class Tile {
	public BufferedImage img;
	public int xCord;
	public int yCord;
	
	//Sets the tile image
	public void genTile(BufferedImage image, int x, int y){
		this.img = image;
		this.xCord = x;
		this.yCord = y;
	}
	
	//will set the tile to floor if it is bombable
	public void bombTile(){
		int chance = (int) (Math.random()*100 + 1);
		if(this.img == Main.window.breakable){
			this.img = Main.window.floor;
			if(chance <= 15 && Main.clientThread.isClient == false){
				Main.window.powerup[xCord][yCord].xPos = xCord;
				Main.window.powerup[xCord][yCord].yPos = yCord;
				Main.window.powerup[xCord][yCord].exists = true;
			}
		}
	}
	
	public boolean isBombable(){
		if(this.img == Main.window.floor){
			return false;
		}else{
			return true;
		}
	}
	
	public Rectangle getBounds(){
		int W = Main.window.panel.getWidth();
		int H = Main.window.panel.getHeight();
		if(img != Main.window.floor){
			return new Rectangle(xCord*W/31, yCord*H/17, W/31, H/17);
		}else{
			return new Rectangle(0,0,0,0);
		}
	}
}
