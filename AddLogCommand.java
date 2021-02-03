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
		System.out.println(l);
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
