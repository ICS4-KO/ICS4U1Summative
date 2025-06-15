/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;

//Import packages
import processing.core.PApplet;

/**
 * Game class of interactive story game Ibong Adarna, superclass of the BoarFightGame, RhythmGame, and EscapeWellGame classes.
 * This class represents the basic structure shared by all three minigames.
 * 
 * @author   Kimi Ong
 * @version  1.0
 * @since    2025-06-15
 */
public abstract class Game {
    //Instance variables
    PApplet gameApp; //The canvas used to display graphical elements
    String name; //Name of the player
    int maxScore; //Maximum amount of points user can earn from playing the game
    int score = 0; //Current player's score
    int damageTaken = 0; //Amount of damage taken by the user
    boolean gameWon = false; //True if user wins the game
    boolean gameOver = false; //True once the game is over
    boolean calculatedPoints = false; //True once the points are calculated for the minigame
    
    
    /**
     * Constructor creates an instance of the Game class with its own name and maximum score
     * 
     * @param p            Reference to canvas
     * @param name         Name of game
     * @param maxScore     Maximum number of points that can possibly be earned
     * @param chosenCharacter  Character appearance chosen by the user
     */
    public Game(PApplet p, String name, int maxScore, String chosenCharacter) {
        this.gameApp = p; //Set reference to canvas
        this.name = name; //Set name attribute to entered value
        this.maxScore = maxScore; //Set masScore attribute to entered value
    }
    
    
    /**
     * Getter method for name attribute of a Game object
     * 
     * @return  Name of player that played the minigame
     */
    public String getName() {
        return name; //Return name of player that played the game
    } 
    
    /**
     * Getter method for score attribute of a Game object
     * 
     * @return  Score/total number of points earned by the user in the minigame
     */
    public int getScore() {
        return score; //Return player's score in the minigame
    } 
    
    /**
     * Getter method for the gameWon attribute of a Game object
     * 
     * @return  True if the player won the minigame, false if they lost
     */
    public boolean playerWonGame() {
        return gameWon; //Return variable indicating whether user won/lost
    }
    
    
    /**
     * Returns a String containing information about how the player did during the minigame
     * 
     * @return  String containing points earned and damage taken in the minigame
     */
    public String returnGameResults() {
        //Return string of minigame results
        return "Points Earned: " + score + "\n\nDamage Taken: " + damageTaken + "\n\n";
    }
    
    
    //Abstract methods for subclasses
    abstract void update(); //Update method
    abstract void draw(); //Draw method
    abstract void mousePressed(); //Mouse pressed method
    abstract void keyPressed(); //Key pressed method
    
} //End Game class
