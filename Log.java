package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
public class Log extends Floaters implements IDrawable, ICollider, ISelectable {
	private int length;
	private boolean occupied;
	private boolean selected;
	private Frog occupant;
	public Log() {
		setLocation(-5, getRandom(300, 450));
		super.setColor(165,42,42);
		setSpeed();
		setDirection(getY());
		setLength(getRandom(1,2));
		setSize(length*50, 30);
		setBouy(getRandom(1,6));
	}
	public Log(int y) {
		setLocation(-5, y);
		super.setColor(165,42,42);
		setSpeed();
		setDirection(getY());
		setLength(getRandom(1,2));
		setSize(length*50, 30);
		setBouy(getRandom(1,6));
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getColor());
		g2d.fillRect(getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
		if (getOccupied() == true) {
			g2d.setColor(getColor());
			g2d.fillRect(getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
			getOccupant().draw(g);
		}
	}
	public int getLength() {
		return length;
	}
	public void setLength(int newLength) {
		length = newLength;
	}
	public void setColor(int r, int g, int b) {}
	public String toString() {
		return "Log: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" bouy="+getBouy()+" length="+getLength();
	}
	public boolean getOccupied() {
		return occupied;
	}
	public void setOccupied(boolean o) {
		occupied = o;
	}
	public Frog getOccupant() {
		return occupant;
	}
	public void setOccupant(Frog f) {
		occupant = f;
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
	public void setSelected(boolean b) {
		selected = b;
		if (b) {
			super.setColor(0,255,0);
		} else {
			super.setColor(165,42,42);
		}
	}
	public boolean isSelected() {
		if (selected) {
			return true;
		} else {
			return false;
		}
	}
	public boolean contains(Point p) {
		int px = (int)p.getX(); // mouse x location
		int py = (int)p.getY(); // mouse y location
		int xLoc = getX(); // shape x location
		int yLoc = getY(); // shape y location
		if ((px >= xLoc-(getWidth()/2)) && (px <= xLoc+(getWidth()/2)) && (py >= yLoc-(getHeight()/2)) && (py <= yLoc+(getHeight()/2))) {
			return true; 
		} else {
			return false;
		}
	}
}
