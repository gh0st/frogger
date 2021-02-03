package a3;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 3
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.3 (November 09, 2012)
 */
/** Hop the target north. */
public class HopNorthCommand extends AbstractAction {
	private GameWorld gw;
	public HopNorthCommand() {
		super("Hop North");
	}
	public HopNorthCommand(GameWorld gw) {
		super("Hop North");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			IIterator theElements = gw.getIterator();
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof Frog) {
					if (((Frog)froggerObject).getActive())
						((Frog)froggerObject).hop(1);
				}
			}
			theElements.reset();
		} catch (NullPointerException f) {
			System.out.println(f);
		}
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
