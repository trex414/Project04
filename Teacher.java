import java.io.*;
import java.util.*;

public class Teacher extends User {
    private ArrayList<Course> courses = new ArrayList<Course>();

    public Teacher(String name, String username, String password, boolean teacherPermission) {
        super(name, username, password, true);
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
    
    public String  addFileQuiz (String course,String fileName) {
        // lines will go
        // 1 quiz name, 2 first question, 3 qestion options, 4 answer line , 5 the answer, then continues
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            for (int k = 0; k < courses.size(); k++) {
                if (course.equalsIgnoreCase(courses.get(k).getName())) {
                    int i = 0;
                    String line = "";
                    ArrayList<Question> questions = new ArrayList<>();
                    line = br.readLine();
                    String quizName = line;
                    while ((line = br.readLine()) != null) {
                        ArrayList<String> options = new ArrayList<>();
                        String question = line;
                        while (!(line = br.readLine()).equalsIgnoreCase("Answer")) {
                            options.add(line);
                        }
                        line = br.readLine();
                        int answer = Integer.parseInt(line);
                        questions.add(i, new Question(question, options.get(answer), options));
                        i++;
                    }
                    courses.get(k).addQuiz(new Quiz(quizName, questions));
                    return "it worked";
                }
            }
            
        } catch (Exception e) {
            return "Error, no file found.";
        }
        return "it did not work";
    }
    
    // adds questions to a quiz and then adds that quiz to a course
    public String addQuiz(String course, String quizName, ArrayList<String> questionsS, ArrayList<String> options,
                          ArrayList<Integer> answers) {
        ArrayList<Question> questions = new ArrayList<>();
        for (int k = 0; k < courses.size(); k++) {
            if (course.equalsIgnoreCase(courses.get(k).getName())) {
                for (int i = 0; i < questionsS.size(); i++) {
                    ArrayList<String> optionsS = new ArrayList<>();
                    // splits the string of options in the arrayList
                    String[] placement = (options.get(i).split("\n"));
                    // adds each one into a String[]
                    optionsS.addAll(Arrays.asList(placement));
                    questions.add(i, new Question(questionsS.get(i), optionsS.get(answers.get(k)), optionsS));
                }
                courses.get(k).addQuiz(new Quiz(quizName, questions));
                return "it worked";
            }
        }
        return "it did not work";
    }
    // modification 1 for quizzes
    public String modifyQuizName(String course, String quizName, String newQuizName) {
        for (Course cours : courses)
            if (course.equalsIgnoreCase(cours.getName())) {
                for (int j = 0; j < cours.getQuizzes().size(); j++) {
                    if (quizName.equalsIgnoreCase(cours.getQuizzes().get(j).getName())) {
                            cours.getQuizzes().get(j).setName(newQuizName);
                            return "The old name was: " + quizName + "\n The new name is: " +
                                    cours.getQuizzes().get(j).getName();
                    }
                }
            }
        return "Error. No quiz in this course by that name.";
    }
    // modification 2 for quizzes
    public String modifyQuizPrompt(String course, String quizName, String question, String newprompt) {
        // check for the course
        for (Course cours : courses)
            if (course.equalsIgnoreCase(cours.getName())) {
                // check for the quiz
                for (int j = 0; j < cours.getQuizzes().size(); j++) {
                    if (quizName.equalsIgnoreCase(cours.getQuizzes().get(j).getName())) {
                        // check for the question
                        for (int k = 0; k < cours.getQuizzes().get(j).getQuestions().size(); k++) {
                            if (question.equalsIgnoreCase
                                    (cours.getQuizzes().get(j).getQuestions().get(k).getPrompt())) {
                                cours.getQuizzes().get(j).getQuestions().get(k).setPrompt(newprompt);
                                return "The old question was: " + question + "\n The new question is: " +
                                        cours.getQuizzes().get(j).getQuestions().get(k).getPrompt();
                            }
                        }
                    }
                }
            }
        return "Error. No quiz question in this course by that name";
    }
    // modification 3 for quizzes
    public String modifyQuizChoices(String course, String quizName,String question, String choices, int answer) {

        for (Course cours : courses) {
            if (course.equalsIgnoreCase(cours.getName())) {
                // check for the quiz
                for (int j = 0; j < cours.getQuizzes().size(); j++) {
                    if (quizName.equalsIgnoreCase(cours.getQuizzes().get(j).getName())) {
                        // check for the question
                        for (int k = 0; k < cours.getQuizzes().get(j).getQuestions().size(); k++) {
                            ArrayList<String> optionsS = new ArrayList<>();
                            if (question.equalsIgnoreCase
                                    (cours.getQuizzes().get(j).getQuestions().get(k).getPrompt())) {
                                String[] placement = (choices.split("\n"));
                                // adds each one into a String[]
                                optionsS.addAll(Arrays.asList(placement));
                                cours.getQuizzes().get(j).getQuestions().get(k).setChoices(optionsS);
                                modifyQuizAnswer(course, quizName, question, answer);
                                return "The new options are: " +
                                        cours.getQuizzes().get(j).getQuestions().get(k).getChoices();
                            }
                        }
                    }
                }
            }
        }
        return "Error. No quiz in this course by that name";
    }
    // modification 4 for quizzes
    public void modifyQuizAnswer(String course, String quizName,String question, int answer) {
        for (Course cours : courses) {
            if (course.equalsIgnoreCase(cours.getName())) {
                // check for the quiz
                for (int j = 0; j < cours.getQuizzes().size(); j++) {
                    if (quizName.equalsIgnoreCase(cours.getQuizzes().get(j).getName())) {
                        // check for the question
                        for (int k = 0; k < cours.getQuizzes().get(j).getQuestions().size(); k++) {
                            if (question.equalsIgnoreCase
                                    (cours.getQuizzes().get(j).getQuestions().get(k).getPrompt())) {
                                cours.getQuizzes().get(j).getQuestions().get(k).setAnswer
                                        (cours.getQuizzes().get(j).getQuestions().get(k).getChoices().get(answer));
                            }
                        }
                    }
                }
            }
        }
    }
    // removes quiz from course
    public String removeQuiz(String course, String quizName) {
        for (Course cours : courses) {
            if (course.equalsIgnoreCase(cours.getName())) {
                for (int j = 0; j < cours.getQuizzes().size(); j++) {
                    if (quizName.equalsIgnoreCase(cours.getQuizzes().get(j).getName())) {
                        cours.removeQuiz(cours.getQuizzes().get(j));
                        return "Removed Quiz: " + quizName;
                    }
                }
            }
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
     * @return
     */

    // An attempt made by Aakar for a grading function before realizing he could just do this in the application
    /*
    public int[] gradeQuiz(Student student, Quiz quiz, Scanner scan) {
        ArrayList<String> studentResponse = student.getQuizSubmission(quiz);
        int[] scores = new int[studentResponse.size()];
        for (int i = 0; i < studentResponse.size(); i++) {
            Question question = quiz.getQuestions().get(i);
            System.out.println("Question: " + question.getPrompt() + "\nAnswer: " + studentResponse.get(i) +
                    "\nEnter the score for this answer.");
            int score = scan.nextInt();
            scores[i] = score;
        }
        return scores;
    }

     */

    // this will send each question to the main to grade
    public ArrayList<String> PrintQuestionQuiz(String fileName, String quizName, String questionNum) {
        // calls the quiz needed version
        ArrayList<String> student = checkStudentQuiz(fileName, quizName);
        ArrayList<String> question = new ArrayList<>();
        int questionNextInt = Integer.parseInt(questionNum);
        String questionNextString = "" + (questionNextInt + 1);
        //will loop through the whole quiz for the question
        for (int i = 0; i < student.size(); i++) {
            // checks to see if the question is starting in the right spot
            while (student.get(i).equalsIgnoreCase(questionNum)) {
                i++;
            }
            // checks to see if the next question is next
            if (!student.get(i).equalsIgnoreCase(questionNextString)) {
                break;
            } else {
                // adds all the lines for the question to a arraylist
                question.add(student.get(i));
            }
        }
        return question;
    }

    // with the grades for each question this will print the graded quiz to a file
    public String getQuizResult(String fileName, String quizName, String writingFile, ArrayList<Integer> grades,
                            String student) {
        try {
            // starts with the first question and make a verable to add for a final grade
            int questionNextInt = 1;
            int finalgrade = 0;
            // call for the whole quiz ungraded
            ArrayList<String> student1 = checkStudentQuiz(fileName, quizName);
            // make a file with the name given
            FileWriter fw1 = new FileWriter(writingFile, true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw1 = new PrintWriter(bw1);
            // start writing the graded quiz
            pw1.println(student + "\nGraded Quiz: " + quizName);
            // make a for loop to look through the whole quiz
            for (int i = 0; i < student1.size(); i++) {
                String questionNextString = "" + (questionNextInt + 1);
                if (!student1.get(i).equalsIgnoreCase(questionNextString)) {
                    pw1.println("the grade for that question is: " + grades.get(i));
                    questionNextInt++;
                } else {
                    pw1.println(student1.get(i));
                }
                finalgrade = finalgrade + grades.get(i);
            }
            pw1.println("The total grade is: " + (finalgrade/grades.size()));
            fw1.close();
            bw1.close();
            fw1.close();
            return "The quiz is finished being graded";
        } catch (IOException e) {
            e.printStackTrace();
            return "The quiz did not get graded";
        }
    }
}
