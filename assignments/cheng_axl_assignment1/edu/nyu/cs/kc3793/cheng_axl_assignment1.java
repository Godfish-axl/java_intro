/**
 * @date 20190909;
 * @author Axl Cheng;
 * @file cheng_axl_assignment1-1.7.java*/

package edu.nyu.cs.kc3793;

import java.util.Scanner;
public class cheng_axl_assignment1 {
	/*1.7*/
	public static void main(String[] args) {
		System.out.print("4 * (1.0 - 1.0/3 + 1.0/5 - 1.0/7 + 1.0/9 - 1.0/11) = ");
		System.out.println(4 * (1.0 - 1.0/3 + 1.0/5 - 1.0/7 + 1.0/9 - 1.0/11));
		System.out.print("4 * (1.0 - 1.0/3 + 1.0/5 - 1.0/7 + 1.0/9 - 1.0/11 + 1.0/13) = ");
		System.out.println(4 * (1.0 - 1.0/3 + 1.0/5 - 1.0/7 + 1.0/9 - 1.0/11 + 1.0/13));
	

	/*1.10*/
		double averagespeed;
		averagespeed = (14 / 1.6) / (45.5 / 60);
		System.out.println("Assume a runner runs 14 kilometers in 45 minutes and 30 seconds, the average speed is " + averagespeed + " miles per hour.");
	

	/*1.11*/
		double birthPerYear;
		double deathPerYear;
		double immigrantPerYear;
		double currentPopulation = 312032486;
		birthPerYear = 365 * 24 * 60 * 60 / 7.0;
		deathPerYear = 365 * 24 * 60 * 60 / 13.0;
		immigrantPerYear = 365 * 24 * 60 * 60 / 45.0;
		double yearlyIncrease = birthPerYear - deathPerYear + immigrantPerYear;
		double firstYearPop = currentPopulation + yearlyIncrease;
		double secondYearPop = firstYearPop + yearlyIncrease;
		double thirdYearPop = secondYearPop + yearlyIncrease;
		double fourthYearPop = thirdYearPop + yearlyIncrease;
		double fifthYearPop = fourthYearPop + yearlyIncrease;
		System.out.println ("The estimated populations for the following 5 years are: " + firstYearPop + ", " + secondYearPop + ", " + thirdYearPop + ", " + fourthYearPop + ", " + fifthYearPop + ".");



	/*2.5*/
		// create a scanner object named 'input'
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the subtotal and a gratuity rate: ");
		double subtotal = input.nextDouble();
		double gratuityRate = input.nextDouble();

		//compute
		double gratuity = subtotal * gratuityRate * 0.01;
		double total = subtotal + gratuity;

		//print
		System.out.println("The gratuity is $" + gratuity + " and total is $" + total + ".");
	

	/*2.9*/
		System.out.print("Enter v0, v1, and t: ");
		double v0 = input.nextDouble();
		double v1 = input.nextDouble();
		double t = input.nextDouble();
		double a = (v1 - v0) / t;
		System.out.println("The average accleration is " + a + ".");
	


	/*2.13*/
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