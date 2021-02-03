package a4;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public class GameWorldProxy implements IObservable, IGameWorld {
	/**
	 * Code here to accept and hold a GameWorld, provide implementations of all
	 * the public methods in a GamewWorld, forward allowed calls to the actual
	 * GameWorld, and reject calls to methods which the outside should not be
	 * able to access in the GameWorld.
	 */
	private int frogLives;
	private int gameScore;
	private int gameTime;
	private int vehicleVIN;
	private boolean sound;
	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw) {
		realGameWorld = gw;
		this.frogLives = gw.getFrogLives();
		this.gameScore = gw.getGameScore();
		this.gameTime = gw.getGameTime();
		this.vehicleVIN = gw.getVehicleVIN();
		this.sound = gw.getSound();
	}
	public void addObserver(IObserver o) {}
	public void notifyObservers() {
		realGameWorld.notifyObservers(); // lets try adding this to see if it works
	}
	public void addGameObject(GameObject o) {
		realGameWorld.addGameObject(o);
	}
	public boolean removeGameObject(GameObject o) {
		return false;
	}
	public int getFrogLives() {
		return frogLives;
	}
	public void setFrogLives(int num) {}
	public int getGameScore() {
		return gameScore;
	}
	public void setGameScore(int num) {}
	public int getGameTime() {
		return gameTime;
	}
	public void setGameTime(int num) {}
	public int getVehicleVIN() {
		return vehicleVIN;
	}
	public void setVehicleVIN(int num) {
		vehicleVIN = num;
	}
	public boolean getSound() {
		return sound;
	}
	public void setSound(boolean s) {}
}
