/**
 * 
 */
package quizTool;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * @author arwae
 *
 */
// quizApp class uses main function to run application
public class QuizApp {

	/**
	 * @param args
	 */
	
	// QuestionPrinter controller class to print Quiz questions and answers
	public static class QuestionPrinter {
		public static void printQuestions(ArrayList<Question> questions) {
			for (Question question : questions) {
				System.out.println(question);
			}
		}
	}
	
	// static added
	// Display Header and get json file 
	public static String displayIntroandGetFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*          What could possibly be more fun than this?           *");
		System.out.println("*****************************************************************");
		System.out.println("*               OOP Theory and Concept Questions                *");
		System.out.println("*          Nothing. Nothing at all. Nope. Nada. Nunca.          *\n");
		System.out.print("Enter name of file containing questions: ");
		String path = sc.next();
		sc.nextLine();
		return path;
	}
	
	// Display menu and get user choice
	public static int displayMenuAndGetChoice(Scanner sc) {
		System.out.println("Here are your choices: ");
		System.out.println("1. Take a quiz.");
		System.out.println("2. See questions and answers");
		System.out.println("3. Exit");
		System.out.println("Enter the number of your choice: ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}
	
	// Main method displays header, and user options 
	public static void main(String[] args) {
		ArrayList<Question> questions = new ArrayList<Question>();
		Scanner sc = new Scanner(System.in);
		int userChoice;
		String path;
		Question question;
		QuestionReader qr = new QuestionReader();
		//QuestionReader qr = null;
		
		// Display header and store file path
		path = displayIntroandGetFile();
		System.out.println(path);
		
		// Read file and store in questions ArrayList
		questions = qr.readFromJSON(path);
		if (questions == null) {
			System.out.println("Couldn't read the questions.");
			System.exit(1);
		} else {
			System.out.println("Read questions.");
		}
		
		do {
			// Display Menu and get user choice
			userChoice = displayMenuAndGetChoice(sc);
			// Have user take quiz if user choice is 1
			if (userChoice == 1) {
				Quizzer.printQs(questions);
			} 
			// Display questions and answers if user choice is 2
			else if (userChoice == 2)  {
				System.out.println("Here are the answers:");
				QuestionPrinter.printQuestions(questions);
			} 
			
			// quit quiz tool if user choice is 3
			} while (userChoice != 3) ;
		System.out.println("*****************************************************************");
		System.out.println("*               Thank you for taking CPSC 24500                 *");
		System.out.println("*****************************************************************");
		System.exit(0);
	}

}
