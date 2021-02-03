package a2;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
/** Displays a JOptionPane giving name, course, and version of program. */
public class AboutCommand extends AbstractAction {
	public AboutCommand() {
		super("About");
	}
	public void actionPerformed(ActionEvent e) {		
		System.out.println("About requested from "+e.getActionCommand()+" "+e.getSource().getClass());
		JOptionPane.showMessageDialog(
			null,
			"Chad Hollman\nCSC133 - Object Oriented Computer Graphics\nDr. John Clevenger\nVersion 0.2 (October 16, 2012)",
			"About",
			JOptionPane.INFORMATION_MESSAGE
		);
	}
}
