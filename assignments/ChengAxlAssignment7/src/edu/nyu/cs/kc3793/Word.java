package edu.nyu.cs.kc3793;

import java.util.ArrayList;

public class Word extends OrderedThing implements SequentiallyOrdered {
	
	//instances
	private ArrayList<Character> characters = new ArrayList<Character>();
	private int posInSentence;
	
	//constructors
	public Word(String wordInString, int posInSentence) {
		this.setPosInSentence(posInSentence);
		for (int i =0; i<wordInString.length();i++) {
			Character c = new Character(wordInString.charAt(i));
			this.characters.add(c);
		}
	}
	public Word() {}
	
	//methods
	@Override
	public OrderedThing getFirst() {
		// TODO Auto-generated method stub
		return this.getCharacters().get(0);
	}

	@Override
	public OrderedThing getLast() {
		// TODO Auto-generated method stub
		int wordLength = this.getCharacters().size();
		return this.getCharacters().get(wordLength-1);
	}

	@Override
	public ArrayList<OrderedThing> getSequence() {
		// TODO Auto-generated method stub
		ArrayList<OrderedThing> motherTypeSeq = new ArrayList<OrderedThing>();
		ArrayList<Character> charTypeSeq = this.getCharacters();
		for (int i=0; i<charTypeSeq.size();i++){
			OrderedThing c = charTypeSeq.get(i);
			motherTypeSeq.add(c);
		}
		return motherTypeSeq;
	}

	//setters and getters
	/**
	 * @return the characters
	 */
	public ArrayList<Character> getCharacters() {
		return this.characters;
	}

	/**
	 * @param characters the characters to set
	 */
	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}

	/**s
	 * @return the posInSentence
	 */
	public int getPosInSentence() {
		return this.posInSentence;
	}

	/**
	 * @param posInSentence the posInSentence to set
	 */
	public void setPosInSentence(int posInSentence) {
		this.posInSentence = posInSentence;
	}
}
