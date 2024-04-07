package League_Invaders;

public class GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	private int speed = 0;
	public static boolean isActive = true;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX () {
		   return x;	
		}
	public int getY () {
		   return y;	
		}
	public void setSpeed (int x) {
		speed = x;
	}
	public void update () {
		
	}
}