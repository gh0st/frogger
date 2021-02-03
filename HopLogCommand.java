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
public class HopLogCommand extends AbstractAction {
	private GameWorld gw;
	public HopLogCommand() {
		super("4-Hop onto Log");
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("Frog hopped onto Log...");
		// set the frogs location, speed, direction to match that of the log
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
