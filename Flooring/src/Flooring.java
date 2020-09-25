/**
 * @author arwae
 *
 */
// Import scanner utility
import java.util.Scanner;

// Create Flooring class
public class Flooring {

   public static void main(String[] args) {

	   // Set variables
       double totalHouseArea = 0;
       double width;
       double length;
       int boardArea = 180;
       int totalBoards = 0;
       int totalPackages = 0;
       double totalPackagesCost = 0.0;

       // Create scanner object
       Scanner scan = new Scanner(System.in);

       // Ask for user input of length and width of room
       System.out.print("Enter the length of the room (ft): ");
       length = scan.nextDouble();
       System.out.print("Enter the width of the room (ft): ");
       width = scan.nextDouble();

       // Calculate the total house area
       totalHouseArea = length * width * 12;
       
       // Add extra 20 percent
       totalHouseArea = (int) (totalHouseArea + (totalHouseArea * 0.20));
       
       // Calculate total number of boards
       totalBoards = (int)Math.ceil((totalHouseArea / boardArea));
       
       // Calculate total number of packages
       totalPackages = totalBoards / 6;

       totalPackagesCost = costOfPackages(totalPackages);
      
       // Print the number of packages one must buy and total cost
       System.out.println("Buy " + totalPackages+ " packages of laminate boards.");
       System.out.println("Total cost of packages: $"+ (totalPackagesCost));

   }

   private static double costOfPackages(int totalPackages) {
       double packageCost = 24.99;
       
       // Multiply one package cost to total number of packages to gain total cost of packages
       return totalPackages * packageCost;
   }

}
