# CSC133 Clevenger Fall '12
## Assignment 4 - Frogger - 20121129

## Contributors
Chad Hollman (hollmanchad@gmail.com)

## Description
Frogger game written in Java. Assignment 3 of CSUS: CSC133 Object Oriented
Computer Graphics Programming.

## Installation Instructions
Extract all `.java` files to a folder. From a command prompt navigate to where
all the java files are and compile using `javac *.java`. Once compiled navigate
to the parent folder and run `java a4.Starter` from command prompt.

## Bugs/Feature Requests
Please submit all bugs and feature request to the author above.

## Changelog
10/29/12
   * Created GUI interface.
   * Reorganized code to follow Model-View-Controller (MVC) architecture.
   * Replaced keyboard commands with GUI components.
   * GUI Components have an associated "commmand" object.
   * Implemented Observer/Observable pattern.
   * Implemented Iterator pattern.
   * Implemented Command pattern.
   * Implemented Proxy pattern.
   * Created a ScoreView responsible for displaying gamescore, froglives, game
     time, sound mode.
   * Created MapView responsible for displaying the gameworld which has not
     been implemented yet.
   * Added the following key bindings:
     - n - hops the frog north
     - s - hops the frog south
     - e - hops the frog east
     - w - hops the frog west
     - space - adds a frog to the gameworld
     - q - invokes quit command prompting user whether or not they'd like to
           quit.

11/09/12
   * Modified MapView to display the contents of the game graphically.
   * Added a new GameObject called "Bird".
   * Movement and animation is now driven by a timer.
   * Game now supports dynamic collision detection.
   * Game now supports dynamic collision response.
   * Game now plays sounds corresponding to specific actions taken during
     gameplay.
   * Game now supports the ability to edit some objects of the gameworld.

11/29/12
   * Game now plays sounds, a background sound during gameplay, a sound when
     the frog dies, a sound when the frog hops, and a sound when the frog has
     run out of lives.
   * Game now supports the ability to pause.
   * Game now supports the ability to delete game objects but only while the
     game is in paused mode.
