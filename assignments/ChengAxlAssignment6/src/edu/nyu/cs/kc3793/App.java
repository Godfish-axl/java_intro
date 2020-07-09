package edu.nyu.cs.kc3793;
import processing.core.*;
import java.util.ArrayList;

/**
 * The main logic of a Rescue game.
 * @author Axl Cheng
 * @version 0.1
 */
public class App extends PApplet {
	
	// window size
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	
	//make constants for black and white colors
	public final int BLACK = this.color(0,0,0);
	public final int WHITE = this.color(255,255,255);
	
	// components of this game
	private Circle circle;
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Target target;
	private CircleAsExit exit;
	private AuthorImg img;
	
	// default settings() method
	public void settings() {
		this.size(App.getWidth(), App.getHeight());
	}
	
	// default setup() method
	public void setup() {
		// set background color to white
		this.background(this.WHITE);
		// instantiate a circle, a target, and an exit
		this.circle = new Circle();
		this.target = new Target();
		this.exit = new CircleAsExit(this.circle);
		// instantiate 5 obstacles and add them to an ArrayList
		Obstacle initObs = new Obstacle();// use the no-arg constructor to instantiate the first obstacle
		this.obstacles.add(initObs);
		for (int i=1;i<5;i++) {// use the overloaded constructor to instantiate the rest four obstacles based on the position of the previous obstacle instantiated in the ArrayList
			int prevX = obstacles.get(i-1).getX();
			int randGap = obstacles.get(i-1).getRandGap();
			Obstacle newObs = new Obstacle(prevX+randGap);
			this.obstacles.add(newObs);	
		}
	}
	
	// default draw() method
	public void draw() {
		boolean requestFocusInWindow = true;// I tried to request focus in the App window but this line didn't seem to be working as I'd hoped... I'll just leave it here.
		// set background color to white
		this.background(this.WHITE);
		
		// draw the circle
		this.noStroke();
		this.fill(this.circle.getR(),this.circle.getG(),this.circle.getB());
		this.ellipse(this.circle.getX(),this.circle.getY(),this.circle.getRADIUS(),this.circle.getRADIUS());
		
		// determine whether the target is rescued--detect collision between the circle and the target
		if(this.circle.checkCollisionbwCircles(target)) this.circle.rescue(this.target);

		// draw the target if it is not yet rescued
		if (!this.target.isRescued()){
			this.fill(this.target.getR(),this.target.getG(),this.target.getB());
			this.ellipse(this.target.getX(),this.target.getY(),this.target.getRADIUS(),this.target.getRADIUS());
		}
		
		// draw the obstacles
		for (int i =0;i<obstacles.size();i++) {
			Obstacle obs = this.obstacles.get(i);
			this.fill(this.BLACK);
			this.rect(obs.getX(), obs.getY(), Obstacle.getWidth(), Obstacle.getLength());
			obs.move();
		}
		
		// show the exit when the target is rescued
		if (this.target.isRescued()) {
			// draw the exit
			this.stroke(this.BLACK);
			this.fill(this.exit.getR(), this.exit.getG(), this.exit.getB());
			this.ellipse(this.exit.getX(),this.exit.getY(),this.exit.getRADIUS(),this.exit.getRADIUS());
			// also load an image of the author on the upper left corner
			this.img = new AuthorImg(this.exit,this);
			this.img.draw(this);
			
		}
		
		// detect collision between the circle and the obstacles
		if (this.circle.detectCollision(this.obstacles)) this.circle.crash(target);
		
		// check if the circle has made it to the exit with the target rescued
		if (this.target.isRescued() && this.circle.checkCollisionbwCircles(exit)) {			
			
			// restart the game after completion
			this.circle.restart(this.target);
		}
	}
	
	// accept user keyPress to control the movement of the circle
	public void keyPressed() {
		if (this.key == PConstants.CODED) {
			if (this.keyCode==PConstants.LEFT) this.circle.goLeft();
			if (this.keyCode==PConstants.RIGHT) this.circle.goRight();
			if (this.keyCode==PConstants.UP) this.circle.goUp();
			if (this.keyCode==PConstants.DOWN) this.circle.goDown();
		}
	}
	
	// setters and getters
	/**
	 * @return the width
	 */
	public static int getWidth() {
		return App.WIDTH;
	}
	/**
	 * @return the height
	 */
	public static int getHeight() {
		return App.HEIGHT;
	}
	
	/**
	 * Automatically called to start the program.
	 * @param args Command-line arguments (ignored)
	 */
	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.kc3793.App");
	}
}
