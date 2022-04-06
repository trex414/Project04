import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    ArrayList<Question> questions;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Quiz() {
        this.questions = new ArrayList<Question>();
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
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