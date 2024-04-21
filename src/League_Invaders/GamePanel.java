package League_Invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static int MENU = 0;
	final static int GAME = 1;
	final static int END = 2;

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	int currentState = MENU;

	Font titleFont;
	Font scriptFont;

	Timer frameDraw;

	Rocketship rocket = new Rocketship(250, 700, 50, 50);

	ObjectManager manager = new ObjectManager(rocket);

	public void startGame() {
		manager.alienSpawn.start();
	}

	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		scriptFont = new Font("Arial", Font.PLAIN, 20);

		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();

		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else {
			drawEndState(g);
		}

	}

	public void updateMenuState() {
		currentState = MENU;
	}

	public void updateGameState() {
		currentState = GAME;
		startGame();
		manager.update();

		if (!rocket.isActive) {
			currentState++;
		}
	}

	public void updateEndState() {
		currentState = END;
		manager.alienSpawn.stop();
	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 100);

		g.setFont(scriptFont);
		g.drawString("press ENTER to start", 150, 400);
		g.drawString("press SPACE for instructions", 110, 500);
	}

	public void drawGameState(Graphics g) {
		
		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		g.setFont(scriptFont);
		g.setColor(Color.BLUE);
		g.drawString("score: " + manager.getScore(), 10, 20);
		manager.draw(g);
	
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 100, 100);

		g.setFont(scriptFont);
		g.drawString("You killed " + manager.getScore() + " enemies", 150, 400);
		g.drawString("press ENTER to restart", 135, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		// GAME STATE
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				rocket = new Rocketship(250, 700, 50, 50);
				manager = new ObjectManager(rocket);
			} else {
				currentState++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && currentState == 1) {
			manager.addProjectile(rocket.getProjectile());
		}

		// VCERTICAL MOVEMENT
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			if ((rocket.getY() - Rocketship.speed) >= 0) {
				rocket.up = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		
			if ((rocket.getY() + Rocketship.speed) < 730) {
				rocket.down = true;
			}
		}

		// SIDEWASY MOVEMENT
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			if ((rocket.getX() - Rocketship.speed) >= 0) {
				rocket.left = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			

			if ((rocket.getX() + Rocketship.speed) < 440) {
				rocket.right = true;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			rocket.up = false;

		} 
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rocket.down = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			rocket.left = false;

		} 
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocket.right = false;

		}
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

}
