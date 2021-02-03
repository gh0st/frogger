package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
public class Car extends Vehicles implements IDrawable, ICollider, ISelectable {
	private int passengerCount;
	private boolean selected;
	/**
	 * Default constructor must be passed the GameWorld.
	 * @param gw the gameworld which will hold the car so that it has access to
	 * the global vin.
	 */
	public Car(GameWorld gw) {
		int y = getRandom(50,250);
		int x = -5;
		if (y >= 200 && y < 250) {
			x = 1005; // start the car on the right hand side of the screen
		} else if (y >= 150 && y < 200) {
			x = -5; // start the car on the left hand side of the screen
		} else if (y >= 100 && y < 150) {
			x = 10005; // start the car on the right hand side of the screen
		} else if (y >= 50 && y < 100) {
			x = -5; // start the car on the left hand side of the screen
		}
		setLocation(x, y);
		setColor(Color.WHITE);
		setSpeed();
		setDirection(getY());
		setSize(30, 30);
		setPassengerCount(getRandom(1,8));
		setVIN(gw.getVehicleVIN(), gw);
	}
	public Car(GameWorld gw, int y) {
		int x = -25;
		if (y >= 200 && y < 250) {
			x = 1025; // start the car on the right hand side of the screen
		} else if (y >= 150 && y < 200) {
			x = -25; // start the car on the left hand side of the screen
		} else if (y >= 100 && y < 150) {
			x = 1025; // start the car on the right hand side of the screen
		} else if (y >= 50 && y < 100) {
			x = -25; // start the car on the left hand side of the screen
		}
		setLocation(x, y);
		setColor(Color.WHITE);
		setSpeed();
		setDirection(getY());
		setSize(30, 30);
		setPassengerCount(getRandom(1,8));
		setVIN(gw.getVehicleVIN(), gw);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getColor());
		g2d.drawRect(getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
	}
	/**
	 * Get the number of passengers in car.
	 * @return int passengerCount
	 */
	public int getPassengerCount() {
		return passengerCount;
	}
	/** 
	 * Set the number of passengers in the car.
	 * @param int newPassenger count
	 */
	public void setPassengerCount(int newPassengerCount) {
		passengerCount = newPassengerCount;
	}
	public String toString() {
		return "Car: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" passengerCount="+getPassengerCount()+" vin="+getVIN();
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
		//setRemoveFlag(true);
	}
	public void setSelected(boolean b) {
		selected = b;
		if (b) {
			setColor(0,255,0);
		} else {
			setColor(Color.WHITE);
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
