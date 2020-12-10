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

//QuestionReader controller class to read json file
public  class QuestionReader {
	public ArrayList<Question> readFromJSON(String fname) {
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			FileReader reader = new FileReader(new File(fname));
			JSONParser parser = new JSONParser();
			JSONObject all = (JSONObject)parser.parse(reader);
			JSONArray arr = (JSONArray)all.get("questions");
			Iterator itr = arr.iterator();
			/* itr will help us navigate through all the JSONObjects
			 * in the JSONArray arr. So, we will now use a while loop
			 * to do that. As we do, we will recover JSONObjects which
			 * we can convert to Article objects.
			 */
			JSONObject questionObject;
			String question, option1, option2, option3, option4, answer;
			while (itr.hasNext()) {
				questionObject = (JSONObject)itr.next();
				question = questionObject.get("question").toString();
				option1 = questionObject.get("a").toString();
				option2 = questionObject.get("b").toString();
				option3 = questionObject.get("c").toString();
				option4 = questionObject.get("d").toString();
				answer = questionObject.get("answer").toString();
				questions.add(new Question(question, option1, option2, option3, option4, answer));
			}
			reader.close();
			return questions;
		} catch (Exception ex) {
			return null;
		}
	}

}
