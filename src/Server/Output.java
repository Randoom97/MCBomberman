package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import Classes.Main;
import Objects.powerUp;

public class Output implements Runnable{
	Socket threadSocket;
	int[] xPos = new int[4];
	int[] yPos = new int[4];
	int[] lives = new int[4];
	int character;
	int chc = 0;
	powerUp pUp[][] = new powerUp[31][17];
	String outputString = "";
	boolean Changed = false;
	
	public Output(Socket socket, int ch){
		threadSocket = socket;
		character = ch;
		for(int x = 0; x < 31; x++){
			for(int y = 0; y < 17; y++){
				pUp[x][y] = new powerUp();
			}
		}
	}
	
	//Output is formated as "creep xpos , ypos : bomb num"
	public void run() {
		try{
			PrintWriter output = new PrintWriter(threadSocket.getOutputStream(),true);
			
			while(true){
				long iTime = System.nanoTime();
				for(int j = 0; j < Main.window.creep.length; j++){
					//Which character it is
					if(Main.serverThread.isServer){
						if(chc == character){
							j++;
							if(j == 4){
								break;
							}
						}
						if(chc == 0){
							outputString+= "a";
						}else if(chc == 1){
							outputString += "b";
						}else if(chc == 2){
							outputString += "c";
						}
						chc++;
					}
					
					//Character position
					if((Main.window.creep[j].xCord != xPos[j] || Main.window.creep[j].yCord != yPos[j]) && Main.window.creep[j].xCord > -1){
						boolean spawn = false;
						if(xPos[j] == 0){
							spawn = true;
						}
						xPos[j] = Main.window.creep[j].xCord;
						yPos[j] = Main.window.creep[j].yCord;
						outputString += "C" + xPos[j] + "," + yPos[j] + "e";
						if(spawn){
							outputString += "S";
						}
						Changed = true;
					}
					
					//Character lives
					if(Main.window.creep[j].lives != lives[j]){
						lives[j] = Main.window.creep[j].lives;
						outputString += "L" + lives[j] + "e";
						Changed = true;
					}
					
					//Placed bombs
					for(int i = 0; i < Main.window.creep[j].bombs.length; i++){
						if(Main.window.creep[j].bombs[i].isPlaced && Main.window.creep[j].bombs[i].timer <= 1){
							outputString = outputString + "B"+i+"e";
							Changed = true;
						}
					}
					
					//Powerup spawns
					for(int x = 0; x < 31; x++){
						for(int y = 0; y < 17; y++){
							if(Main.window.powerup[x][y].exists !=  pUp[x][y].exists && Main.window.powerup[x][y].exists){	
								pUp[x][y].exists = true;
								outputString += "P" + x + "," + y + "e";
								Changed = true;
							}
						}
					}
					
					//If anything changed it will send a packet
					if(Changed){
						output.println(outputString);
						iTime = System.nanoTime() - iTime;
						System.out.println(iTime);
					}
				
					outputString = "";
					Changed = false;
					if(!Main.serverThread.isServer){
						break;
					}
				}
				chc = 0;
				try{
					Thread.sleep(17);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}catch(IOException exception){
			System.out.println("Error: " + exception);
		}
	}
}
