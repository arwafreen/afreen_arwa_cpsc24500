package quizTool;

//Question model class to get, set, and create Question objects
	public class Question {
		private String question;
		private String answer;
		private String option1;
		private String option2;
		private String option3;
		private String option4;	
		
		// Question
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question = question;
		}
		// Options
		public String getOption1() {
			return option1;
		}
		public void setOption1(String option1) {
			this.option1 = option1;
		}
		public String getOption2() {
			return option2;
		}
		public void setOption2(String option2) {
			this.option2 = option2;
		}
		public String getOption3() {
			return option3;
		}
		public void setOption3(String option3) {
			this.option3 = option3;
		}
		public String getOption4() {
			return option4;
		}
		public void setOption4(String option4) {
			this.option4 = option4;
		} 
		// Answer
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		
		// Question constructor
		public Question() {
			question = "none";
			option1 = "none";
			option2 = "none";
			option3 = "none";
			option4 = "none";
			answer = "none";
			
		}
		public Question(String question, String option1, String option2, 
				String option3, String option4, String answer) {
			setOption1(option1);
			setOption2(option2);
			setOption3(option3);
			setOption4(option4);
			setQuestion(question);
			setAnswer(answer);
		}
		@Override
		public String toString() {
			return String.format("%s %s", answer, question);
			//return String.format("%s\n %s\n %s\n %s\n %s\n %", question, option1, option2, option3, option4, answer);
		}
		/*
		public String toStringAnswer() {
			return String.format("%s %s", answer, question);
		}
		*/
	}