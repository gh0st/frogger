package a3;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 3
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.3 (November 09, 2012)
 */
public interface ICollider {
	public boolean collidesWith(ICollider otherObject);
	public void handleCollision(ICollider otherObject);
}