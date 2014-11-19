package Client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class clientTest {
	
	static Socket s;
	static Scanner tIn = new Scanner(System.in);
	private static OutputStream out;
	private static InputStream in;
	private static BufferedReader br;
	static String text = "";
	static JPanel PanelInfo=new JPanel();
	static JLabel Balance=new JLabel("Stan konta: ");
	static JLabel BalanceAmount=new JLabel();
	static JLabel TotalAmount=new JLabel();
	static JLabel Total=new JLabel("Stawka w grze: ");		
	static JPanel PanelAuction=new JPanel();
	 static JButton Check= new JButton("Check");
	static JButton Bet= new JButton("Bet");
	static JButton Raise= new JButton("Raise");
	static JButton Call= new JButton("Call");
	static JButton Fold= new JButton("Fold");
	static JButton AllIn= new JButton("All in");
	static JLabel Amount=new JLabel("Kwota: ");
	static JTextField Payment=new JTextField();
	static JPanel PanelCards=new JPanel();
	static JButton SendDecision= new JButton("Wymień karty");
	static JLabel[] card=new JLabel[4];
	static JCheckBox[] cardOk =new JCheckBox[4];
	static JFrame window =new JFrame("Badugi");
	static JTextArea game= new JTextArea();
	static JScrollPane scrollpoleDialogu = new JScrollPane(game, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
	
	public static PrintWriter pw;
	/**
	* @param args
	*/
	
	public static void main(String[] args) {
		JFrame window =new JFrame("Badugi");
		final JTextArea game= new JTextArea();
		game.setEditable(false);
		game.setLineWrap(true);
		 DefaultCaret caret = (DefaultCaret)game.getCaret();
	        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
	        window.setLayout(new GridLayout(3,1));
	        PanelInfo.setLayout(new GridLayout(3,2));
			PanelInfo.add(Balance);
			PanelInfo.add(BalanceAmount);
			PanelInfo.add(Total);		
			PanelInfo.add(TotalAmount);
			PanelInfo.add(game);
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
	        window.setSize(800,500);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
	try {
	game.append("\nConnecting...");
	s = new Socket(InetAddress.getLocalHost(), 4444);
	game.append("\nConnected");
	in = s.getInputStream();
	out = s.getOutputStream();
	InputStreamReader isr = new InputStreamReader(in);
	br = new BufferedReader(isr);
	pw = new PrintWriter(out);
	pw.println("Cos kurwa");
	} catch (UnknownHostException e) {
	game.append("Unknown Host");
	} catch (IOException e) {
	game.append("IO Connection failure");
	}
	Check.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
		try{
			int k=Integer.parseInt(Payment.getText());
		}
		catch(Exception z){
			game.append("Nieprawidlowa kwota");
		}
		pw.println(sendAction("ch", Payment.getText()));
	
			game.append(sendAction("ch", Payment.getText()));
			
			}
		
	});
	Bet.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			try{
				int k=Integer.parseInt(Payment.getText());
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			pw.println(sendAction("be", Payment.getText()));
				game.append(sendAction("be", Payment.getText()));
			
			
			}
		
	});
	Raise.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try{
				int k=Integer.parseInt(Payment.getText());
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			pw.println(sendAction("ra", Payment.getText()));
				game.append(sendAction("ra", Payment.getText()));
			
			
			
			}
		
	});
	Call.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try{
				int k=Integer.parseInt(Payment.getText());
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			pw.println(sendAction("ra", Payment.getText()));
				game.append(sendAction("ra", Payment.getText()));
			
			
			
			}
		
	});
	Fold.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try{
				int k=Integer.parseInt(Payment.getText());
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			pw.println(sendAction("fo", Payment.getText()));
				game.append(sendAction("fo", Payment.getText()));
			
			
			
			}
		
	});
	AllIn.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try{
				int k=Integer.parseInt(Payment.getText());
			}
			catch(Exception z){
				game.append("Nieprawidlowa kwota");
			}
			pw.println(sendAction("al", Payment.getText()));
				game.append(sendAction("al", Payment.getText()));
			
			
			
			}
		
	});
	SendDecision.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			
				
			
			
			
			}
		
	});
	while (text!=null) {
	try {
	text = br.readLine();
	game.append(text);

	} catch (IOException e) {
	game.append("Connection failure");
	} catch (NullPointerException e) {
	game.append("Null Pointer failure");
	}
	}
	}
	public static void setBalance(String message){
		BalanceAmount.setText(message);
	}
	
	
	public static void setTotal(String message){
		TotalAmount.setText(message);
	}
	
	
	
	public static void setCards(String[] cards){
		for(int i=0; i<4; i++){
			card[i].setText(cards[i]);
		}
	}

	
	public static String sendAction(String action, String amount){
		String message="";
		
		message=action;
		message=message+(amount);
		
		return message;
		}

}
