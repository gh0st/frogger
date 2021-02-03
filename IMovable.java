package a3;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 3
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.3 (November 09, 2012)
 */
public interface IMovable {
	/**
	 * Moves the object to a new location by calling setLocation() on the obeject
	 * @param int x new location
	 * @param int y new location
	 */
	public void move(int newX, int newY);
}
