package edu.nyu.cs.kc3793;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChengAxlAssignment3 {
	
	public static int ticsFrequency(String tic, String file) {
		int occurrence = 0;
		int position = file.indexOf(tic,0);
		
		while (position > -1) {
			occurrence++;
			position = file.indexOf(tic, position + 1);
		}
		
		return occurrence;
	} //ticsFrequency method
	
	
	public static int wordCounter(String file) {
		String[] words = file.split("[ ,.?\\s+]"); //split the chunk of text into an array of words by whitespace, comma, period, question mark, and any sequence of whitespace
		return words.length;
	} //wordCounter method
	
	
	//create a method to center the string for neater output (to be used towards the end of the program)
	//*but I cannot figure out how to quickly add padding to both sides of a centered string so I'll leave this as it is
	public static String centerString (String s, int width) {
	    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	} //centerString method
	
	
	
	
	//main
	public static void main (String[] args) throws  FileNotFoundException{
		
		Scanner input = new Scanner(System.in);
		System.out.println("What file would you like to open? ");
		String fileName = input.nextLine();
		//create a new scanner to get input from the file specified by the user
		Scanner text = new Scanner(new File(fileName));
		
		//read the text file and store all its content in a string named file
		String file = "";
		while (text.hasNextLine()) {
			file += text.nextLine();
		}
		file = file.toLowerCase(); //store the whole text which has been converted to lower case in this string

		//use the wordCounter method to get the word count (to later calculate the density of the tics)
		int wordCount = wordCounter(file);
		
		System.out.println("What words would you like to search for? ");
		String words = input.nextLine();
		//split the tics and store them in an array
		String[] tics = words.split(",");
		//trim and convert the tics to lower case
		for (int i = 0; i < tics.length; i++) {
			tics[i] = tics[i].trim();
			tics[i] = tics[i].toLowerCase();
		}		
		
		
		int occurrence = 0;
		int totalOccurrence = 0;
		//initialize an array to store the occurrence of each tic
		int[] occurrenceList = new int[tics.length];
		
		//use the method ticsFrequency(tic, file) to get the occurrence of each tic;
		for (int i = 0; i < tics.length ; i++) {
			occurrence = ticsFrequency(tics[i], file);
			totalOccurrence += occurrence; //keep track of the total number of occurrences
			occurrenceList[i] = occurrence; //populate the array with the occurrence of each tic accordingly
		}
		
		//initiate a new array to store the percentage of each tic
		int[] percentageList = new int[tics.length];
		
		//if there is at least one occurrence of the tics found, calculate and print the outputs as required
		if (totalOccurrence != 0) {
			//calculate and populate the array of percentages
			for (int i = 0; i < tics.length ; i++) {
				percentageList[i] = ((int)occurrenceList[i] * 100/ totalOccurrence) ;
			}
			//calculate the density of all tics in proportion to the total word count of the file text, rounded to two decimal places
			double density = Math.round(((double) totalOccurrence) / wordCount * 100) / 100.0;
			
			//print all the results
			System.out.println(centerString("...Analyzing text...",50));
			System.out.printf("Total number of tics: %d\nDensity of tics: %.2f\n", totalOccurrence, density);
			System.out.println(centerString("...Tic breakdown...",50));
			//print the breakdown data in a formatted fashion, using printf
			for (int i = 0; i < tics.length ; i++) {
				System.out.printf("tic: %-5s | %2d occurences | %2d percents of all tics\n", tics[i], occurrenceList[i], percentageList[i]);
			}	
		}
		//if no tics are found at all, prompt the user to try again
		else {
			System.out.println("Sorry, none of the tics is found in the text. Please try again with different tics or text files.");
		}
		
		//close the scanners	
		input.close();
		text.close();
	} //main
	

		
} //class



