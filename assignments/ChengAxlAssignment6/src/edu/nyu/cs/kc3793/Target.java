package edu.nyu.cs.kc3793;

/**
 * A Target class inherited from of the Cricle superclass that represents a circle-shaped target, waiting to be rescued by the Circle controlled by the user.
 * @author Axl Cheng
 * @version 0.1
 */
public class Target extends Circle{
		
	private final static int RADIUS = 10;
	private boolean rescued = false;
	
	/***
	 * Constructor for a target
	 */
	public Target() {
		super.fill((int)(Math.random()*(255.0)+1), (int)(Math.random()*(255.0)+1), (int)(Math.random()*(255.0)+1));
		this.setX((int)(Math.random()*(App.getWidth()/8.0)+App.getWidth()/8*7-this.getRADIUS()*2));
		this.setY((int)(Math.random()*(App.getHeight()-this.getRADIUS()*2)*1.0)+this.getRADIUS()*2);
	}
	
	// setters and getters

	/**
	 * @return the RADIUS
	 */
	public int getRADIUS() {
		return Target.RADIUS;
	}

	/**
	 * @param rescued the rescued to set
	 */
	public void setRescued(boolean rescued) {
		this.rescued = rescued;
	}
	/***
	 * @return the boolean value true == Rescued
	 */
	public boolean isRescued() {
		return this.rescued;
	}
}
