/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author 343330528
 */
public class MySketch extends PApplet {
    PApplet app;
    Player player; //Player object representing character
    Game currentGame; //Current minigame being played
    boolean playerDeath = false; //True if player runs out of health
    boolean gamePaused = false; //True if player pauses game to eat food to regain health
    ArrayList<Game> gameResults; //Array list of all player game results
    
    //Variables
    int stage = 0; //Current stage of the game starts at 0
    String userInput = ""; //Stores user input text
    String enteredName = ""; //Player's name (based on user input, by default set to "Don Juan")
    boolean reachedMaxPlayers = false; //True if the maximum number of players have played the game (5 players)
    //Variables for Stage -1 (Start Game)
    boolean customName = false; //True if user enters their own name for the character
    boolean customCharacter = false; //True if user customizes clothing colors of the character
    //Variables for Stage -2 (Choose Name)
    boolean nameError = false; //True if name entered by user is not between 1 - 10 characters long (error)
    //Variables for Stage -3 (Customize Clothes)
    boolean chooseCharacter1 = true;  //True if character clothing v1 is currently displayed
    boolean chooseCharacter2 = false; //True if character clothing v2 is currently displayed
    //Variables for Stage -4 (Leaderboard)
    String[] names; //Declare array of player names
    String[] virtuePoints; //Declare array of player virtue points
    String[] gamePoints; //Declare array of player game points
    String[] totalPoints; //Declare array of total points each player has
    int[][] leaderboardPositions = {{293, 209}, //Names
                                    {293, 249},
                                    {293, 289},
                                    {293, 329},
                                    {293, 369},
                                    {419, 209}, //Virtue Points
                                    {419, 249},
                                    {419, 289},
                                    {419, 329},
                                    {419, 369},
                                    {533, 209}, //Game Points
                                    {533, 249},
                                    {533, 289},
                                    {533, 329},
                                    {533, 369},
                                    {636, 209}, //Total Points
                                    {636, 249},
                                    {636, 289},
                                    {636, 329},
                                    {636, 369},
                                                };
    int[][] gamePositions = {{279, 229}, //Names
                             {279, 269},
                             {279, 309},
                             {279, 349},
                             {279, 389},
                             {414, 229}, //Score
                             {414, 269},
                             {414, 309},
                             {414, 349},
                             {414, 389},
                             {553, 229}, //Attribute specific to game
                             {553, 269},
                             {553, 309},
                             {553, 349},
                             {553, 389},
                                        };
    //Variables for Stage 1 (Begin Story)
    int currentScreen1 = 1; //Keeps track of the different screens being shown in the same stage
    int traitDistribution; //0 means neutral, 1 means high strength, 2 means high intelligence
    int currentStrengthPoints = 0; //Current number of character trait points assigned to strength
    int currentIntelligencePoints = 0; //Current number of character trait points assigned to intelligence
    boolean pointsError = false; //True if not all character trait points have been distributed (error)
    //Variables for Stage 2 (Fork in the Road)
    boolean willFightBoar; //True if player chooses the path with the boar
    //Variables for Stage 4 (Encounter Boar)
    int currentScreen4 = 1; //Current screen being shown in Stage 4
    //Variables for Stage 7 (Meet Hermit)
    int currentScreen7 = 1; //Current screen being shown in Stage 7
    boolean playerHelpsHermit; //True if user decides to help the hermit
    //Variables for Stage 9 (Find Adarna bird)
    int currentScreen9 = 1; //Current screen being shown in Stage 9
    boolean sheerWill; //What user uses to stay awake, which will affect the rhythm minigame
    //Variables for Stage 11 (Catch the Adarna Bird)
    boolean clickedCage = false; //True after user clicks on cage, after which it will disappear
    boolean clickedRope = false; //True after user clicks on rope, after which it will disappear
    //Variables for Stage 13 (Find Brothers)
    int currentScreen13 = 1; //Current screen being shown in Stage 13
    int donPedroX = 390; //X position of Don Pedro
    int donPedroY = 255; //Y position of Don Pedro
    int donDiegoX = 205; //X position of Don Diego
    int donDiegoY = 255; //Y position of Don Diego
    //Variables for Stage 14 (Betrayal)
    int currentScreen14 = 1; //Current screen being shown in Stage 14
    //Variables for Stage 16 (Escaped Well)
    int currentScreen16 = 1; //Current screen being shown in Stage 16
    //Variables for Stage 18 (Heal King)
    int currentScreen18 = 1; //Current screen being shown in Stage 18
    
    
    
    //Buttons
    //Buttons for game pause feature
    boolean overHeart = false; //Set variable indicating mouse is over heart/pause/healing button to false
    int heartX = 728; //Set x position of heart/pause/healing button
    int heartY = 538; //Set y position of heart/pause/healing button
    boolean overEatFood = false; //Set variable indicating mouse is over eat food button to false
    int eatFoodX = 237; //Set x position of eat food button
    int eatFoodY = 341; //Set y position of eat food button
    

    
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
    
    //Buttons for Stage 1 (Distribute Character Points)
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
    
    //Buttons for Stage 7 (Meet Hermit)
    boolean overHelpHermit = false; //Set variable indicating mouse is over help hermit button to false
    int helpHermitX = 230; //Set x position of help hermit button
    int helpHermitY = 220; //Set y position of help hermit button
    boolean overIgnoreHermit = false; //Set variable indicating mouse is over help hermit button to false
    int ignoreHermitX = 230; //Set x position of ignore hermit button
    int ignoreHermitY = 375; //Set y position of ignore hermit button
    
    //Buttons for Stage 9 (Find Adarna Bird)
    boolean overSheerWill = false; //Set variable indicating mouse is over sheer will button to false
    int sheerWillX = 230; //Set x position of sheer will button
    int sheerWillY = 220; //Set y position of sheer will button
    boolean overLimeJuice = false; //Set variable indicating mouse is over lime juice button to false
    int limeJuiceX = 230; //Set x position of lime juice button
    int limeJuiceY = 375; //Set y position of lime juice button
    
    //Buttons for Stage 11 (Catch the Adarna Bird)
    boolean overCatchAdarnaCage = false; //Set variable indicating mouse is over golden cage to false
    int catchAdarnaCageX = 110; //Set x position of golden cage
    int catchAdarnaCageY = 160; //Set y position of golden cage
    boolean overCatchAdarnaRope = false; //Set variable indicating mouse is over rope to false
    int catchAdarnaRopeX = 420; //Set x position of rope
    int catchAdarnaRopeY = 420; //Set y position of rope
    boolean overAdarnaBird = false; //Set variable indicating mouse is over Adarna bird to false
    int adarnaBirdX = -100; //X position of Adarna bird
    int adarnaBirdY = 225; //Y position of Adarna bird
    
    //Buttons for Stage 13 (Find Brothers)
    boolean overSaveThem = false; //Set variable indicating mouse is over save them button to false
    int saveThemX = 230; //Set x position of save them button
    int saveThemY = 220; //Set y position of save them button
    boolean overleaveThem = false; //Set variable indicating mouse is over leave them button to false
    int leaveThemX = 230; //Set x position of save them button
    int leaveThemY = 375; //Set y position of save them button
    
    //Buttons for Stage 18 (Heal King)
    boolean overYes = false; //Set variable indicating mouse is over yes button to false
    int yesX = 230; //Set x position of yes button
    int yesY = 220; //Set y position of yes button
    boolean overNo = false; //Set variable indicating mouse is over no button to false
    int noX = 230; //Set x position of no button
    int noY = 375; //Set y position of no button
    
    
    
    
    
    //Declare images
    //Declare image for Game Over screen
    PImage gameOverScreen;  //Game Over screen
    //Declare image for paused game feature
    PImage heartButton; //Heart/pause/healing button to pause game and eat food to gain health
    PImage eatFoodButton; //Button to consume 1 food item
    PImage fullHealthErrorMessage; //Error message saying health is already full when player eats food
    PImage noFoodErrorMessage; //Error message saying player has no food items left
    PImage gamePausedBG; //Background when game is paused to eat food to gain health
    //Declare images for Stage 0 (Main Menu)
    PImage testimg; ////
    PImage homeScreen;     //Homescreen background
    PImage startGame;      //Start game button
    PImage leaderboard;    //Leaderboard button
    PImage exitGame;       //Exit game button
    PImage maxPlayersMesssage;  //Message telling the user that the maximum number of players has been reached
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
    //Declare images for Stage -4 (Leaderboard)
    PImage leaderboardBG; //Leaderboard background
    //Declare images for Stage -5 (Boar Fight Game Results)
    PImage boarGameResultsBG; //Background for boar fight minigame results
    //Declare images for Stage -6 (Rhythm Game Results)
    PImage rhythmGameResultsBG; //Background for rhythm minigame results
    //Declare images for Stage -7 (Well Escape Game Results)
    PImage wellGameResultsBG; //Background for well escape minigame results
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
    //Declare images for Stage 2 (Leaving Castle)
    PImage castleBG; //Castle background
    //Declare images for Stage 3 (Fork in the Road)
    PImage forkInTheRoadBG; //Fork in the road background
    //Declare images for Stage 4 (Encounter Boar)
    PImage encounterBoarBG; //Background if user chooses path with the boar
    PImage boarFightInstructions; //Background displaying boar fight instructions
    PImage noBoarBG; //Background if user does not choose path with the boar
    PImage boar; //Wild boar
    //Declare images for Stage 6 (Walking in the Forest)
    PImage fillerForest1; //Filler forest scene
    //Declare images for Stage 7 (Meet Hermit)
    PImage meetHermitBG1; //Background when meeting the hermit (part 1)
    PImage meetHermitBG2; //Background when meeting the hermit (part 2)
    PImage meetHermitBG3; //Background when meeting the hermit (part 3)
    PImage meetHermitBG4; //Background when meeting the hermit (part 4)
    PImage helpHermitButton; //Press to help the hermit
    PImage ignoreHermitButton; //Press to ignore the hermit
    PImage hermit; //Hermit character
    PImage hermitWithFood; //Hermit character holding food
    //Declare images for Stage 8 (Walking in the Forest 2)
    PImage fillerForest2; //Filler forest scene 2
    //Declare images for Stage 9 (Find Adarna Bird)
    PImage seeAdarnaBG1; //Background when seeing the Adarna bird (part 1)
    PImage seeAdarnaBG2; //Background when seeing the Adarna bird (part 2)
    PImage rhythmGameInstructions; //Background displaying rhythm game instructions
    PImage sheerWillButton; //Button to use sheer will to stay awake
    PImage limeJuiceButton; //Button to use lime juice to stay awake
    //Declare images for Stage 11 (Catch the Adarna Bird)
    PImage catchAdarnaBG1; //Background when catching the Adarna bird (player has rope and golden cage)
    PImage catchAdarnaCage; //Golden cage user clicks to catch Adarna bird
    PImage catchAdarnaRope; //Rope user clicks to catch Adarna bird
    PImage barelyAwakeMessage; //Message if user lost rhythm minigame
    PImage wideAwakeMessage; //Message if user won rhythm minigame
    PImage adarnaBird; //Image of Adarna bird
    PImage catchAdarnaBG2; //Background when catching the Adarna bird (player doesn't have rope and golden cage)
    //Declare images for Stage 12 (Caught the Adarna Bird)
    PImage afterCaptureBG; //Background after capturing Adarna bird
    PImage adarnaInCage; //Image of Adarna bird in a cage
    //Declare images for Stage 13 (Find Brothers)
    PImage findBrothersBG1; //Background when seeing brothers for the first time
    PImage findBrothersBG2; //Background when choosing to save or leave brothers
    PImage findBrothersBG3; //Background if user saves brothers
    PImage findBrothersBG4; //Background if user leaves brothers
    PImage leaveThemButton; //Leave them button (brothers)
    PImage saveThemButton; //Save them button (brothers)
    PImage donPedro; //Brother 1
    PImage donDiego; //Brother 2
    //Declare images for Stage 14 (Betrayal)
    PImage betrayalBG1; //Background when brothers take the Adarna bird
    PImage betrayalBG2; //Background when player is thrown down a well
    PImage betrayalBG3; //Background when brothers leave
    PImage wellGameInstructions; //Background displaying well game instructions
    //Declare images for Stage 16 (Escaped Well)
    PImage escapedWellBG1; //Background if player escapes well quickly (won well minigame)
    PImage escapedWellBG2; //Background if player escapes well eventually (lost well minigame)
    PImage escapedWellBG3; //Background when player exits scene with well
    //Declare images for Stage 17 (Return Home)
    PImage returnHomeBG; //Background when returning home
    //Declare images for Stage 18 (Heal King)
    PImage healKingBG1; //Background when the player arrives home if they saved their brothers
    PImage healKingBG2; //Background when the bird heals the king if the player saved their brothers
    PImage healKingBG3; //Background when the player decides whether or not to forgive if the they saved their brothers
    PImage healKingBG4; //Background when the player arrives home if they didn't save their brothers
    PImage healKingBG5; //Background when the the bird healst the king if the player didn't save their brothers
    PImage yesButton; //Button to forgive brothers
    PImage noButton; //Button to not forgive brothers
    //Declare images for Stage 19 (Ending)
    PImage ending1; //Player saved brothers and forgave them
    PImage ending2; //Player saved brothers and didn't forgive them
    PImage ending3; //Player didn't save brothers
    

    
    
    
    
    public void settings() {
        size(800, 600); //Set size of frame
    }
    
    public void setup() {
        background(100, 100, 100); ////Set background
        textSize(20); //Set text size
        
        //Clear information to flat-file of user data
        try {
            //Create FileWriter object to overwrite file, named "leadershipdata.txt"
            FileWriter writer = new FileWriter("leadershipdata.txt");
            //Clear file by overwriting with empty string
            writer.write("");
            //Close FileWriter
            writer.close();
        //Catch IO exceptions when writing to flat file
        } catch (IOException e) {
            System.err.println(e); //Error message for IO exceptions
        } //End try-catch statement for clearing file
            
        //Declare array list containing results of all the games played by each player created
        gameResults = new ArrayList<Game>();
                
        //Load images
        //Load image for Game Over screen
        gameOverScreen = loadImage("images/gameoverscreen.jpg"); //Game Over screen
        
        //Load image for paused game
        heartButton = loadImage("images/heartbutton.png"); //Heart/pause/healing button to pause game and eat food to gain health
        eatFoodButton = loadImage("images/eatfoodbutton.jpg"); //Button to consume 1 food item
        fullHealthErrorMessage = loadImage("images/fullhealtherror.png"); //Error message saying health is already full when player eats food
        noFoodErrorMessage = loadImage("images/nofooderror.png"); //Error message saying player has no food items left
        gamePausedBG = loadImage("images/gamepausedbg.jpg"); //Background when game is paused to eat food to gain health
        
        //Load images for Stage 0 (Main Menu)
        testimg = loadImage("testimg.png"); ////
        homeScreen = loadImage("images/homescreen.jpg");         //Homescreen background
        startGame = loadImage("images/startgamebutton.jpg");     //Start game button
        leaderboard = loadImage("images/leaderboardbutton.jpg"); //Leaderboard button
        exitGame = loadImage("images/exitbutton.jpg");           //Exit game button
        maxPlayersMesssage = loadImage("images/maxplayersmessage.jpg");   //Message telling the user that the maximum number of players has been reached
        
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
        
        //Load images for Stage -4 (Leaderboard)
        leaderboardBG = loadImage("images/leaderboardbg.jpg"); //Leaderboard background
        
        //Load images for Stage -5 (Boar Fight Game Results)
        boarGameResultsBG = loadImage("images/boargameresults.jpg"); //Background for boar fight minigame results
        
        //Load images for Stage -6 (Rhythm Game Results)
        rhythmGameResultsBG = loadImage("images/rhythmgameresults.jpg"); //Background for rhythm minigame results
        
        //Load images for Stage -7 (Well Escape Game Results)
        wellGameResultsBG = loadImage("images/wellgameresults.jpg"); //Background for well escape minigame results
        
        
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
        
        //Load images for Stage 2 (Leaving Castle)
        castleBG = loadImage("images/castlebg.jpg");  //Castle background
        
        //Load images for Stage 3 (Fork in the Road)
        forkInTheRoadBG = loadImage("images/forkintheroadbg.jpg");  //Fork in the road background
        
        //Load images for Stage 4 (Encounter Boar)
        encounterBoarBG = loadImage("images/encounterboarbg.jpg");  //Background if user chooses path with the boar
        noBoarBG = loadImage("images/noboarbg.jpg");  //Background if user does not choose path with the boar
        boar = loadImage("images/boar.png"); //Wild boar
        boarFightInstructions = loadImage("images/boarfightinstructions.jpg"); //Background displaying boar fight instructions
        
        //Load images for Stage 6 (Walking in the Forest)
        fillerForest1 = loadImage("images/fillerforest1.jpg"); //Filler forest scene
        
        //Load images for Stage 7 (Meet Hermit)
        meetHermitBG1 = loadImage("images/meethermit1.jpg"); //Background when meeting the hermit (part 1)
        meetHermitBG2 = loadImage("images/meethermit2.jpg"); //Background when meeting the hermit (part 2)
        meetHermitBG3 = loadImage("images/meethermit3.jpg"); //Background when meeting the hermit (part 3)
        meetHermitBG4 = loadImage("images/meethermit4.jpg"); //Background when meeting the hermit (part 4)
        helpHermitButton = loadImage("images/helpthehermit.jpg"); //Press to help the hermit
        ignoreHermitButton = loadImage("images/ignorehim.jpg"); //Press to ignore the hermit
        hermit = loadImage("images/hermit.png"); //Hermit character
        hermitWithFood = loadImage("images/hermitwithfood.png"); //Hermit character holding food
        
        //Load images for Stage 8 (Walking in the Forest 2)
        fillerForest2 = loadImage("images/fillerforest2.jpg"); //Filler forest scene 2
        
        //Load images for Stage 9 (Find Adarna Bird)
        seeAdarnaBG1 = loadImage("images/seeadarna1.jpg"); //Background when seeing the Adarna bird (part 1)
        seeAdarnaBG2 = loadImage("images/seeadarna2.jpg"); //Background when seeing the Adarna bird (part 2)
        rhythmGameInstructions = loadImage("images/rhythmgameinstructions.jpg"); //Background displaying rhythm game instructions
        sheerWillButton = loadImage("images/sheerwillbutton.jpg"); //Button to use sheer will to stay awake
        limeJuiceButton = loadImage("images/limejuicebutton.jpg"); //Button to use lime juice to stay awake
        
        //Load images for Stage 11 (Catch the Adarna Bird)
        catchAdarnaBG1 = loadImage("images/catchadarna1.jpg"); //Background when catching the Adarna bird (player has rope and golden cage)
        catchAdarnaCage = loadImage("images/catchadarnacage.png"); //Golden cage user clicks to catch Adarna bird
        catchAdarnaRope = loadImage("images/catchadarnarope.png"); //Rope user clicks to catch Adarna bird
        barelyAwakeMessage = loadImage("images/barelyawakemessage.png"); //Message if user lost rhythm minigame
        wideAwakeMessage = loadImage("images/wideawakemessage.png"); //Message if user won rhythm minigame
        adarnaBird = loadImage("images/adarnabird.png"); //Image of Adarna bird
        catchAdarnaBG2 = loadImage("images/catchadarna2.jpg"); //Background when catching the Adarna bird (player doesn't have rope and golden cage)
        
        //Load images for Stage 12 (Caught the Adarna Bird)
        afterCaptureBG = loadImage("images/aftercapturebg.jpg"); //Background after capturing Adarna bird
        adarnaInCage = loadImage("images/adarnaincage.png"); //Image of Adarna bird in a cage
      
        //Load images for Stage 13 (Find Brothers)
        findBrothersBG1 = loadImage("images/findbrothers1.jpg"); //Background when seeing brothers for the first time
        findBrothersBG2 = loadImage("images/findbrothers2.jpg"); //Background when choosing to save or leave brothers
        findBrothersBG3 = loadImage("images/findbrothers3.jpg"); //Background if user saves brothers
        findBrothersBG4 = loadImage("images/findbrothers4.jpg"); //Background if user leaves brothers
        leaveThemButton = loadImage("images/leavethembutton.jpg"); //Leave them button (brothers)
        saveThemButton = loadImage("images/savethembutton.jpg"); //Save them button (brothers)
        donPedro = loadImage("images/donpedro.png"); //Brother 1
        donDiego = loadImage("images/dondiego.png"); //Brother 2    
        
        //Load images for Stage 14 (Betrayal)
        betrayalBG1 = loadImage("images/betrayal1.jpg"); //Background when brothers take the Adarna bird
        betrayalBG2 = loadImage("images/betrayal2.jpg"); //Background when player is thrown down a well
        betrayalBG3 = loadImage("images/betrayal3.jpg"); //Background when brothers leave
        wellGameInstructions = loadImage("images/wellescapeinstructions.jpg"); //Background displaying well game instructions
        
        //Load images for Stage 16 (Escaped Well)
        escapedWellBG1 = loadImage("images/escapedwellquickly.jpg"); //Background if player escapes well quickly (won well minigame)
        escapedWellBG2 = loadImage("images/escapedwelleventually.jpg"); //Background if player escapes well eventually (lost well minigame)
        escapedWellBG3 = loadImage("images/escapedwellbg.jpg"); //Background when player exits scene with well
        
        //Load images for Stage 17 (Return Home)
        returnHomeBG = loadImage("images/returnhomebg.jpg"); //Background when player walks home through the forest
        
        //Load images for Stage 18 (Heal King)
        healKingBG1 = loadImage("images/healking1.jpg"); //Background when the player arrives home if they saved their brothers
        healKingBG2 = loadImage("images/healking2.jpg"); //Background when the bird heals the king if the player saved their brothers
        healKingBG3 = loadImage("images/healking3.jpg"); //Background when the player decides whether or not to forgive if the they saved their brothers
        healKingBG4 = loadImage("images/healking4.jpg"); //Background when the player arrives home if they didn't save their brothers
        healKingBG5 = loadImage("images/healking5.jpg"); //Background when the the bird healst the king if the player didn't save their brothers
        yesButton = loadImage("images/yesbutton.jpg"); //Forgive brothers button
        noButton = loadImage("images/nobutton.jpg"); //Don't forgive brothers button
        
        //Load images for Stage 19 (Ending)
        ending1 = loadImage("images/ending1.jpg"); //Ending if player saved brothers and forgave them
        ending2 = loadImage("images/ending2.jpg"); //Ending if player saved brothers and didn't forgive them
        ending3 = loadImage("images/ending3.jpg"); //Ending if player didn't save brothers

        
    }
    
    public void draw() {
        System.out.println("Stage: " + stage);
        System.out.println("Players: " + Player.getNumPlayers());
        System.out.println("");
        
        background(255); //Reset background
        update(mouseX, mouseY); //Update variables indicating button clicks
        
        if (playerDeath) {
            background(gameOverScreen); //Game over background
            
        } else if (gamePaused) {
            //Set background to when game is paused to eat food to gain health
            background(gamePausedBG);
            //If player does not have full health and still has food in their inventory
            if (player.getHealth() < player.getFullHealth() && player.getInventory().getNumFood() > 0)
                image(eatFoodButton, eatFoodX, eatFoodY); //Draw button to eat food
            //If player does not have full health and has no more food in their inventory
            else if (player.getHealth() < player.getFullHealth() && player.getInventory().getNumFood() <= 0)
                image(noFoodErrorMessage, 265, 364); //Draw error message saying the player has no food items left
            //If player has full health and still has food in their inventory
            else if (player.getHealth() >= player.getFullHealth() && player.getInventory().getNumFood() > 0)
                image(fullHealthErrorMessage, 263, 354); //Draw error message saying the player is at full health
            //If player has full health and has no more food in their inventory
            else if (player.getHealth() >= player.getFullHealth() && player.getInventory().getNumFood() <= 0)
                image(noFoodErrorMessage, 265, 364); //Draw error message saying the player has no food items left
                        
            fill(0); //Set text colour to black;
            textAlign(LEFT, LEFT); //Align text to the left
            textSize(24); //Set text size
            
            text(player.getHealth() + " / " + player.getFullHealth(), 371, 242);
            text(player.getInventory().getNumFood(), 460, 280);
            text(Player.getHealthPerFood() + " HP", 447, 312);
            
        } else {
        
        //Main menu
        if (stage == 0) {
            //Set home screen background with title
            background(homeScreen);  
            
            //Display button options
            image(startGame, startGameX, startGameY);        //Start game button
            image(leaderboard, leaderboardX, leaderboardY);  //Show leaderboard button
            image(exitGame, exitGameX, exitGameY);           //Exit button
            
            //If the maximum number of players have played the game (5 players)
            if (reachedMaxPlayers)
                //Message telling the user that the maximum number of players has been reached
                image(maxPlayersMesssage, startGameX, startGameY);
                         

                
            
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
            //Align text to the left
            textAlign(LEFT, LEFT);
            //Display user input text
            text(userInput, 450, 125);
            
            //If user presses enter to confirm their chosen name but the name is not between 1 and 10 characters long
            if(nameError) 
                image(nameErrorMessage, 225, 140); //Display error message regarding name length
            
            //Draw character below the area where the user types in the name
            if (customCharacter) //If the user has chosen a character already in the customize clothing option
                image(currentChoice, 312, 180); //Display the character (1/2/3) that they chose to play as
            else //If the user has not chosen a character yet
                image(character1, 312, 180); //Display the default character option
            
            
        
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
            image(currentChoice, 312, 167);
                
            
            //Display button options
            image(leftButton, leftArrowX, leftArrowY);     //Left arrow button
            image(rightButton, rightArrowX, rightArrowY);  //Right arrow button
            
            
        //Stage -4 (Leaderboard)
        } else if (stage == -4) {
            //Set background to leaderboard table
            background(leaderboardBG);
            //If there has been at least one player that has played the game, the leaderboard can be sorted/displayed
            if (Player.getNumPlayers() > 0) {
                //Dynamically size arrays based on the number of players
                names = new String[Player.getNumPlayers()]; //Set size of names array as the number of players
                virtuePoints = new String[Player.getNumPlayers()]; //Set size of virtue points array as the number of players
                gamePoints = new String[Player.getNumPlayers()]; //Set size of game points array as the number of players
                totalPoints = new String[Player.getNumPlayers()]; //Set size of total points array as the number of players
                
                
                
                int lineCount = 0; //Initalize counter for keeping track of which line the Scanner is currently reading

                // Read the flat file of player data and the store the values in the arrays
                try {
                    //Create Scanner object to read from the file of player data, named "leadershipdata.txt"
                    Scanner reader = new Scanner(new File("leadershipdata.txt")); 

                    //Go through each line of the file with the Scanner
                    while (reader.hasNext() && lineCount < Player.getNumPlayers()){ 
                        System.out.println("Line Count: " + lineCount);
                        String[] info = reader.nextLine().split(","); //Split each line into an array, using comma as the delimiter

                        names[lineCount] = info[0].trim(); //Adds first element of array to array of last names, removes white space
                        virtuePoints[lineCount] = info[1].trim(); //Adds second element of array to array of first names, removes white space
                        gamePoints[lineCount] = info[2].trim();  //Adds third element of array to array of dates of birth, removes white space
                        totalPoints[lineCount] = info[3].trim(); //Adds fourth element of array to array of student numbers, removes white space

                        lineCount++; //Increment counter for current line in flat file of player data
                    } //End while loop going through player data in flat file
                    reader.close(); //Close Scanner object
                //Catch IO exceptions when writing to flat file
                } catch (IOException ioException) { 
                    System.err.println("Java Exception: " + ioException); //Error message for IO exceptions
                } //End try-catch statement for writing to file
                
                
                
                System.out.println("Players: " + Player.getNumPlayers());
                //Sort player data using Bubble Sort
                bubbleSort(names, virtuePoints, gamePoints, totalPoints);
                
                
                //Display player data on the screen
                fill(0); //Set fill colour to black
                textSize(24); //Set text size
                textAlign(CENTER, CENTER); //Align text at the center of its specified coordinates


                // Loop through each player (row)
                for (int playerIndex = 0; playerIndex < Player.getNumPlayers(); playerIndex++) {
                   // Display name (first column)
                   text(names[playerIndex], leaderboardPositions[playerIndex][0], leaderboardPositions[playerIndex][1]);

                   // Display virtue points (second column)
                   text(virtuePoints[playerIndex], leaderboardPositions[playerIndex + 5][0], leaderboardPositions[playerIndex + 5][1]);

                   // Display game points (third column)
                   text(gamePoints[playerIndex], leaderboardPositions[playerIndex + 2 * 5][0], leaderboardPositions[playerIndex + 2 * 5][1]);

                   // Display total points (fourth column)
                   text(totalPoints[playerIndex], leaderboardPositions[playerIndex + 3 * 5][0], leaderboardPositions[playerIndex + 3 * 5][1]);
               }
            
            } //End if statement checking if there is at least one player that has been created
            
            
         //Boar Fight Game Results
        } else if (stage == -5) {
            background(boarGameResultsBG); //Set background to boar fight game results
            displayBoarGameResults(gameResults); //Display all player results of Boar Fight minigame
            
        //Rhythm Game Results
        } else if (stage == -6) {
            background(rhythmGameResultsBG); //Set background to rhythm game results
            displayRhythmGameResults(gameResults); //Display all player results of Rhythm minigame
            
       //Well Escape Game Results
        } else if (stage == -7) {
            background(wellGameResultsBG); //Set background to well escape game results
            displayWellGameResults(gameResults); //Display all player results of Well Escape minigame
            
        
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
            
           
        //Leaving Castle
        } else if (stage == 2) {
            //Set castle background
            background(castleBG);
            player.draw(); //Draw character on the screen
            player.move(2, 0); //Move character to the right
            
        //Fork in the Road
        } else if (stage == 3) {
            //Set fork in the road background
            background(forkInTheRoadBG);
            player.draw(); //Draw character on the screen
            if (player.x < 240) //While character is still left of a certain position on the screen
                player.move(2, 0); //Keep moving character to the right
            
        //Encounter Boar
        } else if (stage == 4) {
            //Player encounters boar
            if (currentScreen4 == 1) {
                //If user chose path with the boar
                if (willFightBoar) {
                    //Set background where user encounters boar
                    background (encounterBoarBG);
                    image(boar,420, 370); //Draw boar on the screen
                    player.draw(); //Draw character on the screen
                    if (player.x < 140) //Keep moving character until it reaches a specific point on the screen
                        player.move(2, 0); //Move character to the right
                //If user chose path without the boar
                } else {
                    //Set background where user does not encounter boar
                    background (noBoarBG);
                    player.draw(); //Draw character on the screen
                    player.move(2, 0); //Move character to the right
                }
            //Display instructions for boar fight minigame
            } else if (currentScreen4 == 2) {
	    //Set backgrounds with boar fight minigame instructions
                background(boarFightInstructions);
            } //End if statement deciding which screen to show for Stage 4
            
            
        //Boar Fight Minigame
        } else if (stage == 5) {
            currentGame.update(); //Call update method for boar fight minigame
            currentGame.draw(); //Call draw method for boar fight minigame
            
        //Walking in the Forest 1
        } else if (stage == 6) {
            //Set background to filler forest scene 1
            background(fillerForest1);
            player.draw(); //Draw character on the screen
            player.move(3, 0); //Move character to the right
            if (player.x > 800) { //Once player passes the right edge of the screen
                player.moveTo(85, 240); //Set new player position
                stage = 7; //Go to the next stage (Meet Hermit)
            }
                
        //Meet Hermit
        } else if (stage == 7) {
            //If player is meeting hermit for the first time
            if (currentScreen7 == 1) {
                //Set background to meeting the hermit for the first time
                background(meetHermitBG1);
                player.draw(); //Draw character on the screen
                image(hermit, 380, 220);  //Draw hermit on the screen
            //If player is choosing how to stay awake
            } else if (currentScreen7 == 2) {
                //Set background to responding to the hermit
                background(meetHermitBG2);
                player.draw(); //Draw character on the screen
                image(hermit, 380, 220); //Draw hermit on the screen
                image(helpHermitButton, helpHermitX, helpHermitY); //Draw button to help hermit
                image(ignoreHermitButton, ignoreHermitX, ignoreHermitY); //Draw button to ignore hermit
            //If player made their choice in response to the hermit
            } else if (currentScreen7 == 3) {
	    //If player chose to help hermit
                if (playerHelpsHermit) {
	        //Set background to leaving the hermit
                    background(meetHermitBG3); 
                    image(hermitWithFood, 380, 220); //Draw hermit on the screen with food
                    player.getInventory().consumeFood(); //Remove one food item from player's inventory (given to hermit)
	        //Add items given by the hermit in response to the player's decision to help
                    player.getInventory().addItem("Lime Juice"); //Add lime juice to player's inventory
                    player.getInventory().addItem("Rope"); //Add rope to player's inventory
                    player.getInventory().addItem("Golden Cage"); //Add golden cage to player's inventory
                    player.addVirtue(50); //Give player virtue points for helping the hermit
                //If player chose to ignore the hermit
                } else {
	        //Set background to leaving the hermit
                    background(meetHermitBG4); 
                    image(hermit, 380, 220); //Draw hermit on the screen
                } //End if statement that awards the player with items and virtue points if they chose to help the hermit
                player.draw(); //Draw character on the screen
                player.move(3, 0); //Move character to the right
            } //End if statement deciding which screen to show for Stage 7
                
        //Walking in the Forest 2   
        } else if (stage == 8) {
	//Set background to filler forest scene 2
            background(fillerForest2);
            player.draw(); //Draw character on the screen
            player.move(3, 0); //Move character to the right
            if (player.x > 800) { //Once player passes the right edge of the screen
                player.moveTo(85, 240); //Set new player position
                stage = 9; //Go to the next stage (Adarna Bird)
            } //End if statement checking if player is still on the screen
            
        //Adarna Bird
        } else if (stage == 9) {
            //If player is seeing the Adarna bird for the first time
            if (currentScreen9 == 1) {
                background(seeAdarnaBG1); //Set background to first time seeing the bird
                player.draw(); //Draw player on the screen
	//If player is deciding how to stay awake during the Adarna bird's songs
            } else if (currentScreen9 == 2) {
	    //Set background to deciding how to stay awake
                background(seeAdarnaBG2);
                player.draw();  //Draw player on the screen
                image(sheerWillButton, sheerWillX, sheerWillY); //Draw sheer will button on the screen
                if (player.getInventory().hasItem("Lime Juice")) //If player does not have lime juice, they have an additional option
                    image(limeJuiceButton, limeJuiceX, limeJuiceY); //Draw lime juice button on the screen
	//Display instructions for the rhythm minigame
            } else if (currentScreen9 == 3) {
	    //Set background to rhythm minigame instructions
                background(rhythmGameInstructions);
            } //End if statement deciding which screen to show for Stage 9
            
        //Rhythm Game Minigame
        } else if (stage == 10) {
            currentGame.update(); //Call update method for rhythm minigame
            currentGame.draw(); //Call draw method for rhythm minigame
            
        //Catch the Adarna Bird
        } else if (stage == 11) {
            //If player has lime juice in their inventory, which would have been used in the last stage
            if (player.getInventory().hasItem("Lime Juice"))
                player.getInventory().removeItem("Lime Juice"); //Remove lime juice from their inventory
            
            //If player has rope and golden cage in their inventory, given by the hermit
            if (player.getInventory().hasItem("Rope") && player.getInventory().hasItem("Golden Cage")) {
                //Set background to catching the Adarna bird (player has rope and golden cage)
                background(catchAdarnaBG1);
              
                //While user has not yet clicked on image of golden cage
                if (!clickedCage)
                    image(catchAdarnaCage, catchAdarnaCageX, catchAdarnaCageY); //Draw cage on screen
                //While user has not yet clicked on image of rope
                if (!clickedRope)
                    image(catchAdarnaRope, catchAdarnaRopeX, catchAdarnaRopeY); //Draw rope on screen
                 
                
            //If player does not have rope and cage from hermit
            } else {
                //Set background to catching the Adarna bird (player does not have rope and golden cage)
                background(catchAdarnaBG2);
                
                image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird on screen
                adarnaBirdX += 4; //Move Adarna bird across the screen
                
                if (adarnaBirdX > 800) //If the Adarna bird goes off the right edge of the screen
                    adarnaBirdX = -100; //Set x position of bird back to the left edge of the screen
                
 
            }
            
            //If player won the rhythm game
            if (currentGame.playerWonGame()) 
                image(wideAwakeMessage, 138, 55); //Display message saying user stayed wide awake
            //If player lost the rhythm game
            else
                image(barelyAwakeMessage, 138, 55); //Display message saying user barely stayed awake

        //Caught the Adarna Bird
        } else if (stage == 12) {
            //Set background to after player catches the Adarna bird
            background(afterCaptureBG);
            player.draw(); //Draw character on the screen
            player.move(-2, 0); //Move character to the left
            
            //If player has golden cage in their inventory from the hermit
            if (player.getInventory().hasItem("Golden Cage"))
                image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
            ///If player has golden cage in their inventory from the hermit
            else
                image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage
            
            adarnaBirdX -= 2; //Move Adarna bird to the left with the player
        
        //Find Brothers
        } else if (stage == 13) {
            //If player is seeing brothers for the first time
            if (currentScreen13 == 1) {
                //Set background to seeing brothers for the first time
                background(findBrothersBG1);
                player.draw(); //Draw character on the screen
                //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage")) 
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                ///If player has golden cage in their inventory from the hermit
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage
                 
            //If player is choosing to save or leave brothers
            } else if (currentScreen13 == 2) {
                //Set background to choosing to save or leave brothers
                background(findBrothersBG2);
                player.draw(); //Draw character on the screen
                //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage")) 
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                ///If player has golden cage in their inventory from the hermit
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage
                
                image(saveThemButton, saveThemX, saveThemY); //Draw button to save brothers 
                image(leaveThemButton, leaveThemX, leaveThemY); //Draw button to leave brothers 
                
            //Player chose to save brothers
            } else if (currentScreen13 == 3) {
                //Set background to after choosing to save brothers
                background(findBrothersBG3);
                player.draw(); //Draw character on the screen
                //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage")) 
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                ///If player has golden cage in their inventory from the hermit
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage
                image(donPedro, donPedroX, donPedroY); //Draw brother 1 on the screen
                image(donDiego, donDiegoX, donDiegoY); //Draw brother 2 on the screen
                
                //Move characters and Adarna bird to the left
                player.move(-3, 0); //Move character to the left
                adarnaBirdX -= 3; //Move Adarna bird to the left
                donPedroX -= 3; //Move brother 1 to the left
                donDiegoX -= 3; //Move brother 2 to the left
                
            //Player chose to leave brothers
            } else if (currentScreen13 == 4) {
                //Set background to after choosing to leave brothers
                background(findBrothersBG4);
                player.draw(); //Draw character on the screen
                //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage")) 
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                ///If player has golden cage in their inventory from the hermit
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage

                //If character is still on the screen, keeping moving player to the left
                if (player.x > -180) {
                    //Move character and Adarna bird to the left
                    player.move(-3, 0); //Move character to the left
                    adarnaBirdX -= 3; //Move Adarna bird to the left
                //Once player is off the screen
                } else {
                    player.moveTo(800, 255); //Set new player position 
                    
                    //If player has golden cage in their inventory from the hermit
                    if (player.getInventory().hasItem("Golden Cage")) {
                        adarnaBirdX = 755; //Set x position of Adarna bird in golden cage
                        adarnaBirdY = 345; //Set y position of Adarna bird in golden cage
                    //If player does not have golden cage in their inventory
                    } else {
                        adarnaBirdX = 775; //Set x position of Adarna bird
                        adarnaBirdY = 370; //Set y position of Adarna bird
                    } //End if statement checking if player has golden cage in their inventory
                    
                    stage = 17; //Go to the next stage (Returning Home) 
                } //End if statement checking if player is still on the screen
                
                
                
            } //End if statement checking which screen of Stage 13 to show
            
            
        //Betrayal
        } else if (stage == 14) {
	//Brothers take the Adarna bird from the player
	if (currentScreen14 == 1) {

                background(betrayalBG1); //Set background to when brothers take the Adarna bird
	    player.draw(); //Draw character on the screen
	    image(donPedro, donPedroX, donPedroY); //Draw brother 1 on the screen
	    image(donDiego, donDiegoX, donDiegoY); //Draw brother 2 on the screen

                //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage")) 
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                ///If player has golden cage in their inventory from the hermit
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage

	//Brothers throw the player down a well
	} else if (currentScreen14 == 2) {
                background(betrayalBG2); //Set background to when player is thrown down a well
	    //Keep moving player and brother 1 left until brother 1 pushes the player down the well
	    //While player is right of a certain position (well)
	    if (player.x > 170) { 
	        player.move(-3, 0); //Keep moving player left
	        player.draw(); //Draw character on the screen
	    //Once player has reached the well
	    } else {
	        currentScreen14 = 3; //Go to the next screen (brothers leave player in well)
                //Pause the scene for a bit
                try {
                // Pause the current thread for 1000 milliseconds (1 seconds)
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    // Handle the case where the thread is interrupted while sleeping
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                    System.err.println("Thread was interrupted while sleeping."); //Print error message
                } //End try-catch to pause the scene
            } //End if statement checking if player has not yet reached the well
	    if (donPedroX > 285) //While brother 1 is right of a certain position (before pushing player into well)
		donPedroX -= 3; //Keep moving brother 1 left
	    image(donPedro, donPedroX, donPedroY); //Draw brother 1 on the screen
	    image(donDiego, donDiegoX, donDiegoY); //Draw brother 2 on the screen

    //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage")) 
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                ///If player has golden cage in their inventory from the hermit
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage

	//Brothers leave player in well
	} else if (currentScreen14 == 3) {
            background(betrayalBG3); //Set background to when brothers leave
	    image(donPedro, donPedroX, donPedroY); //Draw brother 1 on the screen
	    image(donDiego, donDiegoX, donDiegoY); //Draw brother 2 on the screen

            //If player has golden cage in their inventory from the hermit
            if (player.getInventory().hasItem("Golden Cage")) 
                image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
            ///If player has golden cage in their inventory from the hermit
            else
                image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage
	    //Keep moving brothers and Adarna bird to the left
	    donPedroX -= 2; //Move brother 1 to the left
	    donDiegoX -= 2; //Move brother 2 to the left
	    adarnaBirdX -= 2; //Move Adarna bird to the left

	//Well Minigame Instructions
	} else if (currentScreen14 == 4) {
	    //Set background to well minigame instructions
	    background(wellGameInstructions);

	} //End if statement checking which screen to show for Stage 14
            
        //Escape Well Minigame
        } else if (stage == 15) {
            currentGame.update(); //Call update method for escape well minigame
            currentGame.draw(); //Call draw method for escape well minigame
            
        //Escaped Well
        } else if (stage == 16) {
            if (currentScreen16 == 1) {
                //If player won the well escape game
                if (currentGame.playerWonGame()) 
                    background(escapedWellBG1); //Set background to if player escapes the well quickly
                //If player lost the well escape game
                else
                    background(escapedWellBG2); //Set background to if player escapes the well eventually
                
                player.draw(); //Draw character on the screen
            } else if (currentScreen16 == 2) {
                background(escapedWellBG3); //Set background to when player exits scene with well
                //If character is still on the screen, keeping moving player to the left
                if (player.x > -180) {
                    player.move(-3, 0); //Move character to the left
                    player.draw(); //Draw character on the screen
                //Once player is off the screen
                } else {
                    player.moveTo(800, 255); //Set new player position
                    stage = 17; //Go to the next stage (Returning Home) 
                } //End if statement checking if player is still on the screen
            }
            
        //Returning Home   
        } else if (stage == 17) {
            //Set background to when player is walking home through the forest
            background(returnHomeBG);
            player.draw(); //Draw character on the screen
            player.move(-3, 0); //Move character to the left
            //Once player passes the left edge of the screen
            if (player.x < -180) { 
                //player.moveTo(85, 240); //Set new player position ////
                //If player decided not ot save their brothers
                if (!player.getSavedBrothers())
                    currentScreen18 = 4; //Set different screen when arriving at the palace
                stage = 18; //Go to the next stage (Heal King)
            } //End if statement to check when user moves off the screen
            
            //Draw Adarna bird if player decided not to save their brothers
            if (!player.getSavedBrothers()) {
                //If player has golden cage in their inventory from the hermit
                if (player.getInventory().hasItem("Golden Cage"))
                    image(adarnaInCage, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character in the golden cage
                //If player does not have golden cage in their inventory
                else
                    image(adarnaBird, adarnaBirdX, adarnaBirdY); //Draw Adarna bird beside the character without a cage
                adarnaBirdX -= 3; //Move Adarna bird to the left with the player
            } //End if statement checking if player saved their brothers
            
        //Heal King
        } else if (stage == 18) {
            //Player decided to save their brothers and just arrived back at the palace
            if (currentScreen18  == 1) {
                //Set background when the player arrives home if they saved their brothers
                background(healKingBG1);
            //Player decided to save their brothers and the bird heals the king
            } else if (currentScreen18  == 2) {
                //Set background when the bird heals the king if the player saved their brothers
                background(healKingBG2);
            //Player decided to save their brothers and is deciding whether to forgive them after saving the king
            } else if (currentScreen18  == 3) {
                //Set background when the bird heals the king if the player saved their brothers
                background(healKingBG3);
                image(yesButton, yesX, yesY); //Yes button
                image(noButton, noX, noY); //No button
            //Player decided to leave their brothers and just arrived back at the palace
            } else if (currentScreen18  == 4) {
                //Set background when the bird heals the king if the player saved their brothers
                background(healKingBG4);
            //Player decided to leave their brothers and the bird heals the king
            } else if (currentScreen18  == 5) {
                //Set background when the bird heals the king if the player saved their brothers
                background(healKingBG5);
            }
            
        } else if (stage == 19) {
            //If player saved brothers and forgave them
            if (player.getSavedBrothers() && player.getForgaveBrothers())
                background(ending1); //Display best ending
            //If player saved brothers and didn't forgive them
            else if (player.getSavedBrothers())
                background(ending2); //Display moderate ending
            //If player didn't save brothers
            else if (!player.getSavedBrothers())
                background(ending3); //Display unhappy ending
            
        } //End if statement checking which stage of the game it is
        
        //Display heart/pause/healing button on certain screens to allow the user to eat food to regain health
        if (stage == 2 || stage == 3 || stage == 4 || stage == 6 || stage == 7 || stage == 8 || stage == 9 || stage == 12 || stage == 13 || stage == 14 || stage == 16 || stage == 17) {
            image(heartButton, heartX, heartY); //Draw heart/pause/healing button on the screen
        }
            
        
        
        } //End else player death ////
    }
    
    
    public void gameOver() {
        //Reset all variables for next game
        resetGame();
        
        //Write player data to flat file (name, virtue points, game points, total points)
        try {
            //Create FileWriter object to append to the file of player data, named "leadershipdata.txt"
            FileWriter w = new FileWriter("leadershipdata.txt", true);
            //Create new PrintWriter to write formatted output to the file of player data
            PrintWriter writer = new PrintWriter(w);
            player.calculateTotalPoints(); //Calculate player's total points
            //Write name, virtue points, game points, and total points to the file
            writer.printf(player.getName() + "," + player.getVirtuePoints() + "," + player.getGamePoints() + "," + player.getTotalPoints() + "\n");
            //Close PrintWriter
            writer.close();
        //Catch IO exceptions when writing to flat file
        } catch (IOException e) {
            System.err.println(e); //Error message for IO exceptions
        } //End try-catch statement for player data writing to flat file
                 
        stage = 0; //Return to main menu
        
    }
    
    public void resetGame() {
            
        //Reset Variables
        playerDeath = false;
        enteredName = ""; //Player's name (based on user input, by default set to "Don Juan")
        //Variables for Stage -1 (Start Game)
        customName = false; //True if user enters their own name for the character
        customCharacter = false; //True if user customizes clothing colors of the character
        //Variables for Stage -3 (Customize Clothes)
        chooseCharacter1 = true;  //True if character clothing v1 is currently displayed
        chooseCharacter2 = false; //True if character clothing v2 is currently displayed
        //Variables for Stage 1 (Begin Story)
        currentScreen1 = 1; //Keeps track of the different screens being shown in the same stage
        ////int traitDistribution; //0 means neutral, 1 means high strength, 2 means high intelligence
        currentStrengthPoints = 0; //Current number of character trait points assigned to strength
        currentIntelligencePoints = 0; //Current number of character trait points assigned to intelligence
        //Variables for Stage 4 (Encounter Boar)
        currentScreen4 = 1; //Current screen being shown in Stage 4
        //Variables for Stage 7 (Meet Hermit)
        currentScreen7 = 1; //Current screen being shown in Stage 7
        //Variables for Stage 9 (Find Adarna bird)
        currentScreen9 = 1; //Current screen being shown in Stage 9
        //Variables for Stage 11 (Catch the Adarna Bird)
        clickedCage = false; //True after user clicks on cage, after which it will disappear
        clickedRope = false; //True after user clicks on rope, after which it will disappear
        //Variables for Stage 13 (Find Brothers)
        currentScreen13 = 1; //Current screen being shown in Stage 13
        donPedroX = 390; //X position of Don Pedro
        donPedroY = 255; //Y position of Don Pedro
        donDiegoX = 205; //X position of Don Diego
        donDiegoY = 255; //Y position of Don Diego
        //Variables for Stage 14 (Betrayal)
        currentScreen14 = 1; //Current screen being shown in Stage 14
        //Variables for Stage 16 (Escaped Well)
        currentScreen16 = 1; //Current screen being shown in Stage 16
        //Variables for Stage 18 (Heal King)
        currentScreen18 = 1; //Current screen being shown in Stage 18
    }
    
    
    
    void update(int x, int y) {
        //Set all boolean variables indicating that mouse is over a specific button to false (reset)
        //Variables for pause game feature
        overHeart = false;  //Heart/pause/healing button to pause game and eat food to gain health
        overEatFood = false;  //Eat food button to consume 1 food item in exchange for health points
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
        overMinusIntelligence = false; //Button to subtract one point from intelligence trait
        //Variables for Stage 7 (Help Hermit)
        overHelpHermit = false; //Help hermit button
        overIgnoreHermit = false; //Ignore hermit button
        //Variables for Stage 9 (Find Adarna Bird)
        overSheerWill = false; //Sheer will button
        overLimeJuice = false; //Lime juice button
        //Variables for Stage 11 (Catch the Adarna Bird)
        overCatchAdarnaCage = false; //Player clicks golden cage to catch the bird
        overCatchAdarnaRope = false; //Player clicks on rope to catch the bird
        overAdarnaBird = false; //Player clicks on Adarna bird to catch it
        //Variables for Stage 13 (Find Brothers)
        overSaveThem = false; //Button to save brothers
        overleaveThem = false; //Button to leave brothers
        //Variables for Stage 18 (Heal King)
        overYes = false; //Button to forgive brothers
        overNo = false; //Button to not forgive brothers
        
        
        
        if (stage == 2 || stage == 3 || stage == 4 || stage == 6 || stage == 7 || stage == 8 || stage == 9 || stage == 12 || stage == 13 || stage == 14 || stage == 16 || stage == 17) {
            //Set boolean variable of a button to true if the mouse if over it (used for button clicks)
            if (overImage(heartButton, heartX, heartY)) {//Heart/pause/healing button
                overHeart = true; //Set variable indicating mouse is over heart/pause/healing button to true
                return;
            }
        }
    
        
        
        
                
        if (gamePaused) {
            //Set boolean variable of a button to true if the mouse if over it (used for button clicks)
            //Mouse is over the eat food button in the pause game screen (shows up if the player doesn't have full health, player must have at least 1 food item)
            if (player.getInventory().getNumFood() > 0 && player.getHealth() < player.getFullHealth() && overImage(eatFoodButton, eatFoodX, eatFoodY)) //Eat food button
                overEatFood = true; //Set variable indicating mouse is over eat food button to true
        } else {
            //Stage 0 (Main Menu)
            if (stage == 0) {
                //Mouse is over a specific button in Stage 0 (Main Menu)
                if (overImage(startGame, startGameX, startGameY)) //Start game button
                    overStartGame = true; //Set variable indicating mouse is over start game button to true
                else if (overImage(leaderboard, leaderboardX, leaderboardY)) //Show leaderboard button
                    overLeaderboard = true; //Set variable indicating mouse is over leaderboard button to true
                else if (overImage(exitGame, exitGameX, exitGameY)) //Exit game button
                    overExitGame = true; //Set variable indicating mouse is over exit game button to true
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

            //Stage 7 (Meet Hermit)
            } else if (stage == 7) {
                //Mouse is over a specific button in Stage 7 (Meet Hermit)
                if (overImage(helpHermitButton, helpHermitX, helpHermitY)) //Button to help hermit
                    overHelpHermit = true; //Set variable indicating mouse is over help hermit button to true
                //Mouse is over a specific button in Stage 7 (Meet Hermit)
                if (overImage(ignoreHermitButton, ignoreHermitX, ignoreHermitY)) //Button to ignore hermit
                    overIgnoreHermit = true; //Set variable indicating mouse is over ignore hermit button to true

            //Stage 9 (Find Adarna Bird)
            } else if (stage == 9) {
                //Mouse is over a specific button in Stage 9 (Find Adarna Bird)
                if (overImage(sheerWillButton, sheerWillX, sheerWillY)) //Button to use sheer will to stay awake
                    overSheerWill = true; //Set variable indicating mouse is over sheer will button to true
                if (player.getInventory().hasItem("Lime Juice")) {
                    if (overImage(limeJuiceButton, limeJuiceX, limeJuiceY)) //Button to use lime juice to stay awake
                    overLimeJuice = true; //Set variable indicating mouse is over lime juice button to true
                } //End if statement for if user has lime juice in their inventory

            //Stage 11 (Catch the Adarna Bird)
            } else if (stage == 11) {
                //If player has rope and golden cage in their inventory
                if (player.getInventory().hasItem("Rope") && player.getInventory().hasItem("Golden Cage")) {
                    //Mouse is over a specific button in Stage 11 (Catch the Adarna Bird)
                    if (overImage(catchAdarnaCage, catchAdarnaCageX, catchAdarnaCageY)) //Image of golden cage
                        overCatchAdarnaCage = true; //Set variable indicating mouse is over golden cage to true
                    else if (overImage(catchAdarnaRope, catchAdarnaRopeX, catchAdarnaRopeY)) //Image of rope
                        overCatchAdarnaRope = true; //Set variable indicating mouse is over rope to true
                //If player does not have rope and golden cage in their inventory
                } else {
                    //Mouse is over a specific button in Stage 11 (Catch the Adarna Bird)
                    if (overImage(adarnaBird, adarnaBirdX, adarnaBirdY)) //Image of Adarna bird
                        overAdarnaBird = true; //Set variable indicating mouse is over Adarna bird to true
                }

            //Stage 13 (Find Brothers)
            } else if (stage == 13) {
                //Mouse is over a specific button in Stage 13 (Find Brothers)
                if (overImage(saveThemButton, saveThemX, saveThemY)) //Button clicked to save brothers
                    overSaveThem = true; //Set variable indicating mouse is over save them button to true
                else if (overImage(leaveThemButton, leaveThemX, leaveThemY)) //Button clicked to elave brothers
                    overleaveThem = true; //Set variable indicating mouse is over leave them button to true


            //Stage 18 (Heal King)    
            } else if (stage == 18) {
                //Mouse is over a specific button in Stage 18 (Heal King)
                if (overImage(yesButton, yesX, yesY)) //Button clicked to forgive brothers
                    overYes = true; //Set variable indicating mouse is over yes button to true
                else if (overImage(noButton, noX, noY)) //Button clicked to not forgive brothers
                    overNo = true; //Set variable indicating mouse is over no button to true
            }
        } //End if statement checking if game is paused
        
        
        //If mouse is hovering over the the 
        if (overImage(testimg, testImgX, testImgY))
            overTestImg = true;
        else
            overTestImg = false; ////
        
        
            
    }
    
    @Override
    public void mousePressed() {
        
        if (stage == 2 || stage == 3 || stage == 4 || stage == 6 || stage == 7 || stage == 8 || stage == 9 || stage == 12 || stage == 13 || stage == 14 || stage == 16 || stage == 17) {
            //If mouse is over heart/pause/healing button when mouse is clicked
            if (overHeart) {
                gamePaused = true; //Pause game
                return;
            }
        }
        
    
        
        
        
        //Pause Game Screen
        if (gamePaused) {
            //If mouse is over eat food button when mouse is clicked (shows up if the player doesn't have full health, player must have at least 1 food item)
            if (player.getInventory().getNumFood() > 0 && player.getHealth() < player.getFullHealth() && overEatFood) 
                player.eatFood(); //Call method to consume food and give player a health boost
        } else {
        
    
    
            //Stage 0 (Main Menu)
            if (stage == 0) {
                //If mouse is over start game button when mouse is clicked
                if (overStartGame) {
                    //Check if the game hasn't reached the maximum number of players yet
                    if (Player.getNumPlayers() < 5)
                        stage = -1; //Continue to game/character setup screen if there are less than 5 players
                //If mouse is over leaderboard button when mouse is clicked
                } else if (overLeaderboard) {
                    stage = -4; //Go to leaderboard screen
                //If mouse is over exit game button when mouse is clicked
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
                    //If character clothing v2 is currently being shown, show v1
                    } else if (chooseCharacter2) {
                        chooseCharacter2 = false; //Set variable indicating character v2 is showing to false
                        chooseCharacter1 = true; //Set variable indicating character v1 is showing to true
                //If character clothing v3 is currently being shown, show v2
                    } else { 
                        chooseCharacter2 = true; //Set variable indicating character v2 is showing to true
                    } //End if statement deciding which character clothing version to show when left button is clicked

                //If mouse is over right arrow button when mouse is clicked
                else if (overRightArrow)
                //If character clothing v1 is currently being shown, show v3 (v1 is false and v2 is false)
                    if (chooseCharacter1) {
                        chooseCharacter1 = false; //Set variable indicating character v1 is showing to false
                        chooseCharacter2 = true; //Set variable indicating character v2 is showing to true
                //If character clothing v2 is currently being shown, show v3 (v1 is false and v2 is false)
                    } else if (chooseCharacter2) {
                        chooseCharacter2 = false; //Set variable indicating character v2 is showing to false
                //If character clothing v3 is currently being shown, show v1 (v1 is false and v2 is false)
                    } else {
                        chooseCharacter1 = true; //Set variable indicating character v1 is showing to true
                    } //End if statement deciding which character clothing version to show when right button is clicked




            //Stage 1 (Begin Story)
            } else if (stage == 1) {
            //If mouse is over plus strength button when mouse is clicked
                if (overPlusStrength) {
                //If the user has no strength points and no intelligence points
                    if (currentStrengthPoints == 0 && currentIntelligencePoints == 0)
                        currentStrengthPoints = 1; //Set current strength points to 1
                //If user has the maximum number of intelligence points
                    else if (currentIntelligencePoints == 2) {
                        currentStrengthPoints = 1; //Set current strength points to 1
                        currentIntelligencePoints = 1; //Set current intelligence points to 1
                //If user has no strength points and one intelligence point
                    } else if (currentStrengthPoints == 0) 
                        currentStrengthPoints = 1; //Set current strength points to 1
                //If user has one strength point and one intelligence point
                    else if (currentStrengthPoints == 1 && currentIntelligencePoints == 1) {
                        currentStrengthPoints = 2; //Set current strength points to 2
                        currentIntelligencePoints = 0; //Set current intelligence points to 0
                //If the user has one strength point and no intelligence points
                    } else {
                        currentStrengthPoints = 2; //Set current strength points to 2
                    } //End if statement deciding how to set the points when the plus strength button is clicked

            //If mouse is over minus strength button when mouse is clicked
                } else if (overMinusStrength) {
                //If user has maximum strength points
                    if (currentStrengthPoints == 2)
                       currentStrengthPoints = 1; //Set current strength points to 1
                 //If user has zero or one strength points
                    else {
                        currentStrengthPoints = 0; //Set current strength points to 0
                    } //End if statement deciding how to set the points when the minus strength button is clicked

            //If mouse is over plus intelligence button when mouse is clicked
                } else if (overPlusIntelligence) {
                //If the user has no strength points and no intelligence points
                    if (currentStrengthPoints == 0 && currentIntelligencePoints == 0)
                        currentIntelligencePoints = 1; //Set current intelligence points to 1
                //If user has the maximum number of strength points
                    else if (currentStrengthPoints == 2) {
                        currentStrengthPoints = 1; //Set current strength points to 1
                        currentIntelligencePoints = 1; //Set current intelligence points to 1
                //If user has no intelligence points and one strength point
                    } else if (currentIntelligencePoints == 0) 
                        currentIntelligencePoints = 1; //Set current intelligence points to 1
                //If user has one strength point and one intelligence point
                    else if (currentStrengthPoints == 1 && currentIntelligencePoints == 1) {
                        currentIntelligencePoints = 2; //Set current intelligence points to 2
                        currentStrengthPoints = 0; //Set current strength points to 0
                //If the user has one intelligence point and no strength points
                    }  else {
                        currentIntelligencePoints = 2; //Set current intelligence points to 2
                    } //End if statement deciding how to set the points when the plus intelligence button is clicked

            //If mouse is over minus intelligence button when mouse is clicked
                } else if (overMinusIntelligence) {
                //If user has maximum intelligence points
                    if (currentIntelligencePoints == 2)
                       currentIntelligencePoints = 1; //Set current intelligence points to 1
                //If user has zero or one intelligence points
                    else {
                        currentIntelligencePoints = 0; //Set current intelligence points to 0
                    } //End if statement deciding how to set the points when the minus intelligence button is clicked
                } ////might need to remove this


            //Stage 5 (Boar Fight Minigame)
                } else if (stage == 5) {
                    currentGame.mousePressed(); //Call mouse pressed method for boar fight minigame

                //Stage 7 (Meet Hermit)
                } else if (stage == 7) {
                //If user is deciding on whether to help or ignore the hermit
                    if (currentScreen7 == 2) {
                        //If mouse is over help hermit button when mouse is clicked
                        if (overHelpHermit)
                            playerHelpsHermit = true; //Indicate that player helped hermit, determines the next screen
                    //If mouse is over ignore hermit button when mouse is clicked
                        else if (overIgnoreHermit)
                            playerHelpsHermit = false; //Indicate that player didn't help hermit, determines the next screen
                        currentScreen7 = 3; //Set current screen of Stage 7 to the leaving the hermit
                    } //End if statement checking for screen 2 of Stage 7

            //Stage 9 (Adarna Bird)
                } else if (stage == 9) {
                //If user is deciding how to stay awake during the Adarna bird's songs
                    if (currentScreen9 == 2) {
                    //If mouse is over sheer will button when mouse is clicked
                        if (overSheerWill) {
                            sheerWill = true; //Indicate that player decided on using sheer will
                            currentScreen9 = 3; //Set current screen of Stage 9 to the rhythm game instructions
                    //If mouse is over lime juice button when mouse is clicked and user has lime juice in their inventory
                        } else if (overLimeJuice && player.getInventory().hasItem("Lime Juice")) {
                            sheerWill = false; //Indicate that player decided on using lime juice
                            currentScreen9 = 3; //Set current screen of Stage 9 to the rhythm game instructions
                        } //End if statement for if user's mouse is over sheer will button or lime juice button
                    } //End if statement checking for screen 2 of Stage 9


                //Rhythm Minigame
                } else if (stage == 10) {
                    currentGame.mousePressed(); //Call mouse pressed method for rhythm minigame


                //Stage 11 (Find Adarna)
                } else if (stage == 11) {
                    //If mouse is over golden cage when mouse is clicked
                    if (overCatchAdarnaCage) {
                        clickedCage = true; //Indicate that player clicked cage
                        if (clickedRope) { //If user already clicked rope as well
                           player.moveTo(360, 255); //Set new player position
                           adarnaBirdX = 320; //Set x position of Adarna bird in cage
                           adarnaBirdY = 315; //Set x position of Adarna bird in cage
                           stage = 12; //Go to the next stage (Caught Bird) 
                        } //End if statement checking if user already clicked rope as well
                    }
                    //If mouse is over rope when mouse is clicked
                    else if (overCatchAdarnaRope) {
                        clickedRope = true; //Indicate that player clicked rope
                        if (clickedCage) { //If user already clicked cage as well
                           player.moveTo(360, 255); //Set new player position
                           adarnaBirdX = 320; //Set x position of Adarna bird in cage
                           adarnaBirdY = 315; //Set x position of Adarna bird in cage
                           stage = 12; //Go to the next stage (Caught Bird) 
                        } //End if statement checking if user already clicked rope as well
                    }
                    //If mouse is over Adarna bird when mouse is clicked
                    if (overAdarnaBird) {
                        player.moveTo(360, 225); //Set new player position
                        adarnaBirdX = 335; //Set x position of Adarna bird 
                        adarnaBirdY = 355; //Set x position of Adarna bird
                        stage = 12; //Go to the next stage (Caught Bird) 
                    }

                //Stage 12 (Find Brothers)          
                } else if (stage == 13) {
                    //If user is deciding whether to save or leave brothers
                    if (currentScreen13 == 2) {
                        //If mouse is over save them button when mouse is clicked
                        if (overSaveThem) {
                            player.setSavedBrothers(true); //Set variable to indicate player saved brothers
                            player.addVirtue(50); //Give player virtue points for saving their brothers
                            currentScreen13 = 3; //Set current screen of Stage 12 to after saving the brothers
                        //If mouse is leave them button when mouse is clicked
                        } else if (overleaveThem) {
                            player.setSavedBrothers(false); //Set variable to indicate player left brothers
                            currentScreen13 = 4; //Set current screen of Stage 12 to after leaving the brothers
                        } //End if statement checking if user's mouse is over save brothers button or leave brothers button
                    } //End if statement checking for screen 2 of Stage 13


                //Stage 15 (Escape Well Minigame)
                } else if (stage == 15) {
                    currentGame.mousePressed(); //Call mouse pressed method for escape well minigame


                //Stage 18 (Heal King)
                } else if (stage == 18) {
                    //If user is deciding whether to forgive brothers
                    if (currentScreen18 == 3) {
                        //If mouse is over yes button when mouse is clicked
                        if (overYes) {
                            player.setForgaveBrothers(true); //Set variable to indicate player forgave brothers
                            player.addVirtue(50); //Give player virtue points for forgiving their brothers
                        //If mouse is no button when mouse is clicked
                        } else if (overNo)
                            player.setForgaveBrothers(false); //Set variable to indicate player didn't forgive brothers
                        //End if statement checking if user's mouse is over save brothers button or leave brothers button
                        stage = 19; //Go to the next stage (ending)
                    } //End if statement checking for screen 2 of Stage 13
                }

        
            } //End if statement checking if game is paused
                
            }
            
            
        
        
    

      
    @Override
    public void keyPressed() {
        //If the spacebar is pressed
        if (key == ' ') {
            //If player ran out of health and game is over
            if (playerDeath)
                gameOver(); //Call game over method
            
            //Spacebar is pressed when game is paused
            if (gamePaused) {
                gamePaused = false; //Resume game
                
            //Spacebar is pressed when game is not paused
            } else {

                //Spacebar is pressed in Stage -3 (Custom Clothes)
                if (stage == -3) {
                    stage = -1; //Go back to character setup screen
                //Spacebar is pressed in Stage -4 (Leaderboard)
                } else if (stage == -4) {
                    stage = 0; //Go back to main menu
                //Spacebar is pressed in Stage 1 (Begin Story)
                } else if (stage == 1) {
                    if (currentScreen1 == 1) //If screen currently being shown is the first part of the story introduction
                        currentScreen1 = 2; //Go to next screen (second part of story introduction)
                    else if (currentScreen1 == 2) //If screen currently being shown is the second part of the story introduction
                        currentScreen1 = 3; //Go to next screen (character traits distribution)
                    else if (currentScreen1 == 3) { //If screen currently being shown is the character traits distrubution
                        pointsError = false; //Set error indicator for points distribution false in case it was set to true previously
                        //If the user has not distributed both of their points
                        if ((currentStrengthPoints == 0 && currentIntelligencePoints == 0) || (currentStrengthPoints == 1 && currentIntelligencePoints == 0) || (currentStrengthPoints == 0 && currentIntelligencePoints == 1)) {
                            pointsError = true; //Set variable indicating there was an error with the points to true
                        } else { //If user has distributed both of their points
                            pointsError = false; //Reset boolean variable for points distribution error
                            currentScreen1 = 4; //Go to next screen (first part of the game instructions)
                        }
                    } else if (currentScreen1 == 4) //If screen currently being shown is the first part of the game instructions
                        currentScreen1 = 5; //Go to next screen (second part of the game instructions)
                    else { //If screen currently being shown is the second part of the game instructions

                        //Create an instance of the Player class to represent the character the user will be playing as
                        //If the user entered their own name and character clothing
                        if (customName && customCharacter) {
                            if (chooseCharacter1) //If the player chose character option 1
                            //Create new Player with parameters for name and character image, using the image of character 1
                                player = new Player(this, 155, 255, enteredName, traitDistribution, new Inventory(3), "images/chosenCharacter1.png");
                            else if (chooseCharacter2) //If the player chose character option 2
                                //Create new Player with parameters for name and character image, using the image of character 2
                                player = new Player(this, 155, 255, enteredName, traitDistribution, new Inventory(3), "images/chosenCharacter2.png");
                            else //If the player chose character option 3
                            //Create new Player with parameters for name and character image, using the image of character 3
                                player = new Player(this, 155, 255, enteredName, traitDistribution, new Inventory(3), "images/chosenCharacter3.png");
                        //If user entered their own name but did not customize the character clothing
                        } else if (customName) {
                        //Create new Player with parameter for the name
                            player = new Player(this, 155, 255, enteredName, traitDistribution, new Inventory(3));
                        //If user did not enter their own name but customized the character clothing
                        } else if (customCharacter) {
                            if (chooseCharacter1) //If the player chose character option 1
                           //Create new Player with parameter for the character image, using image of character 1
                               player = new Player(this, 155, 255, traitDistribution, new Inventory(3), "images/chosenCharacter1.png");
                            else if (chooseCharacter2) //If the player chose character option 2
                           //Create new Player with parameter for the character image, using image of character 2
                               player = new Player(this, 155, 255, traitDistribution, new Inventory(3), "images/chosenCharacter2.png");
                            else //If the player chose character option 3
                           //Create new Player with parameter for the charcter image, using image of character 3
                               player = new Player(this, 155, 255, traitDistribution, new Inventory(3), "images/chosenCharacter3.png");
                        //If user did not enter their own name and did not customize the character clothing
                        } else {
                         //Create new Player using constructor with no extra parameters to set default values for name and character image
                            player = new Player(this, 155, 255, traitDistribution, new Inventory(3));
                        } //End if statement deciding which Player constructor to call depending on the custom values entered by the user

                        //If the maximum number of players has been reached after creating this player (maximum 5 players)
                        if (Player.getNumPlayers() >= 5)
                            //Set message telling the user that the maximum number of players has been reached to true
                            reachedMaxPlayers = true;
                        stage = 2; //Go to the next stage (Leaving Castle)
                        currentScreen1 = 1; //Reset screen to be shown for Stage 1 to the first part of the story introduction
                    }   //End if statement for which screen of Stage 1 is being shown
                //Spacebar is pressed in Stage 2 (Leaving Castle)
                } else if (stage == 2) {
                    stage = 3; //Go to next stage (Fork in the Road)
                    player.moveTo(-90, 255); //Set new player position
                //Spacebar is pressed in Stage 4 (Encounter boar)
                } else if (stage == 4) {    
                    if (currentScreen4 == 1) { //If screen currently being shown is the encounter with the boar
                        if (willFightBoar) { //If user chose path with boar
                            currentScreen4 = 2; //Display boar fight minigame instructions
                        } else { //If user chose path without boar 
                            player.moveTo(85, 240); //Set new player position
                            stage = 7; //Go to the next stage (Meet Hermit)
                        } //End if statement for if user chose path with the boar
                    } else if (currentScreen4 == 2){ //If screen currently being shown is the boar fight minigame instructions
                        //Create an instance of the Boar Fight Game class depending on the user's character image
                        //If the user's character clothing is v1
                        if (chooseCharacter1)
                            //Create instance of boar fight minigame with character clothing image v1
                            currentGame = new BoarFightGame(this, player.getName(), 100, 0, "images/chosenCharacter1.png", traitDistribution);
                        //If the user's character clothing is v2
                        else if (chooseCharacter2)
                            //Create instance of boar fight minigame with character clothing image v2
                            currentGame = new BoarFightGame(this, player.getName(), 100, 0, "images/chosenCharacter2.png", traitDistribution);
                        //If the user's character clothing is v3
                        else
                            //Create instance of boar fight minigame with character clothing image v3
                            currentGame = new BoarFightGame(this, player.getName(), 100, 0, "images/chosenCharacter3.png", traitDistribution);

                        stage = 5; //Go to the boar fight minigame
                    } //If statement for which screen of Stage 4 is being shown


                //Meet Hermit
                } else if (stage == 7) {
                    //If the user is meeting the hermit for the first time
                    if (currentScreen7 == 1)
                        currentScreen7 = 2; //Go to the next screen (deciding on whether to help or ignore the hermit)
                    //if the user is leaving the hermit
                    if (currentScreen7 == 3) { 
                        player.moveTo(-90, 255); //Set new player position
                        stage = 8; //Go on to the next stage (Walking in the Forest 2)
                    } //End if statement for if current screen of Stage 7 is leaving the hermit
                //Find Adarna Bird
                } else if (stage == 9) {
                    //If screen currently being shown is the first sighting of the Adarna bird
                    if (currentScreen9 == 1) 
                        currentScreen9 = 2; //Show button options to stay awake using sheer will or lime juice
                    //If screen currently being shown is the rhythm minigame instructions
                    else if (currentScreen9 == 3) { 
                        //Create an instance of the Boar Fight Game class depending on the user's character image
                        //If the user's character clothing is v1
                        if (chooseCharacter1)
                            //Create instance of rhythm minigame with character clothing image v1
                            currentGame = new RhythmGame(this, player.getName(), 245, 1, "images/chosenCharacter1.png", traitDistribution, sheerWill);
                        //If the user's character clothing is v1 
                         else if (chooseCharacter2)
                            //Create instance of rhythm minigame with character clothing image v2
                            currentGame = new RhythmGame(this, player.getName(), 245, 1, "images/chosenCharacter2.png", traitDistribution, sheerWill);
                        //If the user's character clothing is v1
                        else
                            //Create instance of rhythm minigame with character clothing image v3
                            currentGame = new RhythmGame(this, player.getName(), 245, 1, "images/chosenCharacter3.png", traitDistribution, sheerWill);

                        adarnaBirdX = -100; //Set x position of Adarna bird
                        adarnaBirdY = 225; //Set y position of Adarna bird

                        //stage = 10; //Go to the Rhythm Minigame //////////skip minigame
                        stage = 11;
                    } //If statement for which screen of Stage 9 is being shown

                //Caught the Adarna Bird
                } else if (stage == 12) {
                    player.moveTo(595, 255); //Set new player position
                    //If player has golden cage in their inventory from the hermit
                    if (player.getInventory().hasItem("Golden Cage")) {
                        adarnaBirdX = 550; //Set x position of Adarna bird in golden cage
                        adarnaBirdY = 345; //Set y position of Adarna bird in golden cage
                    //If player does not have golden cage in their inventory
                    } else {
                        adarnaBirdX = 570; //Set x position of Adarna bird
                        adarnaBirdY = 370; //Set y position of Adarna bird
                    } //End if statement checking if player has golden cage in their inventory
                    stage = 13; //Go to the next stage (Find Brothers)

                //Find Brothers
                } else if (stage == 13) {
                    //If the user is seeing their brothers turned to stone for the first time
                    if (currentScreen13 == 1)
                        currentScreen13 = 2; //Go to the next screen (deciding to save or leave brothers)
                    //If user just decided to save their brothers
                    else if (currentScreen13 == 3) {
                        stage = 14; //Go to the next stage (Betrayal)
                        player.moveTo(360, 260); //Set new player position
                        donPedroX = 495; //Set x position of brother 1
                        donPedroY = 260; //Set y position of brother 1
                        donDiegoX = 615; //Set x position of brother 2
                        donDiegoY = 260; //Set y position of brother 2
                        //If player has golden cage in their inventory from the hermit
                        if (player.getInventory().hasItem("Golden Cage")) {
                            adarnaBirdX = 580; //Set x position of Adarna bird in golden cage
                            adarnaBirdY = 330; //Set y position of Adarna bird in golden cage
                        //If player does not have golden cage in their inventory
                        } else {
                            adarnaBirdX = 590; //Set x position of Adarna bird
                            adarnaBirdY = 375; //Set y position of Adarna bird
                        } //End if statement checking if player has golden cage in their inventory
                } //End if statement checking if current screen of Stage 13 is 3 or 4

                //Spacebar is pressed in Stage 14 (Betrayal)
                } else if (stage == 14) {
                    if (currentScreen14 == 1) //If screen currently being shown is when the brothers take the Adarna bird
                        currentScreen14 = 2; //Go to next screen (push player down the wall)
                    else if (currentScreen14 == 3) //If screen currently being shown is the brothers leaving the player
                        currentScreen14 = 4; //Go to next screen (well minigame instructions)
                    else if (currentScreen14 == 4){ //If screen currently being shown is the well minigame instructions
                        //Create an instance of the Well Escape Game class depending on the user's character image
                        //If the user's character clothing is v1
                        if (chooseCharacter1)
                            //Create instance of escape well minigame with character clothing image v1
                            currentGame = new EscapeWellGame(this, player.getName(), 50, 1, "images/chosenCharacter1.png", traitDistribution);
                        //If the user's character clothing is v2
                        else if (chooseCharacter2)
                            //Create instance of escape well minigame with character clothing image v2
                            currentGame = new EscapeWellGame(this, player.getName(), 50, 1, "images/chosenCharacter2.png", traitDistribution);
                        //If the user's character clothing is v3
                        else
                            //Create instance of escape well minigame with character clothing image v3
                            currentGame = new EscapeWellGame(this, player.getName(), 50, 1, "images/chosenCharacter3.png", traitDistribution);

                        stage = 15; //Go to the next stage (Well Minigame)
                    }

                //Spacebar is pressed in Stage 16 (Escaped Well)
                } else if (stage == 16) {
                    if (currentScreen16 == 1) //If screen currently being shown is when user just got out of the well
                        currentScreen16 = 2; //Go to the next screen (leaving the well)


                //Spacebar is pressed in Stage 18 (Heal King)
                } else if (stage == 18) {
                    if (currentScreen18 == 1) //If screen currently being shown is when player decided to save their brothers and just arrived back at the palace
                        currentScreen18 = 2; //Go to the next screen
                    else if (currentScreen18 == 2) //If screen currently being shown is when player decided to save their brothers and the bird heals the king
                        currentScreen18 = 3; //Go to the next screen
                    else if (currentScreen18 == 4) //If screen currently being shown is when player decided to leave their brothers and just arrived back at the palace
                        currentScreen18 = 5; //Go to the next screen
                    else if (currentScreen18 == 5) //If screen currently being shown is when player decided to leave their brothers and the bird heals the king
                        stage = 19; //Go to the next stage (Ending)

                //Spacebar is pressed in Stage 19 (Ending)    
                } else if (stage == 19) {
                    gameOver(); //Call game over method
                    

                //Spacebar is pressed in Stage -5/-6/-7 (Displaying Boar/Rhythm/Well Minigame Results)    
                } else if (stage == -5 || stage == -6 || stage == -7) {
                    stage = -4; //Return to leaderboard
                }
                
            } //End if statement checking if game was paused

                
                
        } 
        
        //If key is pressed in Stage -2 (Choosing Name)
        if (stage == -2) {
            //If enter key is pressed
            if (keyCode == ENTER) { 
                nameError = false; //Set error indicator for name to false in case it was set to true previously
                if (userInput.isEmpty() || userInput.length() > 10) //If the user's input is not between 1 to 10 characters
                    nameError = true; //Set error indicator for name to true
                else {  //If the user's input matches the length requirements
                    enteredName = userInput; //Set entered name variable to user input
                    userInput = ""; //Clear user input
                    nameError = false; //Reset boolean variable for name length error
                    stage = -1; //Return to character setup screen
                } //End if statement checking if user’s input matches the length requirements
            //If backspace key is pressed
            } else if (keyCode == BACKSPACE) {
                if (userInput.length() > 0) //If user has typed in some letters on the screen
                    userInput = userInput.substring(0, userInput.length() - 1); //Remove last character from user input string
             
            //If user enters an ASCII character
            } else if (key != CODED) { 
                userInput += key; //Add each keystroke to user input string
            } //End if statement for which key is pressed
        //If key is pressed in Stage -4 (Leaderboard)
        } else if (stage == -4) {
            //If player presses A
            if (key == 'A' || key == 'a')
                stage = -5; //Go to Stage -5 (Boar Fight Game Leaderboard)
            else if (key == 'B' || key == 'b')
                stage = -6; //Go to Stage -6 (Rhythm Game Leaderboard)
            else if (key == 'C' || key == 'c')
                stage = -7; //Go to Stage -7 (Well Escape Game Leaderboard)
            
        //If key is pressed in Stage 3 (Fork in the Road)
        } else if (stage == 3) {
            //If user presses left arrow key
            if (keyCode == LEFT) {
                willFightBoar = false; //User will not play boar fight minigame
                stage = 4; //Go to the next stage (Encounter Boar)
                player.moveTo(-90, 255); //Set new player position
            //If user presses right arrow key
            } else if (keyCode == RIGHT){
                willFightBoar = true; //User will play boar fight minigame
                stage = 4; //Go to the next stage (Encounter Boar)
                player.moveTo(-90, 255); //Set new player position
            } //End if statement for which key is pressed

        //If key is pressed in Stage 5 (Boar Fight Minigame)
        } else if (stage == 5) {
            currentGame.keyPressed(); //Call key pressed method for boar fight game

        //If key is pressed in Stage 10 (Rhythm Minigame)
        } else if (stage == 10) {
            currentGame.keyPressed(); //Call key pressed method for rhythm game
            
        //If key is pressed in Stage 15 (Escape Well Minigame)    
        } else if (stage == 15) {
            currentGame.keyPressed(); //Call key pressed method for escape well game
            
        }
        
    }
     
   boolean overImage(PImage image, int x, int y)  {
        //If mouse is over the specified image
        if (mouseX >= x && mouseX <= x+image.width && 
            mouseY >= y && mouseY <= y+image.height) {
          return true; //Return true
        //If mouse is not over the specified image
        } else { 
          return false; //Return false
        } //End if statement checking if mouse is over image
    }
   
 
   /**
    * This method iterates through all of the previously set Game objects (Boar/Rhythm/Well) from all of the times and adds each game settings object to the game settings history combo box
     * 
    * @param gamesPlayed 
    */
   public void displayBoarGameResults(ArrayList<Game> gamesPlayed) {
       BoarFightGame currentBoarGame; //Current Boar Fight Game object whose information is being displayed
       int playerIndex = 0; //Row that the information will be displayed at
       
        //Iterate through each Game object in the array list of games
        for (Game game : gamesPlayed) {
            //Check if the Game object's instance type is BoarFightGame so that it can be downcast
            if (game instanceof BoarFightGame) {
                //Downcast Game object to BoarFightGame object
                currentBoarGame = ((BoarFightGame) game);
                
                //Display information for (attributes of this object) in one row
                //Display player name (first column)
                text(currentBoarGame.getName(), gamePositions[playerIndex][0], gamePositions[playerIndex][1]);
                //Display score (second column)
                text(currentBoarGame.getScore(), gamePositions[playerIndex + 5][0], gamePositions[playerIndex + 5][1]);
                //Display total time (third column)
                text(currentBoarGame.getTotalTime() + " s", gamePositions[playerIndex + 2 * 5][0], gamePositions[playerIndex + 2 * 5][1]);
                
                //Go to next row for the next BoarFightGame object that is found
                playerIndex++;
            } //End if statement checking if the Game object is an instance of BoarFightGame
        } //End for loop iterating through array list of settings Game objects
   }
   
    public void displayRhythmGameResults(ArrayList<Game> gamesPlayed) {
       RhythmGame currentRhythmGame; //Current Rhythm Game object whose information is being displayed
       int playerIndex = 0; //Row that the information will be displayed at
       
        //Iterate through each Game object in the array list of games
        for (Game game : gamesPlayed) {
            //Check if the Game object's instance type is RhythmGame so that it can be downcast
            if (game instanceof RhythmGame) {
                //Downcast Game object to RhythmGame object
                currentRhythmGame = ((RhythmGame) game);
                
                //Display information for (attributes of this object) in one row
                //Display player name (first column)
                text(currentRhythmGame.getName(), gamePositions[playerIndex][0], gamePositions[playerIndex][1]);
                //Display score (second column)
                text(currentRhythmGame.getScore(), gamePositions[playerIndex + 5][0], gamePositions[playerIndex + 5][1]);
                //Display number of letters matched (third column)
                text(currentRhythmGame.getLettersMatched(), gamePositions[playerIndex + 2 * 5][0], gamePositions[playerIndex + 2 * 5][1]);
                
                //Go to next row for the next RhythmGame object that is found
                playerIndex++;
            } //End if statement checking if the Game object is an instance of RhythmGame
        } //End for loop iterating through array list of settings Game objects
   }
    
    public void displayWellGameResults(ArrayList<Game> gamesPlayed) {
       EscapeWellGame currentWellGame; //Current Escape Well object whose information is being displayed
       int playerIndex = 0; //Row that the information will be displayed at
       String winOrLose; //Says whether the player won or lost the game
       
        //Iterate through each Game object in the array list of games
        for (Game game : gamesPlayed) {
            //Check if the Game object's instance type is EscapeWellGame so that it can be downcast
            if (game instanceof EscapeWellGame) {
                //Downcast Game object to EscapeWellGame object
                currentWellGame = ((EscapeWellGame) game);
                //Set win or lose variable to "Lost" at first
                winOrLose = " (Lost)";
                
                //Display information for (attributes of this object) in one row
                //Display player name (first column)
                text(currentWellGame.getName(), gamePositions[playerIndex][0], gamePositions[playerIndex][1]);
                //Display score (second column)
                text(currentWellGame.getScore(), gamePositions[playerIndex + 5][0], gamePositions[playerIndex + 5][1]);
                //Check if player won this game
                if (currentWellGame.playerWonGame())
                    winOrLose = " (Won)"; //If player won the current game, set win or lose text to display "Won"
                //Display number of letters matched (third column)
                text(currentWellGame.getRungsUsed() + winOrLose, gamePositions[playerIndex + 2 * 5][0], gamePositions[playerIndex + 2 * 5][1]);
                
                //Go to next row for the next RhythmGame object that is found
                playerIndex++;
            } //End if statement checking if the Game object is an instance of RhythmGame
        } //End for loop iterating through array list of settings Game objects
   }
   
   
   
    /**
     * Sorts players' total points in ascending order using the Bubble Sort algorithm, also sorts the associated player data (names, 
     * virtue points, game points) to maintain their correct order after ordering the total points
     *
     * @param names  The array of player names corresponding to the sorted total points
     * @param virtuePoints  The array of virtue points corresponding to the sorted total points
     * @param gamePoints  The array of game points corresponding to the sorted total points
     * @param totalPoints  The array of total points to be sorted
     */
    public static void bubbleSort(String[] names, String[] virtuePoints, String[] gamePoints, String[] totalPoints) {
        int n = totalPoints.length; //Get length of array of total points

        //Outer loop to traverse the array
        for (int i = 0; i < n - 1; i++) {
            //Inner loop to perform the comparisons and swaps
            for (int j = 0; j < n - i - 1; j++) {
                //If the current element is less than the next element, swap them
                if (Integer.parseInt(totalPoints[j]) < Integer.parseInt(totalPoints[j + 1])) {
                    //Swap arr[j] and arr[j + 1]
                    //Swap elements for total points
                    String temp = totalPoints[j]; //Initialize the temporary variable for storing the first element
                    totalPoints[j] = totalPoints[j + 1]; //Assigns first element the value of the second element
                    totalPoints[j + 1] = temp; //Assigns the second element the value of the first element stored in the temporary variable
                    
                    //Swap elements for player names
                    temp = names[j]; //Store first element in temporary variable
                    names[j] = names[j + 1]; //Assigns first element the value of the second element
                    names[j + 1] = temp; //Assigns the second element the value of the first element stored in the temporary variable
                    
                    //Swap elements for virtue points
                    temp = virtuePoints[j];//initializing the temporary variable for comparision
                    virtuePoints[j] = virtuePoints[j + 1]; //Assigns first element the value of the second element
                    virtuePoints[j + 1] = temp; //Assigns the second element the value of the first element stored in the temporary variable
                    
                    //Swap elements for game points
                    temp = gamePoints[j]; //initializing the temporary variable for comparision
                    gamePoints[j] = gamePoints[j + 1]; //Assigns first element the value of the second element
                    gamePoints[j + 1] = temp; //Assigns the second element the value of the first element stored in the temporary variable
                } //End if statement checking if a swap should be made
            } //End for loop for comparison and swaps
        } //End for loop to traverse the array
    } 
    
    
}



