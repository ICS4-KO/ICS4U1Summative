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
    private PImage image;
    private int width; //Width of image
    public int height; //Height of image
    private int fallingSpeed = 5; //Speed the rung falls at
    private boolean movingRight = true; //True if rung is moving to the right
    private int sidewaysSpeed = 3; //Speed the rung moves left and right
    
    /**
     * Constructor creates a new instance of the Rung class in the Escape Well game
     * 
     * @param p                Reference to canvas
     * @param x                X position of rung
     * @param y                Y position of rung
     */
    public Rung(PApplet p, int x, int y) {
        this.rungApp = p;
        this.x = x;
        this.y = y;
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
        //Check if the bounding boxes of the two rungs intersect
        //boolean isLeftOfOtherRight = x < rung.x + rung.width;
        //boolean isRightofOtherLeft = x + width > rung.x;
        boolean isAboveOtherBottom = y < rung.y + rung.height;
        boolean isBelowOtherTop = y + height > rung.y;
        boolean isWithinWidthLimit = (rung.x - 5) < x && x < (rung.x + 5);

        return isAboveOtherBottom && isBelowOtherTop && isWithinWidthLimit;
    }
    
    public void draw() {
        rungApp.image(image, x, y); //Draw the image at the rung's position
    }
    
    public void move() {
        if (movingRight) {
            x += sidewaysSpeed; //Move right
            if (x + image.width >= 550)
                movingRight = false;
        } else {
            x -= sidewaysSpeed; //Move left
            if (x <= 250)
                movingRight = true;
        }
            
            
    }
}
