/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ibongadarnagame;

import java.util.ArrayList;

/**
 *
 * @author 343330528
 */
public class Inventory {
    //Instance variables
    private ArrayList<String> items = new ArrayList<>(); //List of items in player's inventory
    private int numFood; //Number of food items (health boosts) the player has
    
    /**
     * Constructor creates an instance of the Inventory class and sets the number of food the player has as the entered amount (won in
     * the mini-game) 
     * 
     * @param numFood  Number of food items the player has
     */
    public Inventory(int numFood) {
        this.numFood = numFood;
    }
    
    
    
    /**
     * Getter method for numFood attribute of Inventory object
     * 
     * @return  Returns number of food in player's inventory
     */
    public int getNumFood() {
        return numFood; //Returns number of food items left in inventory
    }
    
    
    
    
    /**
     * Adds entered item to the player's inventory
     * 
     * @param item  Name of item to add to the inventory
     */
    public void addItem(String item) {
        items.add(item); //Add item to array list of inventory items
    }
    
    /**
     * Removes entered item from player's inventory
     * 
     * @param item  Name of item to remove from the inventory
     */
    public void removeItem (String item) {
        items.remove(item); //Remove item from array list of inventory items
    }
    
    /**
     * Checks if item exists in player's inventory
     * 
     * @param targetItem  Name of item being checked
     * @return            Returns true if item exists in inventory, otherwise returns false
     */
    public boolean hasItem(String targetItem) {
        return items.contains(targetItem); //Check if target item exists in array list, return true/false
    }
    
    /**
     * Consumes one food item from user's inventory
     */
    public void consumeFood() {
        numFood -= 1; //Reduce number of food in inventory by one
    }
    
    
}
