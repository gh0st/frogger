/**
 * The BirdWing is a triangle represented by three lines with three points. The 
 * wing is drawn so that transformations can be made to it which appear like the
 * wings are moving.
 */
package a4;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

public class BirdWing {
	private Point top, bottomLeft, bottomRight;
	private Color myColor;
	private AffineTransform myTranslation, myRotation, myScale;
	
	public BirdWing() {
		// define the default wing with base=2, height=4, and origin at center
		top = new Point(0,12);
		bottomLeft = new Point(-4,-12);
		bottomRight = new Point(4,-12);
		// initialize the default color
		myColor = Color.BLUE;
		// initialize the transformations applied to the wing
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
		g2d.drawLine(top.x, top.y, bottomLeft.x, bottomLeft.y);
		g2d.drawLine(bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y);
		g2d.drawLine(bottomRight.x, bottomRight.y, top.x, top.y);
		
		g2d.setTransform(saveAT);
	}
}
