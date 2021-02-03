package frogger;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
public interface ICollider {
	public boolean collidesWith(ICollider otherObject);
	public void handleCollision(ICollider otherObject);
}
