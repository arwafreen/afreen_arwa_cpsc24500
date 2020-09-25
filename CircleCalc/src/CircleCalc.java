
// Import random 
import java.util.Random;

// Create CircleCalc class
public class CircleCalc {
    public static void main(String[] args) {
    	
    	// Set radius as random number
        double radius;
        Random rand= new Random();
        
        // Calculate and print circumference and area
        radius = rand.nextDouble();
        System.out.println("Radius: "+radius);
        System.out.println("Area = " + Math.PI*radius*radius);
        System.out.println("Circumference = " + 2*Math.PI*radius);
    }
}