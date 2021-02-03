package frogger;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Iterate through the game objects. If the object is set as selected then
 * remove the gameobject from the collection.
 */
public class DeleteCommand extends AbstractAction {
  GameWorld gw;

  public DeleteCommand(GameWorld gw) {
    super("Delete Selected");
    this.gw = gw;
  }

  public void actionPerformed(ActionEvent e) {
    System.out.println("Delete requested from " + e.getActionCommand() + " " + e.getSource().getClass());
    IIterator itr = gw.getIterator();
    while (itr.hasNext()) {
      GameObject g = itr.getNext();
      if (g instanceof ISelectable) {
        if (((ISelectable) g).isSelected()) {
          itr.remove(g);
        }
      }
    }
    itr.reset();
    gw.notifyObservers();
  }
}
