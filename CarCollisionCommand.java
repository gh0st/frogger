package a4;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public class CarCollisionCommand extends AbstractAction {
	private GameWorld gw;
	public CarCollisionCommand() {
		super("1-Car Collision");
	}
	public void actionPerformed(ActionEvent e) {
		try {
			IIterator theElements = gw.getIterator();
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof Frog) {
					theElements.remove(froggerObject);
				}
			}
			theElements.reset();
		} catch (NullPointerException f) {
			System.out.println(f);
		}
		// decrease our frog lives by one
		gw.setFrogLives((gw.getFrogLives())-1);
		System.out.println("Car killed frog...");
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
