package units;

public class Plan {
	
	public static final int MOVE = 0;
	public static final int ATTACK = 1;
	public int typeOfPlan;
	public int x;
	public int y;
	
	public float perciseX;
	public float perciseY;
	public float xSpeed;
	public float ySpeed;
	
	public Plan(int i, int x, int y)
	{
		this.typeOfPlan = i;
		this.x = x;
		this.y = y;
	}
	
	public void calculateMovement(int unitX, int unitY)
	{
		xSpeed = ((float)x - (float)unitX) / 30.0f;
		ySpeed = ((float)y - (float)unitY) / 30.0f;
		perciseX = unitX;
		perciseY = unitY;
	}
	
	public void updatePrecisePosition()
	{
		perciseX += xSpeed;
		perciseY += ySpeed;
	}

}
