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
    public static String chOpt = "1. Add Question\n2. Edit Quiz\n3. Delete Quiz\n4. View Student Submissions\n5. Exit";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println(welcome);
        System.out.println(loginP);
        System.out.println(stuSignU);
        System.out.println(teaSignU);
        System.out.println(logIn);
        System.out.println(exit);

        String user = "";
        String pass = "";

        int optAns = sc.nextInt();
        sc.nextLine();
        if (optAns == 1) {
            //Setting up students account
            String userName;
            System.out.println(entFname);
            String nFullName = sc.nextLine();
            do {
                System.out.println(chooseUser);
                userName = sc.nextLine();
                if () {
                    //existing username (deny and ask to try again.
                    System.out.println(existUser);
                    System.out.println(tryL);
                }

            } while ();
            //verify existing usernames.
            System.out.println(choosePass);
            String nPassword = sc.nextLine();
            System.out.println(signUpSucc);
            //
            User user = new User(nFullName, userName, nPassword);
            user.addUser();
            //adding user (need user interface)

        } else if (optAns == 2) {
            //setting up teachers account
            String userName;
            System.out.println(entFname);
            String nFullName = sc.nextLine();
            do {
                System.out.println(chooseUser);
                userName = sc.nextLine();
                if () {
                    //existing username (deny and ask to try again.
                    System.out.println(existUser);
                    System.out.println(tryL);
                }

            } while ();
            //verify existing usernames.
            System.out.println(choosePass);
            String nPassword = sc.nextLine();
            System.out.println(signUpSucc);
            //
            User user = new User(nFullName, userName, nPassword);
            user.addUser();
            // interface needed.

        } else if (optAns == 3) {
            // Login
            System.out.println(role);
            System.out.println(roleSelection);
            int rSelection = sc.nextInt();
            sc.nextLine();
            if (rSelection == 1) {
                //teacher account
                do {
                    System.out.println(username);
                    user = sc.nextLine();
                    System.out.println(password);
                    pass = sc.nextLine();
                    if () {
                        //verify username and password (doesn't match)
                        System.out.println(verifyAcc);
                        System.out.println(tryL);

                    } else if () {
                        // if logged in as a different role
                        System.out.println(verifUser);
                        System.out.println(tryL);

                    } else {
                        System.out.println(logSuccess);
                        // continue for selection add quiz/ edit / delete/ view sub / Exit
                        System.out.println(selOpt);
                        System.out.println(chOpt);
                        int optSel = sc.nextInt();
                        sc.nextLine();
                        if (optSel == 1) {
                            //add quiz...

                        } else if (optSel == 2) {
                            //edit...

                        } else if (optSel == 3) {
                            //delete...

                        } else if (optSel == 4) {
                            //view submissions...

                        } else {
                            //exit
                            System.out.println(exitPrompt);
                        }
                    }

                } while ()
                //verify username and password

            } else if (rSelection == 2) {
                //student account
                do {
                    System.out.println(username);
                    user = sc.nextLine();
                    System.out.println(password);
                    pass = sc.nextLine();
                    if () {
                        //verify username and password (doesn't match)
                        System.out.println(verifyAcc);
                        System.out.println(tryL);
                    } else if () {
                        //verify whether its stu account or teacher's account (not sure)
                        System.out.println(verifUser);
                        System.out.println(tryL);

                    } else {
                        // if everything works matchs
                        System.out.println(logSuccess);
                        // continue for selection add quiz/ edit / delete/ view sub / Exit
                        System.out.println(selOpt);
                        System.out.println(chOpt);
                        int optSel = sc.nextInt();
                        sc.nextLine();
                        if (optSel == 1) {
                            //add quiz...

                        } else if (optSel == 2) {
                            //edit...

                        } else if (optSel == 3) {
                            //delete...

                        } else if (optSel == 4) {
                            //view submissions...

                        } else {
                            //exit
                            System.out.println(exitPrompt);
                        }
                    }

                } while ()
                //verify username and password

            }

        } else if (optAns == 4) {
            // Exit from the program
            System.out.println(exitPrompt);
        }

    }
}
