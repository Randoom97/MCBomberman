package Classes;

import Server.Client;
import Server.Server;
import UI.Window;


public class Main {
	public static Window window = new Window();
	public static GameThread thread = new GameThread();
	public static Server serverThread = new Server();
	public static Client clientThread = new Client();
	
	public static void main(String args[]){
		window.startWindow();
		window.titleScreen();
		thread.start();
	}
}
