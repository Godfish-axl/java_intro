package edu.nyu.cs.kc3793;

import java.util.ArrayList;
import java.util.Scanner;

public class TestSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ask for user input to instantiate a Sentence
		Scanner scn = new Scanner(System.in);
		System.out.println("Hello! Please enter any sentence of your choice.");
		String sentence = scn.nextLine();
		scn.close();
		//1. instantiate the sentence
		Sentence s1 = new Sentence(sentence);
		ArrayList<OrderedThing> OGSeq = s1.getSequence();
		//2. call the methods from the Sentence class
		OrderedThing wordf = new Word();
		OrderedThing wordl = new Word();
		//2.1 call the getFirst() method from the Sentence class 
		wordf = s1.getFirst();
		//2.2 call the getLast() method from the Sentence class 
		wordl = s1.getLast();
			//switch the first and the last words in the ArrayList<Word> of Sentence s1
		System.out.println("...switching the first and the last word in the sentence...");
		s1.getWords().set(0, (Word) wordl);
		s1.getWords().set(s1.words.size()-1, (Word) wordf);
		//2.3 call the getSequence() method from the Sentence class to get the new sequence after the switch
		ArrayList<OrderedThing> modifiedSeq = s1.getSequence();// note that the getSequence() method returns an ArrayList of the OrderedThing type, rather than the Word type
		
		//3. call the methods from the Word class
			//instantiate a polymorphic(Word&OrderedThing) object named newWordF to store the new first word in the Sentence s1
		OrderedThing newWordF = new Word();
		newWordF = modifiedSeq.get(0);
		//3.1 call the getFrist() method from the Word class
		Character c1 = (Character) ((SequentiallyOrdered) newWordF).getFirst();
		//3.2 call the getLast() method from the Word class
		Character c2 = (Character)	((SequentiallyOrdered) newWordF).getLast();
		System.out.println("The switch is done! Now the first word in the sentence starts with the character "+c1.getCharacter()+", and ends with the character "+c2.getCharacter()+".");
		System.out.print("There you have it--the word is ");
		//3.3 call the getSequence() method from the Word class
		ArrayList<OrderedThing> words = ((SequentiallyOrdered) newWordF).getSequence();
		for (int i =0; i<words.size();i++) {
				// print the word-by printing each element (that is both a Character and an OrderedThing) in the words ArrayList
				char c =  ((Character) words.get(i)).getCharacter();
				System.out.print(c);
		}
		//3.4 call the getPosInSentence() method from the Word class
		((Word) newWordF).setPosInSentence(modifiedSeq.indexOf(newWordF));
		int OGPos = OGSeq.indexOf(newWordF);
		int newPos = ((Word) newWordF).getPosInSentence();
		System.out.printf(", which was at position %d in the original sentnece, and is now at position %d.",OGPos,newPos);
	}

}
