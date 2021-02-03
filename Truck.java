package a2;
/**
 * Truck
 */
public class Truck extends Vehicles {
	private int length;
	public Truck(GameWorld gw) {
		setColor(0,255,0);
		setDirection(Direction.WEST);
		setLength((1+(int)(Math.random()*((2-1)+1))));
		setLocation((50 + (int)(Math.random() * ((950-50)+1))), (15 + (int)(Math.random() * ((45-15)+1))));
		setSpeed(2);
		setVIN(gw.getVehicleVIN(), gw);
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
}
