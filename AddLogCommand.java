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
/** Add a log to the target. */
public class AddLogCommand extends AbstractAction {
	private GameWorld gw;
	public AddLogCommand() {
		super("Add Log");
	}
	public void actionPerformed(ActionEvent e) {
		Log l = new Log();
		gw.addGameObject(l);
		System.out.println("AddLog requested from "+e.getActionCommand()+" "+e.getSource().getClass());
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
