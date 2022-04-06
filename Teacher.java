import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Teachers extends User {
    private ArrayList<Course> courses = new ArrayList<Course>();
    public Teachers(String username, String password) {
        super(username, password, true);
    }

    /** do we want to make each teacher have all their quizzes?
     *  how do we want to get files printed for persistence? we could have a Writing function in each file
     *  we can have each quiz print into a quizzes file, students name file, each student with their quiz, same with teachers courses
     *  each course could write the quizzes int their course 
     */

    //be able to add quizzes to a course the teach can only acces it
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    // will add all the courses they are in
    public void addTeacherCourse(Course course) {
        courses.add(course);
    }
    // adds questions to a quiz and then adds that quiz to a course
    public String addQuiz(String course, String quizName, ArrayList<String> promt, ArrayList<String> answers, int answer) {
        ArrayList<Question> questions = new ArrayList<>();
        for (int k = 0; k < courses.size(); k++) {
                for (int i = 0; i < promt.size(); i++) {
                    questions.set(i, new Question(promt.get(i), answers.get(answer), answers));
                }
                if (course.equalsIgnoreCase(courses.get(k).getName())) {
                    courses.get(k).addQuiz(new Quiz(quizName, questions));
                }
                return "it worked";
            }
        return "it did not work";
    }
    // modification 1 for quizzes
    public String modifyQuizName(String course, int quizName, String newQuizName) {
        for (int i = 0; i < courses.size(); i++)
            if (course.equalsIgnoreCase(courses.get(i).getName())) {
                    courses.get(i).getQuiz(quizName).setName(newQuizName);
                
                return "Quiz Name updated";
            }
        return "Error. No quiz in this course by that name";
    }
    // modification 2 for quizzes
    public String modifyQuizPrompt(String course, int quizName, String prompt, String newprompt) {
        for (int i = 0; i < courses.size(); i++)
            if (course.equalsIgnoreCase(courses.get(i).getName())) {
                courses.get(i).getQuiz(quizName).getQuestion(prompt).setPrompt(newprompt);
                return "Question prompt updated";
            }
        return "Error. No quiz in this course by that name";
    }
    // modification 3 for quizzes
    public String modifyQuizChoices(String course, int quizName,String prompt, ArrayList<String> choices) {
        for (int i = 0; i < courses.size(); i++)
            if (course.equalsIgnoreCase(courses.get(i).getName())) {
                courses.get(i).getQuiz(quizName).getQuestion(prompt).setChoices(choices);
                return "Question choices updated";
            }
        return "Error. No quiz in this course by that name";
    }
    // modification 4 for quizzes
    public String modifyQuizAnswer(String course, int quizName,String prompt, String answer) {
        for (int i = 0; i < courses.size(); i++)
            if (course.equalsIgnoreCase(courses.get(i).getName())) {
                courses.get(i).getQuiz(quizName).getQuestion(prompt).setAnswer(answer);
                return "Question answer updated";
            }
        return "Error. No quiz in this course by that name";
    }
    // removes quiz from course
    public String removeQuiz(String course) {
        for (int i = 0; i < courses.size(); i++)
            if (course.equalsIgnoreCase(courses.get(i).getName())) {
                courses.get(i).removeQuiz(courses.get(i).getQuiz(i));
                return "Removed Quiz";
            }
        return "Error. No quiz in this course by that name";
    }
    // studentname = filename, need a String of what number quiz it is
    public ArrayList<String> checkStudentQuiz(String fileName, String quizName) {
        ArrayList<String> studentQuiz = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // will get the file and read it until the quiz is found.
            while(true) {
                if ((line = br.readLine()).equalsIgnoreCase(quizName)) {
                    break;
                }
            }
            // finds when the quiz ends and adds each line of the quiz to a arraylist
            while((line = br.readLine()).equalsIgnoreCase("end")) {
                studentQuiz.add(line);
            }
            // returns a arraylist that can then be printed to show someones quiz
            return studentQuiz;
            
        } catch (Exception e) {
            e.printStackTrace();
            studentQuiz.add("Did not work");
            return studentQuiz;
        }
    }
    // do quizzes get graded auto?
    /** public String gradeQuiz() {
     * we would need to have a list of completed quizzes or are we gradding each quiz per student(would rather do all)
     * or each student that finished a quiz so i can look through their file and then check their answers with the answer
     * i would then rewirte each file to get where they have the quiz and then under it put score, ones they got wrong,
     * ones they got correct
     * or add all the graded quizes to their own file for each quiz
     */
}
