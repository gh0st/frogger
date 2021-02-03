package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.geom.AffineTransform;

public class Truck extends Vehicles implements IDrawable, ICollider, ISelectable {
	private int length;
	private boolean selected;
	private GameWorld gw;
	private AffineTransform myTranslation, myRotation, myScale;
	/**
	 * Default constructor must be passed the GameWorld.
	 * @param gw the gameworld which will hold the truck so that it has access to
	 * the global vin.
	 */
	public Truck(GameWorld gw) {
		myTranslation = new AffineTransform();
		myRotation = new AffineTransform();
		myScale = new AffineTransform();
		int y = getRandom(50,250);
		int x = -25;
		if (y >= 200 && y < 250) {
			x = 1025; // start the car on the right hand side of the screen
		} else if (y >= 150 && y < 200) {
			x = -5; // start the car on the left hand side of the screen
		} else if (y >= 100 && y < 150) {
			x = 1025; // start the car on the right hand side of the screen
		} else if (y >= 50 && y < 100) {
			x = -5; // start the car on the left hand side of the screen
		}
		setLocation(x,y);
		myTranslation.translate(x,y);
		setColor(Color.RED);
		setSpeed();
		setDirection(getY());
		setLength(getRandom(1,2));
		setSize(50*length, 30);
		setVIN(gw.getVehicleVIN(), gw);
		this.gw = gw;
	}
	public Truck(GameWorld gw, int y) {
		myTranslation = new AffineTransform();
		myRotation = new AffineTransform();
		myScale = new AffineTransform();
		int x = -25;
		if (y >= 200 && y < 250) {
			x = 1025; // start the car on the right hand side of the screen
		} else if (y >= 150 && y < 200) {
			x = -25; // start the car on the left hand side of the screen
		} else if (y >= 100 && y < 150) {
			x = 1025; // start the car on the right hand side of the screen
		} else if (y >= 50 && y < 100) {
			x = -5; // start the car on the left hand side of the screen
		}
		setLocation(x,y);
		myTranslation.translate(x,y);
		setColor(Color.RED);
		setSpeed();
		setDirection(getY());
		setLength(getRandom(1,2));
		setSize(50*length, 30);
		setVIN(gw.getVehicleVIN(), gw);
		this.gw = gw;
	}
	public void draw(Graphics2D g2d) {
		/* removing this in favor of affinetransforms
		g2d.setColor(getColor());
		g2d.drawRect(getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());*/
		
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(myTranslation);
		g2d.setColor(getColor());
		g2d.drawRect(0,0,getWidth(),getHeight());
		
		g2d.setTransform(saveAT);
		return;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int newLength) {
		length = newLength;
	}
	public String toString() {
		return "Truck: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" length="+getLength()+" vin="+getVIN();
	}
	public void move(int elapsedMillisecs) {
		int currX = getX();
		int currY = getY();
		int dist = getSpeed();
		int dx = (int)Math.cos(Math.toRadians(getDirection()))*dist;
		int dy = (int)Math.sin(Math.toRadians(getDirection()))*dist;
		setLocation(currX+dy, currY+dx);
		translate(dy,dx);
	}
	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		GameObject g = (GameObject)obj; // cast obj as a gameObject
		// find the outside sides of the object
		int thisLeftSide = getX()-(getWidth()/2); // the left side of the bounding square
		int thisRightSide = getX()+(getWidth()/2); // the right side of the bounding square
		int thisTopSide = getY()-(getHeight()/2); // the top side of the bounding square
		int thisBottomSide = getY()+(getHeight()/2); // the bottom side of the bounding square
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
		//setRemoveFlag(true);
	}
	public void setSelected(boolean b) {
		selected = b;
		if (b) {
			setColor(0,255,0);
		} else {
			setColor(255,0,0);
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
	public void rotate(double degrees) {
		myRotation.rotate(Math.toRadians(degrees));
	}
	public void scale(double sx, double sy) {
		myScale.scale(sx, sy);
	}
	public void translate(double dx, double dy) {
		myTranslation.translate(dx, dy);
	}
}
