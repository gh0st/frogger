package a2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * CSC133 Clevenger Fall '12
 * Assignment 2
 * Frogger
 *
 * @author Chad Hollman (hollmanchad@gmail.com)
 * @version 0.2 (October 16, 2012)
 * This is the "controller" of the MVC architecture
 */
public class Game extends JFrame implements ItemListener, WindowListener {
	private GameWorld gw = new GameWorld();
	private MapView mv = new MapView(gw);
	private ScoreView sv = new ScoreView(gw);
	private QuitCommand quitCommand = new QuitCommand();
	private AboutCommand aboutCommand = new AboutCommand();
	private AddCarCommand addCarCommand = new AddCarCommand();
	private AddFrogCommand addFrogCommand = new AddFrogCommand();
	private AddLogCommand addLogCommand = new AddLogCommand();
	private AddRockCommand addRockCommand = new AddRockCommand();
	private AddTruckCommand addTruckCommand = new AddTruckCommand();
	private AddTurtleCommand addTurtleCommand = new AddTurtleCommand();
	private HopEastCommand hopEastCommand = new HopEastCommand();
	private HopNorthCommand hopNorthCommand = new HopNorthCommand();
	private HopSouthCommand hopSouthCommand = new HopSouthCommand();
	private HopWestCommand hopWestCommand = new HopWestCommand();
	private GameTickCommand gameTickCommand = new GameTickCommand(gw);
	private CarCollisionCommand carCollisionCommand = new CarCollisionCommand();
	private TruckCollisionCommand truckCollisionCommand = new TruckCollisionCommand();
	private HopRockCommand hopRockCommand = new HopRockCommand();
	private HopLogCommand hopLogCommand = new HopLogCommand();
	private HopTurtleCommand hopTurtleCommand = new HopTurtleCommand();
	private HopEmptyLilyPadCommand hopEmptyLilyPadCommand = new HopEmptyLilyPadCommand();
	private HopFullLilyPadCommand hopFullLilyPadCommand = new HopFullLilyPadCommand();
	private HopWaterCommand hopWaterCommand = new HopWaterCommand();
	private HopOutsideCommand hopOutsideCommand = new HopOutsideCommand();
	private JMenuItem soundMenuItem = new JCheckBoxMenuItem("Sound");
	
	public Game() {
		gw.addObserver(sv);
		gw.addObserver(mv);
		
		hopEastCommand.setTarget(gw);
		hopNorthCommand.setTarget(gw);
		hopSouthCommand.setTarget(gw);
		hopWestCommand.setTarget(gw);
			
		setSize(1100,800);
		setTitle("Frogger");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
			JMenuItem mItem = new JMenuItem("New"); fileMenu.add(mItem);
			mItem = new JMenuItem("Save"); fileMenu.add(mItem);
			mItem = new JMenuItem("Undo"); fileMenu.add(mItem);
			fileMenu.addSeparator();
			soundMenuItem.addItemListener(this); fileMenu.add(soundMenuItem);
			mItem = new JMenuItem("About"); mItem.setAction(aboutCommand); fileMenu.add(mItem);
			mItem = new JMenuItem("Quit"); mItem.setAction(quitCommand); fileMenu.add(mItem);
		JMenu commandMenu = new JMenu("Commands");
			mItem = new JMenuItem("Add Rock..."); mItem.setAction(addRockCommand); addRockCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("Add Log..."); mItem.setAction(addLogCommand); addLogCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("Add turtle..."); mItem.setAction(addTurtleCommand); addTurtleCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("Add car..."); mItem.setAction(addCarCommand); addCarCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("Add truck..."); mItem.setAction(addTruckCommand); addTruckCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("Tick game clock...");	mItem.setAction(gameTickCommand); gameTickCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("Quit"); mItem.setAction(quitCommand); commandMenu.add(mItem);
			commandMenu.addSeparator();
			mItem = new JMenuItem("1-Car Kills Frog..."); mItem.setAction(carCollisionCommand); carCollisionCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("2-Truck Kills Frog..."); mItem.setAction(truckCollisionCommand); truckCollisionCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("3-Rock Kills Frog..."); mItem.setAction(hopRockCommand); hopRockCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("4-Frog Hops on Log..."); mItem.setAction(hopLogCommand); hopLogCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("5-Frog Hops on Turtle..."); mItem.setAction(hopTurtleCommand); hopTurtleCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("6-Frog Hops on Empty Lilypad..."); mItem.setAction(hopEmptyLilyPadCommand); hopEmptyLilyPadCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("7-Frog Hops on Occupied Lilypad..."); mItem.setAction(hopFullLilyPadCommand); hopFullLilyPadCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("8-Frog Hops into Water..."); mItem.setAction(hopWaterCommand); hopWaterCommand.setTarget(gw); commandMenu.add(mItem);
			mItem = new JMenuItem("9-Frog Hops Out of Game..."); mItem.setAction(hopOutsideCommand); hopOutsideCommand.setTarget(gw); commandMenu.add(mItem);
		bar.add(fileMenu);
		bar.add(commandMenu);
		setJMenuBar(bar);
		
		add(sv, BorderLayout.NORTH); // add our scoreview
		
		add(buildWestPanel(), BorderLayout.WEST); // build and add our command menu
		JPanel CenterPanel = new JPanel(); // add our mapview which still lists off objects in 
		CenterPanel.setBorder(new LineBorder(Color.blue,2));
		add(CenterPanel, BorderLayout.CENTER); // add our mapview
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = CenterPanel.getInputMap(mapName);
		KeyStroke spaceKey = KeyStroke.getKeyStroke("SPACE");
		KeyStroke upArrow = KeyStroke.getKeyStroke("UP");
		KeyStroke downArrow = KeyStroke.getKeyStroke("DOWN");
		KeyStroke rightArrow = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke leftArrow = KeyStroke.getKeyStroke("LEFT");
		KeyStroke qKey = KeyStroke.getKeyStroke('q');
		imap.put(spaceKey, "AddFrog");
		imap.put(upArrow, "HopNorth");
		imap.put(downArrow, "HopSouth");
		imap.put(rightArrow, "HopEast");
		imap.put(leftArrow, "HopWest");
		imap.put(qKey, "Quit");
		ActionMap amap = CenterPanel.getActionMap();
		amap.put("AddFrog", addFrogCommand);
		amap.put("HopNorth", hopNorthCommand);
		amap.put("HopSouth", hopSouthCommand);
		amap.put("HopEast", hopEastCommand);
		amap.put("HopWest", hopWestCommand);
		amap.put("Quit", quitCommand);
		
		this.requestFocus();
		setVisible(true);
	}
	/**
	 * Builds our WestPanel containing all our command JButtons
	 */
	private JPanel buildWestPanel() {
		JPanel westPanel = new JPanel();
		westPanel.setBorder(new TitledBorder("Commands: "));
		westPanel.setLayout(new GridLayout(20,2));
		JButton addRock = new JButton("Add Rock"); addRock.setAction(addRockCommand); addRockCommand.setTarget(gw); addRock.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(addRock);
		JButton addLog = new JButton("Add Log"); addLog.setAction(addLogCommand); addLogCommand.setTarget(gw); addLog.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(addLog);
		JButton addTurtle = new JButton("Add Turtle"); addTurtle.setAction(addTurtleCommand); addTurtleCommand.setTarget(gw); addTurtle.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(addTurtle);
		JButton addCar = new JButton("Add Car"); addCar.setAction(addCarCommand); addCarCommand.setTarget(gw); addCar.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(addCar);
		JButton addTruck = new JButton("Add Truck"); addTruck.setAction(addTruckCommand); addTruckCommand.setTarget(gw); addTruck.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(addTruck);
		JButton addFrog = new JButton("Add Frog"); addFrog.setAction(addFrogCommand);	addFrogCommand.setTarget(gw); addFrog.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(addFrog);
		JButton hopNorth = new JButton("Hop North");	hopNorth.setAction(hopNorthCommand); hopNorth.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopNorth);
		JButton hopSouth = new JButton("Hop South");	hopSouth.setAction(hopSouthCommand); hopSouth.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopSouth);
		JButton hopEast = new JButton("Hop East"); hopEast.setAction(hopEastCommand); hopEast.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopEast);
		JButton hopWest = new JButton("Hop West"); hopWest.setAction(hopWestCommand); hopWest.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopWest);
		JButton carCollision = new JButton("1-Car Collision"); carCollision.setAction(carCollisionCommand); carCollision.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(carCollision);
		JButton truckCollision = new JButton("2-Truck Collision"); truckCollision.setAction(truckCollisionCommand); truckCollision.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(truckCollision);
		JButton hopOnRock = new JButton("3-Hop Onto Rock");hopOnRock.setAction(hopRockCommand); hopOnRock.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopOnRock);
		JButton hopOnLog = new JButton("4-Hop Onto Log"); hopOnLog.setAction(hopLogCommand); hopOnLog.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopOnLog);
		JButton hopOnTurtle = new JButton("5-Hop Onto Turtle"); hopOnTurtle.setAction(hopTurtleCommand); hopOnTurtle.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopOnTurtle);
		JButton hopOnEmptyPad = new JButton("6-Hop Empty LilyPad"); hopOnEmptyPad.setAction(hopEmptyLilyPadCommand); hopOnEmptyPad.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopOnEmptyPad);
		JButton hopOnOccupiedPad = new JButton("7-Hop Full LilyPad"); hopOnOccupiedPad.setAction(hopFullLilyPadCommand); hopOnOccupiedPad.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopOnOccupiedPad);
		JButton hopInWater = new JButton("8-Hop Into Water"); hopInWater.setAction(hopWaterCommand); hopInWater.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopInWater);
		JButton hopOutside = new JButton("9-Hop Outside"); hopOutside.setAction(hopOutsideCommand); hopOutside.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(hopOutside);
		JButton tick = new JButton("Tick");	tick.setAction(gameTickCommand); tick.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); gameTickCommand.setTarget(gw); westPanel.add(tick);
		JButton QuitButton = new JButton ("Quit"); QuitButton.setAction(quitCommand); QuitButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); westPanel.add(QuitButton);
		return westPanel;
	}
	/**
	 * Overriding ItemListener methods
	 */
	public void itemStateChanged(ItemEvent e) {
		if (soundMenuItem.isSelected()) {
			System.out.println("Sound menu item selected...");
			gw.setSound(true);
			gw.notifyObservers();
		} else {
			System.out.println("Sound menu item unselected...");
			gw.setSound(false);
			gw.notifyObservers();
		}
	}
	/**
	 * Overriding WindowListener methods
	 */
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		System.out.println("Quit requested from "+e.getWindow());
		int result = JOptionPane.showConfirmDialog(
			null,
			"Are you sure you want to quit?",
			"Quit",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		return;
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
