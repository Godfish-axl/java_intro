package edu.nyu.cs.kc3793;
import java.util.Scanner;

public class MopedsOnTheRoadExtraCredit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MopedExtraCredit userMoped = new MopedExtraCredit();
		
		//generate two drunk mopeds
		int ave1 = (int)(Math.random()*10.0+1);
		int ave2 = (int)(Math.random()*10.0+1);
		if (ave1==ave2) {
			ave2 = 11-ave2;
		}// we don't want Rick and Morty to be on the same ave.
		MopedExtraCredit Rick = new MopedExtraCredit((int)(Math.random()*200.0+1),ave1,(int)(Math.random()*4.0));
		MopedExtraCredit Morty = new MopedExtraCredit((int)(Math.random()*200.0+1),ave2,(int)(Math.random()*4.0));
		
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
		// print the initial locations of the two drunk mopeds as well
		System.out.print("The drunk Rick is also on the road. ");
		Rick.printLocationNOrientation();
		System.out.print("So is the drunk Morty--");
		Morty.printLocationNOrientation();
		System.out.println("Be aware not to crush into them!");
		
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
			
			// move the drunk mopeds
			if (Rick.getLocation()[0]<200) {
				System.out.print("The drunk Rick--");
				Rick.goNorth();
			}
			else {
				System.out.print("The drunk Rick--");
				Rick.goSouth();
			}
			if(Morty.getLocation()[0]>0) {
				System.out.print("The drunk Morty--");
				Morty.goSouth();
			}
			else {
				System.out.print("The drunk Morty--");
				Morty.goNorth();
			}
			
			// check if there's a crush
			int userSt = userMoped.getLocation()[0];
			int RickSt = Rick.getLocation()[0];
			int MortySt = Morty.getLocation()[0];
			int userAve = userMoped.getLocation()[1];
			int RickAve = Rick.getLocation()[1];
			int MortyAve = Morty.getLocation()[1];
			if (userSt==RickSt&&userAve==RickAve) {
				System.out.println("Oops--you have just crushed into Rick and your moped is unfortunately broken. It's been a joyful ride--until next time!");
				keepGoing = false;
			}
			else if (userSt==MortySt&&userAve==MortyAve) {
				System.out.println("Oops--you have just crushed into Morty and your moped is unfortunately broken. It's been a joyful ride--until next time!");
				keepGoing = false;
			}
			
			// finally, check if the gas is out
			if (keepGoing) {
				keepGoing = userMoped.setGas(userMoped.getGas());// set keepGoing to false if gas is out
				if (!keepGoing) {
					System.out.println("You are out of gas. Until next time--bye.");
				}
			}
			
		}// while (keepGoing)
			
		
		scn.close();
	}// main()

}
