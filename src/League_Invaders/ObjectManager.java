package League_Invaders;

import java.util.ArrayList;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile> ();
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
	}
}
       