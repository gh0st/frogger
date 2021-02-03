package frogger;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Rock extends FixedGameObject implements IDrawable, ICollider {
	private int size;
	public Rock() {
		setLocation(getRandom(1000), 275);
		setColor(Color.DARK_GRAY);
		setSize(getRandom(1));
	}
	public Rock(int x, int y, int size) {
		setLocation(x, y);
		setColor(Color.DARK_GRAY);
		setSize(size);
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
	public void setColor(int r, int g, int b) {}
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillRect((getX()-25), (getY()-15), 30, 30);
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
		/*if (obj instanceof Frog) {
			Frog f = (Frog)obj;
			setOccupied(true);
			setOccupant(f);
		}*/
	}
}
