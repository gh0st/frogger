package frogger;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import javax.swing.border.TitledBorder;

/**
 * CSC133 Clevenger Fall '12
 * Assignment 4
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.4 (November 29, 2012)
 * This is the "controller" of the MVC architecture
 */
public class Game extends JFrame implements ActionListener {
	/* Observables */
	private GameWorld gw = new GameWorld();
	/* Observers */
	private MapView mv = new MapView(gw);
	private ScoreView sv = new ScoreView(gw);
	

	/* Game Commands */
	private DeleteCommand deleteCommand = new DeleteCommand(gw);
	private PauseCommand pauseCommand = new PauseCommand(this, gw, mv);
	private QuitCommand quitCommand = new QuitCommand();
	private SoundCommand soundCommand = new SoundCommand(gw);
	private AboutCommand aboutCommand = new AboutCommand();
	private AddFrogCommand addFrogCommand = new AddFrogCommand(gw);
	private HopEastCommand hopEastCommand = new HopEastCommand(gw);
	private HopNorthCommand hopNorthCommand = new HopNorthCommand(gw);
	private HopSouthCommand hopSouthCommand = new HopSouthCommand(gw);
	private HopWestCommand hopWestCommand = new HopWestCommand(gw);
	private GameTickCommand gameTickCommand = new GameTickCommand(gw);
	/* End Game Commands */

	/* JComponent list */
	private JMenuItem soundMenuItem = new JCheckBoxMenuItem("Sound", true);
	private JButton deleteButton;
	private JButton pauseButton;
	private JButton quitButton;
	/* End JComponent list */

	/* Game Constants */
	private final int DELAY_IN_MSEC = 20;
	private int elapsed_time = 0;
	private Timer timer;
	private boolean gameMode;
	/* End Game constants */
	
	
	/**
	 * Game constructor
	 */
	public Game() {
		gw.addObserver(sv);
		gw.addObserver(mv);
		
		/* Commands here to construct the JFrame */
		setSize(1100,800);
		setTitle("Frogger");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		/* Build the menu bar and add the menu items */
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
			JMenuItem mItem;
			mItem = new JMenuItem("New"); fileMenu.add(mItem);
			mItem = new JMenuItem("Save"); fileMenu.add(mItem);
			mItem = new JMenuItem("Undo"); fileMenu.add(mItem);
			fileMenu.addSeparator();
			soundMenuItem.setAction(soundCommand); fileMenu.add(soundMenuItem);
			mItem = new JMenuItem(aboutCommand); fileMenu.add(mItem);
			mItem = new JMenuItem(quitCommand); fileMenu.add(mItem);
		JMenu commandMenu = new JMenu("Commands");
			mItem = new JMenuItem(gameTickCommand); commandMenu.add(mItem);
			mItem = new JMenuItem(quitCommand); commandMenu.add(mItem);
		bar.add(fileMenu);
		bar.add(commandMenu);
		setJMenuBar(bar);
		
		/* Build the west control panel and add the buttons */
		JPanel westPanel = new JPanel();
		westPanel.setBorder(new TitledBorder("Commands: ")); 
		westPanel.setLayout(new GridLayout(24,1));
		
		pauseButton = new JButton(pauseCommand); westPanel.add(pauseButton);
		pauseButton.getInputMap().put(KeyStroke.getKeyStroke("Space"),"none");
		
		deleteButton = new JButton(deleteCommand); westPanel.add(deleteButton);
		deleteButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		if (!gw.getPaused()) {
			deleteButton.setEnabled(false);
		}
		
		quitButton = new JButton(quitCommand); westPanel.add(quitButton);
		quitButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		
		add(sv, BorderLayout.NORTH); // add our scoreview
		add(mv, BorderLayout.CENTER); // add our mapview
		add(westPanel, BorderLayout.WEST); // build and add our command menu
		
		KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
		KeyStroke upArrow = KeyStroke.getKeyStroke("UP");
		KeyStroke downArrow = KeyStroke.getKeyStroke("DOWN");
		KeyStroke rightArrow = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke leftArrow = KeyStroke.getKeyStroke("LEFT");
		KeyStroke qKey = KeyStroke.getKeyStroke('q');
		
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = mv.getInputMap(mapName);
		imap.put(spaceKey, "AddFrog");
		imap.put(upArrow, "HopNorth");
		imap.put(downArrow, "HopSouth");
		imap.put(rightArrow, "HopEast");
		imap.put(leftArrow, "HopWest");
		imap.put(qKey, "Quit");
		
		ActionMap amap = mv.getActionMap();
		amap.put("AddFrog", addFrogCommand);
		amap.put("HopNorth", hopNorthCommand);
		amap.put("HopSouth", hopSouthCommand);
		amap.put("HopEast", hopEastCommand);
		amap.put("HopWest", hopWestCommand);
		amap.put("Quit", quitCommand);
		
		this.requestFocus();
		setVisible(true);
		
		timer = new Timer(DELAY_IN_MSEC, this);
		timer.start();
	}
	
	/*this.addWindowListener(
		new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setAction(quitCommand);
			}
		}
	);*/
	
	public void setDeleteButton(boolean b) {
		if (b) {
			deleteButton.setEnabled(true);
		} else {
			deleteButton.setEnabled(false);
		}
	}
	
	public void timerStart() {
		timer.restart();
	}
	public void timerStop() {
		timer.stop();
	}
	
	/**
	 * Handler for the Timer ActionEvent: repaints the panel
	 * @param e ActionEvent which will be the timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// first go through the list and move all the objects
		elapsed_time += DELAY_IN_MSEC;
		try {
			IIterator theElements = gw.getIterator();
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject instanceof MovingGameObject) {
					froggerObject.tick(elapsed_time);
				}
			}
			theElements.reset();
		} catch (Exception f) {
			System.out.println(f);
		}
		// second go through the list and find out if any of the objects collided
		// with each other and set their remove flags to true
		try {
			IIterator iter = gw.getObjectCollection().getIterator();
			while (iter.hasNext()) {
				GameObject curObj = (GameObject)iter.getNext();
				if (curObj instanceof ICollider) {
					ICollider colObj = (ICollider)curObj;
					IIterator iter2 = gw.getObjectCollection().getIterator();
					while (iter2.hasNext()) {
						GameObject otherObj = (GameObject)iter2.getNext();
						if (otherObj instanceof ICollider) {
							ICollider colliderObj = (ICollider)otherObj;
							if (colliderObj != colObj) {
								if (colObj.collidesWith(colliderObj)) {
									colObj.handleCollision(colliderObj);
								}
							}
						}
					}
				}
			}
		} catch (Exception f) {
			System.out.println(f);
		}
		// third, go through the list and remove from the collection any object
		// that has a true setRemoveFlag
		try {
			IIterator theElements = gw.getObjectCollection().getIterator();
			while (theElements.hasNext()) {
				GameObject froggerObject = (GameObject)theElements.getNext();
				if (froggerObject.getRemoveFlag() == true) {
					theElements.remove(froggerObject);
					theElements.reset();
				}
			}
		} catch (Exception f) {
			System.out.println("Exception...");
			System.out.println(f);
		}
		if (elapsed_time % 7200 == 0)
			gw.addGameObjectBatch();
		gw.incGameTime();
		gw.notifyObservers();
	}
}
