package a1;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 1
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.1 (September 25, 2012)
 */
public interface IHoppable {
	/**
	 * Provides objects the ability to hop in a specified direction.
	 * @param Direction dir is the direction in which the object will hop ie NORTH/SOUTH/EAST/WEST
	 */
	public void hop(GameWorld.Direction dir);
}
