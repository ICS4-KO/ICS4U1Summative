/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import processing.core.PApplet;

/**
 *
 * @author 343330528
 */
public abstract class Game {
    //Instance variables
    PApplet gameApp;
    String name; //Name of the game
    int maxScore; //Maximum amount of points user can earn from playing the game
    int gameIndex; //Index number of game in user's 2D array of game scores
    int score = 0; //Current player's score
    int numTries = 0; //Number of tries the current player makes
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
        System.out.println("a");
        return "a";
    }
    
    abstract void update();
    abstract void draw();
    abstract void mousePressed();
    abstract void keyPressed();
    
    
    
}
