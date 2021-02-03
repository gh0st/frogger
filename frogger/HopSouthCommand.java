package frogger;
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
/** Hop the target south. */
public class HopSouthCommand extends AbstractAction {
	private GameWorld gw;
	public HopSouthCommand() {
		super("Hop South");
	}
	public HopSouthCommand(GameWorld gw) {
		super("Hop South");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			IIterator theElements = gw.getIterator();
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof Frog) {
					if (((Frog)froggerObject).getActive())
						((Frog)froggerObject).hop(2);
				}
			}
			theElements.reset();
		} catch (NullPointerException f) {
			System.out.println(f);
		}
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
