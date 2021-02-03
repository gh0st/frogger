/**
 * BirdLeg class creates a leg represented by a line with two points. The two
 * points are 8 units away from each other and provide a center at (0,0).
 */
package a4;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

public class BirdLeg {
	private Point top, bottom;
	private Color myColor;
	private AffineTransform myTranslation, myRotation, myScale;
	
	public BirdLeg() {
		// define the default leg with height=2 and origin at center
		top = new Point(0,4);
		bottom = new Point(0,-4);
		// initialize the default color
		myColor = Color.BLUE;
		// initialize the transformations on the leg
		myTranslation = new AffineTransform();
		myRotation = new AffineTransform();
		myScale = new AffineTransform();
	}
	
	public void rotate(double degrees) {
		myRotation.rotate(Math.toRadians(degrees));
	}
	public void scale(double sx, double sy) {
		myScale.scale(sx, sy);
	}
	public void translate(double dx, double dy) {
		myTranslation.translate(dx, dy);
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(myRotation);
		g2d.transform(myScale);
		g2d.transform(myTranslation);
		
		g2d.setColor(myColor);
		g2d.drawLine(top.x, top.y, bottom.x, bottom.y);
		
		g2d.setTransform(saveAT);
	}
}
