package Objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Classes.Main;

public class Bomb {
	public int timer = 0;
	private int smokeTimer = 0;
	public int xPos;
	public int yPos;
	private int sxPos;
	private int syPos;
	public boolean isPlaced = false;
	public boolean isSmoke = false;
	public BufferedImage img = Main.window.tnt;
	public int power = 1;
	
	public void placeBomb(int x, int y){
		isPlaced = true;
		this.xPos = x;
		this.yPos = y;
	}
	
	//detonation of bomb
	public void countDown(){
		boolean up = true;
		boolean left = true;
		boolean down = true;
		boolean right = true;
		if(this.isPlaced){
			if(timer == 150){
				//bomb blowup and block detection
				for(int x = 1; x <= power; x++){
					if(xPos + x < 31){
						if(Main.window.board[xPos + x][yPos].isBombable() && right){
							Main.window.board[xPos + x][yPos].bombTile();
							right = false;
						}
						if(right){
							Main.window.bombSmoke[xPos + x][yPos].xPos = xPos + x;
							Main.window.bombSmoke[xPos + x][yPos].yPos = yPos;
							Main.window.bombSmoke[xPos + x][yPos].exists = true;
						}
					}
					if(xPos - x > 0){
						if(Main.window.board[xPos - x][yPos].isBombable() && left){
							Main.window.board[xPos - x][yPos].bombTile();
							left = false;
						}
						if(left){
							Main.window.bombSmoke[xPos - x][yPos].xPos = xPos - x;
							Main.window.bombSmoke[xPos - x][yPos].yPos = yPos;
							Main.window.bombSmoke[xPos - x][yPos].exists = true;
						}
					}
				}
				for(int y = 1; y <= power; y++){
					if(yPos + y < 17){
						if(Main.window.board[xPos][yPos + y].isBombable() && down){
							Main.window.board[xPos][yPos + y].bombTile();
							down = false;
						}
						if(down){
							Main.window.bombSmoke[xPos][yPos + y].xPos = xPos;
							Main.window.bombSmoke[xPos][yPos + y].yPos = yPos + y;
							Main.window.bombSmoke[xPos][yPos + y].exists = true;
						}
					}
					if(yPos - y > 0){	
						if(Main.window.board[xPos][yPos - y].isBombable() && up){
							Main.window.board[xPos][yPos - y].bombTile();
							up = false;
						}
						if(up){
							Main.window.bombSmoke[xPos][yPos - y].xPos = xPos;
							Main.window.bombSmoke[xPos][yPos - y].yPos = yPos - y;
							Main.window.bombSmoke[xPos][yPos - y].exists = true;
						}
					}
				}
				Main.window.board[xPos][yPos].bombTile();
				Main.window.bombSmoke[xPos][yPos].xPos = xPos;
				Main.window.bombSmoke[xPos][yPos].yPos = yPos;
				Main.window.bombSmoke[xPos][yPos].exists = true;
				isPlaced = false;
				timer = 0;
				isSmoke = true;
				sxPos = xPos;
				syPos = yPos;
			}else{
				//flashing of bomb
				if(timer % 20 == 0 && timer <= 150){
					if(img == Main.window.tnt){
						img = Main.window.tnt2;
					}else{
						img = Main.window.tnt;
					}
				}
				timer++;
			}
		}
		//smoke removal
		if(isSmoke){
			for(int x = 0; x < 31; x++){
				for(int y = 0; y < 17; y++){
					if(smokeTimer == 20){
						if(Main.window.bombSmoke[x][y].exists && x == this.sxPos || y == this.syPos){
							Main.window.bombSmoke[x][y].exists = false;
						}
						if(smokeTimer == 20 && x == 30 && y == 16){
							smokeTimer = 0;
							isSmoke = false;
						}
					}
				}
			}
			smokeTimer++;
		}
	}
	
	public Rectangle getBounds(){
		int W = Main.window.panel.getWidth();
		int H = Main.window.panel.getHeight();
		if(isPlaced){
			return new Rectangle(xPos*W/31, yPos*H/17, W/31, H/17);
		}else{
			return new Rectangle(0,0,0,0);
		}
	}
}
