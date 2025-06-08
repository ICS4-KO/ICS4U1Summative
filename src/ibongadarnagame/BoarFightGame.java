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
public class BoarFightGame extends Game {
    //Instance variables
    private int playerHealth = 100; //Default player health
    private int fullHealth = 100; //Full player health
    private int boarHealth = 150; //Boar health
    private int playerAttackPower = 5; //Damage dealt with each player attack
    private int boarAttackPower = 40; //Damage dealt with each boar attack
    private int timer = 0; //Timer to keep track of attack/neutral intervals
    private int attackInterval = 800; //Time duration of player's attack, max. duration of boar's attack
    private int neutralInterval = 300; //Time duration of neutral intervals between attack turns
    private int totalTime = 0; //Time it takes the user to complete the game
    private int playerX = 112; //Initial x position of player 
    private int playerY = 78;  //Initial y position of player
    private int playerSpeed = 15; // Player's speed (pixels per key press)
    private int boarX = 550; //Initial x position of boar
    private int boarY = 420;  //Initial y position of boar
    private int boarSpeed = 2; //Boar's speed
    boolean boarAttackTurn = true; //True when it is the boar's turn to attack
    boolean neutralTime = false; //True during the neutral times between the player and boar's turns
    private String message = "Boar's Turn"; //Tells player whose attack turn it is
    private String background; //Current background image path
    
    //Declare images
    PImage boar; //Boar
    PImage player; //Player
    PImage gameBG; //Minigame background (player's turn)
    PImage mediumGameBG; //Medium minigame background (neutral turn)
    PImage darkGameBG; //Dark minigame background (boar's turn)
    PImage boarGameVictory; 
    PImage boarGameDefeat;
    
    
    
    
    //boolean gameWon = false; //True if user wins the game
    

    
    public BoarFightGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution) {
        super(p, name, maxScore, gameIndex, chosenCharacter);
        if (traitsDistribution == 1) { //If player has high strength 
            playerHealth = 120; //Set higher health points to start with
            fullHealth = 120;
        }
        
        //Load Images
        gameBG = gameApp.loadImage("images/gamebg.jpg"); //Load minigame background
        mediumGameBG = gameApp.loadImage("images/mediumgamebg.jpg"); //Load medium minigame background
        darkGameBG = gameApp.loadImage("images/darkgamebg.jpg"); //Load dark minigame background
        boar = gameApp.loadImage("images/boar.png"); //Load boar image
        boarGameVictory = gameApp.loadImage("images/boargamevictory.jpg"); 
        boarGameDefeat = gameApp.loadImage("images/boargamedefeat.jpg"); 
        
        player = gameApp.loadImage(chosenCharacter); //Load character image
        boar.resize(166, 122); //Make boar smaller for the minigame
        player.resize(118, 230); //Make character smaller for the minigame
        
        gameOver = false; ////
        gameWon = false; ////
    }

    @Override
    void update() {
        if (!gameOver) {
            timer++;
            if (neutralTime) {
                if (timer >= neutralInterval) {
                    totalTime += neutralInterval;
                    neutralTime = false;
                }
            } else {
                if (timer >= attackInterval) {
                    boarAttackTurn = !boarAttackTurn;
                    totalTime += attackInterval;
                    timer = 0;
                    neutralTime = true;
                    return;
                }
            
                if (isColliding()) {
                    if (boarAttackTurn) {
                        playerHealth -= boarAttackPower;
                        neutralTime = true;
                        totalTime += timer;
                        timer = 0;
                        boarAttackTurn = false;
                        
                        if (playerHealth <= 0) {
                            playerHealth = 0;
                            gameWon = false;
                            gameOver = true;
                        }
                    } else if (!boarAttackTurn){
                        if (boarHealth <= 0) {
                                boarHealth = 0;
                                gameWon = true;
                        }
                    }
                
                }
            }
        }
    }

    @Override
    void draw() {
        //Set minigame background
        if (neutralTime)
            gameApp.background(mediumGameBG);
        else if (boarAttackTurn)
            gameApp.background(darkGameBG);
        else gameApp.background(gameBG);
        
        if (!gameOver) {
            //Draw character
            gameApp.image(player, playerX, playerY);
            //Draw boar
            gameApp.image(boar, boarX, boarY);
            if (!neutralTime)
                moveBoar();
            
            
            
            
            // Display health bars
            gameApp.fill(0);
            gameApp.textAlign(gameApp.CENTER, gameApp.CENTER);
            gameApp.textSize(18);
            gameApp.text("Player Health: " + playerHealth + " " + totalTime/60, gameApp.width / 2, 50);
            gameApp.text("Boar Health: " + boarHealth, gameApp.width / 2, 80);
            gameApp.text(message, gameApp.width / 2, 110);
            if (neutralTime)
                message = "No one's turn - Get ready!";
            else if (boarAttackTurn)
                message = "Boar's Turn - Run away from the boar!";
            else if (isColliding())
                message = "Your Turn - Click on the boar now!";
            else
                message = "Your Turn - Get close to the boar!";
            

        } else {
            calculatePoints(); 
            damageTaken = fullHealth - playerHealth;
            
            if (gameWon) {
                gameApp.background(boarGameVictory);
            } else {
                gameApp.background(boarGameDefeat);
            }
            
            // Display game over message
            gameApp.textAlign(gameApp.LEFT, gameApp.LEFT);
            gameApp.textSize(24);
            gameApp.text(returnGameResults(), 245, 240);
            /**
            gameApp.text("Total Time:  " + Math.round(totalTime), gameApp.width / 2, 230);
            gameApp.text("Points Earned:  " + score, gameApp.width / 2, 280);
            gameApp.text("Damage Taken:  " + damageTaken, gameApp.width / 2, 330);
            * */
            
        }
    }

    @Override
    void mousePressed() {
        if (!gameOver && isColliding() && !boarAttackTurn && !neutralTime) {
            boarHealth -= playerAttackPower;
            if (boarHealth <= 0) {
                boarHealth = 0;
                gameWon = true;
                gameOver = true;
            }
        }
    }
    
    // New method to handle key presses for player movement
    @Override
    void keyPressed() {
        if (!gameOver) {
                if (gameApp.keyCode == gameApp.UP) {
                    playerY -= playerSpeed;
                } else if  (gameApp.keyCode == gameApp.DOWN) {
                    playerY += playerSpeed;
                } else if  (gameApp.keyCode == gameApp.LEFT) {
                    playerX -= playerSpeed;
                } else if  (gameApp.keyCode == gameApp.RIGHT) {
                    System.out.println("right");
                    playerX += playerSpeed;
                }
                // Keep player within window bounds
                playerX = gameApp.constrain(playerX, 0, gameApp.width - 118); // Adjust for player image width
                playerY = gameApp.constrain(playerY, 0, gameApp.height - 230); // Adjust for player image height
        } else {
            if (gameApp.key == ' ') {
                ((MySketch) gameApp).stage = 6; //Walking in the Forest
                ((MySketch) gameApp).player.moveTo(-90, 255); //Set new player position
            }
        }
        
    }
    
    private boolean isColliding() {
        // Check if the bounding boxes of the two persons intersect
        boolean isLeftofOtherRight = playerX < boarX + boar.width;
        boolean isRightofOtherLeft = playerX + player.width > boarX;
        boolean isAboveOtherBottom = playerY < boarY + boar.height;
        boolean isBelow0therTop = playerY + player.height > boarY;
        return isLeftofOtherRight && isRightofOtherLeft
        && isAboveOtherBottom && isBelow0therTop;
    }
    
    private void moveBoar() {
        if (boarAttackTurn) {
                if (playerX > boarX)
                    boarX += boarSpeed;
                else if (playerX < boarX)
                    boarX -= boarSpeed;

                if (playerY > boarY)
                    boarY += boarSpeed;
                else if (playerY < boarY)
                    boarY -= boarSpeed;
        } else if (!boarAttackTurn){
                if (playerX > boarX)
                    boarX -= boarSpeed;
                else if (playerX < boarX)
                    boarX += boarSpeed;

                if (playerY > boarY)
                    boarY -= boarSpeed;
                else if (playerY < boarY)
                    boarY += boarSpeed;
        }
        // Keep boar within window bounds
        boarX = gameApp.constrain(boarX, 0, gameApp.width - boar.width); 
        boarY = gameApp.constrain(boarY, 0, gameApp.height - boar.height); 
    }
    
    
    public void calculatePoints() {
        if (totalTime >= 3300)
            score = 5;
        else if (totalTime >= 2800)
            score = 10;
        else if (totalTime >= 2300)
            score = 15;
        else if (totalTime >= 1800)
            score = 20;
        else
            score = 25;
        
        if (!gameWon)
            score -= 5;
    }
    
    
    
    @Override
    public String returnGameResults() {
        return super.returnGameResults() + "Total Time: " + totalTime;
    }
}
