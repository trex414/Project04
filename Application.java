import java.util.ArrayList;
import java.util.Scanner;

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

    public static void main(String[] args) {

        // Create scanner to read input from user
        Scanner sc = new Scanner(System.in);

        // Hold usernames
        ArrayList<String> usernames = new ArrayList<String>();

        // Hold users
        ArrayList<User> users = new ArrayList<User>();

        ArrayList<Course> courses = new ArrayList<Course>();

        while (true) {


            // Welcome user, give user options to create a student account, 
            // create a teacher account, log in, or exit
            System.out.println(welcome);
            System.out.println(loginP);
            System.out.println(stuSignU);
            System.out.println(teaSignU);
            System.out.println(logIn);
            System.out.println(exit);

            // Store user's username and password
            String userName;
            String nPassword;

            int optAns = sc.nextInt();
            sc.nextLine();

            // Verify user input
            String prompt = String.format("%s\n%s\n%s\n%s\n%s\n", loginP, stuSignU, teaSignU, logIn, exit);
            optAns = verifyInput(sc, 1, 4, prompt, optAns);

            if (optAns == 1) {

                // Setting up students account
                User user = createAccount(sc, usernames, false);
                
                // adding user
                users.add(user);

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

                boolean didLogin = false;

                if (rSelection == 1) {

                    //teacher account
                    do {

                        System.out.println(username);
                        userName = sc.nextLine();
                        System.out.println(password);
                        nPassword = sc.nextLine();

                        didLogin = loginUser(sc, userName, nPassword, users);

                        if (!didLogin) {

                            //verify username and password (doesn't match)
                            System.out.println(verifyAcc);
                            System.out.println(tryL);
                        }

                    } while (!didLogin);

                    int courseOp;

                    // after they login
                    System.out.println(selOpt);
                    System.out.println(courseS);

                    courseOp = sc.nextInt();
                    sc.nextLine();

                    courseOp = verifyInput(sc, 1, 2, String.format("%s%s\n", selOpt, courseS), courseOp);

                    String courseName = "";
                    boolean isNewClass = true;
                    Course course = null;

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
                                    System.out.println();
                                    // Trey's got this

                                // Edit a quiz
                                case 2:
                                    // Trey's got this

                                // Delete a quiz
                                case 3:
                                    // Trey's got this

                                // View submissions
                                case 4:

                                    boolean isUser = false;
                                    String user = "";
                                    while (!isUser) {

                                        System.out.println(enterUser);
                                        user = sc.nextLine();

                                        for (int i = 0; i < users.size(); i++) {

                                            if (users.get(i).getUsername().equals(user)) {
                                                isUser = true;
                                            }
                                        }

                                        if (!isUser) {
                                            System.out.println("Error! User does not exist!");
                                        }
                                    }

                                    String quizName = "";
                                    boolean isQuiz = false;
                                    while (!isQuiz) {

                                        System.out.println(enterQuiz);
                                        user = sc.nextLine();

                                        for (int i = 0; i < course.getQuizzes().size(); i++) {

                                            if (course.getQuizzes().get(i).getName().equals(quizName)) {
                                                isQuiz = true;
                                            }
                                        }

                                        if (!isQuiz) {
                                            System.out.println("Error! Quiz does not exist!");
                                        }
                                    }

                                    // Now, we have the quiz name and the username
                                    

                                // Grade quizzes
                                case 5:
                                    

                                // Exit
                                case 6:
                            }
                     }

                } else if (rSelection == 2) {

                    //teacher account
                    do {

                        System.out.println(username);
                        userName = sc.nextLine();
                        System.out.println(password);
                        nPassword = sc.nextLine();

                        didLogin = loginUser(sc, userName, nPassword, users);

                        if (!didLogin) {

                            //verify username and password (doesn't match)
                            System.out.println(verifyAcc);
                            System.out.println(tryL);
                        }

                    } while (!didLogin);

                    //student account
                    // do {
                    //     System.out.println(username);
                    //     user = sc.nextLine();
                    //     System.out.println(password);
                    //     pass = sc.nextLine();
                    //     if () {
                    //         //verify username and password (doesn't match)
                    //         System.out.println(verifyAcc);
                    //         System.out.println(tryL);
                    //     } else if () {
                    //         //verify whether its stu account or teacher's account (not sure)
                    //         System.out.println(verifUser);
                    //         System.out.println(tryL);

                    //     } else {
                    //         // if everything works matchs
                    //         System.out.println(logSuccess);
                    //         // continue for selection add quiz/ edit / delete/ view sub / Exit
                    //         System.out.println(selOpt);
                    //         System.out.println(chOpt);
                    //         int optSel = sc.nextInt();
                    //         sc.nextLine();
                    //         if (optSel == 1) {
                    //             //add quiz...

                    //         } else if (optSel == 2) {
                    //             //edit...

                        //     } else if (optSel == 3) {
                        //         //delete...

                        //     } else if (optSel == 4) {
                        //         //view submissions...

                        //     } else {
                        //         //exit
                        //         System.out.println(exitPrompt);
                        //     }
                        // }

                }

                // after they login


            } else {

                // Exit from the program
                System.out.println(exitPrompt);
                return;
            }
        }
    }

    public static boolean loginUser(Scanner sc, String username, String password, ArrayList<User> users) {

        boolean didLogin = false;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                didLogin = true;
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
