package a2;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
/** Hop the target south. */
public class HopSouthCommand extends AbstractAction {
	private GameWorld gw;
	public HopSouthCommand() {
		super("Hop South");
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("HopSouth requested from "+e.getActionCommand()+" "+e.getSource().getClass());
		try {
			IIterator theElements = gw.froggerIterator;
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof Frog) {
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
