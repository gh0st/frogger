package frogger;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public interface IGameWorld {
	void addObserver(IObserver o);
	void notifyObservers();
	void addGameObject(GameObject o);
	boolean removeGameObject(GameObject o);
	int getFrogLives();
	void setFrogLives(int num);
	int getGameScore();
	void setGameScore(int num);
	int getGameTime();
	void setGameTime(int num);
	boolean getSound();
	void setSound(boolean s);
}
