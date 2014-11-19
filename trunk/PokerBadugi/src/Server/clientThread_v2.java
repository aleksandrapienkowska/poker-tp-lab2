package Server;

import java.io.*;
import java.net.*;

public class clientThread_v2 extends Thread{

	
	  BufferedReader is = null;
	  PrintStream os = null;
	  private Socket clientSocket = null;
	  private final clientThread_v2[] threads;
	  private int maxClientsCount;

	  Integer Id;
	  
	  
	  public clientThread_v2(Socket clientSocket, clientThread_v2[] threads) {
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	  }

	  public void run() {
	    int maxClientsCount = this.maxClientsCount;
	    clientThread_v2[] threads = this.threads;
	    Integer[] InputData=new Integer[7];
		Integer[] OutputData=new Integer[7];
		for(int k=0;k<7; k++){
			InputData[k]=0;
			OutputData[k]=0;
		}
		
	    try {
	     
	      is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	      os = new PrintStream(clientSocket.getOutputStream());
	       os.println("Coś Kurwa!!!!!");
	       System.out.println("Dupa0");
	       synchronized (this) {
		          for (int i = 0; i < maxClientsCount; i++) {
		            if (threads[i] == this) {
		              Id=i;
		              OutputData[0]=i;
		              os.println("\nJestes graczem"+ i);
		              System.out.println("Dupa1");
		              break;
		            }
		          }
		        }
	       System.out.println("Dupa2");
	       String data="";
		 while(true){
			 System.out.println("Dupa3");
			data=is.readLine();
			 if(data!=null){
				 
				System.out.println(data);
			 }
			 else{
			 break;
			 }
		 }
	     synchronized (this) {
	    	 System.out.println("Dupa4");
	          for (int i = 0; i < maxClientsCount; i++) {
	            if (threads[i] == this) {
	              threads[i] = null;
	            }
	          }
	        }
	     System.out.println("Dupa5");
	     
	      is.close();
	      os.close();
	      clientSocket.close();
	    } catch (Exception e) {}
	  }

}
