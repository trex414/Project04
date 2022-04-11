import java.util.*;
import java.io.*;

// GRAYSON GOT THIS
public class Application {
    public static String welcome = "Welcome to Management System!\n";
    public static String loginP = "Login Options:\n";
    public static String stuSignU = "2. Set up a Student account\n";
    public static String teaSignU = "1. Set up a Teacher account\n";
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
    public static String selOpt = "Select an option below:";
    public static String chOpt = "1. Add Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View and Grade Student Submissions\n5. Sign out";
    public static String error = "Error! Please enter one of the options!\n";
    public static String courseS = "1. Create course\n2. Join course\n";
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
            System.out.print(teaSignU);
            System.out.print(stuSignU);
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

            if (optAns == 2) {

                // Setting up students account
                User user = createAccount(sc, usernames, false);

                // adding user
                users.add(user);
                m.addUser(user);

                //System.out.println();
                //System.out.println(usernames.size());
                //System.out.println();

            } else if (optAns == 1) {

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
                    try {
                        t = (Teacher) m.getUser(userName, nPassword);
                    } catch (ClassCastException e) {
                        System.out.println("Students cannot log in as teachers");
                        return;
                    }
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
                            t.setCourses(courses);

                            // Joined a course
                        case 2:

                            // If they have not created
                            while (course == null) {

                                boolean courseExistence = false;
                                System.out.println(enterCourse);
                                courseName = sc.nextLine();

                                for (int i = 0; i < courses.size(); i++) {

                                    if (courses.get(i).getName().equals(courseName)) {
                                        course = courses.get(i);
                                        courseExistence = true;
                                        break;
                                    }
                                }

                                if (!courseExistence) {
                                    System.out.println("That course does not exist!");
                                }
                            }

                            // makes it so the whole teacher can do whatever they want in the course before leaving
                            int inCourseOp = 0;
                            do {
                                // Now we are in a course
                                // Options: 1 add quiz, 2 edit quiz, 3 delete quiz, 4 view submissions, 5 grade quizzes, 6 exit
                                System.out.println(selOpt);
                                System.out.println(chOpt);

                                inCourseOp = sc.nextInt();
                                sc.nextLine();

                                inCourseOp = verifyInput(sc, 1, 6, String.format("%s%s\n", selOpt, chOpt), inCourseOp);

                                switch (inCourseOp) {
                                    // Add a quiz
                                    case 1:
                                        ArrayList<String> questionsS = new ArrayList<>();
                                        ArrayList<String> options = new ArrayList<>();
                                        ArrayList<Integer> answers = new ArrayList<>();
                                        String quizName;
                                        int questionNum;
                                        int optionNum;
                                        int answerNum;
                                        while (true) {
                                            try {
                                                System.out.println("1. Import a file\n2. Type the quiz");
                                                int teacherQuiz = sc.nextInt();
                                                sc.nextLine();
                                                if (teacherQuiz == 1) {
                                                    System.out.println("What is the file name?");
                                                    String filename = sc.nextLine();
                                                    System.out.println(t.addFileQuiz(courseName, filename));
                                                } else if (teacherQuiz == 2) {

                                                    // ask for the name of the quiz
                                                    System.out.println("What would you like to name the quiz?");
                                                    quizName = sc.nextLine();
                                                    System.out.println("How many questions is the quiz?");
                                                    questionNum = sc.nextInt();
                                                    sc.nextLine();
                                                    // use the amount of questions they want to create that many questions
                                                    for (int i = 1; i <= questionNum; i++) {
                                                        StringBuilder optionsForQuestion = new StringBuilder();
                                                        System.out.println("What is your question " + i + "?");
                                                        String question = sc.nextLine();
                                                        System.out.println("How many options will you have?");
                                                        optionNum = sc.nextInt();
                                                        sc.nextLine();
                                                        // use the optionNum to get as many options they want
                                                        for (int j = 0; j < optionNum; j++) {
                                                            System.out.println("What is your option " + (j + 1) + "?");
                                                            String option = sc.nextLine();
                                                            optionsForQuestion.append(j + 1).append(". ").append(option).append("\n");

                                                        }

                                                        System.out.println("which one is the answer?\n" +
                                                                "please use the number of the option");
                                                        System.out.println(optionsForQuestion);
                                                        answerNum = sc.nextInt();
                                                        sc.nextLine();
                                                        questionsS.add(question);
                                                        options.add(optionsForQuestion.toString());
                                                        answers.add(answerNum - 1);
                                                    }
                                                    System.out.println(t.addQuiz(courseName, quizName, questionsS, options, answers));
                                                }
                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Error, this did not work.\nTry again.");
                                            }
                                        }
                                        break;
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
                                                    try {
                                                        System.out.println("What is the name of the quiz?");
                                                        String oldQuizName = sc.nextLine();
                                                        System.out.println("What is the new quiz name?");
                                                        String newQuizName = sc.nextLine();
                                                        System.out.println(t.modifyQuizName(courseName, oldQuizName, newQuizName));
                                                        break;
                                                    } catch (Exception e) {
                                                        System.out.println("Error, this did not work.");
                                                        break;
                                                    }
                                                    // Quiz question
                                                case 2:
                                                    String question;
                                                    try {
                                                        System.out.println("What is the name of the quiz?");
                                                        quizName = sc.nextLine();
                                                        System.out.println("What question do you want to change?");
                                                        question = sc.nextLine();
                                                        System.out.println("What is the new question?");
                                                        String newQuestion = sc.nextLine();
                                                        System.out.println(t.modifyQuizPrompt
                                                                (courseName, quizName, question, newQuestion));
                                                        break;
                                                    } catch (Exception e) {
                                                        System.out.println("Error, this did not work.");
                                                        break;
                                                    }
                                                    // Questions choices
                                                case 3:
                                                    try {
                                                        StringBuilder optionsForQuestion = new StringBuilder();
                                                        System.out.println("What is the name of the quiz?");
                                                        quizName = sc.nextLine();
                                                        System.out.println("What question do you want to change?");
                                                        question = sc.nextLine();
                                                        System.out.println("How many options will you have?");
                                                        optionNum = sc.nextInt();
                                                        // use the optionNum to get as many options they want
                                                        for (int j = 1; j <= optionNum; j++) {
                                                            System.out.println("What is your option " + j + "?");
                                                            String option = sc.nextLine();
                                                            optionsForQuestion.append(j).append(option).append("\n");

                                                        }
                                                        System.out.println("which one is the answer?\n " +
                                                                "please use the number of the option");
                                                        System.out.print(optionsForQuestion);
                                                        answerNum = sc.nextInt();
                                                        sc.nextLine();

                                                        System.out.println(t.modifyQuizChoices
                                                                (courseName, quizName, question, optionsForQuestion.toString(), answerNum));
                                                        break;
                                                    } catch (Exception e) {
                                                        System.out.println("Error, this did not work.");
                                                        break;
                                                    }
                                                    // exit
                                            }
                                        } while (editOption != 4);
                                        break;
                                    // Delete a quiz
                                    case 3:
                                        while (true) {
                                            try {
                                                System.out.println("What is the name of the quiz?");
                                                quizName = sc.nextLine();
                                                System.out.println(t.removeQuiz(courseName, quizName));
                                                break;
                                            } catch (Exception e) {
                                                System.out.println("Error, this did not work.\nTry again.");
                                            }
                                        }
                                        break;
                                    // View submissions
                                    case 4:

                                        Student s = null;
                                        Quiz q = null;

                                        boolean isUser = false;
                                        String user = "";
                                        while (!isUser) {

                                            System.out.println(enterUser);
                                            user = sc.nextLine();

                                            for (int i = 0; i < users.size(); i++) {

                                                if (users.get(i).getUsername().equals(user)) {
                                                    isUser = true;
                                                    s = (Student) users.get(i);
                                                }
                                            }

                                            if (!isUser) {
                                                System.out.println("Error! User does not exist!");
                                            }
                                        }

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

                                        // Now, we have the quiz name and the username
                                        ArrayList<String> responses = s.getQuizSubmission(q);

                                        ArrayList<Question> questions = q.getQuestions();

                                        for (int i = 0; i < responses.size(); i++) {
                                            System.out.println("Question " + (i + 1) + ": " + questions.get(i).getPrompt() + "\nChoices:");
                                            for (int j = 0; j < questions.get(i).getChoices().size(); j++) {
                                                System.out.println(questions.get(i).getChoice(j));
                                            }
                                            System.out.println("Student's response: " + responses.get(i).substring(responses.get(i).indexOf(" ")));
                                        }

                                        System.out.println("Would you like to grade this quiz?\n1. Yes\n2. No");
                                        int gradingOption;
                                        do {
                                            gradingOption = sc.nextInt();
                                            if (gradingOption == 1) {
                                                int[] grades = new int[responses.size() + 1];
                                                System.out.println("Enter the maximum possible score for this quiz.");
                                                int maxScore = sc.nextInt();
                                                for (int i = 0; i < responses.size(); i++) {
                                                    System.out.println("Enter a numerical score for question " + (i + 1) + ":");
                                                    grades[i] = sc.nextInt();
                                                }
                                                sc.nextLine();
                                                grades[responses.size()] = maxScore;
                                                System.out.println("Enter name of file to write scores of student for this quiz to: ");
                                                String fileName = sc.nextLine();
                                                File f = new File(fileName);
                                                try {
                                                    FileOutputStream fos = new FileOutputStream(f);
                                                    PrintWriter pw = new PrintWriter(fos);
                                                    for (int i = 0; i < grades.length; i++) {
                                                        pw.println(grades[i]);
                                                    }
                                                    pw.close();
                                                } catch (IOException e) {
                                                    System.out.println("Was not able to successfully write grades to a file.");
                                                }
                                            }
                                        } while (gradingOption < 1 || gradingOption > 2);
                                        break;

                                    case 5:
                                        //System.out.println(exitPrompt);
                                        break;
                                }
                            } while (inCourseOp != 5);
                    }


                } else if (rSelection == 2) {

                    Student s;

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
                    try {
                        s = (Student) m.getUser(userName, nPassword);
                    } catch (ClassCastException e) {
                        System.out.println("Teacher's cannot log in as Students");
                        return;
                    }

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

                        if (course == null) {
                            System.out.println("That course does not exist!");
                        }
                    }

                    int stuOp = 0;
                    do {
                        System.out.println(selOpt);
                        System.out.println(studentOp);

                        // Option 1 quiz, 2 view grades, 3 exit
                        stuOp = sc.nextInt();
                        sc.nextLine();

                        stuOp = verifyInput(sc, 1, 3, String.format("%s%s\n", selOpt, studentOp), stuOp);

                        Quiz q = null;
                        String quizName;
                        boolean isQuiz;

                        switch (stuOp) {

                            // Take a quiz
                            case 1:

                                // System.out.println(enterQuiz);
                                // quizName = sc.nextLine();

                                isQuiz = false;
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
                                break;
                            // View grades
                            case 2:
                                // System.out.println(enterQuiz);
                                // quizName = sc.nextLine();

                                isQuiz = false;
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

                                System.out.println("Enter name of file with your scores for this quiz: ");
                                String fileName = sc.nextLine();
                                int[] grades = new int[q.getQuestions().size()];
                                File f = new File(fileName);
                                try {
                                    BufferedReader bfr = new BufferedReader(new FileReader(f));
                                    for (int i = 0; i < grades.length; i++) {
                                        grades[i] = Integer.parseInt(bfr.readLine());
                                    }
                                    bfr.close();
                                } catch (Exception e) {
                                    System.out.println("Was not able to successfully read grades from a file.");
                                }
                                s.viewGrades(q, grades);
                                break;
                            // Exit
                            case 3:
                                break;
                        }
                    } while (stuOp != 3);
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

        return new Student(nFullName, userName, password, isTeacher);
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
