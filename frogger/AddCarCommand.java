package frogger;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
/** Add a car to the target. */
public class AddCarCommand extends AbstractAction {
  private GameWorld gw;

  public AddCarCommand() {
    super("Add Car");
  }

  public void actionPerformed(ActionEvent e) {
    Car c = new Car(gw);
    gw.addGameObject(c);
    System.out.println("AddCar requested from " + e.getActionCommand() + " " + e.getSource().getClass());
    System.out.println(c);
  }

  public void setTarget(GameWorld gw) {
    this.gw = gw;
  }
}
