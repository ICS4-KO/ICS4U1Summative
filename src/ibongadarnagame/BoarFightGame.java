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
    private int playerHealth = 100;
    private int boarHealth = 150;
    private int playerAttackPower = 5;
    private int boarAttackPower = 30;
    private int attackInterval = 800;
    private int timer = 0;
    private int neutralInterval = 300;
    //Score is based on how much life they have left
    
    private int HPLost = 0; //Amount of damage taken by the user
    PImage boar;
    PImage player;
    
    // New variables for player position and movement speed
    private int playerX = 112; // Initial x position
    private int playerY = 78;  // Initial y position
    private int playerSpeed = 15; // Pixels per key press
    private int boarX = 550; // Initial x position
    private int boarY = 420;  // Initial y position
    private int boarSpeed = 2; //Boar's speed
    boolean boarAttackTurn = true;
    boolean neutralTime = false;
    boolean gameWon = false;
    

    public BoarFightGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution) {
        super(p, name, maxScore, gameIndex, chosenCharacter);
        if (traitsDistribution == 1) //If player has high strength
            playerHealth = 120; //Set higher health points to start with
        
        boar = gameApp.loadImage("images/boar.png");
        player = gameApp.loadImage(chosenCharacter);
        boar.resize(166, 122); //Make boar smaller for the minigame
        player.resize(118, 230); //Make character smaller for the minigame
    }

    @Override
    void update() {
        if (!gameOver) {
            timer++;
            if (neutralTime) {
                if (timer >= neutralInterval)
                    neutralTime = false;
            } else {
                if (timer >= attackInterval) {
                    boarAttackTurn = !boarAttackTurn;
                    timer = 0;
                    neutralTime = true;
                    return;
                }
            
                if (isColliding()) {
                    if (boarAttackTurn) {
                        playerHealth -= boarAttackPower;
                        neutralTime = true;
                        timer = 0;
                        boarAttackTurn = false;
                        
                        if (playerHealth <= 0) {
                            playerHealth = 0;
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
            gameApp.textSize(20);
            gameApp.text("Player Health: " + playerHealth, gameApp.width / 2, 50);
            gameApp.text("Boar Health: " + boarHealth, gameApp.width / 2, 100);
            if (boarAttackTurn)
                gameApp.text("Boar's  Attack " + timer, gameApp.width / 2, 150);
            else
                gameApp.text("Your Attack " + timer, gameApp.width / 2, 150);
            if (neutralTime)
                gameApp.text("Netural", gameApp.width / 2, 200);

        } else {
            // Display game over message
            gameApp.fill(0);
            gameApp.text("Game Over", gameApp.width / 2, gameApp.height / 2);
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
}
