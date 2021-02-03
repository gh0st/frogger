package a2;
/** LilyPad */
public class LilyPad extends FixedGameObject {
	private int height;
	private boolean occupied;
	public LilyPad(int newX, int newY, int newHeight, boolean newOccupied) {
		setLocation(newX, newY);
		setHeight(newHeight);
		setOccupied(newOccupied);
	}
	/**
	 * Gets the height of the LilyPad
	 * @return the height of the LilyPad
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Sets the height of the LilyPad
	 * @param newHeight the new height of the LilyPad
	 */
	public void setHeight(int newHeight) {
		height = newHeight;
	}
	/**
	 * Gets whether or not the lilypad is occupied or not.
	 * @return boolean true or false
	 */
	public boolean getOccupied() {
		return occupied;
	}
	/**
	 * Sets whether or not the lilypad is occupied or not.
	 * @param boolean true/false true of the lilypad is occupied, false if not
	 */
	public void setOccupied (boolean occ) {
		occupied = occ;
	}
	/**
	 * returns a description of LilyPad
	 * @return string fetching it's location and objColor
	 */
	public String toString() {
		return "LilyPad: "+getLocation()+" "+getColor()+" height="+getHeight()+" occupied="+getOccupied();
	}
}
