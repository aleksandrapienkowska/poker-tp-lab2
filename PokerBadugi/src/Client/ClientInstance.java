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
	 JTextArea game= new JTextArea();
	 JScrollPane scrollpoleDialogu = new JScrollPane(game, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
	 Writer wr ;
	 PrintWriter pw;

	
	public ClientInstance(OutputStream out, InputStream in){
		
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
	
			
			this.in=in;
			this.out =out;
				InputStreamReader isr = new InputStreamReader(in);
				br = new BufferedReader(isr);
				pw = new PrintWriter(out);
				wr = new OutputStreamWriter(out);
			   
			   

				
	
	
	
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
		pw.flush();
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
			pw.flush();
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
			pw.flush();
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
			pw.println(sendAction("ca", Payment.getText()));
			pw.flush();
				game.append(sendAction("ca", Payment.getText()));
			
			
			
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
			pw.flush();
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
			pw.flush();
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
	
	public void broadcast(String message){
		game.append(text);
	}
	
	public static String sendAction(String action, String amount){
		String message="";
		
		message=action;
		message=message+(amount);
		
		return message;
		}
	
	

	
}
