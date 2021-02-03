package a1;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 1
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.1 (September 25, 2012)
 */
public interface IMoveable {
	/**
	 * Gets the location of the given object.
	 * @param obj the object in which you would like to obtain the location of
	 */
	public String getLocation();
	/**
	 * Sets the location of the given object.
	 * @param x the x-coordinate you wish to set
	 * @param y the y-coordinate you wish to set
	 */
	public void setLocation(int newX, int newY);
	/**
	 * Moves the object to a new location
	 * @param int x new location
	 * @param int y new location
	 */
	public void move(int newX, int newY);
}
