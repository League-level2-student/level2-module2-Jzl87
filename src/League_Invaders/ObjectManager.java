package League_Invaders;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {

	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random ran = new Random();
	Timer alienSpawn;
	
	int score = 0;
	
	public int getScore() {
		return score;
	}
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
		alienSpawn = new Timer(400, this);
	}

	public void checkCollision() {
		for (Alien alien : aliens) {
			if (rocket.boundingBox.intersects(alien.boundingBox)) {
				rocket.setActive(false);
				alien.isActive = false;
			}
			
			for (Projectile bullet : projectiles) {
				if (bullet.boundingBox.intersects(alien.boundingBox)) {
					alien.isActive = false;
					bullet.isActive = false;
					score++;
				}
			}
		}
	}

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public void addAlien() {
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	public void update() {
		rocket.update();
		if (rocket.isActive) {
			for (Alien alien : aliens) {
				alien.update();
				if (alien.getY() > LeagueInvaders.HEIGHT || alien.getX() > LeagueInvaders.WIDTH - 20) {
					alien.setActive(false);
				}
			}
			for (Projectile alien : projectiles) {
				alien.update();
				if (alien.getY() < 0) {
					alien.setActive(false);
				}
			}
			checkCollision();
			purgeObjects();
		}
	}

	public void draw(Graphics g) {
		rocket.draw(g);
		for (Alien alien : aliens) {
			alien.draw(g);
		}
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
		}
	}

	public void purgeObjects() {
		for (int x = 0; x < aliens.size(); x++) {
			if (!aliens.get(x).getActive()) {
				aliens.remove(x);
				x--;
			}
		}

		for (int x = 0; x < projectiles.size(); x++) {
			if (!projectiles.get(x).getActive()) {
				projectiles.remove(x);
				x--;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
		
		
	}

}
