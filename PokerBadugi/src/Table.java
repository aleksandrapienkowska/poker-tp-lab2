import java.util.ArrayList;
import java.util.Random;

public class Table 
{
	ArrayList<Integer[]> gracze=new ArrayList<Integer[]>(6);
	Random random = new Random();
	
	int count,i,temp;
	
	Table()
	{
		
	}
	
	public void addPlayer()
	{
		gracze.set(count++,newHand());
	}
	
	private Integer[] newHand()
	{
		ArrayList<Integer> hand = new ArrayList<Integer>(4);
		i=0;
		while(i<4)
		{
			temp=random.nextInt(52);
			if(!hand.contains(temp))
			{
				hand.add(temp);
				(Cards.get(temp)).move(1);
				i++;
			}
		}
		return hand.toArray(Integer[]a);
	}
	
	public Object[] listen(Object[] input)
	{
		return null;
	
	}
}
