/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
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
    PApplet gameApp;
    String name; //Name of the player
    int maxScore; //Maximum amount of points user can earn from playing the game
    int gameIndex; //Index number of game in user's 2D array of game scores
    int score = 0; //Current player's score
    int numTries = 0; //Number of tries the current player makes
    
    int damageTaken = 0; //Amount of damage taken by the user
    
    boolean gameWon = false; //True if user wins the game
    boolean gameOver = false;
    
    //Static variables
    private static int highestScore = 0; //Highest score among all players
    
    //Constants
    private static final int NUM_TRIES = 3; //Total number of tries per game
    
    /**
     * Constructor creates an instance of the Game class with its own name and maximum score
     * 
     * @param p            Reference to canvas
     * @param name         Name of game
     * @param maxScore     Maximum number of points that can possibly be earned
     * @param gameIndex    Index number of game in user's 2D array of game scores
     * @param chosenCharacter  Character appearance chosen by the user
     */
    public Game(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter) {
        this.gameApp = p;
        this.name = name; //Set name attribute to entered value
        this.maxScore = maxScore; //Set masScore attribute to entered value
        this.gameIndex = gameIndex; //Set gameIndex attribute to entered value
    }
    
    
    /**
     * Logs game score and number of tries after player finishes a mini-game into their 2D array of game scores
     * 
     * @param player  Player that played the mini-game
     * @param points  Number of points player earned
     * @param tries   Number of tries before completing the game
     */
    public void setScoreAndTries(Player player, int points, int tries) {
        int[][] gameScores = player.getGameScores();
        gameScores[gameIndex][0] = points;
        gameScores[gameIndex][1] = tries;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String returnGameResults() {
        return "Points Earned: " + score + "\n\nDamage Taken: " + damageTaken + "\n\n";
    }
    
    public String getName() {
        return name; //Return name of player that played the game
    }
    
    public int getScore() {
        return score;
    }
    
    public boolean playerWonGame() {
        return gameWon;
    }
    public int getNumTries() {
        return numTries;
    }
    
    /**
     * Getter method for damageTaken attribute of Game object
     * 
     * @return  Amount of damage player takes in the minigame
     */
    public int getDamageTaken() {
        return damageTaken; //Return the amount of damage taken from the current game
    } /////may be unneeded
    
    abstract void update();
    abstract void draw();
    abstract void mousePressed();
    abstract void keyPressed();
    
    
    
}
