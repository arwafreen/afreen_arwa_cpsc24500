/**
 * Arwa Afreen
 * 10/18/2020
 * CPSC 24500
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;
// C:\Users\arwae\OneDrive\Desktop\countries_deaths.txt

/**
 * @author arwae
 *
 */
public class CovidHomework {
	
	// Create HashMap method
	public static LinkedHashMap<String,double[]> readData(Scanner fsc) {
	        LinkedHashMap<String,double[]> covidDeaths = new LinkedHashMap<String,double[]>();
	        
	        // Contain header
	        fsc.nextLine();
	        
	        String[] parts;
	        String line; 
	        double[] deaths; 
	        String country;
	        
	        while (fsc.hasNextLine()) {
	        	
	        	// Read next line
	            line = fsc.nextLine();
	            
	            // Split into parts using tab
	            parts = line.split("\t");
	            country = parts[0];
	           
	            // Store death counts
	            deaths = new double[parts.length-1];
	            for (int i = 1; i < parts.length; i++) {
	                deaths[i-1] = Double.parseDouble(parts[i]);
	            }
	            
	            // Put death counts on map
	            covidDeaths.put(country,deaths);
	        }
	        return covidDeaths;
	    }
	
	// Method to display the frame for Daily Deaths
    public static void dailyDeathFrame(Plot2DPanel plot) {
        JFrame frm = new JFrame();
        frm.setTitle("Daily Deaths");
        frm.setBounds(100,100,500,500);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frm.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(plot,BorderLayout.CENTER);
        frm.setVisible(true);
    }
    
    // Method to display the frame for Cumulative Deaths
    public static void cumulativeDeathFrame(Plot2DPanel plot) {
        JFrame frm = new JFrame();
        frm.setTitle("Cumulative Deaths");
        frm.setBounds(100,100,500,500);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frm.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(plot,BorderLayout.CENTER);
        frm.setVisible(true);
    }
    
    // Get total days function
    public static double[] getDays(int howMany) {
        double[] result = new double[howMany];
        for (int i = 0; i < howMany; i++) {
            result[i] = i;
        }
        return result;
    }
    
    // Main function
	public static void main(String[] args) {
	    	
		    // Display title text
		    System.out.println("**********************************************");
		    System.out.println("*   INTERNATIONAL COVID-19 MORTALITY RATES   *" );
		    System.out.println("**********************************************"); 
		    
		    // Create Scanner Object
		    Scanner sc = new Scanner(System.in);
		    
		    // Ask user to input file name
		    System.out.println("Enter name of data file: ");
		    String file1 = sc.nextLine();
		    
	        LinkedHashMap<String,double[]> countryData = null;
	        String countries;
	        String[] parts;
	        double[] data; 
	        
	        try {	            
	            // Create scanner object to open file object and read lines
		        Scanner sc2 = new Scanner(new File(file1));
		        countryData = readData(sc2);
	            sc2.close();
	            
	        } catch (Exception ex) {
	        	countryData = null;
	        }
	        if (countryData == null) {
	            System.out.println("File does not exist.");
	        } else {
	        	
	            // Once data stored ask user for countries to display plot graph of data
	            do {
	            	System.out.println("Enter countries to plot, separated by commas (or quit to end): ");
	            	countries = sc.nextLine();
	            	
	                if (!countries.equalsIgnoreCase("quit")) {
	                	
	                	// Create Plot2DPanel object called plot
	                    Plot2DPanel plot = new Plot2DPanel();
	                    
	                    // Label axis and add legend
	                    plot.setAxisLabels("Day","Deaths");
	                    plot.addLegend("south");
	                    
	                    // Split country names using comma
	                    parts = countries.split(",");
	                    
	                    // Add line plots for each part, which is a country name
	                    for (String part : parts) {
	                    	
	                    	// Remove spaces
	                        part = part.trim(); 
	                        
	                        // Display country not found if not on Hash Map
	                        if (countryData.containsKey(part) == false) {
	                            System.out.printf("%s was not found.\n",part);
	                        } else {
	                        	// Get country name
	                            data = countryData.get(part);                           
	                            
	                            // Ask user which type of graph to present data
	    	                    System.out.println("[D]aily or [C]umulative?");
	    	                    String dailyOrCumulative = sc.nextLine();
	    	                    
	    	                    // Plot graph for user identified option
	    	                    if (dailyOrCumulative.equalsIgnoreCase("D")) {
	    	                    	
	    	                    	plot.addLinePlot(part,getDays(data.length),data);
	    	                    	dailyDeathFrame(plot);
	    	                    }
	    	                    else if (dailyOrCumulative.equalsIgnoreCase("C")) {
	    	                    	plot.addLinePlot(part,getDays(data.length),data);
	    	                    	cumulativeDeathFrame(plot);
	    	                    }
	                        }
	                    }         
	                    
	               }
	             
	              // Continue loop while user does not type quit
	            } while (!countries.equalsIgnoreCase("quit"));
	            
	            // When user quits display text
	            System.out.println("Your only task … ");
	        	System.out.println("               … is to wear a mask.");
	        }
	        sc.close();
	    }
}

