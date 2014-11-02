package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame extends JFrame{
	public static JLabel[] CardImages=new JLabel[52];
	public static String[] Card=new String[52];
	static JPanel PanelInfo=new JPanel();
	static JLabel Balance=new JLabel("Stan konta: ");
	static JLabel BalanceAmount=new JLabel();
	static JLabel RoundOfBetting=new JLabel("Runda licytacji: ");
	static JLabel RoundOfBettingAmount=new JLabel();
	static JLabel TotalAmount=new JLabel();
	static JLabel Total=new JLabel("Stawka w grze: ");
	static JLabel MaxBet=new JLabel("Maksymalny Bet");
	static JLabel MaxBetAmount=new JLabel();
	static JPanel PanelAuction=new JPanel();
	static JButton Check= new JButton("Check");
	static JButton Bet= new JButton("Bet");
	static JButton Raise= new JButton("Raise");
	static JButton Call= new JButton("Call");
	static JButton Fold= new JButton("Fold");
	static JButton AllIn= new JButton("All in");
	static JButton Send= new JButton("Postaw");
	static JLabel Amount=new JLabel("Kwota: ");
	static JTextField Payment=new JTextField();
	static JPanel PanelCards=new JPanel();
	static JButton SendDecision= new JButton("Wymień karty");
	static JLabel[] card=new JLabel[4];
	static JCheckBox[] cardOk =new JCheckBox[4];
	
	public MyFrame(){
		super("PokerBadugi");
		//makeCardsImages();
		setLayout(new GridLayout(3,1));
		PanelInfo.setLayout(new GridLayout(3,2));
		PanelInfo.add(Balance);
		PanelInfo.add(BalanceAmount);
		PanelInfo.add(RoundOfBetting);
		PanelInfo.add(RoundOfBettingAmount);
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
		PanelAuction.add(Send);
		PanelCards.setLayout(new FlowLayout());
		for(int k=0; k<4; k++){
			card[k]=new JLabel();
			PanelCards.add(card[k]);
			cardOk[k]=new JCheckBox();
			PanelCards.add(cardOk[k]);
		}
		PanelCards.add(SendDecision);
		PanelCards.add(new JLabel(new ImageIcon("2c.jpg")));
		add(PanelInfo);
		add(PanelAuction);
		add(PanelCards);
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		Check.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
		Bet.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			//setBet(Integer.parseInt(Payment.getText()));
			
				
			}
			
		});
		Raise.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

			}
			
		});
		Call.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
		Fold.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				
			}
			
		});
		AllIn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
			
		});
		Send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
		
				
			}
			
		});
		SendDecision.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				
			}
			
		});	
	}
	
	
	public static void setBalance(String message){
		BalanceAmount.setText(message);
	}
	
	public static void setRoundOfBetting(String message){
		RoundOfBettingAmount.setText(message);
	}
	
	public static void setTotal(String message){
		TotalAmount.setText(message);
	}
	
	public static void setMaxBet(String message){
		MaxBetAmount.setText(message);
		
	}
	
	public static void setCards(String[] cards){
		for(int i=0; i<4; i++){
			card[i].setText(cards[i]);
		}
	}
	
	public static void makeCardsImages(){
		char suit='c';
		for(int k=0;k<52;k++){
			
			if(k<13){
				suit='c';
			}else if(k<26){
				suit='d';
			}else if(k<39){
				suit='h';
			}else if(k<=51){
				suit='s';
			}		
			
			for(int i=2; i<=10; i++){
				Card[k]=Integer.toString(i)+suit;
				CardImages[k]=new JLabel(new ImageIcon(Card[k]+".jpg"));
			}
		}
	}
}
