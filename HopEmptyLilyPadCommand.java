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
public class HopEmptyLilyPadCommand extends AbstractAction {
	private GameWorld gw;
	public HopEmptyLilyPadCommand() {
		super("6-Hop Empty Lilypad");
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("Frog hopped onto Empty Lilypad...");
		// frog made it across safely
		// remove the old frog and create a new frog at the bottom of the world
		// remove the from from the gameworld objects
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
		// create a new frog
		Frog f = new Frog(gw);
		// add the frog to the gameobjects collection.
		gw.addGameObject(f);
		// increase game score
		gw.setGameScore((gw.getGameScore())+100);
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
