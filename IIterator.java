package a2;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
	public void remove(GameObject theObject);
	public void reset();
}
