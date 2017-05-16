package UI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Classes.userInput;
import Objects.Bomb;
import Objects.Character;
import Objects.Tile;
import Objects.powerUp;


public class Window{
	
	public JFrame frame = new JFrame();
	public Panel panel = new Panel();
	public userInput input = new userInput();
	private int width = 1071;
	private int height = 600;
	private String title = "Bomberman";
	public Tile[][] board = new Tile[31][17];
	public Character[] creep = new Character[4];
	public powerUp[][] powerup = new powerUp[31][17];
	public powerUp[][] bombSmoke = new powerUp[31][17];
	
	public BufferedImage overlay;
	public BufferedImage border;
	public BufferedImage wall;
	public BufferedImage breakable;
	public BufferedImage floor;
	public BufferedImage tnt;
	public BufferedImage character;
	public BufferedImage tnt2;
	public BufferedImage tntUp;
	public BufferedImage singlePlayer;
	public BufferedImage singlePlayerHover;
	public BufferedImage multiPlayer;
	public BufferedImage multiPlayerHover;
	public BufferedImage host;
	public BufferedImage hostHover;
	public BufferedImage join;
	public BufferedImage joinHover;
	public BufferedImage restart;
	public BufferedImage restartHover;
	public BufferedImage smoke;
	public BufferedImage Zombie;
	public BufferedImage Creeper;
	public BufferedImage Pig;
	public BufferedImage Enderman;
	public BufferedImage IPSpot;
	
	public int ScreenType = 0;
	public int isTitle = 1;
	public int isMultiplayer = 2;
	public int isGame = 3;
	
	public String serverIp = "";
	
	//Starts the window
	public void startWindow(){
		gatherImages();
		frame.setSize(width,height);
		frame.setTitle(title);
		frame.setIconImage(tnt);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.addKeyListener(input);
		frame.addMouseListener(input);
		frame.add(panel);
	}
	
	//Sets the game to the title screen
	public void titleScreen(){
		ScreenType = isTitle;
	}
	
	//goes to the multiplayer screen
	public void multiPlayer(){
		ScreenType = isMultiplayer;
	}
	
	//sets the game to the game screen
	public void gameScreen(){
		ScreenType = isGame;
		for(int i = 0; i < creep.length; i++){
			for(int j = 0; j < creep[i].bombs.length; j++){
				creep[i].bombs[j] = new Bomb();
			}
		}
		creep[0].spawn();
	}
	
	//gathers the required images
	public void gatherImages(){
		try {
			overlay = ImageIO.read(this.getClass().getResourceAsStream("/resources/overlay.png"));
			border = ImageIO.read(this.getClass().getResourceAsStream("/resources/Tiles/border.png"));
			wall = ImageIO.read(this.getClass().getResourceAsStream("/resources/Tiles/wall.png"));
			breakable = ImageIO.read(this.getClass().getResourceAsStream("/resources/Tiles/breakable.png"));
			floor = ImageIO.read(this.getClass().getResourceAsStream("/resources/Tiles/floor.png"));
			tnt = ImageIO.read(this.getClass().getResourceAsStream("/resources/tnt.png"));
			character = ImageIO.read(this.getClass().getResourceAsStream("/resources/Characters/character.png"));
			Zombie = ImageIO.read(this.getClass().getResourceAsStream("/resources/Characters/ZombieFace.png"));
			Creeper = ImageIO.read(this.getClass().getResourceAsStream("/resources/Characters/creeper2.png"));
			Pig = ImageIO.read(this.getClass().getResourceAsStream("/resources/Characters/pig.png"));
			Enderman = ImageIO.read(this.getClass().getResourceAsStream("/resources/Characters/enderman.png"));
			tnt2 = ImageIO.read(this.getClass().getResourceAsStream("/resources/tnt2.png"));
			tntUp = ImageIO.read(this.getClass().getResourceAsStream("/resources/tntUp.png"));
			singlePlayer = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/Singleplayer.png"));
			singlePlayerHover = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/SingleplayerHover.png"));
			multiPlayer = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/Multiplayer.png"));
			multiPlayerHover = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/MultiplayerHover.png"));
			host = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/Host.png"));
			hostHover = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/HostHover.png"));
			join = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/Join.png"));
			joinHover = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/JoinHover.png"));
			restart = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/restart.png"));
			restartHover = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/Restarthover.png"));
			IPSpot = ImageIO.read(this.getClass().getResourceAsStream("/resources/Buttons/IPSpot.png"));
			smoke = ImageIO.read(this.getClass().getResourceAsStream("/resources/smoke.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		genBoard();
	}
	
	//generates the board tiles
	public void genBoard(){
		for(int x = 0; x < 31; x++){
			for(int y = 0; y < 17; y++){
				board[x][y] = new Tile();
				board[x][y].genTile(border, x, y);
				powerup[x][y] = new powerUp();
				bombSmoke[x][y] = new powerUp();
			}
		}
		for(int x = 1; x < 30; x++){
			for(int y = 1; y < 16; y++){
				board[x][y].genTile(breakable, x, y);
				if(x % 2 == 0 && y % 2 ==0){
					board[x][y].genTile(wall, x, y);
				}
			}
		}
		for(int i = 0; i < creep.length; i++){
			if(creep[i] == null){
				creep[i] = new Character();
			}
		}
	}
	
}
