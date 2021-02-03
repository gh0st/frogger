package a1;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 1
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.1 (September 25, 2012)
 */
class Game {
	private GameWorld gw;
	public Game() {
		gw = new GameWorld();
		play();
	}
	
	/** Envokes getCommand() and runs until the user quits by pressing 'q' */
	private void play() {
		while(true) {
			gw.getCommand();
		}
	}	
}
