package Server;

import java.io.*;
import java.net.*;

import Table.Deck;
import Table.Table;

public class clientThread_v2 extends Thread{

	
	  BufferedReader is = null;
	  PrintStream os = null;
	  private Socket clientSocket = null;
	  private final clientThread_v2[] threads;
	  private int maxClientsCount;
	  int[] cards=new int[4];
	  Integer Id;
	  private Table table;
	  
	  public clientThread_v2(Socket clientSocket, clientThread_v2[] threads, int t[], Table table) {
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	    this.cards=t;
	    this.table=table;
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
	     
	       synchronized (this) {
		          for (int i = 0; i < maxClientsCount; i++) {
		            if (threads[i] == this) {
		              Id=i;
		              OutputData[0]=i;
		              os.println("\nJestes graczem  "+ i);
		              setCards(Id);
		              break;
		            }
		          }
		        }
	   
	       String data="";
		 while(true){
			 
			data=is.readLine();
			if(data!=null){
			
				switch(data.substring(0,2)){
				case "ch" :{OutputData[1]=1;
							OutputData[2]=1; 
							break;}
				case "be" :{OutputData[1]=1;
				OutputData[2]=2; 
				break;}
				case "ra" :{OutputData[1]=1;
				OutputData[2]=2; 
				break;}
				case "ca" :{OutputData[1]=1;
				OutputData[2]=3; 
				break;}
				case "fo" :{OutputData[1]=1;
				OutputData[2]=4; 
				break;}
				case "al" :{OutputData[1]=1;
				OutputData[2]=2; 
				break;}
				case "cc" :{OutputData[1]=2;
				OutputData[2]=0; 
				for(int l=2;l<=5; l++){
					System.out.println(data);
				for(int k=3; k<7; k++){
					if(data.charAt(l)!='x'){
				
					OutputData[k]=getCard(Id, Integer.parseInt(data, l));
					System.out.println(OutputData[k]+getCard(Id, Integer.parseInt(data, l)));
				
					}
					break;	
					
				}}
				break;}
				}
				for(int k=0;k<OutputData.length;k++){
				System.out.println(OutputData[k]+"\n");
				}
				System.out.println(data);
				
				data="";
			 }
			 else{
	
			 break;
			 }
		 }
	     synchronized (this) {
	    	
	          for (int i = 0; i < maxClientsCount; i++) {
	            if (threads[i] == this) {
	              threads[i] = null;
	            }
	          }
	        }
	 
	     
	      is.close();
	      os.close();
	      clientSocket.close();
	    } catch (Exception e) {}
	  }
	  
	  public Integer getCard(int id_card, int id_player){
		  return threads[id_player].cards[id_card];
	  }
	  public void setCards(int i){
		  for(int k=0; k<cards.length;k++)
              os.println("set"+Deck.val(threads[i].cards[k])+"  "+Deck.col(threads[i].cards[k]));
	  }
}