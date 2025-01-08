package project;
import java.util.Scanner;

public class Main {   //please change to UTF-8 first
					  //https://jeffyu925.pixnet.net/blog/post/65219650
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("歡迎來玩 21點");
		String again; 
		do
		{
			int number = 0;
			do
			{
				System.out.println("總共幾個人玩? (1/2)");
				
				try {
					number = in.nextInt(); 
					in.nextLine(); 

					if(number < 1 || number > 2)
						System.out.println("只能輸入1或2喔~");
					
				}catch(Exception e) 
				{
					System.out.println("只能輸入1或2喔");
					in.nextLine();
					number = 0;
				}
				
			}while(number < 1 || number > 2); 
			
			if(number == 1)
			{
				System.out.println("你接下來會跟電腦玩");
				System.out.println("請輸入你的名字 :");
				String name = in.nextLine().trim();
				
				Game game = new Game(name); 
				System.out.println(game);
				game.start(); 
			}
			else if(number == 2)
			{
				System.out.println("請輸入玩家1 名字 :");
				String name = in.nextLine().trim();

				
				System.out.println("請輸入玩家2 名字 :");
				String name2 = in.nextLine().trim();
				
				Game game = new Game(name,name2);  
				System.out.println(game);
				game.start(); 
				
			}
			
			
			System.out.println("是否繼續玩? (yes/no)");
			again = in.nextLine().trim(); 
			while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))  
			{
				System.out.println("只能輸入yes或no喔!");
				System.out.println("是否繼續玩? (yes/no)");
				again = in.nextLine().trim();
			}
			
			
		}while(!again.equalsIgnoreCase("no")); 
	}
}
