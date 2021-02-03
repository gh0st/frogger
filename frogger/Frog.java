package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

/**
 * Add a <b>f</b>rog to the world at a randomly-chosen X location along the
 * bottom (grass)
 */
public class Frog extends MovingGameObject implements IDrawable, IHoppable, IMovable, ICollider {
  private GameWorld gw;
  private boolean safeFlag = false;
  private boolean active = true;
  private Sound myBoingSound;
  private Sound myDyingSound;
  private Sound myGameOverSound;
  private String soundDir = "." + File.separator + "frogger" + File.separator + "sounds" + File.separator;

  /*
   * public Frog() { setColor(0,128,0); setLocation(500,25); setSize(30, 30);
   * String boingSoundFile = "boing.wav"; String boingSoundPath =
   * soundDir+boingSoundFile; myBoingSound = new Sound(boingSoundPath); String
   * dyingSoundFile = "dyingfrog.wav"; String dyingSoundPath =
   * soundDir+dyingSoundFile; myDyingSound = new Sound(dyingSoundPath); String
   * myGameOverSoundFile = "gameover.wav"; String myGameOverSoundPath =
   * soundDir+myGameOverSoundFile; myGameOverSound = new
   * Sound(myGameOverSoundPath); }
   */
  public Frog(GameWorld gw) {
    setColor(0, 128, 0);
    setLocation(500, 25);
    setSize(30, 30);
    String boingSoundFile = "boing.wav";
    String boingSoundPath = soundDir + boingSoundFile;
    myBoingSound = new Sound(boingSoundPath);
    String dyingSoundFile = "dyingfrog.wav";
    String dyingSoundPath = soundDir + dyingSoundFile;
    myDyingSound = new Sound(dyingSoundPath);
    String myGameOverSoundFile = "gameover.wav";
    String myGameOverSoundPath = soundDir + myGameOverSoundFile;
    myGameOverSound = new Sound(myGameOverSoundPath);
    this.gw = gw;
  }

  /**
   * Kill frog method will only work if there is an active frog to be killed
   */
  public void killFrog(IGameWorld igw) {
    if (igw instanceof GameWorld) {
      GameWorld gw = (GameWorld) igw;
      myDyingSound.play();
      gw.setFrogLives((gw.getFrogLives() - 1));
      if (gw.getFrogLives() == -1) {
        myGameOverSound.play();
      }
    }
  }

  /**
   * Move frog to given location.
   * 
   * @param int newX the new x position
   * @param int newY the new y position
   */
  public void move(int x, int y) {
    setLocation(x, y);
  }

  /**
   * The frog hops given a specified direction a fixed number of spaces which
   * implements move()
   * 
   * @param Direction dir either NORTH, SOUTH, EAST, WEST
   */
  public void hop(int dir) {
    switch (dir) {
      case 1:
        move(getX(), getY() + 50);
        break;
      case 2:
        move(getX(), getY() - 50);
        break;
      case 3:
        move(getX() + 50, getY());
        break;
      case 4:
        move(getX() - 50, getY());
        break;
    }
    myBoingSound.play();
  }

  /**
   * This function is used when a frog hops onto a floating object such as a log
   * or a turtle. The effect is that the frogs speed and direction now reflect the
   * object on which it hopped onto.
   * 
   * @param int newSpeed is the new speed of the frog
   * @param int newDir is the new direction of the frog
   */
  public void matchFloater(int speed, int headingInDegrees) {
    System.out.println("Inside matchFloater() with params: speed=" + speed + " dir=" + headingInDegrees + " degrees");
    // setSpeed(getY());
    setDirection(getY());
  }

  public void matchFloater(int x) {
    move(x, getY());
  }

  /**
   * This function is used when a frog hops onto a lilypad. The effect is the
   * frogs location is now set to that of the lilypad. The lilypad is set to
   * occupied and a new frog will be created along the bottom row.
   * 
   * @param LilyPad lpad is the lilypad object of which the frog has jumped on
   * @return boolean true or false regarding whether or not the lilypad is
   *         occupied.
   */
  public boolean matchLilyPad(LilyPad lpad) {
    if (!lpad.getOccupied()) {
      setLocation(lpad.getX(), lpad.getY());
      setSpeed(0);
      setDirection(0);
      setSafeFlag(true);
      setActive(false);
      return false;
    } else {
      System.out.println("The frog is now on an occupied lilypad!");
      System.out.println("Here we would initiate killFrog() but we'll do that later.");
      return true;
    }
  }

  public boolean getSafeFlag() {
    return safeFlag;
  }

  public void setSafeFlag(boolean b) {
    safeFlag = b;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean b) {
    active = b;
  }

  public void tick() {
    // do nothing unless told to
  }

  public String toString() {
    return "Frog: " + getLocation() + " " + getColor() + " speed=" + getSpeed() + " dir=" + getDirection() + " degrees";
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(getColor());
    g2d.fillOval((getX() - (getWidth() / 2)), (getY() - (getHeight() / 2)), getWidth(), getHeight());
  }

  public boolean collidesWith(ICollider obj) {
    boolean result = false;
    // cast obj as a gameObject
    GameObject g = (GameObject) obj;
    // find the outside sides of the object
    int thisLeftSide = getX() - (getWidth() / 2);
    int thisRightSide = getX() + (getWidth() / 2);
    int thisTopSide = getY() + (getHeight() / 2);
    int thisBottomSide = getY() - (getHeight() / 2);
    // find the outside sides of the other object
    int otherLeftSide = g.getX() - (g.getHeight() / 2);
    int otherRightSide = g.getX() + (g.getHeight() / 2);
    int otherTopSide = g.getY() - (g.getHeight() / 2);
    int otherBottomSide = g.getY() + (g.getHeight() / 2);

    if (thisRightSide < otherLeftSide || thisLeftSide > otherRightSide || otherTopSide < thisBottomSide
        || thisTopSide < otherBottomSide) {
      result = false;
    } else {
      result = true;
    }
    return result;
  }

  public void handleCollision(ICollider obj) {
    if (obj instanceof Vehicles || obj instanceof Rock || obj instanceof Bird) {
      setRemoveFlag(true);
      killFrog(gw);
    }
    if (obj instanceof LilyPad) {
      LilyPad l = (LilyPad) obj; // cast the object to an instance of LilyPad
      if (!l.getOccupied()) {
        matchLilyPad(l);
      } else if (l.getOccupied()) {
        setRemoveFlag(true);
        killFrog(gw);
        gw.notifyObservers();
      }
    }
    if (obj instanceof Floaters) {
      Floaters f = (Floaters) obj;
      matchFloater(f.getX());
    }
  }
}
