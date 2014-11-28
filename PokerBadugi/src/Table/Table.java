package Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Table 
{
	Player[] 	gracze;
	Deck 		talia;
	Evaluator	krupier;
	Random		los;
	int			count, 
				i,temp,foo,bar,
				small_blind,
				big_blind,
				initial_cash,
				best_result,
				dealer,
				active_players,
				round,
				max_bet,
				pot,
				draw,
				swapped;
	public int	current;
	int[]		ttab;
	int[][]		results;
	String		response;
	Object[]	output;
	ArrayList<Integer> winners;
	boolean 	new_game,
				swapping,
				alive;

	public Table(int size,int small,int big,int cash)
	{
		gracze=new Player[size];
		talia = new Deck();
		krupier=new Evaluator();
		los=new Random();
		alive=true;
		small_blind=small;
		big_blind=big;
		initial_cash=cash;
		response="";
		active_players=size;
		round=1;
		pot=0;
		dealer=los.nextInt(size);
		for(int i=0;i<gracze.length;i++)
		{
			gracze[i]=new Player(i);
			gracze[i].setCash(initial_cash);
			gracze[i].setHand(startingHand());
		}
		System.out.println("|Rozpoczyna sie nowa partia...|");
		betBlind(dealer+1,small_blind);
		betBlind(temp+1,big_blind);
		current=nextPlayer(temp+1);
		System.out.println("Akcja gracza "+current+"|");
	}
	
	// ##### OBSLUGA GRACZY #####
	private int[] startingHand()
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
		temp=0;
		for(int i=0;i<which.length;i++)
		{
			if(which[i]>=0)
			{
				temp++;
			}
		}
		ttab=new int[temp];
		temp=0;
		for(int i=0;i<which.length;i++)
		{
			if(which[i]>=0)
			{
				talia.dumpCard(which[i]);
				ttab[temp]=talia.takeCard();
			}
		}
		System.out.println("Gracz "+who+" wymienia "+ttab.length+" karty|");
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
			alive=false;
			return;
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
		if(gracze[who].getCash()<what)
		{
			gracze[who].lost=true;
			gracze[who].active=false;
			for(int i=0;i<4;i++)
			{
				talia.dumpCard(gracze[who].hand[i]);
			}
			active_players--;
			betBlind(++who,what);
		}
		else
		{
			gracze[who].bet(what);
			pot+=what;
			if(what==big_blind)
			{
				max_bet=big_blind;
				System.out.println("Gracz "+who+" stawia duza ciemna|");
				gracze[who].big_blind=true;
			}
			else if(what==small_blind)
			{
				System.out.println("Gracz "+who+" stawia mala ciemna|");
				gracze[who].small_blind=true;
			}
			temp=who;
		}
	}
	
	private void bet(int who, int amount)
	{
		if(gracze[who].getCash()<amount)
		{
			return;
		}
		else
		{
			if(gracze[who].getCash()==amount)
			{
				gracze[who].all_in=true;
				gracze[who].active=false;
			}
			gracze[who].bet(amount);
			pot+=amount;
			System.out.println("Gracz "+who+" stawia "+amount+", lacznie: "+gracze[who].bet+"|");
			if(amount>max_bet)
			{
				max_bet=amount;
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
		System.out.println("Gracz "+who+" folduje|");
		gracze[who].fold=true;
		gracze[who].active=false;
		active_players--;
	}
	
	private void check(int who)
	{
		System.out.println("Gracz "+who+" checkuje|");
	}
	
	// ##### OBSLUGA ROZGRYWKI #####
	private int nextPlayer(int try_id)
	{
		if(try_id>=gracze.length)
		{
			try_id=0;
		}
		if(!gracze[try_id].checkActive())
		{
			try_id=nextPlayer(1+try_id);
		}
		return try_id;
	}
	
	private boolean checkIfEnd()
	{
		temp=nextPlayer(current+1);
		if((gracze[temp].leader==true && (gracze[temp].big_blind==false || round>1)) 
			|| active_players==1 || (gracze[current].big_blind==true && round==1 && max_bet==big_blind))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void newRound()
	{
		round++;
		if(round==5)
		{
			System.out.println("Minela tura 4|");
			endgame();
			return;
		}
		swapped=0;
		swapping=true;
		System.out.println("|Rozpoczyna sie "+round+" tura...|");
		max_bet=0;
		for(Player p : gracze)
		{
			p.leader=false;
		}
		for(int i=0;i<gracze.length;i++)
		{
			if(gracze[i].big_blind==true)
			{
				current=nextPlayer(i+1);
				gracze[current].leader=true;
				System.out.println("Akcja gracza "+current+"|");
			}
		}
	}
	
	private void newGame()
	{
		new_game=true;
		active_players=0;
		for(int i=0;i<gracze.length;i++)
		{
			if(gracze[i].cash==0)
			{
				System.out.println("Gracz "+i+" przegrywa");
				gracze[i].lost=true;
			}
			if(gracze[i].lost==false)
			{
				active_players++;
				gracze[i].active=true;
				swapCards(i,gracze[i].getHand());
			}
			gracze[i].fold=false;
			gracze[i].all_in=false;
			gracze[i].small_blind=false;
			gracze[i].big_blind=false;
			gracze[i].changeBet(0,0);
			gracze[i].points=0;
		}
		System.out.println("|Rozpoczyna sie nowa partia...|");
		max_bet=0;
		pot=0;
		swapped=0;
		swapping=false;
		round=1;
		dealer=nextPlayer(dealer+1);
		betBlind(dealer+1,small_blind);
		betBlind(temp+1,big_blind);
		current=nextPlayer(temp+1);
		System.out.println("Akcja gracza "+current+"|");
	}
	
	private void endgame()
	{
		if(active_players==1)
		{
			for(Player p : gracze)
			{
				if(p.active==true)
				{
					System.out.println("Gracz "+p.id+" zgarnia pule!|");
					p.cash+=pot;
				}
			}
		}
		else
		{
			ttab=new int[active_players];
			i=0;
			for(Player p : gracze)
			{
				if(p.active==true || p.all_in==true)
				{
					ttab[i++]=p.id;
				}
			}
			showdown(ttab);
		}
		newGame();
	}
	
	// ##### OBSLUGA SHOWDOWN'U ######
	private void showdown(int[] who)
	{
		System.out.println("Showdown!|");
		results=new int[who.length][];
		for(int i=0;i<who.length;i++)
		{
			results[i]=krupier.countHand(gracze[who[i]].getHand());
			System.out.println("Gracz "+who[i]+" ma "+Arrays.toString(gracze[who[i]].hand)+"|");
		}
		System.out.println("Finalowcy: "+results.length);
		System.out.println(Arrays.deepToString(results));
		for(int i=0;i<results.length;i++)
		{
			for(int j=0;j<results.length;j++)
			{
				if(i==j)
				{
					System.out.println("a");
					continue;
				}
				else if(results[i].length!=results[j].length)
				{
					System.out.println("b");
					if(results[i][0]<results[j][0])
					{
						gracze[who[i]].points++;
					}
				}
				else if(results[i].length==results[j].length)
				{
					System.out.println("c");
					for(int k=0;k<results[i].length;k++)
					{
						if(results[i][k]==results[j][k])
						{
							if(k==results[i].length-1)
							{
								gracze[who[i]].points++;
							}
						}
						else
						{
							break;
						}
					}
					for(int k=0;k<results[i].length;k++)
					{
						if(results[i][k]<=results[j][k])
						{
							if(results[i][k]<results[j][k])
							{
								gracze[who[i]].points++;
							}
						}
						else
						{
							break;
						}
					}
				}
			}
		}
		best_result=0;
		for(Player p : gracze)
		{
			System.out.println("Gracz "+p.id+" ma "+p.points+" punkty|");
			if(p.points>best_result)
			{
				best_result=p.points;
			}
		}
		payment(best_result,who);
	}
	
	private void payment(int points, int[] who) 
	{
		System.out.println("Wyplata dla: "+points+" punktow|");
		draw=0;
		for(Player p : gracze)
		{
			if(p.points==points)
			{
				draw++;
			}
		}
		if(draw==0 && points>=0)
		{
			payment(points-1,who);
		}
		if(draw==1)
		{
			for(Player p : gracze)
			{
				if(p.points==points)
				{
					if(pot>p.bet*active_players)
					{
						p.cash+=p.bet*active_players;
						System.out.println("Gracz "+p.id+" bierze z puli "+p.bet*active_players+"|");
						payment(points-1,who);
					}
					else
					{
						p.cash+=pot;
						System.out.println("Gracz "+p.id+" zgarnia pule "+pot+"|");
						pot=0;
					}
				}
			}
		}
		if(draw>1)
		{
			System.out.println("Remis, "+pot+" z puli przepada|");
			pot=0;
		}
	}
	
	// ##### METODY PUBLICZNE #####
	public boolean quit(int player_id)
	{
		gracze[player_id].lost=true;
		gracze[player_id].active=false;
		active_players--;
		temp=0;
		for(Player p : gracze)
		{
			if(p.lost==false)
			{
				temp++;	
			}
		}
		if(temp<=1)
		{
			alive=false;
		}
		return alive;
	}
	
	public int[] getHand(int player_id)
	{
		return gracze[player_id].getHand();
	}
	
	public int getCash(int player_id)
	{
		return gracze[player_id].cash;
	}
	
	public boolean checkActive(int player_id)
	{
		return gracze[player_id].active;
	}
	
	public boolean checkSwapping()
	{
		return swapping;
	}
	
	public int getCurrent()
	{
		return current;
	}
	
	public int getMyBet(int player_id)
	{
		return gracze[player_id].bet;
	}
	
	public int getMaxBet()
	{
		return max_bet;
	}
	
	public int getPot()
	{
		return pot;
	}
	
	public String getResponse()
	{
		return response;
	}
	
	public Object[] listen(Object[] input)
	{
		response="";
		if((Integer)input[1]==1)
		{
			// ### AKCJA BANKOWA ###
			switch((int)input[2])
			{
				case 1:	check((int)input[0]);break;
				case 2: bet((int)input[0],(int)input[3]);break;
				case 3: fold((int)input[0]);break;
				case 4: quit((int)input[0]);break;
			}
			output=new Object[]{alive};
		}
		if((Integer)input[1]==2)
		{
			// ### WYMIANA KART ###
			swapped++;
			ttab=new int[]{(int)input[3],(int)input[4],(int)input[5],(int)input[6]};
			swapCards((int)input[0],ttab);
			output=new Object[]{input[0],input[1],0,gracze[(int)input[0]].getHand()[0],
													gracze[(int)input[0]].getHand()[1],
													gracze[(int)input[0]].getHand()[2],
													gracze[(int)input[0]].getHand()[3]};
			temp=0;
			for(Player p : gracze)
			{
				if(p.lost==false)
				{
					temp++;
				}
			}
			if(temp==swapped)
			{
				swapping=false;
			}
		}
		if((int)input[1]==1 && checkIfEnd()) 
		{
			if(active_players == 1) 
			{
				endgame();
			}
			if(new_game==false)
			{
				newRound();
			}
		} 
		else 
		{
			current = nextPlayer(current + 1);
			response += ("Akcja gracza " + current + "|");
		}
		new_game=false;
		return output;
	}
}
