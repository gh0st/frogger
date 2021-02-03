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
/** Add a turtle to the target. */
public class AddTurtleCommand extends AbstractAction {
	private GameWorld gw;
	public AddTurtleCommand() {
		super("Add Turtle");
	}
	public void actionPerformed(ActionEvent e) {
		Turtle t = new Turtle();
		gw.addGameObject(t);
		System.out.println("AddTurtle requested from "+e.getActionCommand()+" "+e.getSource().getClass());
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
