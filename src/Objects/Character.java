package Objects;

import java.awt.Rectangle;

import Classes.Main;


public class Character {
	public int xCord = -50;
	public int yCord = -50;
	public int xUpdate;
	public int yUpdate;
	public Bomb[] bombs = new Bomb[3];
	public boolean inSmoke;
	public int inSmokeTimer = 0;
	public int lives = 3;
	
	//spawns the character
	public void spawn(){
		int xRand = (int) (Math.floor(Math.random()*14)+1)*2+1;
		int yRand = (int) (Math.floor(Math.random()*7)+1)*2+1;
		xCord = xRand * 1600/31 + 8;
		yCord = yRand * 900/17 + 8;
		
		//breaks the blocks around the player for spawning
		for(int x = -1; x < 2; x++){
			if(Main.window.board[xRand + x][yRand].img != Main.window.border){
				Main.window.board[xRand + x][yRand].genTile(Main.window.floor, xRand + x, yRand);
			}
		}
		for(int y = -1; y < 2; y++){
			if(Main.window.board[xRand][yRand + y].img != Main.window.border){
				Main.window.board[xRand][yRand + y].genTile(Main.window.floor, xRand, yRand + y);
			}
		}
	}
	
	public Rectangle getBounds(){
		int W = Main.window.panel.getWidth();
		int H = Main.window.panel.getHeight();
		return new Rectangle(xCord*W/1600, yCord*H/900, W/51, H/28);
	}
	
	public void placeBomb(){
		for(int i = 0; i < bombs.length; i++){
			if(bombs[i].isPlaced != true){
				bombs[i].placeBomb((xCord+8)*31/1600, (yCord+8)*17/900);
				break;
			}
		}
	}
	
	public void kickBomb(int direction){
		for(int i = 0; i < bombs.length; i++){
			if(bombs[i].isPlaced != true){
				if(direction == 1){
					if(yCord > 208){
						bombs[i].placeBomb((xCord+8)*31/1600, (yCord+8-156)*17/900);
						break;
					}
				}
				if(direction == 2){
					if(xCord < 1392){
						bombs[i].placeBomb((xCord+8+156)*31/1600, (yCord+8)*17/900);
						break;
					}
				}
				if(direction == 3){
					if(xCord < 692){
						bombs[i].placeBomb((xCord+8)*31/1600, (yCord+8+156)*17/900);
						break;
					}
				}
				if(direction == 4){
					if(yCord > 208){
						bombs[i].placeBomb((xCord+8-156)*31/1600, (yCord+8)*17/900);
						break;
					}
				}
			}
		}
	}
	
	public void wasHit(){
		if(inSmoke){
			if(inSmokeTimer == 1){
				lives--;
			}
			if(inSmokeTimer == 40){
				inSmoke = false;
				inSmokeTimer = 0;
			}
			if(Main.window.creep[0].inSmoke){
				Main.window.input.up = false;
				Main.window.input.right = false;
				Main.window.input.down = false;
				Main.window.input.left = false;
			}
			inSmokeTimer++;
		}
	}
}
