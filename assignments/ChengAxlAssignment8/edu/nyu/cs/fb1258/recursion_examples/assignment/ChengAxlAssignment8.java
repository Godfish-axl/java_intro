package edu.nyu.cs.fb1258.recursion_examples.assignment;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This program draws a fractal tree using recursion.
 * @author Foo Barstein, with comments by Axl Cheng (kc3793)
 *
 */
public class FractalTree extends JPanel {
	/** 
	 * an instance from the java.awt.Graphics2D class; works as the canvas for the fractal tree that will be drawn
	 */
    public Graphics2D g1;
    /**
     * the max angle at which a branch can be drawn, which is the full angle
     */
    public static final int maxAngle = 360;
    /**
     * the starting x and y coordinates at which the tree will be drawn
     */
    public static final int startX = 600;
    public static final int startY = 800;
    /**
	 * the levels of recursion; different from the number of times a loop is iterated
	 */
    public static final int numOfRecursions = 9;
    /**
     * the initial angle at which the first branch of the tree will be drawn 
     */
    public static final int startAngle = 0;
    /**
     * a double that limits the length of the lines/branches
     */
    public static final double treeSize = 2;
    /**
     * a threshold value indicating (probably) the level of desired minuteness (meaning that once the decrementing numOfRecursions equates this value, the recusion will stop.)
     */
    public static final int Detail = 3;
    
    /**
     * int randFact and int[] constFact are both contributive to determining the angle of a branch
     */
    public static final int randFact = 30;
    public static final int[] constFact = {-60, 05, -50, 45};
     
    /**
     * three int arrays that store some values to be randomly picked from to an RGB color of the stroke
     */
    public static int[] red =   {0, 0, 0, 0, 7, 15, 23, 31, 39, 47, 55, 43};
    public static int[] green = {171, 159, 147, 135, 123, 111, 99, 87, 75, 63, 51, 43};
    public static int[] blue =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};   
     
    /**
     * a method that does the conversion from degree to radian
     * @param deg degree as an integer
     * @return a double of the value of the radian
     */
    public static double degToRad(int deg) {
        return deg * Math.PI / 180;
    }
     
    /**
     * the method draws an initial line when first being called, and calculates the midpoint
     * @param g1 the canvas (an instance of the Graphics2D class)
     * @param x the x coordinate of the starting point of an initial line
     * @param y the y coordinate of the starting point of an initial line
     * @param n a counter to break the recursion once the prescribed levels of recursion are exhausted
     * @param angle the angle at which a fractal/branch will be drawn
     */
    public static void drawFractal(Graphics2D g1, int x, int y, int n, int angle) {
        if (n == Detail) return;
        // an integer len that is 2 (treeSize) to the power of n-1
        int len = (int) Math.round(Math.pow(treeSize, n - 1));
         
        // xn1 and yn1 are the coordinates of the end point of a branch based on the desired angle
        // note that the previously mentioned variable treeSize contributes to determining the endpoint, and thus the length, of the branches
        int xn1 = (int) Math.round(x - (2 * len * Math.sin(degToRad(angle))));
        int yn1 = (int) Math.round(y - (2 * len * Math.cos(degToRad(angle))));
        
        // calculates 4 split points (.25,.375,.5,.75--if ) along the initial line, at which 4 new lines/branches will be drawn
        int mid1x = (x + xn1) / 2;
        int mid1y = (y + yn1) / 2;
        int mid2x = (mid1x + xn1) / 2;
        int mid2y = (mid1y + yn1) / 2;
        int mid3x = (x + mid1x) / 2;
        int mid3y = (y + mid1y) / 2;
        int mid4x = (mid3x + mid1x) / 2;
        int mid4y = (mid3y + mid1y) / 2;
        //determine the color of the stroke
        java.util.Random r = new java.util.Random();
        Color c = new Color(red[(r.nextInt() % 3) + n], green[(r.nextInt() % 3) + n], blue[(r.nextInt() % 3) + n]);
        g1.setColor(c);
        
        //draw the initial line
        Line2D L1 = new Line2D.Double(x, y, xn1, yn1);
        g1.draw(L1);

        //recursion!
        // So every time one branch grows, four more sub-branches will grow from the previous branch,
        // until the decrementing n (counter) equates the final int Detail, and that's when the recursion stops.
        // Also the angle at which each branch will grow is determined by the interworking of 1) an instance r of the java.util.Random class as a rand int generator, 
        // 2) the int randFact as a bound for the random int generator, 3) the int[] constFact to make sure that each time the four branches grow from a previous branch, 
        // the angles of the four branches only wiggle a little but fall around roughly the same value of {-60, 05, -50, 45}
        // and finally, 4) maxAngle to make sure that no angle value exceeds 360, in which case angle=angle-360. 
        drawFractal(g1, mid1x, mid1y, n - 1, (angle + r.nextInt(randFact) + constFact[0]) % maxAngle);
        drawFractal(g1, mid2x, mid2y, n - 1, (angle + r.nextInt(randFact) + constFact[1]) % maxAngle);
        drawFractal(g1, mid3x, mid3y, n - 1, (angle + r.nextInt(randFact) + constFact[2]) % maxAngle);
        drawFractal(g1, mid4x, mid4y, n - 1, (angle + r.nextInt(randFact) + constFact[3]) % maxAngle);

    }
     
    /**
     * @override paint() in JPanel or JComponent
     */
    public void paint(final Graphics g) {
    	//instantiate a g1 Graphics2D object as the canvas
        g1 = (Graphics2D) g;
        //initial call of the recursive drawFractal() method
        //arguments that are passed in this call are respectively 1) g1 that is the Graphics2D "canvas",
        //2) startX and startY that are the predetermined (final ints) coordinates of a starting point,
        //3) numOfRecursions-an int that determines the desired levels of recursions, and
        //4) startAngle that determines the angle of the first line-or branch, or trunk.
        drawFractal(g1, startX, startY, numOfRecursions, startAngle);
    }

    /**
     * the main method of drawing a fractal tree, containing 
     * @param args
     */
    public static void main(String args[]) {
    	//instantiate a JFrame object (which is a Frame, or a window) with the title "Drawing a recursive tree"
        JFrame FF = new JFrame("Drawing a recursive tree");
        FF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //instantiate a object from the FractalTree class, upon which the paint() method will be called by default, and the recursion will be activated
        FractalTree F = new FractalTree();
        FF.setBackground(Color.BLACK);
        //add the newly instantiated/drawn FractalTree F to the JFrame FF, or as I understand it, the "canvas"
        FF.add(F);
        //set the window size the fit the tree
        FF.pack();
        //display the tree
        FF.setVisible(true);
        FF.setSize(1200, 1000);
    }
}