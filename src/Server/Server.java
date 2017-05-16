package Server;
import java.net.*;
import java.util.*;
import java.io.*;

public class Server implements Runnable{
	public Output SO[] = new Output[4];
	public Input SI[] = new Input[4];
	public boolean isServer = false;
	
	
	public int sNum = 0;
	
	public void run(){
		try {
			isServer = true;
			@SuppressWarnings("resource")
//			ServerSocket sSocket = new ServerSocket(50497);
			ServerSocket sSocket = new ServerSocket(2468);
			System.out.println("Server started at: " + new Date());
			
			// Loops until a certain number of clients join
			while (sNum < 4) {
				
				// Wait for a client to connect
				Socket socket = sSocket.accept();

				SO[sNum] = new Output(socket, sNum + 1);
				SI[sNum] = new Input(socket, sNum + 1);
				new Thread(SO[sNum]).start();
				new Thread(SI[sNum]).start();
				sNum++;
			}
		} catch (IOException exception) {
			System.out.println("Error: " + exception);
		}
	}
}