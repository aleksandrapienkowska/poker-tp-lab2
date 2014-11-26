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
	  int Id;
	  private Table table;
	  private boolean enabled=true;
	  public static int bill;
	  public clientThread_v2(Socket clientSocket, clientThread_v2[] threads, int t[], Table table, int bill) {
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	    this.cards=t;
	    this.table=table;
	    this.bill=bill;
	  }

	  public void run() {
	    int maxClientsCount = this.maxClientsCount;
	    clientThread_v2[] threads = this.threads;
	    Object[] InputData=new Object[7];
		int[] OutputData=new int[7];
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
		              os.println("setBill"+bill);
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
					OutputData[3]=Integer.parseInt(data.substring(2));
					break;}
				case "ra" :{OutputData[1]=1;
					OutputData[2]=2; 
					OutputData[3]=Integer.parseInt(data.substring(2));
					break;}
				case "ca" :{OutputData[1]=1;
					OutputData[2]=3; 
					OutputData[3]=Integer.parseInt(data.substring(2));
					break;}
				case "fo" :{OutputData[1]=1;
					OutputData[2]=4; 
					break;}
				case "al" :{OutputData[1]=1;
					OutputData[2]=2; 
					OutputData[3]=Integer.parseInt(data.substring(2));
					break;}
				case "cc" :{
					OutputData[1]=2;
					OutputData[2]=0; 
					data=data.substring(2);
					int l=0;
					int[] changedcards= new int[4];
					for(int k=3; k<7; k++){
					OutputData[k]=getCard(data.substring(l,l+1),Id);
					changedcards[l]=l;
					l=l+1;
					}		
					}
					break;}
				
				/*for(int k=0;k<OutputData.length;k++){
				System.out.println(OutputData[k]);
				}*/
				Integer[] Output=new Integer[7];
				for(int l=0;l<7;l++){
				
					Output[l]=Integer.valueOf(OutputData[l]);
				}
				//System.out.println("przed");
				InputData=table.listen(Output);
				System.out.println("po");
				for(int k=0;k<InputData.length;k++){
					System.out.println(InputData[k]);
					}
				for(int k=1;k<7; k++){
					OutputData[k]=0;
				}
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
	  
	  public int getCard(String id_card, int id_player){
		  int value;
		 try{ 
			  value=threads[id_player].cards[Integer.parseInt(id_card)];
		 }
		 catch(Exception z){
			 value=0;
		 }
		 
		  return value;
	  }
	  public void setCards(int i){
		  String[] colors={"czerwo", "wino","zoladz","dzwonek"};
		  String[] figures={"A", "2","3","4","5","6","7","8","9","10","J","Q","K"};
		  for(int k=0; k<cards.length;k++)
              os.println("setCards"+figures[Deck.val(threads[i].cards[k])-1]+"  "+colors[Deck.col(threads[i].cards[k])-1]);
	  }
}