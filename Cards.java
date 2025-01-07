package project;

import java.util.ArrayList;
import java.util.Random;

public class Cards {

	ArrayList<Card> deck = new ArrayList<Card>(); //存放一副牌的地方
	Random random = new Random();
	
	public Cards() //新增此物件代表新增一副牌
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
	public Card drawCard() //抽牌
	{
		if(deck.size() >0)
			return deck.remove(random.nextInt(deck.size())); //用random獲取亂數，並用該值當作index來remove
		else
			return null;
	}
	public void reset() //把整副牌重製回52張
	{
		deck = new ArrayList<Card>();
		for(int i = 0 ; i <= 3 ; i++)
			for(int j = 1 ; j <= 13 ; j++)
			{
				deck.add(new Card(i,j));
			}
	}
			
}
