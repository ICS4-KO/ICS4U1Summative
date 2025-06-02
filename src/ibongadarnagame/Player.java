/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;

/**
 *
 * @author 343330528
 */
public class Player {
    //Instance Variables
    private int x, y; //Position of the character
    private String name; //Name of the player
    private int lives = 3;  //Player has 3 lives
    private int health; //Current health
    private int healthPoints; //Health points per life
    private int virtuePoints = 0; //Virtue points earned, starting from 0
    private int characterTraits; //0 means neutral, 1 means high strength, 2 means high intelligence
    private boolean playerDeath = false; //Marker for player death
    private Inventory inventory; //Player's inventory
    private PApplet app; //The canvas used to display graphical elements
    private PImage image; //Image of the character
    
    //Default Constants
    private static String DEFAULT_NAME = "Don Juan"; //Default player name
    private static String DEFAULT_CLOTHES = "images/redShirt.png"; //Default character image
    private static int NORMAL_HEALTH = 500; //Normal health per life
    private static int HIGH_HEALTH = 700; //More HP for players with high strength
    
    /**
     * Constructor creates a new instance of the Player class with the name entered and character clothing 
     * color selected by the player.
     * 
     * @param p                //Reference to canvas
     * @param x                //X position of character
     * @param y                //Y position of character
     * @param name             //Player name entered by the user
     * @param characterTraits  //Character trait distribution made by user
     * @param inventory        //Player's inventory object
     * @param imagePath        //Image of character based on user's selection
     */
    public Player(Papplet p, int x, int y, String name, int characterTraits, Inventory inventory, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.inventory = inventory;
        this.image = app.loadImage(imagePath);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.healthPoints = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPoints = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
    }
    
    /**
     * Constructor creates a new instance of the Player class with the name entered by the player, but default
     * image will be assigned as player did not select character clothing color
     * 
     * @param p                //Reference to canvas
     * @param x                //X position of character
     * @param y                //Y position of character
     * @param name             //Player name entered by the user
     * @param inventory        //Player's inventory object
     * @param characterTraits  //Character trait distribution made by user
     */
    public Player(Papplet p, int x, int y, String name, int characterTraits, Inventory inventory) {
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
            this.healthPoints = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPoints = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
    }
    
   /**
     * Constructor creates a new instance of the Player class with the character clothing color selected by the 
     * player, but default name will be assigned as user did not enter a name
     * 
     * 
     * @param p                //Reference to canvas
     * @param x                //X position of character
     * @param y                //Y position of character
     * @param characterTraits  //Character trait distribution made by user
     * @param inventory        //Player's inventory object
     * @param imagePath        //Image of character based on user's selection
     */
    public Player(Papplet p, int x, int y, int characterTraits, Inventory inventory, String imagePath) {
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
            this.healthPoints = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPoints = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
    }

   /**
     * Constructor creates a new instance of the Player class with the name and character image will be
     * assigned default values because name and clothing color were not selected by the user
     * 
     * @param p                //Reference to canvas
     * @param x                //X position of character
     * @param y                //Y position of character
     * @param characterTraits  //Character trait distribution made by user
     * @param inventory        //Player's inventory object
     */
    public Player(Papplet p, int x, int y, int characterTraits, Inventory inventory) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.inventory = inventory;
        this.image = app.loadImage(imagePath);
        
        //Set character name to default name
        this.name = DEFAULT_NAME;
        //Set character image to default image
        this.image = app.loadImage(DEFAULT_CLOTHES);
        
        //If characterTraits is equal to 1, character has high strengh
        if (characterTraits == 1) {
            //Set user HP to higher value if user has high strength
            this.healthPoints = HIGH_HEALTH;
        //Otherwise, characterTrait is equal to 0 or 2 and the character has low to medium strength
        } else {
            //Assign low to medium strength characterTrait distribution
            this.healthPoints = NORMAL_HEALTH;
        } //End if statement for assigning initial health points
    }      
            
    /**
     * Removes specified amount of HP from user's health
     * 
     * @param amount  //If user
     * @return 
     */
    public boolean takeDamage(int amount) {
        if (amount > healthPoints) {
            subtractFromNextLife = amount - healthPoints;
            if (lives > 1) {
                lives--;
                
            } else {
                
            }
        }
        healthPoints -= amount;
        if (healthPoints <= 0)
            playerDeath = true;
        return playerDeath;
    }    
    
            
            
+ addVirtue(int points)
+ getName():String
+ getLives():int
+ getHealth():int
+ getVP():int
+ setLives(int numLives)


}
