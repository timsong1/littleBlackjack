package project;

import java.util.ArrayList;

public class Player implements Comparable{  //實作compareTo方法
	int money; //player身上的錢
	ArrayList<Card> CardOnHand = new ArrayList<Card>(); //player手上的牌
	
	public Player()
	{
		money = 1000; //一開始都定為1000元
	}
	
	public void addCard(Card card)
	{
		CardOnHand.add(card);
	}
	public int getNumofCards() //手牌數
	{
		return CardOnHand.size();
	}
	public String getCards() //取得手上所有的牌
	{
		String text = "";
		for(Card card : CardOnHand)
		{
			text += card + " ";
		}
		return text;
	}
	public int getPoints() //取得目前的點數
	{
		int point = 0;
		int count = 0; //A的次數
		for(Card card : CardOnHand)
		{
			if(card.getRank() == 1)
				count ++;
			else if(card.getRank() == 11 || card.getRank() ==12 || card.getRank() == 13)
				point += 10;
			else
				point += card.getRank();
		}
		for(int i = 0 ; i < count ; i++) // 最後再計算A的點數
		{
			if((point + 11) > 21) //如果以11點算，大於21的話就用1點算
				point += 1;
			else				  //沒有的話就是用11點算
				point += 11; 
		}
		return point;
	}
	@Override
	public int compareTo(Object arg0) { //比較兩個Player的點數
		// TODO Auto-generated method stub
		Player player = (Player)arg0; //傳入的Player
		return getPoints() - player.getPoints();
	}
	public double getSaveRate(Cards cards) //不爆牌的機率
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
	public double getLoseRate(Cards cards) //爆牌的機率
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
	public void clear() //清空手上的牌
	{
		CardOnHand.clear();
	}
	public boolean isContinue(Cards cards) //只會用到多型的部分，所以這裡不寫
	{
		return true;
	}

	
	
}
