package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
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
public class MapView extends JPanel implements IObserver, MouseListener, MouseWheelListener, MouseMotionListener { // mapview implements mouselistener
	private GameWorld gw;
	private boolean paused;
	private AffineTransform worldToND, ndToScreen, theVTM, inverseVTM;
	// world window boundaries
	private double windowLeft, windowRight, windowTop, windowBottom;
	private Point2D mouseLoc;
	
	public MapView(IObservable obs) {
		this.gw = (GameWorld)obs;
		JPanel centerPanel = new JPanel(); // create our panel
		setBorder(new LineBorder(Color.blue,2)); // add a border to the panel
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		windowLeft = 0; windowRight = 1000;
		windowTop = 500; windowBottom = 0;
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
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform currentAT = g2d.getTransform();
		
		// update the viewing transformation matrix
		worldToND = buildWorldToNDXform(windowRight, windowTop, windowLeft, windowBottom);
		ndToScreen = buildNDToScreenXform(this.getWidth(),this.getHeight());
		theVTM = (AffineTransform)ndToScreen.clone();
		theVTM.concatenate(worldToND);
		g2d.transform(theVTM);
		
		IIterator theElements = gw.getIterator();
		while (theElements.hasNext()) {
			GameObject froggerObject = (GameObject)theElements.getNext();
			if (froggerObject instanceof IDrawable) {
				IDrawable d = (IDrawable)froggerObject;
				d.draw(g2d);
			}
		}
		theElements.reset();
		g2d.setTransform(currentAT);
	}
	
	public boolean getPaused() {
		return paused;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void mouseClicked(MouseEvent e) {
		if (getPaused()) {
			Point2D p2d = e.getPoint();
			
			// translate the screen point to normalized device coordinates
			try {
				inverseVTM = theVTM.createInverse();
			} catch (NoninvertibleTransformException nte) {
				System.out.println("Unable to translate point "+nte+" to world points...");
			}
			Point2D mouseWorldLoc = new Point();
			inverseVTM.transform(p2d,mouseWorldLoc);
			
			IIterator iter = gw.getIterator(); // get an iterator on the gameobjects
			while (iter.hasNext()) { // while there are gameobjects in the collection
				GameObject s = iter.getNext(); // cast the gameobject to a seletable type
				if (s instanceof ISelectable) {
					if(e.isControlDown()) {
						if (((ISelectable)s).contains((Point)mouseWorldLoc)) {
							((ISelectable)s).setSelected(true);
						}
					} else {
						if (((ISelectable)s).contains((Point)mouseWorldLoc)) {
							System.out.println((Point)mouseWorldLoc);
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
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			// if the mouse wheel has moved backward, we must zoon out, increase
			// the world size
			zoomOut();
		} else if (e.getWheelRotation() < 0) {
			// if the mouse wheel has moved forward, we must zoom in, and 
			// decrease the world size
			zoomIn();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		Point2D oldMouseLoc;
		double x, y;
		
		//mouseLoc = new Point(e.getPoint());
		oldMouseLoc = mouseLoc;
		System.out.println(mouseLoc);
		//inverseVTM.transform(this.getMousePosition(), mouseLoc);
		if (e.isShiftDown()) {
			System.out.println("We would like to pan across the screen.");
			
		}
	}
	public void mouseDragged(MouseEvent e) {
		
	}
	
	private AffineTransform buildWorldToNDXform(double maxX, double maxY, double minX, double minY) {
		AffineTransform at = new AffineTransform();
		at.scale(1/maxX, 1/maxY); // scale(1/window width and 1/window height)
		at.translate(-minX, -minY); // origin at lower left
		return at;
	}
	private AffineTransform buildNDToScreenXform(int width, int height) {
		AffineTransform at = new AffineTransform();
		at.translate(0, height);
		at.scale(width, -height);
		return at;
	}
	
	/**
	 * This method changes the window boundaries to be smaller then calls a 
	 * redraw.
	 */
	public void zoomIn() {
		double h = windowTop - windowBottom;
		double w = windowRight - windowLeft;
		windowLeft += w*0.05; windowRight -= w*0.05;
		windowTop  -= h*0.05; windowBottom += h*0.05;
		this.repaint();
	}
	public void zoomOut() {
		double h = windowTop - windowBottom;
		double w = windowRight - windowLeft;
		windowLeft -= w*0.05; windowRight += w*0.05;
		windowTop += h*0.05; windowBottom -= h*0.05;
	}
	public void panLeft() {
	}
	public void panRight() {
	}
	public void panUp() {
	}
	public void panDown() {
	}
}
