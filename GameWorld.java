package a2;
import java.util.LinkedList;
import java.util.Random;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 * This is the "data model" of the MVC architecture
 */
public class GameWorld implements IObservable, IGameWorld {
	private int frogLives = 5;
	private int gameScore = 0;
	private int gameTime = 0; // our variable which will hold the GameTime to be manipulated by gameTick()
	private Random myRNG = new Random();
	private int vehicleVIN = 0; // our variable which will hold the VIN for every vehicle instantiated
	private boolean sound = false; // our variable which will hold the status of whether or not to project sound
	private FroggerCollection froggerObjects = new FroggerCollection();
	IIterator froggerIterator = froggerObjects.getIterator();
	public static LinkedList<IObserver> myObserverList = new LinkedList<IObserver>(); // list which will contain all our observers
	
	/**
	 * Constructor for GameWorld. Set up our lilypads across the top row, add
	 * vehicles to each lane that move in opposite direction to it's neighboring
	 * lane, add floaters that move east, add rocks to the dirt river bank, add
	 * logs, add turtles, add cars and trucks.
	 */
	public GameWorld() {
		//addGameObject(new LilyPad());
	}
	public void displayCollection() {
		IIterator theElements = froggerObjects.getIterator();
		while (theElements.hasNext()) {
			GameObject gameObject = (GameObject)theElements.getNext();
			System.out.println(gameObject);
		}		
	}
	/**
	 * Adds the given Observer to the ObserverList so that when the observer needs to
	 * be notified the list is iterated through and the Observers are notified.
	 * @param o the observer to be notified
	 */
	public void addObserver(IObserver o) {
		myObserverList.add(o);
	}
	/**
	 * Notify the observers that the gameworld has changed but because we don't want
	 * the observers to be able to modify any of the gameworld directly we'll create
	 * a GameWorldProxy that contains all the elements and methods that the GameWorld
	 * contains but the set() methods do not contain a body.
	 */
	public void notifyObservers() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		for (IObserver o : myObserverList) {
			o.update((IObservable)proxy, null);
		}
	}
	/**
	 * Adds the given game object to the GameObject collection
	 * @param o the game object to be added to the linked list
	 */
	public void addGameObject(GameObject o) {
		//myGameObjects.add(o);
		froggerObjects.add(o);
	}
	/**
	 * Removes the given game object from the GameObject collection
	 * @param o the game object to be removed from the linked list
	 */
	public boolean removeGameObject(GameObject o) {
		//myGameObjects.remove(o);
		froggerObjects.remove(o);
		return true;
	}
	/**
	 * Retuns an int indicating how many lives remain.
	 * @return an integer indicated how many frog lives remain
	 */
	public int getFrogLives() {
		return frogLives;
	}
	/**
	 * Sets the frog lives to the given int. The frog lives should decrement whenever
	 * the frog 'dies'.
	 * @param num number of frogLives that will remain
	 */
	public void setFrogLives(int num) {
		frogLives = num;
	}
	/**
	 * Returns an int indicated what the current gamescore is.
	 * @return an integer for the current gamescore
	 */
	public int getGameScore() {
		return gameScore;
	}
	/**
	 * Sets the current gamescore
	 * @param num what to set the gamescore to. Should be implemented by using
	 * setGameScore(getGameScore +/- change)
	 */
	public void setGameScore(int num) {
		gameScore = num;
	}
	/**
	 * Returns the current gametime
	 * @return an integer for the current gametime
	 */
	public int getGameTime() {
		return gameTime;
	}
	/**
	 * Sets the current gametime.
	 * @param num what to set the gametime to. Should be implemented by using
	 * setGameTime(getGameTime()++)
	 */
	public void setGameTime(int num) {
		gameTime = num;
	}
	/**
	 * Returns the current global vehiclevin
	 * @return an integer with the current vehiclevin
	 */
	public int getVehicleVIN() {
		return vehicleVIN;
	}
	/**
	 * Sets the current vehiclevin. This is incremented everytime a vehicle is
	 * added to the gameWorld.
	 * @param num of the new vehicles vin. Should be something along the lines
	 * of setVehicleVIN(getVehicleVIN()++);
	 */
	public void setVehicleVIN(int num) {
		vehicleVIN = num;
	}
	/**
	 * This method is used to get the field for sound.
	 * @ return the state of the sound variable
	 */
	public boolean getSound() {
		return sound;
	}
	/**
	 * This method is used to set the field for sound.
	 * @param s which will be either true (on) or false (off)
	 */
	public void setSound(boolean s) {
		sound = s;
	}
}
