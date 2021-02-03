package a2;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
public interface IMovable {
	/**
	 * Gets the location of the given object.
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
