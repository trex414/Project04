import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMethod {
    private static String welcome = "Hello, Welcome to quiz manager!\n" +
            "1. Sign up\n" +
            "2. Log in ";
    private static String quit = "log out";

    public static void main(String[] args) throws IOException {
        int choiceOne;
        int choiceTwo;
        String cUser = null;
        String cPassword = null;
        ArrayList<String> userInfo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        BufferedReader libReader = new BufferedReader(new FileReader("UserLibrary.txt"));
        PrintWriter libWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("UserLibrary.txt",true)));

        do {
            System.out.println(welcome);
            choiceOne = sc.nextInt();
            sc.nextLine();
            if (choiceOne == 1 || choiceOne == 2) {
                break;
            } else {
                System.out.println("Error, please enter 1 or 2.");
            }
        } while (true);

        switch (choiceOne) {
            case 1:
                do {
                    System.out.println("1.Teacher 2.Student");
                    choiceTwo = sc.nextInt();
                    sc.nextLine();
                    if( choiceTwo == 1 || choiceTwo == 2) {
                        break;
                    } else {
                        System.out.println("Error, please enter 1 or 2.");
                    }
                } while (true);
                int booleanA = 0;
                do {
                    System.out.println("Enter a username");
                    cUser = sc.nextLine();
                    System.out.println("Enter a password");
                    cPassword = sc.nextLine();
                    String info = libReader.readLine();
                    while (info != null) {
                        userInfo.add(info);
                        info = libReader.readLine();
                    }

                    for (int i = 0; i < userInfo.size(); i += 3) {
                        if (userInfo.get(i).equals(cUser)) {
                            System.out.println("Error, account exists.");
                            break;
                        } else {
                            booleanA = 1;
                        }
                    }

                    userInfo = null;
                } while (booleanA == 0);

                if (choiceTwo == 1) {
                    Teachers u = new Teachers(cUser,cPassword);
                    libWriter.print(u.toString());
                } else {
                    Students u = new Students(cUser,cPassword);
                    libWriter.print(u.toString());
                }
                System.out.println("Do you want to log in? \n 1.yes\n 2.no");
                int logInOrQuit = sc.nextInt();
                sc.nextLine();
                if (logInOrQuit == 2) {
                    libReader.close();
                    libWriter.close();
                    break;
                }


            case 2:
                do {
                    System.out.println("Enter the username.");
                    String username = sc.nextLine();
                    for (int i = 0; i < userInfo.size(); i += 3) {
                        if (userInfo.get(i).equals(username)) {
                            System.out.println("Enter the password.");
                            String password = sc.nextLine();
                            if (userInfo.get(i + 1).equals(password)) {
                                System.out.println("Success!");
                            } else {
                                System.out.println("Error! Invalid password.");
                            }

                        } else {
                            System.out.println("Error! Invalid username.");
                        }
                    }
                } while (true);




                break;
            default:
                break;
        }




    }
}
