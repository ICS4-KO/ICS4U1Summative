/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author 343330528
 */
public class Rung {
    //Instance Variables
    public int x, y; //Position of the rung
    private PApplet rungApp; //The canvas used to display graphical elements
    private PImage image; //Image of rung
    private int width; //Width of image
    public int height; //Height of image
    private int fallingSpeed = 5; //Speed the rung falls at
    private boolean movingRight = true; //True if rung is moving to the right
    private int sidewaysSpeed = 3; //Speed at which the rung moves left and right
    
    /**
     * Constructor creates a new instance of the Rung class in the Escape Well game
     * 
     * @param p                Reference to canvas
     * @param x                X position of rung
     * @param y                Y position of rung
     */
    public Rung(PApplet p, int x, int y) {
        this.rungApp = p; //Papplet reference
        this.x = x; //Set x postion of rung
        this.y = y; //Set y position of rung
        this.image = rungApp.loadImage("images/rung.png"); //Load and set image of the rung
        this.width = image.width; //Set width of image
        this.height = image.height; //Set height of image
        
    }

    
    public void rungFalling() {
        y += fallingSpeed; //Rung moves down the screen
        
    }
    
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

        return isAboveOtherBottom && isBelowOtherTop && isWithinWidthLimit;
    }
    
    public void draw() {
        rungApp.image(image, x, y); //Draw the image at the rung's position
    }
    
    public void move() {
        //If the rung is moving right
        if (movingRight) {
            x += sidewaysSpeed; //Move rung right
            if (x + image.width >= 550) //Once the rung passes a certain point on the screen
                movingRight = false; //Set variable to indicate that the rungs is moving left
        //If the rung is moving left
        } else {
            x -= sidewaysSpeed; //Move rung left
            if (x <= 250) //Once the rung passes a certain point on the screen
                movingRight = true; //Set variable to indicate that the rungs is moving right
        } //End if statement checking if the rung is moving right or left
            
            
    }
}


