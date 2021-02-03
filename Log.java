package a2;
/**
 * Logs are floating objects which have a length attribute, which will be either
 * "short" or "long". Logs start with a random positive bouyancy which never
 * changes.
 */
public class Log extends Floaters {
	private int length;
	public Log() {
		setBouy((1+(int)(Math.random() * ((6-1)+1))));
		setColor(0,0,0);
		setDirection(Direction.EAST);
		setLength((1 + (int)(Math.random() * ((2-1)+1))));
		setLocation((50 + (int)(Math.random() * ((950-50)+1))), (65 + (int)(Math.random() * ((85-65)+1))));
	}
	public int getLength() {
		return length;
	}
	public void setLength(int newLength) {
		length = newLength;
	}
	public String toString() {
		return "Log: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" bouy="+getBouy()+" length="+getLength();
	}
}
