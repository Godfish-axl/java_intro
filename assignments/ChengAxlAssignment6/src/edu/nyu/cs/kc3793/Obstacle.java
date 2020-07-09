package edu.nyu.cs.kc3793;

/**
 * The Obstacle class represents an obstacle.
 * @author Axl Cheng
 * @version 0.1
 */
public class Obstacle {
	
	private static int width = 10;
	private static int length = 50;
	private int x,y;
	private int speed;
	public static final int MAX_SPEED = 8;
	public static final int MIN_SPEED = 2;
		
	public static final int MIN_GAP = 100;
	public static final int MAX_GAP = 120;
	
	// constructor
	/***
	 * a basic no-args constructor for Obstacle objects
	 */
	public Obstacle() {
		this.setX(200);
		this.setY(300);
		this.setSpeed(5);
		}
	
	// overloaded constructor
	/***
	 * an overloaded constructor for Obstacle objects that accepts an integer parameter indicating the x coordinate at which the obstacle will appear
	 * @param x the x coordinate of the obstacle to-be-made.
	 */
	public Obstacle(int x) {
		this.setX(x);
		this.setSpeed(this.getRandSpeed());
	}
	
	/***
	 * a method that generates a random speed.
	 * @return int speed, longitudinal
	 */
	public int getRandSpeed() {
		return (int)(Math.random()*(Obstacle.MAX_SPEED-Obstacle.MIN_SPEED+1.0)+Obstacle.MIN_SPEED);
	}
	
	// behaviors
	/***
	 * This method lets each obstacles make vertical oscillations at a randomly generated speed.
	 */
	public void move() {
		this.setSpeed(this.getSpeed());
		if (this.getY()+Obstacle.getLength()>App.getHeight() || this.getY()<0) {
			this.setSpeed(this.getSpeed()*-1);
		}
		this.setY(this.getY()+this.getSpeed());
	}
	
	/***
	 * this method generates a random gap in between each two obstacles
	 * @return int the gap
	 */
	public int getRandGap() {
		return (int)(Math.random()*(Obstacle.MAX_GAP-Obstacle.MIN_GAP+1.0)+Obstacle.MIN_GAP);// generate a random gap b/w 100 and 120
	}

	// setters and getters
	/**
	 * @return the width
	 */
	public static int getWidth() {
		return Obstacle.width;
	}
	/**
	 * @param width the width to set
	 */
	public static void setWidth(int width) {
		Obstacle.width = width;
	}
	/**
	 * @return the length
	 */
	public static int getLength() {
		return Obstacle.length;
	}
	/**
	 * @param length the length to set
	 */
	public static void setLength(int length) {
		Obstacle.length = length;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return this.speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}


}
