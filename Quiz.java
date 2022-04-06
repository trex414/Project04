import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    private String title;
    private ArrayList<Question> questions;

    public Quiz(String title, ArrayList<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public Quiz() {
        this.title = "";
        this.questions = new ArrayList<Question>();
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
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