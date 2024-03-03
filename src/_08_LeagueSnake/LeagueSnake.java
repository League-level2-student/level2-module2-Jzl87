package _08_LeagueSnake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
	static final int WIDTH = 800;
	static final int HEIGHT = 800;
	ArrayList<Segment> tail = new ArrayList<Segment>();
	
	/*
	 * Game variables
	 * 
	 * Put all the game variables here.
	 */
	Random ran = new Random();
	Segment head;
	int foodX;
	int foodY;

	int direction = UP;
	int foodEaten = 0;

	/*
	 * Setup methods
	 * 
	 * These methods are called at the start of the game.
	 */
	@Override
	public void settings() {
		setSize(500, 500);
	}

	@Override
	public void setup() {
		head = new Segment(250, 250);
		frameRate(10);
		dropFood();
		drawSnake();
	}

	void dropFood() {
		// Set the food in a new random location
		
		foodX = ran.nextInt(50) * 10;
		foodY = ran.nextInt(50) * 10;
	}

	/*
	 * Draw Methods
	 * 
	 * These methods are used to draw the snake and its food
	 */

	@Override
	public void draw() {
		background(0, 0, 0);
		drawFood();
		move();
		drawSnake();
		eat();
		manageTail();
	}

	void drawFood() {
		// Draw the food
		fill(255, 0, 0);
		rect(foodX, foodY, 10, 10);

	}

	void drawSnake() {
		// Draw the head of the snake followed by its tail

		fill(0, 255, 0);
		rect(head.x, head.y, 10, 10);
	}

	void drawTail() {
		// Draw each segment of the tail
		for (int z = 0; z < tail.size(); z ++) {
			fill(0,255,0);
			rect(tail.get(z).x, tail.get(z).y ,10,10);
		}
	}

	/*
	 * Tail Management methods
	 * 
	 * These methods make sure the tail is the correct length.
	 */

	void manageTail() {
		// After drawing the tail, add a new segment at the "start" of the tail and
		// remove the one at the "end"
		// This produces the illusion of the snake tail moving.
		checkTailCollision();
		drawTail();
		tail.add(new Segment(head.x, head.y));
		tail.remove(0);

	}

	void checkTailCollision() {
		// If the snake crosses its own tail, shrink the tail back to one segment
		for (int x =0; x<tail.size(); x++) {
			if (tail.get(x).getX() == head.getX() && tail.get(x).getY() == head.getY() && tail.size() > 4) {
			}
		}
	}

	/*
	 * Control methods
	 * 
	 * These methods are used to change what is happening to the snake
	 */

	@Override
	public void keyPressed() {
		// Set the direction of the snake according to the arrow keys pressed
		System.out.println(keyCode);
		if (keyCode == UP && direction != DOWN) {
			direction = UP;
		
		} else if (keyCode == DOWN && direction != UP) {
			direction = DOWN;
		} else if (keyCode == RIGHT && direction != LEFT) {
			direction = RIGHT;
		} else if (keyCode == LEFT && direction != RIGHT) {
			direction = LEFT;
		}
	}

	void move() {
		// Change the location of the Snake head based on the direction it is moving.

		if (direction == UP) {
			// Move head up
			head.y -= 10;
		} else if (direction == DOWN) {
			// Move head down
			head.y += 10;
		} else if (direction == LEFT) {
			head.x -= 10;
		} else if (direction == RIGHT) {
			head.x += 10;
		}
		checkBoundaries();
	}

	void checkBoundaries() {
		// If the snake leaves the frame, make it reappear on the other side
		if (head.x > 500) {
			head.x = 0;
		} else if (head.x < 0) {
			head.x = 500;
		} else if (head.y > 500) {
			head.y = 0;
		} else if (head.y < 0) {
			head.y = 500;
		}
	}

	void eat() {
		// When the snake eats the food, its tail should grow and more
		// food appear
		
		if (head.x == foodX && head.y == foodY) {
			foodEaten ++;
			dropFood();
			tail.add(new Segment(head.x, head.y));
		}

	}

	static public void main(String[] passedArgs) {
		PApplet.main(LeagueSnake.class.getName());
	}
}
