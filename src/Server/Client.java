package Server;

import java.io.*;
import java.net.*;

import Classes.Main;

public class Client implements Runnable{
	public boolean isClient = false;
	
	public void run(){
		isClient = true;
		try {
//			Socket socket = new Socket(Main.window.serverIp,50497);
			Socket socket = new Socket(Main.window.serverIp,2468);
			Output CO = new Output(socket, 1);
			Input CI = new Input(socket, 1);
			new Thread(CO).start();
			new Thread(CI).start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}