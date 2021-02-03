package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
public class Turtle extends Floaters implements IDrawable, ICollider, ISelectable {
	private int size;
	private GameWorld gw;
	private boolean selected;
	public Turtle() {
		setLocation(-5,getRandom(300,450));
		setColor(255,0,255);
		setSpeed();
		setDirection(getY());
		setBouy(getRandom(5));
		setSize(getRandom(2));
		setSize(30, 30);
	}
	public Turtle(int y) {
		setLocation(-5, y);
		setColor(255,0,255);
		setSpeed();
		setDirection(getY());
		setBouy(getRandom(5));
		setSize(getRandom(2));
		setSize(30, 30);
	}
	public int getSize() {
		return size;
	}
	public void setSize(int s) {
		size = s;
	}
	public String toString() {
		return "Turtle: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+"degrees bouy="+getBouy()+" size="+getSize();
	}
	/**
	 * Any turtle with a new bouyancy less than 4 change color
	 * any turtle with a new bouyancy of zero "dives" and is removed from the
	 * game.
	 */
	public void tick(int time) {
		if (time % 60 == 0)
			move(time);
		if (getX() > 1010 || getX() < -10) {
			setRemoveFlag(true);
		}
		setBouy(getBouy()-0.005); // reduce all turtle's bouyancy by one
		if (getBouy() < 4) setColor(0,0,245);
		if (getBouy() < 0) {
			setRemoveFlag(true);
		}
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getColor());
		g2d.fillOval(getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
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
			f.matchFloater(getSpeed(), getDirection());
		}*/
	}
	public void setSelected(boolean b) {
		selected = b;
		if (b) {
			setColor(0,255,0);
		} else {
			setColor(Color.BLUE);
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
