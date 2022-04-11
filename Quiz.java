import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    private String name;
    private ArrayList<Question> questions;
    boolean isFinished;
    boolean isGraded;

    public Quiz(String name, ArrayList<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Quiz() {
        this.name = "";
        this.questions = new ArrayList<Question>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public boolean getFinished() {
        return this.isFinished;
    }

    public boolean getGraded() {
        return this.isGraded;
    }

    // Allows user to get a question based on the prompt
    public Question getQuestion(String prompt) {

        Question q = null;

        for (int i = 0; i < questions.size(); i++) {
            if (prompt.equals(questions.get(i).getPrompt())) {
                q = questions.get(i);
                break;
            }
        }

        return q;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setGraded(boolean graded) {
        this.isGraded = graded;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void removeQuestion(Question q) {
        questions.remove(q);
    }

    // Randomize the list of questions
    public void randomizeQuestions() {
        Collections.shuffle(questions);
    }
}
