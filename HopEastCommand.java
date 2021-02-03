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
/** Hop the target east. */
public class HopEastCommand extends AbstractAction {
	private GameWorld gw;
	public HopEastCommand() {
		super("Hop East");
	}
	public HopEastCommand(GameWorld gw) {
		super("Hop East");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		//System.out.println("HopEast requested from "+e.getActionCommand()+" "+e.getSource().getClass());
		try {
			IIterator theElements = gw.getIterator();
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof Frog) {
					if (((Frog)froggerObject).getActive())
						((Frog)froggerObject).hop(3);
				}
			}
			theElements.reset();
		} catch (NullPointerException f) {
			System.out.println(f);
		}
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
