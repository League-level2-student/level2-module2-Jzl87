package League_Invaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	static int speed = 10;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		if (needImage) {
			loadImage("rocket.png");
		}

	}

	public void draw(Graphics g) {

		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	public Projectile getProjectile() {
		return new Projectile(x + width / 2, y, 10, 10);
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	public void update() {
		super.update();

		if (up && y - speed > 0) {
			y -= speed;
		} else if (down && y + 35 < LeagueInvaders.HEIGHT-height) {
			y += speed;
		}
		if (right && x +20 < LeagueInvaders.WIDTH-width) {
			x += speed;
		} else if (left && x - speed > 0) {
			x -= speed;
		}
	}

}
