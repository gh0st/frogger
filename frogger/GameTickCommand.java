package frogger;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 */
/** Hop the target west. */
public class GameTickCommand extends AbstractAction {
  private GameWorld gw;

  public GameTickCommand(GameWorld gw) {
    super("Tick");
    this.gw = gw;
  }

  public void actionPerformed(ActionEvent e) {
    try {
      IIterator itr = gw.getIterator();
      while (itr.hasNext()) {
        GameObject froggerObject = (GameObject) itr.getNext();
        if (froggerObject instanceof MovingGameObject) {
          froggerObject.tick();
        }
      }
      gw.displayCollection();
    } catch (NullPointerException f) {
      System.out.println(f.toString());
    }
    int gt = gw.getGameTime();
    gt++;
    gw.setGameTime(gt);
    gw.notifyObservers();
  }

  public void setTarget(GameWorld gw) {
    this.gw = gw;
  }
}
