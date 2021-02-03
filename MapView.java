package a2;
import javax.swing.*;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 * This is the "controller" of the MVC architecture
 */
public class MapView extends JPanel implements IObserver {
	public MapView() {}
	public MapView(IObservable obs) {}
	public void update(IObservable o, Object arg) {
		// code here to output current map information (based on the data in the Observable)
		// to the console. Note that the received "Observable" is a GameWorld PROXY and can
		// be cast to type IGameWorld in order to access the GaeWorld methods in it.
		
	}
}
