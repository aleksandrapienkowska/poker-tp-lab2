package Table;

import java.util.ArrayList;
import java.util.Random;

public class Table 
{
	Player[] 	gracze;
	Deck 		talia;
	Evaluator	krupier;
	Random		los;
	int			count, 
				i,temp,
				small_blind,
				big_blind,
				initial_cash,
				best_result,
				dealer,
				active_players,
				round,
				max_bet,
				pot;
	public int	current;
	int[]		ttab,
				results;
	String		response;
	ArrayList<Integer> winners;

	public Table(int size,int small,int big,int cash)
	{
		gracze=new Player[size];
		talia = new Deck();
		krupier=new Evaluator();
		los=new Random();
		small_blind=small;
		big_blind=big;
		initial_cash=cash;
		current=dealer=los.nextInt(size);
		
		System.out.println(gracze.length);
		for(int i=0;i<gracze.length;i++)
		{
			gracze[i]=new Player(i);
			gracze[i].setCash(initial_cash);
			gracze[i].setHand(newHand());
			System.out.println(i+" Tworze gracza...");
		}
	}
	
	private int nextPlayer(int try_id)
	{
		if(try_id==gracze.length)
		{
			try_id=0;
		}
		if(!gracze[try_id].checkActive())
		{
			try_id=nextPlayer(1+try_id);
		}
		return try_id;
	}
	
	private int[] newHand()
	{
		ttab=new int[4];
		for(i=0;i<4;i++)
		{
			ttab[i]=talia.takeCard();
		}
		return ttab;
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
	
	private void betBlind(int who,int what)
	{
		if(active_players==1 && what==big_blind)
		{
			endgame();
			return;
		}
		if(active_players==0 && what==small_blind)
		{
			//	!!! WSZYSCY BIEDNI ZAMYKAMY STOL !!!
		}
		if(who==gracze.length)
		{  
			who=0;
		}
		if(!gracze[who].checkActive())
		{
			betBlind(++who,what);
			return;
		}
		if(gracze[who].getCash()<=what)
		{
			gracze[who].lost=true;
			gracze[who].active=false;
			betBlind(++who,what);
		}
		else
		{
			gracze[who].bet(what);
			if(what==big_blind)
			temp=who;
		}
	}
	
	private void bet(int who, int what)
	{
		if(gracze[who].getCash()<what)
		{
			// !!! NO CHYBA NIE !!!
		}
		else
		{
			if(gracze[who].getCash()==what)
			{
				gracze[who].all_in=true;
				gracze[who].active=false;
			}
			gracze[who].bet(what);
			pot+=what;
			if(what>max_bet)
			{
				max_bet=what;
				for(Player p : gracze)
				{
					p.leader=false;
				}
				gracze[who].leader=true;
			}
		}
	}
	
	private void fold(int who)
	{
		gracze[who].fold=true;
		gracze[who].active=false;
	}
	
	private void newRound()
	{
		max_bet=0;
		for(i=0;i<gracze.length;i++)
		{
			if(gracze[i].small_blind==true)
			{
				current=nextPlayer(i);
			}
		}
	}
	
	private void newGame()
	{
		for(i=0;i<gracze.length;i++)
		{
			if(gracze[i].lost=false)
			{
				gracze[i].active=true;
				gracze[i].fold=false;
				gracze[i].all_in=false;
				gracze[i].big_blind=false;
				gracze[i].setHand(newHand());
				gracze[i].changeBet(0,0);
			}
		}
		max_bet=0;
		pot=0;
		dealer=nextPlayer(dealer+1);
		betBlind(dealer,small_blind);
		gracze[temp].small_blind=true;
		betBlind(temp+1,big_blind);
		gracze[temp].big_blind=true;
		current=nextPlayer(temp+1);
	}
	
	private ArrayList<Integer> showdown(Player[] who)
	{
		results=new int[who.length];
		winners=new ArrayList<Integer>();
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
	
	public int[] getHand(int player_id)
	{
		return gracze[player_id].getHand();
	}
	
	public boolean checkActive(int player_id)
	{
		return gracze[player_id].active;
	}
	
	public int getCurrent()
	{
		return current;
	}
	
	public Object[] listen(Object[] input)
	{
		return null;
	}
	
	public static void main (String args[]) 
	{
		Table stol = new Table(2,1,2,100);
		int[] cos = stol.getHand(0);
		System.out.println(cos[0]);
	}
}
