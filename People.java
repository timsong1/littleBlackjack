package project;

import java.text.DecimalFormat;
import java.util.Scanner;

public class People extends Player{
	String name; //名字

	public People(String name)
	{
		super();	
		this.name = name;
	}
	public String toString() //覆蓋
	{
		return name;
	}
	public boolean isContinue(Cards cards) //多型，如果呼叫此方法，會根據此物件是People或Computer來跑到相對應的方法裡
	{
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("##.###%");//進位並轉換成百分比
		double p1 = super.getLoseRate(cards); //爆牌機率
		double p2 = super.getSaveRate(cards); //不爆牌機率

		
		System.out.println("------"+name+" 的回合------");
		System.out.println("你的目前的點數為 "+super.getPoints()+" 點");
		System.out.println("抽下一張牌不爆掉的機率為 : "+df.format(p2));
		System.out.println("抽下一張牌爆掉的機率為 : "+df.format(p1));
		
		System.out.println("是否要繼續發牌? (yes/no)");
		String again = in.nextLine().trim(); //去掉空白字元
		while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))
		{
			System.out.println("只能輸入yes或no喔!");
			System.out.println("是否繼續發牌? (yes/no)");
			again = in.nextLine().trim();
		}
		return again.equalsIgnoreCase("yes") ? true : false;
	}
}
