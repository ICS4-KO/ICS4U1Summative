/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Player class of interactive story game Ibong Adarna, that represents the user and their game stats as they progress through
 * the story.
 * 
 * @author   Kimi Ong
 * @version  1.0
 * @since    2025-06-15
 */
public class Player {
    //Instance Variables
    public int x, y; //Position of the character
    private String name; //Name of the player
    private int characterTraits; //0 means neutral, 1 means high strength, 2 means high intelligence
    private Inventory inventory; //Player's inventory
    private int health; //Current health
    private int fullHealth; //Value of full health
    private int virtuePoints = 0; //Virtue points earned by the player
    private int gamePoints = 0; //Total points player earns from the minigames they play
    private int totalPoints = 0; //Combined virtue points and game points
    private int boarFightGamePoints = 0; //Points earned from boar fight minigame
    private int rhythmGamePoints = 0; //Points earned from rhythm minigame
    private int escapeWellGamePoints = 0; //Points earned from well escape minigame
    private boolean savedBrothers; //True if player chose to save their brothers, who were turned to stone
    private boolean forgaveBrothers = false; //True if player chose to forgive their brothers for their betrayal (after saving them)
    private PApplet app; //The canvas used to display graphical elements
    private PImage image; //Image of the character
    
    //Static Variables
    private static int numPlayers = 0; //Number of users who have played the game
    
    //Default Constants
    private static final String DEFAULT_NAME = "Don Juan"; //Default player name
    private static final String DEFAULT_CLOTHES = "images/chosenCharacter1.png"; //Default character image
    private static final int LOW_HEALTH = 130; //Low full health for players with high intelligence (low strength)
    private static final int NORMAL_HEALTH = 140; //Normal full health
    private static final int HIGH_HEALTH = 150; //Higher full health for players with high strength
    private static final int HEALTH_PER_FOOD = 15; //HP each food item is worth
    
    
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
        this.app = p; //Set reference to canvas
        this.x = x; //Set x position of character
        this.y = y; //Set y position of character
        this.name = name; //Set player's name
        this.inventory = inventory; //Set player's inventory
        this.image = app.loadImage(imagePath); //Set character image
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.fullHealth = HIGH_HEALTH;
        //If characterTraits is equal to 0, character has neutral strength/intelligence
        } else if (characterTraits == 0) {
            //Set user HP to slightly higher value if user has neutral strength/intelligence
            this.fullHealth = NORMAL_HEALTH;    
        //Otherwise, characterTrait is equal to 2 and the character has low strength
        } else {
            //Set user HP low value if user has high intelligence (low strenght)
            this.fullHealth = LOW_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = fullHealth;
        
        numPlayers += 1; //Add one to the number of players
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
        this.app = p; //Set reference to canvas
        this.x = x; //Set x position of character
        this.y = y; //Set y position of character
        this.name = name; //Set player's name
        this.inventory = inventory; //Set player's inventory
        
        //Set character image to default image
        this.image = app.loadImage(DEFAULT_CLOTHES);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.fullHealth = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.fullHealth = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = fullHealth;
        
        numPlayers += 1; //Add one to the number of players
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
        this.app = p; //Set reference to canvas
        this.x = x; //Set x position of character
        this.y = y; //Set y position of character
        this.inventory = inventory; //Set player's inventory
        this.image = app.loadImage(imagePath); //Set character image
        
        //Set character name to default name
        this.name = DEFAULT_NAME;
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.fullHealth = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.fullHealth = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = fullHealth;
        
        numPlayers += 1; //Add one to the number of players
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
        this.app = p; //Set reference to canvas
        this.x = x; //Set x position of character
        this.y = y; //Set y position of character
        this.inventory = inventory; //Set player's inventory
        
        //Set character name to default name
        this.name = DEFAULT_NAME;
        //Set character image to default image
        this.image = app.loadImage(DEFAULT_CLOTHES);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.fullHealth = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.fullHealth = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
        //Set player's initial health to full health
        health = fullHealth;
        
        numPlayers += 1; //Add one to the number of players
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
     * Getter method to return health attribute of Player object
     * 
     * @return  Health points remaining in current life
     */
    public int getHealth() {
        return health; //Return HP of current life
    }
    
    /**
     * Getter method to return fullHealth attribute of Player object
     * 
     * @return  Number of health points player has at full health
     */
    public int getFullHealth() {
        return fullHealth; //Return value of full health
    }
    
   /**
    * Getter method to return virtuePoints attribute of Player object
    * 
    * @return  Virtue points earned by the player so far
    */
    public int getVirtuePoints() {
        return virtuePoints; //Return player's virtue points
    }
    
    /**
     * Getter method to return gamePoints attribute of Player object
     * 
     * @return  Total points earned across all minigames played by the player
     */
    public int getGamePoints() {
        return gamePoints; //Return player's game points
    }
       
    /**
     * Method to calculate and set totalPoints attribute of Player object
     */
    public void calculateTotalPoints() {
        totalPoints = virtuePoints + gamePoints; //Add virtue points and game points together for the total points
    }
    
    /**
     * Getter method to return totalPoints attribute of Player object
     * 
     * @return  Total points virtue and game points earned by the player
     */
    public int getTotalPoints() {
        return totalPoints; //Return sum of player's virtue points and game points
    }
    
    /**
     * Getter method to return inventory attribute of Player object
     * 
     * @return  Inventory of player
     */
    public Inventory getInventory() {
        return inventory; //Return object representing player's inventory
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
     * Getter method to return the static variable numPlayers shared across all Player objects
     * 
     * @return  Number of players that have been created (played the game)
     */
    public static int getNumPlayers() {
        return numPlayers; //Return the number of Player objects that have been instantiated
    }
    
    /**
     * Getter method to return the constant HEALTH_PER_FOOD
     * 
     * @return  HP each food item is worth
     */
    public static int getHealthPerFood() { 
        return HEALTH_PER_FOOD; //Return the number of health points each food item is worth
    }
    
    /**
     * Sets the player's boar fight game points to the specified value
     * 
     * @param points  Points earned in the boar fight game
     */
    public void setBoarFightGamePoints(int points) {
        boarFightGamePoints = points; //Set amount of points
        gamePoints += points; //Add points to total number of game points the player has
    }
    
    /**
     * Sets the player's rhythm game points to the specified value
     * 
     * @param points  Points earned in the rhythm game
     */
    public void setRhythmGamePoints(int points) {
        rhythmGamePoints = points; //Set amount of points
        gamePoints += points; //Add points to total number of game points the player has
    }
    
    /**
     * Sets the player's well escape game points to the specified value
     * 
     * @param points  Points earned in the well escape game
     */
    public void setWellEscapePoints(int points) {
        escapeWellGamePoints = points; //Set amount of points
        gamePoints += points; //Add points to total number of game points the player has
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
     * Removes 1 food item from player's inventory and gives player a health boost
     */
    public void eatFood() {
        inventory.consumeFood(); //Remove 1 food item from the inventory
        healthBoost(HEALTH_PER_FOOD); //Increase player's health
    }
    
    /**
     * Adds specified number of health points to the player's health, health will not exceed their full health
     * 
     * @param points  Number of health points to add to the player's health
     */
    public void healthBoost(int points) {
        health += points; //Add points from health boost to player's health points
        //If the user's health in after adding the points is greater than their full health
        if (health > fullHealth) 
            health = fullHealth; //Set health points to full health (maximum amount)
    }

    /**
     * Removes specified amount of HP from player's health and calls game over method if user has no more lives
     * 
     * @param amount  Amount of damage taken
     */
    public void takeDamage(int amount) {
        //Check if the amount of damage is greater than the user's health 
        if (amount > health) {
            ((MySketch) app).playerDeath = true; //Set variable to indicate that player ran out of health
        //If the amount of damage is less than the user's health 
        } else {
            health -= amount; //Subtract damage from the user's HP 
        } //End if statement checking if damage is greater/less than user's health
    }    
    
    /**
     * Adds the specified number of points to the player's virtue points
     * 
     * @param points  Number of virtue points to add
     */
    public void addVirtue(int points) {
        virtuePoints += points; //Add amount to user's virtue points
    }

    /**
     * Draws the character on the screen
     */
    public void draw() {
        app.image(image, x, y); //Draw the image at the character's position
    }
    
    /**
     * Moves the character across the screen
     * 
     * @param dx  Horizontal speed, left or right
     * @param dy  Vertical speed, up or down
     */
    public void move(int dx, int dy) {
        x += dx; //Add horizontal speed to character's x position
        y += dy; //Add vertical speed to character's y position
    }
    
    /**
     * Set new position for the character
     * 
     * @param dx   New x position
     * @param dy   New y position
     */
    public void moveTo(int dx, int dy) {
        x = dx; //Set character's x position to the new position
        y = dy; //Set character's y position to the new position
    }
    
} //End Player class