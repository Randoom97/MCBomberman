package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import Classes.Main;


public class Panel extends JPanel{
private static final long serialVersionUID = 1L;
	
	private Image img;
	private Graphics doubleB;
	private int W = getWidth();
	private int H = getHeight();
	
	//double buffer method
	public void update(Graphics g){
		if(img == null){
			img = createImage(this.getSize().width, this.getSize().height);
			doubleB = img.getGraphics();
		}
		
		doubleB.setColor(getBackground());
		doubleB.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		doubleB.setColor(getForeground());
		paint(doubleB);
		
		g.drawImage(img,  0, 0, this);
	}
	
	//what actually gets painted
	public void paint(Graphics g){
		W = getWidth();
		H = getHeight();
		
		//What renders no matter what;
		for(int x = 0; x < 31; x ++){
				for(int y = 0; y < 17; y++){
					g.drawImage(Main.window.board[x][y].img, x * W/31, y * H/17, W/31 + 1, H/17 + 1, this);
					if(Main.window.bombSmoke[x][y].exists){
						g.drawImage(Main.window.smoke, x*W/31, y*H/17, W/31 + 1, H/17 + 1, this);
					}
					if(Main.window.powerup[x][y].exists){
						g.drawImage(Main.window.tntUp, x*W/31, y*H/17, W/31, H/17, this);
					}
				}
			}
		
		//Title screen
		if(Main.window.ScreenType == Main.window.isTitle){
			g.drawImage(Main.window.overlay, 0, 0, W, H/17*3, this);
			g.drawImage(Main.window.overlay, 0, H/17*14, W, H/17*4, this);
			if(Main.window.input.SPH){
				g.drawImage(Main.window.singlePlayerHover, (W/2)-(W/6), H/2-(H/15)-(H/20), (W/3), (H/15), this);
			}else{
				g.drawImage(Main.window.singlePlayer, (W/2)-(W/6), H/2-(H/15)-(H/20), (W/3), (H/15), this);
			}
			if(Main.window.input.MPH){
				g.drawImage(Main.window.multiPlayerHover, (W/2)-(W/6), H/2-(H/15)+(H/20), (W/3), (H/15), this);
			}else{
				g.drawImage(Main.window.multiPlayer, (W/2)-(W/6), H/2-(H/15)+(H/20), (W/3), (H/15), this);
			}
		}
		
		//Multiplayer screen
		if(Main.window.ScreenType == Main.window.isMultiplayer){
			g.drawImage(Main.window.overlay, 0, 0, W, H/17*3, this);
			g.drawImage(Main.window.overlay, 0, H/17*14, W, H/17*3, this);
			if(Main.window.input.HH){
				g.drawImage(Main.window.hostHover, (W/2)-(W/6), H/2-(H/15)-(H/20), (W/3), (H/15), this);
			}else{
				g.drawImage(Main.window.host, (W/2)-(W/6), H/2-(H/15)-(H/20), (W/3), (H/15), this);
			}
			if(Main.window.input.JH){
				g.drawImage(Main.window.joinHover, (W/2)-(W/6), H/2-(H/15)+(H/20), (W/3), (H/15), this);
			}else{
				g.drawImage(Main.window.join, (W/2)-(W/6), H/2-(H/15)+(H/20), (W/3), (H/15), this);
			}
			
			//IP render
			g.drawImage(Main.window.IPSpot, (W/2)-(W/6), H/2-(H/15)+(H/20)*3, (W/3), (H/15), this);
			g.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, H/20));
			g.setColor(Color.white);
			g.drawString("IP: ",(W/2)-(W/6) + W/100,H/2-(H/15)+(H/20)*3 + H/20);
			g.drawString(Main.window.serverIp,(W/2)-(W/6) + W/20,H/2-(H/15)+(H/20)*3 + H/20);
			
		}
		
		//What renders in game;
		if(Main.window.ScreenType == Main.window.isGame){
			for(int j = 0; j < Main.window.creep.length; j++){
				for(int i = 0; i < Main.window.creep[j].bombs.length; i++){
					if(Main.window.creep[j].bombs[i].isPlaced){
						g.drawImage(Main.window.creep[j].bombs[i].img, Main.window.creep[j].bombs[i].xPos*W/31, Main.window.creep[j].bombs[i].yPos*H/17, W/31, H/17, this);
					}
				}
				
			}
			
			//Restart button
			if(!Main.serverThread.isServer && !Main.clientThread.isClient){
				if(Main.window.creep[0].xCord < 0 || Main.window.creep[0].yCord < 0){
					if(Main.window.input.RH){
						g.drawImage(Main.window.restartHover, (W/2)-(W/6), H/2-(H/15)-(H/20), (W/3), (H/15), this);
					}else{
						g.drawImage(Main.window.restart, (W/2)-(W/6), H/2-(H/15)-(H/20), (W/3), (H/15), this);
					}
				}
			}
			
			//Characters
			g.drawImage(Main.window.Creeper, Main.window.creep[0].xCord*W/1600, Main.window.creep[0].yCord*H/900, W/51, H/28, this);
			g.drawImage(Main.window.Zombie, Main.window.creep[1].xCord*W/1600, Main.window.creep[1].yCord*H/900, W/51, H/28, this);
			g.drawImage(Main.window.Pig, Main.window.creep[2].xCord*W/1600, Main.window.creep[2].yCord*H/900, W/51, H/28, this);
			g.drawImage(Main.window.Enderman, Main.window.creep[3].xCord*W/1600, Main.window.creep[3].yCord*H/900, W/51, H/28, this);
			for(int i = 0; i < Main.window.creep.length; i++){
				if(Main.window.creep[i].xCord > -1 && Main.window.creep[i].yCord > -1){
					g.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, H/17));
					g.setColor(Color.white);
					g.drawString(" x"+Main.window.creep[i].lives, W/5*(i+1), H/20);
					if(i == 0){
						g.drawImage(Main.window.Creeper, W/5*(i+1) - W/51, H/20 - H/28, W/51, H/28, this);
					}else if(i == 1){
						g.drawImage(Main.window.Zombie, W/5*(i+1) - W/51, H/20 - H/28, W/51, H/28, this);
					}else if(i == 2){
						g.drawImage(Main.window.Pig, W/5*(i+1) - W/51, H/20 - H/28, W/51, H/28, this);
					}else if(i == 3){
						g.drawImage(Main.window.Enderman, W/5*(i+1) - W/51, H/20 - H/28, W/51, H/28, this);
					}
				}
			}
		}
	}
}
