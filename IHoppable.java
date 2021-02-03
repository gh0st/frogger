package a2;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
public interface IHoppable {
	/**
	 * Provides objects the ability to hop in a specified direction.
	 * @param Direction dir is the direction in which the object will hop ie NORTH/SOUTH/EAST/WEST
	 */
	public void hop(int dir);
}
