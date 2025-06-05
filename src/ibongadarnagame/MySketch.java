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
public class MySketch extends PApplet {
    //Variables
    int stage = 0; //Current stage of the game starts at 0
    String userInput = ""; //Stores user input text
    //Variables for Stage -1 (Start Game)
    boolean customName = false; //True if user enters their own name for the character
    boolean customCharacter = false; //True if user customizes clothing colors of the character
    //Variables for Stage 0 (Main Menu)
    
    //Buttons
    //Buttons for Stage 0 (Main Menu)
    boolean overTestImg = false; ////Set variable indicating mouse is over button to false
    int testImgX = 0; ////Set x position of button
    int testImgY = 0; ////Set y position of button
    boolean overStartGame = false; //Set variable indicating mouse is over button to false
    int startGameX = 230; //Set x position of start game button
    int startGameY = 230; //Set y position of start game button
    boolean overLeaderboard = false; //Set variable indicating mouse is over button to false
    int leaderboardX = 230; //Set x position of show leaderboard button
    int leaderboardY = 320; //Set y position of show leaderboard button
    boolean overExitGame = false; //Set variable indicating mouse is over button to false
    int exitGameX = 230; //Set x position of exit game button
    int exitGameY = 410; //Set y position of exit game button
            
     
    //Declare images
    //Declare images for Stage 0 (Main Menu)
    PImage testimg; ////
    PImage homeScreen;     //Homescreen background
    PImage startGame;      //Start game button
    PImage leaderboard;    //Leaderboard button
    PImage exitGame;       //Exit game button
    //Declare images for Stage 1 (Start Game Screen)
    PImage character;      //Character
    PImage leftButton;     //Left arrow button
    PImage rightButton;    //Right arrow button
    PImage customname;     //Custom name button
    PImage customclothes;  //Custom clothes button
    //Declare images for Stage -1 (Character Setup)
    PImage characterSetup;  //Character setup background
    
    public void settings() {
        size(800, 600); //Set size of frame
    }
    
    public void setup() {
        background(100, 100, 100); ////Set background
        textSize(20); //Set text size
        
        //Load images
        //Load images for Stage 0 (Main Menu)
        testimg = loadImage("testimg.png"); ////
        homeScreen = loadImage("images/homescreen.jpg");         //Homescreen background
        startGame = loadImage("images/startgamebutton.jpg");     //Start game button
        leaderboard = loadImage("images/leaderboardbutton.jpg"); //Leaderboard button
        exitGame = loadImage("images/exitbutton.jpg");           //Exit game button
        /*
        //Load images for Stage 1 (Start Game Screen)
        character = loadImage("images/player.jpg");            //Character
        leftButton = loadImage("images/leftbutton.jpg");       //Left arrow button
        rightButton = loadImage("images/rightbutton.jpg");     //Right arrow button
        customname = loadImage("images/customname.jpg");       //Custom name button
        customclothes = loadImage("images/customclothes.jpg"); //Custom clothes button */
        
        //Load images for Stage -1 (Character Setup Screen)
        characterSetup = loadImage("images/charactersetupbg.jpg");
        
    }
    
    public void draw() {
        background(255); //Reset background
        update(mouseX, mouseY); //Update variables indicating button clicks
        
        //Main menu
        if (stage == 0) {
            //Set home screen background with title
            background(homeScreen);  
            
            //Display button options
            image(startGame, startGameX, startGameY);        //Start game button
            image(leaderboard, leaderboardX, leaderboardY);  //Show leaderboard button
            image(exitGame, exitGameX, exitGameY);           //Exit button
            
        //Start game
        } else if (stage == -1) {
            background(characterSetup);
            
            /**
            image(customname, 200, 500); 
            image(customclothes, 200, 500); 
            image(character, 200, 500); 
            
            if (customName) {
                text("Enter name:", 20, 50);
                text(userInput, 20, 100);
            }
            
            if (customCharacter) {
                image(leftButton, 200, 500); 
                image(rightButton, 200, 500); 
            }
            * */
            
            
        } else if (stage == -2) {
            image(character, 200, 500); 
            image(leftButton, 200, 500); 
            image(rightButton, 200, 500); 
        
        } else if (stage == -3) {
            image(character, 200, 500); 
            image(leftButton, 200, 500); 
            image(rightButton, 200, 500); 
            
        } else if (stage == 1) {
            fill(255, 255, 0);
            image(testimg, 50, 0);
        } 
    }
    
    
    public static void gameOver() {
        ////unfinished
    }
    
    void update(int x, int y) {
        //Set all boolean variables indicating that mouse is over a button to false
        overStartGame = false;
        overLeaderboard = false;
        overExitGame = false;
        
        if (overImage(startGame, startGameX, startGameY)) {
            overStartGame = true;
        } else if (overImage(leaderboard, leaderboardX, leaderboardY))
            overLeaderboard = true;
        else if (overImage(exitGame, exitGameX, exitGameY))
            overExitGame = true;
        
        //If mouse is hovering over the the 
        if (overImage(testimg, testImgX, testImgY))
            overTestImg = true;
        else
            overTestImg = false;
            
    }
    
    @Override
    public void mousePressed() {
        //Main menu
        if (stage == 0) { //Main menu
            //If mouse is over start game button when mouse is clicked
            if (overStartGame) {
                System.out.println("ab");
                stage = -1; //Go to game/character setup screen
                System.out.println("c");
            //If user is over leaderboard button when mouse is clicked
            } else if (overLeaderboard) {
                stage = -3; //Go to leaderboard screen
            //If user is over exit game button when mouse is clicked
            } else if (overExitGame) {
                System.exit(0); //Exit game
            ////
            /*
            if (overImage(startGame)) {
              stage = 1;
            }
            if (overImage(leaderboard)) {
              stage = -10;
            }
            if (overImage(exitGame)) {
              System.exit(0);
            }
        } else if (stage == -1) {
            if (overImage(customname)) {
              customName = true;
            } 
            if (overImage(customcharacter)) {
              customCharacter = true;
            } 
*////
            }
            
        }
    }
    
     
   boolean overImage(PImage image, int x, int y)  {
        if (mouseX >= x && mouseX <= x+image.width && 
            mouseY >= y && mouseY <= y+image.height) {
          return true;
        } else {
          return false;
        }
    }
}
