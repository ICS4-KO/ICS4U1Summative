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
    String enteredName = ""; //Player's name (based on user input, by default set to "Don Juan")
    //Variables for Stage -1 (Start Game)
    boolean customName = false; //True if user enters their own name for the character
    boolean customCharacter = false; //True if user customizes clothing colors of the character
    //Variables for Stage -2 (Choose Name)
    boolean nameError = false; //True if name entered by user is not between 1 - 10 characters long (error)
    //Variables for Stage -3 (Customize Clothes)
    boolean chooseCharacter1 = true;  //True if character clothing v1 is currently displayed
    boolean chooseCharacter2 = false; //True if character clothing v2 is currently displayed
    //Variables for Stage 1 (Begin Story)
    int currentScreen1 = 1; //Keeps track of the different screens being shown in the same stage
    int traitDistribution; //0 means neutral, 1 means high strength, 2 means high intelligence
    int currentStrengthPoints = 0; //Current number of character trait points assigned to strength
    int currentIntelligencePoints = 0; //Current number of character trait points assigned to intelligence
    boolean pointsError = false; //True if not all character trait points have been distributed (error0
    
    
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
    int chooseNameX = 230; //Set x position of choose name button
    int chooseNameY = 150; //Set y position of choose name button
    boolean overCustomize = false; //Set variable indicating mouse is over button to false
    int customizeX = 230; //Set x position of customize button
    int customizeY = 260; //Set y position of customize button
    boolean overStart = false; //Set variable indicating mouse is over button to false
    int startX = 230; //Set x position of start button
    int startY = 370; //Set y position of start button
    
    //Buttons for Stage -3 (Customize Clothes)
    boolean overLeftArrow = false; //Set variable indicating mouse is over left arrow button to false
    int leftArrowX = 70; //Set x position of left arrow button
    int leftArrowY = 245; //Set y position of left arrow button
    boolean overRightArrow = false; //Set variable indicating mouse is over right arrow button to false
    int rightArrowX = 602; //Set x position of right arrow button
    int rightArrowY = 245; //Set y position of right arrow button
    
    //Buttons for Stage 1 (Distrubute Character Points)
    boolean overPlusStrength = false; //Set variable indicating mouse is over plus strength button to false
    int plusStrengthX = 425; //Set x position of plus strength button
    int plusStrengthY = 270; //Set y position of plus strength button
    boolean overMinusStrength = false; //Set variable indicating mouse is over minus strength button to false
    int minusStrengthX = 320; //Set x position of minus strength button
    int minusStrengthY = 270; //Set y position of minus strength button
    boolean overPlusIntelligence = false; //Set variable indicating mouse is over plus intelligence button to false
    int plusIntelligenceX = 425; //Set x position of plus intelligence button
    int plusIntelligenceY = 370; //Set y position of plus intelligence button
    boolean overMinusIntelligence = false; //Set variable indicating mouse is over minus intelligence button to false
    int minusIntelligenceX = 320; //Set x position of minus intelligence button
    int minusIntelligenceY = 370; //Set y position of minus intelligence button
    
    
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
    //Declare images for Stage -2 (Choose Name)
    PImage chooseNameBG; //Choose name background
    PImage nameErrorMessage; //Error message for entered name length
    //Declare images for Stage -3 (Custom Clothes)
    PImage customizeClothesBG; //Customize clothes background
    PImage currentChoice;  //Current character choice (v1/v2/v3)
    PImage character1;     //Character clothes v1
    PImage character2;     //Character clothes v2
    PImage character3;     //Character clothes v3
    PImage leftButton;     //Left arrow button
    PImage rightButton;    //Right arrow button
    //Declare images for Stage 1 (Begin Story)
    PImage tellStory1;     //Tell the player about the story before they begin (part 1)
    PImage tellStory2;     //Tell the player about the story before they begin (part 2)
    PImage selectTraits;   //Character traits point distribution (strength/intelligence)
    PImage gameInstructions1;  //Give the player instructions before they start the story (part 1)
    PImage gameInstructions2;  //Give the player instructions before they start the story (part 2)
    PImage pressSpaceToContinue;  //Message telling the player to press space to continue
    PImage plusStrengthButton;       //Button to add one point to strength trait
    PImage minusStrengthButton;      //Button to subtract one point to strength trait
    PImage plusInteligenceButton;     //Button to add one point to intelligence trait
    PImage minusIntelligenceButton;    //Button to subtract one point to intelligence trait
    PImage zero;    //Zero points assigned to trait
    PImage one;     //One point assigned to trait
    PImage two;     //Two points assigned to trait
    PImage pointsErrorMessage; //Error message for if character trait points have not been distributed
    
    
    
    public void settings() {
        size(800, 600); //Set size of frame
    }
    
    public void setup() {
        background(100, 100, 100); ////Set background
        textSize(20); //Set text size
        Player player; //Player object representing character
        
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
        startButton = loadImage("images/startbutton.jpg");            //Start button
        
        //Load images for Stage -2 (Choose Name)
        chooseNameBG = loadImage("images/choosenamebg.jpg");  //Choose name background
        nameErrorMessage = loadImage("images/nameerrormessage.png");  //Error message for entered name length
        
        //Load images for Stage -3 (Custom Clothes)
        customizeClothesBG = loadImage("images/customizeclothesbg.jpg");  //Customize clothes background
        character1 = loadImage("images/character1.png");    //Character clothes v1
        character2 = loadImage("images/character2.png");    //Character clothes v2
        character3 = loadImage("images/character3.png");    //Character clothes v3
        leftButton = loadImage("images/leftbutton.jpg");    //Left arrow button
        rightButton = loadImage("images/rightbutton.jpg");  //Right arrow button
        
        //Load images for Stage 1 (Begin Story)
        tellStory1 = loadImage("images/story1.jpg");    //Part 1 of story introduction
        tellStory2 = loadImage("images/story2.jpg");    //Part 2 of story introduction
        selectTraits  = loadImage("images/selecttraits.jpg");   //Character traits point distribution (strength/intelligence)
        gameInstructions1 = loadImage("images/instructions1.jpg"); //Part 1 of game instructions
        gameInstructions2 = loadImage("images/instructions2.jpg"); //Part 2 of game instructions
                

        pressSpaceToContinue = loadImage("images/spacetocontinue.png");  //Press SPACE to continue message
        
        plusStrengthButton = loadImage("images/plusbutton.jpg");   //Button to add one point to strength trait
        minusStrengthButton = loadImage("images/minusbutton.jpg");   //Button to subtract one point to strength trait
        plusInteligenceButton = loadImage("images/plusbutton.jpg");   //Button to add one point to intelligence trait
        minusIntelligenceButton = loadImage("images/minusbutton.jpg");   //Button to subtract one point to intelligence trait
        
        zero = loadImage("images/zero.png");  //Zero points assigned to trait
        one = loadImage("images/one.png");  //One point assigned to trait
        two = loadImage("images/two.png");  //Two points assigned to trait
        
        pointsErrorMessage = loadImage("images/pointserror.png");  //Error message for incomplete point distribution
        
        
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
            
            
            
        //Choose Character's Name
        } else if (stage == -2) {  
            customName = true; //Set variable indicating that user entered their own name for the character to true
    
            //Set choose name background
            background(chooseNameBG);
            //Set fill colour to black for user input text
            fill(0);
            //Display user input text
            text(userInput, 450, 185);
            
            //If user presses enter to confirm their chosen name but the name is not between 1 and 10 characters long
            if(nameError) 
                image(nameErrorMessage, 225, 200); //Display error message regarding name length
            ////Remember to set it back to false and put default name "don juan" as well as clothes
            
            
        
        //Customize Character's Appearance
        } else if (stage == -3) {
            customCharacter = true; //Set variable indicating that user chose character's clothing to true
            
            //Set customize clothes background
            background(customizeClothesBG);
            
            //If user's choice is currently character 1, the image is shown on the screen
            if (chooseCharacter1)
                currentChoice = character1; //Set current character clothing choice to be displayed as character v1
            //If user's choice is currently character 2, the image is shown on the screen
            else if (chooseCharacter2)
                currentChoice = character2; //Set current character clothing choice to be displayed as character v2
            //If user's choice is currently character 3, the image is shown on the screen
            else
                currentChoice = character3; //Set current character clothing choice to be displayed as character v3
            
            //Draw user's current character clothing choice (v1/v2/v3)
            image(currentChoice, 0, 0);
                
            
            //Display button options
            image(leftButton, leftArrowX, leftArrowY);     //Left arrow button
            image(rightButton, rightArrowX, rightArrowY);  //Right arrow button
            
        
        //Begin Story
        } else if (stage == 1) {
            //Part 1 of story introduction
            if (currentScreen1 == 1)
                //Tell the player about the story before they begin (part 1)
                background(tellStory1);
            
            //Part 2 of story introduction
            else if (currentScreen1 == 2)
                //Tell the player about the story before they begin (part 2)
                background(tellStory2);
            
            //Character traits point distribution (strength/intelligence)
            else if (currentScreen1 == 3) {
                //Allow the character to distrubute two traits points between strength and intelligence
                background(selectTraits);
                
            //Display button options
            image(plusStrengthButton, plusStrengthX, plusStrengthY);         //Button to add point to strength trait
            image(minusStrengthButton, minusStrengthX, minusStrengthY);      //Button to subtract point from strength trait
            image(plusInteligenceButton, plusIntelligenceX, plusIntelligenceY);      //Button to add point to intelligence trait
            image(minusIntelligenceButton, minusIntelligenceX, minusIntelligenceY);  //Button to subtract point from intelligence trait

            //Current number of character trait points assigned to strength is 0
            if (currentStrengthPoints == 0)
                image(zero, 375, 270); //Display the number 0 for strength trait
            //Current number of character trait points assigned to strength is 1
            else if (currentStrengthPoints == 1)
                image(one, 375, 270); //Display the number 1 for strength trait
            //Current number of character trait points assigned to strength is 2
            else
                image(two, 375, 270); //Display the number 2 for strength trait

            //Current number of character trait points assigned to intelligence is 0
            if (currentIntelligencePoints == 0)
                image(zero, 375, 370); //Display the number 0 for intelligence trait
            //Current number of character trait points assigned to intelligence is 1
            else if (currentIntelligencePoints == 1)
                image(one, 375, 370); //Display the number 1 for intelligence trait
            //Current number of character trait points assigned to intelligence is 2
            else
                image(two, 375, 370); //Display the number 2 for intelligence trait
              
            
            //If the user assigned both of their points to strength
            if (currentStrengthPoints == 2)
                traitDistribution = 1; //Set trait distrbution to indicate high strength
            //If the user assigned both of their points to intelligence
            else if (currentIntelligencePoints == 2)
                traitDistribution = 2; //Set trait distrbution to indicate high intelligence
            //If the user assigned one point to strength and one point ot intelligence
            else
               traitDistribution = 0; //Set trait distribution to indicate neutral strength/intelligence
            
            //If user pressed space to continue but did not distrbute both points
            if (pointsError)
                image(pointsErrorMessage, 265, 425); //Display error message regarding point distribution
             
            //Part 1 of game instructions    
            } else if (currentScreen1 == 4) {
                //Tell the user how about Virtue Points, inventory, health, etc.
                background(gameInstructions1);
            //Part 2 of game instructions    
            } else {
                //Wish the user good luck
                background(gameInstructions2);
            }
            
            //Display text instructions telling the user to press space to continue
            image(pressSpaceToContinue, 280, 450);
            
            
            /**
            if (customName && customCharacter) {
                if (chooseCharacter1)
                    player = new Player(this, 0, 0, enteredName, int characterTraits, Inventory inventory, String imagePat);
                else if (chooseCharacter2)
                    player = new Player(this, 0, 0, enteredName, int characterTraits, Inventory inventory, String imagePat);
                else
                    player = new Player(this, 0, 0, enteredName, int characterTraits, Inventory inventory, String imagePat);
            } else if (customName) {
                player = new Player(this, 0, 0, enteredName, int characterTraits, Inventory inventory);
            } else if (customCharacter) {
                if (chooseCharacter1)
                   player = new Player(this, 0, 0, int characterTraits, Inventory inventory, String imagePat);
               else if (chooseCharacter2)
                   player = new Player(this, 0, 0, int characterTraits, Inventory inventory, String imagePat);
               else
                   player = new Player(this, 0, 0, int characterTraits, Inventory inventory, String imagePat);
            } else {
                player = new Player(this, 0, 0, int characterTraits, Inventory inventory);
            }
            * */
                
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
        //Variables for Stage 1 (Begin Story)
        overPlusStrength = false; //Button to add one point to strength trait
        overMinusStrength = false; //Button to subtract one point from strength trait
        overPlusIntelligence = false; //Button to add one point to intelligence trait
        overMinusIntelligence = false; //Button to subtract one point from inteeligence trait
       

    
    
        //Stage 0 (Main Menu)
        if (stage == 0) {
            //Set boolean variable of a button to true if the mouse if over it (used for button clicks)
            //Mouse is over a specific button in Stage 0 (Main Menu)
            if (overImage(startGame, startGameX, startGameY)) //Start game button
                overStartGame = true; //Set variable indicating mouse is over start game button to true
            else if (overImage(leaderboard, leaderboardX, leaderboardY)) //Show leaderboard button
                overLeaderboard = true; //Set variable indicating mouse is over leaderboard button to true
            else if (overImage(exitGame, exitGameX, exitGameY)) //Exit game button
                overExitGame = true; //Set variable indicating mouse is over exit gamebutton to true
        //Stage -1 (Character Setup)
        } else if (stage == -1) {
            //Mouse is over a specific button in Stage 1 (Character Setup)
            if (overImage(chooseNameButton, chooseNameX, chooseNameY)) //Choose name button
                overChooseName = true; //Set variable indicating mouse is over choose name button to true
            else if (overImage(customizeButton, customizeX, customizeY)) //Customize clothes button
                overCustomize = true; //Set variable indicating mouse is over customize clothes button to true
            else if (overImage(startButton, startX, startY)) //Start button
                overStart = true; //Set variable indicating mouse is over start button to true 
        //Stage -3 (Customize Clothes)
        } else if (stage == -3) {
            //Mouse is over a specific button in Stage -3 (Customize Clothes)
            if (overImage(leftButton, leftArrowX, leftArrowY)) //Left arrow button
                overLeftArrow = true; //Set variable indicating mouse is over left arrow button to true
            else if (overImage(rightButton, rightArrowX, rightArrowY)) //Right arrow button
                overRightArrow = true; //Set variable indicating mouse is over right arrow button to true
        //Stage 1 (Begin Story)
        } else if (stage == 1) {
            //Mouse is over a specific button in Stage 1 (Begin Story)
            if (overImage(plusStrengthButton, plusStrengthX, plusStrengthY)) //Button to add one point to strength trait
                overPlusStrength = true; //Set variable indicating mouse is over add strength button to true
            else if (overImage(minusStrengthButton, minusStrengthX, minusStrengthY)) //Button to subtract one point to strength trait
                overMinusStrength = true; //Set variable indicating mouse is over minus strength button to true
            if (overImage(plusInteligenceButton, plusIntelligenceX, plusIntelligenceY)) //Button to add one point to intelligence trait
                overPlusIntelligence = true; //Set variable indicating mouse is over add intelligence button to true
            else if (overImage(minusIntelligenceButton, minusIntelligenceX, minusIntelligenceY)) //Button to subtract one point to intelligence trait
                overMinusIntelligence = true; //Set variable indicating mouse is over minus intelligence button to true
           
                
        }
       
        
    
        
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
            else if (overStart) {
                stage = 1; //Begin story
            }
            
    
    
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
        //Stage 1 (Begin Story)
        } else if (stage == 1) {
            if (overPlusStrength) {
                if (currentStrengthPoints == 0 && currentIntelligencePoints == 0)
                    currentStrengthPoints = 1;
                else if (currentIntelligencePoints == 2) {
                    currentStrengthPoints = 1;
                    currentIntelligencePoints = 1;
                } else if (currentStrengthPoints == 0) 
                    currentStrengthPoints = 1;
                else if (currentStrengthPoints == 1 && currentIntelligencePoints == 1) {
                    currentStrengthPoints = 2;
                    currentIntelligencePoints = 0;
                }
                else {
                    currentStrengthPoints = 2;
                }
            } else if (overMinusStrength) {
                if (currentStrengthPoints == 2)
                   currentStrengthPoints = 1;
                else {
                    currentStrengthPoints = 0;
                }
            } else if (overPlusIntelligence) {
                if (currentStrengthPoints == 0 && currentIntelligencePoints == 0)
                    currentIntelligencePoints = 1;
                else if (currentStrengthPoints == 2) {
                    currentStrengthPoints = 1;
                    currentIntelligencePoints = 1;
                } else if (currentIntelligencePoints == 0) 
                    currentIntelligencePoints = 1;
                else if (currentStrengthPoints == 1 && currentIntelligencePoints == 1) {
                    currentIntelligencePoints = 2;
                    currentStrengthPoints = 0;
                }
                else {
                    currentIntelligencePoints = 2;
                }
            } else if (overMinusIntelligence) {
                if (currentIntelligencePoints == 2)
                   currentIntelligencePoints = 1;
                else {
                    currentIntelligencePoints = 0;
                }
            }
                
                    
                    
            } 
            
            
        }
        
    

      
    @Override
    public void keyPressed() {
        if (key == ' ') {
            if (stage == -3)
                stage = -1;
            //Spacebar is pressed in Stage 1 (Begin Story)
            else if (stage == 1) {
                if (currentScreen1 == 1) //If screen currently being shown is the first part of the story introduction
                    currentScreen1 = 2;
                else if (currentScreen1 == 2) //If screen currently being shown is the second part of the story introduction
                    currentScreen1 = 3;
                else if (currentScreen1 == 3) { //If screen currently being shown is the character traits distrubution
                    pointsError = false; //Set error indicator for points distribution false in case it was set to true previously
                    if ((currentStrengthPoints == 0 && currentIntelligencePoints == 0) || (currentStrengthPoints == 1 && currentIntelligencePoints == 0) || (currentStrengthPoints == 0 && currentIntelligencePoints == 1)) {
                        pointsError = true;
                    } else  
                        currentScreen1 = 4;
                } else  if (currentScreen1 == 4) //If screen currently being shown is the first part of the game instructions
                    currentScreen1 = 5;
                else {
                    stage = 2;
                    currentScreen1 = 1; //Reset screen to be shown for Stage 1 to the first part of the story introduction
                }
                    
            }
        }
        
        if (stage == -2) {
            if (keyCode == ENTER) { //If enter key is pressed
                nameError = false; //Set error indicator for name to false in case it was set to true previously
                if (userInput.isEmpty() || userInput.length() > 10)
                    nameError = true;
                else {     
                    enteredName = userInput;
                    stage = -1; //Return to character setup screen
                }
            } else if (keyCode == BACKSPACE) {
                if (userInput.length() > 0) 
                    userInput = userInput.substring(0, userInput.length() - 1); //Remove last character from user input string
             
            } else if (key != CODED) { // checks if it is an ASCII character
                userInput += key; // add each keystroke to user input string
            } // end nested if
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
