package quizTool;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Quizzer controller class to display questions
// & save and evaluate student answers
public class Quizzer {
	
	// Generate random number
	Random rnd = new Random();
	
	public static void printQs (ArrayList<Question> questions) {
		
		// Set correct
		int correct = 0;
		
		// Get number of questions
		Scanner sc = new Scanner(System.in);
		System.out.println("How many questions would you like? ");
		int numOfQ = sc.nextInt();
		
		// Set count as 0
		int count = 0;
		
		// Choose random questions to quiz
		ArrayList<Question> rndmQs = chooseRandomQ(questions, numOfQ);
		
		// Print only number of Qs specified by user
		while (count < numOfQ) {
			
		// Display each question object
			for (Question question : rndmQs) {
				
				// Print each question
				System.out.println(question.getQuestion());
				System.out.println("a. "+ question.getOption1());
				System.out.println("b. "+ question.getOption2());
				System.out.println("c. "+ question.getOption3());
				System.out.println("d. "+ question.getOption4());
				
				System.out.println("Type the letter of your choice: ");
				String optionSelected = sc.next();
				char selected = optionSelected.charAt(0);
				
				char answer = (question.getAnswer()).charAt(0);
				if (selected == answer) {
					//Increment correct
					correct++;
					System.out.println("That's correct!");
				} else {
					System.out.println(String.format("Sorry. The correct answer is %s.", question.getAnswer()));
				}
				count++;
			}
		}
		System.out.println("You answered "+correct+" correct out of "+numOfQ+" questons asked." );
	}
	
	// Create arraylist with random question objects
	public static ArrayList<Question> chooseRandomQ (ArrayList<Question> questions, int numOfQs) {
		ArrayList<Question> randomQs = new ArrayList<Question>();
		Random rnd = new Random();
		for(int i = 0; i < numOfQs; i++) {
			int r = rnd.nextInt(17);
			Question rq = questions.get(r);
			randomQs.add(rq);
		}
		return randomQs;
	}
	
}