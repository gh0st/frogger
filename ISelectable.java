package a4;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 * This interface defines the services (methods) provided by an object which is
 * "selectable" on the screen.
 */
public interface ISelectable {
	// a way to mark an object as "selected" or not
	public void setSelected(boolean b);
	// a way to test whether an object is selected
	public boolean isSelected();
	// a way to determine if a mouse point is "in" an object
	public boolean contains(Point p);
	// a way to "draw" the object that knows about drawing different ways
	// depending on "isSelected"
	public void draw(Graphics2D g2d);
}
