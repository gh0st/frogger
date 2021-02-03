package a2;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
public class GameWorldProxy implements IObservable, IGameWorld {
	// code here to accept and hold a GameWorld, provide implementations
	// of all the public methods in a GameWorld, forward allowed
	// calls to the actual GameWorld, and reject calls to methods
	// which the outside should not be able to access in the GameWorld.
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
	/*public Iterator getIterator() {
		return realGameWorld.getIterator();
	}*/
	public void addObserver(IObserver o) {}
	public void notifyObservers() {}
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
