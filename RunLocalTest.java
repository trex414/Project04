import org.junit.*;

import java.lang.reflect.Field;

import org.junit.runner.JUnitCore;
import org.junit.runner.OrderWith;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.MethodSorters;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.Assert.*;


public class RunLocalTest {
    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)

    public static class TestCase {
        private final InputStream originalInput = System.in;
        private final PrintStream originalOutput = System.out;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;


        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @Test(timeout = 1000)


        public void testTwo() {

            String input = "1\r\nq\r\nq\r\n1\r\n2\r\nw\r\nw\r\n2\r\n2\r\ne" +
                    "w\r\ne\r\n2\r\n4\r\n1\r\nq\r\n1\r\n1\r\na\r\na\r\n1\r\n" +
                    "3\r\n1\r\na\r\n1\r\n1\r\nBio\r\n1\r\n2\r\nquiz\r\n1\r\n1+4\r\n2\r\n" +
                    "5\r\n1\r\n1\r\n2\r\n1\r\nquiz\r\nquiz1\r\n4\r\n1\r\n2\r\nmath\r\n" +
                    "2\r\n1+2\r\n2\r\n3\r\n2\r\n1\r\n4+3\r\n2\r\n7\r\n2\r\n1\r\n3\r\nquiz1\r\n5\r\n5\r\n";

            receiveInput(input);

            try {
                Application.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Teacher deleted!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Select an option below:\n" +
                    "1. Create course\n" +
                    "2. Join course\n" +
                    "3. Delete course\n" +
                    "Enter the name of the course: \n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "1. Import a file\n" +
                    "2. Type the quiz\n" +
                    "What would you like to name the quiz?\n" +
                    "How many questions is the quiz?\n" +
                    "What is your question 1?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 5\n" +
                    "2. 1\n" +
                    "\n" +
                    "it worked\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "What would you like to edit?\n" +
                    "1. Quiz Name\n" +
                    "2. Quiz question\n" +
                    "3. Questions new choices\n" +
                    "4. Leave edit options\n" +
                    "What is the name of the quiz?\n" +
                    "What is the new quiz name?\n" +
                    "The old name was: quiz\n" +
                    " The new name is: quiz1\n" +
                    "What would you like to edit?\n" +
                    "1. Quiz Name\n" +
                    "2. Quiz question\n" +
                    "3. Questions new choices\n" +
                    "4. Leave edit options\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "1. Import a file\n" +
                    "2. Type the quiz\n" +
                    "What would you like to name the quiz?\n" +
                    "How many questions is the quiz?\n" +
                    "What is your question 1?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 3\n" +
                    "2. 2\n" +
                    "\n" +
                    "What is your question 2?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 7\n" +
                    "2. 2\n" +
                    "\n" +
                    "it worked\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "What is the name of the quiz?\n" +
                    "Removed Quiz: quiz1\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Bye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);


        }

        public void testThree() {

            String input = "1\r\nq\r\nq\r\n1\r\n2\r\nw\r\nw\r\n2\r\n2\r\ne" +
                    "e\r\n2\r\n3\r\n1\r\nq\r\n1\r\n1\r\nChem\r\n1\r\n2\r\nquiz\r\n" +
                    "2\r\n1+6\r\n2\r\n1\r\n7\r\n2\r\n2*2\r\n2\r\n4\r\n3\r\n1\r\n1\r\n" +
                    "2\r\nquiz2\r\n1\r\n1+9\r\n2\r\n9\r\n2\r\n1\r\n3\r\nquiz2\r\n5\r\n3\r\n2\r\nw\r\n2\r\n" +
                    "Chem\r\n1\r\nquiz\r\n2\r\n1\r\n2\r\n1\r\n1\r\n1\r\n3\r\n5\r\n";

            receiveInput(input);

            try {
                Application.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Select an option below:\n" +
                    "1. Create course\n" +
                    "2. Join course\n" +
                    "3. Delete course\n" +
                    "Enter the name of the course: \n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "1. Import a file\n" +
                    "2. Type the quiz\n" +
                    "What would you like to name the quiz?\n" +
                    "How many questions is the quiz?\n" +
                    "What is your question 1?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 1\n" +
                    "2. 7\n" +
                    "\n" +
                    "What is your question 2?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 4\n" +
                    "2. 3\n" +
                    "\n" +
                    "it worked\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "1. Import a file\n" +
                    "2. Type the quiz\n" +
                    "What would you like to name the quiz?\n" +
                    "How many questions is the quiz?\n" +
                    "What is your question 1?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 9\n" +
                    "2. 2\n" +
                    "\n" +
                    "it worked\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "What is the name of the quiz?\n" +
                    "quiz2\n" +
                    "Removed Quiz: quiz2\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Enter the name of the course: \n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Enter the quiz name\n" +
                    "quiz\n" +
                    "1+6\n" +
                    "1. 1\n" +
                    "2. 7\n" +
                    "2*2\n" +
                    "1. 4\n" +
                    "2. 3\n" +
                    "You have answered every question. Would you like to submit or redo a question?\n" +
                    "1. Submit\n" +
                    "2. Change an answer\n" +
                    "Which question (1-2) would you like to redo?\n" +
                    "1\n" +
                    "1+6\n" +
                    "1. 1\n" +
                    "2. 7\n" +
                    "You have answered every question. Would you like to submit or redo a question?\n" +
                    "1. Submit\n" +
                    "2. Change an answer\n" +
                    "Submitted!\n" +
                    "2022-04-11 17:13:04\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Error! Please enter a number!\n" +
                    "Try Again!\n" +
                    "Select an option below:1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Bye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);


        }

        public void TestFour() {

            String input = "1\r\nq\r\nq\r\n1\r\n4\r\n1\r\nq\r\n2\r\n4\r\n1" +
                    "q\r\n1\r\n2\r\nw\r\nw\r\n2\r\n4\r\n2\r\nw\r\n2\r\n5\r\n";

            receiveInput(input);

            try {
                Application.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "User does not exist!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Teacher deleted!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Student deleted!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Delete Account\n" +
                    "5. Exit\n" +
                    "Bye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);


        }



        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }
    }
}