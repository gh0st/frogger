package frogger;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 * This is the "controller" of the MVC architecture
 */
public class ScoreView extends JPanel implements IObserver {
	private JLabel timeLabel, livesLabel, scoreLabel, soundLabel;
	private String s;
	/**
	 * Constructor for our ScoreView that displays our current time, lives left, the
	 * score, and whether sound is on or off.
	 */
	public ScoreView() {
		JPanel northPanel = new JPanel(); // create our panel
		setBorder(new LineBorder(Color.red, 2)); // add our border to the panel
		timeLabel = new JLabel("Current Time: "); this.add(timeLabel);
		livesLabel = new JLabel("Lives Left: "); this.add(livesLabel);
		scoreLabel = new JLabel("Score: "); this.add(scoreLabel);
		soundLabel = new JLabel("Sound: "); this.add(soundLabel);
	}
	public ScoreView(IObservable o) {
		JPanel northPanel = new JPanel(); // create our panel
		setBorder(new LineBorder(Color.red, 2)); // add our border to the panel
		if (o instanceof IGameWorld) {
			GameWorld gw = (GameWorld)o;
			timeLabel = new JLabel("Current Time: "+gw.getGameTime()); this.add(timeLabel);
			livesLabel = new JLabel("Lives Left: "+gw.getFrogLives()); this.add(livesLabel);
			scoreLabel = new JLabel("Score: "+gw.getGameScore()); this.add(scoreLabel);
			if (gw.getSound() == true) {
				s = "ON";
			} else {
				s = "OFF";
			}
			soundLabel = new JLabel("Sound: "+s); this.add(soundLabel);	
		}
	}
	/**
	 * Update method which will update our jlabels with the appropriate values.
	 * @param o is the gameworldproxy which is to provide copies of the values which
	 *				will be updated
	 * @param arg is an argument which will not be used.
	 */
	public void update(IObservable o, Object arg) {
		if (o instanceof IGameWorld) {
			GameWorldProxy gw = (GameWorldProxy)o;
			timeLabel.setText("Current Time: "+gw.getGameTime());
			livesLabel.setText("Lives Left: "+gw.getFrogLives());
			scoreLabel.setText("Score: "+gw.getGameScore());
			if (gw.getSound() == true) {
				s = "ON";
			} else {
				s = "OFF";
			}
			soundLabel.setText("Sound: "+s);
		}
	}
}
