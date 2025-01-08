package project;

import java.text.DecimalFormat;
import java.util.Scanner;

public class People extends Player{
	String name; 

	public People(String name)
	{
		super();	
		this.name = name;
	}
	public String toString() 
	{
		return name;
	}
	public boolean isContinue(Cards cards) 
	{
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("##.###%");
		double p1 = super.getLoseRate(cards); 
		double p2 = super.getSaveRate(cards); 

		
		System.out.println("------"+name+" 的回合------");
		System.out.println("你的目前的點數為 "+super.getPoints()+" 點");
		System.out.println("抽下一張牌不爆掉的機率為 : "+df.format(p2));
		System.out.println("抽下一張牌爆掉的機率為 : "+df.format(p1));
		
		System.out.println("是否要繼續發牌? (yes/no)");
		String again = in.nextLine().trim(); 
		while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))
		{
			System.out.println("只能輸入yes或no喔!");
			System.out.println("是否繼續發牌? (yes/no)");
			again = in.nextLine().trim();
		}
		return again.equalsIgnoreCase("yes") ? true : false;
	}
}
