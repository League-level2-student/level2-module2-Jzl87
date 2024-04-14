package League_Invaders;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile> ();
	ArrayList<Alien> aliens = new ArrayList <Alien> ();
	Random ran = new Random();
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);	
	}
	
	public void addAlien() {
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	public void update() {
		for (Alien alien : aliens) {
			alien.update();
			if (alien.getX() > LeagueInvaders.WIDTH || alien.getX() < 0 || alien.getY() <0 || alien.getY() > LeagueInvaders.HEIGHT) {
				alien.setActive(false);
			}
		}
		for (Projectile alien : projectiles) {
			alien.update();
			if (alien.getY() <0 || alien.getY() > LeagueInvaders.HEIGHT) {
				alien.setActive(false);
			}
		}
	}
	
	public void draw (Graphics g) {
		rocket.draw(g);
		
		for (Alien alien : aliens) {
			alien.draw(g);
		}
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
		}
	}
	
	public void purgeObjects () {
		for (int x = 0; x < aliens.size(); x++) {
			if (aliens.get(x).getActive()) {
				aliens.remove(x);
			}
		}
		
		for (int x = 0; x < projectiles.size(); x++) {
			if (projectiles.get(x).getActive()) {
				projectiles.remove(x);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
		FIX THIS AIZUGBDSAIUVD GamePanel.resetSpawn();
		
	}
	
	
}
       