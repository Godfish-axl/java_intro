package edu.nyu.cs.kc3793;
import java.util.ArrayList;

/**
 * The Circle class represents a circle controlled by the user.
 * @author Axl Cheng
 * @version 0.1
 */
public class Circle {
		
	
	private int RADIUS = 20;
	private int x,y;
	public static final int SPEED = 9;
	private int r=100;
	private int g=100;
	private int b=100;
	private boolean crashed = false;
		
	/***
	 * Constructor for a circle
	 */
	public Circle() {
		this.setX((int)(Math.random()*(App.getWidth()/6.0-this.getRADIUS()*2)+this.getRADIUS()));
		this.setY((int)(Math.random()*(App.getHeight()*1.0-this.getRADIUS()*2)));
	}
	
	// behaviors
	/***
	 * fill the circle with RGB
	 * @param r int red
	 * @param g int green
	 * @param b int blue
	 */
	public void fill(int r, int g, int b) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
	}
	/***
	 * rescue the target
	 * @param target the target to be rescued
	 */
	public void rescue(Target target) {
		// set the fill color of this circle to that of the target
		this.fill(target.getR(), target.getG(), target.getB());
		target.setRescued(true);
	}
	/***
	 * set the x position of the circle such that it goes left
	 */
	public void goLeft() {
		if (this.getX()>=this.getRADIUS()) this.setX(this.getX()-Circle.SPEED);
	}
	/***
	 * set the x position of the circle such that it goes right
	 */
	public void goRight() {
		if (this.getX()+this.getRADIUS()<=App.getWidth()) this.setX(this.getX()+Circle.SPEED);
	}
	/***
	 * set the y position of the circle such that it goes up
	 */
	public void goUp() {
		if (this.getY()>=this.getRADIUS()) this.setY(this.getY()-Circle.SPEED);
	}
	/***
	 * set the y position of the circle such that it goes down
	 */
	public void goDown() {
		if (this.getY()+this.getRADIUS()<=App.getHeight()) this.setY(this.getY()+Circle.SPEED);
	}
	
	/***
	 * detect if there is a collision between this circle and a bunch of obstacles in an ArrayList
	 * @param obstacles the ArrayList that stores obstacles
	 * @return a boolean value indicating whether there is a collision between the circle and the obstacles
	 */
	public boolean detectCollision(ArrayList<Obstacle> obstacles) {
		// loop through the ArrayList to detect collision for each obstacles
		for (int i =0; i<obstacles.size();i++) {
			int circXL = this.getX();
			int rectXL = obstacles.get(i).getX();
			int rectXR = rectXL + Obstacle.getWidth();
			int circYU = this.getY();
			int circYL = circYU + this.getRADIUS();
			int rectYU = obstacles.get(i).getY();
			int rectYL = rectYU + Obstacle.getLength();
			
			// temporary variables to set edges for testing
			float testX = circXL;
			float testY = circYL;

			// which edge is closest?
			if (circXL < rectXL)         testX = rectXL;      // compare left edge
			else if (circXL > rectXR) testX = rectXR;   // right edge
			if (circYU < rectYU)         testY = rectYU;      // top edge
			else if (circYU > rectYL) testY = rectYL;   // bottom edge

			// get distance from closest edges
			float distX = circXL-testX;
			float distY = circYU-testY;
			double distance = Math.sqrt((distX*distX) + (distY*distY));

			// if the distance is less than the radius, then there is a collision
			if (distance <= this.getRADIUS()) {
				this.crashed = true;
				}
		}
	return this.crashed;
	}
	
	/**
	 * a method that resets the coordinates of the circle and calls within it a restart method that resets the properties of the target
	 * @param target a target object passed onto the restart method called within this crash method, where the properties of the target will be reset
	 */
	public void crash(Target target) {
		// reset the coordinates of the circle
		this.setX((int)(Math.random()*(App.getWidth()/6.0)+this.getRADIUS()));
		this.setY((int)(Math.random()*App.getHeight()*1.0));
		// restart the game
		this.restart(target);
	}
	
	/**
	 * a method that checks for collision between two circles
	 * @param object an object belong to the Circle superclass, which means that it can be a circle, a target, or a circleAsExit
	 * @return a boolean value indicating whether the two circles have collided
	 */
	public boolean checkCollisionbwCircles(Circle object) {
		int circCenterX = this.getX()+this.getRADIUS();
		int circCenterY = this.getY()+this.getRADIUS();
		int objectCenterX = object.getX()+object.getRADIUS();
		int objectCenterY = object.getY()+object.getRADIUS();
		float distX = circCenterX - objectCenterX;
		float distY = circCenterY - objectCenterY;
		double distance = Math.sqrt((distX*distX)+(distY*distY));
		if (distance<this.getRADIUS()+object.getRADIUS()) {
			return true;
		}
		return false;
	}

	/**
	 * a method that resets some properties of the target and this circle to restart the game
	 * @param target a target object whose properties including fillColor and position will be reset
	 */
	public void restart(Target target) {
		this.crashed = false;
		target.setRescued(false);
		target.fill((int)(Math.random()*(255.0)+1), (int)(Math.random()*(255.0)+1), (int)(Math.random()*(255.0)+1));
		target.setX((int)(Math.random()*(App.getWidth()/8.0)+App.getWidth()/8*7-target.getRADIUS()*2));
		target.setY((int)(Math.random()*(App.getHeight()-target.getRADIUS()*2)*1.0)+target.getRADIUS()*2);
		this.fill(100,100,100);
	}
	
	// setters and getters
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
	 * @return the r
	 */
	public int getR() {
		return this.r;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * @return the b
	 */
	public int getB() {
		return this.b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(int b) {
		this.b = b;
	}

	/**
	 * @return the g
	 */
	public int getG() {
		return this.g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(int g) {
		this.g = g;
	}

	/**
	 * @return the RADIUS
	 */
	public int getRADIUS() {
		return this.RADIUS;
	}

	/**
	 * @param rADIUS the rADIUS to set
	 */
	public void setRADIUS(int rADIUS) {
		this.RADIUS = rADIUS;
	}
}
