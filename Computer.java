package project;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Computer extends Player{
	String name; 

	public Computer()
	{
		super();
		name = "Computer";
		
	}
	public String toString()
	{
		return name;
	}
	public boolean isContinue(Cards  cards) // Polymorphism
	{
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("##.###%");
		double p1 = super.getLoseRate(cards);
		double p2 = super.getSaveRate(cards);
		System.out.println("------"+name+"'s turn------");
		System.out.println("Your current point: "+super.getPoints());
		System.out.println("Probability of not bust: "+df.format(p2));
		System.out.println("Probability of bust: "+ df.format(p1));
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
