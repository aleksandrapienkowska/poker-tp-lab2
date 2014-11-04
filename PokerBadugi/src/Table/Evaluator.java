package Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class Evaluator 
{
	private int			tint,
						points;
	private ArrayList<Integer> 	other_colors,
								temp;
	private ArrayList<ArrayList<Integer>> figures;
	private ListIterator<Integer> it;
		
	public int countHand(int[] input_hand)
	{
		Arrays.sort(input_hand);
		temp=new ArrayList<Integer>(0);
		figures=new ArrayList<ArrayList<Integer>>(0);
		tint=0;
		while(tint<4)
		{
			temp.add(input_hand[tint++]);
			while(tint<4 && Deck.val(input_hand[tint])==Deck.val(temp.get(0)))
			{
				temp.add(input_hand[tint++]);
			}
			figures.add(temp);
			temp=new ArrayList<Integer>(0);
		}
		
		for(int i=0;i<figures.size();i++)
		{
			other_colors=new ArrayList<Integer>(3);
			for(int j=0;j<figures.size();j++)
			{
				if(i!=j)
				{
					for(int k=0;k<(figures.get(j)).size();k++)
					{
						other_colors.add(Deck.col((figures.get(j)).get(k)));
					}
				}
			}
			it=(figures.get(i)).listIterator();
			while(it.hasNext())
			{
				tint=it.next();
				if((figures.get(i)).size()>1 && other_colors.contains(Deck.col(tint)))
				{
					it.remove();
				}
			}
		}
		
		temp=new ArrayList<Integer>(1);
		for(ArrayList<Integer> arl : figures)
		{
			temp.add(arl.get(0));
		}
		
		for(Integer i : temp)
		{
			for(Integer j : temp)
			{
				if(Deck.col(i)==Deck.col(j) && Deck.val(i)>Deck.val(j))
				{
					i=-1;
				}
			}
		}
		
		for(Integer i : temp)
		{
			if(i>=0)
			{
				points+=Deck.val(i);
			}
		}
		return points;	
	}
}
