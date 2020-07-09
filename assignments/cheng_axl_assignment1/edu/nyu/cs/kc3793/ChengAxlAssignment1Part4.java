/**
 * @date 20190909;
 * @author Axl Cheng;
 * @file ChengAxlAssignment1Part4.java*/

package edu.nyu.cs.kc3793;
import java.util.Scanner;
public class ChengAxlAssignment1Part4 {
	public static void main(String[] args) {
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
		input.close();
	}
}