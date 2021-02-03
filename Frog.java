package a2;
/** Add a <b>f</b>rog to the world at a randomly-chosen X location along the bottom (grass) */
public class Frog extends MovingGameObject implements IHoppable, IMovable {
	public Frog() {
		setColor(0,128,0);
		setLocation(450, 5);
	}
	/*public synchronized Frog getFrog() {
		if (theFrog == null)
			theFrog = new Frog();
		return theFrog;
	}*/
	/**
	 * Kill frog method will only work if there is an active frog to be killed
	 */
	public void killFrog(IGameWorld o) {
		if (o instanceof GameWorld) {
			GameWorld gw = (GameWorld)o;
			int currentLives = gw.getFrogLives();
			gw.setFrogLives(currentLives--);
			if (gw.getFrogLives() == -1) {
				System.out.println("You're out of lives. Game over... =[");
			}
		}
	}
	/**
	 * Move frog to given location.
	 * @param int newX the new x position
	 * @param int newY the new y position
	 */
	public void move(int newX, int newY) {
		setLocation(newX, newY);
	}
	/**
	 * The frog hops given a specified direction a fixed number of spaces which
	 * implements move()
	 * @param Direction dir either NORTH, SOUTH, EAST, WEST
	 */
	public void hop(int dir) {
		System.out.println("Hop has been called...");
		switch(dir) {
			case 1: move(getX(), getY()+10); break;
			case 2: move(getX(), getY()-10); break;
			case 3: move(getX()+100, getY()); break;
			case 4: move(getX()-100, getY()); break;
		}
	}
	/**
	 * This function is used when a frog hops onto a floating object such as a log
	 * or a turtle. The effect is that the frogs speed and direction now reflect
	 * the object on which it hopped onto.
	 * @param int newSpeed is the new speed of the frog
	 * @param int newDir is the new direction of the frog
	 */
	public void matchFloater(int newSpeed, Direction newDir) {
		System.out.println("Inside matchFloater() with params: newSpeed="+newSpeed+" newDir"+newDir);
		setSpeed(newSpeed);
		setDirection(newDir);
	}
	/**
	 * This function is used when a frog hops onto a lilypad. The effect is the
	 * frogs location is now set to that of the lilypad. The lilypad is set to
	 * occupied and a new frog will be created along the bottom row.
	 * @param LilyPad lpad is the lilypad object of which the frog has jumped on
	 * @return boolean true or false regarding whether or not the lilypad is
	 * occupied.
	 */
	public boolean matchLilyPad(LilyPad lpad) {			
		if (lpad.getOccupied() == false) {
			setLocation(lpad.getX(), lpad.getY());
			setSpeed(0);
			System.out.println("The frog is on an empty lilypad!");
			return false;
		} else {
			System.out.println("The frog is now on an occupied lilypad!");
			System.out.println("Here we would initiate killFrog() but we'll do that later.");
			return true;
		}
	}
	public String toString() {
		return "Frog: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection();
	}
}
