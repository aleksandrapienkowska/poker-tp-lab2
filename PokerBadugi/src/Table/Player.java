package Table;
public class Player 
{
	private int 	number,
					temp;
	private int[] 	hand;
	private boolean	active;
	
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
}