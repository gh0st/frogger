package frogger;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class LilyPad extends FixedGameObject implements IDrawable, ICollider {
	private int height;
	private boolean occupied;
	/**
	 * Constructor for LilyPad
	 */
	public LilyPad(int x, int y, int h, boolean o) {
		setLocation(x, y);
		setLHeight(h);
		setOccupied(o);
		setColor(124,124,124);
		setSize(80, 30);
	}
	/**
	 * Gets the height of the LilyPad
	 * @return the height of the LilyPad
	 */
	public int getLHeight() {
		return height;
	}
	/**
	 * Sets the height of the LilyPad
	 * @param h the new height of the LilyPad
	 */
	public void setLHeight(int h) {
		height = h;
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
	 * @param o true/false true of the lilypad is occupied, false if not
	 */
	public void setOccupied (boolean o) {
		occupied = o;
	}
	/**
	 * returns a description of LilyPad
	 * @return string fetching it's location and objColor
	 */
	public String toString() {
		return "LilyPad: "+getLocation()+" "+getColor()+" height="+getLHeight()+" occupied="+getOccupied();
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.drawOval(getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
	}
	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		GameObject g = (GameObject)obj; // cast obj as a gameObject
		// find the outside sides of the object
		int thisLeftSide = getX()-(getWidth()/2);
		int thisRightSide = getX()+(getWidth()/2);
		int thisTopSide = getY()-(getHeight()/2);
		int thisBottomSide = getY()+(getHeight()/2);
		// find the outside sides of the other object
		int otherLeftSide = g.getX()-(g.getWidth()/2);
		int otherRightSide = g.getX()+(g.getWidth()/2);
		int otherTopSide = g.getY()-(g.getHeight()/2);
		int otherBottomSide = g.getY()+(g.getHeight()/2);
		if (thisRightSide < otherLeftSide || thisLeftSide > otherRightSide || otherTopSide < thisBottomSide || thisTopSide < otherBottomSide) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	public void handleCollision(ICollider obj) {
		setOccupied(true);
		/*if (obj instanceof Frog) {
			Frog f = (Frog)obj;
			setOccupant(f);
		}*/
	}
}
