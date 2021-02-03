package a1;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;

public class GameWorld {
	//private static Frog myFrog; // our frog which will be the only frog that operates in the game
	public static int frogLives = 5;
	public static int gameScore = 0;
	public static int globalGameTime = 0; // our variable which will hold the GameTime to be manipulated by gameTick()
	public static int globalVIN = 0; // our variable which will hold the VIN for every vehicle instantiated
	public Frog f;
	public LinkedList<GameObject> myGameObjects = new LinkedList<GameObject>(); // list which will contain all our gameobjects
	public enum Direction {
		NORTH, SOUTH, EAST, WEST
	}
	
	public LilyPad tempUnoccupiedLilyPad = new LilyPad(40, 40, 1, false); // a temporary lily pad which will be used for pretending the frog has hopped
	// onto an unoccupied lily pad. this will be removed in later versions of the game
	public LilyPad tempOccupiedLilyPad = new LilyPad(80, 40, 1, true); // a temporary lily pad which will be used for pretending the frog has hopped
	// onto an occupied lily pad. this will be removed in later versions of		Iterator<GameObject> itr = list.iterator();
	
	/**
	 * Begin reading characters from the InpuStreamReader
	 * then parsers the input to execute methods in GameWorld
	 */
	public void getCommand() {
		// code here that will be
		// placed in play that continuously
		// gets command from Scanner
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			int input = br.read();
			switch(input) {
				case 'c': case 'C': addCar(); break; // add a new car with randomly-selected traffic lane and passenger capacity
				case 'd': case 'D': printDisplay(); break; // print a display giving the current game state values
				case 'e': case 'E': f.hop(Direction.EAST); break; // hop the frog east one lane width
				case 'f': case 'F': f = addFrog(); break; // add a new frog at randomly-selected x location along bottom lane
				case 'g': case 'G': addGameObject(); break; // add a game object to the gameworld
				case 'k': case 'K': addTruck(); break; // add a new truck with randomly-selected traffic lane and length
				case 'l': case 'L': addLog(); break; // add a new log with a randomly-selected length and x location
				case 'm': case 'M': printMap(); break; // print a "map" showing the current world state
				case 'n': case 'N': f.hop(Direction.NORTH); break; // hop the frog north one lane width
				case 'q': case 'Q': quit(); break; // quit the game but first confirm the user's intent before actually quiting
				case 'r': case 'R': addRock(); break; // add a new rock with a randomly-selected size and location
				case 's': case 'S': f.hop(Direction.SOUTH); break; // hop the frog south one lane width
				case 't': case 'T': System.out.println("Game clock ticked..."); gameTick(myGameObjects); break; // tell the game world that the "game clock" has ticked
				case 'u': case 'U': addTurtle(); break; // add a new turtle with random size and bouyancy
				case 'w': case 'W': f.hop(Direction.WEST); break; // hop the frog west one lane width
				case '1': System.out.println("Car killed Frog..."); f.killFrog(); f = null; break; // pretend that the frog has collided with a Car
				case '2': System.out.println("Truck killed Frog..."); f.killFrog(); f = null; break; // prented that the frog has collided with a Truck
				case '3': System.out.println("Rock killed Frog..."); f.killFrog(); f = null; break; // pretend that the frog has hopped onto a hot rock
				case '4': System.out.println("Frog hops on log..."); f.matchFloater(getLog(myGameObjects).getSpeed(), getLog(myGameObjects).getDirection()); break; // prented that the frog has hopped onto a log
				case '5': System.out.println("Frog hops on turtle..."); f.matchFloater(getTurtle(myGameObjects).getSpeed(), getTurtle(myGameObjects).getDirection()); break; // pretend that the frog has hopped onto a turtle
				case '6': System.out.println("Frog hops on empty lily pad..."); f.matchLilyPad(tempUnoccupiedLilyPad); break; // pretend that the frog has hopped onto an empty lily pad
				case '7': System.out.println("Frog hops on occupied lily pad..."); f.matchLilyPad(tempOccupiedLilyPad); break; // pretend that the frong has hopped onto an occupied lily pad
				case '8': System.out.println("Frog hops into water..."); f.killFrog(); f = null; break; // prented that the frog has hopped into the water
				case '9': System.out.println("Frog hops out of game..."); f.killFrog(); f = null; break; // pretend that the frog has attempted to move outside the game world boundaries
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	/**
	 * Cycles through the GameObject linked list and returns a log once it reaches on.
	 * @param LinkedList<GameObject> list the list to be searched
	 */
	public Log getLog(LinkedList<GameObject> list) {
		Iterator<GameObject> itr = list.iterator();
		while (itr.hasNext()) {
			GameObject o = itr.next();
			if (o instanceof Log) {
				return (Log)o;
			}
		}
		System.out.println("There's no logs!!");
		return null;
	}
	/**
	 * Cycles through the GameObject linked list and returns a turtle once it reaches one.
	 * @param LinkedList<GameObject> list the list to be searched
	 */
	public Turtle getTurtle(LinkedList<GameObject> list) {
		Iterator<GameObject> itr = list.iterator();
		while (itr.hasNext()) {
			GameObject o = itr.next();
			if (o instanceof Turtle) {
				return (Turtle)o;
			}
		}
		System.out.println("There's no turtles!!");
		return null;
	}
	/**
	 * Cycles through the GameObject linked list and returns a lilypad once it reaches one.
	 * @param LinkedList<GameObject> list the list to be searched
	 */
	public LilyPad getLilyPad(LinkedList<GameObject> list) {
		Iterator<GameObject> itr = list.iterator();
		while (itr.hasNext()) {
			GameObject o = itr.next();
			if (o instanceof LilyPad) {
				return (LilyPad)o;
			}
		}
		System.out.println("There's no lilypads!!");
		return null;
	}
	/** GameObject class is the parent of all other subobjects. */
	public class GameObject {
		private Point location = new Point();
		private Color objColor;
		public String getLocation() {
			return "x="+location.getX()+",y="+location.getY();
		}
		public void setLocation(int newX, int newY) {
			location.x = newX;
			location.y = newY;
		}
		public String getColor() {
			return "color=["+objColor.getRed()+","+objColor.getGreen()+","+objColor.getBlue()+"]";
		}
		public void setColor(int newRed, int newGreen, int newBlue) {
			objColor = new Color(newRed, newGreen, newBlue);
		}
		
		public String toString() {
			return "GameObject: "+getLocation()+" "+getColor();
		}
		public int getX() {
			return (int)location.getX();
		}
		public int getY() {
			return (int)location.getY();
		}
		public void tick() {
			System.out.println("Gameobject tick()...");
		}
	}
	
	/** This class is the abstract superclass which all fixed objects are inhereted from */
	abstract class FixedGameObject extends GameObject {}
	
	/** This class is the abstract superclass which all moving objects are inhereted from */
	abstract class MovingGameObject extends GameObject {
		private int speed;
		private Direction dir;
		/**
		 * Moves the object to the specified x- and y-coordinates by calling the
		 * superclass' setLocation()
		 * @param x new x-coordinate which the object will move to
		 * @param y new y-coodrinate which the object will move to
		 */
		public void move(int x, int y) {
			setLocation(x, y);
		}
		/** Get the direction of the moving object */
		public Direction getDirection() {
			return dir;
		}
		/** Set the direction of the moving object */
		public void setDirection(Direction newDir) {
			dir = newDir;
		}
		/** Get the speed of the moving object */
		public int getSpeed() {
			return speed;
		}
		/** Set the speed of the moving object */
		public void setSpeed(int newSpeed) {
			speed = newSpeed;
		}
		/**
		 * Implemented in gameTick()
		 */
		public void tick() {
			// update positions of all moveable objects according to their speed and heading
			// reduce all turtle's bouyancy by one
			// any turtle with a new bouyancy less than four change color
			// any turtle with a new bouyancy of zero "dive" and are removed from the game
			switch (getDirection()) {
				case EAST: move((getX()+(getSpeed()*100)), getY()); break;
				case WEST: move((getX()-(getSpeed()*100)), getY()); break;
			}
		}
	}
	
	/**
	 * Lily pads are fixed objects which have an attribute height; for this assignment
	 * there are just two heights (tall and short). Lily pads start out empty; as the
	 * game progresses frogs try to hop on them (but only one frog is allowed per lily
	 * pad).
	 */
	public class LilyPad extends FixedGameObject {
		private int height;
		private boolean occupied;
		public LilyPad(int newX, int newY, int newHeight, boolean newOccupied) {
			setLocation(newX, newY);
			setHeight(newHeight);
			setOccupied(newOccupied);
		}
		/**
		 * Gets the height of the LilyPad
		 * @return the height of the LilyPad
		 */
		public int getHeight() {
			return height;
		}
		/**
		 * Sets the height of the LilyPad
		 * @param newHeight the new height of the LilyPad
		 */
		public void setHeight(int newHeight) {
			height = newHeight;
		}
		/**
		 * Gets whether or not the lilypad is occupied or not.
		 * @return boolean true or false
		 */
		public boolean getOccupied() {
			return occupied;
		}
		/**
		 * Sets whether or not the lilypad is occupied or not.
		 * @param boolean true/false true of the lilypad is occupied, false if not
		 */
		public void setOccupied (boolean occ) {
			occupied = occ;
		}
		/**
		 * returns a description of LilyPad
		 * @return string fetching it's location and objColor
		 */
		public String toString() {
			return "LilyPad: "+getLocation()+" "+getColor()+" height="+getHeight()+" occupied="+getOccupied();
		}
	}
	
	/**
	 * Rocks are fixed objects which have an attribute size; for this assignment there
	 * are just two sizes (small [1] and large [2]).
	 */
	public class Rock extends FixedGameObject {
		private int size;
		// constructs a new rock with a randomly chosen size and a
		// randomly chosen location along the dirt river bank
		public Rock() {
			setLocation((50 + (int)(Math.random() * ((950-50)+1))), 55);
			setColor(128,128,128);
			setSize((1 + (int)(Math.random() * ((2-1)+1))));
		}
		/**
		 * Gets the size of the Rock
		 * @return int the size of the rock either small (1) or large (2)
		 */
		public int getSize() {
			return size;
		}
		/**
		 * Sets the size of the rock
		 * @param int size of the rock just small (1) and large (2)
		 */
		public void setSize(int newSize) {
			size = newSize;
		}
		public String toString() {
			return "Rock: "+getLocation()+" "+getColor()+" size="+getSize();
		}
	}
	
	/**
	 * Floating objects (floaters) have an attribute bouyancy which controls how much
	 * weight they can support. Objects with zero bouyancy stop floading (sink to the
	 * bottom). When a floating object is created it is randomly assigned to one of
	 * the three river channels (meaning its y location is determinted and remains
	 * fixed thereafter). Floating objects always move in the direction and at the
	 * river channel in which they are floating (and they never change channels).
	 */
	public class Floaters extends MovingGameObject implements IMoveable {
		private int bouy; // bouyancy
		/**
		 * Gets the bouyancy of the object
		 * @return int the bouyancy determines how much weight the object
		 * can support
		 */
		public int getBouy() {
			return bouy;
		}
		/**
		 * Sets the bouyancy of the object
		 * @param int the bouyancy is the weight which the object will be able to hold
		 */
		public void setBouy(int newBouy) {
			bouy = newBouy;
		}
	}
	/**
	 * Logs are floating objects which have a length attribute, which will be either
	 * "short" or "long". Logs start with a random positive bouyancy which never
	 * changes.
	 */
	public class Log extends Floaters {
		private int length;
		public Log() {
			setBouy((1+(int)(Math.random() * ((6-1)+1))));
			setColor(0,0,0);
			setDirection(Direction.EAST);
			setLength((1 + (int)(Math.random() * ((2-1)+1))));
			setLocation((50 + (int)(Math.random() * ((950-50)+1))), (65 + (int)(Math.random() * ((85-65)+1))));
		}
		public int getLength() {
			return length;
		}
		public void setLength(int newLength) {
			length = newLength;
		}
		public String toString() {
			return "Log: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" bouy="+getBouy()+" length="+getLength();
		}
	}
	/**
	 * Turtles are loating objects which have a size attribute, which will be either
	 * "large" or "small". Turtles have a bouyancy which decreases over time (because
	 * the turtles eventually dive below the surface). Turtles can change color, which
	 * they do when they are about to dive.
	 */
	public class Turtle extends Floaters {
		private int size;
		public Turtle() {
			setBouy((1+(int)(Math.random() * ((6-1)+1))));
			setColor(255,0,255);
			setDirection(Direction.WEST);
			setSize((1 + (int)(Math.random() * ((2-1)+1))));
			setSpeed(1);
			setLocation((50 + (int)(Math.random() * ((950-50)+1))), (65 + (int)(Math.random() * ((85-65)+1))));
		}
		public int getSize() {
			return size;
		}
		public void setSize(int newSize) {
			size = newSize;
		}
		public String toString() {
			return "Turtle: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" bouy="+getBouy()+" size="+getSize();
		}
		public void tick() {
			// update positions of all moveable objects according to their speed and heading
			// any turtle with a new bouyancy less than four change color
			// any turtle with a new bouyancy of zero "dive" and are removed from the game
			switch (getDirection()) {
				case EAST: move((getX()+(getSpeed()*100)), getY()); break;
				case WEST: move((getX()-(getSpeed()*100)), getY()); break;
			}
			int tempBouy = getBouy() - 1;
			setBouy(tempBouy); // reduce all turtle's bouyancy by one
			if (getBouy() < 4) setColor(0,0,245);
			if (getBouy() == 0) System.out.println("Kill the turtle!!");
		}
	}
	
	/**
	 * Vehicles have a unique VIN; this is a positive integer which is different for
	 * every vehicle in the game. For simplicity the first vehicle gets VIN 1, then
	 * each new vehicle after that gets the next available integer. Car vehicles also
	 * have an integer attribute passengerCount specifying how many passengers the car
	 * can carry, while Trucks have an attribute length specifying the length of the
	 * truck.
	 */
	public class Vehicles extends MovingGameObject implements IMoveable {
		private int vin;
		/**
		 * Get the vin of the vehicle
		 * @return int vin of the vehicle
		 */
		public int getVIN() {
			return vin;
		}
		/**
		 * Set the vin of the vehicle based off the global vin so that all vehicles
		 * have a consistent vin
		 * @param int vin of new vehicle
		 */
		public void setVIN(int newVin) {
			vin = newVin;
			// add one to the global vin
			globalVIN++;
		}
	}
	/**
	 * Car
	 */
	public class Car extends Vehicles {
		private int passengerCount;
		public Car() {
			setColor(255,0,0);
			setDirection(Direction.EAST);
			setLocation((50 + (int)(Math.random() * ((950-50)+1))), (15 + (int)(Math.random() * ((45-15)+1))));
			setPassengerCount((1+(int)(Math.random()*((8-1)+1))));
			setSpeed(1);
			setVIN(globalVIN);
		}
		/**
		 * Get the number of passengers in car. I think this really returns the size
		 * of the car, like the length...
		 * @return int passengerCount
		 */
		public int getPassengerCount() {
			return passengerCount;
		}
		/** Set the number of passengers in the car.
		 * @param int newPassenger count
		 */
		public void setPassengerCount(int newPassengerCount) {
			passengerCount = newPassengerCount;
		}
		public String toString() {
			return "Car: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" passengerCount="+getPassengerCount()+" vin="+getVIN();
		}
	}
	/**
	 * Truck
	 */
	public class Truck extends Vehicles {
		private int length;
		public Truck() {
			setColor(0,255,0);
			setDirection(Direction.WEST);
			setLength((1+(int)(Math.random()*((2-1)+1))));
			setLocation((50 + (int)(Math.random() * ((950-50)+1))), (15 + (int)(Math.random() * ((45-15)+1))));
			setSpeed(2);
			setVIN(globalVIN);
		}
		public int getLength() {
			return length;
		}
		public void setLength(int newLength) {
			length = newLength;
		}
		public String toString() {
			return "Truck: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" length="+getLength()+" vin="+getVIN();
		}
	}
	/** Add a <b>f</b>rog to the world at a randomly-chosen X location along the bottom (grass) */
	public class Frog extends MovingGameObject implements IHoppable, IMoveable {
		public Frog() {
			setColor(0,128,0);
			setLocation(450, 5);
		}
		/*public synchronized Frog getFrog() {
			if (theFrog == null)
				theFrog = new Frog();
			return theFrog;
		}*/
		/**
		 * Kill frog method will only work if there is an active frog to be killed
		 */
		public void killFrog() {
			frogLives--;
			if (frogLives == -1) {
				System.out.println("You're out of lives. Game over... =[");
			}
		}
		/**
		 * Move frog to given location.
		 * @param int newX the new x position
		 * @param int newY the new y position
		 */
		public void move(int newX, int newY) {
			setLocation(newX, newY);
		}
		/**
		 * The frog hops given a specified direction a fixed number of spaces which
		 * implements move()
		 * @param Direction dir either NORTH, SOUTH, EAST, WEST
		 */
		public void hop(Direction dir) {
			switch(dir) {
				case NORTH: move(getX(), getY()+10); break;
				case SOUTH: move(getX(), getY()-10); break;
				case EAST: move(getX()+100, getY()); break;
				case WEST: move(getX()-100, getY()); break;
			}
		}
		/**
		 * This function is used when a frog hops onto a floating object such as a log
		 * or a turtle. The effect is that the frogs speed and direction now reflect
		 * the object on which it hopped onto.
		 * @param int newSpeed is the new speed of the frog
		 * @param int newDir is the new direction of the frog
		 */
		public void matchFloater(int newSpeed, Direction newDir) {
			System.out.println("Inside matchFloater() with params: newSpeed="+newSpeed+" newDir"+newDir);
			setSpeed(newSpeed);
			setDirection(newDir);
		}
		/**
		 * This function is used when a frog hops onto a lilypad. The effect is the
		 * frogs location is now set to that of the lilypad. The lilypad is set to
		 * occupied and a new frog will be created along the bottom row.
		 * @param LilyPad lpad is the lilypad object of which the frog has jumped on
		 * @return boolean true or false regarding whether or not the lilypad is
		 * occupied.
		 */
		public boolean matchLilyPad(LilyPad lpad) {			
			if (lpad.getOccupied() == false) {
				setLocation(lpad.getX(), lpad.getY());
				setSpeed(0);
				System.out.println("The frog is on an empty lilypad!");
				return false;
			} else {
				System.out.println("The frog is now on an occupied lilypad!");
				System.out.println("Here we would initiate killFrog() but we'll do that later.");
				return true;
			}
		}
		public String toString() {
			return "Frog: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection();
		}
	}
	
	/** Add a <b>r</b>ock to the current world state. */
	public void addRock() {
		Rock r = new Rock();
		myGameObjects.add(r);
	}
	/** Add a <b>l</b>og to the current world state. */
	public void addLog() {
		Log l = new Log();
		myGameObjects.add(l);
	}
	/** Add a <b>t</b>urtle to the current world state. */
	public void addTurtle() {
		Turtle t = new Turtle();
		myGameObjects.add(t);
	}
	/** Add a <b>c</b>ar to the current world state. */
	public void addCar() {
		Car c = new Car();
		myGameObjects.add(c);
	}
	/** Add a truc<b>k</b> to the current world state. */
	public void addTruck() {
		Truck k = new Truck();
		myGameObjects.add(k);
	}
	/** Add a <b>f</b>rog to the current world state. */
	public Frog addFrog() {
		return new Frog();
		//myGameObjects.add(f);
	}
	/** Add a <b>g</b>ameobject to the current world state. */
	public void addGameObject() {
		GameObject g = new GameObject();
		myGameObjects.add(g);
	}
	/** Print a <b>d</b>isplay giving current game state values. */
	public void printDisplay() {
		System.out.println("Current game clock value "+globalGameTime);
		System.out.println("Curreng game score value "+gameScore);
		System.out.println("Number of frogs left "+frogLives);
	}
	/** Print a <b>m</b>ap showing the current world state. */
	public void printMap() {
		for (GameObject x: myGameObjects) {
			System.out.println(x.toString());
		}
	}
	/**
	 * "Game Clock" has ticked. A clock tick in the game world has the following
	 * effects:
	 * (1) all moveable objects are told to update their positions according to their
	 * current heading and speed.
	 * (2) every turtle's bouyancy is reduced by one
	 * (3) any turtles with a new bouyancy less than four change color
	 * (4) any turtles with a new bouyancy of zero "dive" and are removed from the game
	 * (5) the "ellapsed game time" is incremented by one
	 */
	public void gameTick(LinkedList<GameObject> list) {
		for (GameObject x: list) {
			if (x instanceof MovingGameObject || x instanceof Turtle) {
				x.tick();
			}
		}
		globalGameTime++; // the ellapsed game time is incremented by one 
	}
	/** Prompts the user whether or not they want to quit before exiting the game. */
	public void quit() {
		System.out.println("Are you sure you want to quit? (Y/N)");
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			int input = br.read();
			switch(input) {
				case 'y': case 'Y': System.exit(0);
				case 'n': case 'N': System.out.println("Game on!");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
