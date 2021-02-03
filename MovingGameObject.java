package a2;
/** This class is the abstract superclass which all moving objects are inhereted from */
abstract class MovingGameObject extends GameObject {
	private int speed;
	public enum Direction {NORTH, SOUTH, EAST, WEST}
	private Direction dir;
	/**
	 * Moves the object to the specified x- and y-coordinates by calling the
	 * superclass' setLocation()
	 * @param x new x-coordinate which the object will move to
	 * @param y new y-coodrinate which the object will move to
	 */
	public void move(int x, int y) {
		setLocation(x, y);
	}
	/** Get the direction of the moving object */
	public Direction getDirection() {
		return dir;
	}
	/** Set the direction of the moving object */
	public void setDirection(Direction newDir) {
		dir = newDir;
	}
	/** Get the speed of the moving object */
	public int getSpeed() {
		return speed;
	}
	/** Set the speed of the moving object */
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	/**
	 * Implemented in gameTick()
	 */
	public void tick() {
		// update positions of all moveable objects according to their speed and heading
		// reduce all turtle's bouyancy by one
		// any turtle with a new bouyancy less than four change color
		// any turtle with a new bouyancy of zero "dive" and are removed from the game
		switch (getDirection()) {
			case EAST: move((getX()+(getSpeed()*100)), getY()); break;
			case WEST: move((getX()-(getSpeed()*100)), getY()); break;
		}
	}
}
