package a2;
import java.awt.Point;
import java.awt.Color;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 */
public class GameObject {
	private Point location = new Point();
	private Color objColor;
	public String getLocation() {
		return "x="+location.getX()+",y="+location.getY();
	}
	public void setLocation(int x, int y) {
		location.x = x;
		location.y = y;
	}
	public String getColor() {
		return "color=["+objColor.getRed()+","+objColor.getGreen()+","+objColor.getBlue()+"]";
	}
	public void setColor(int newRed, int newGreen, int newBlue) {
		objColor = new Color(newRed, newGreen, newBlue);
	}
	public void changeColor(int newRed, int newGreen, int newBlue) {
		setColor(newRed, newGreen, newBlue);
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
}
