package a3;
import java.lang.Math;
/** 
 * This class is the abstract superclass which all moving objects are inhereted
 * from. 
 */
public abstract class MovingGameObject extends GameObject {
	private int speed;
	//private Direction dir;
	private int headingInDegrees;
	//private int speedInUnitsPerSec;
	//protected enum Direction {NORTH, SOUTH, EAST, WEST}
	/**
	 * Moves the object to the specified x- and y-coordinates by calling the
	 * setLocation()
	 * @param x new x-coordinate which the object will move to
	 * @param y new y-coodrinate which the object will move to
	 */
	public void move(int x, int y) {
		setLocation(x, y);
	}
	/**
	 * Moves the object based on it's speed and direction. We'll take its current
	 * location and then call setLocation passing its new location based on our
	 * calculations. I'm actually not using elapsed time because it's making it
	 * a lot more difficult to work it into the equations.
	 * @param t which is the elapsed time
	 */
	public void move(int elapsedMillisecs) {
		int currX = getX();
		int currY = getY();
		//int dist = speed * (elapsedMillisecs/8000);
		int dist = speed;
		int dx = (int)Math.cos(Math.toRadians(headingInDegrees))*dist;
		int dy = (int)Math.sin(Math.toRadians(headingInDegrees))*dist;
		setLocation(currX+dy, currY+dx);
	}
	/** Get the direction of the moving object */
	/*public Direction getDirection() {
		return dir;
	}*/
	public int getDirection() {
		return headingInDegrees;
	}
	/** 
	 * Set the direction of the moving object
	 * If y is (200, 250) then travelling west,
	 * if y is (150, 200) then travelling east,
	 * if y is (100, 150) then travelling west,
	 * if y is (50,100) then travelling east.
	 */
	public void setDirection(int y) {
		if (y >= 200 && y < 250) {
			headingInDegrees = 270;
		} else if (y >= 150 && y < 200) {
			headingInDegrees = 90;
		} else if (y >= 100 && y < 150) {
			headingInDegrees = 270;
		} else if (y >= 50 && y < 100) {
			headingInDegrees = 90;
		} else if (y >= 300 && y < 450) {
			headingInDegrees = 90;
		}
	}
	/**
	 * Set the direction of the moving object. Instead of automatically setting
	 * the direction of the object based on it's y-coordinate, set it manually
	 * if the second parameter is true.
	 */
	public void setDirection(int d, boolean auto) {
		if (auto == false) {
			setDirection(d);
		} else if (auto == true) {
			headingInDegrees = d;
		}
	}
	/** Get the speed of the moving object */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Set the speed of the moving object based on the y coordinate of the object.
	 * If y is (400, 450) then speed = 2,
	 * if y is (350, 400) then speed = 1,
	 * if y is (300, 350) then speed = 3,
	 * if y is (200, 250) then speed = 3,
	 * if y is (150, 200) then speed = 2,
	 * if y is (100, 150) then speed = 1,
	 * if y is (50,100) then speed = 3.
	 */
	public void setSpeed() {
		int y = getY();
		if (y >= 400 && y < 450) {
			speed = 2;
		} else if (y >= 350 && y < 400) {
			speed = 1;
		} else if (y >= 300 && y < 350) {
			speed = 3;
		} else if (y >= 200 && y < 250) {
			speed = 3;
		} else if (y >= 150 && y < 200) {
			speed = 2;
		} else if (y >= 100 && y < 150) {
			speed = 1;
		} else if (y >= 50 && y < 100) {
			speed = 3;
		}
	}
	/**
	 * Set the speed of the object manually.
	 * @param s to be the speed of the object
	 */
	public void setSpeed(int s) {
		speed = s;
	}
	/**
	 * Update positions of all movable objects according to their speed and heading
	 */
	public void tick(int time) {
		//if (time % 60 == 0) {
		//if (time % 120 == 0)
		move(time);
		if (getX() > 1050 || getX() < -50 || getY() < -50 || getY() > 550) {
			setRemoveFlag(true);
		}
	}
}
