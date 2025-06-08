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
    boolean roundRunning = false; //True if round is currently being played
    String userInput;
    String message = "Type in the letter you see!";
    
    
    ArrayList<String> sequence; // Stores the full sequence of keys
    Random random; // For generating random keys
    static final String[] KEYS = {"A", "S", "D", "F", "G", "H", "J"}; // Available keys
    
    
    
    //Declare images
    PImage player; //Player
    PImage gameBG; //Minigame background
    PImage mediumGameBG; //Medium minigame background
    PImage darkGameBG; //Dark minigame background
    PImage boarGameVictory; 
    PImage boarGameDefeat;
    
    
    
    public RhythmGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution) {
        super(p, name, maxScore, gameIndex, chosenCharacter);
        if (traitsDistribution == 2) { //If player has high intelligence 
            roundsToWin = 5; //Player only needs to win 5 rounds to win
            
        }
        
        //Load Images
        gameBG = gameApp.loadImage("images/gamebg.jpg"); //Load minigame background
        mediumGameBG = gameApp.loadImage("images/mediumgamebg.jpg"); //Load medium minigame background
        darkGameBG = gameApp.loadImage("images/darkgamebg.jpg"); //Load dark minigame background
        boarGameVictory = gameApp.loadImage("images/boargamevictory.jpg"); 
        boarGameDefeat = gameApp.loadImage("images/boargamedefeat.jpg"); 
        
        player = gameApp.loadImage(chosenCharacter); //Load character image
        player.resize(118, 230); //Make character smaller for the minigame
        
        
        sequence = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < 7; i++) {
            String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random integer from 0 to 6
            sequence.add(newKey); // Generate the 7-round sequence ////
        
        gameOver = false; ////
        gameWon = false; ////
        }
    }
        
    @Override
    void update() {
        if (!gameOver) {
            if (roundRunning) {
                if (currentLetter >= sequence.size()) {
                    roundRunning = false;
                } else {
                    // Check if the input matches the expected key for this round
                    String expectedKey = sequence.get(currentLetter);
                    if (!userInput.isEmpty()) {
                        if (!userInput.equalsIgnoreCase(expectedKey)) {
                            message = "Wrong key!";
                        } else if (userInput.equalsIgnoreCase(expectedKey)) {
                            message = "Correct!";
                            currentLetter++;
                        }
                    } else {
                        message = "Type in the letter you see!";
                    }
                }

            } else {
                if (round < 8) {
                    sequence.clear();
                    for (int i = 0; i < 7; i++) {
                        String newKey = KEYS[random.nextInt(KEYS.length)]; //Return random integer from 0 to 6
                        sequence.add(newKey);
                    }
                    round++;
                    roundRunning = true;
                } else {
                    gameOver = true;
                }

            }
        }
    }
    
    
    @Override
    void draw() {
        if (!gameOver) {
            if (roundRunning) {
                gameApp.background(gameBG);
                gameApp.fill(0);
                gameApp.textAlign(gameApp.CENTER, gameApp.CENTER);
                gameApp.textSize(18);
                gameApp.text("Round: " + round + " of 7", gameApp.width / 2, 50);
                gameApp.text("Match the sequence!", gameApp.width / 2, 80);

                gameApp.text(String.join(" ", sequence.subList(0, sequence.size())), gameApp.width / 2, 150);

                gameApp.text(userInput, 0, 0);
                gameApp.text(sequence.get(currentLetter), 0, 0);
                gameApp.text(message, gameApp.width / 2, 110);
            } else {
                gameApp.background(gameBG);
                gameApp.text("Round " + round, gameApp.width / 2, gameApp.height / 2);
            }
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
                if (userInput.length() < 2)
                    userInput += gameApp.key; // add each keystroke to user input string

            } // end nested if
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
    
    
    
}

