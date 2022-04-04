import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Teachers extends User {
    private String course;
    
    public Teachers(String username, String password, String course) {
        super(username, password);
        this.course = course;
    }

    // add quiz. needs the quiz name, needs a quiz object to get to the questions
    // we can make this where it makes one question on a quiz and keeps getting asked or,
    // it makes the whole quiz\
  
  //questions:
  //how does course work with quizzes and questions, what do i need from the user for creating the class, removing the class, and modifying the class.

    //be able to add quizes to a course the teach can only acces it
    public String addQuiz(String quizName, String[] quizObject) {
        if (!quizName.equalsIgnoreCase(course.getQuizzes().getname())) {
            course.getQuizzes.setquizName(quizName);
            course.getQuizzes.setQuestions(quizObject);
            return "Quiz Added";
        } else {
            return "Error, Quiz already created";
        }
    }
    public String modifyQuiz(String quizName, String newQuizName) {
        if (quizName.equalsIgnoreCase(course.getQuizzes().getname())) {
            course.getQuizzes().getname(newQuizName);
            //do they waant to add a question, modify a question, change a answer?
            course.getQuizzes().getquestion();
        }
        return "Error. No quiz in this course by that name";
    }
    public String removeQuiz() {
        if (quizName.equalsIgnoreCase(course.getQuizzes().getname())) {
            course.getQuizzes().getname("");
            course.getQuizzes().setquestion(null);
        }
        return "Error. No quiz in this course by that name";
    }
    // studentname = filename, need a String of what number quiz it is
    public String checkStudentQuiz(String fileName, String quizName) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)) {
            while ((line = br.readLine()) == null) {
                if (line.equals(quizName)) {
                    //print the amount of lines the quiz is
                    for (int i = 0; i < 8; i++) {
                        System.out.println(line = br.readLine());
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
