package a2;
/**
 * Turtles are loating objects which have a size attribute, which will be either
 * "large" or "small". Turtles have a bouyancy which decreases over time (because
 * the turtles eventually dive below the surface). Turtles can change color, which
 * they do when they are about to dive.
 */
public class Turtle extends Floaters {
	private int size;
	public Turtle() {
		setBouy((1+(int)(Math.random() * ((6-1)+1))));
		setColor(255,0,255);
		setDirection(Direction.WEST);
		setSize((1 + (int)(Math.random() * ((2-1)+1))));
		setSpeed(1);
		setLocation((50 + (int)(Math.random() * ((950-50)+1))), (65 + (int)(Math.random() * ((85-65)+1))));
	}
	public int getSize() {
		return size;
	}
	public void setSize(int newSize) {
		size = newSize;
	}
	public String toString() {
		return "Turtle: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" bouy="+getBouy()+" size="+getSize();
	}
	public void tick() {
		// update positions of all moveable objects according to their speed and heading
		// any turtle with a new bouyancy less than four change color
		// any turtle with a new bouyancy of zero "dive" and are removed from the game
		switch (getDirection()) {
			case EAST: move((getX()+(getSpeed()*100)), getY()); break;
			case WEST: move((getX()-(getSpeed()*100)), getY()); break;
		}
		//int tempBouy = getBouy() - 1;
		setBouy(getBouy() - 1); // reduce all turtle's bouyancy by one
		if (getBouy() < 4) setColor(0,0,245);
		if (getBouy() == 0) System.out.println("Kill the turtle!!");
	}
}
