package frogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.geom.AffineTransform;

import java.util.Random;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public abstract class GameObject {
	private Point location = new Point();
	private Color objColor;
	private Random myRNG = new Random();
	private boolean removeFlag = false;
	private Dimension objSize = new Dimension(); // this variable will be used for collision detection
	public String getLocation() {
		return "x="+location.getX()+",y="+location.getY();
	}
	public void setLocation(int x, int y) {
		location.x = x;
		location.y = y;
	}
	public void setLocation(double x, double y) {
		location.x = (int)x;
		location.y = (int)y;
	}
	public Color getColor() {
		return objColor;
	}
	public void setColor(int r, int g, int b) {
		objColor = new Color(r, g, b);
	}
	public void setColor(Color c) {
		objColor = c;
	}
	public void changeColor(int r, int g, int b) {
		setColor(r, g, b);
	}
	public String toString() {
		return "GameObject: "+getLocation()+" "+getColor();
	}
	public int getX() {
		return (int)location.getX();
	}
	public int getY() {
		return (int)location.getY();
	}
	public void tick() {
		System.out.println("Gameobject tick()...");
	}
	public void tick(int t) {
		System.out.println("Gameobject tick()...");
	}
	/**
	 * Returns a random value between 0 and the "max".
	 * @param max Desired maximum integer.
	 * @return int An integer between 0 and max.
	 */
	public int getRandom(int max) {
		return myRNG.nextInt(max);
	}
	/**
	 * Returns a random lane number between the min param and the max param.
	 * @param min The minimum desired value.
	 * @param max the maximum desired value.
	 * @return int A value between min and max.
	 */
	public int getRandom(int min, int max) {
		int r = (min + (int)(Math.random() * ((max-min)+1)));
		if (r <= 450 && r >= 400) {
			r = 425;
		} else if (r < 400 && r >= 350) {
			r = 375;
		} else if (r < 350 && r >= 300) {
			r = 325;
		} else if (r < 250 && r >= 200) {
			r = 225;
		} else if (r < 200 && r >= 150) {
			r = 175;
		} else if (r < 150 && r >= 100) {
			r = 125;
		} else if (r < 100 && r >= 50) {
			r = 75;
		}
		return r;
	}
	/**
	 * Returns a random number between the min param and the max param only if
	 * the auto param is true
	 * @param min The minimum desired random value.
	 * @param max The maximum desired value.
	 * @param auto Determines whether or not to return an actual lane position or
	 * an actual random value between the min and max values.
	 * @return A value between min and max or a lane position based on the min
	 * and max.
	 */
	public int getRandom(int min, int max, boolean auto) {
		if (auto == true)
			return (min + (int)(Math.random() * ((max-min)+1)));
		else return getRandom(min, max);
	}
	public void setRemoveFlag(boolean b) {
		removeFlag = b;
	}
	public boolean getRemoveFlag() {
		return removeFlag;
	}
	public int getHeight() {
		return objSize.height;
	}
	public int getWidth() {
		return objSize.width;
	}
	public void setSize(int w, int h) {
		objSize.setSize(w, h);
	}
}
