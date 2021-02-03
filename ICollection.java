package a2;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
public interface ICollection {
	public void add(GameObject newObject);
	public void remove(GameObject object);
	public IIterator getIterator();
}
