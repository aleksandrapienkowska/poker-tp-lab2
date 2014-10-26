import java.io.*;
import java.net.Socket;


public class Client {
	private static Socket clientSocket = null;
	private static PrintStream os = null;
	private static BufferedReader inputLine = null;
	
	
	public Client(){
		new MyFrame();
	
	}

	  public static void main(String[] args) {
		  new Client();
	
	  }

	 
	  
	
}
