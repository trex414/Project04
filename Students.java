import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.sql.Timestamp;

public class Students extends User {

    // Holds the quizzes matched with the responses for that quiz
    Hashtable<Quiz, ArrayList<String>> submissions = new Hashtable<Quiz, ArrayList<String>>();

    public Students(String name, String username, String password, boolean teacherPermission) {
        super(name, username, password, false);
    }

    public Hashtable<Quiz, ArrayList<String>> getSubmissions() {
        return this.submissions;
    }

    public ArrayList<String> getQuizSubmission(Quiz q) {
        return submissions.get(q);
    }

    public ArrayList<String> takeQuiz(Quiz quiz, Scanner scan) {
        ArrayList<String> responses;
        int responseOption;
        Question currentQuestion;
        for (int i = 0; i < quiz.getQuestions.size(); i++) {
            currentQuestion = quiz.getQuestions.get(i);
            System.out.println(currentQuestion.getPrompt());
            for (int j = 0; j < currentQuestion.getChoices().size(); j++) {
                System.out.println(currentQuestion.getChoice(j));
            }
            responseOption = scan.nextInt();
            responses.add(currentQuestion.getChoices().get(responseOption - 1));
        }
        while (true) {
            System.out.println("You have answered every question. Would you like to submit or redo a question?" +
                    "\n1. Submit\n2. Change an answer");
            int submitOption = scan.nextInt();
            if (submitOption == 1) {
                break;
            } else if (submitOption == 2) {
                System.out.println("Which question (1-" + quiz.getQuestions.size() + ") would you like to redo?");
                int questionToRedo = scan.nextInt();
                currentQuestion = quiz.getQuestions.get(questionToRedo - 1);
                System.out.println(currentQuestion.getPrompt());
                for (int j = 0: j < currentQuestion.getChoices().size(); j++) {
                    System.out.println(currentQuestion.getChoice(j));
                }
                responseOption = scan.nextInt();
                responses.set(currentQuestion.getChoices().get(responseOption - 1));
            }
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Submitted!\n" + dateFormat.format(timestamp));

        submissions.put(quiz, responses);

        return responses;
    }
}
