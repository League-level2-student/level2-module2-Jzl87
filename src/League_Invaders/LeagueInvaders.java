package League_Invaders;

import javax.swing.JFrame;

public class LeagueInvaders {
	
	public final static int WIDTH = 500;
	public final static int HEIGHT = 800;
	private static JFrame gameFrame;
	private static GamePanel gamePanel;
	
	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}
	
	public LeagueInvaders () {
		gameFrame = new JFrame("League Invaders");
		gamePanel = new GamePanel();
		gameFrame.addKeyListener(gamePanel);
	}
	
	public void setup() {
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.add(gamePanel);
	
	}
}
