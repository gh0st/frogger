package a2;
/**
 * Car
 */
public class Car extends Vehicles {
	private int passengerCount;
	public Car(GameWorld gw) {
		setColor(255,0,0);
		setDirection(Direction.EAST);
		setLocation((50 + (int)(Math.random() * ((950-50)+1))), (15 + (int)(Math.random() * ((45-15)+1))));
		setPassengerCount((1+(int)(Math.random()*((8-1)+1))));
		setSpeed(1);
		setVIN(gw.getVehicleVIN(), gw);
	}
	/**
	 * Get the number of passengers in car. I think this really returns the size
	 * of the car, like the length...
	 * @return int passengerCount
	 */
	public int getPassengerCount() {
		return passengerCount;
	}
	/** Set the number of passengers in the car.
	 * @param int newPassenger count
	 */
	public void setPassengerCount(int newPassengerCount) {
		passengerCount = newPassengerCount;
	}
	public String toString() {
		return "Car: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+" passengerCount="+getPassengerCount()+" vin="+getVIN();
	}
}
