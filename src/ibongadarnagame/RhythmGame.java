/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;

//Import packages
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * Rhythm class of interactive story game Ibong Adarna, a subclass of the Game class and representation of minigame #2.
 * The player must type in the letters that appear on the screen within the time limit.
 * 
 * @author   Kimi Ong
 * @version  1.0
 * @since    2025-06-15
 */
public class RhythmGame extends Game {
    //Instance variables
    private int roundsToWin = 6; //Rounds player has to win to win the minigame
    private int roundsWon = 0; //Rounds player has won so far
    private int round = 0; //Current round number
    private int currentLetter = 0; //Index of current letter in each round (7 letters per round)
    private int pointsPerLetter = 2; //Points each letter is worth
    private int lettersMatched = 0; //Number of letters the player matched correctly
    
    private int timer = 0; //Timer to keep track of rounds
    private int roundInterval = 400; //Maximum time duration of each round
    private int breakInterval = 200; //Time duration of breaks between rounds
    private boolean roundRunning = false; //True if a round is currently being played
      
    private String expectedKey = ""; //String storing the expected letter
    private String userInput = ""; //String storing the letter entered by the user
    private String message = "Type in the letter you see!"; //Message that tells the user to type in a letter and also lets them know when they type in a wrong letter
    
    private static final int EXPECTED_X = 335;  //X value of where the current expected letter is displayed
    private static final int PLAYER_X = 485; //X value of where the current letter being entered by the player is displayed
    private static int positions[] = {145, 210, 275, 340, 410, 478, 543}; //Array list if y positions that each of the 7 letters (per round) are displayed at
    private String[][] pastLetters = new String[7][2]; //Array of past letters the user has correctly matched in the same round, so that they can be kept on the screen as the user progresses
    
    private ArrayList<String> sequence; //Array list of the current full sequence of keys the player needs to match
    private Random random; // For generating random keys
    private static final String[] KEYS = {"A", "S", "D", "F", "G", "H", "J"}; //Available keys for the random sequence of keys
    
    //Static variables
    private static int highestRhythmScore = 0; //Highest rhythm game score among all players
    
    //Constants
    private static final int TOTAL_ROUNDS = 7; //Total number of rounds
    private static final int LETTERS_PER_ROUND = 7; //Number of letters in each round 
    
    //Declare images
    PImage player; //Player
    PImage gameBG; //Minigame background
    PImage rhythmGameVictory; //Rhythm game victory screen
    PImage rhythmGameDefeat; //Rhythm game defeat screen
    PImage rhythmGameBG; //Background for rhythm game
    
    
    /**
     * Constructor creates an instance of the RhythmGame class, which represents minigame #2 in the story game
     * 
     * @param p            Reference to canvas
     * @param name         Name of game
     * @param maxScore     Maximum number of points that can possibly be earned
     * @param chosenCharacter     Character appearance chosen by the user
     * @param traitsDistribution  Integer indicating whether user has high strength, high intelligence, or neutral strength/intelligence
     * @param sheerWill           True if the user deciding to use sheer will to stay awake as opposed to lime juice (increased difficulty) 
     */
    public RhythmGame(PApplet p, String name, int maxScore, String chosenCharacter, int traitsDistribution, boolean sheerWill) {
        //Call parent constructor with parameters for variables that all the minigames have
        super(p, name, maxScore, chosenCharacter);
        
        //If player has high intelligence, they get the advantage of needing to pass less rounds to win
        if (traitsDistribution == 2)  
            roundsToWin = 4; //Player only needs to win 4 rounds to win
        //If player has neutral strength/intelligence, they get a slight advantage of needing to win less rounds
        else if (traitsDistribution == 0)
            roundsToWin = 5; //Player only needs to win 5 rounds to win
            
        //If the player decided to stay awake using sher will, the game will be harder
        if (sheerWill) {
            roundInterval = 300; //Shorter time duration for each round
            pointsPerLetter = 1; //Fewer points per letter
        } //End if statement checking if the player decided to stay awake using sher will
        
        //Load Images
        gameBG = gameApp.loadImage("images/gamebg.jpg"); //Load minigame background
        rhythmGameVictory = gameApp.loadImage("images/gamevictory.jpg"); //Load rhythm game victory screen
        rhythmGameDefeat = gameApp.loadImage("images/rhythmgamedefeat.jpg"); //Load rhythm game defeat screen
        rhythmGameBG = gameApp.loadImage("images/rhythmgamebg.jpg"); //Load rhythm game background
        
        player = gameApp.loadImage(chosenCharacter); //Load character image
        player.resize(155, 300); //Make character smaller for the minigame
        
        //Prepare array list of random letter sequences
        sequence = new ArrayList<>(); //Initialize array list for sequence of letters
        random = new Random(); //Create an instance of the Random class
	
        //Add 7 random letters the array list for the first letter sequence the player has to match
        for (int i = 0; i < LETTERS_PER_ROUND; i++) {
            String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random letter from array list if available keys (randomly generated index from 0 to 6)
            sequence.add(newKey); //Add letter to the 7-letter sequence 
        } //End for loop storing the first letter sequence the player has to match
    }
        
    
    @Override
    /**
     * Update method implementation for RhythmGame class
     */
    void update() {
        //If the game is still running
        if (!gameOver) {
            timer++; //Increment timer

	    //If the game is running and the user is currently playing a round
            if (roundRunning) {
                //Check if the time that passed has surpassed the time interval for each round (round ends)
                if (timer >= roundInterval) {
                    //pastLetters[currentLetter][0] = expectedKey;
                    //pastLetters[currentLetter][1] = userInput.toUpperCase();
	            //Loop through each row in the array of previous letters in the current round
                    for (int i = 0; i < pastLetters.length; i++) {
	                 //Loop through each column in the array of previous letters in the current round
                        for (int j = 0; j < pastLetters[i].length; j++) {
		            //If the element at the current index is not null
                            if (pastLetters[i][j] != null)
                                pastLetters[i][j] = null; //Set each filled value in the array to null to reset it for the next round
                        } //End for loop iterating through each column in the array of previous letters in the current round
                    } //End for loop iterating through each row in the array of previous letters in the current round

	            //If the player still has more rounds to play (total of 7)
                    if (round < TOTAL_ROUNDS) {
                        pause(); //Pause the game for a bit once their time has run out for the current round                        
                        round += 1; //Go to the next round
                        roundRunning = false; //Set variable indicating that the player is currently playing a round to false
                        currentLetter = 0; //Reset index of current letter that is being displayed to 0 for the next round
                        timer = 0; //Reset timer

	            //If the current round was the player’s last (7th) round
                    } else {
                        pause(); //Pause the game for a bit once their time has run out for the current round                        
                        //If player matched or exceeded the number of rounds they needed to win
                        if (roundsWon >= roundsToWin)
                            gameWon = true; //Set game won to true, otherwise it will stay false
                        gameOver = true; //Set game over to true
                    } //End if statement checking if the player still has more rounds to play (7 in total)  
                } //End if statement checking if the round has ended because time ran out
    
                //If the player is currently playing a round, check if the player has gone through all 7 of the letters they need to match for that round  (round ends)
                if (currentLetter >= LETTERS_PER_ROUND) {
	            //If the player still has more rounds to play (total of 7)
                    if (round < TOTAL_ROUNDS) {
                        pause(); //Pause the game for a bit once the user has matched al the letters for the current round
                        roundsWon += 1; //Add one to the number of rounds they have won
                        round += 1; //Go to the next round
                        roundRunning = false; //Set variable indicating that the player is currently playing a round to false
                        currentLetter = 0; //Reset index of current letter that is being displayed to 0 for the next round
                        timer = 0; //Reset timer

	            //If the current round was the player’s last (7th) round		
                    } else {
                        pause(); //Pause the game for a bit once the user has matched al the letters for the current round
                        //If player matched or exceeded the number of rounds they needed to win
                        if (roundsWon >= roundsToWin)
                            gameWon = true; //Set game won to true, otherwise it will stay false
                        gameOver = true; //Set game over to true
                    } //End if statement checking if the player  matched or exceeded the number of rounds they needed to win

                //If the player is currently playing a round, check if the player has not yet gone through all 7 of the letters they need to match for that round (round continues)
                } else {                  
	            //Get currently expected letter (currentLetter represents an index from 0 to 6) from sequence of available keys
                    expectedKey = sequence.get(currentLetter);

	            //If the user has entered a letter
                    if (!userInput.trim().equals("")) {
                        //Check if the player’s input matches the current expected key in this round
                        if (userInput.trim().toUpperCase().equalsIgnoreCase(expectedKey)) {
		            //If the user entered the correct letter
                            lettersMatched += 1; //Add one to the number of letters they matched correctly
                            pastLetters[currentLetter][0] = expectedKey; //Add expected key to the array of previous letters in the same round
                            pastLetters[currentLetter][1] = expectedKey; //Add user’s input (expected key) to the array of previous letters in the same round
                            userInput = ""; //Reset user input to an empty string
                            currentLetter++; //Increment index of current letter that the user has to match (used as an index value for the array list of the letter sequence the player has to match for the current round)
		        //If the player’s input does not match the current expected key in this round
                        } else {
                            message = "Wrong key!"; //Set error message to let the user know that they entered the wrong key
                        } //End if statement checking if the player’s input matches the letter correctly
	            //If the user has not entered a letter
                    } else {
                        message = "Type in the letter you see!"; //Set message to let the user know to type in a letter
                    } //End if statement checking if the user has entered a letter
                } //End if statement checking if the player has gone through all 7 of the letters they need to match for the round they are currently playing (round ends)

                
            //If the game is running and it is currently the brief break between rounds
            } else {
	        //If the player has not yet gone through all 7 rounds
                if (round < TOTAL_ROUNDS) {
	            //Once the break is over, get ready for the next round
                    if (timer >= breakInterval) {
                        sequence.clear(); //Clear the sequence of letters the player has to match
		        //Add 7 random letters the array list for the first letter sequence the player has to match
                        for (int i = 0; i < TOTAL_ROUNDS; i++) {
                            String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random letter from array list if available keys (randomly generated index from 0 to 6) 
                            sequence.add(newKey);  //Add letter to the 7-letter sequence
                        } //End for loop storing the first letter sequence the player has to match

                        userInput = ""; //Reset user input to an empty string
                        timer = 0; //Reset timer
                        roundRunning = true; //Set variable indicating that the user is playing a round to true
                    } //End if statement checking if the brief break between rounds is over
                    
	        //If the player just completed their final (7th) round
                } else {
                    //If player matched or exceeded the number of rounds they needed to win
                    if (roundsWon >= roundsToWin)
                        gameWon = true; //Set game won to true, otherwise it will stay false
                   gameOver = true; //Set game over to true
                } //End if statement checking if the player has gone through all 7 rounds

            } //End if statement checking the player is currently playing a round
        } //End if statement checking if the game is still running
    } //End update method
    
    
    @Override
    /**
     * Draw method implementation for RhythmGame class
     */
    void draw() {
        gameApp.background(gameBG); //Set game background
        gameApp.fill(0); //Set fill colour to black

        //If the game is still running
        if (!gameOver) {
	     //Check if the player is currently playing a round
            if (roundRunning) {
                //Set rhythm game background
                gameApp.background(rhythmGameBG);
                gameApp.textAlign(gameApp.CENTER, gameApp.CENTER); //Center text
                gameApp.textSize(18); //Set text size
                gameApp.text("Round: " + (round+1) + " of 7", gameApp.width / 2, 50); //Display current round number (out of 7)
                gameApp.text(message, gameApp.width / 2, 80); //Set message to tell the user to type in a letter and lets them know if they typed in the wrong letter

                gameApp.textSize(24); //Set larger text size to display the letters the player has matched and their input               
                //Iterate through each element in the array storing the letters the player has matched in the past to display them
                for (int i = 0; i < currentLetter; i++) {
                    //Display all of the past letters the player had to match
                    gameApp.text(pastLetters[i][0], EXPECTED_X, positions[i]);
                    //Display all of the past letters the player has entered to match
                    gameApp.text(pastLetters[i][1], PLAYER_X, positions[i]);
                } //End for loop iterating through all of the letters the user has matched for the current round
                
	        //If there are still letters to match for the current round (7 in total), display the current letter the user has to match along with the user's input
                if(currentLetter < LETTERS_PER_ROUND) { 
	            //Display letter the user has typed in for the current letter in upper case
                    gameApp.text(userInput.toUpperCase(), PLAYER_X, positions[currentLetter]);
	            //Display the current letter in the array list of the sequence of letters the player has to match
                    gameApp.text(sequence.get(currentLetter), EXPECTED_X, positions[currentLetter]);
                } //End if statement checking if there are still letters to match for the current round (7 in total)
                
                gameApp.image(player, 580, 170); //Display character on the screen
             
            //If it is currently the brief break between rounds   
            } else {
                //Display round number ofnext round in the center of the screen
                gameApp.text("Round " + (round+1), gameApp.width / 2, gameApp.height / 2);
            } //End if statement checking if the player is currently playing a round
            
        //If game is over
        } else {
            //If points have not yet been calculated
            if (!calculatedPoints) {
                calculatePoints(); //Calculate the number of points the player earned 
                calculatedPoints = true; //Indicate that points have been calculated

                //Player loses 3 HP for each letter they didn't get
                damageTaken = 3 * (49 - (score/2)); //Set damage taken, dividing by 2 because each letter is worth 2 points
            } //End if statement checking if points have been calculated
            
            //If the game is over and the player won the game
            if (gameWon)
                gameApp.background(rhythmGameVictory); //Set background to display victory screen
            //If the game is over and the player lost the game
            else 
                gameApp.background(rhythmGameDefeat); //Set background to display defeat screen
            
            //Display game over message
            gameApp.textAlign(gameApp.LEFT, gameApp.LEFT); //Align text to the left
            gameApp.textSize(18); //Set text size
            gameApp.text(returnGameResults(), 245, 225); //Display game results
        } //End if statement checking if the game is still running
    } //End draw method
    
    @Override
    /**
     * Mouse pressed method implementation for RhythmGame class
     */           
    void mousePressed(){
        if (!gameOver) {
        }
    } //End mouse pressed method
    
    @Override
    void keyPressed() {
        //If game is still running
        if (!gameOver) {
            //If the user presses the backspace button
            if (gameApp.keyCode == gameApp.BACKSPACE) {
                //If the user has entered a letter
                if (userInput.length() > 0) 
                    userInput = userInput.substring(0, userInput.length() - 1); //Remove last character from user input string
            //Checks if it is an ASCII character
            } else if (gameApp.key != gameApp.CODED) { 
                //If user has not entered a letter
                if (userInput.length() < 1)
                    userInput += gameApp.key; //Add each keystroke to user input string
            } //End if statement checking which key is pressed
            
        //If the game is over
        } else {
            //If spacebar is pressed
            if (gameApp.key == ' ') { 
                //Check if player's score exceeds the highest score for all rhythm games
                if (score > highestRhythmScore) {
                    highestRhythmScore = score; //Set player's score to highest score
                    ((MySketch) gameApp).player.healthBoost(10); //Reward player with health boost
                } //End if statement checking if player's score exceeds the highest score for all rhythm games
                
                ((MySketch) gameApp).player.setRhythmGamePoints(score); //Set the points the player earned in the game
                ((MySketch) gameApp).gameResults.add(this); //Add game to array list of games that have been played
                ((MySketch) gameApp).player.takeDamage(damageTaken); //Transfer damage taken to player
                //If player has lime juice in their inventory, which would have been used in this game, remove it
                if (((MySketch) gameApp).player.getInventory().hasItem("Lime Juice"))
                    ((MySketch) gameApp).player.getInventory().removeItem("Lime Juice"); //Remove lime juice from their inventory
                
                ((MySketch) gameApp).stage = 11; //Go to the next stage (Catching the Adarna Bird)
                ((MySketch) gameApp).player.moveTo(-90, 255); //Set new player position
            } //End if statement checking if the spacebar was pressed
        } //End if statement checking if the game is still running
    } //End key pressed method
    
    
    /**
     * Pauses the minigame briefly right after each round end, to ensure that the screen changes aren't too abrupt
     */
    static void pause() {
        //Briefly pause the game
        try {
            // Pause the current thread for 1000 milliseconds (1 second1)
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            // Handle the case where the thread is interrupted while sleeping
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Thread was interrupted while sleeping."); //Error message
        } //End try catch statement for pausing the game
    } //End pause method
    
    /**
     * Calculates the number of points the player earned in the rhythm minigame based on the how many letters they matched
     */
    public void calculatePoints() {
        score = pointsPerLetter * lettersMatched; //Player earns points fo reach letter they matched
        
        //If the player lost the game
        if (!gameWon)
            score = (score / 2); //They get half the amount of the points they earned
    } //End calculate points method
    
    /**
     * Getter method for the number of letters the player successfully matched in the game
     * 
     * @return  Number of letters the player matched correctly
     */
    public int getLettersMatched() {
        return (score / 2); //Divide score by 2, each letter is worth 2 points
    } //End getter method for letters matched
    
    
    @Override
    /**
     * Returns a String containing information about how the player did during the rhythm minigame
     * 
     * @return  String containing points earned and damage taken in the minigame, as well as the number of letters matched, rounds needed to win, and rounds won from this game to the String that will be returned
     */
    public String returnGameResults() {
        //Call parent method that returns game results shared across all games, add the number of letters matched, rounds needed to win, and rounds won from this game to the String that will be returned
        return super.returnGameResults() + "Letters Matched: " + (score/2) + " / 49\n\n" + "Rounds to Win: " + roundsToWin
                + " / 7\n\n" + "Rounds Won: " + roundsWon + " / 7";
    } //End return game results method
    
} //End RhythmGame class