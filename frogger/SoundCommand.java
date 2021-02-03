package frogger;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
/** Pause the game. */
public class SoundCommand extends AbstractAction {
	private GameWorld gw;
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("Sound requested from "+e.getActionCommand()+" "+e.getSource().getClass());
		JCheckBoxMenuItem s = (JCheckBoxMenuItem)e.getSource();
		if (s.isSelected()) {
			gw.setSound(true);
			gw.notifyObservers();
		} else if (!s.isSelected()) {
			gw.setSound(false);
			gw.notifyObservers();
		}
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
