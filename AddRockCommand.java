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
/** Add a rock to the target. */
public class AddRockCommand extends AbstractAction {
	private GameWorld gw;
	public AddRockCommand() {
		super("Add Rock");
	}
	public void actionPerformed(ActionEvent e) {
		Rock r = new Rock();
		gw.addGameObject(r);
		System.out.println("AddRock requested from "+e.getActionCommand()+" "+e.getSource().getClass());
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
