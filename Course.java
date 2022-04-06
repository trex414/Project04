import java.util.ArrayList;

public class Course {

    String name;
    private ArrayList<Quiz> quizzes;

    // Possible parameters we could add:
    // private ArrayList<String> students; to hold the names of the students in the course
    // private int enrollment; to hold the number of students in the course

    public Course(String name, ArrayList<Quiz> quizzes) {
        this.name = name;
        this.quizzes = quizzes;
    }

    public Course(String name) {
        this.name = name;
        this.quizzes = new ArrayList<Quiz>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Quiz> getQuizzes() {
        return this.quizzes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz q) {
        quizzes.add(q);
    }

    public void removeQuiz(Quiz q) {
        quizzes.remove(q);
    }

    // Gets a quiz at index i
    public Quiz getQuiz(int i) {

        Quiz q = null;

        try {
            q = quizzes.get(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }

        return q;
    }
}
