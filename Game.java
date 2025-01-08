package project;

import java.util.Random;
import java.util.Scanner;

public class Game {

	Player p1; 
	Player p2; 
	Player final_winner; 
	int bet;  
	Cards cards = new Cards(); 
	
	public Game(String name) 
	{
		p1 = new People(name); 
		p2 = new Computer();  
	}
	public Game(String name , String name2) 
	{
		p1 = new People(name);
		p2 = new People(name2);	
	}
	public void start() 
	{
		Scanner in = new Scanner(System.in);
		
		
		while(true) 
		{
			bet = 0;
			Player winner = null; 
			Player loser = null;  
			boolean p1_continue = true;
			boolean p2_continue = true;
			System.out.println(p1+" 目前有 "+p1.getMoney()+"元  "+p2+" 目前有 "+p2.getMoney()+"元");
			do {
				if(!(p2 instanceof Computer)) 
					System.out.println("這一輪想賭多少錢? 兩位自己決定好");
				else	//只有一個玩家和電腦
					System.out.println("這一輪想賭多少錢?");
				try {
					bet = in.nextInt();
					in.nextLine(); 
				}catch(Exception e) 
				{
					System.out.println("請輸入 '大於0' 的 '數字' !");
					in.nextLine();
					bet = 0;
				}
			}while(bet <= 0); 
			
			
			distribute(p1,2); 
			distribute(p2,2);
			while(p1_continue || p2_continue) 
			{
				int p1_points = p1.getPoints(); 
				int p2_points = p2.getPoints();
				
				System.out.println("\n######發牌######");
				System.out.println(p1+" 的牌 :    "+p1.getCards()+"  點數 : "+p1_points);
				System.out.println(p2+" 的牌 :    "+p2.getCards()+"  點數 : "+p2_points);
				
				if(p1_points == 21) 
				{
					if(p2_points == 21)
					{
						System.out.println("******************");
						System.out.println("兩邊都21點，所以這局平手不算錢");
						System.out.println("******************");
						bet = 0;
						break;
					}
					else
					{
						System.out.println("******************");
						System.out.println(p1+" 21點 ， 贏了");
						System.out.println("******************");
						winner = p1;
						loser = p2;
						break;
					}
				}
				else if(p2_points == 21)
				{
					System.out.println("******************");
					System.out.println(p2+" 21點 ， 贏了");
					System.out.println("******************");
					winner = p2;
					loser = p1;
					break;
				}
				else if(p1_points > 21)
				{
					if(p2_points > 21)
					{
						System.out.println("******************");
						System.out.println("兩邊都爆掉了，所以這局平手不算錢");
						System.out.println("******************");
						bet = 0;
						break;
					}
					else
					{
						System.out.println("******************");
						System.out.println(p1+" 爆掉 ， "+p2+" 贏了");
						System.out.println("******************");
						winner = p2;
						loser = p1;
						break;
					}
				}
				else if(p2_points > 21)
				{
					System.out.println("******************");
					System.out.println(p2+" 爆掉 ， "+p1+" 贏了");
					System.out.println("******************");
					winner = p1;
					loser = p2;
					break;
				}
				
				if(p1_continue) 
				{
					p1_continue = p1.isContinue(cards); 
														//People和Computer的判斷方式不一樣(多型)
					if(p1_continue) 
					{
						distribute(p1,1);
					}
				}
				if(p2_continue)
				{
					p2_continue = p2.isContinue(cards);
					if(p2_continue) 
					{
						distribute(p2,1);
					}
				}
				
			}
			if(winner == null && loser == null) 
			{
				if(bet > 0) 
				{
					if(p1.compareTo(p2) >0) 
					{
						System.out.println("******************");
						System.out.println(p1+" 點數大於 "+p2+" , "+ p1+"贏了");
						System.out.println("******************");
						winner = p1;
						loser = p2;
					}
					else if(p1.compareTo(p2) == 0) 
					{
						System.out.println("******************");
						System.out.println("~ 平手 ， 所以這局不算錢");
						System.out.println("******************");
						bet = 0;
						cards.reset();
						p1.clear();
						p2.clear();
						continue;
					}
					else 
					{
						System.out.println("******************");
						System.out.println(p2+" 點數大於 "+p1+" , "+ p2+"贏了");
						System.out.println("******************");
						winner = p2;
						loser = p1;
					}
					
				}
				else 
				{
					cards.reset();
					p1.clear();
					p2.clear();
					continue;
				}
			}
			if(bet > 0) 
			{
				if(winner.getNumofCards() >= 5) 
				{
					bet *= 2;
					System.out.println("過五關，獎金乘2，為"+bet+" 元");
				}
				System.out.println(winner+" 贏了 "+bet+"元    "+loser+" 輸了 "+bet+"元");
				winner.addMoney(bet); 
				loser.reduceMoney(bet); 
			}
			cards.reset(); 
			p1.clear(); 
			p2.clear();
			if(loser.getMoney() <=0) 
			{
				System.out.println(loser+" 已經沒錢了~  "+winner+" 是**最終贏家**");
				final_winner = winner; 
				break;
			}
		}
	}
	public void distribute(Player p ,int times)  
	{
		Random random = new Random();
		for(int i = 0 ; i < times ; i++)
		{
			p.addCard(cards.drawCard());
		}
	}
	public Player getFinalWinner()
	{
		return final_winner;
	}
	public String toString()
	{
		return "玩家1 : "+p1+"   玩家2 : "+p2;
	}
}
