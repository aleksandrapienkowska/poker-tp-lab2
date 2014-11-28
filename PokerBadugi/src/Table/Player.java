package Table;

public class Player 
{
	int 	id,
			bet,
			round_bet,
			cash,
			temp,
			points;
	int[] 	hand;
	boolean	lost,
			fold,
			active,
			all_in,
			leader,
			big_blind,
			small_blind;
	
	Player(int which)
	{
		id=which;
		active=true;
	}
	
	public void setHand (int[] cards)
	{
		hand=cards;
	}
	
	public void changeCards(int[] which, int[] newCards)
	{
		temp=0;
		for(int i=0;i<hand.length;i++)
		{
			for(int j=0;j<which.length;j++)
			{	
				if(hand[i]==which[j])
				{
					hand[i]=newCards[temp++];
				}
			}
		}
	}
	
	public int[] getHand()
	{
		return hand;
	}
	
	public int getNumber()
	{
		return id;
	}
	
	public int getCash()
	{
		return cash;
	}
	
	void setCash(int amount) 
	{
		cash=amount;
	}
	
	public boolean checkAll()
	{
		return all_in;
	}
	
	public boolean checkActive()
	{
		return active;
	}

	public void bet(int what) 
	{
		round_bet+=what;
		cash-=what;
		bet+=what;
	}
}