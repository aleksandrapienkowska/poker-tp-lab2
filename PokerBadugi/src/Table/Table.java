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
				i,temp,
				small_blind,
				big_blind,
				initial_cash,
				best_result,
				dealer,
				active_players,
				round,
				max_bet,
				pot,
				draw;
	public int	current;
	int[]		ttab;
	int[][]		results;
	String		response;
	Object[]	output;
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
		response="";
		active_players=size;
		round=1;
		dealer=los.nextInt(size);
		for(int i=0;i<gracze.length;i++)
		{
			gracze[i]=new Player(i);
			gracze[i].setCash(initial_cash);
			gracze[i].setHand(startingHand());
		}
		response+="\nRozpoczyna sie nowa partia...\n";
		betBlind(dealer,small_blind);
		betBlind(temp+1,big_blind);
		current=nextPlayer(temp+1);
	}
	
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
		ttab=new int[which.length];
		for(int i=0;i<which.length;i++)
		{
			if(which[i]>=0)
			{
				talia.dumpCard(which[i]);
				ttab[i]=talia.takeCard();
			}
		}
		response+=("Gracz "+who+" wymienia "+which.length+" karty\n");
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
			if(what==big_blind)
			{
				response+="Gracz "+who+" stawia duza ciemna\n";
				gracze[who].big_blind=true;
			}
			else if(what==small_blind)
			{
				response+="Gracz "+who+" stawia mala ciemna\n";
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
			response+=("Gracz "+who+" stawia "+amount+", lacznie: "+gracze[who].bet+"\n");
			pot+=amount;
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
	
	private boolean checkIfEnd()
	{
		temp=nextPlayer(current+1);
		if((gracze[temp].leader==true && (gracze[temp].big_blind==false || round>1)) 
			|| active_players==1 || (gracze[current].big_blind==true && round==1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void endgame()
	{
		if(active_players==1)
		{
			for(Player p : gracze)
			{
				if(p.active==true)
				{
					response+=("Gracz "+p.id+" zgarnia pule!\n");
					p.cash+=pot;
					return;
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
	
	private void fold(int who)
	{
		response+=("Gracz "+who+" folduje\n");
		gracze[who].fold=true;
		gracze[who].active=false;
		active_players--;
	}
	
	private void check(int who)
	{
		response+=("Gracz "+who+" checkuje\n");
		if(gracze[who].big_blind==true && round==1)
		{
			newRound();
		}
	}
	
	private void newRound()
	{
		round++;
		if(round==5)
		{
			response+="Minela tura 4\n";
			endgame();
			return;
		}
		response+="Rozpoczyna sie "+round+" tura...\n";
		max_bet=0;
		for(int i=0;i<gracze.length;i++)
		{
			if(gracze[i].small_blind==true)
			{
				current=nextPlayer(i+1);
			}
		}
	}
	
	private void newGame()
	{
		active_players=0;
		for(int i=0;i<gracze.length;i++)
		{
			if(gracze[i].cash==0)
			{
				response+=("Gracz "+i+" przegrywa");
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
		response+="\nRozpoczyna sie nowa partia...\n";
		max_bet=0;
		pot=0;
		round=1;
		dealer=nextPlayer(dealer+1);
		betBlind(dealer,small_blind);
		betBlind(dealer+1,big_blind);
		current=nextPlayer(temp+1);
	}
	
	private void showdown(int[] who)
	{
		response+="Showdown!\n";
		results=new int[who.length][];
		for(int i=0;i<who.length;i++)
		{
			results[i]=krupier.countHand(gracze[who[i]].getHand());
			response+=("Gracz "+who[i]+" ma "+gracze[who[i]].hand.toString()+"\n");
		}
		for(int i=0;i<results.length-1;i++)
		{
			for(int j=0;i<results.length;j++)
			{
				if(i==j)
				{
					continue;
				}
				else
				{
					for(int k=0;k<4;k++)
					{
						if(k<results[i].length-1 && results[i][k]==results[j][k])
						{
							continue;
						}
						else
						{
							if(k==results[i].length-1 && results[i][k]==results[j][k])
							{
								gracze[i].points++;
								gracze[j].points++;
								break;
							}
							else if(results[i][k]<results[j][k])
							{
								gracze[i].points++;
								break;
							}
							else
							{
								break;
							}
						}
					}
				}
			}
		}
		best_result=0;
		for(Player p : gracze)
		{
			if(p.points>best_result)
			{
				best_result=p.points;
			}
		}
		
		payment(best_result,who);
	}
	
	private void payment(int points, int[] who) 
	{
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
						response+=("Gracz "+p.id+" bierze z puli "+p.bet*active_players+"\n");
						payment(points-1,who);
					}
					else
					{
						p.cash+=pot;
						response+=("Gracz "+p.id+" bierze z puli "+pot+"\n");
					}
				}
			}
			payment(points-1,who);
		}
		if(draw>1)
		{
			response+="Zaszedl remis, co zostalo w puli przepada\n";
		}
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
	
	public int getMaxBet()
	{
		return max_bet;
	}
	
	public Object[] listen(Object[] input)
	{
		Arrays.deepToString(input);
		if((Integer)input[1]==1)
		{
			// ### AKCJA BANKOWA ###
			switch((int)input[2])
			{
				case 1:	check((int)input[0]);break;
				case 2: bet((int)input[0],(int)input[3]);break;
				case 3: fold((int)input[0]);break;
			}
		}
		if((Integer)input[1]==2)
		{
			// ### WYMIANA KART ###
			ttab=new int[]{(int)input[3],(int)input[4],(int)input[5],(int)input[6]};
			swapCards((int)input[0],ttab);
			output=new Object[]{input[0],input[1],0,gracze[(int)input[0]].getHand()[0],
													gracze[(int)input[0]].getHand()[1],
													gracze[(int)input[0]].getHand()[2],
													gracze[(int)input[0]].getHand()[3]};
		}
		if(checkIfEnd())
		{
			newRound();
		}
		else
		{
			current=nextPlayer(current+1);
			response+=("Akcja gracza "+current+"\n");
		}
		Arrays.deepToString(output);
		return output;
	}
	
	public static void main (String args[]) 
	{
		Table stol = new Table(3,1,2,100);
		/*int[] cos;
		for (int i = 0; i<13;i++) 
		{
			System.out.println("Talia : "+stol.talia.talia.size());
			System.out.println("Dumped: "+stol.talia.dumped.size());
			cos = stol.getHand(0);
			System.out.println(cos[0] + " " + cos[1] + " " + cos[2] + " "+ cos[3]);
			System.out.println(stol.response);
			stol.response="";
			Object[] foo = new Object[]{0,1,0,stol.gracze[0].getHand()[0],
											stol.gracze[0].getHand()[1],
											stol.gracze[0].getHand()[2],
											stol.gracze[0].getHand()[3]};
			stol.listen(foo);
			//stol.swapCards(0, stol.gracze[0].getHand());
		}*/
		//System.out.println(stol.nextPlayer(1));
		System.out.println(stol.response);
		System.out.println(stol.current);
	}
}
