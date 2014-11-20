package Table;

public class Player 
{
	int 	number,
			bet,
			cash,
			temp;
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
		number=which;
	}
	
	public void setHand (int[] cards)
	{
		hand=cards;
	}
	
	public void changeCards(int[] which, int[] newCards)
	{
		temp=0;
		for(int i : hand)
		{
			for(int j : which)
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
		return number;
	}
	
	public int getCash()
	{
		return cash;
	}
	
	void setCash(int amount) 
	{
		cash=amount;
	}
	
	public void changeBet(int how,int amount)
	{
		if(how==0)
		{
			bet=how;
		}
		if(how==1)
		{
			bet+=how;
		}
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
		
	}
}