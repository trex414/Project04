import java.util.ArrayList;
import java.util.Scanner;

// TREY GOT THIS
public class Application {
    public static String welcome = "Welcome to Management System!";
    public static String loginP = "Login Options:\n";
    public static String stuSignU = "1. Set up a Student account\n";
    public static String teaSignU = "2. Set up a Teacher account\n";
    public static String logIn = "3. Log-In\n";
    public static String exit = "4. Exit";
    public static String exitPrompt = "Bye!";
    public static String role = "Teacher account or Student Account";
    public static String roleSelection = "1. Teacher Account\n2. Student Account";
    public static String username = "Enter Username:";
    public static String password = "Enter Password:";
    public static String verifyAcc = "Either your Username or Password is incorrect!";
    public static String tryL = "Try Again!";
    public static String yN = "1. Yes\n2. No";
    public static String logSuccess = "Login is Successful!";
    public static String verifUser = "Double check if your login to different role!";
    public static String chooseUser = "Type in your username:";
    public static String choosePass = "Type in your password:";
    public static String entFname = "Enter your Full Name:";
    public static String signUpSucc = "You are Signed-Up Successfully!";
    public static String existUser = "This is an existing Username!";
    public static String selOpt = "Select an option below:\n";
    public static String chOpt = "1. Add Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Student Submissions\n5. View Graded Quizes\n 6. Exit";
    public static String error = "Error! Please enter one of the options!\n";
    public static String courseS = "1. Create course/n2. Join course";
    public static String enterCourse = "Enter the name of the course: ";
    public static String enterUser = "Enter the student's username";
    public static String enterQuiz = "Enter the quiz name";
    public static String studentOp = "1. Take quiz\n2. View grades\n3. Exit";
    // Take a quiz, view grades, exit

    public static void main(String[] args) {

        // Create scanner to read input from user
        Scanner sc = new Scanner(System.in);

        // Hold usernames
        ArrayList<String> usernames = new ArrayList<String>();

        // Hold users
        ArrayList<User> users = new ArrayList<User>();

        ArrayList<Course> courses = new ArrayList<Course>();

        Manager m = new Manager(courses, users);

        while (true) {

            // Welcome user, give user options to create a student account, 
            // create a teacher account, log in, or exit
            System.out.print(welcome);
            System.out.print(loginP);
            System.out.print(stuSignU);
            System.out.print(teaSignU);
            System.out.print(logIn);
            System.out.println(exit);

            // Store user's username and password
            String userName;
            String nPassword;

            int optAns = sc.nextInt();
            sc.nextLine();

            // Verify user input
            String prompt = String.format("%s\n%s\n%s\n%s\n%s\n", loginP, stuSignU, teaSignU, logIn, exit);
            optAns = verifyInput(sc, 1, 4, prompt, optAns);

            Course course = null;

            if (optAns == 1) {

                // Setting up students account
                User user = createAccount(sc, usernames, false);
                
                // adding user
                users.add(user);
                m.addUser(user);

                System.out.println();
                System.out.println(usernames.size());
                System.out.println();

            } else if (optAns == 2) {

                // setting up teachers account
                User user = createAccount(sc, usernames, true);

                // adding user
                users.add(user);

            } else if (optAns == 3) {

                // Login
                System.out.println(role);
                System.out.println(roleSelection);

                // Get selection: teacher, student
                int rSelection = sc.nextInt();
                sc.nextLine();

                // Verify input
                String roleS = String.format("%s\n%s\n", role, roleSelection);
                rSelection = verifyInput(sc, 1, 2, roleS, rSelection);

                //int didLogin = -1;
                boolean didLogIn = false;

                if (rSelection == 1) {

                    Teacher t;

                    //teacher account
                    do {

                        System.out.println(username);
                        userName = sc.nextLine();
                        System.out.println(password);
                        nPassword = sc.nextLine();

                        //didLogin = loginUser(sc, userName, nPassword, users);
                        didLogIn = m.isUser(userName, nPassword);

                        if (!didLogIn) {

                            //verify username and password (doesn't match)
                            System.out.println(verifyAcc);
                            System.out.println(tryL);
                        }

                    } while (!didLogIn);

                    t = (Teacher) m.getUser(userName, nPassword);

                    System.out.println(logSuccess);

                    int courseOp;

                    // after they login
                    System.out.println(selOpt);
                    System.out.printf(courseS);

                    courseOp = sc.nextInt();
                    sc.nextLine();

                    courseOp = verifyInput(sc, 1, 2, String.format("%s%s\n", selOpt, courseS), courseOp);

                    String courseName = "";
                    boolean isNewClass = true;

                    switch (courseOp) {
                        
                        // Create a course
                        case 1:

                            // Ensures that the class has unique name
                            while (true) {

                                System.out.println(enterCourse);
                                courseName = sc.nextLine();

                                for (int i = 0; i < courses.size(); i++) {

                                    if (courses.get(i).getName().equals(courseName)) {
                                        isNewClass = false;
                                    }
                                }

                                if (isNewClass) {
                                    break;
                                }

                                System.out.println("Error\n");
                            }

                            // Create new course 
                            course = new Course(courseName);
                            courses.add(course);

                        // Joined a course
                        case 2:

                            // If they have not created 
                            while (course == null) {

                                System.out.println(enterCourse);
                                courseName = sc.nextLine();

                                for (int i = 0; i < courses.size(); i++) {

                                    if (courses.get(i).getName().equals(courseName)) {
                                        course = courses.get(i);
                                        break;
                                    }
                                }

                                System.out.println("That course does not exist!");
                            }

                            // Now we are in a course
                            // Options: 1 add quiz, 2 edit quiz, 3 delete quiz, 4 view submissions, 5 grade quizzes, 6 exit
                            System.out.println(selOpt);
                            System.out.println(chOpt);
                            
                            int inCourseOp = sc.nextInt();
                            sc.nextLine();

                            inCourseOp = verifyInput(sc, 1, 6, String.format("%s%s\n", selOpt, chOpt), inCourseOp);

                            switch (inCourseOp) {

                                // Add a quiz
                                case 1:
                                    ArrayList<String> questionsS = new ArrayList<>();
                                    ArrayList<String> options = new ArrayList<>();
                                    ArrayList<Integer> answers = new ArrayList<>();
                                    String optionsForQuestion = "";
                                    // ask for the name of the quiz
                                    System.out.println("What would you like to name the quiz?");
                                    String quizName = sc.nextLine();
                                    System.out.println("How many questions is the quiz?");
                                    int questionNum = sc.nextInt();
                                    sc.nextLine();
                                    // use the amount of questions they want to create that many questions
                                    for (int i = 1; i <= questionNum; i++) {
                                        System.out.println("What is your question " + i + "?");
                                        String question = sc.nextLine();
                                        System.out.println("How many options will you have?");
                                        int optionNum = sc.nextInt();
                                        // use the optionNum to get as many options they want
                                        for (int j = 1; j <= optionNum; j++) {
                                            System.out.println("What is your option " + i + "?");
                                            String option = sc.nextLine();
                                            optionsForQuestion = optionsForQuestion + i + option + "\n";

                                        }
                                        System.out.println("which one is the answer?\n " +
                                                "please use the number of the option");
                                        System.out.println(optionsForQuestion);
                                        int answerNum = sc.nextInt();
                                        sc.nextLine();
                                        questionsS.add(question);
                                        options.add(optionsForQuestion);
                                        answers.add(answerNum);
                                    }
                                    System.out.println(t.addQuiz(courseName,quizName, questionsS, options, answers));
                                    // Edit a quiz
                                    
                                case 2:
                                    // all the options they can choose from in a do while loop
                                    int editOption;
                                    do {
                                        System.out.println("What would you like to edit?");
                                        System.out.println("""
                                                1. Quiz Name
                                                2. Quiz question
                                                3. Questions new choices
                                                4. Leave edit options""");
                                        editOption = sc.nextInt();
                                        sc.nextLine();
                                        switch (editOption) {
                                            // quiz name
                                            case 1:
                                                System.out.println("What is the name of the quiz?");
                                                String oldQuizName = sc.nextLine();
                                                System.out.println("What is the new quiz name?");
                                                String newQuizName = sc.nextLine();
                                                System.out.println(t.modifyQuizName(courseName, oldQuizName, newQuizName));
                                            // Quiz question
                                            case 2:
                                                System.out.println("What is the name of the quiz?");
                                                quizName = sc.nextLine();
                                                System.out.println("What question do you want to change?");
                                                String question = sc.nextLine();
                                                System.out.println("What is the new question?");
                                                String newQuestion = sc.nextLine();
                                                System.out.println(t.modifyQuizPrompt
                                                        (courseName, quizName, question, newQuestion));

                                            // Questions choices
                                            case 3:
                                                optionsForQuestion = "";
                                                System.out.println("What is the name of the quiz?");
                                                quizName = sc.nextLine();
                                                System.out.println("What question do you want to change?");
                                                question = sc.nextLine();
                                                System.out.println("How many options will you have?");
                                                int optionNum = sc.nextInt();
                                                // use the optionNum to get as many options they want
                                                for (int j = 1; j <= optionNum; j++) {
                                                    System.out.println("What is your option " + j + "?");
                                                    String option = sc.nextLine();
                                                    optionsForQuestion = optionsForQuestion + j + option + "\n";

                                                }
                                                System.out.println("which one is the answer?\n " +
                                                        "please use the number of the option");
                                                System.out.println(optionsForQuestion);
                                                int answerNum = sc.nextInt();
                                                sc.nextLine();

                                                System.out.println(t.modifyQuizChoices
                                                        (courseName, quizName, question, optionsForQuestion, answerNum));
                                                // exit
                                            case 4:
                                                break;
                                        }
                                    } while (editOption != 5);
                                    // Delete a quiz
                                case 3:
                                    System.out.println("What is the name of the quiz?");
                                    quizName = sc.nextLine();
                                    System.out.println(t.removeQuiz(courseName, quizName));
                                    // View submissions
                                case 4:

                                    Students s = null;
                                    Quiz q = null;

                                    boolean isUser = false;
                                    String user = "";
                                    while (!isUser) {

                                        System.out.println(enterUser);
                                        user = sc.nextLine();

                                        for (int i = 0; i < users.size(); i++) {

                                            if (users.get(i).getUsername().equals(user)) {
                                                isUser = true;
                                                s = (Students) users.get(i);
                                            }
                                        }

                                        if (!isUser) {
                                            System.out.println("Error! User does not exist!");
                                        }
                                    }

                                    quizName = "";
                                    boolean isQuiz = false;
                                    while (!isQuiz) {

                                        System.out.println(enterQuiz);
                                        user = sc.nextLine();

                                        for (int i = 0; i < course.getQuizzes().size(); i++) {

                                            if (course.getQuiz(i).getName().equals(quizName)) {
                                                isQuiz = true;
                                                q = course.getQuiz(i);
                                            }
                                        }

                                        if (!isQuiz) {
                                            System.out.println("Error! Quiz does not exist!");
                                        }
                                    }

                                    // Now, we have the quiz name and the username
                                    ArrayList<String> responses = s.getQuizSubmission(q);

                                    ArrayList<Question> questions = q.getQuestions();

                                    for (int i = 0; i < responses.size(); i++) {
                                        System.out.println(questions.get(i).getPrompt());
                                        for (int j = 0; j < questions.get(i).getChoices().size(); j++) {
                                            System.out.println(questions.get(i).getChoice(i));
                                        }
                                        System.out.println(responses.get(i));
                                    }


                                // Grade quizzes
                                case 5:
                                    // AAKAR GOT THIS
                                    
                                // Exit
                                case 6:

                                    System.out.println(exitPrompt);
                                    return;
                            }
                     }

                } else if (rSelection == 2) {

                    Students s;

                    //student account
                    do {

                        System.out.println(username);
                        userName = sc.nextLine();
                        System.out.println(password);
                        nPassword = sc.nextLine();

                        //didLogin = loginUser(sc, userName, nPassword, users);
                        didLogIn = m.isUser(userName, nPassword);

                        if (!didLogIn) {

                            //verify username and password (doesn't match)
                            System.out.println(verifyAcc);
                            System.out.println(tryL);
                        }

                    } while (!didLogIn);

                    System.out.println(logSuccess);

                    s = (Students) m.getUser(userName, nPassword);

                    String courseName = "";

                    course = null;
                    
                    // If they have not created 
                    while (course == null) {

                        System.out.println(enterCourse);
                        courseName = sc.nextLine();

                        for (int i = 0; i < courses.size(); i++) {

                            if (courses.get(i).getName().equals(courseName)) {
                                course = courses.get(i);
                                break;
                            }
                        }

                        System.out.println("That course does not exist!");
                    }

                    System.out.println(selOpt);
                    System.out.println(studentOp);

                    // Option 1 quiz, 2 view grades, 3 exit
                    int stuOp = sc.nextInt();
                    sc.nextLine();

                    stuOp = verifyInput(sc, 1, 3, String.format("%s%s\n", selOpt, studentOp), stuOp);

                    Quiz q = null;

                    switch (stuOp) {

                        // Take a quiz
                        case 1:

                            System.out.println(enterQuiz);
                            String quizName = sc.nextLine();

                            boolean isQuiz = false;
                            while (!isQuiz) {

                                System.out.println(enterQuiz);
                                quizName = sc.nextLine();

                                for (int i = 0; i < course.getQuizzes().size(); i++) {

                                    if (course.getQuiz(i).getName().equals(quizName)) {
                                        isQuiz = true;
                                        q = course.getQuiz(i);
                                    }
                                }

                                if (!isQuiz) {
                                    System.out.println("Error! Quiz does not exist!");
                                }
                            }

                            s.takeQuiz(q, sc);

                        // View grades
                        case 2:
                        // Alex got this

                        // Exit
                        case 3:
                        // Carrie got this
                    }
                }
                // after they login

            } else {

                // Exit from the program
                System.out.println(exitPrompt);
                return;
            }
        }
    }

    public static int loginUser(Scanner sc, String username, String password, ArrayList<User> users) {

        int didLogin = -1;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                didLogin = i;
                break;
            }
        }

        return didLogin;
    }

    public static User createAccount(Scanner sc, ArrayList<String> usernames, boolean isTeacher) {

        String userName;
        String password;

        // Prompts user for full name
        System.out.println(entFname);
        String nFullName = sc.nextLine();

        // Ensure user adds a unique username
        while (true) {

            // Asks user for username
            System.out.println(chooseUser);
            userName = sc.nextLine();

            if (usernames.contains(userName)) {

                //existing username, deny and ask to try again.
                System.out.println(existUser);
                System.out.println(tryL);

            } else {
                break;
            }
        }

        // Get password from user
        System.out.println(choosePass);
        password = sc.nextLine();
        System.out.println(signUpSucc);

        usernames.add(userName);

        if (isTeacher) {
            return new Teacher(nFullName, userName, password, isTeacher);
        }

        return new Students(nFullName, userName, password, isTeacher);
    }

    public static int verifyInput(Scanner sc, int lowerB, int upperB, String prompt, int input) {

        while (input < lowerB || input > upperB) {

            System.out.println(error);
            System.out.println(tryL);

            input = sc.nextInt();
            sc.nextLine();
        }

        return input;
    }

    public static boolean isInList(Object o, ArrayList<Object> list) {

        boolean isIn = false;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) == o) {
                isIn = true;
            }
        }

        return isIn;
    }
}
