package frogger;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public interface IHoppable {
	/**
	 * Provides objects the ability to hop in a specified direction.
	 * @param Direction dir is the direction in which the object will hop ie NORTH/SOUTH/EAST/WEST
	 */
	public void hop(int dir);
}
