/**
 * @date 20190909;
 * @author Axl Cheng;
 * @file ChengAxlAssignment1Part5.java*/

package edu.nyu.cs.kc3793;
import java.util.Scanner;
public class ChengAxlAssignment1Part5 {
	public static void main(String[] args) {
	/*2.9*/
		Scanner input = new Scanner(System.in);
		System.out.print("Enter v0, v1, and t: ");
		double v0 = input.nextDouble();
		double v1 = input.nextDouble();
		double t = input.nextDouble();
		double a = (v1 - v0) / t;
		System.out.println("The average accleration is " + a + ".");
		input.close();
	}
}