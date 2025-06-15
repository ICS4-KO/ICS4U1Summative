/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;

//Import pakcages
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Rung class of interactive story game Ibong Adarna. Each EscapeWellGame has a Rung object that represents each rung the player
 * uses to create the ladder.
 * 
 * @author   Kimi Ong
 * @version  1.0
 * @since    2025-06-15
 */
public class Rung {
    //Instance Variables
    public int x, y; //Position of the rung
    private int width; //Width of image
    public int height; //Height of image
    private int fallingSpeed = 5; //Speed the rung falls at
    private int sidewaysSpeed = 3; //Speed at which the rung moves left and right
    private boolean movingRight = true; //True if rung is moving to the right
    private PApplet rungApp; //The canvas used to display graphical elements
    private PImage image; //Image of rung
    
    
    /**
     * Constructor creates a new instance of the Rung class in the Escape Well game
     * 
     * @param p   Reference to canvas
     * @param x   X position of rung
     * @param y   Y position of rung
     */
    public Rung(PApplet p, int x, int y) {
        this.rungApp = p; //Papplet reference
        this.x = x; //Set x postion of rung
        this.y = y; //Set y position of rung
        this.image = rungApp.loadImage("images/rung.png"); //Load and set image of the rung
        this.width = image.width; //Set width of image
        this.height = image.height; //Set height of image
    }

    
    /**
     * Moves rung down the screen to show that it is falling
     */
    public void rungFalling() {
        y += fallingSpeed; //Increase y position of rung
    } //End rung falling method
    
    /**
     * Checks if Rung object is colliding with another rung from the top
     * 
     * @param rung  Previous Rung object
     * @return      Returns true is collision is detected
     */
    public boolean isCollidingWith(Rung rung) {
        //Check if the bounding boxes of the two rungs intersect (collide vertically)
        boolean isAboveOtherBottom = y < rung.y + rung.height;
        boolean isBelowOtherTop = y + height > rung.y;
        //Check if the current rung is lined up with the previous rung (within certain pixel width)
        boolean isWithinWidthLimit = (rung.x - 10) < x && x < (rung.x + 10); 

        //Return true if a collision is detected
        return isAboveOtherBottom && isBelowOtherTop && isWithinWidthLimit;
    } //End rung collision method
    
    /**
     * Draws the rung on the screen
     */
    public void draw() {
        rungApp.image(image, x, y); //Draw the image at the rung's position
    } //End draw method
    
   /**
     * Moves the rung left and right across the screen
     */
    public void move() {
        //If the rung is moving right
        if (movingRight) {
            x += sidewaysSpeed; //Add horizontal speed to rung's x position
            if (x + image.width >= 550) //Once the rung passes a certain point on the screen
                movingRight = false; //Set variable to indicate that the rungs is moving left
        //If the rung is moving left
        } else {
            x -= sidewaysSpeed; //Subtract horizontal speed from rung's x position
            if (x <= 250) //Once the rung passes a certain point on the screen
                movingRight = true; //Set variable to indicate that the rungs is moving right
        } //End if statement checking if the rung is moving right or left   
    } //End move method
    
} //End Rung class