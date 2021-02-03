/**
 * The BirdBody is represented by circle which is then scaled in the actual
 * bird.
 */
package a4;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

public class BirdBody {
	private int myRadius;
	private Color myColor;
	private AffineTransform myTranslation, myRotation, myScale;
	
	public BirdBody() {
		// define the default radius as 1
		myRadius = 12;
		// initialize the default color of the birds body
		myColor = Color.BLUE;
		// initialize the transformations on the body
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
		
		g2d.transform(myTranslation);
		g2d.transform(myRotation);
		g2d.transform(myScale);
		
		g2d.setColor(myColor);
		Point upperLeft = new Point(-myRadius,-myRadius);
		g2d.fillOval(upperLeft.x, upperLeft.y, myRadius*2, myRadius*2);
		
		g2d.setTransform(saveAT);
	}
}
