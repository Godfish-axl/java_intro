package edu.nyu.cs.kc3793;
import java.util.Scanner;

public class MopedsOnTheRoad {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Moped userMoped = new Moped();

		
		String[] acceptableCommands = {
				"go left",
				"go right",
				"straight on",
				"back up",
				"how we doin'?",
				"fill 'er up",
				"park",
				"go to Petite Abeille",
				"help"};
		
		
		// print a welcome message
		System.out.println("Welcome aboard! We are now parked outside Dr. Rossinsky DDS's office at 10th St. and 5th Ave, facing South.");
		
		//ask for a move
		Scanner scn = new Scanner(System.in);
		System.out.println("What would you like to do?  At any time, say \"help\" for assistance.");
		
		boolean keepGoing = true;
		while (keepGoing) {
			String command = scn.nextLine();
			int commandIndex=10;
			for (int i=0; i < acceptableCommands.length;i++) {
				if (command.equals(acceptableCommands[i])) {
					commandIndex = i;
				}
			}

			boolean onTheGrid = true;
			if (commandIndex<4) {
				onTheGrid = userMoped.onTheGrid(commandIndex);
				if (onTheGrid) {
					if (commandIndex==0) {
						userMoped.goLeft();
					}
					else if (commandIndex ==1) {
						userMoped.goRight();
					}
					else if (commandIndex ==2) {
						userMoped.straightOn();
					}
					else if (commandIndex ==3) {
						userMoped.backUp();
					}
				}// if (onTheGird)
				
				else{
					System.out.println("Sorry, you are on the brink of the city--please change a direction to stay within Manhattan.");
				}// else--if (!onTheGrid)
				
			}// if (commandIndex<4)
			
			else if(commandIndex>=4 && commandIndex<=8) {
				if (commandIndex==4) {
					double gas = userMoped.getGas();
					double gasLevel = gas/1.0*100;
					System.out.printf("The gas tank is currently %.2f%s full.\n", gasLevel,"%");
				}// "how we doin'?"
				else if (commandIndex==5) {
					userMoped.setGas(1.0);
					double gasLevel = userMoped.getGas()/1.0*100;
					System.out.printf("Done. The gas tank is currently %.2f%s full.\n",gasLevel,"%");
				}// "fill 'er up"
				else if (commandIndex==6) {
					System.out.print("Done--parked. ");
					userMoped.printLocationNOrientation();// print the final location
					keepGoing = false;
				}// "park"
				else if (commandIndex==7) {
					userMoped.goHome();
				}// "go to Petite Abeille" at 17th St. and 6th Ave.
				else if (commandIndex==8) {
					System.out.println("I can understand the following commands: ");
					for(String e:acceptableCommands) {
						System.out.println(e);
					}
				}// "help"
			}// else if (commandIndex\in[4,8])
			
			else if (commandIndex==10) {
				System.out.println("Sorry, I don't think I understand that command. Remember that you can enter \"help\" to find out what commands you can pass on.");
			}// else if command is not supported
			
			if (keepGoing) {
				keepGoing = userMoped.setGas(userMoped.getGas());
				if (!keepGoing) {
					System.out.println("You are out of gas. Until next time--bye.");
				}
			}

		}// while (keepGoing)
			
		
		scn.close();
	}// main()

}
