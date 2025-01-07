package project;

import java.util.Random;
import java.util.Scanner;

public class Game {

	Player p1; //玩家1
	Player p2; //玩家2
	Player final_winner; //最終贏家
	int bet;  //賭金
	Cards cards = new Cards(); //一副牌
	
	public Game(String name) // 只有一個玩家名稱，代表另一位由電腦擔任
	{
		p1 = new People(name); //多型，People繼承Player
		p2 = new Computer();  //多型，Computer繼承Player
	}
	public Game(String name , String name2) // 有兩個玩家，代表不會有電腦陪玩
	{
		p1 = new People(name);
		p2 = new People(name2);	
	}
	public void start() //如果過五關且贏的話 獎金x2
	{
		Scanner in = new Scanner(System.in);
		
		
		while(true) //玩好幾輪，直到有一方沒錢
		{
			bet = 0;
			Player winner = null; //贏家
			Player loser = null;  //輸家
			boolean p1_continue = true;
			boolean p2_continue = true;
			System.out.println(p1+" 目前有 "+p1.getMoney()+"元  "+p2+" 目前有 "+p2.getMoney()+"元");
			do {
				if(!(p2 instanceof Computer)) //p2 不是電腦，所以玩家有兩個
					System.out.println("這一輪想賭多少錢呢? 你們兩個自己決定好喔~");
				else	//只有一個玩家和電腦
					System.out.println("這一輪想賭多少錢呢?");
				try {
					bet = in.nextInt(); //賭金
					in.nextLine(); //抓取enter字元
				}catch(Exception e) 
				{
					System.out.println("請輸入 '大於0' 的 '數字' !");
					in.nextLine();
					bet = 0;
				}
			}while(bet <= 0); //如果bet輸入錯誤格式，會catch或是重跑一次迴圈
			
			
			distribute(p1,2); //一開始發兩張牌
			distribute(p2,2);
			while(p1_continue || p2_continue) //一輪中，重複發牌，直到分出勝負or都不想拿牌
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
				
				if(p1_continue) //判斷之前是不是已經不要發了
				{
					p1_continue = p1.isContinue(cards); //給player贏和輸的機率，重新判斷
														//People和Computer的判斷方式不一樣(多型)
					if(p1_continue) //判斷這次要不要發
					{
						distribute(p1,1);
					}
				}
				if(p2_continue)
				{
					p2_continue = p2.isContinue(cards);//給player贏和輸的機率，重新判斷
					if(p2_continue) 
					{
						distribute(p2,1);
					}
				}
				//如果兩邊都不想發牌，皆為false，則跳出迴圈
			}
			if(winner == null && loser == null) //還沒決定好贏輸家，代表有可能是平手 or 都不想再拿牌
			{
				if(bet > 0) //都不想再拿牌，所以還不確定誰贏，而且都沒21點
				{
					if(p1.compareTo(p2) >0) //p1點數大於p2 
					{
						System.out.println("******************");
						System.out.println(p1+" 點數大於 "+p2+" , "+ p1+"贏了");
						System.out.println("******************");
						winner = p1;
						loser = p2;
					}
					else if(p1.compareTo(p2) == 0) //p1點數等於p2
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
					else //p1點數小於p2
					{
						System.out.println("******************");
						System.out.println(p2+" 點數大於 "+p1+" , "+ p2+"贏了");
						System.out.println("******************");
						winner = p2;
						loser = p1;
					}
					
				}
				else // 一起爆掉或是平手 所以bet = 0
				{
					cards.reset();
					p1.clear();
					p2.clear();
					continue;
				}
			}
			if(bet > 0) //贏輸家決定好了，準備給錢
			{
				if(winner.getNumofCards() >= 5) //過五關的話，獎金乘2
				{
					bet *= 2;
					System.out.println("過五關，獎金乘2，為"+bet+" 元");
				}
				System.out.println(winner+" 贏了 "+bet+"元    "+loser+" 輸了 "+bet+"元");
				winner.addMoney(bet); //贏家的錢增加
				loser.reduceMoney(bet); //輸家的錢減少
			}
			cards.reset(); //撲克牌重製，變回52張
			p1.clear(); //清掉player手上的牌
			p2.clear();
			if(loser.getMoney() <=0) //如果輸的人沒錢了
			{
				System.out.println(loser+" 已經沒錢了~  "+winner+" 是**最終贏家**");
				final_winner = winner; //整場遊戲的贏家
				break;
			}
		}
	}
	public void distribute(Player p ,int times)  // 發times次張牌
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
