package a4;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public interface ICollection {
	public void add(GameObject newObject);
	public void remove(GameObject object);
	public IIterator getIterator();
}
