/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;

//Import packages
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * EscapeWellGame class of interactive story game Ibong Adarna, a subclass of the Game class and representation of minigame #3.
 * The player uses the DOWN arrow key to drop ladder rungs and must form an aligned ladder to win the game.
 * 
 * @author   Kimi Ong
 * @version  1.0
 * @since    2025-06-15
 */
public class EscapeWellGame extends Game {
    //Instance variables
    boolean rungDropping = false; //True if rung is dropping 
    private Rung currentRung; //Current rung that is falling
    private int totalNumRungs = 15; //Current total number of rungs the user has
    private int currentRungNumber = 1; //Number of current rung the player is interacting with
    private Rung previousRung; //Previous rung that was successfully paced
    private ArrayList<Rung> assembledRungs; //ArrayList of rungs that have been successfully placed
    private String message = "Press SPACE to drop the rung!"; //Tells player to press space to drop make the rung fall
    
    //Static variables
    private static int highestWellScore = 0; //Highest well escape game score among all players
    
    //Declare images
    PImage player; //Player
    PImage wellGameBG; //Background of well escape game
    PImage wellGameVictory; //Well escape game victory screen
    PImage wellGameDefeat; //Well escape game defeat screen
    
    
    /**
     * Constructor creates an instance of the EscapeWellGame class, which represents minigame #3 in the story game
     *
     * @param p            Reference to canvas
     * @param name         Name of game
     * @param maxScore     Maximum number of points that can possibly be earned
     * @param chosenCharacter     Character appearance chosen by the user
     * @param traitsDistribution  Integer indicating whether user has high strength, high intelligence, or neutral strength/intelligence
     */
    public EscapeWellGame(PApplet p, String name, int maxScore, String chosenCharacter, int traitsDistribution) {
        //Call parent constructor with parameters for variables that all the minigames have
        super(p, name, maxScore, chosenCharacter); 
        
        //If player has high intelligence, they get the advantage of having more rungs (more tries)
        if (traitsDistribution == 2) { 
            totalNumRungs = 20; //Set higher current total number of rungs the user has
        //If player has neutral strength/intelligence, they get a slight advantage of more rungs
        } else if (traitsDistribution == 2) {
            totalNumRungs = 17; //Set slightly higher current total number of rungs the user has
        } //End if statement chekcing for traits distribution
        
        //Load Images
        wellGameBG = gameApp.loadImage("images/wellgamebg.jpg"); //Load background of well escape game
        wellGameVictory = gameApp.loadImage("images/gamevictory.jpg"); //Load well escape game victory screen
        wellGameDefeat = gameApp.loadImage("images/wellgamedefeat.jpg"); //Load well escape game defeat screen
        
        player = gameApp.loadImage(chosenCharacter); //Load character image
        player.resize(155, 300); //Make character smaller for the minigame
        
        assembledRungs = new ArrayList<>(); //Initialize array list of rungs that have been placed successfully
        currentRung = new Rung(p, 250, 130); //Create first Rung object
    }
    
    
    @Override
    /**
     * Update method implementation for EscapeWellGame class
     */
    void update() {
        //If game is still running
        if (!gameOver) {
	    //If the user has assembled the entire 12-rung ladder (player wins the game)
            if (assembledRungs.size() >= 12) {
                gameWon = true; //Set game won to true
                gameOver = true; //Set game over to true
            } //End if statement if the user has assembled the entire 12-rung ladder
            
            //If the number of rungs the user has left is less than the rungs they need to complete the ladder (player loses the game)
            if (totalNumRungs - currentRungNumber + 1 <  12 - assembledRungs.size()) {
                gameWon = false; //Set game won to false
                gameOver = true; //Set game over to true
            } //End if statement checking if the user no longer has enough rungs to complete the ladder
            
            //If a rung is currently dropping
            if (rungDropping) {
                //If the rung is still above the ground
                if (currentRung.y + currentRung.height <= 585) {
		    //If the rung is not the first one the user places (the first rung does not need to collide with any other rungs to continue building the ladder)
                    if (currentRungNumber != 1) {
			//If the current rung the user is placing lines up with and collides with the previous rung before it hits the ground
                        if (currentRung.isCollidingWith(previousRung)) {
                            assembledRungs.add(currentRung); //Add current rung to the array list of Rung objects that have been assembled in the ladder
                            previousRung = currentRung; //Set current rung to the previous rung that is checked for collisions to continue building the ladder
                            rungDropping = false; //Set variable indicating that a rung is dropping to false
                            currentRung = new Rung(gameApp, 250, 130); //Create new Rung object and set it as the current rung
                            currentRungNumber += 1; //Incrememt number of current rung that is falling
                        } //End if statement checking if the current rung the user is placing lines up with and collides with the previous rung before it hits the ground
                    } //End if statement checking if the current rung falling is the furst rung
                    
                //If rung is hits/passes the ground
                } else {
		    //If the rung is the first one the user places (the first rung does not need to collide with any other rungs to continue building the ladder)
                    if (currentRungNumber == 1) {
                        previousRung = currentRung; //Set current rung to the previous rung that is checked for collisions to continue building the ladder
                        assembledRungs.add(currentRung); //Add current rung to the array list of Rung objects that have been assembled in the ladder
                    } //End if statement checking if the current rung falling is the furst rung
                        
                    //For all rungs that hit the ground, including the first rung
                    rungDropping = false; //Set variable indicating that a rung is dropping to false
                    currentRung = new Rung(gameApp, 250, 130); //Create new Rung object and set it as the current rung
                    currentRungNumber += 1; //Incrememt number of current rung that is falling                   
                } //End if statement checking if the rung has reached the ground
            } //End if statement checking if a rung is curerntly dropping
            
        } //End if statement checking if game is still running
    } //End update method
    
    @Override
    /**
     * Draw method implementation for EscapeWellGame class
     */        
    void draw() {
        //If game is still running
        if (!gameOver) {
            gameApp.background(wellGameBG); //Set background to well game background
            gameApp.image(player, 600, 295); //Draw character on the screen 
            
            //Iterate through array list of successfully placed rungs
            for (Rung rung : assembledRungs) {
                rung.draw(); //Draw each old rung onto the screen
            } //End for loop iterating through each of the successfully placed rungs
        
            //If a rung is currently dropping
            if (rungDropping) {
                currentRung.rungFalling(); //Draw rung falling down screen
                currentRung.draw(); //Draw rung on the screen
            //If the player is deciding when to click the down arrow to let the rung fall
            } else {
                currentRung.draw(); //Draw rung on the screen
                currentRung.move(); //Draw rung moving left and right
            } //End if statement checking if a rung is currently dropping or moving side to side
            
            // Display game information
            gameApp.fill(255); //Set fill colour to while
            gameApp.textAlign(gameApp.CENTER, gameApp.CENTER); //Center text
            gameApp.textSize(18); //Set text size
            //Display number of rungs left including rung the user is currently interacting with
            gameApp.text("Rungs Left: " + (totalNumRungs - currentRungNumber + 1), gameApp.width / 2, 50); //Display number of rungs (tries) the user has left
            gameApp.text("Ladder Completion: " + assembledRungs.size() + " / 12 rungs" , gameApp.width / 2, 80); //Display the number of rungs that the user has assembled so far
            //gameApp.text("Rungs Needed: " + (11 - assembledRungs.size()), gameApp.width / 2, 80);
            gameApp.text(message, gameApp.width / 2, 110); //Display message that lets the user know to oress the down arrow key to drop a rung
            
	    //If a rung is currently dropping
            if (rungDropping)
                message = ""; //Clear the message
	    //If the player is deciding when to click the down arrow to let the rung fall
            else
                message = "Press the DOWN arrow key to drop the rung!"; //Let the user know that they should press the down arrow key to drop the rung
            
        //If the game is over
        } else {
            //If points have not yet been calculated
            if (!calculatedPoints) {
                calculatePoints(); //Calculate the number of points the player earned 
                calculatedPoints = true; //Indicate that points have been calculated

                damageTaken = 5 * (currentRungNumber - assembledRungs.size()); //Set damage taken depending on the number of times the user missed
                //If player lost the game
                if (!gameWon)
                    damageTaken *= 2; //Damage taken is doubled
            } //End if statement checking if points have been calculated
            
            //If the game is over and the player won the game
            if (gameWon) 
                gameApp.background(wellGameVictory); //Set background to display victory screen
            //If the game is over and the player lost the game
            else 
                gameApp.background(wellGameDefeat); //Set background to display defeat screen
            
            // Display game over message
            gameApp.fill(0); //Set fill colour to black
            gameApp.textAlign(gameApp.LEFT, gameApp.LEFT); //Align text to the left
            gameApp.textSize(24); //Set text size
            gameApp.text(returnGameResults(), 245, 250); //Display game results
        } //End if statement checking if the game is still running
    } //End draw method
    
    @Override
    /**
     * Mouse pressed method implementation for EscapeWellGame class
     */
    void mousePressed() {
        if (!gameOver) {
        }
    } //End mouse pressed method
    
    @Override
    /**
     * Key pressed method implementation for EscapeWellGame class
     */
    void keyPressed() {
        //If game is still running
        if (!gameOver) {
            //If the player presses the down arrow key
            if (gameApp.keyCode == gameApp.DOWN) {
                if (!rungDropping) 
                    rungDropping = true; //Set variable indicating that a rung is dropping to true;
            } //End if statement checking if the player presses the downn arrow key
           
        //If the game is over
        } else {
	//If the spacebar is pressed
            if (gameApp.key == ' ') { 
                //Check if player's score exceeds the highest score for all well escape games
                if (score > highestWellScore) {
                    highestWellScore = score; //Set player's score to highest score
                    ((MySketch) gameApp).player.healthBoost(10); //Reward player with health boost
                } //End if statement checking if player's score exceeds the highest score for all well escape games
                
                ((MySketch) gameApp).player.setWellEscapePoints(score); //Set the points the player earned in the game
                ((MySketch) gameApp).gameResults.add(this); //Add game to array list of games that have been played
                ((MySketch) gameApp).player.takeDamage(damageTaken); //Transfer damage taken to player
                
                ((MySketch) gameApp).stage = 16; //Go to the next stage (Escaped Well)
                ((MySketch) gameApp).player.moveTo(285, 255); //Set new player position
            } //End if statement for if the spacebar is pressed
        } //End if statement checking if the game is still running
    } //End key pressed method
    
    
    /**
     * Calculates the number of points the player earned in the well escape minigame based on the how many rungs they used
     */
    public void calculatePoints() {
        //If the number of rungs used is greater than 17
        if (currentRungNumber >= 18)
            score = 10; //Set very low player score
        //If the number of rungs used is greater than 15
        else if (currentRungNumber >= 16)
            score = 20; //Set low player score
        //If the number of rungs used is greater than 13
        else if (currentRungNumber >= 14)
            score = 30; //Set average player score
        //If the number of rungs used is greater than 11
        else if (currentRungNumber >= 12)
            score = 40; //Set good player score
        //If the player completed the ladder without wasting any rungs
        else
            score = 50; //Set best player score
        
        //If the player lost the game
        if (!gameWon)
            score = (score/2); //They get have the amount of points they earned
    } //End calculate points method
    
    /**
     * Getter method for the number of rungs the player used before the game ended
     * 
     * @return  Number of rungs used by the player
     */
    public int getRungsUsed() {
        return (currentRungNumber - 1); //Game ends at the rung number of the next rung the player would have used
    } //End getter method for rungs used
    
    
    @Override
    public String returnGameResults() {
        //Call parent method that returns game results shared across all games, add the total number of rungs the user used from this game to the String that will be returned
        return super.returnGameResults() + "Rungs Used: " + (currentRungNumber-1);
    } //End return game results method
    
} //End EscapeWellGame class