package a4;
/**
 * Vehicles have a unique VIN; this is a positive integer which is different for
 * every vehicle in the game. For simplicity the first vehicle gets VIN 1, then
 * each new vehicle after that gets the next available integer. Car vehicles 
 * alsohave an integer attribute passengerCount specifying how many passengers 
 * the car can carry, while Trucks have an attribute length specifying the
 * length of the truck.
 */
public class Vehicles extends MovingGameObject implements IMovable {
	private int vin;
	/**
	 * Get the vin of the vehicle
	 * @return int vin of the vehicle
	 */
	public int getVIN() {
		return vin;
	}
	/**
	 * Set the vin of the vehicle based off the global vin so that all vehicles
	 * have a consistent vin
	 * @param int vin of new vehicle
	 */
	public void setVIN(int newVin, GameWorld gw) {
		vin = newVin;
		// add one to the global vin
		int tempVIN = gw.getVehicleVIN();
		tempVIN++;
		gw.setVehicleVIN(tempVIN);
		//GameWorld.globalVIN++;
	}
	/**
	 * setColor must be overridden so that you cannot change the color once it's
	 * been created
	 */
	public void setColor() {}
}
