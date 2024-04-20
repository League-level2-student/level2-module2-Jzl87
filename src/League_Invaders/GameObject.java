package League_Invaders;

import java.awt.Rectangle;

public class GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	private int speed = 0;
	public boolean isActive = true;
	Rectangle boundingBox;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundingBox = new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getActive() {
		return isActive;
	}

	public void setSpeed(int x) {
		speed = x;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
	
	public void update() {
		boundingBox.setBounds(x, y, width, height);
	}
}