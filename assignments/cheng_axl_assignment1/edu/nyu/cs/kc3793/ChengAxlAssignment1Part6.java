/**
 * @date 20190909;
 * @author Axl Cheng;
 * @file ChengAxlAssignment1Part6.java*/

package edu.nyu.cs.kc3793;
import java.util.Scanner;
public class ChengAxlAssignment1Part6 {
	public static void main(String[] args) {
	/*2.13*/
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the monthly saving amount: ");
		double monthlySaving = input.nextDouble();
		double accountBalance = monthlySaving * (1 + 0.00417);
		
		int counter = 2; //I set the counter at 2 to start with because inside the while loop, the first time of computing 
						//will essentially yield the acocunt balance for the second month.
		while (counter < 7) {
			counter = counter + 1;
			accountBalance = (100 + accountBalance) * (1 + 0.00417);
		}

		System.out.println("After the sixth month, the account value is $" + accountBalance + ".");
		
		// close the scanner
		input.close();
	}
}