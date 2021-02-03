package frogger;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

/**
 * CSC133 Clevenger Fall '12 Assignment 4 Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012) This is the "data model" of the MVC
 *          architecture
 */
public class GameWorld implements IObservable, IGameWorld {
  private int frogLives;
  private int gameScore;
  private int gameTime; // our variable which will hold the GameTime to be manipulated by gameTick()
  private int gameTimeBuffer;
  private Random myRNG = new Random();
  private int vehicleVIN; // our variable which will hold the VIN for every vehicle instantiated
  private boolean sound; // our variable which will hold the status of whether or not to project sound
  private boolean paused; // is the game paused
  /*
   * private int carCount; // tracks how many cars are in the world private int
   * logCount; // tracks how many logs are in the gameworld private int
   * truckCount; // tracks how many trucks are in the gameworld private int
   * turtleCount; // tracks how many turtles are in the gameworld
   */
  private FroggerCollection froggerObjects;
  private IIterator froggerIterator;
  public static LinkedList<IObserver> myObserverList; // list which will contain all our observers
  private String soundDir = "." + File.separator + "frogger" + File.separator + "sounds" + File.separator;
  private String fileName = "background.wav";
  private String filePath = soundDir + fileName;
  private Sound bgSound = new Sound(filePath);

  /**
   * Constructor for GameWorld. Set up our lilypads across the top row, add
   * vehicles to each lane that move in opposite direction to it's neighboring
   * lane, add floaters that move east, add rocks to the dirt river bank, add
   * logs, add turtles, add cars and trucks.
   */
  public GameWorld() {
    frogLives = 5;
    gameScore = 0;
    gameTime = 0;
    gameTimeBuffer = 0;
    vehicleVIN = 0;
    sound = true;
    froggerObjects = new FroggerCollection();
    froggerIterator = froggerObjects.getIterator();
    myObserverList = new LinkedList<IObserver>();
    // add lilypads across the top row
    froggerObjects.add(new LilyPad(100, 475, 0, false));
    froggerObjects.add(new LilyPad(300, 475, 0, false));
    froggerObjects.add(new LilyPad(500, 475, 0, false));
    froggerObjects.add(new LilyPad(700, 475, 0, false));
    froggerObjects.add(new LilyPad(900, 475, 0, false));
    // add rocks to the dirt river bank
    froggerObjects.add(new Rock(getRandom(0, 200), 275, myRNG.nextInt(1)));
    froggerObjects.add(new Rock(getRandom(200, 400), 275, myRNG.nextInt(1)));
    froggerObjects.add(new Rock(getRandom(400, 600), 275, myRNG.nextInt(1)));
    froggerObjects.add(new Rock(getRandom(600, 800), 275, myRNG.nextInt(1)));
    froggerObjects.add(new Rock(getRandom(800, 1000), 275, myRNG.nextInt(1)));
    // begin adding other objects to the gameworld. add an object to each row
    froggerObjects.add(new Log(425));
    froggerObjects.add(new Turtle(375));
    froggerObjects.add(new Log(325));
    froggerObjects.add(new Car(this, 225));
    froggerObjects.add(new Truck(this, 175));
    froggerObjects.add(new Truck(this, 125));
    froggerObjects.add(new Car(this, 75));
    froggerObjects.add(new Frog(this));
    froggerObjects.add(new Bird());
    if (getSound())
      bgSound.loop();
  }

  /**
   * Display all the elements that are part of the collection
   */
  public void displayCollection() {
    IIterator theElements = froggerObjects.getIterator();
    while (theElements.hasNext()) {
      GameObject gameObject = (GameObject) theElements.getNext();
      System.out.println(gameObject);
    }
  }

  /**
   * Adds the given Observer to the ObserverList so that when the observer needs
   * to be notified the list is iterated through and the Observers are notified.
   * 
   * @param o the observer to be notified
   */
  public void addObserver(IObserver o) {
    myObserverList.add(o);
  }

  /**
   * Notify the observers that the gameworld has changed but because we don't want
   * the observers to be able to modify any of the gameworld directly we'll create
   * a GameWorldProxy that contains all the elements and methods that the
   * GameWorld contains but the set() methods do not contain a body.
   */
  public void notifyObservers() {
    GameWorldProxy proxy = new GameWorldProxy(this);
    for (IObserver o : myObserverList) {
      o.update((IObservable) proxy, null);
    }
  }

  /**
   * Adds the given game object to the GameObject collection
   * 
   * @param o the game object to be added to the linked list
   */
  public void addGameObject(GameObject o) {
    froggerObjects.add(o);
  }

  /**
   * Removes the given game object from the GameObject collection
   * 
   * @param o the game object to be removed from the linked list
   */
  public boolean removeGameObject(GameObject o) {
    froggerObjects.remove(o);
    return true;
  }

  /**
   * Retuns an int indicating how many lives remain.
   * 
   * @return an integer indicated how many frog lives remain
   */
  public int getFrogLives() {
    return frogLives;
  }

  /**
   * Sets the frog lives to the given int. The frog lives should decrement
   * whenever the frog 'dies'.
   * 
   * @param num number of frogLives that will remain
   */
  public void setFrogLives(int num) {
    frogLives = num;
  }

  /**
   * Returns an int indicated what the current gamescore is.
   * 
   * @return an integer for the current gamescore
   */
  public int getGameScore() {
    return gameScore;
  }

  /**
   * Sets the current gamescore
   * 
   * @param num what to set the gamescore to. Should be implemented by using
   *            setGameScore(getGameScore +/- change)
   */
  public void setGameScore(int num) {
    gameScore = num;
  }

  /**
   * Returns the current gametime
   * 
   * @return an integer for the current gametime
   */
  public int getGameTime() {
    return gameTime;
  }

  /**
   * Sets the current gametime.
   * 
   * @param num what to set the gametime to. Should be implemented by using
   *            setGameTime(getGameTime()++)
   */
  public void setGameTime(int i) {
    gameTime = i;
  }

  public void incGameTime() {
    gameTimeBuffer++;
    if ((gameTimeBuffer % 50) == 0)
      gameTime++;
  }

  /**
   * Returns the current global vehiclevin
   * 
   * @return an integer with the current vehiclevin
   */
  public int getVehicleVIN() {
    return vehicleVIN;
  }

  /**
   * Sets the current vehiclevin. This is incremented everytime a vehicle is added
   * to the gameWorld.
   * 
   * @param num of the new vehicles vin. Should be something along the lines of
   *            setVehicleVIN(getVehicleVIN()++);
   */
  public void setVehicleVIN(int num) {
    vehicleVIN = num;
  }

  /**
   * This method is used to get the field for sound. @ return the state of the
   * sound variable
   */
  public boolean getSound() {
    return sound;
  }

  /**
   * This method is used to set the field for sound.
   * 
   * @param s which will be either true (on) or false (off)
   */
  public void setSound(boolean s) {
    if (s == false) {
      bgSound.stop();
    } else if (s == true) {
      bgSound.loop();
    }
    sound = s;
    return;
  }

  public boolean getPaused() {
    return paused;
  }

  public void setPaused(boolean b) {
    paused = b; // the game has been paused
    return;
  }

  public FroggerCollection getObjectCollection() {
    return froggerObjects;
  }

  public IIterator getIterator() {
    return froggerIterator;
  }

  public int getRandom(int min, int max) {
    int r = (min + (int) (Math.random() * ((max - min) + 1)));
    return r;
  }

  public void addGameObjectBatch() {
    froggerObjects.add(new Car(this, 225));
    froggerObjects.add(new Truck(this, 175));
    froggerObjects.add(new Truck(this, 125));
    froggerObjects.add(new Car(this, 75));
    froggerObjects.add(new Bird());
    froggerObjects.add(new Turtle(325));
    froggerObjects.add(new Log(375));
    froggerObjects.add(new Turtle(425));
  }

  public void addGameObjectBatch2() {
    froggerObjects.add(new Truck(this, 225));
    froggerObjects.add(new Truck(this, 75));
    froggerObjects.add(new Car(this, 175));
    froggerObjects.add(new Car(this, 125));
    froggerObjects.add(new Log(425));
    froggerObjects.add(new Turtle(375));
    froggerObjects.add(new Log(325));
  }
}
