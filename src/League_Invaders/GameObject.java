package League_Invaders;

public class GameObject {

	private int x;
	private int y;
	private int width;
	private int height;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int n) {
		x = n;
	}

	public void setY(int n) {
		y = n;
	}

	public void setWidth(int n) {
		width = n;
	}

	public void setHeight(int n) {
		height = n;
	}
}
