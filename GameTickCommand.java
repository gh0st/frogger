package a2;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
/** Hop the target west. */
public class GameTickCommand extends AbstractAction {
	private GameWorld gw;
	public GameTickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		// code here to perform the actual "tick" operation...
		System.out.println("Tick requested from "+e.getActionCommand()+" "+e.getSource().getClass());
		gw.displayCollection();
		System.out.println("Now...TICK!");
		try {
			IIterator theElements = gw.froggerIterator;
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof MovingGameObject) {
					froggerObject.tick();
				}
			}
			/*for (GameObject x: gw.myGameObjects) {
				//if (x instanceof MovingGameObject || x instanceof Turtle) {
				if (x instanceof MovingGameObject) {
					System.out.println(x.toString());
					x.tick();
				}
			}*/
			gw.displayCollection();
		} catch (NullPointerException f) {
			//System.out.println("Error...");
			//System.out.println(f.toString());
		}
		int gt = gw.getGameTime(); gt++;
		gw.setGameTime(gt);
		gw.notifyObservers();
		
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
