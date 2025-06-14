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
public class Player {
    //Instance Variables
    public int x, y; //Position of the character
    private String name; //Name of the player
    private int lives = 3;  //Player has 3 lives
    private int health; //Current health
    private int healthPerLife; //Health points per life
    private int virtuePoints = 0; //Virtue points earned, starting from 0
    private int characterTraits; //0 means neutral, 1 means high strength, 2 means high intelligence
    private boolean playerDeath = false; //Marker for player death
    private Inventory inventory; //Player's inventory
    private int[][] gameScores = new int[5][2]; //Array of player's mini-game scores
    private PApplet app; //The canvas used to display graphical elements
    private PImage image; //Image of the character
    
    private boolean savedBrothers; //True if player chose to save their brothers, who were turned to stone
    private boolean forgaveBrothers = false; //True if player chose to forgive their brothers for their betrayal (after saving them)
    
    //Default Constants
    private static String DEFAULT_NAME = "Don Juan"; //Default player name
    private static String DEFAULT_CLOTHES = "images/chosenCharacter1.png"; //Default character image
    private static int NORMAL_HEALTH = 500; //Normal health per life
    private static int HIGH_HEALTH = 700; //More HP for players with high strength
    
    /**
     * Constructor creates a new instance of the Player class with the name entered and character clothing 
     * color selected by the player.
     * 
     * @param p                Reference to canvas
     * @param x                X position of character
     * @param y                Y position of character
     * @param name             Player name entered by the user
     * @param characterTraits  Character trait distribution made by user
     * @param inventory        Player's inventory object
     * @param imagePath        Image of character based on user's selection
     */
    public Player(PApplet p, int x, int y, String name, int characterTraits, Inventory inventory, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.inventory = inventory;
        this.image = app.loadImage(imagePath);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.healthPerLife = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPerLife = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = healthPerLife;
    }
    
    /**
     * Constructor creates a new instance of the Player class with the name entered by the player, but default
     * image will be assigned as player did not select character clothing color
     * 
     * @param p                Reference to canvas
     * @param x                X position of character
     * @param y                Y position of character
     * @param name             Player name entered by the user
     * @param inventory        Player's inventory object
     * @param characterTraits  Character trait distribution made by user
     */
    public Player(PApplet p, int x, int y, String name, int characterTraits, Inventory inventory) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.inventory = inventory;
        
        //Set character image to default image
        this.image = app.loadImage(DEFAULT_CLOTHES);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.healthPerLife = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPerLife = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = healthPerLife;
    }
    
   /**
     * Constructor creates a new instance of the Player class with the character clothing color selected by the 
     * player, but default name will be assigned as user did not enter a name
     * 
     * 
     * @param p                Reference to canvas
     * @param x                X position of character
     * @param y                Y position of character
     * @param characterTraits  Character trait distribution made by user
     * @param inventory        Player's inventory object
     * @param imagePath        Image of character based on user's selection
     */
    public Player(PApplet p, int x, int y, int characterTraits, Inventory inventory, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.inventory = inventory;
        this.image = app.loadImage(imagePath);
        
        //Set character name to default name
        this.name = DEFAULT_NAME;
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.healthPerLife = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPerLife = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = healthPerLife;
    }

   /**
     * Constructor creates a new instance of the Player class with the name and character image will be
     * assigned default values because name and clothing color were not selected by the user
     * 
     * @param p                Reference to canvas
     * @param x                X position of character
     * @param y                Y position of character
     * @param characterTraits  Character trait distribution made by user
     * @param inventory        Player's inventory object
     */
    public Player(PApplet p, int x, int y, int characterTraits, Inventory inventory) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.inventory = inventory;
        
        //Set character name to default name
        this.name = DEFAULT_NAME;
        //Set character image to default image
        this.image = app.loadImage(DEFAULT_CLOTHES);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.healthPerLife = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPerLife = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = healthPerLife;
    }      
       
    
    
    
    /**
     * Getter method to return name attribute of Player object
     * 
     * @return  Player's name
     */
    public String getName() {
        return name; //Return player's name
    }
    
    /**
     * Getter method to return lives attribute of Player object
     * 
     * @return  Number of lives left
     */
    public int getLives() {
        return lives; //Return number of lives left (3 in total)
    }
    
    /**
     * Getter method to return health attribute of Player object
     * 
     * @return  Health points remaining in current life
     */
    public int getHealth() {
        return health; //Return HP of current life
    }
    
   /**
    * Getter method to return virtuePoints attribute of Player object
    * 
    * @return  Virtue points earned by the player so far
    */
    public int getVP() {
        return virtuePoints; //Return player's virtue points
    }
       
    
    /**
     * Getter method to return gameScores attribute of Player object
     * 
     * @return  Array of user's mini-game scores 
     */
    public int[][] getGameScores() {
        return gameScores; //Return player's 2D array of mini-game scores
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    /**
     * Getter method to return savedBrothers attribute of Player object
     * 
     * @return  True if player saved brothers
     */
    public boolean getSavedBrothers() {
        return savedBrothers; //Return variable indicating whether player saved brothers
    }
    
    /**
     * Getter method to return forgaveBrothers attribute of Player object
     * 
     * @return  True if player forgave brothers
     */
    public boolean getForgaveBrothers() {
        return forgaveBrothers; //Return variable indicating whether player forgave brothers
    }
    
    
    /**
     * Setter method to set savedBrothers attribute of Player object
     * 
     * @param answer  True if player chose to save their brothers, who were turned to stone
     */
    public void setSavedBrothers(boolean answer) {
        savedBrothers = answer; //Set variable indicating whether player saved brothers
    }
    
    /**
     * Setter method to set forgaveBrothers attribute of Player object
     * 
     * @param answer  True if player chose to forgive their brothers for their betrayal
     */
    public void setForgaveBrothers(boolean answer) {
        forgaveBrothers = answer; //Set variable indicating whether player forgave brothers
    }
            
    
    /**
     * Removes specified amount of HP from user's health and calls game over method if user has no more lives
     * 
     * @param amount  Amount of damage taken
     */
    public void takeDamage(int amount) {
        //Check if the amount of damage is greater than the user's health in the current life
        if (amount > health) {
            //If health is depleted and the user has at least one life left, go to the next life
            if (lives > 1) {
                lives -= 1; //Decrease number of lives by one
                health = healthPerLife; //Reset next life to full health
            //If health is depleted and the user has no more lives left, game over
            } else {
                MySketch.gameOver(); //Call game over method
            } //End if statement checking if user has lives left
        //If the amount of damage is less than the user's health in the current life
        } else {
            health -= amount; //Subtract damage from the user's HP in the current life
        } //End if statement checking if damage is greater/less than HP in current life
    }    
    
    /**
     * Adds the specified number of points to the user's virtue points
     * 
     * @param points  Number of virtue points to add
     */
    public void addVirtue(int points) {
        virtuePoints += points; //Add amount to user's virtue points
    }
    
    /**
     * Adds specified number of health points to the user's health in the current life, and transfers any surplus into the next life
     * if the user has less than 3 lives, Health after health boost cannot exceed 3 lives left with full health
     * 
     * @param points  Number of health points to add
     */
    public void healthBoost(int points) {
        health += points; //Add points from health boost to health points
        //If the user's health in the current life after adding the points is greater than their health per life
        if (health > healthPerLife) {
            int surplus = health - healthPerLife; //Store the surplus health points
            health = healthPerLife; //Set health points in current life to maximum amount (health per life)
            //If user has less than 3 lives, surplus can go to the next life
            if (lives < 3) {
                lives += 1; //Add back one of the user's lives if they had 1 or 2 lives before the health boost
                //If the surplus is less than or equal to their health per life
                if (surplus <= healthPerLife)
                    health = surplus; //Set health in new life to the surplus HP amount
                //If the suprlus is greater than their health per life
                else
                    health = healthPerLife; //Set health in new life to maximum amount (health per life)
            } //End if statement checking if user has less than 3 lives and surplus HP can transfer into additional life
        } //End if statement checking if user has a surplus of HP after health boost   
    }
    
    public void draw() {
        app.image(image, x, y); //Draw the image at the character's position
    }
    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public void moveTo(int dx, int dy) {
        x = dx;
        y = dy;
    }



}
