/**
 * Arwa Afreen
 * CPSC-24500-001
 * Baseball Assignment
 * @author arwae
 * 10/05/2020
 */

// FILE INPUT C:\Users\arwae\OneDrive\Desktop\baseball_standings.txt
package baseball;

// Import utilities
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


public class baseball {
	
	 // Main method
	 public static void main(String[] args) {
		 
	     // Display title text
	     System.out.println("*****************************************");
	     System.out.println("*      BASEBALL STANDINGS ANALYZER      *" );
	     System.out.println("*****************************************");
	     System.out.println("This program reads a file that contains current baseball\n" +
	     "standings and adds tomore detailed statistics. It also prints\n" +
	     "overall standings in the American and national leagues.\n"); 
	     
	     // Create ArrayList objects for each team
	     ArrayList<String> target = null;
	     ArrayList<String> aleast = new ArrayList<String>();
	     ArrayList<String> alcentral = new ArrayList<String>();
	     ArrayList<String> alwest = new ArrayList<String>();
	     ArrayList<String> nleast = new ArrayList<String>();
	     ArrayList<String> nlcentral = new ArrayList<String>();
	     ArrayList<String> nlwest = new ArrayList<String>();
	     ArrayList<String> overall = new ArrayList<String>();
	     
	     
	     // Declare variables
	     int option;
	     String line1;
	     String caseTxt;
	     
	     // Create String array to store line sections
	     String[] lineSection;
	     
	     // Set file existing default as true
	     boolean fileExists = true; 
	     
		 // Create Scanner Object
	     Scanner sc = new Scanner(System.in);
	     
	     // Ask user to input file name
	     System.out.println("Enter the name of the standings file: ");
	     String file1 = sc.nextLine();
	     
	     // Attempt to open file, split and store data into ArrayLists of teams
	     try{
	    	 
	    	 // Create scanner object to open file object and read lines
	         Scanner sc2 = new Scanner(new File(file1));
	         
	         // Continue reading file while next line exists
	         while(sc2.hasNextLine()){
	        	 
	        	 // Set next line as line1
	             line1 = sc2.nextLine();
	             
	             // Store line section in array
	             lineSection = line1.split("\t");
	             
	             // Compare section 0, team name and store data in team's array
	             if(lineSection[0].equalsIgnoreCase("LEAGUE")){
	                 caseTxt = lineSection[1].toUpperCase();
	                 if(caseTxt.equalsIgnoreCase("AL EAST")){
	                     target = aleast;
	                 } else if(caseTxt.equalsIgnoreCase("AL CENTRAL")){
	                     target = alcentral;
	                 }else if(caseTxt.equalsIgnoreCase("AL West")){
	                     target = alwest;
	                 }else if(caseTxt.equalsIgnoreCase("NL EAST")){
	                     target = nleast;
	                 }else if(caseTxt.equalsIgnoreCase("NL CENTRAL")){
	                     target = nlcentral;
	                 }else if(caseTxt.equalsIgnoreCase("NL WEST")){
	                     target = nlwest;
	                 }
	             } else{
	                 target.add(line1);
	                 sortWin(overall, line1);
	             }
	         }
	         
	         // Close file
	         sc2.close();
	         
	     // If file does not open, set fileExists to false
	     } catch(Exception ex){
	         System.out.println("File not found!");
	         fileExists = false;      
	     }

	     // While file exists run method based on user option selected
	     if(fileExists){
	    	 
	    	 // Loop to perform functions based on user input
	         do{
	             option = printMenuAndGetOption(sc);
	             if(option == 1){
	                 options1to6(aleast);
	                 
	             } else if(option == 2){
	                 options1to6(alcentral);
	                 
	             } else if(option == 3){
	                 options1to6(alwest);
	                 
	             } else if(option == 4){
	                 options1to6(nleast);
	                 
	             } else if(option == 5){
	                 options1to6(nlcentral);
	                 
	             } else if(option == 6){
	                 options1to6(nlcentral);
	                 
	             } else if(option == 7){
	                 option7(overall);
	             }
	             
	           // Continue loop while option 8 not chosen
	         } while (option != 8);
	         
	         // Exit Program when option 8 selected
	         System.out.println("Successful Exit!");
	         System.exit(1);
	     }
	 }
	
	 // Function to display menu and store user input of menu option return option
	 public static int printMenuAndGetOption(Scanner sc){
	     System.out.println("Which Standings would you like to see?");
	     System.out.println("1. AL East");
	     System.out.println("2. Al Central");
	     System.out.println("3. AL West");
	     System.out.println("4. NL East");
	     System.out.println("5. NL Central");
	     System.out.println("6. NL West");
	     System.out.println("7. Overall");
	     System.out.println("8. Exit");
	     
	     System.out.print("Enter your choice: ");
	     int option = sc.nextInt();
	     
	     // Return option
	     return option;
	 }
 
	 // Function to display team stats for user menu options 1-6
	 public static void options1to6(ArrayList<String> standings){
		 
		 // Declare variables
	     String[] lineSection;
	     double average;
	     double gamesBehind;
	     
	     // Display Heading of table
	     System.out.println("Teams:     Wins:      Loses:     Pct:     Behind:" );
	     System.out.println("-----------------------------------------------------");
	     for(String standing : standings){
	         lineSection = standing.split("\t");
	         average = calcAverage(standing);
	         gamesBehind = calcGamesBehind(standings);
	         
	         // Display line in table format
	         System.out.printf("%-12s%-10s%-11s%-7.3f%8.2f \n", lineSection[0], lineSection[1], 
	        		 lineSection[2], average, gamesBehind);
	        
	     }     
	 }
 
	 // Function to display Option 7 stats 
	 public static void option7(ArrayList<String> standings){
		 
		 	 // Create String array to store line sections
	         String[] lineSection;
	         
	         // Display Heading of table
	         System.out.println("Teams:    Wins:    Losses:  " );
	         System.out.println("----------------------------");
	         
	         // For each line store section into array
	         for(String standing : standings){
	             lineSection = standing.split("\t");           
	             
	             // Display Team names and Pct stats in table format
	             System.out.printf("%-13s%-10s%-11s \n", lineSection[0], lineSection[1], lineSection[2]);    
	         }
	}

	// Function to sort by average wins for option 7
	public static void sortWin(ArrayList<String> teamStanding, String line1){
	  
	  // Set average to the calculated average of the first line
	    double average = calcAverage(line1);
	    
	  //REMOVE? double average1;
	  
	  // Set line position default to -1
	  int linePosition = -1;
	  
	  // Based on comparison with average to average1 change position of line
	  for(int i =0; i < teamStanding.size(); i++){
		  
		 // Calculate average of next line
	 	 double average1 = calcAverage(teamStanding.get(i));
	 	 
	 	 // Compare the averages
	      if(average > average1){
	    	  
	    	 // Move line position to i if the previous line average is higher
	     	 linePosition = i;
	          break;
	      }
	  }
	  
	  // Compare line position and add line to ArrayList
	  if(linePosition < 0){
	      teamStanding.add(line1);
	      
	  } else {
	 	 teamStanding.add(linePosition, line1);
	  }
	}
 
	 // Function to calculate the average given a string
	 public static double calcAverage(String line){
		 
		 // Store line sections into an array
	     String[] lineSection = line.split("\t");
	     
	     double wins = Integer.parseInt(lineSection[1]);
	     double losses = Integer.parseInt(lineSection[2]); 
	     
	     // Add line sections 1 and 2 to equal total games
	     double totalGames = wins + losses;
	     
	     // Divide wins by total games and set to average
	     double average = wins / totalGames;
	     
	     // return average
	     return average;
	 }
 
	 // GB between first place 
	 // and second place = (Difference in wins + Difference in losses) / 2
	 // Function to calculate games behind stats
	 public static double calcGamesBehind(ArrayList<String> standings) {
		 
		 // Create string array to store line sections
	     String [] lineSection;
	     
	     // Declare variables
	     //String leaderTeam;
	     double netWin = 0; 
	     double netLoss = 0;
	     double wins = 0;
	     double losses = 0;
	     double wins1 = 0;
	     double losses1 = 0;
	     double gamesBehind = 0;
	     
	     // Store wins and losses of current line
	     for(int i = 0; i < standings.size(); i++){
	    	 for (String standing : standings){
	    		 lineSection = standing.split("\t");
	    		 wins = Double.parseDouble(lineSection[1]);
	    		 losses = Double.parseDouble(lineSection[2]);
	    		 
	    	 }
	  	     // Store wins and losses of next line
		     for(int j = 1; i < standings.size(); i++){
		    	 for (String standing : standings){
		    		 lineSection = standing.split("\t");
		    		 wins1 = Double.parseDouble(lineSection[1]);
		    		 losses1 = Double.parseDouble(lineSection[2]);
		    		 
		    	     // Subtract in correct order to gain positive output
		    	     if (wins > wins1) {
		    	    	 netWin = (wins - wins1);
		    	     }
		    	     else {
		    	    	netWin = (wins1 - wins);
		    	     }
		    	     
		    	     if (losses > losses1) {
		    	    	 netLoss = (losses - losses1);
		    	     }
		    	     else {
		    	    	netLoss = (wins1 -wins);
		    	     }

		    	     gamesBehind = (netWin + netLoss)/2;
		    	 }
		     }
	     }
	     
	     return gamesBehind;
	     
	     
	 }
	 
	 // Calculate net win and loss
	 public static double calcWin(String line){
		 
		 // Store line sections into an array
	     String[] lineSection = line.split("\t");
	     
	     double wins = Integer.parseInt(lineSection[1]);
	     
	     // return average
	     return wins;
	 }
	 public static double calcLost(String line){
		 
		 // Store line sections into an array
	     String[] lineSection = line.split("\t");
	     
	     double loss = Integer.parseInt(lineSection[2]);
	     
	     // return average
	     return loss;
	 }


}