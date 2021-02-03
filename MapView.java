package a3;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 3
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.3 (November 09, 2012)
 * This is the "controller" of the MVC architecture
 */
public class MapView extends JPanel implements IObserver, MouseListener { // mapview implements mouselistener
	private GameWorld gw;
	private boolean paused;
	
	public MapView(IObservable obs) {
		this.gw = (GameWorld)obs;
		JPanel centerPanel = new JPanel(); // create our panel
		setBorder(new LineBorder(Color.blue,2)); // add a border to the panel
		addMouseListener(this);
	}
	/**
	 * Code here to output current map information (based on the data in the 
	 * Observable) to the console. Note that the received "Observable" is a
	 * GameWorld PROXY and can be cast to type IGameWorld in order to access the
	 * GameWorld methods inside it.
	 * @param o an IObservable that provides the data that must be displayed
	 * @param arg an extra argument
	 */
	public void update(IObservable o, Object arg) {
		this.repaint();
	}
	/**
	 * Paint component ust iterate through our gameobjects collection and then
	 * call draw() on each gameworld object.
	 * @param gw paint component must have access to the gameworld so that it can
	 * iterate through all the objects.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(0, 50, 1000, 50);
		g.drawLine(0, 100, 1000, 100);
		g.drawLine(0, 150, 1000, 150);
		g.drawLine(0, 200, 1000, 200);
		g.drawLine(0, 250, 1000, 250);
		g.drawLine(0, 300, 1000, 300);
		g.drawLine(0, 350, 1000, 350);
		g.drawLine(0, 400, 1000, 400);
		g.drawLine(0, 450, 1000, 450);
		g.drawLine(0, 500, 1000, 500);
		IIterator theElements = gw.getIterator();
		while (theElements.hasNext()) {
			GameObject froggerObject = (GameObject)theElements.getNext();
			if (froggerObject instanceof IDrawable) {
				IDrawable d = (IDrawable)froggerObject;
				d.draw(g);
			}
		}
		theElements.reset();
	}
	
	public boolean getPaused() {
		return paused;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void mouseClicked(MouseEvent e) {
		if (getPaused()) {
			Point p = e.getPoint();
			IIterator iter = gw.getIterator(); // get an iterator on the gameobjects
			while (iter.hasNext()) { // while there are gameobjects in the collection
				GameObject s = iter.getNext(); // cast the gameobject to a seletable type
				if (s instanceof ISelectable) {
					if(e.isControlDown()) {
						if (((ISelectable)s).contains(p)) {
							((ISelectable)s).setSelected(true);
						}
					} else {
						if (((ISelectable)s).contains(p)) {
							System.out.println(p);
							((ISelectable)s).setSelected(true);
						} else {
							((ISelectable)s).setSelected(false);
						}
					}
				}
			}
			iter.reset();
			repaint();
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
