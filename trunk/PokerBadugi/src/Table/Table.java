package Table;

import java.util.ArrayList;

public class Table 
{
	Player[] 	gracze;
	Deck 		talia;
	Evaluator	krupier;
	int 		count, 
				i,temp,
				best_result;
	int[]		ttab,
				results;
	ArrayList<Integer> winners;

	Table(int size)
	{
		gracze=new Player[size];
		talia = new Deck();
		krupier=new Evaluator();
	}
	
	private void newHand(int who)
	{
		ttab=new int[4];
		for(i=0;i<4;i++)
		{
			ttab[i]=talia.takeCard();
		}
		gracze[who].setHand(ttab);
	}
	
	private void swapCards(int who, int[] which)
	{
		ttab=new int[which.length];
		for(i=0;i<which.length;i++)
		{
			talia.dumpCard(which[i]);
			ttab[i]=talia.takeCard();
		}
		gracze[who].changeCards(which,ttab);
	}
	
	private ArrayList<Integer> showdown(Player[] who)
	{
		results=new int[who.length];
		i=0;
		for(Player p : who)
		{
			results[i++]=krupier.countHand(p.getHand());
		}
		for(i=0;i<results.length-1;i++)
		{
			best_result=Math.min(results[i],results[i+1]);
		}
		for(Player p : who)
		{
			if(best_result==krupier.countHand(p.getHand()))
			{
				winners.add(p.getNumber());
			}
		}
		return winners;
	}
	public Object[] listen(Object[] input)
	{
		newHand(count++);//Nowy gracz
		swapCards(0,null); //Podmiana kart
		showdown(null);
		
		return null;
	}
}
