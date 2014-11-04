package Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck
{
	ArrayList<Integer> talia,dumped;
	int temp;
	Random random;
	private static enum Cards 
	{
		//Pierwsza cyfra w nawiasie pokazuje ID karty - po tym ja wolamy za pomoca Cards.get(id)
		AS_a(0,1,1),		AS_b(1,1,2),		AS_c(2,1,3),		AS_d(3,1,4),
		DWA_a(4,2,1),		DWA_b(5,2,2),		DWA_c(6,2,3),		DWA_d(7,2,4),
		TRZY_a(8,3,1),		TRZY_b(9,3,2),		TRZY_c(10,3,3),		TRZY_d(11,3,4),
		CZTERY_a(12,4,1),	CZTERY_b(13,4,2),	CZTERY_c(14,4,3),	CZTERY_d(15,4,4),
		PIEC_a(16,5,1),		PIEC_b(17,5,2),		PIEC_c(18,5,3),		PIEC_d(19,5,4),
		SZESC_a(20,6,1),	SZESC_b(21,6,2),	SZESC_c(22,6,3),	SZESC_d(23,6,4),
		SIEDEM_a(24,7,1),	SIEDEM_b(25,7,2),	SIEDEM_c(26,7,3),	SIEDEM_d(27,7,4),
		OSIEM_a(28,8,1),	OSIEM_b(29,8,2),	OSIEM_c(30,8,3),	OSIEM_d(31,8,4),
		DZIEWIEC_a(32,9,1),	DZIEWIEC_b(33,9,2),	DZIEWIEC_c(34,9,3),	DZIEWIEC_d(35,9,4),
		DZIESIEC_a(36,10,1),DZIESIEC_b(37,10,2),DZIESIEC_c(38,10,3),DZIESIEC_d(39,10,4),
		JOPEK_a(40,11,1),	JOPEK_b(41,11,2),	JOPEK_c(42,11,3),	JOPEK_d(43,11,4),
		QUEEN_a(44,12,1),	QUEEN_b(45,12,2),	QUEEN_c(46,12,3),	QUEEN_d(47,12,4),
		KING_a(48,13,1),	KING_b(49,13,2),	KING_c(50,13,3),	KING_d(51,13,4);
		@SuppressWarnings("unused")
		private final int id; //0 talia, 1 reka gracza, -1 stos odrzuconych
		private final int wartosc; //wg zasad badugi
		private final int kolor; //1-czerwo,2-wino,3-zoladz,4-dzwonki
		
		private Cards(int id,int wartosc,int kolor)
		{
			this.id=id;
			this.wartosc=wartosc;
			this.kolor=kolor;
		}
	}

	@SuppressWarnings("serial")
	public Deck()
	{
		talia=new ArrayList<Integer>(52){{
			add(1);	add(2);	add(3);	add(4);	add(5);	add(6);	add(7);	add(8);	add(9);	add(10);add(11);add(12);add(13);
			add(14);add(15);add(16);add(17);add(18);add(19);add(20);add(21);add(22);add(23);add(24);add(25);add(26);
			add(27);add(28);add(29);add(30);add(31);add(32);add(33);add(34);add(35);add(36);add(37);add(38);add(39);
			add(40);add(41);add(42);add(43);add(44);add(45);add(46);add(47);add(48);add(49);add(50);add(51);add(52);
		}};
		dumped=new ArrayList<Integer>();
		random = new Random();
		Collections.shuffle(talia,random);
	}
	
	public int takeCard()
	{
		if(talia.size()==0)
		{
			reshuffle();
		}
		temp=talia.get(0);
		talia.remove(0);
		talia.trimToSize();
		return temp;
	}
	
	private void reshuffle()
	{
		talia=dumped;
		dumped=new ArrayList<Integer>();
		Collections.shuffle(talia,random);
	}
	
	public void dumpCard(int id)
	{
		dumped.add(id);
	}
	
	public static int val(int id)
	{
		return (Cards.values()[id]).wartosc;
	}
	
	public static int col(int id)
	{
		return (Cards.values()[id]).kolor;
	}
}
