package League_Invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	final static int MENU = 0;
	final static int GAME = 1;
	final static int END = 2;

	int currentState = MENU;

	Font titleFont;
	Font scriptFont;

	Timer frameDraw;

	Rocketship rocket = new Rocketship(250, 700, 50, 50);

	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		scriptFont = new Font("Arial", Font.PLAIN, 20);

		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
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
	}

	public void updateEndState() {
		currentState = END;
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		rocket.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 100, 100);

		g.setFont(scriptFont);
		g.drawString("You killed" + " enemies", 150, 400);
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
			} else {
				currentState++;
			}
		}

		// VCERTICAL MOVEMENT
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("up");
			if ((rocket.getY() -  Rocketship.speed) >= 0) {
				rocket.up();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("down");
			if ((rocket.getY() + Rocketship.speed) < 730) {
				rocket.down();
			}
		}

		// SIDEWASY MOVEMENT
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("Left");
			if ((rocket.getX() -  Rocketship.speed) >= 0) {
				rocket.left();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("right");

			if ((rocket.getX() +  Rocketship.speed) < 440) {
				rocket.right();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
