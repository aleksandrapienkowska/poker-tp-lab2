package Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;

public class Evaluator 
{
        private int             tint;
        private int[]           true_hand,output;
        private ArrayList<Integer>      other_colors,
                                        temp,foo;
        private ArrayList<ArrayList<Integer>> figures, colours;
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
                foo=temp;
                //System.out.println(foo);
                
                colours=new ArrayList<ArrayList<Integer>>(0);
                for(int i=0;i<foo.size();i++)
                {
                	if(foo.get(i)>=0)
                	{
                		temp=new ArrayList<Integer>(0);
                		temp.add(foo.get(i));
                		foo.set(i,-1);
                	   	for(int j=0;j<foo.size();j++)
	                	{
	                		if(i!=j && foo.get(j)>=0 
	                		&& Deck.col(temp.get(0))==Deck.col(foo.get(j)))
	                		{
	                			temp.add(foo.get(j));
	                  			foo.set(j,-1);
	                		}
	                	}
                	   	colours.add(temp);
                	}
                }
                
                //System.out.println("Wg kolorow: "+colours);
                true_hand=new int[colours.size()];
                tint=0;
                for(int i=0;i<colours.size();i++)
                {
              		Collections.sort(colours.get(i));
               		true_hand[tint++]=(colours.get(i)).get(0);
               	}
                //System.out.println("Prawdziwa reka: "+Arrays.toString(true_hand));
                
                temp=new ArrayList<Integer>(0);
                for(int i=0;i<true_hand.length;i++)
                {
                        temp.add(100*(4-true_hand.length)+Deck.val(true_hand[i]));
                        //System.out.println(temp.get(i));
                }
                Collections.sort(temp,Collections.reverseOrder());
                output=new int[true_hand.length];
                for(int i=0;i<true_hand.length;i++)
                {
                        output[i]=temp.get(i);
                }
                //System.out.println(Arrays.toString(output));
                return output;
        }
        
        /*public static void main (String args[]) 
        {
                Evaluator krupier = new Evaluator();
                int[] input = {5,21,31,44};
                krupier.countHand(input);
        }*/
}