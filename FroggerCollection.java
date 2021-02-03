package a3;
import java.util.LinkedList;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 3
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.3 (November 09, 2012)
 */
public class FroggerCollection implements ICollection {
	private LinkedList<GameObject> theCollection;
	public FroggerCollection() {
		theCollection = new LinkedList<GameObject>();
	}
	public void add(GameObject newObject) {
		theCollection.add(newObject);
	}
	public void remove(GameObject theObject) {
		theCollection.remove(theObject);
	}
	public IIterator getIterator() {
		return new FroggerListIterator();
	}
	
	private class FroggerListIterator implements IIterator {
		private int currElementIndex;
		public FroggerListIterator() {
			currElementIndex = -1;
		}
		public boolean hasNext() {	
			if (theCollection.size() <= 0) return false;
			if (currElementIndex == theCollection.size() -1) return false;
			return true;
		}
		public GameObject getNext() {
			currElementIndex++;
			return(theCollection.get(currElementIndex));
		}
		public void remove(GameObject theObject) {
			theCollection.remove(theObject);
		}
		public void reset() {
			currElementIndex = -1;
		}
	}
}
