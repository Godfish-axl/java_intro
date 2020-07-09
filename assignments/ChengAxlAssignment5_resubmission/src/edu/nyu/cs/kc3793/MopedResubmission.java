package edu.nyu.cs.kc3793;

public class MopedResubmission {
	//properties of mopeds
		private int[] location = {10,5};// location in the {st #, ave #} format
		private static String[] Orientations = {"South","East","North","West"};// "goLeft"==index++/(0>1>2>3); "goRight"==(0>3>2>1);"backUp"==(0>2,1>3,2>0,3>1)
		private String orientation = Orientations[0];
		private double gas = 1.0;// gas in gallon
		private boolean notReverse = true;// indicate whether the moped is in reserve, with a default true
		private String username = "random";
		
		// constructors
		/***
		 * the regular no-arg constructor
		 */
		public MopedResubmission() {}
		
		/***
		 * an overloaded constructor used to pseudo-randomly generate "drunk" mopeds
		 * @param street an integer indicating the initial street on which the moped is generated
		 * @param ave an integer indicating the initial avenue on which the moped is generated 
		 * @param orientation an integer indicating the initial orientation of the moped
		 */
		public MopedResubmission(int street, int ave,int orientation) {
			int[] location = {street, ave};
			this.setLocation(location);
			this.setOrientation(Orientations, orientation);
		}
		
		
		
		//setters and getters
		/***
		 * getter of username for a Moped object
		 * @return a string that is the username of the moped object
		 */
		public String getUsername() {
			return this.username;
		}
		/***
		 * setter of username for a Moped object
		 * @param x
		 */
		public void setUsername(String x) {
			this.username = x;
		}
		/***
		 * getter of notReverse for a Moped object
		 * @return a boolean value indicating whether a Moped is moving in reverse (true==is not in reverse, false==is in reverse)
		 */
		public boolean getNotReverse() {
			return this.notReverse;
		}
		/***
		 * setter of notReverse for a Moped object
		 * @param a boolean value for the notReverse variable
		 */
		public void setNotReverse(boolean notReverse) {
			this.notReverse = notReverse;
		}
		/***
		 * getter of location for a Moped object
		 * @return an integer array [a,b] with a being the street number and b the avenue number of the location of a Moped object
		 */
		public int[] getLocation() {
			return this.location;
		}
		/***
		 * setter of location for a Moped object
		 * @param location an integer array [a,b] with a being the street number and b the avenue number of the location of a Moped object
		 */
		public void setLocation(int[] location) {
			this.location = location;
		}
		/***
		 * getter of orientation for a Moped object
		 * @return a string of the orientation of the Moped object
		 */
		public String getOrientation() {
			return this.orientation;
		}
		
		/***
		 * a method to translate the orientation of a Moped object from a string to an integer
		 * @param orientationInString a string of the orientation of the Moped
		 * @return an integer indicating the index of value for the public String[] Orientations for Moped objects
		 */
		public int OrientationStringToInt(String orientationInString) {
			for (int i=0; i<Orientations.length;i++) {
				if (orientationInString.equals(Orientations[i])) {
					return i;
				}
			}
			return 3;// this line is unreachable; just to provide a return value outside the loop 
		}
		
		/***
		 * setter of orientation for a Moped object
		 * @param Orientations a public String[] of four orientations for all Moped objects 
		 * @param index an integer indicating the index of value for the String[]
		 */
		public void setOrientation(String[] Orientations, int index) {
			this.orientation = Orientations[index];
		}
		/***
		 * getter of gas for a Moped object
		 * @return a double indicating the gas left in the Moped
		 */
		public double getGas() {
			return this.gas;
		}
		/***
		 * setter of gas for a Moped object
		 * @param gas a double indicating the current gas level of the Moped
		 * @return a boolean value hasGas indicating whether the gas tank of the Moped is not empty (true==not empty, false==empty) 
		 */
		public boolean setGas(double gas) {
			boolean hasGas = true;
			if (gas >= 0) {
				this.gas = gas;
				if(this.getGas()==0) {
					hasGas = false;
				}
				else if (this.getGas()>0) {
					hasGas = true;
				}
			}
			else {
				this.gas = 0;
				hasGas = false;
			}
			return hasGas;
		}
		
		/***
		 * a validation method that returns a boolean value indicating whether a Moped can stay on the grid after making a desired move
		 * @param commandIndex an integer indicating the index of the command that the user has passed on in the String[] acceptableCommands
		 * @return a boolean value indicating whether a Moped can stay on the grid after making a desired move (true==will be staying on the grid, false==will be off the grid)
		 */
		public boolean onTheGrid(int commandIndex) {
			boolean onTheGrid = true;
			
			int orientation = this.OrientationStringToInt(this.getOrientation());
			int[] location = this.getLocation();
			
			if (location[0]==1) {// if the moped is currently on 1st st.
				if((orientation == 3 && commandIndex==0)||(orientation==1 && commandIndex==1)||(orientation==0&&commandIndex==2)||(orientation==2&&commandIndex==3)) {
					onTheGrid = false;
				}			
			}
			else if (location[0]==200) {// if the moped is currently on 10th st.
				if((orientation == 1 && commandIndex==0)||(orientation==3 && commandIndex==1)||(orientation==2&&commandIndex==2)||(orientation==0&&commandIndex==3)) {
					onTheGrid = false;
				}			
			}
			else if (location[1]==1) {// if the moped is currently on 1st ave.
				if((orientation == 0 && commandIndex==0)||(orientation==2 && commandIndex==1)||(orientation==1&&commandIndex==2)||(orientation==3&&commandIndex==3)) {
					onTheGrid = false;
				}
			}
			else if (location[1]==10) {// if the moped is currently on 200th ave.
				if((orientation == 2 && commandIndex==0)||(orientation==0 && commandIndex==1)||(orientation==3&&commandIndex==2)||(orientation==1&&commandIndex==3)) {
					onTheGrid = false;
				}
			}
					
			return onTheGrid;
		} // onTheGrid()
		
		/***
		 * behavior of Moped objects: go left
		 */
		public void goLeft() {
			// figure out if we are in reverse right now
			boolean notReverse = this.getNotReverse();
			// get the current orientation
			int OGOrientation = this.OrientationStringToInt(this.getOrientation());
			int newOrientation;
			
			// modify the orientation based on the boolean value of notReverse
			if (notReverse) {
				if (OGOrientation != 3) {
					newOrientation = OGOrientation +1;
				}
				else {
					newOrientation = 0;
				}
			}// if we are moving straight ahead
			else {
				if (OGOrientation != 0) {
					newOrientation= OGOrientation -1;
					}
					else {
						newOrientation = 3;
					}
			}// else--we are in reverse
			
			this.setOrientation(Orientations, newOrientation);
			// make an one-block move based on the OG orientation
			this.move(OGOrientation);
		}
		
		/***
		 * behavior of Moped objects: go right
		 */
		public void goRight() {
			// figure out if we are in reverse right now
			boolean notReverse = this.getNotReverse();
			// get the orientation
			int OGOrientation = this.OrientationStringToInt(this.getOrientation());
			int newOrientation;
			
			//modify the orientation based on the boolean value of notReverse
			if (notReverse) {
				if (OGOrientation != 0) {
					newOrientation=OGOrientation -1;
				}
				else {
					newOrientation = 3;
				}
			}// if we are moving straight ahead
			else {
				if (OGOrientation != 3) {
					newOrientation=OGOrientation +1;
					}
					else {
						newOrientation = 0;
					}
			}// else--we are in reverse
			
			// modify the initial orientation to make a param variable for the move() function so I don't have to rewrite my code...
			// *in this way, goLeft() and goRight() can share the same move() function call, and all other places that I have called the move() function do not need to be modified/rewritten
			int param = 0;
			if (OGOrientation == 0) {
				param = 2;
			}
			else if (OGOrientation == 1) {
				param = 3;
			}
			else if (OGOrientation == 2) {
				param = 0;
			}
			else if (OGOrientation == 3) {
				param = 1;
			}
			
			this.setOrientation(Orientations,newOrientation);
			// make an one-block move based on the OG orientation
			this.move(param);
		}
		
		/***
		 * behavior of Moped objects: stragiht on
		 */
		public void straightOn() {
			// flip the notReverse flag
			this.setNotReverse(true);
			// get the current orientation and leave it be
			int orientation = this.OrientationStringToInt(this.getOrientation());
			// make an param variable based on the current orientation (and some spaces on my scratching paper) to pass on to the move() function as a parameter
			int param = 0;
			if (orientation != 0) {
				param = orientation-1;
			}
			else {
				param = 3;
			}
			// make an one-block move based on the actual moving direction
			this.move(param);
		}
		
		/***
		 * behavior of Moped objects: back up
		 */
		public void backUp() {
			// flip the notReverse flag
			this.setNotReverse(false);
			// get the current orientation
			int orientation = this.OrientationStringToInt(this.getOrientation());
			// make an param variable based on the current orientation (and some spaces on my scratching paper) to pass on to the move() function as a parameter
			int param = 0;
			if (orientation != 3) {
				param = orientation+1;
			}
			else {
				param = 0;
			}
			// make an one-block move based on the actual moving direction
			this.move(param);			
		}
		
		/***
		 * behavior of Moped objects: move (make an one-block move)
		 * @param orientation an integer indicating the orientation of a Moped, depending on which the move will be made in a certain direction
		 */
		public void move(int orientation) {
			int currentSt = this.getLocation()[0];
			int currentAve = this.getLocation()[1];
			int[] newLocation = {currentSt,currentAve};
			if (orientation == 0) {
				newLocation[1] = currentAve-1;
				this.setLocation(newLocation);
			}
			else if (orientation == 1) {
				newLocation[0] = currentSt+1;
				this.setLocation(newLocation);
			}
			else if (orientation == 2) {
				newLocation[1] = currentAve+1;
				this.setLocation(newLocation);
			}
			else if (orientation == 3) {
				newLocation[0] = currentSt-1;
				this.setLocation(newLocation);
			}
			
			// print the location and orientation after this move
			this.printLocationNOrientation();
			
			// print the location-based ad, if triggered
			this.Ad();
			
			// burn 1/20 gallon of gas
			double gas = this.getGas();
			gas = gas - 0.05;
			this.setGas(gas);
			
		}
		
		/***
		 * a method that accesses, formats, and prints out the current location and orientation of a Moped
		 */
		public void printLocationNOrientation(){
			String orientation = this.getOrientation();
			int street = this.getLocation()[0];
			String stString = ""+street;
			int avenue = this.getLocation()[1];
			String stOrdinal = "th";
			String aveOrdinal = "th";
			if (street!=11 && street!=111 && (stString.substring(stString.length()-1)).equals("1")) {
				stOrdinal = "st";
			}
			else if (street!=12 && street!=112 && (stString.substring(stString.length()-1)).equals("2")) {
				stOrdinal = "nd";
			}
			else if (street!=13 && street!=113 && (stString.substring(stString.length()-1)).equals("3")) {
				stOrdinal = "rd";
			}
			
			if (avenue == 1) {
				aveOrdinal = "st";
			}
			else if(avenue==2) {
				aveOrdinal = "nd";
			}
			else if(avenue==3) {
				aveOrdinal = "rd";
			}
			System.out.printf("Now at %d%s St. and %d%s Ave., facing %s.\n", street,stOrdinal,avenue,aveOrdinal,orientation);
		}
		
		/***
		 * behavior of Moped objects: go to Petite Abeille/go home
		 */
		public void goHome() {
			int currentSt = this.getLocation()[0];
			int currentAve = this.getLocation()[1];
			int currentOrient = this.OrientationStringToInt(this.getOrientation());
			int destSt = 17;
			int destAve = 6;
			boolean hasGas = this.setGas(this.getGas());
			// move lengthwise to destSt (South/North)
			if (currentSt < destSt) {// has to head North
				if (currentOrient == 0) {// currently facing South
					this.backUp();
				}
				else if (currentOrient == 1) {// currently facing East
					this.goLeft();
				}
				else if (currentOrient == 2) {// currently facing North
					this.straightOn();
				}
				else if (currentOrient == 3) {// currently facing West
					this.goRight();
				}
				hasGas = this.setGas(this.getGas());
				// make the rest of the moves to the desired 17th St.
				for (int i=0;i<destSt-currentSt-1;i++) {
					this.straightOn();
					hasGas = this.setGas(this.getGas());
					if(!hasGas) {
						break;
					}
				}
			}// if (currentSt < destSt)
			
			if (currentSt > destSt) {// has to head South
				if (currentOrient == 0) {// currently facing South
					this.straightOn();
				}
				else if (currentOrient == 1) {// currently facing East
					this.goRight();
				}
				else if (currentOrient == 2) {// currently facing North
					this.backUp();
				}
				else if (currentOrient == 3) {// currently facing West
					this.goLeft();
				}
				hasGas = this.setGas(this.getGas());
				// make the rest of the moves to the desired 17th St.
				for (int i=0;i<currentSt-destSt-1;i++) {
					this.straightOn();
					hasGas = this.setGas(this.getGas());
					if(!hasGas) {
						break;
					}
				}
			}// else if (currentSt > destSt)
			
			else {
				;
			}// if currentSt==destSt, do nothing
			
			//update the current orientation
			currentOrient = this.OrientationStringToInt(this.getOrientation());
			
			while(hasGas) {
				// then move crosswise to destAve (West/East)
				if (currentAve < destAve) {// has to head West
					if (currentOrient == 0) {// currently facing South
						this.goRight();
					}
					else if (currentOrient == 1) {// currently facing East
						this.backUp();
					}
					else if (currentOrient == 2) {// currently facing North
						this.goLeft();
					}
					else if (currentOrient == 3) {// currently facing West
						this.straightOn();
					}
					hasGas = this.setGas(this.getGas());
					// make the rest of the moves to the desired 6th Ave.
					for (int i=0;i<destAve-currentAve-1;i++) {
						this.straightOn();
						hasGas = this.setGas(this.getGas());
						if(!hasGas) {
							break;
						}
					}
				}// if (currentAve <= destAve)
				
				else if (currentAve > destAve) {// has to head East
					if (currentOrient == 0) {// currently facing South
						this.goLeft();
					}
					else if (currentOrient == 1) {// currently facing East
						this.straightOn();
					}
					else if (currentOrient == 2) {// currently facing North
						this.goRight();
					}
					else if (currentOrient == 3) {// currently facing West
						this.backUp();
					}
					hasGas = this.setGas(this.getGas());
					System.out.println(hasGas);
					// make the rest of the moves to the desired 6th Ave.
					for (int i=0;i<currentAve-destAve-1;i++) {
						this.straightOn();
						hasGas = this.setGas(this.getGas());
						System.out.println(hasGas);
						if(!hasGas) {
							break;
						}
					}
				}// if (currentAve > destAve)
				
				else {
					;
				}// if currentAve==destAve, do nothing
				
				System.out.println("We have reached the Petite Abeille.");
				break;
			}// while (hasGas)--continue to move crosswise
			
		}// goHome()
		
		/***
		 * a method that prints out advertisements based on the location of the user's Moped (not that of the robotic Mopeds)
		 */
		public void Ad() {
			int currentSt = this.getLocation()[0];
			int currentAve = this.getLocation()[1];
			String username = this.getUsername();
			if (!username.equals("random")) {// Ads can only be triggered by the user's moped, not the two random drunk moped
				if (currentSt == 3 && currentAve == 6) {
					System.out.println("Hey! Fay Da Bakery is just around the corner, featuring their coveted sweet taro puff. Wanna check that out?");
				}
				else if (currentSt == 12 && currentAve == 4) {
					System.out.println("Welcome to Strand Book Store, New York City's legendary home of 18 Miles of new, used and rare books.");
				}
				else if (currentSt == 74 && currentAve == 1) {
					System.out.println("Do you know that Memorial Sloan Kettering Cancer Center is the world's oldest and largest private cancer center? You are right in front of it!");
				}
				else if (currentSt == 79 && currentAve == 8) {
					System.out.println("Welcome to the American Museum of Natural History, one of the world's preeminent scientific and cultural institutions.");
				}
			}
		}// location based Ad()
		
		/***
		 * a method that auto-refills the gas tank of the robotic Mopeds
		 */
		public void autoFill() {
			if (this.getGas()<=0.1) {
				this.setGas(1.0);
			}
		}// autoFill()--for drunk mopeds
		
		
		/***
		 * a method that stipulates a North-bound route for the robotic Mopeds
		 */
		public void goNorth() {
			int orient = this.OrientationStringToInt(this.getOrientation());
			if (orient==0) {// facing South
				this.backUp();
			}
			else if(orient==1) {// facing East
				this.goLeft();
			}
			else if(orient==2) {// facing North
				this.straightOn();;
			}
			else if(orient==3) {// facing West
				this.goRight();
			}
			this.autoFill();
		}// goNorth()--for drunk mopeds
		
		/***
		 * a method that stipulates a South-bound route for the robotic Mopeds
		 */
		public void goSouth() {
			int orient = this.OrientationStringToInt(this.getOrientation());
			if (orient==0) {// facing South
				this.straightOn();
			}
			else if(orient==1) {// facing East
				this.goRight();
			}
			else if(orient==2) {// facing North
				this.backUp();
			}
			else if(orient==3) {// facing West
				this.goLeft();
			}
			this.autoFill();
		}// goSouth()--for drunk mopeds
		
}// Moped class
