package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Bird extends MovingGameObject implements IDrawable, ICollider {
	private int range;
	public Bird() {
		setLocation(getRandom(100,900), getRandom(425,475));
		setColor(Color.red);
		setSpeed(1);
		setDirection(getRandom(90, 270, true), true);
		setRange(5);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(getColor());
		g2d.drawOval((getX()-15), (getY()-15), 30, 30);
	}
	public int getRange() {
		return range;
	}
	public void setRange(int r) {
		range = r;
	}
	public void tick(int time) {
		if (time % 60 == 0)
			move(time);
		if (getX() > 999 || getX() < 1 || getY() < 1 || getY() > 499) {
			setRemoveFlag(true);
		}
	}
	public String toString() {
		return "Bird: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+"degrees";
	}
	public void move(int elapsedMillisecs) {
		int currX = getX();
		int currY = getY();
		int dist = getSpeed();
		double dx = Math.cos(Math.toRadians(getDirection()))*dist;
		double dy = Math.sin(Math.toRadians(getDirection()))*dist;
		setLocation(currX+dy, currY+dx);
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
