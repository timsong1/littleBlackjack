package project;
import java.util.Scanner;

public class Main {   //please change to UTF-8 first
					  //https://jeffyu925.pixnet.net/blog/post/65219650
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("歡迎來玩 21點");
		String again; //判斷是否繼續玩
		do
		{
			int number = 0;
			do
			{
				System.out.println("總共幾個人玩? (1/2)");
				
				try {
					number = in.nextInt(); // 人數
					in.nextLine(); //刪掉enter字元

					if(number < 1 || number > 2)
						System.out.println("只能輸入1或2喔~");
					
				}catch(Exception e) // 如果輸入中文或小數點，會error並catch
				{
					System.out.println("只能輸入1或2喔");
					in.nextLine();
					number = 0;
				}
				
			}while(number < 1 || number > 2); // 如果輸入數字不是1或2，惠要求重新輸入
			
			if(number == 1)
			{
				System.out.println("你接下來會跟電腦玩");
				System.out.println("請輸入你的名字 :");
				String name = in.nextLine().trim();
				
				Game game = new Game(name); // 新增Game物件 存放玩家及賭金
				System.out.println(game);
				game.start(); //遊戲開始
			}
			else if(number == 2)
			{
				System.out.println("請輸入玩家1 名字 :");
				String name = in.nextLine().trim();

				
				System.out.println("請輸入玩家2 名字 :");
				String name2 = in.nextLine().trim();
				
				Game game = new Game(name,name2);  // 新增Game物件 存放玩家及賭金
				System.out.println(game);
				game.start(); //遊戲開始
				
			}
			
			//遊戲結束 
			//之後決定是否繼續玩
			
			System.out.println("是否繼續玩? (yes/no)");
			again = in.nextLine().trim(); //去掉前後空白字元
			while(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"))  // 如果輸入yes或no以外的字，會要求重新輸入
			{
				System.out.println("只能輸入yes或no喔!");
				System.out.println("是否繼續玩? (yes/no)");
				again = in.nextLine().trim();
			}
			
			
		}while(!again.equalsIgnoreCase("no")); // 如果使用者輸入no的話才會整個程式停止
	}
}
