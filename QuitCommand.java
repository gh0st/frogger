package a4;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
/** Prompts the user whether or not they want to quit before exiting the game. */
public class QuitCommand extends AbstractAction {
	public QuitCommand() {
		super("Quit");
	}
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(
			null,
			"Are you sure you want to quit?",
			"Quit",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		return;
	}
}
