import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Timestamp;

public class Students extends User {

    public Students(String name, String username, String password, boolean teacherPermission) {
        super(name, username, password, false);
    }

    public ArrayList<String> takeQuiz(Quiz quiz, Scanner scan) {
        ArrayList<String> responses;
        for (int i = 0; i < quiz.getQuestions.size(); i++) {
            Question currentQuestion = quiz.getQuestions.get(i);
            System.out.println(currentQuestion.getPrompt());
            for (int j = 0: j < currentQuestion.getChoices().size(); j++) {
                System.out.println(currentQuestion.getChoice(j));
            }
            int responseOption = scan.nextInt();
            responses.add(currentQuestion.getChoices().get(responseOption));
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Submitted!\n" + dateFormat.format(timestamp));
        return responses;
    }


}
