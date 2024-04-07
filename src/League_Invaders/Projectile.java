package League_Invaders;
import java.awt.Color;
import java.awt.Graphics;

import League_Invaders.GameObject;

public class Projectile extends GameObject {
	int speed = 10; 
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	
	public void draw (Graphics g) {
	    g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
	}
	
	public void update() {
		y -= speed;
	}
}
