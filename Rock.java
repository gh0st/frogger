package a2;
/**
 * Rocks are fixed objects which have an attribute size; for this assignment there
 * are just two sizes (small [1] and large [2]).
 */
public class Rock extends FixedGameObject {
	private int size;
	// constructs a new rock with a randomly chosen size and a
	// randomly chosen location along the dirt river bank
	public Rock() {
		setLocation((50 + (int)(Math.random() * ((950-50)+1))), 55);
		setColor(128,128,128);
		setSize((1 + (int)(Math.random() * ((2-1)+1))));
	}
	/**
	 * Gets the size of the Rock
	 * @return int the size of the rock either small (1) or large (2)
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Sets the size of the rock
	 * @param int size of the rock just small (1) and large (2)
	 */
	public void setSize(int newSize) {
		size = newSize;
	}
	public String toString() {
		return "Rock: "+getLocation()+" "+getColor()+" size="+getSize();
	}
}
