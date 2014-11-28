package Client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class ClientInstance {

	static Socket s;
	static Scanner tIn = new Scanner(System.in);
	private static OutputStream out;
	private static InputStream in;
	private static BufferedReader br;
	 String text = "";
	 JPanel PanelInfo=new JPanel();
	 JLabel Balance=new JLabel("Stan konta: ");
	 JLabel BalanceAmount=new JLabel();
	 JLabel TotalAmount=new JLabel();
	 JLabel Total=new JLabel("Stawka w grze: ");
	 JLabel MaxBet=new JLabel("Najwyższa stawka: ");
	 JLabel MaxBetAmount=new JLabel();
	 JLabel Bets=new JLabel("Zakłady graczy");
	 JLabel BetsAmount=new JLabel();
	 JPanel PanelAuction=new JPanel();
	 JButton Check= new JButton("Check");
	 JButton Bet= new JButton("Bet");
	 JButton Raise= new JButton("Raise");
	 JButton Call= new JButton("Call");
	 JButton Fold= new JButton("Fold");
	 JButton AllIn= new JButton("All in");
	 JLabel Amount=new JLabel("Kwota: ");
	 JTextField Payment=new JTextField();
	 JPanel PanelCards=new JPanel();
	 JButton SendDecision= new JButton("Wymień karty");
	 JLabel[] card=new JLabel[4];
	 JCheckBox[] cardOk =new JCheckBox[4];
	 JFrame window =new JFrame("Badugi");
	 JTextArea game= new JTextArea(800,400);
	
	 PrintWriter pw;
	int bill;
	int maxbet;
	 
	public ClientInstance(OutputStream out, InputStream in){
	
		game.setEditable(false);
		game.setLineWrap(true);
		window.add(game);
		window.add(new JScrollPane(game,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		 DefaultCaret caret = (DefaultCaret)game.getCaret();
	        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
	        window.setLayout(new GridLayout(5,1));
	        PanelInfo.setLayout(new GridLayout(3,2));
			PanelInfo.add(Balance);
			PanelInfo.add(BalanceAmount);
			PanelInfo.add(Total);		
			PanelInfo.add(TotalAmount);
			PanelInfo.add(MaxBet);
			PanelInfo.add(MaxBetAmount);					
			PanelAuction.setLayout(new FlowLayout());
			PanelAuction.add(Check);
			PanelAuction.add(Bet);
			PanelAuction.add(Raise);
			PanelAuction.add(Call);
			PanelAuction.add(Fold);
			PanelAuction.add(AllIn);
			PanelAuction.add(Amount);
			Payment.setPreferredSize(new Dimension(70,20));
			PanelAuction.add(Payment);
			
			
			PanelCards.setLayout(new FlowLayout());
			for(int k=0; k<4; k++){
				card[k]=new JLabel();
				PanelCards.add(card[k]);
				cardOk[k]=new JCheckBox();
				PanelCards.add(cardOk[k]);
			}
			PanelCards.add(SendDecision);
			
			
			window.add(PanelInfo);
			window.add(PanelAuction);
			window.add(PanelCards);
			JPanel BetOfRound=new JPanel();
			BetOfRound.add(Bets);
			BetOfRound.add(BetsAmount);
			
			window.add(BetOfRound);
	        window.setSize(400,500);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
			
			
			this.in=in;
			this.out =out;
				InputStreamReader isr = new InputStreamReader(in);
				br = new BufferedReader(isr);
				pw = new PrintWriter(out);
				
			   
			

				
	
	
	
	Check.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
		
		pw.println("ch");
		pw.flush();
		//game.append(sendAction("ch", Payment.getText()));

		Payment.setText("");
			
			
			}
		
	});
	Bet.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		int k=-1;
			
	
			try{
				k=Integer.parseInt(Payment.getText());
				pw.println(sendAction("be", Payment.getText()));
				pw.flush();
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
				
			}
			Payment.setText("");
			
			}
		
	});
	Raise.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int k=-1;
			
			
			try{
				
				k=Integer.parseInt(Payment.getText());
				pw.println(sendAction("ra", Payment.getText()));
				pw.flush();
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			
			
				
			Payment.setText("");
			}
			
			
		
	});
	Call.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int k=-1;
			
			
			try{
				k=Integer.parseInt(Payment.getText());
				pw.println(sendAction("ca", Payment.getText()));
				pw.flush();
					
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
		
			Payment.setText("");
			
			}
		
	});
	Fold.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			pw.println("fo");
			pw.flush();
				//game.append("fo", Payment.getText()));
			
			Payment.setText("");
			
			}
		
	});
	AllIn.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int k=-1;
			
			
			try{
				k=Integer.parseInt(Payment.getText());
				pw.println(sendAction("al", Payment.getText()));
				pw.flush();
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			
				
			
			Payment.setText("");
			
			}
		
	});
	SendDecision.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String selected="";
			for(Integer i=0;i<cardOk.length;i++){
				if(cardOk[i].isSelected()){
					selected=selected+Integer.toString(i);
					
				}
				else{
					selected=selected+"n";
				}
			}
			pw.println("cc"+selected);			
			pw.flush();
			for(Integer i=0;i<cardOk.length;i++){
				cardOk[i].setSelected(false);
			}
			}
		
	});
	
	
	
	while (text!=null) {
		
		window.repaint();
		window.revalidate();
		try {
		text = br.readLine();
		int l=0;
	
	while(text.startsWith("setBill")){
			BalanceAmount.setText(text.replace("setBill",""));
			text = br.readLine();	
			bill=Integer.parseInt(BalanceAmount.getText());
			
	}
	
	
	while(text.startsWith("setPot")){
		TotalAmount.setText(text.replace("setPot", ""));
		text=br.readLine();
		
	}		
	while(text.startsWith("setMaxBet")){
		MaxBetAmount.setText(text.replace("setMaxBet", ""));
		maxbet=Integer.parseInt(MaxBetAmount.getText());
		text=br.readLine();
		
	}
	while(text.startsWith("setTitle")){
		window.setTitle("Badugi"+text.replace("setTitle", ""));
		text=br.readLine();
	
	}
	while(text.startsWith("setBetsAmount")){
		l++;
		if(l==1)
			BetsAmount.setText("");
		BetsAmount.setText(BetsAmount.getText()+text.replace("setBetsAmount",""));
		text = br.readLine();
		
		if(l==4){
			l=0;
		}
	}
		while(text.startsWith("setCards"))
		{
			
			card[l].setText(text.replace("setCards",""));
			
			l++;
			text = br.readLine();
			if(l==4){
				l=0;
			}
		}
		
		if(text.contains("")){
			
		}
		
		
		broadcast(text.replace("|","\n"));
		window.revalidate();
		window.repaint();
		} catch (IOException e) {
		game.append("Utracono połączenie z serverem");
		} catch (NullPointerException e) {
		game.append("Utracono połączenie z serverem");
		}
		}
	
	
	}
	
	void broadcast(String message){
		game.append(message);
		window.repaint();
		window.revalidate();
	}
	
	public static String sendAction(String action, String amount){
		String message="";
		
		message=action;
		message=message+(amount);
		
		return message;
		}
	
	

	
}
