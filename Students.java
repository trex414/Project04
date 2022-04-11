import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;
import java.io.*;

public class Students extends User {

    // Holds the quizzes matched with the responses for that quiz
    private Hashtable<Quiz, ArrayList<String>> submissions = new Hashtable<>();

    public Students(String name, String username, String password, boolean teacherPermission) {
        super(name, username, password, false);
    }

    public Hashtable<Quiz, ArrayList<String>> getSubmissions() {
        return this.submissions;
    }

    public ArrayList<String> getQuizSubmission(Quiz q) {
        return submissions.get(q);
    }

    public Hashtable<Quiz, ArrayList<String>> takeQuiz(Quiz quiz, Scanner scan) {
        ArrayList<String> responses = new ArrayList<>();
        int responseOption = 0;
        int responseMethod;
        Question currentQuestion;
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            currentQuestion = quiz.getQuestions().get(i);
            System.out.println(currentQuestion.getPrompt());
            for (int j = 0; j < currentQuestion.getChoices().size(); j++) {
                System.out.println(currentQuestion.getChoice(j));
            }
            System.out.println("Would you like to answer this question by attaching a file?" + "\n1. Yes\n2. No");

            do {
                responseMethod = scan.nextInt();
                scan.nextLine();
                if (responseMethod == 1) {
                    System.out.println("Enter the name of the file which contains the option for your response: ");
                    String fileName = scan.nextLine();
                    File f = new File(fileName);
                    try {
                        BufferedReader bfr = new BufferedReader(new FileReader(f));
                        responseOption = Integer.parseInt(bfr.readLine());
                        break;
                    } catch (IOException e) {
                        System.out.println("That file doesn't exist!");
                    }
                } else if (responseMethod == 2) {
                    System.out.println("Enter the option for your response: ");
                    responseOption = scan.nextInt();
                } else {
                    System.out.println("Please enter a valid option number!");
                }
            } while (responseMethod < 1 || responseMethod > 2);

            responses.add(currentQuestion.getChoices().get(responseOption - 1));
        }
        while (true) {
            System.out.println("You have answered every question. Would you like to submit or redo a question?" +
                    "\n1. Submit\n2. Change an answer");
            int submitOption = scan.nextInt();
            if (submitOption == 1) {
                break;
            } else if (submitOption == 2) {
                System.out.println("Which question (1-" + quiz.getQuestions().size() + ") would you like to redo?");
                int questionToRedo = scan.nextInt();
                currentQuestion = quiz.getQuestions().get(questionToRedo - 1);
                System.out.println(currentQuestion.getPrompt());
                for (int j = 0; j < currentQuestion.getChoices().size(); j++) {
                    System.out.println(currentQuestion.getChoice(j));
                }
                responseOption = scan.nextInt();
                responses.set((responseOption - 1), currentQuestion.getChoices().get(responseOption - 1));
            }
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Submitted!\n" + dateFormat.format(timestamp));
        quiz.setFinished(true);
        submissions.put(quiz, responses);

        return submissions;
    }

    public void viewGrades(Quiz quiz, int[] scores) {
        System.out.println("Grades for '" + quiz.getName() + "':\n");
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question currentQuestion = quiz.getQuestions().get(i);
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getPrompt());
            if (!getQuizSubmission(quiz).get(i).equals(currentQuestion.getAnswer())) {
                System.out.println("Your answer was incorrect! The correct answer was: " + currentQuestion.getAnswer());
            } else {
                System.out.println("Correct!");
            }
            System.out.println("Points Earned: " + scores[i] + "\n");
        }
        int scoreObtained = 0;
        for (int i = 0; i < scores.length - 1; i++) {
            scoreObtained += scores[i];
        }
        System.out.println("Final Score: " + scoreObtained + " / " + scores[scores.length - 1]);
    }
}
