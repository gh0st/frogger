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
/** Add a truck to the target. */
public class AddTruckCommand extends AbstractAction {
  private GameWorld gw;

  public AddTruckCommand() {
    super("Add Truck");
  }

  public void actionPerformed(ActionEvent e) {
    Truck u = new Truck(gw);
    gw.addGameObject(u);
    System.out.println("AddTruck requested from " + e.getActionCommand() + " " + e.getSource().getClass());
    System.out.println(u);
  }

  public void setTarget(GameWorld gw) {
    this.gw = gw;
  }
}
