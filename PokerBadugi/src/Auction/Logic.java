package Auction;
import java.util.*;

import Client.Client;

public class Logic {

	static int numberRound=1;
	static int MaxBet=0;
	static int TotalAmount=10000;
	static int bankroll=100;
	/*Stany użytkowników: 
	 * INITIAL-stan początkowy pierwszej rozgrywki dla każdego usera
	 * MB<MX-kwota postawiona przez usera mniejsza od poprzedniego zakladu
	 * MB=MX-kwota postawiona przez usera równa poprzedniemu zakladowi
	 * MAI-user gra AllIn
	 * MO-user zagral Fold
	 */
	static String[] UsersStatus={"INITIAL","MB<MX","MB=MX","MAI","MO"};
	public Logic(){
		
	}
	
	public static void MyBet(int amount, Client player){
		if(amount>MaxBet){
			MaxBet=amount;
			player.Status="MB=MX";
		}
		else if(amount==MaxBet){
			player.Status="MB=MX";
		}
		else{
			//blokowanie 
		}
	}
	
	public static String getMaxBet(){
		return Integer.toString(MaxBet);
	}

	public static String getTotalAmount(){
		return Integer.toString(TotalAmount);
	}

	public static String getbankRoll(){
		return Integer.toString(bankroll);
		}
	public static String getRound(){
		return Integer.toString(numberRound);
	}







}
