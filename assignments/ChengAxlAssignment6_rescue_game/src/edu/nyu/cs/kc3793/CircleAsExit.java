/**
 * 
 */
package edu.nyu.cs.kc3793;

/**
 * A CircleAsExit class inherited from of the Cricle superclass that represents a circle-shaped exit.
 * @author Axl Cheng
 * @version 0.1
 */
public class CircleAsExit extends Circle {
	
	private final int radius = super.getRADIUS()*2;
	
	public CircleAsExit(Circle circle) {
		super();// calling the no-args Circle constructor from the superclass to generate a random (x,y) coordinate for this circle-exit
		this.setR(this.radius);// overriding the superclass constructor to make this circle-exit twice as large
		super.fill(255,255,255);// using the fill() method inherited from the Circle superclass
	}
	

}
