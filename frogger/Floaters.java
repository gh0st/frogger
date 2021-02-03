package frogger;

/**
 * Floating objects (floaters) have an attribute bouyancy which controls how
 * much weight they can support. Objects with zero bouyancy stop floading (sink
 * to the bottom). When a floating object is created it is randomly assigned to
 * one of the three river channels (meaning its y location is determinted and
 * remains fixed thereafter). Floating objects always move in the direction and
 * at the river channel in which they are floating (and they never change
 * channels).
 */
public class Floaters extends MovingGameObject implements IMovable {
  private double bouy; // bouyancy

  /**
   * Gets the bouyancy of the object
   * 
   * @return int the bouyancy determines how much weight the object can support
   */
  public double getBouy() {
    return bouy;
  }

  /**
   * Sets the bouyancy of the object
   * 
   * @param int the bouyancy is the weight which the object will be able to hold
   */
  protected void setBouy(int b) {
    bouy = b;
  }

  protected void setBouy(double b) {
    bouy = b;
  }
}
