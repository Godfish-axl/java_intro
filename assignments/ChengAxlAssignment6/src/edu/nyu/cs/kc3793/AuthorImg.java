/**
 * 
 */
package edu.nyu.cs.kc3793;

import processing.core.*;

/**
 * @author Axl Cheng
 *
 */
public class AuthorImg{

	/**
	 * 
	 */
	private final static String AUTHOR_IMAGE_PATH  = "src/albert.png";
	private PImage img;
	
	private int x,y;
	
	public AuthorImg(CircleAsExit exit,App app) {
		// TODO Auto-generated constructor stub
		this.setX(0);
		this.setY(0);
		
		// load the image
		this.img = app.loadImage(AUTHOR_IMAGE_PATH);
	}
	
	public void draw(App app) {
		app.image(this.img, this.getX(), this.getY());
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
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
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
