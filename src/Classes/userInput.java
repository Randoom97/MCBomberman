package Classes;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class userInput implements MouseListener, KeyListener{
	//keep in mind that the window frame is counted in the cords. Add 8 for the width and 30 for the height.
	
	public boolean SPH = false;
	public boolean MPH = false;
	public boolean JH = false;
	public boolean HH = false;
	public boolean RH = false;
	
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		int W = Main.window.panel.getWidth();
		int H = Main.window.panel.getHeight();
		
		//What is pressed in the title screen
		if(Main.window.ScreenType == Main.window.isTitle){
			
			//Single player button pressed
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)-(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)-(H/20)+30){
				SPH = true;
				}else{
					SPH = false;
				}
			}
		
			//Multiplayer button pressed
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)+(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2+(H/15) + (H/15)-(H/20)+30){
					MPH = true;
				}else{
					MPH = false;
				}
			}
		}
		
		//What is pressed in the Multiplayer screen
		if(Main.window.ScreenType == Main.window.isMultiplayer){
			
			//Host button pressed
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)-(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)-(H/20)+30){
					HH = true;
				}else{
					HH = false;
				}
			}
			
			//Join button pressed
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)+(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2+(H/15) + (H/15)-(H/20)+30){
					JH = true;
				}else{
					JH = false;
				}
			}
		}
		
		//Restart button
		if(Main.window.ScreenType == Main.window.isGame && !Main.serverThread.isServer && !Main.clientThread.isClient){
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)-(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)-(H/20)+30){
					RH = true;
				}else{
					RH = false;
				}
			}
		}
		
	}

	public void mouseReleased(MouseEvent arg0) {
		int W = Main.window.panel.getWidth();
		int H = Main.window.panel.getHeight();
		
		//Restart button
		if(Main.window.ScreenType == Main.window.isGame && Main.window.creep[0].xCord < -1 && Main.window.creep[0].yCord < -1 && !Main.serverThread.isServer && !Main.clientThread.isClient){
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)-(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)-(H/20)+30){
					Main.window.genBoard();
					Main.window.creep[0].lives = 3;
					Main.window.creep[0].spawn();
					for(int i = 0; i < Main.window.creep[0].bombs.length; i++){
						Main.window.creep[0].bombs[i].power = 1;
					}
				}
			}
			RH = false;
		}
		
		//What is pressed in the Multi player screen
		if(Main.window.ScreenType == Main.window.isMultiplayer){
			//Host button released
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)-(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)-(H/20)+30){
					Main.window.gameScreen();
					new Thread(Main.serverThread).start();
				}
			}
			HH = false;
		
			//Join button released
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)+(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)+(H/20)+30){
					Main.window.gameScreen();
					new Thread(Main.clientThread).start();
				}
			}
			JH = false;
		}
		
		//What is pressed in the Title screen
		if(Main.window.ScreenType == Main.window.isTitle){
			//Singleplayer button released
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)-(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)-(H/20)+30){
					Main.window.gameScreen();
				}
			}
			SPH = false;
		
			//Multiplayer button released
			if(arg0.getX() > (W/2)-(W/6)+8 && arg0.getY() > H/2-(H/15)+(H/20)+30){
				if(arg0.getX() < (W/2)-(W/6) + (W/3)+8 && arg0.getY() < H/2-(H/15) + (H/15)+(H/20)+30){
					Main.window.multiPlayer();
				}
			}
			MPH = false;
		}
	}

	public void keyPressed(KeyEvent arg0) {
		//Multiplayer screen
		if(Main.window.ScreenType == Main.window.isMultiplayer){
			//IP input
			if(arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE && Main.window.serverIp.length() > 0){
				Main.window.serverIp = Main.window.serverIp.substring(0, Main.window.serverIp.length()-1);
			}else if(arg0.getKeyChar() != KeyEvent.CHAR_UNDEFINED && arg0.getKeyCode() != KeyEvent.VK_BACK_SPACE){
				Main.window.serverIp += arg0.getKeyChar();
			}
		}
		
		//Game screen
		if(Main.window.ScreenType == Main.window.isGame && !Main.window.creep[0].inSmoke && Main.window.creep[0].xCord > -1){
			
			//Bomb placement / movement
			if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
				Main.window.creep[0].placeBomb();
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_W){
				up = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_A){
				left = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_S){
				down = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_D){
				right = true;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_SHIFT){
				if(up){
					Main.window.creep[0].kickBomb(1);
				}
				if(right){
					Main.window.creep[0].kickBomb(2);
				}
				if(down){
					Main.window.creep[0].kickBomb(3);
				}
				if(left){
					Main.window.creep[0].kickBomb(4);
				}
			}
			
		}
	}

	public void keyReleased(KeyEvent arg0) {
		if(Main.window.ScreenType == Main.window.isGame && Main.window.creep[0].xCord > -1){
			
			if(arg0.getKeyCode() == KeyEvent.VK_W){
				up = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_A){
				left = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_S){
				down = false;
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_D){
				right = false;
			}
			
		}
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

}
