package edu.nyu.cs.kc3793;

public class Character extends OrderedThing {

	private char character;
	
	public Character(char c) {
		this.setCharacter(c);
	}

	/**
	 * @return the character
	 */
	public char getCharacter() {
		return this.character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(char character) {
		this.character = character;
	}


}
