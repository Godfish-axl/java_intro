package edu.nyu.cs.kc3793;

public class MopedExtraCredit {
	//properties of mopeds
		private int[] location = {10,5};// location in the {st #, ave #} format
		private static String[] Orientations = {"South","East","North","West"};// "goLeft"==index++/(0>1>2>3); "goRight"==(0>3>2>1);"backUp"==(0>2,1>3,2>0,3>1)
		private String orientation = Orientations[0];
		private double gas = 1.0;// gas in gallon
		
		// constructors
		public MopedExtraCredit() {
			
		}// regular no-arg constructor
		
		public MopedExtraCredit(int street, int ave,int orientation) {
			int[] location = {street, ave};
			this.setLocation(location);
			this.setOrientation(Orientations, orientation);
		}// overloaded constructor used to pseudo-randomly generate "drunk" mopeds
		
		
		
		//setters and getters
		public int[] getLocation() {
			return this.location;
		}
		public void setLocation(int[] location) {
			this.location = location;
		}
		public String getOrientation() {
			return this.orientation;
		}
		
		// *a method to translate orientation from string to int
		public int OrientationStringToInt(String orientationInString) {
			for (int i=0; i<Orientations.length;i++) {
				if (orientationInString.equals(Orientations[i])) {
					return i;
				}
			}
			return 3;// this line is unreachable; just to provide a return value outside the loop 
		}
		
		public void setOrientation(String[] Orientations, int index) {
			this.orientation = Orientations[index];
		}
		public double getGas() {
			return this.gas;
		}
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
		} // onTheGrid() validation method
		
		public void goLeft() {
			// modify the orientation
			int orientation = this.OrientationStringToInt(this.getOrientation());
			
			if (orientation != 3) {
				orientation ++;
			}
			else {
				orientation = 0;
			}
			
			this.setOrientation(Orientations, orientation);
			// make an one-block move based on the modified orientation
			this.move(orientation);
		}
		
		public void goRight() {
			// modify the orientation
			int orientation = this.OrientationStringToInt(this.getOrientation());
			
			if (orientation != 0) {
				orientation --;
			}
			else {
				orientation = 3;
			}
			
			this.setOrientation(Orientations, orientation);
			// make an one-block move based on the modified orientation
			this.move(orientation);
		}
		
		public void straightOn() {
			// get the current orientation and leave it be
			int orientation = this.OrientationStringToInt(this.getOrientation());
			// make a one-block move based on the (un)modified orientation
			this.move(orientation);
		}
		
		public void backUp() {
			// modify the orientation
			int orientation = this.OrientationStringToInt(this.getOrientation());
			if (orientation == 0) {
				orientation = 2;
			}
			else if (orientation == 1) {
				orientation = 3;
			}
			else if (orientation == 2) {
				orientation = 0;
			}
			else if (orientation == 3) {
				orientation = 1;
			}
			this.setOrientation(Orientations, orientation);
			// make an one-block move based on the modified orientation
			this.move(orientation);
		}
		
		public void move(int orientation) {
			int currentSt = this.getLocation()[0];
			int currentAve = this.getLocation()[1];
			int[] newLocation = {currentSt,currentAve};
			if (orientation == 0) {
				newLocation[0] = currentSt-1;
				this.setLocation(newLocation);
			}
			else if (orientation == 1) {
				newLocation[1] = currentAve-1;
				this.setLocation(newLocation);
			}
			else if (orientation == 2) {
				newLocation[0] = currentSt+1;
				this.setLocation(newLocation);
			}
			else if (orientation == 3) {
				newLocation[1] = currentAve+1;
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
				
		}//the move() function modifies the location based on the modified orientation of the moped
		
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
		
		public void Ad() {
			int currentSt = this.getLocation()[0];
			int currentAve = this.getLocation()[1];
			
			if (currentSt == 3 && currentAve == 6) {
				System.out.println("Hey! Fay Da Bakery is just around the corner, featuring their coveted sweet taro puff. Wanna check that out?");
			}
			else if (currentSt == 12 && currentAve == 4) {
				System.out.println("Welcome to Strand Book Store, New York City's legendary home of 18 Miles of new, used and rare books.");
			}
			else if (currentSt == 74 && currentAve == 1) {
				System.out.println("Do you know that Memorial Sloan Kettering Cancer Center is the world’s oldest and largest private cancer center? You are right in front of it!");
			}
			else if (currentSt == 79 && currentAve == 8) {
				System.out.println("Welcome to the American Museum of Natural History, one of the world's preeminent scientific and cultural institutions.");
			}
		}// location based Ad()
		
		
		public void autoFill() {
			if (this.getGas()<=0.1) {
				this.setGas(1.0);
			}
		}// autoFill()--for drunk mopeds
		
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
		
}
