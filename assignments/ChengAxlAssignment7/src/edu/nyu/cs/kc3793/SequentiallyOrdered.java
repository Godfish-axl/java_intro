/**
 * 
 */
package edu.nyu.cs.kc3793;

import java.util.ArrayList;

public interface SequentiallyOrdered {

	public abstract OrderedThing getFirst();
	public abstract OrderedThing getLast();
	public abstract ArrayList<OrderedThing> getSequence();

}