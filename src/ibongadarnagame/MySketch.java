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
    //Variables for Stage -3 (Customize Clothes)
    boolean chooseCharacter1 = true;  //True if character clothing v1 is currently displayed
    boolean chooseCharacter2 = false; //True if character clothing v2 is currently displayed
    
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
    
    //Buttons for Stage -1 (Character Setup)
    boolean overChooseName = false; //Set variable indicating mouse is over button to false
    int chooseNameX = 0; //Set x position of choose name button
    int chooseNameY = 0; //Set y position of choose name button
    boolean overCustomize = false; //Set variable indicating mouse is over button to false
    int customizeX = 0; //Set x position of customize button
    int customizeY = 0; //Set y position of customize button
    boolean overStart = false; //Set variable indicating mouse is over button to false
    int startX = 0; //Set x position of start button
    int startY = 0; //Set y position of start button
    
    //Buttons for Stage -3 (Customize Clothes)
    boolean overLeftArrow = false; //Set variable indicating mouse is over left arrow button to false
    int leftArrowX = 0; //Set x position of left arrow button
    int leftArrowY = 0; //Set y position of left arrow button
    boolean overRightArrow = false; //Set variable indicating mouse is over right arrow button to false
    int rightArrowX = 0; //Set x position of right arrow button
    int rightArrowY = 0; //Set y position of right arrow button
            
     
    //Declare images
    //Declare images for Stage 0 (Main Menu)
    PImage testimg; ////
    PImage homeScreen;     //Homescreen background
    PImage startGame;      //Start game button
    PImage leaderboard;    //Leaderboard button
    PImage exitGame;       //Exit game button
    //Declare images for Stage -1 (Character Setup)
    PImage characterSetup;  //Character setup background
    PImage chooseNameButton;  //Custom name button
    PImage customizeButton;   //Custom clothes button
    PImage startButton;       //Start button
    //Declare images for Stage -3 (Custom Clothes)
    PImage currentChoice;  //Current character choice (v1/v2/v3)
    PImage character1;     //Character clothes v1
    PImage character2;     //Character clothes v2
    PImage character3;     //Character clothes v3
    PImage leftButton;     //Left arrow button
    PImage rightButton;    //Right arrow button
    
    
    
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
        characterSetup = loadImage("images/charactersetupbg.jpg");    //Character setup background
        chooseNameButton = loadImage("images/choosenamebutton.jpg");  //Custom name button
        customizeButton = loadImage("images/customizebutton.jpg");    //Custom clothes button
        startButton = loadImage("images.startbutton.jpg");            //Start button
        
        //Load images for Stage -3 (Custom Clothes)
        character1 = loadImage("images.character1.jpg");    //Character clothes v1
        character2 = loadImage("images.character2.jpg");    //Character clothes v2
        character3 = loadImage("images.character3.jpg");    //Character clothes v3
        leftButton = loadImage("images.leftbutton.jpg");    //Left arrow button
        rightButton = loadImage("images.rightbutton.jpg");  //Right arrow button
        
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
            
        //Character Setup
        } else if (stage == -1) {
            //Set character setup background
            background(characterSetup);
            
            //Display button options
            image(chooseNameButton, chooseNameX, chooseNameY);  //Choose name button
            image(customizeButton, customizeX, customizeY);     //Customize clothes button
            image(startButton, startX, startY);                 //Start button
            
            
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
            
            
        } else if (stage == -3) {
            
            
            //If user's choice is currently character 1, the image is shown on the screen
            if (chooseCharacter1)
                currentChoice = character1; //Set current character clothing choice to be displayed as character v1
            else if (chooseCharacter2)
                currentChoice = character2; //Set current character clothing choice to be displayed as character v2
            else
                currentChoice = character3; //Set current character clothing choice to be displayed as character v3
            
            //Draw user's current character clothing choice (v1/v2/v3)
            image(currentChoice, 0, 0);
                
            
            //Display button options
            image(leftButton, leftArrowX, leftArrowY); 
            image(rightButton, rightArrowX, rightArrowY); 
            
        
        } else if (stage == 1) {
            if (chooseCharacter1)
                //Player player = new Player().... "character1";
                System.out.println("a");
            else if (chooseCharacter2)
                System.out.println("b");
            else
                System.out.println("c");
                
        } 
    }
    
    
    public static void gameOver() {
        ////unfinished
    }
    
    void update(int x, int y) {
        //Set all boolean variables indicating that mouse is over a specific button to false (reset)
        //Variables for Stage 0 (Main Menu)
        overStartGame = false;   //Start game button
        overLeaderboard = false; //Show leaderboard button
        overExitGame = false;    //Exit game button
        //Variables for Stage -1 (Character Setup)
        overChooseName = false;  //Choose name button
        overCustomize = false;   //Customize clothes button
        overStart = false;       //Start button
        //Variables for Stage -3 (Customize Clothes)
        overLeftArrow = false;   //Left arrow button
        overRightArrow = false;  //Right arrow button
       
    
    
        //Set boolean variable of a button to true if the mouse if over it (used for button clicks)
        //Mouse is over a specific button in Stage 0 (Main Menu)
        if (overImage(startGame, startGameX, startGameY)) //Start game button
            overStartGame = true; //Set variable indicating mouse is over start game button to true
        else if (overImage(leaderboard, leaderboardX, leaderboardY)) //Show leaderboard button
            overLeaderboard = true; //Set variable indicating mouse is over leaderboard button to true
        else if (overImage(exitGame, exitGameX, exitGameY)) //Exit game button
            overExitGame = true; //Set variable indicating mouse is over exit gamebutton to true
        
        //Mouse is over a specific button in Stage 1 (Character Setup)
        else if (overImage(chooseNameButton, chooseNameX, chooseNameY)) //Choose name button
            overChooseName = true; //Set variable indicating mouse is over choose name button to true
        else if (overImage(customizeButton, customizeX, customizeY)) //Customize clothes button
            overCustomize = true; //Set variable indicating mouse is over customize clothes button to true
        else if (overImage(startButton, startX, startY)) //Start button
            overStart = true; //Set variable indicating mouse is over start button to true
        
        //Mouse is over a specific button in Stage -3 (Customize Clothes)
        else if (overImage(leftButton, leftArrowX, leftArrowY)) //Left arrow button
            overLeftArrow = true; //Set variable indicating mouse is over left arrow button to true
        else if (overImage(rightButton, rightArrowX, rightArrowY)) //Right arrow button
            overRightArrow = true; //Set variable indicating mouse is over right arrow button to true
        
       
    
        
        //If mouse is hovering over the the 
        if (overImage(testimg, testImgX, testImgY))
            overTestImg = true;
        else
            overTestImg = false; ////
        
        
            
    }
    
    @Override
    public void mousePressed() {
        //Stage 0 (Main Menu)
        if (stage == 0) {
            //If mouse is over start game button when mouse is clicked
            if (overStartGame) {
                stage = -1; //Go to game/character setup screen
            //If user is over leaderboard button when mouse is clicked
            } else if (overLeaderboard) {
                stage = -3; //Go to leaderboard screen
            //If user is over exit game button when mouse is clicked
            } else if (overExitGame) {
                System.exit(0); //Exit game
            ////
        /**} else if (stage == -1) {
            if (overImage(customname)) {
              customName = true;
            } 
            if (overImage(customcharacter)) {
              customCharacter = true;
            } 
*////
            }
        //Stage -1 (Character Setup)
        } else if (stage == -1) {
            //If mouse is over choose name button when mouse is clicked
            if (overChooseName) 
                stage = -2; //Go to choose name screen
            //If mouse is over customize clothes button when mouse is clicked
            else if (overCustomize) 
                stage = -3; //Go to customize character screen
            //If mouse is over start button when mouse is clicked
            else if (overStart) 
                stage = 1; //Begin story
            
        //Stage -3 (Customize Clothes)
        } else if (stage == -3) {
            //If mouse is over left arrow button when mouse is clicked
            if (overLeftArrow)
                //If character clothing v1 is currently being shown, show v3 (v1 is false and v2 is false)
                if (chooseCharacter1) {
                    chooseCharacter1 = false; //Set variable indicating character v1 is showing to false
                //If character clothing v2 is currently being shown
                } else if (chooseCharacter2) {
                    chooseCharacter2 = false; //Set variable indicating character v2 is showing to false
                    chooseCharacter1 = true;
                } else {
                    chooseCharacter2 = true;
                }
                            
            //If mouse is over right arrow button when mouse is clicked
            else if (overRightArrow)
                if (chooseCharacter1) {
                    chooseCharacter1 = false;
                    chooseCharacter2 = true;
                } else if (chooseCharacter2) {
                    chooseCharacter2 = false;
                } else {
                    chooseCharacter1 = true;
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
