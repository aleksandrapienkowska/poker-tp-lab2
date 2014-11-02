package Server;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
	  
	  private static ServerSocket serverSocket = null;
	  private static Socket clientSocket = null;
	  private static final int maxClientsCount = 6;
	  private static final clientThread[] threads = new clientThread[maxClientsCount];
	  private static final Security[] users=new Security[maxClientsCount];
	 
	 /* public Server(int port){
		  try {
			serverSocket= new ServerSocket(port);
			new Thread(new Runnable(){

				@Override
				public void run() {
					while(true){
					try {
						clientSocket=serverSocket.accept();
						out=new PrintWriter(clientSocket.getOutputStream(),true);
						in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						users.add(new Security(out, in));
						
						
						new Thread(new Runnable(){				
							@Override
							public void run() {
							 PrintWriter o = out;
							 BufferedReader i = in;
							 o.println("Welcome");
							 while(true){
								 try {
									String text=i.readLine();
									sendInfo(text);
								} catch (IOException e) {}
								 
							 }
							}
											
							
						}).start();
					} catch (IOException e) {
						e.printStackTrace();
					}			
				}}
				
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		  
		  
	  }*/
	 /* public static void sendInfo(String message){
		  for(int i=0; i< users.size(); i++){
			  Security user= users.get(i);
			  
			if(user==null||user.out==null){
				  users.remove(user);
			  }
			else
				user.out.println(message);
			  
		  }
	  }*/
	
	  public static void main(String[] args){
		     
		    int portNumber = 2222;
		    if (args.length < 1) {
		      System.out
		          .println("Usage: java MultiThreadChatServer <portNumber>\n"
		              + "Now using port number=" + portNumber);
		    } else {
		    	
		      portNumber = Integer.valueOf(args[0]).intValue();
		    }

		    
		    try {
		    	serverSocket = new ServerSocket(portNumber);
		    	//new Server(Integer.parseInt(args[0]));
		    } catch (IOException e) {
		      System.out.println(e);
		    }

		    
		    while (true) {
		      try {
		        clientSocket = serverSocket.accept();
		        int i = 0;
		        for (i = 0; i < maxClientsCount; i++) {
		          if (threads[i] == null) {
		            (threads[i] = new clientThread(clientSocket, threads)).start();
		            break;
		          }
		        }
		        if (i == maxClientsCount) {
		          PrintStream os = new PrintStream(clientSocket.getOutputStream());
		          os.println("Server too busy. Try later.");
		          os.close();
		          clientSocket.close();
		        }
		      } catch (IOException e) {
		        System.out.println(e);
		      }
		    }
		  }
	  }
	
	

