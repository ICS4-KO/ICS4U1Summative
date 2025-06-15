/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * BoarFightGame class of interactive story game Ibong Adarna, a subclass of the Game class and representation of minigame #1.
 * The player must defeat a boar by taking turns attacking using collisions and mouse clicks.
 * 
 * @author   Kimi Ong
 * @version  1.0
 * @since    2025-06-15
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
    PImage boarGameVictory; //Boar fight victory screen
    PImage boarGameDefeat; //Boar fight defeat screen
    
    
    
    
    //boolean gameWon = false; //True if user wins the game
    

    
    public BoarFightGame(PApplet p, String name, int maxScore, int gameIndex, String chosenCharacter, int traitsDistribution) {
        //Call parent constructor with parameters for variables that all the minigames have
        super(p, name, maxScore, gameIndex, chosenCharacter);
        //If player has high strength 
        if (traitsDistribution == 1) { 
            playerHealth = 120; //Set higher health points to start with
            fullHealth = 120; //Set higher value as the player’s full health
        //If player has neutral strength/intelligence
        } else if (traitsDistribution == 0) {
            playerHealth = 110; //Set slightly higher health points to start with
            fullHealth = 110; //Set slightly higher value as the player’s full health
        } //End if statement checking for traits distribution
        
        //Load Images
        gameBG = gameApp.loadImage("images/gamebg.jpg"); //Load minigame background
        mediumGameBG = gameApp.loadImage("images/mediumgamebg.jpg"); //Load medium minigame background
        darkGameBG = gameApp.loadImage("images/darkgamebg.jpg"); //Load dark minigame background
        boar = gameApp.loadImage("images/boar.png"); //Load boar image
        boarGameVictory = gameApp.loadImage("images/gamevictory.jpg"); //Load boar fight victory screen
        boarGameDefeat = gameApp.loadImage("images/boargamedefeat.jpg"); //Load boar fight defeat screen
        player = gameApp.loadImage(chosenCharacter); //Load character image

        boar.resize(166, 122); //Make boar smaller for the minigame
        player.resize(118, 230); //Make character smaller for the minigame
        
    }

    @Override
    void update() {
        //If the game is still running
        if (!gameOver) {
            timer++; //Increment the timer
            //If the game is running and it is nobody’s attack turn
            if (neutralTime) {
	    //Check if the time that passed has surpassed the time interval when it is nobody’s attack turn (neutral period has ended)
                if (timer >= neutralInterval) {
                    totalTime += neutralInterval; //Add time length of neutral period to the total time user spends playing the game
                    neutralTime = false; //Set indicator that it is nobody’s attack turn to false
                } //End if statement checking if the neutral period has ended

            //If the game is running and it’s either the boar or the player’s attack turn
            } else {
	    //If the time that passed has surpassed the time interval of an attack turn (attack turn is over)
                if (timer >= attackInterval) {
                    boarAttackTurn = !boarAttackTurn; //Invert variable indicating whose attack turn it is
                    totalTime += attackInterval; //Add time length of attack turn to the total time user spends playing the game
                    timer = 0; //Reset timer /////this isn't in the neutral time statements
                    neutralTime = true; //Set variable indicating that is currently nobody’s attack turn to true
                    return; //Exit method before running collision check /////
                } //End if statement checking if attack turn is over
            
                //Check if boar and player are colliding
                if (isColliding()) {
	        //If they collide during the boar’s attack turn
                    if (boarAttackTurn) {
                        playerHealth -= boarAttackPower; //Subtract attack power of the boar from the player’s health
                        neutralTime = true; //Set variable indicating that is currently nobody’s attack turn to true
                        totalTime += timer; //Add time length of attack turn to the total time user spends playing the game
                        timer = 0; //Reset timer
                        boarAttackTurn = false; //Set variable indicating whose attack turn it is to the player’s
                        //If player’s health is depleted during the boar’s attack turn
                        if (playerHealth <= 0) {
                            playerHealth = 0; //Set player health to zero
                            gameWon = false; //Set variable indicating if player won the game to false
                            gameOver = true;  //Set variable indicating that the game is over to true
                        } //End if statement checking if player runs out of health during the boar’s attack
	        //If they collide during the player’s attack turn
                    } else if (!boarAttackTurn) {
                        //If boar’s health is depleted during the player’s attack turn
                        if (boarHealth <= 0) {
                                boarHealth = 0; //Set boar health to zero
                                gameWon = true; //Set variable indicating if player won the game to true
                        } //End if statement checking if boar runs out of health during the player’s attack 
                    } //End if statement checking whose attack turn it is when the boar and player collide
                
                } //End if statement checking if boar and player are colliding
            } //End if statement checking if the game is running and it’s either the boar or the player’s attack turn
        } //End if statement checking if the game is still running
    }

    @Override
    void draw() {
        //Set minigame background depending on whose turn it is
        //If it is nobody’s attack turn
        if (neutralTime)
            gameApp.background(mediumGameBG); //Set background to medium background
        //If it is the boar’s attack turn
        else if (boarAttackTurn)
            gameApp.background(darkGameBG); //Set background to dark background
        //If it is the player’s attack turn
        else 
            gameApp.background(gameBG); //Set background to light background
        
        //If the game is still running
        if (!gameOver) {
            //Draw character
            gameApp.image(player, playerX, playerY);
            //Draw boar
            gameApp.image(boar, boarX, boarY);
	//If it is either the boar or the player’s attack turn (not the neutral period)
            if (!neutralTime)
                moveBoar(); //Call method deciding the boar’s movements (towards/away from the player)
            
            
            //Display health and attack turn information in the top center of the screen
            gameApp.fill(0); //Set fill colour to black
            gameApp.textAlign(gameApp.CENTER, gameApp.CENTER); //Center text
            gameApp.textSize(18); //Set text size
            gameApp.text("Player Health: " + playerHealth + " " + totalTime/60, gameApp.width / 2, 50); //Display player’s health points
            gameApp.text("Boar Health: " + boarHealth, gameApp.width / 2, 80); //Display boar’s health points
            gameApp.text(message, gameApp.width / 2, 110); //Display whose attack turn it is
	//If it is nobody’s attack turn
            if (neutralTime)
                message = "No one's turn - Get ready!"; //Set message to tell the user that it is no one’s turn
	//If it is the boar’s attack turn
            else if (boarAttackTurn)
                message = "Boar's Turn - Run away from the boar!"; //Set message to tell the user to run away from the boar
	//If it is the player’s turn and the player is colliding with the boar
            else if (isColliding())
                message = "Your Turn - Click on the boar now!"; //Set message to tell the user to click on the boar
	//If it is the player’s turn and the player is not colliding with the boar
            else
                message = "Your Turn - Get close to the boar!"; //Set message to tell the user to get close to the boar
            

        //If the game is over
        } else {
            calculatePoints(); //Calculate the number of points the player earned 
            damageTaken = fullHealth - playerHealth; //Set damage taken to the player’s full health minus their current health
            
	//If the game is over and the player won the game
            if (gameWon) 
                gameApp.background(boarGameVictory); //Set background to display victory screen
	//If the game is over and the player lost the game
            else 
                gameApp.background(boarGameDefeat); //Set background to display defeat screen
            
            // Display game over message
            gameApp.textAlign(gameApp.LEFT, gameApp.LEFT); //Align text to the left
            gameApp.textSize(24); //Set text size
            gameApp.text(returnGameResults(), 230, 250); //Display game results
            //245, 250
            /**
            gameApp.text("Total Time:  " + Math.round(totalTime), gameApp.width / 2, 230);
            gameApp.text("Points Earned:  " + score, gameApp.width / 2, 280);
            gameApp.text("Damage Taken:  " + damageTaken, gameApp.width / 2, 330);
            * */
            
        } //End if statement checking if the game is still running
    }

    @Override
    void mousePressed() {
        //If the game is still running and the boar and player are colliding during the player’s turn
        if (!gameOver && isColliding() && !boarAttackTurn && !neutralTime) {
            boarHealth -= playerAttackPower; //Subtract attack power of the boar from the player’s health
	

	//If boar’s health is depleted during the player’s attack turn
            if (boarHealth <= 0) {
                boarHealth = 0; //Set boar health to zero
                gameWon = true; //Set variable indicating if player won the game to true
                gameOver = true; //Set variable indicating that the game is over to true
            } //End if statement checking if boar runs out of health during the player’s attack
        } //End if statement checking if the boar and player collide during the player’s attack turn
    }
    
    // New method to handle key presses for player movement
    @Override
    void keyPressed() {
        //If the game is still running
        if (!gameOver) {
	    //If the player presses the up arrow key
                if (gameApp.keyCode == gameApp.UP) {
                    playerY -= playerSpeed; //Move character up
	    //If the player presses the down arrow key
                } else if  (gameApp.keyCode == gameApp.DOWN) {
                    playerY += playerSpeed; //Move player down
	    //If the player presses the left arrow key
                } else if  (gameApp.keyCode == gameApp.LEFT) {
                    playerX -= playerSpeed; //Move player left
	    //If the player presses the right arrow key
                } else if  (gameApp.keyCode == gameApp.RIGHT) {
                    System.out.println("right");
                    playerX += playerSpeed; //Move player right
                } //End if statement checking which arrow key the player pressed

                // Keep player within window bounds
                playerX = gameApp.constrain(playerX, 0, gameApp.width - player.width); //X boundaries
                playerY = gameApp.constrain(playerY, 0, gameApp.height - player.height); //Y boundaries
        //If the game is over
        } else {
	//If the spacebar is pressed
            if (gameApp.key == ' ') { 
                ((MySketch) gameApp).player.setBoarFightGamePoints(score); //Set the points the player earned in the game
                ((MySketch) gameApp).gameResults.add(this); //Add game to array list of games that have been played
                ((MySketch) gameApp).player.takeDamage(damageTaken); //Transfer damage taken to player
                ((MySketch) gameApp).stage = 6; //Go to the next stage (Walking in the Forest)
                ((MySketch) gameApp).player.moveTo(-90, 255); //Set new player position
            } //End if statement for if the spacebar is pressed
        } //End if statement checking if the game is still running
        
    }
    
    private boolean isColliding() {
        // Check if the bounding boxes of the player and the boar intersect
        boolean isLeftofOtherRight = playerX < boarX + boar.width;
        boolean isRightofOtherLeft = playerX + player.width > boarX;
        boolean isAboveOtherBottom = playerY < boarY + boar.height;
        boolean isBelow0therTop = playerY + player.height > boarY;
        return isLeftofOtherRight && isRightofOtherLeft
        && isAboveOtherBottom && isBelow0therTop;
    }
    
    private void moveBoar() {
        //If it is the boar’s attack turn, make the boar follow the player
        if (boarAttackTurn) {
	    //If the player is to the right of the boar
                if (playerX > boarX)
                    boarX += boarSpeed; //Move boar to the right
	    //If the player is to the left of the boar
                else if (playerX < boarX)
                    boarX -= boarSpeed; //Move boar to the left

	    //If player is below boar
                if (playerY > boarY)
                    boarY += boarSpeed; //Move boar down
	    //If player is above boar
                else if (playerY < boarY)
                    boarY -= boarSpeed; //Move boar up
        //If it is the player’s attack turn, make the boar run from the player
        } else if (!boarAttackTurn){
	    //If the player is to the right of the boar
                if (playerX > boarX) 
                    boarX -= boarSpeed; //Move boar to the left
	    //If the player is to the left of the boar
                else if (playerX < boarX)
                    boarX += boarSpeed; //Move boar to the right

	    //If player is below boar
                if (playerY > boarY)
                    boarY -= boarSpeed;  //Move boar up
	    //If player is above boar
                else if (playerY < boarY)
                    boarY += boarSpeed; //Move boar down
        }
        // Keep boar within window bounds
        boarX = gameApp.constrain(boarX, 0, gameApp.width - boar.width); 
        boarY = gameApp.constrain(boarY, 0, gameApp.height - boar.height); 
    }
    
    
    public void calculatePoints() {
        //If the total time is greater than 3300
        if (totalTime >= 3300)
            score = 5; //Set very low player score
        //If the total time is greater than 3300
        else if (totalTime >= 2800)
            score = 10; //Set low player score
        //If the total time is greater than 3300
        else if (totalTime >= 2300)
            score = 15; //Set average player score
        //If the total time is greater than 3300
        else if (totalTime >= 1800)
            score = 20; //Set good player score
        //If the total time is any shorter than 3300
        else
            score = 25; //Set best player score
        
        //If the player lost the game
        if (!gameWon)
            score -= 5; //Deduct an amount from the points they earned
    }
    
    public int getTotalTime() {
        return (totalTime / 60);
    }
    
    @Override
    public String returnGameResults() {
        //Call parent method that returns game results shared across all games, add the total time from this game to the String that will be returned
        return super.returnGameResults() + "Total Time: " + (totalTime / 60) + " seconds";
    }

}

