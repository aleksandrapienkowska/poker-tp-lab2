package Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;

public class Evaluator 
{
	private int			tint,
						length;
	private int[]		result;
	private ArrayList<Integer> 	other_colors,
								temp;
	private ArrayList<ArrayList<Integer>> figures;
	private ListIterator<Integer> it;
		
	public int[] countHand(int[] input_hand)
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
		length=temp.size();
		result=new int[length];
		System.out.println(length+" "+temp);// ###
		
		for(int i=0;i<length;i++)
		{
			temp.set(i,100*(4-length)+Deck.val(temp.get(i)));
			System.out.println(temp.get(i));
		}
		Collections.sort(temp,Collections.reverseOrder());
		for(int i=0;i<length;i++)
		{
			result[i]=temp.get(i);
		}
		System.out.println(temp);
		return result;
	}
	
	/*public static void main (String args[]) 
	{
		Evaluator krupier = new Evaluator();
		int[] input = {4,5,40,0};
		krupier.countHand(input);
	}*/
}
