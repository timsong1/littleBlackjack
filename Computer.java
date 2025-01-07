package project;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Computer extends Player{
	String name; //名字

	public Computer()
	{
		super();
		name = "Computer";
		
	}
	public String toString()
	{
		return name;
	}
	public boolean isContinue(Cards  cards)//多型，如果呼叫此方法，會根據此物件是People或Computer來跑到相對應的方法裡
	{
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("##.###%");//進位並轉換成百分比
		double p1 = super.getLoseRate(cards);
		double p2 = super.getSaveRate(cards);
		System.out.println("------"+name+" 的回合------");
		System.out.println("你的目前的點數為 "+super.getPoints()+" 點");
		System.out.println("抽下一張牌不爆掉的機率為 : "+df.format(p2));
		System.out.println("抽下一張牌爆掉的機率為 : "+ df.format(p1));
		if(p2 >= p1)
		{
			System.out.println("由於不爆牌的機率 >= 爆牌的機率 ， 所以 "+name+" 決定抽牌");
			return true;
		}
		else
		{
			System.out.println("由於不爆牌的機率 <= 爆牌的機率 ， 所以 "+name+" 決定抽牌");
			return false;
		}
	}
}
