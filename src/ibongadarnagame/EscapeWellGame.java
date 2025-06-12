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
    private int totalNumRungs = 15; //Total number of rungs the user has
    private String message = "Press SPACE to drop the rung!"; //Tells player to press space to drop make the rung fall
    
    //Declare images
    
    public EscapeWellGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution) {
        super(p, name, maxScore, gameIndex, chosenCharacter); //Call parent constructor
        assembledRungs = new ArrayList<>(); //Initialize array list of rungs that have been placed successfully
        currentRung = new Rung(p, 250, 60); //Create first Rung object
        
        if (traitsDistribution == 2) { //If player has high intelligence 
            totalNumRungs = 20; //Player gets advantage of having more rungs (more tries)
        } //End if statement chekcing if player has high intelligence
        
        //Load Images
        
    }
    
    
    void update() {
        //If game is still running
        if (!gameOver) {
            if (assembledRungs.size() >= 11) {
                gameWon = true;
                gameOver = false;
            }
            
            if (currentRungNumber > totalNumRungs) {
                gameWon = false;
                gameOver = true;
            }
            
            
            //If a rung is currently dropping
            if (rungDropping) {
                //If the rung is still above the ground
                if (currentRung.y + currentRung.height <= 585) {
                    currentRung.rungFalling(); //Keep rung falling until it hits or passes a certain point
                    if (currentRungNumber != 1) {
                        if (currentRung.isCollidingWith(previousRung)) {
                            assembledRungs.add(currentRung);
                            rungDropping = false;
                            currentRung = new Rung(gameApp, 250, 60); //Create new Rung object
                            currentRungNumber += 1; //Incrememt number of current rung that is falling
                        }
                    }
                    
                //If rung is hits/passes the ground
                } else {
                    if (currentRungNumber == 1) {
                        assembledRungs.add(currentRung);
                        rungDropping = false;
                    //If any rung other than the first rung hits the ground, they were ot placed succesfully
                    } else {
                        rungDropping = false;
                        currentRung = new Rung(gameApp, 250, 60); //Create new Rung object
                        currentRungNumber += 1; //Incrememt number of current rung that is falling
                    }
                }
                    
            }
        }//End if statement checking if game is still running
        
    }
    
    @Override
    void draw() {
        //If game is still running
        if (!gameOver) {
            //Iterate through array list of successfully placed rungs
            for (Rung rung : assembledRungs) {
                rung.draw(); //Draw each old rung onto the screen
            }
        
            //If a rung is currently dropping
            if (rungDropping) {
                currentRung.draw(); //Draw rung on the screen
            //If the player is deciding when to click the spacebar to let the rung fall
            } else {
                currentRung.draw(); //Draw rung on the screen
                currentRung.move(); //Draw rung moving left and right
            }
            
            // Display game information
            gameApp.fill(255);
            gameApp.textAlign(gameApp.CENTER, gameApp.CENTER);
            gameApp.textSize(18);
            gameApp.text("Rungs Left: " + (totalNumRungs - currentRungNumber), gameApp.width / 2, 50);
            gameApp.text("Rungs Needed: " + (11 - assembledRungs.size()), gameApp.width / 2, 80);
            gameApp.text(message, gameApp.width / 2, 110);
            if (rungDropping)
                message = "";
            else
                message = "Press SPACE to drop the rung!";
        }
        
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
            //If the spacebar is pressed
            if (gameApp.key == ' ') {
                rungDropping = true; //Set variable indicating that a rung is dropping to true;
            } //End if statement for when the spacebar is pressed
        } //End if statement checking if game is still running
        
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
