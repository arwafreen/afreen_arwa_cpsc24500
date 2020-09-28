/**
 * Object Oriented Programming
 * @author arwae
 * 09/28/2020
 * Game Zone Assignment
 */

// Import Scanner
import java.util.Scanner;

// Import Random
import java.util.Random;

public class GameZone {
	
	// Create Scanner and Random objects
			Scanner sc = new Scanner(System.in);
			Random rand = new Random();
			
	public static void main(String[] args){
		
		// Print Welcome banner at start
		Scanner sc = new Scanner(System.in);
		System.out.println("******************************************");
		System.out.println("*        Welcome to the Game Zone        *");
		System.out.println("******************************************");
	    
		// Create loop that repeats until user chooses to quit	
		// Set option to 0 
		int option = 0;
		
		do {	 			
		    // Prints the menu of options
		    System.out.println("What would you like to play?");
		    System.out.println("1. Twenty-one");
		    System.out.println("2. Rock Paper Scissors");
		    System.out.println("3. Neither - I'm done!");
		    
		    // Ask for user input
		    System.out.print("Please enter the number of your choice: ");
		    
		    // Set option as user input value
		    option = Integer.parseInt(sc.nextLine());
		    
		    // If user selects option 1 start TwentyOne game
		    if (option == 1) {
		    	TwentyOne();
		    }
		    
		    // If user selects option 2 start RockPaper game
		    if (option == 2) {
		    	RockPaper();
		    }
		    	
		} while (option != 3);
			
		//if (option == 3) {
			// Close Scanner Object 
			sc.close();
			// Display Exit message
			System.out.println("Thank you for playing");
		//}
	}
	
	// Create Twenty One game
	public static void TwentyOne(){
		
		// Create Scanner and Random objects
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		// Set score to 0 for start of game
		int score = 0;
		
		// Create blank string to store yes/no for further draws
		String drawAgain = "";
	   
	   // Create loop to continue to add cards until user says no
	   do{
		   
	   // User random utility to create random draw amount and display amount
	   int newDraw = rand.nextInt(11)+1;
	   System.out.println("You drew "+ newDraw + ".");
	   
	   // Add the newDraw value to the total score of the user and display total
	   score = score + newDraw; 
	   System.out.println("Your current total is "+ score +".");
	   
	   // If user score greater than 21, display user lost
	   if(score > 21){
	       System.out.println("You lost.");
	       return;
	   }
	   
	   // If user score is equal to 21, display user won
	   if(score == 21){
	       System.out.println("You won!");
	       return;
	   }
	   
	   // Ask user if they would like to draw another card
	   System.out.print("Do you want to draw another card? ");
	   
	   // Store user input as String drawAgain
	   drawAgain = sc.nextLine();
	   
	   // Check is drawAgain contains n letter to check if user answered yes or no
	   // Continue loop while user input is yes
	   } while(drawAgain.compareToIgnoreCase("n") != 0);
	
	   // Use random to create random draw by dealer/computer in range 13 to 20
	   int compDraw = rand.nextInt(8)+13;
	   
	   // Display the computer draw
	   System.out.println("The computer drew "+ compDraw +".");
	   
	   // If computer score is higher, display user lost
	   if(score < compDraw){
		   System.out.println("You lost!");
		   return;
	   }
	   
	   // If player has more or equal score than computer, display user won
	   else{
		   System.out.println("You won.");

		   return;
	   }
	   
	}

	// Create Rock Paper Scissors game
	public static void RockPaper(){

		// Store user choice as generated option
		int userNum = getRandomNum();
		
		// Store computer choice as generated option
		int compNum = getRandomNum();
		
		// Determine which option was selected for user
		String userOption = whichRPS(userNum);
		
		// Determine which option was selected for computer
		String compOption = whichRPS(compNum);
		
	   // Display user choice and computer choice
	   System.out.println("You played "+ userOption+", and the computer played "+ compOption);
	   
	   // Determine results of game
	   
	   // If user and computer choices are the same 
	   if (userNum == compNum) {
		   System.out.println("It was a tie.");
	   }  
	   
	   // Rock1 > Scissors3
	   if (compNum==1 && userNum==3) {
		   System.out.println("You lost.");
	   }
	   // Scissors3 > Paper2
	   if (compNum==3 && userNum==2) {
		   System.out.println("You lost.");
	   }
	   // Paper2 > Rock1
	   if (compNum==2 && userNum==1) {
		   System.out.println("You lost.");
	   }
	   // Or else the user won
	   else{
	   System.out.println("You won.");
	   }
	   return;
	}
	
	// Random number between 1, 2 and 3 generator function
	public static int getRandomNum() {
		Random rand = new Random();
		int num = rand.nextInt(3)+1;
		return num;
	}
	
	// Function determines if option selected was rock, paper or scissor
	public static String whichRPS(int num){
		switch(num) {
		case 1:
			return "Rock";
		case 2:
		   return "Paper";
		// Return scissor if number is not 1 or 3 as it is then 3
		default:
			return "Scissors";
	   }
	}
}