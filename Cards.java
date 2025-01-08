package project;

import java.util.ArrayList;
import java.util.Random;

public class Cards {

	ArrayList<Card> deck = new ArrayList<Card>();
	Random random = new Random();
	
	public Cards() 
	{
		for(int i = 0 ; i <= 3 ; i++) //0~3
			for(int j = 1 ; j <= 13 ; j++) //1~13
			{
				deck.add(new Card(i,j));
			}
	}
	
	
	public String toString()
	{
		String cards = "";
		for(Card card : deck)
			cards += card+" ";
		return cards;
	}
	public Card drawCard() 
	{
		if(deck.size() >0)
			return deck.remove(random.nextInt(deck.size())); 
		else
			return null;
	}
	public void reset() 
	{
		deck = new ArrayList<Card>();
		for(int i = 0 ; i <= 3 ; i++)
			for(int j = 1 ; j <= 13 ; j++)
			{
				deck.add(new Card(i,j));
			}
	}
			
}
