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
public class HopFullLilyPadCommand extends AbstractAction {
	private GameWorld gw;
	public HopFullLilyPadCommand() {
		super("7-Hop Full LilyPad");
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("From hopped onto Full LilyPad...");
		// remove the from from the gameworld objects
		try {
			IIterator theElements = gw.froggerIterator;
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
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
