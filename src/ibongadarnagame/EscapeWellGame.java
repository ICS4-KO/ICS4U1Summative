/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author 343330528
 */
public class EscapeWellGame extends Game {
    //Instance variables
    boolean rungDropping = false; //True if rung is dropping 
    private int wellHeight = 500; //Height of well that rung falls through
    private Rung currentRung; //Current rung that is falling
    private boolean hitsBottom = false; //True once rung hits the bottom
    private int currentRungNumber = 1; //Number of current rung the player is interacting with
    private ArrayList<Rung> assembledRungs; //ArrayList of rungs that have been successfully placed
    private Rung previousRung; //Previous rung that was successfully paced
    //private int originalNumRungs = 15; //Original number of rungs the user starts with
    private int totalNumRungs = 15; //Current total number of rungs the user has
    private String message = "Press SPACE to drop the rung!"; //Tells player to press space to drop make the rung fall
    
    
    //Declare images
    PImage player; //Player
    PImage wellGameBG; //Background of well escape game
    PImage wellGameVictory; //Well escape game victory screen
    PImage wellGameDefeat; //Well escape game defeat screen
    
    public EscapeWellGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution) {
        super(p, name, maxScore, gameIndex, chosenCharacter); //Call parent constructor
        assembledRungs = new ArrayList<>(); //Initialize array list of rungs that have been placed successfully
        currentRung = new Rung(p, 250, 130); //Create first Rung object
        
        if (traitsDistribution == 2) { //If player has high intelligence, they get the advantage of having more rungs (more tries)
            //originalNumRungs = 20; //Set higher original number of rungs the user start with
            totalNumRungs = 20; //Set new current total number of rungs the user has
        } //End if statement chekcing if player has high intelligence
        
        //Load Images
        wellGameBG = gameApp.loadImage("images/wellgamebg.jpg"); //Load background of well escape game
        wellGameVictory = gameApp.loadImage("images/gamevictory.jpg"); //Load well escape game victory screen
        wellGameDefeat = gameApp.loadImage("images/wellgamedefeat.jpg"); //Load well escape game defeat screen
        
        player = gameApp.loadImage(chosenCharacter); //Load character image
        player.resize(155, 300); //Make character smaller for the minigame
    }
    
    
    void update() {
        System.out.println(rungDropping);
        //If game is still running
        if (!gameOver) {
            if (assembledRungs.size() >= 12) {
                gameWon = true;
                gameOver = true;
            }
            
            if (currentRungNumber > totalNumRungs) { ////may not need because of method below
                gameWon = false;
                gameOver = true;
            }
            
            //If the number of rungs the user has left is less than the rungs they need
            if (totalNumRungs - currentRungNumber <  12 - assembledRungs.size()) {
                gameWon = false;
                gameOver = true;
            }
            
            //If a rung is currently dropping
            if (rungDropping) {
                //If the rung is still above the ground
                if (currentRung.y + currentRung.height <= 585) {
                    //currentRung.rungFalling(); //Keep rung falling until it hits or passes a certain point
                    if (currentRungNumber != 1) {
                        if (currentRung.isCollidingWith(previousRung)) {
                            assembledRungs.add(currentRung);
                            previousRung = currentRung;
                            rungDropping = false;
                            currentRung = new Rung(gameApp, 250, 130); //Create new Rung object
                            currentRungNumber += 1; //Incrememt number of current rung that is falling
                        }
                    } 
                    
                //If rung is hits/passes the ground
                } else {
                    //if (currentRungNumber == 1) {
                        //assembledRungs.add(currentRung);
                      //  rungDropping = false;
                    //If any rung other than the first rung hits the ground, they were ot placed succesfully
                    //} else {
                    if (currentRungNumber == 1) {
                        previousRung = currentRung;
                        assembledRungs.add(currentRung);
                    }
                        
                    
                    
                    rungDropping = false;
                    currentRung = new Rung(gameApp, 250, 130); //Create new Rung object
                    currentRungNumber += 1; //Incrememt number of current rung that is falling
                    
                    //}
                }
                    
            } else {
                //currentRung.move();
            }
        }//End if statement checking if game is still running
        
    }
    
    @Override
    void draw() {
        //If game is still running
        if (!gameOver) {
            gameApp.background(wellGameBG); //Set background to well game background
            gameApp.image(player, 600, 295); //Draw character on the screen 
            
            //Iterate through array list of successfully placed rungs
            for (Rung rung : assembledRungs) {
                rung.draw(); //Draw each old rung onto the screen
            }
        
            //If a rung is currently dropping
            if (rungDropping) {
                currentRung.rungFalling(); //Draw rung falling down screen
                currentRung.draw(); //Draw rung on the screen
            //If the player is deciding when to click the down arrow to let the rung fall
            } else {
                currentRung.draw(); //Draw rung on the screen
                currentRung.move(); //Draw rung moving left and right
            }
            
            // Display game information
            gameApp.fill(255);
            gameApp.textAlign(gameApp.CENTER, gameApp.CENTER);
            gameApp.textSize(18);
            //Display number of rungs left including rung the user is currently interacting with
            gameApp.text("Rungs Left: " + (totalNumRungs - currentRungNumber + 1), gameApp.width / 2, 50);
            gameApp.text("Ladder Completion: " + assembledRungs.size() + " / 12 rungs" , gameApp.width / 2, 80);
            //gameApp.text("Rungs Needed: " + (11 - assembledRungs.size()), gameApp.width / 2, 80);
            gameApp.text(message, gameApp.width / 2, 110);
            
            //gameApp.text(currentRungNumber, gameApp.width / 2, 140);
            if (rungDropping)
                message = "";
            else
                message = "Press the DOWN arrow key to drop the rung!";
            
            
        //If the game is over
        } else {
            calculatePoints(); //Calculate the number of points the player earned
            damageTaken = 5 * (currentRungNumber - assembledRungs.size()); //Set damage taken depending on the number of times the user missed
            
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
        
    }
    
    @Override
    void mousePressed() {
        if (!gameOver) {
            
    }
        
    }
    
    @Override
    void keyPressed() {
        //If game is still running
        if (!gameOver) {
            //If the player presses the up arrow key
            if (gameApp.keyCode == gameApp.DOWN) {
                if (!rungDropping) {
                    rungDropping = true; //Set variable indicating that a rung is dropping to true;
                }
            }
            /**
            //If the spacebar is pressed while the player is deciding when to press the spacebar to drop the rung
            if (gameApp.key == 'F') {
                if (!rungDropping) {
                    rungDropping = true; //Set variable indicating that a rung is dropping to true;
                    numSpaceClicks += 1;////
                }
            } //End if statement for when the spacebar is pressed
            * */
            
        //If the game is over
        } else {
	//If the spacebar is pressed
            if (gameApp.key == ' ') { 
                ((MySketch) gameApp).stage = 16; //Go to the next stage (Escaped Well)
                ((MySketch) gameApp).player.moveTo(285, 255); //Set new player position
            } //End if statement for if the spacebar is pressed
        } //End if statement checking if the game is still running
        
    }
    
    
    public void calculatePoints() {
        //int rungsUsed = totalNumRungs - assembledRungs.size(); //Calculate number of rungs used
        
        //If the number of rungs used is greater than 17
        if (currentRungNumber >= 18)
            score = 5; //Set very low player score
        //If the number of rungs used is greater than 15
        else if (currentRungNumber >= 16)
            score = 10; //Set low player score
        //If the number of rungs usedis greater than 13
        else if (currentRungNumber >= 14)
            score = 15; //Set average player score
        //If the number of rungs used is greater than 11
        else if (currentRungNumber >= 12)
            score = 20; //Set good player score
        //If the player completed the ladder without wasting any rungs
        else
            score = 25; //Set best player score
        
        //If the player lost the game
        if (!gameWon)
            score -= 10; //Deduct an amount from the points they earned
    }
    
    
    @Override
    public String returnGameResults() {
        //Call parent method that returns game results shared across all games, add the total number of rungs the user used from this game to the String that will be returned
        return super.returnGameResults() + "Rungs Used: " + (currentRungNumber-1);
    }
    //Move left/right, press space to drop a rung, sets first rung
    //Drop rung, once collides with bottom rung 
    //Rung is a class, where it has a collides with method with loop through arraylist of rung objects 
    //If in line with rung, they stack, if not, disappear
    //Win with least amount of rungs, max 10 score
    //User has maximum of 15 rungs but high intelligence gets 20
    //Stage rung dropping
    //Timer to drop rungs
    
}
