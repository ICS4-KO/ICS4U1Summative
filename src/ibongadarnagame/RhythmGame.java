/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 *
 * @author 343330528
 */
public class RhythmGame extends Game {
    //Instance variables
    int roundsToWin = 6; //Rounds player has to win to win the minigame
    int roundsWon = 0; //Rounds player has won so far
    int round = 0; //Current round
    int currentLetter = 0; //Current letter in each round
    int pointsPerLetter = 5;
    //int letterInterval = 300;
    int roundInterval = 600;
    int breakInterval = 200;
    int timer = 0;
    boolean roundRunning = false; //True if round is currently being played
    static final int TOTAL_ROUNDS = 7;
    String expectedKey = "";
    String userInput = "";
    String message = "Type in the letter you see!";
    static final int EXPECTED_X = 335;
    static final int PLAYER_X = 485;
    static int positions[] = {145, 210, 275, 340, 410, 478, 543};
    String[][] pastLetters = new String[7][2];
    
                                                
    
    
    ArrayList<String> sequence; // Stores the full sequence of keys
    Random random; // For generating random keys
    static final String[] KEYS = {"A", "S", "D", "F", "G", "H", "J"}; // Available keys
    
    
    
    //Declare images
    PImage player; //Player
    PImage gameBG; //Minigame background
    PImage rhythmGameVictory; //Rhythm game victory screen
    PImage rhythmGameDefeat; //Rhythm game defeat screen
    PImage rhythmGameBG; //Background for rhythm game
    
    
    
    public RhythmGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution, boolean sheerWill) {
        super(p, name, maxScore, gameIndex, chosenCharacter);
        if (traitsDistribution == 2) { //If player has high intelligence 
            roundsToWin = 5; //Player only needs to win 5 rounds to win
            
        if (sheerWill) {
            roundInterval = 300;
            pointsPerLetter = 1;
        }
        }
        
        //Load Images
        gameBG = gameApp.loadImage("images/gamebg.jpg"); //Load minigame background
        rhythmGameVictory = gameApp.loadImage("images/gamevictory.jpg"); //Load rhythm game victory screen
        rhythmGameDefeat = gameApp.loadImage("images/rhythmgamedefeat.jpg"); //Load rhythm game defeat screen
        rhythmGameBG = gameApp.loadImage("images/rhythmgamebg.jpg"); //Load rhythm game background
        
        player = gameApp.loadImage(chosenCharacter); //Load character image
        player.resize(155, 300); //Make character smaller for the minigame
        
        
        sequence = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < TOTAL_ROUNDS; i++) {
            String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random integer from 0 to 6
            sequence.add(newKey); // Generate the 7-round sequence ////
        
        }
        
        gameOver = false; ////
        gameWon = false; ////
    }
        
    @Override
    void update() {
        if (!gameOver) {
            timer++;
            if (roundRunning) {
                if (timer >= roundInterval) {
                    //pastLetters[currentLetter][0] = expectedKey;
                    //pastLetters[currentLetter][1] = userInput.toUpperCase();
                    for (int i = 0; i < pastLetters.length; i++) {
                        for (int j = 0; j < pastLetters[i].length; j++) {
                            if (pastLetters[i][j] != null)
                                pastLetters[i][j] = null;
                        }
                    }
                    if (round < 7) {
                        pause();
                        
                        round += 1;
                        roundRunning = false;
                        currentLetter = 0;
                        timer = 0;
                    } else {
                        pause();
                        
                        //If player matched or exceeded the number of rounds they needed to win
                        if (roundsWon >= roundsToWin)
                            gameWon = true; //Set game won to true
                        gameOver = true;
                    }
                    //userInput = "";
                    //currentLetter++;
                    
                }
    
                    
                
                if (currentLetter >= sequence.size()) {
                    if (round < 7) {
                        pause();
                        
                        roundsWon += 1;
                        round += 1;
                        roundRunning = false;
                        currentLetter = 0;
                        timer = 0;
                    } else {
                        pause();
                        
                        //If player matched or exceeded the number of rounds they needed to win
                        if (roundsWon >= roundsToWin)
                            gameWon = true; //Set game won to true
                        gameOver = true;
                    }
                } else {
                    
                    // Check if the input matches the expected key for this round
                    expectedKey = sequence.get(currentLetter);
                    System.out.println(expectedKey + " " + userInput + " " + userInput.trim().toUpperCase().equalsIgnoreCase(expectedKey) + " " + timer);
                    if (!userInput.trim().equals("")) {
                        if (userInput.trim().toUpperCase().equalsIgnoreCase(expectedKey)) {
                            score += pointsPerLetter;
                            pastLetters[currentLetter][0] = expectedKey;
                            pastLetters[currentLetter][1] = expectedKey;
                            //timer = 0;
                            userInput = "";
                            currentLetter++;
                        } else {
                            message = "Wrong key!";
                        }
                    } else {
                        message = "Type in the letter you see!";
                    }
                }

            } else {
                if (round < 7) {
                    if (timer >= breakInterval) {
                        sequence.clear();
                        for (int i = 0; i < TOTAL_ROUNDS; i++) {
                            String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random integer from 0 to 6
                            sequence.add(newKey);
                        }
                        //round += 1;
                        userInput = "";
                        timer = 0;
                        roundRunning = true;
                    }
                } else {
                    //If player matched or exceeded the number of rounds they needed to win
                    if (roundsWon >= roundsToWin)
                        gameWon = true; //Set game won to true
                    gameOver = true;
                }

            }
        }
    }
    
    
    @Override
    void draw() {
        gameApp.background(gameBG);
        gameApp.fill(0);
        
        if (!gameOver) {
            
            if (roundRunning) {
                //Set rhythm game background
                gameApp.background(rhythmGameBG);
                gameApp.textAlign(gameApp.CENTER, gameApp.CENTER);
                gameApp.textSize(18);
                gameApp.text("Round: " + (round+1) + " of 7", gameApp.width / 2, 50);
                //gameApp.text("Match the sequence!", gameApp.width / 2, 80);
                gameApp.text(message, gameApp.width / 2, 80);

                //gameApp.text(String.join(" ", sequence.subList(0, sequence.size())), gameApp.width / 2, 150);

                gameApp.textSize(24);
                
                //original
                //gameApp.text(userInput, 0, 0);
                //gameApp.text(sequence.get(currentLetter), 0, 0);
                
                
                    //make (3d-7 - positions, pastletters-reset)
                    for (int i = 0; i < currentLetter; i++) {
                        gameApp.text(pastLetters[i][0], EXPECTED_X, positions[i]);
                        gameApp.text(pastLetters[i][1], PLAYER_X, positions[i]);
                        gameApp.text(pastLetters[i][0], 0, 0);
                    }
                
                
                for (int i = 0; i < (currentLetter); i++) {
                    System.out.println(pastLetters[i][0] + " "  + pastLetters[i][1]);
                }
                
                if(currentLetter < 7) { //index 7 out of bounds
                    gameApp.text(userInput.toUpperCase(), PLAYER_X, positions[currentLetter]);
                    gameApp.text(sequence.get(currentLetter), EXPECTED_X, positions[currentLetter]);
                }
                
                gameApp.image(player, 580, 170);
                
            } else {
                gameApp.text("Round " + (round+1), gameApp.width / 2, gameApp.height / 2);
            }
            
            gameApp.text(timer, 10, 10);
            
        } else {//Game Over
            //Player loses 10 HP for each letter they didn't get
            damageTaken = 10 * (49 - (score/5)); //Dividing by 5 because each letter is worth 5 points
            
            if (gameWon) {
                gameApp.background(rhythmGameVictory);
            } else {
                gameApp.background(rhythmGameDefeat);
            }
            
            // Display game over message
            gameApp.textAlign(gameApp.LEFT, gameApp.LEFT);
            gameApp.textSize(18);
            gameApp.text(returnGameResults(), 245, 240);
        }
    }
    
    @Override
    void mousePressed(){
        if (!gameOver) {
            
        }
        
    }
    
    @Override
    void keyPressed() {
        if (!gameOver) {
            if (gameApp.keyCode == gameApp.BACKSPACE) {
                if (userInput.length() > 0) 
                    userInput = userInput.substring(0, userInput.length() - 1); //Remove last character from user input string
            } else if (gameApp.key != gameApp.CODED) { // checks if it is an ASCII character
                if (userInput.length() < 1)
                    userInput += gameApp.key; // add each keystroke to user input string

            } // end nested if
        } else {
            if (gameApp.key == ' ') { //If spacebar is pressed
                ((MySketch) gameApp).stage = 11; //Go to the next stage (Catching the Adarna Bird)
                ((MySketch) gameApp).player.moveTo(-90, 255); //Set new player position
            }
        }
    }
    
    
    
    
/**
    // Generate a 7-round sequence with increasing difficulty
    private void generateSequence() {
        for (int i = 0; i < 7; i++) {
            // Add a new random key for each round
            String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random integer from 0 to 6
            sequence.add(newKey);
        }
    }

    @Override
    public boolean runChallenge(Scanner scanner) {
        System.out.println("The Ibong Adarna's lullaby begins! You must press the keys in rhythm to stay awake.");
        System.out.println("You have 7 rounds to prove your focus. Follow the sequence displayed each round.");
        System.out.println("Press the keys one by one when prompted. Good luck!");

        // Iterate through each round
        for (int round = 0; round < 7; round++) {
            //System.out.println("\nRound " + (round + 1) + " of 7");
            System.out.println("Sequence so far: " + String.join(" ", sequence.subList(0, round + 1)));
            System.out.print("Enter the next key: ");

            // Get player input
            String input = scanner.nextLine().trim().toUpperCase();

            // Check if the input matches the expected key for this round
            String expectedKey = sequence.get(round);
            if (!input.equals(expectedKey)) {
                System.out.println("Wrong key! Expected: " + expectedKey + ", Got: " + input);
                System.out.println("You fall asleep and take damage from the lullaby!");
                return false; // Fail the challenge if any round is incorrect
            }
            System.out.println("Correct! You resist the lullaby so far...");
        }

        System.out.println("\nYou successfully resisted the Ibong Adarna's lullaby for all 7 rounds!");
        return true; // Pass the challenge if all rounds are completed
    }
    */
    
    static void pause() {
        try {
        // Pause the current thread for 2000 milliseconds (2 seconds)
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            // Handle the case where the thread is interrupted while sleeping
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Thread was interrupted while sleeping.");
        }
    }
    
    
    @Override
    public String returnGameResults() {
        return super.returnGameResults() + "Letters Matched: " + (score/5) + " / 49\n\n" + "Rounds to Win: " + roundsToWin
                + " / 7\n\n" + "Rounds Won: " + roundsWon + " / 7";
    }
}

