package edu.nyu.cs.kc3793;
import java.util.Scanner;
import java.io.*;


public class ChengAxlAssignment4 {
	public static String[][] parsingCSV() throws FileNotFoundException {
		String fileName = "C:\\NYU\\2019 Fall\\Intro to CS\\assignments\\ChengAxlAssignment4\\src\\Popular_Baby_Names_full.csv";
		// *note about the fileName: I had to put the full path of the file on my computer to make it work;
		// so dear graders, you may need to make some modifications to the fileName to make the project run on your machine;
		// otherwise I'm pretty sure it'll crash at the FileNotFoundException...
		Scanner scn1 = new Scanner(new File(fileName));
		int rowCounter = 0;
		String textBlock = "";
		while (scn1.hasNextLine()) {
			rowCounter ++;
			textBlock += scn1.nextLine()+"\r\n"; //add a line break to each "line" read by the scanner.nextLine() command
		}
		scn1.close();
		String[] textByLine = textBlock.split("\\r?\\n"); //now we can split the block of text by line breaks that we just added back in
		String[][] data = new String[rowCounter][];
		for (int i = 0; i<textByLine.length;i++) {
			data[i] = textByLine[i].split(",");
		}
		
		return data;
	} //parsingCSV
	
	public static void function1(String[][] data) {
		Scanner scn2 = new Scanner(System.in);
		System.out.println("Welcome! What name would you like to look up? (Try \"olivia\" if you want...)");
		String input = scn2.nextLine().toLowerCase();
		System.out.println("Parsing data...... It may take a while.");
		for (String x : data[0]) {
			System.out.printf("|%-30s",x);
		}
		System.out.println(); //print the header
		int counter = 0;
		for (int i = 0; i<data.length ; i++) {
			if (input.equals(data[i][3].toLowerCase())){
				counter ++;
			}
		}
		if (counter == 0) {
			for (int i = 0; i<data[0].length ; i++) {
				System.out.printf("|%-30s","null");
			}
			System.out.println("\nSorry, the name you are looking for seems to be not that popular.");
		}
		else {
			String[][] matches = new String[counter][];
			int j = 0;
			int row = 0;
			while (j < data.length) {
				if (input.equals(data[j][3].toLowerCase())){
					matches[row] = data[j];
					row ++;
				}
				j ++;
			} //String[][] matches now has all the matched entries stored
			
			//paginate and print
			int lineNum = matches.length;
			int start = 0;
			int end = 9;
			boolean keepPrinting = true;
			int i = 0;
			
			while (keepPrinting) {
				while (i <= end) {
					for (String x:matches[i]) {
						System.out.printf("|%-30s",x);
					}
					System.out.println();
					i++;
				}
				if (end+1 != lineNum) {
					System.out.printf("\nShowing %d-%d of %d entries. Hit enter to view the next page, or enter any value to stop.\n", start+1,end+1,lineNum);
				}
				else {
					System.out.printf("\nShowing %d-%d of %d entries. You have reached the last page.\n", start+1,end+1,lineNum);
					break;
				}
				String response = scn2.nextLine();
				if (!response.equals("")) {
					keepPrinting = false;
					break;
				}
				start = start+10;
				if (end+10<=lineNum-1) {
					end = end+10;
				}
				else {
					end = lineNum-1;
				}
			}
			
		}
		
		scn2.close();
	} //function1()
	

	
	public static void function2(String[][] data) {
		System.out.println("Welcome to the name picker function! What is the gender of the baby? Please enter \"female\" or \"male\".");
		Scanner scn4 = new Scanner(System.in);
		String gender = scn4.nextLine().toLowerCase();

		System.out.println("Great! Now please enter the corresponding number to choose one of the following ethnic groups: ");
		String[][] ethnicGroups = {
				{"1", "ASIAN AND PACIFIC ISLANDER"},
				{"2","BLACK NON HISPANIC"},
				{"3","HISPANIC"},
				{"4","WHITE NON HISPANIC"},
		};
		for (String[] x : ethnicGroups) {
			for (String y : x) {
				System.out.print(y+" ");
			}
			System.out.println();
		}
		String numOfGroup = scn4.nextLine();
		String ethnic = "";
		for (int i = 0; i<ethnicGroups.length;i++) {
			if (numOfGroup.equals(ethnicGroups[i][0])) {
				ethnic = ethnicGroups[i][1]; 
			}
		}
		for (String x : data[0]) {
			System.out.printf("|%-30s",x);
		}
		System.out.println(); //print the header
		for (int i = 0; i<data.length ; i++) {
			if (gender.equals(data[i][1].toLowerCase())){
				if (ethnic.equals(data[i][2])) {
					if (Integer.parseInt(data[i][5]) == 1) {
						for (String j: data[i]) {
							System.out.printf("|%-30s",j);
						}
					System.out.println();
					}
				}
			}
		}
		scn4.close();
	} //function2()
	
	public static void function3(String[][] data) {
		System.out.println("Welcome! Please enter the year you want to look up, from 2011 to 2016.");
		Scanner scn5 = new Scanner(System.in);
		int year = scn5.nextInt();
		
		System.out.printf("|%-30s|%-30s|%-30s|%-30s",data[0][0],data[0][1],data[0][3],data[0][4]);
		System.out.println(); //print the header
		
		int counter = 0;
		for (int i = 1; i<data.length ; i++) {
			if (Integer.parseInt(data[i][0]) == year & counter < 10) {
				if ("FEMALE".equals(data[i][1]) & counter<5){
					counter ++;
					System.out.printf("|%-30s|%-30s|%-30s|%-30s\n",data[i][0],data[i][1],data[i][3],data[i][4]);
				}
				else if ("MALE".equals(data[i][1]) & counter < 10){
					counter ++;
					System.out.printf("|%-30s|%-30s|%-30s|%-30s\n",data[i][0],data[i][1],data[i][3],data[i][4]);
				}
			}
		}
		scn5.close();
	}//function3
	
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		String[][] data = parsingCSV();

		System.out.println("Welcome to the All You Need To Know About Popular Baby Names App.\nWell, that is not exactly true--\n");
		System.out.println("As the App uses data from https://data.cityofnewyork.us/Health/Popular-Baby-Names/25th-nujf, it only really tells you about the popular baby names for those born in NYC since 2011 through 2016, by gender and ethnic group.\n");
		System.out.println("Now you can choose what to do--you have several options, so please type in the corresponding number and hit enter:");
		System.out.println("-1 Type in a baby name to find out how popular it is! We will show you the count and rank of the name by year of birth, gender, and ethnic group;");
		System.out.println("-2 Let us help you pick a name for the baby! Simply specify the gender and the ethnic group, and we will show you THE most popular names--ranked 1--of each year from 2011 to 2016;");
		System.out.println("-3 Look up the most popular baby names by year--top five for female and top five for male;");
		Scanner scn3 = new Scanner(System.in);
		String input = scn3.nextLine();
		switch (input) {
			case "1":
				function1(data);
				break;
			case "2":
				function2(data);
				break;
			case "3":
				function3(data);
				break;
		}
		scn3.close();
	}
}