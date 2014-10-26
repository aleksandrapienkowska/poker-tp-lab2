import java.awt.*;

import javax.swing.*;

public class MyFrame extends JFrame{
	public static JLabel[] CardImages=new JLabel[52];
	public static String[] Card=new String[52];
	static JPanel PanelInfo=new JPanel();
	static JLabel Balance=new JLabel("Stan konta: ");
	static JLabel RoundOfBetting=new JLabel("Runda licytacji: ");
	static JLabel TotalAmount=new JLabel("Stawka w grze: ");
	static JPanel PanelAuction=new JPanel();
	static JButton Check= new JButton("Check");
	static JButton Bet= new JButton("Bet");
	static JButton Raise= new JButton("Raise");
	static JButton Call= new JButton("Call");
	static JButton Fold= new JButton("Fold");
	static JButton AllIn= new JButton("All in");
	static JButton Send= new JButton("Postaw");
	static JLabel Amount=new JLabel("Kwota: ");
	static JTextArea Payment=new JTextArea();
	static JPanel PanelCards=new JPanel();
	static JButton SendDecision= new JButton("Wymień karty");
	static JCheckBox card1=new JCheckBox();
	static JCheckBox card2=new JCheckBox();
	static JCheckBox card3=new JCheckBox();
	static JCheckBox card4=new JCheckBox();
	public MyFrame(){
		super("PokerBadugi");
		//makeCardsImages();
		setLayout(new GridLayout(3,1));
		PanelInfo.setLayout(new GridLayout(3,1));
		PanelInfo.add(Balance);
		PanelInfo.add(RoundOfBetting);
		PanelInfo.add(TotalAmount);
		PanelAuction.setLayout(new FlowLayout());
		PanelAuction.add(Check);
		PanelAuction.add(Bet);
		PanelAuction.add(Raise);
		PanelAuction.add(Call);
		PanelAuction.add(Fold);
		PanelAuction.add(AllIn);
		PanelAuction.add(Send);
		PanelAuction.add(Amount);
		PanelAuction.add(Payment);
		PanelCards.setLayout(new FlowLayout());
		PanelCards.add(card1);
		PanelCards.add(card2);
		PanelCards.add(card3);
		PanelCards.add(card4);
		PanelCards.add(SendDecision);
		PanelCards.add(new JLabel(new ImageIcon("2c.jpg")));
		add(PanelInfo);
		add(PanelAuction);
		add(PanelCards);
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
