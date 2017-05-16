package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import Classes.Main;

public class Input implements Runnable{
	Socket threadSocket;
	int character;
	
	public Input (Socket socket, int ch){
		threadSocket = socket;
		this.character = ch;
	}
	
	public void run(){
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
			
			//This will wait until a packet has been sent
			while (true) {
				String inputString = input.readLine();
				char[] charArray = inputString.toCharArray();
				String inputIndex = "";
				
				//This will assign to the correct location
				for(int i = 0; i < charArray.length; i++){
					if(Main.clientThread.isClient){
						if(Character.toString(charArray[i]).equals("a")){
							character = 1;
						}else if(Character.toString(charArray[i]).equals("b")){
							character = 2;
						}else if(Character.toString(charArray[i]).equals("c")){
							character = 3;
						}
					}
					//Character location
					if(Character.toString(charArray[i]).equals("C")){
						for(int j = 1; j < 10; j++){
							if(Character.toString(charArray[i+j]).equals(",")){
								Main.window.creep[character].xCord = Integer.parseInt(inputIndex);
								inputIndex = "";
								j++;
							}
							if(Character.toString(charArray[i+j]).equals("e")){
								Main.window.creep[character].yCord = Integer.parseInt(inputIndex);
								inputIndex = "";
								i += j;
								break;
							}
							inputIndex += charArray[i+j];
						}
					}
					
					//Character Spawn
					if(Character.toString(charArray[i]).equals("S")){
						int xCord = Main.window.creep[character].xCord*31/1600;
						int yCord = Main.window.creep[character].yCord*17/900;
						for(int x = -1; x < 2; x++){
							if(Main.window.board[xCord + x][yCord].img != Main.window.border){
								Main.window.board[xCord + x][yCord].genTile(Main.window.floor, xCord + x, yCord);
							}
						}
						for(int y = -1; y < 2; y++){
							if(Main.window.board[xCord][yCord + y].img != Main.window.border){
								Main.window.board[xCord][yCord + y].genTile(Main.window.floor, xCord, yCord + y);
							}
						}
					}
					
					//Lives
					if(Character.toString(charArray[i]).equals("L")){
						inputIndex += charArray[i+1];
						Main.window.creep[character].lives = Integer.parseInt(inputIndex);
						inputIndex = "";
						i += 2;
					}
					
					//Bombs
					if(Character.toString(charArray[i]).equals("B")){
						i++;
						if(Main.window.creep[character].bombs[Integer.parseInt(Character.toString(charArray[i]))].isPlaced == false){
							Main.window.creep[character].placeBomb();
						}
					}
					
					//Powerups
					if(Character.toString(charArray[i]).equals("P")){
						int xCord = 0;
						int yCord = 0;
						for(int j = 1; j < 7; j++){
							if(Character.toString(charArray[i+j]).equals(",")){
								xCord = Integer.parseInt(inputIndex);
								inputIndex = "";
								j++;
							}
							if(Character.toString(charArray[i+j]).equals("e")){
								yCord = Integer.parseInt(inputIndex);
								inputIndex = "";
								i += j;
								break;
							}
							inputIndex += charArray[i+j];
						}
						Main.window.powerup[xCord][yCord].xPos = xCord;
						Main.window.powerup[xCord][yCord].yPos = yCord;
						Main.window.powerup[xCord][yCord].exists = true;
					}
				}
			}
		} catch (IOException exception) {
			System.out.println("Error: " + exception);
		}
	}
}
