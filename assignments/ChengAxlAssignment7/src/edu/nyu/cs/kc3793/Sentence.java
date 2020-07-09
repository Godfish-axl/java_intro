package edu.nyu.cs.kc3793;

import java.util.ArrayList;

public class Sentence implements SequentiallyOrdered {

	//instances
	public ArrayList<Word> words = new ArrayList<Word>();

	//constructor
	public Sentence(String sentenceInString) {
		String[] words = sentenceInString.split("[^\\w']+");
		for (int i = 0; i<words.length;i++) {
			Word w = new Word(words[i],i);
			this.words.add(w);
		}
		System.out.println("New sentence created: "+sentenceInString);
	}
		
	//methods
	@Override
	public OrderedThing getFirst() {
		// TODO Auto-generated method stub
		return this.words.get(0);
	}

	@Override
	public OrderedThing getLast() {
		// TODO Auto-generated method stub
		int sentenceLength = this.words.size();
		return this.words.get(sentenceLength-1);
	}

	@Override
	public ArrayList<OrderedThing> getSequence() {
		// TODO Auto-generated method stub
		ArrayList<OrderedThing> motherTypeSeq = new ArrayList<OrderedThing>();
		ArrayList<Word> wordTypeSeq = this.words;
		for (int i=0; i<wordTypeSeq.size();i++){
			OrderedThing w = wordTypeSeq.get(i);
			motherTypeSeq.add(w);
		}
		return motherTypeSeq;
	}

	//setter and getter
	public ArrayList<Word> getWords() {
		return words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}

}
