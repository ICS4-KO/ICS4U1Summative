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
    int buttonIndex = 0; ////
    int testImgX = 0; ////Set x position of button
    int testImgY = 0; ////Set y position of button
            
     
    //Declare images
    //Declare images for Stage 0 (Main Menu)
    PImage testimg; ////
    PImage title;          //Title of story
    PImage startGame;      //Start game button
    PImage leaderboard;    //Leaderboard button
    PImage exitGame;       //Exit game button
    //Declare images for Stage 1 (Start Game Screen)
    PImage character;      //Character
    PImage leftButton;     //Left arrow button
    PImage rightButton;    //Right arrow button
    PImage customname;     //Custom name button
    PImage customclothes;  //Custom clothes button
    
    public void settings() {
        size(800, 600); //Set size of frame
    }
    
    public void setup() {
        background(100, 100, 100); ////Set background
        textSize(20); //Set text size
        
        //Load images
        //Load images for Stage 0 (Main Menu)
        testimg = loadImage("testimg.png"); ////
        title = loadImage("images/title.jpg");                 //Title of story
        startGame = loadImage("images/startgame.jpg");         //Start game button
        leaderboard = loadImage("images/leaderboard.jpg");     //Leaderboard button
        exitGame = loadImage("images/exitGame.jpg");           //Exit game button
        //Load images for Stage 1 (Start Game Screen)
        character = loadImage("images/player,jpg");            //Character
        leftButton = loadImage("images/leftbutton.jpg");       //Left arrow button
        rightButton = loadImage("images/rightbutton.jpg");     //Right arrow button
        customname = loadImage("images/customname.jpg");       //Custom name button
        customclothes = loadImage("images/customclothes.jpg"); //Custom clothes button
        
    }
    
    public void draw() {
        background(255); //Reset background
        update(mouseX, mouseY); //Update variables indicating button clicks
        
        //Main menu
        if (stage == 0) {
            
            //Set background
            
            //Display title
            image(title, 0, 0);
            //Display button options
            rect(200, 200, 200, 30);
            image(testimg, 0, 0); ////
            
            
            image(startGame, testImgX, testImgY);   //Start game button
            image(leaderboard, 0, 0); //Show leaderboard button
            image(exitGame, 0, 0);    //Exit game button
            
        //Start game
        } else if (stage == -1) {
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
        //set all booleans in array to false
        
        //If mouse is hovering over the the 
        if (overImage(testimg, testImgX, testImgY))
            overTestImg = true;
        else
            overTestImg = false;
            
    }
    
    @Override
    public void mousePressed() {
        if (stage == 0) {
            if (overTestImg) {
                stage = 1;
            }////
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
    
     
   boolean overImage(PImage image, int x, int y)  {
        if (mouseX >= x && mouseX <= x+image.width && 
            mouseY >= y && mouseY <= y+image.height) {
          return true;
        } else {
          return false;
        }
    }
}
