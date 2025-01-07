package project;

public class Card {
	int suit;		// 0 : 梅花  1 : 方塊 , 2 : 愛心 , 3 : 黑桃
	int rank;		// 1: A; 2: 2; 11: J; 12: Q; 13: K
	
	final String[] suits = {"\u2663", "\u2666", "\u2764", "\u2660"}; // 梅花 方塊 愛心 黑桃的unicode
	final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9","10", "J", "Q", "K"}; 
	final int[] points = { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 10 , 10 , 10 }; //每個牌代表的點數，A先以1計
	public Card(int s, int r) //新增一張牌
	{
		suit = s;
		rank = r;
	}
	public int getSuit()
	{	
		return suit;
	}
	public int getRank()
	{
		return rank;
	}
	public int getPoint()
	{
		return points[rank-1];
	}
	public String toString()
	{
		
		return suits[suit]+ranks[rank-1];
	}
}
