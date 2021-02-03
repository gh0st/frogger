/**
 * The bird class is composed of three other classes, the BirdBody, BirdWing,
 * and BirdLeg. The leg, and wing transforms are handled by this class.
 */
package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

public class Bird extends MovingGameObject implements IDrawable, ICollider {
	private int range;
	private BirdBody myBody;
	private BirdWing wings[];
	private BirdLeg legs[];
	private AffineTransform myTranslation, myRotation, myScale;
	
	private BirdWing bw0, bw1;
	private BirdLeg bl0, bl1;
	
	private double leftLegOffset = 0;
	private double leftLegIncrement = +1;
	private double leftLegMaxOffset = 5.0;
	
	private double rightLegOffset = 0;
	private double rightLegIncrement = -1;
	private double rightLegMaxOffset = 5.0;
	
	private double leftWingOffsetTx = 0;
	private double leftWingIncrementTx = +0.05;
	private double leftWingMaxOffsetTx = 1;
	private double leftWingOffsetSx = 1;
	private double leftWingIncrementSx = +0.005;
	private double leftWingMaxOffsetSx = 1.1;
	
	private double rightWingOffsetTx = 0;
	private double rightWingIncrementTx = -0.05;
	private double rightWingMaxOffsetTx = -1;
	private double rightWingOffsetSx = 1;
	private double rightWingIncrementSx = 0.05;
	private double rightWingMaxOffsetSx = 1.10;
	
	public Bird() {
		myTranslation = new AffineTransform();
		myRotation = new AffineTransform();
		myScale = new AffineTransform();
		
		myBody = new BirdBody();
		myBody.scale(1,1.5);
		
		wings = new BirdWing[2];
		bw0 = new BirdWing();
		bw0.rotate(90.0);
		bw0.translate(-10.0,24.0);
		wings[0] = bw0;
		bw1 = new BirdWing();
		bw1.rotate(270.0);
		bw1.translate(10.0,24.0);
		wings[1] = bw1;
		
		legs = new BirdLeg[2];
		bl0 = new BirdLeg();
		bl0.translate(-3.0,20.0);
		bl0.rotate(10.0);
		legs[0] = bl0;
		bl1 = new BirdLeg();
		bl1.translate(3.0,20.0);
		bl1.rotate(-10.0);
		legs[1] = bl1;
		
		int x = getRandom(100,900); int y = getRandom(425,475);
		setLocation(x, y);
		myTranslation.translate(x, y);
		setColor(Color.RED);
		setSpeed(1);
		setDirection(getRandom(90, 270, true), true);
		setRange(5);
	}
	
	public void draw(Graphics2D g2d) {
		// save the current graphics transform for later restoration
		AffineTransform saveAT = g2d.getTransform();
		
		// append this shapes transforms to the graphics objects transform
		g2d.transform(myTranslation);
		g2d.transform(myRotation);
		g2d.transform(myScale);
		
		// draw this shapes components, modified by the updated g2d transformation
		/*******************/
		myBody.draw(g2d);
		/*******************/
		for (BirdWing bw : wings) {
			bw.draw(g2d);
		}
		for (BirdLeg bl : legs) {
			bl.draw(g2d);
		}
		// restore the old graphics transform (remove this shapes transform)
		g2d.setTransform(saveAT);
	}
	public int getRange() {
		return range;
	}
	public void setRange(int r) {
		range = r;
	}
	public void tick(int time) {
		if (time % 60 == 0)
			move(time);
		if (getX() > 999 || getX() < 1 || getY() < 1 || getY() > 499) {
			setRemoveFlag(true);
		}
	}
	public String toString() {
		return "Bird: "+getLocation()+" "+getColor()+" speed="+getSpeed()+" dir="+getDirection()+"degrees";
	}
	public void move(int elapsedMillisecs) {
		int currX = getX();
		int currY = getY();
		int dist = getSpeed();
		double dx = Math.cos(Math.toRadians(getDirection()))*dist;
		double dy = Math.sin(Math.toRadians(getDirection()))*dist;
		setLocation(currX+dy, currY+dx);
		myTranslation.translate(dy, dx);
		update();
	}
	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		GameObject g = (GameObject)obj; // cast obj as a gameObject
		// find the outside sides of the object
		int thisLeftSide = getX()-(getWidth()/2);
		int thisRightSide = getX()+(getWidth()/2);
		int thisTopSide = getY()-(getHeight()/2);
		int thisBottomSide = getY()+(getHeight()/2);
		// find the outside sides of the other object
		int otherLeftSide = g.getX()-(g.getWidth()/2);
		int otherRightSide = g.getX()+(g.getWidth()/2);
		int otherTopSide = g.getY()-(g.getHeight()/2);
		int otherBottomSide = g.getY()+(g.getHeight()/2);
		if (thisRightSide < otherLeftSide || thisLeftSide > otherRightSide || otherTopSide < thisBottomSide || thisTopSide < otherBottomSide) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}
	public void handleCollision(ICollider obj) {}
	public void rotate(double degrees) {
		myRotation.rotate(Math.toRadians(degrees));
	}
	public void scale(double sx, double sy) {
		myScale.scale(sx, sy);
	}
	public void translate(double dx, double dy) {
		myTranslation.translate(dx, dy);
	}
	public void update() {
		leftLegOffset += leftLegIncrement;
		bl0.rotate(leftLegOffset);
		if (Math.abs(leftLegOffset) >= leftLegMaxOffset)
			leftLegIncrement *= -1;
		
		rightLegOffset += rightLegIncrement;
		bl1.rotate(rightLegOffset);
		if (Math.abs(rightLegOffset) >= rightLegMaxOffset)
			rightLegIncrement *= -1;
			
		leftWingOffsetTx += leftWingIncrementTx;
		bw0.translate(leftWingOffsetTx,0);
		if (Math.abs(leftWingOffsetTx) >= leftWingMaxOffsetTx)
			leftWingIncrementTx *= -1;
		//leftWingOffsetSx += leftWingIncrementSx;
		//bw0.scale(1,leftWingOffsetSx);
		/*if (leftWingOffsetSx <= leftWingMaxOffsetSx)
			//leftWingIncrementSx *= 1/leftWingIncrementSx;
			leftWingOffsetSx += leftWingIncrementSx;
		else if (leftWingOffsetSx >= leftWingMaxOffsetSx)
			leftWingOffsetSx *= (1/leftWingIncrementSx);*/
		
		rightWingOffsetTx += rightWingIncrementTx;
		bw1.translate(rightWingOffsetTx,0);
		if (Math.abs(rightWingOffsetTx) >= Math.abs(rightWingMaxOffsetTx))
			rightWingIncrementTx *= -1;
	}
}
