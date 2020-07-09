/**
 * @date 20190909;
 * @author Axl Cheng;
 * @file ChengAxlAssignment1Part2.java*/

package edu.nyu.cs.kc3793;

public class ChengAxlAssignment1Part2 {
	public static void main(String[] args) {
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
	}
}