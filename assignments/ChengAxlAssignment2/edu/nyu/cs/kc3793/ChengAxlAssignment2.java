package edu.nyu.cs.kc3793;

import java.util.Scanner;

public class ChengAxlAssignment2{
	public static void main(String[] args){

		//generate two cards each for the user and the dealer
		int cardUser1 = (int)(Math.random()*10+2);
		int cardUser2 = (int)(Math.random()*10+2);
		int cardDealer1 = (int)(Math.random()*10+2);
		int cardDealer2 = (int)(Math.random()*10+2);
		
		//print a welcome message
		System.out.println("Welcome to the Blackjack!");
		
		//create a scanner object
		Scanner input = new Scanner(System.in);
		String hitOrStand = input.nextLine();
		
		//set up a while loop to stop the program when either of the player is busted
		int pointsUser = cardUser1 + cardUser2;
		int pointsDealer = cardDealer1 + cardDealer2;
		String userCards = cardUser1 + ", " + cardUser2;
		String dealerCards = cardDealer1 + ", " + cardDealer2;
		
		boolean gameContinues = (pointsUser <= 21 & pointsDealer <= 21);
		//set a flag to indicate whether the user has finished their game (meaning no longer requesting cards)
		boolean userGameFinished = false;
		
		while (gameContinues) { 
			
			//show the user their cards and ask for their next move: hit/stand
			System.out.println("Your cards are: " + userCards + ". Would you like to hit or stand?");
			
			//get user's response
			hitOrStand = input.nextLine();
			hitOrStand = hitOrStand.toLowerCase();
	
			//determine whether the user wants more cards
			switch (hitOrStand) {
				case "hit":
					int cardUserNew = (int)(Math.random()*10+2);
					pointsUser += cardUserNew;
					userCards += ", " + cardUserNew;
					gameContinues = pointsUser <= 21;
					if (gameContinues) {
						System.out.println("Your new card is " + cardUserNew +".");
					}
					else {
						break;
					}
					
				case "stop":
				case "stand":
				case "pass":
					userGameFinished = true;
					break;
			} //end of switching cases
			
			//determine for the robotic dealer whether it would want more cards depending on the cards it holds
			while (pointsDealer <= 15 & userGameFinished) { //let's say the dealer will request additional cards until thier total reaches 15
				int cardDealerNew = (int)(Math.random()*10+2);
				pointsDealer += cardDealerNew;
				dealerCards += ", " + cardDealerNew;
			}
			gameContinues = false;
			
		} //end of the major while loop
		
		//calculate and print the result
		boolean win = (21 >= pointsUser & (pointsUser > pointsDealer) || (pointsDealer > 21));
		if (win) {
			System.out.println("Congratulations! You won the game.");
		}
		else if (pointsUser == pointsDealer & pointsUser <= 21){
			System.out.println("You both won! This was a tie.");
		}
		else if (pointsUser == pointsDealer & pointsUser > 21) {
			System.out.println("This was a tie... but you both lost.");
		}
		else {
			System.out.println("You lost.");
		}
		//show both players' cards regardless of the result
		System.out.println("Your cards are: " + userCards + ", and your total is " + pointsUser + ". The dealer's cards are: " + dealerCards + ", and their total is " + pointsDealer + ".");
		
		//close the scanner
		input.close();
	}
}