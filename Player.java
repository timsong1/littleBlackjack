package project;

import java.util.ArrayList;

public class Player implements Comparable{  
	int money; 
	ArrayList<Card> CardOnHand = new ArrayList<Card>(); 
	
	public Player()
	{
		money = 1000; 
	}
	
	public void addCard(Card card)
	{
		CardOnHand.add(card);
	}
	public int getNumofCards() 
	{
		return CardOnHand.size();
	}
	public String getCards() 
	{
		String text = "";
		for(Card card : CardOnHand)
		{
			text += card + " ";
		}
		return text;
	}
	public int getPoints() 
	{
		int point = 0;
		int count = 0; 
		for(Card card : CardOnHand)
		{
			if(card.getRank() == 1)
				count ++;
			else if(card.getRank() == 11 || card.getRank() ==12 || card.getRank() == 13)
				point += 10;
			else
				point += card.getRank();
		}
		for(int i = 0 ; i < count ; i++) 
		{
			if((point + 11) > 21) 
				point += 1;
			else				  
				point += 11; 
		}
		return point;
	}
	@Override
	public int compareTo(Object arg0) { 
		// TODO Auto-generated method stub
		Player player = (Player)arg0; 
		return getPoints() - player.getPoints();
	}
	public double getSaveRate(Cards cards) 
	{
		int points = getPoints();
		double count = 0;
		for(Card item : cards.deck)
		{
			if(item.getPoint() + points <= 21)
				count++;
		}
		return count/cards.deck.size();
	}
	public double getLoseRate(Cards cards) 
	{
		int points = getPoints();
		double count = 0;
		for(Card item : cards.deck)
		{
			if(item.getPoint() + points > 21)
				count++;
		}
		return count/cards.deck.size();
	}
	public void addMoney(int money)
	{
		this.money += money;
	}
	public void reduceMoney(int money)
	{
		this.money -= money;
	}
	public int getMoney()
	{
		return money;
	}
	public void clear() 
	{
		CardOnHand.clear();
	}
	public boolean isContinue(Cards cards) // never used
	{
		return true;
	}

	
	
}
