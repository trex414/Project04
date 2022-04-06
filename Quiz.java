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