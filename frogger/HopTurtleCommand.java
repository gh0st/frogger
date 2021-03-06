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
public class HopTurtleCommand extends AbstractAction {
	private GameWorld gw;
	public HopTurtleCommand() {
		super("5-Hop onto Turtle");
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("From hopped onto Turtle...");
		// set the frogs location, speed, and direction to match the turtle
	}
	public void setTarget(GameWorld gw) {this.gw = gw;}
}
