package a3;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
/** Pause the game. */
public class PauseCommand extends AbstractAction {
	private Game g;
	private GameWorld gw;
	private MapView mv;
	public PauseCommand(Game g, GameWorld gw, MapView mv) {
		super("Pause");
		this.g = g;
		this.gw = gw;
		this.mv = mv;
	}
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if (!gw.getPaused()) { // if the game is not paused (in play) pause it
			b.setText("Play");
			g.setDeleteButton(true);
			gw.setSound(false);
			gw.setPaused(true);
			mv.setPaused(true);
			gw.notifyObservers();
			g.timerStop();
		} else if (gw.getPaused()) { // if the game is paused play it
			b.setText("Pause");
			g.setDeleteButton(false);
			gw.setSound(true);
			gw.setPaused(false);
			mv.setPaused(false);
			gw.notifyObservers();
			g.timerStart();
		}
	}
	public void setTarget(Game g) {this.g = g;}
}
