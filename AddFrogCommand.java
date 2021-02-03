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
/** Add a frog to the target. */
public class AddFrogCommand extends AbstractAction {
	private GameWorld gw;
	public AddFrogCommand() {
		super("AddFrog");
	}
	public void actionPerformed(ActionEvent e) {
		Frog f = new Frog();
		gw.addGameObject(f);
		System.out.println(f);
		System.out.println("AddFrog requested from "+e.getActionCommand()+" "+e.getSource().getClass());
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
