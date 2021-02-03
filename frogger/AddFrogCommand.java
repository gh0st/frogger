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
/** Add a frog to the target. */
public class AddFrogCommand extends AbstractAction {
	private GameWorld gw;
	public AddFrogCommand() {
		super("AddFrog");
	}
	public AddFrogCommand(GameWorld gw) {
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		Frog f = new Frog(gw);
		gw.addGameObject(f);
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
