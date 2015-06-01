package data;

public class Player {
	
	public static int STARTING_MONEY = 1500;
	public static int MONEY_PER_TURN = 200;
	public int id;
	public int money;
	
	public Player(int id, int m)
	{
		this.id = id;
		this.money = m;
		
	}

}
