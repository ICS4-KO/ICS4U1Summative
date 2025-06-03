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
    String userInput = ""; //Stores user text
    //Variables for Stage -1 (Start Game)
    boolean customName = false; //True if user enters their own name for the character
    boolean customCharacter = false; //True if user customizes clothing colors of the character
    //Variables for Stage 0 (Main Menu)
     
    //Load images for Stage 0 (Main Menu)
    PImage title = loadImage("images/title.jpg");
    PImage startGame = loadImage("images/startgame.jpg");
    PImage leaderboard = loadImage("images/leaderboard.jpg");
    PImage exitGame = loadImage("images/exitGame.jpg");
    //Load images for Stage 1 (Start Game Screen)
    PImage character = loadImage("images/player,jpg");
    PImage leftButton = loadImage("images/leftbutton.jpg");
    PImage rightButton = loadImage("images/rightbutton.jpg");
    PImage customname = loadImage("images/customname.jpg");
    PImage customcharacter = loadImage("images/customcharacter.jpg");
    
    
    public void settings() {
        size(600, 500);
    }
    
    public void setup() {
        background(100, 100, 100); //Set background
        textSize(20); //Set text size
    }
    
    public void draw() {
        //Main menu
        if (stage == 0) {
            //Set background
            fill(255, 0, 0);
            //Display title
            image(title, 0, 0);
            //Display button options
            rect(200, 200, 200, 30);
            image(startGame, 0, 0);   //Start game button
            image(leaderboard, 0, 0); //Show leaderboard button
            image(exitGame, 0, 0);    //Exit game button
            
        //Start game
        } else if (stage == -1) {
            image(customname, 200, 500); 
            image(customcharacter, 200, 500); 
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
        }
    }
    
    
    public static void gameOver() {
        ////unfinished
    }
    
    
    @Override
    public void mousePressed() {
        if (stage == 0) {
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
        }
            
    }
    
    
    boolean overImage(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width && 
            mouseY >= y && mouseY <= y+height) {
          return true;
        } else {
          return false;
        }
    }
    
    boolean overImage(PImage image)  {
        if (mouseX >= image.x && mouseX <= image.x+image.width && 
            mouseY >= image.y && mouseY <= image.y+image.height) {
          return true;
        } else {
          return false;
        }
    }
}
