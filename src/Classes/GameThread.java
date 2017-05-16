package Classes;

import java.awt.Rectangle;


public class GameThread implements Runnable {
	private Thread thread;
	
	//Starts the tread
	public synchronized void start(){
		thread = new Thread(this);
		thread.run();
	}
	
	//Stops the thread
	public synchronized void stop(){
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	//The thread
	public void run() {
		while(true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Main.window.panel.repaint();
			updateCharacter();
			collision();
			updateBomb();
		}
	}
	
	//Moves the character
	public void updateCharacter(){
		if(Main.window.input != null){
			if(Main.window.input.up){
				Main.window.creep[0].yCord -= 3;
			}
			if(Main.window.input.left){
				Main.window.creep[0].xCord -= 3;
			}
			if(Main.window.input.down){
				Main.window.creep[0].yCord += 3;
			}
			if(Main.window.input.right){
				Main.window.creep[0].xCord += 3;
			}
		}
		for(int i = 0; i < Main.window.creep.length; i++){
			Main.window.creep[i].wasHit();
			if(Main.window.creep[i].lives <= 0){
				Main.window.creep[i].xCord = -50;
				Main.window.creep[i].yCord = -50;
			}
		}
	}
	
	//detects collision
	public void collision(){
		if(Main.window.ScreenType == Main.window.isGame){
			for(int x = 0; x < 31; x++){
				for(int y = 0; y < 17; y++){
					Rectangle tile = Main.window.board[x][y].getBounds();
					Rectangle powerup = Main.window.powerup[x][y].getBounds();
					Rectangle smoke = Main.window.bombSmoke[x][y].getBounds();
					Rectangle character = Main.window.creep[0].getBounds();
					
					//Character collides with a tile
					if(character.intersects(tile)){
						boolean intersects = true;
						if(intersects && Main.window.input.up){
							Main.window.creep[0].yCord += 3;
							character = Main.window.creep[0].getBounds();
							if(character.intersects(tile)){
								Main.window.creep[0].yCord -= 3;
								character = Main.window.creep[0].getBounds();
							}else{
								intersects = false;
							}
						}
						if(intersects && Main.window.input.left){
							Main.window.creep[0].xCord += 3;
							character = Main.window.creep[0].getBounds();
							if(character.intersects(tile)){
								Main.window.creep[0].xCord -= 3;
								character = Main.window.creep[0].getBounds();
							}else{
								intersects = false;
							}
						}
						if(intersects && Main.window.input.down){
							Main.window.creep[0].yCord -= 3;
							character = Main.window.creep[0].getBounds();
							if(character.intersects(tile)){
								Main.window.creep[0].yCord += 3;
								character = Main.window.creep[0].getBounds();
							}else{
								intersects = false;
							}
						}
						if(intersects && Main.window.input.right){
							Main.window.creep[0].xCord -= 3;
							character = Main.window.creep[0].getBounds();
							if(character.intersects(tile)){
								Main.window.creep[0].xCord += 3;
								character = Main.window.creep[0].getBounds();
							}else{
								intersects = false;
							}
						}
					}
					
					//Character collides with smoke
					if(character.intersects(smoke)){
						Main.window.creep[0].inSmoke = true;
					}
					
					for(int i = 0; i < Main.window.creep.length; i++){
						character = Main.window.creep[i].getBounds();
						
						//Character collides with a powerup
						if(character.intersects(powerup)){
							for(int j = 0; j < Main.window.creep[i].bombs.length; j ++){
								Main.window.creep[i].bombs[j].power++;
							}
							Main.window.powerup[x][y].exists = false;
						}
						
						//Bomb collides with smoke
						for(int k = 0; k < Main.window.creep[i].bombs.length; k++){
							Rectangle bomb = Main.window.creep[i].bombs[k].getBounds();
							if(smoke.intersects(bomb)){
								Main.window.creep[i].bombs[k].timer = 150;
							}
						}
						
					}
				}
			}
		}
	}
	
	//Updates the timer for the bomb
	public void updateBomb(){
		if(Main.window.ScreenType == Main.window.isGame){
			for(int j = 0; j < Main.window.creep.length; j++){
				for(int i = 0; i < Main.window.creep[j].bombs.length; i++){
					Main.window.creep[j].bombs[i].countDown();
				}
			}
		}
	}
}
