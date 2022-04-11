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
        public void testOne() {

            String input = "1\r\nAbc\r\nabc\r\n123\r\n1\r\nEfg\r\nefg\r\n1234\r\n2\r\nQwe\r\nqwe\r\n12345\r\n2\r\nZxc\r\nzxc\r\n123\r\n4\r\n";

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
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Bye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);


        }

        public void testTwo() {

            String input = "1\r\nAa\r\naa\r\n123\r\n2\r\nZz\r\n2\r\nXx\r\nxx\r\n1234" +
                    "1\r\nQq\r\nqq\r\n1234\r\n3\r\n1\r\nqq\r\n1234\r\n1\r\nArt\r\n1\r\n" +
                    "2\r\nquiz1\r\n2\r\n1+1\r\n2\r\n1\r\n2\r\n2\r\n1+1+1\r\n2\r\n3\r\n1\r\n" +
                    "1\r\n5\r\n3\r\n2\r\nzz\r\n123\r\nArt\r\n1\r\nquiz1\r\n2\r\n3\r\n" +
                    "1\r\n3\r\n4\r\n";

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
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Select an option below:\n" +
                    "1. Create course\n" +
                    "2. Join course\n" +
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
                    "2. 2\n" +
                    "\n" +
                    "What is your question 2?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 3\n" +
                    "2. 1\n" +
                    "\n" +
                    "it worked\n" +
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
                    "4. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Enter the name of the course: \n" +
                    "That course does not exist!\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Enter the quiz name\n" +
                    "1+1\n" +
                    "1. 1\n" +
                    "2. 2\n" +
                    "1+1+1\n" +
                    "1. 3\n" +
                    "2. 1\n" +
                    "You have answered every question. Would you like to submit or redo a question?\n" +
                    "1. Submit\n" +
                    "2. Change an answer\n" +
                    "Submitted!\n" +
                    "2022-04-11 14:55:59\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Bye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);


        }

        public void testThree() {

            String input = "1\r\nqq\r\nqq\r\n11\r\n3\r\n1\r\nqq\r\n11\r\n1\r\nmath" +
                    "1\r\n2\r\nquiz\r\n1\r\n1+1\r\n2\r\n2\r\n1\r\n1\r\n5\r\n2\r\n" +
                    "aa\r\naa\r\n111\r\n3\r\n2\r\naa\r\n111\r\nmath\r\n1\r\nquiz\r\n1\r\n1\r\n" +
                    "3\r\n4\r\n";

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
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Select an option below:\n" +
                    "1. Create course\n" +
                    "2. Join course\n" +
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
                    "1. 2\n" +
                    "2. 1\n" +
                    "\n" +
                    "it worked\n" +
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
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Enter the name of the course: \n" +
                    "That course does not exist!\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Enter the quiz name\n" +
                    "quiz\n" +
                    "1+1\n" +
                    "1. 2\n" +
                    "2. 1\n" +
                    "You have answered every question. Would you like to submit or redo a question?\n" +
                    "1. Submit\n" +
                    "2. Change an answer\n" +
                    "Submitted!\n" +
                    "2022-04-11 15:20:45\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Bye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);


        }

        public void TestFour() {

            String input = "1\r\nq\r\nq\r\n1\r\n2\r\nw\r\nw\r\n2\r\n2\r\ne" +
                    "e\r\n2\r\n3\r\n1\r\nq\r\n1\r\n1\r\nBio\r\n1\r\n2\r\nquiz\r\n1\r\n" +
                    "1+2\r\n2\r\n3\r\n1\r\n1\r\n1\r\n2\r\nquiz2\r\n2\r\n1+4\r\n2\r\n3\r\n" +
                    "5\r\n2\r\n2+1\r\n2\r\n1\r\n3\r\n2\r\n3\r\nquiz\r\n5\r\n3\r\n2\r\nw\r\n2\r\n" +
                    "Bio\r\n1\r\nquiz\r\nquiz2\r\n2\r\n2\r\n1\r\n3\r\n4\r\n";

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
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Enter your Full Name:\n" +
                    "Type in your username:\n" +
                    "Type in your password:\n" +
                    "You are Signed-Up Successfully!\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Select an option below:\n" +
                    "1. Create course\n" +
                    "2. Join course\n" +
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
                    "1. 3\n" +
                    "2. 1\n" +
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
                    "1. 3\n" +
                    "2. 5\n" +
                    "\n" +
                    "What is your question 2?\n" +
                    "How many options will you have?\n" +
                    "What is your option 1?\n" +
                    "What is your option 2?\n" +
                    "which one is the answer?\n" +
                    "please use the number of the option\n" +
                    "1. 1\n" +
                    "2. 3\n" +
                    "\n" +
                    "it worked\n" +
                    "Select an option below:\n" +
                    "1. Add Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. View and Grade Student Submissions\n" +
                    "5. Sign out\n" +
                    "What is the name of the quiz?\n" +
                    "Removed Quiz: quiz\n" +
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
                    "4. Exit\n" +
                    "Teacher account or Student Account\n" +
                    "1. Teacher Account\n" +
                    "2. Student Account\n" +
                    "Enter Username:\n" +
                    "Enter Password:\n" +
                    "Login is Successful!\n" +
                    "Enter the name of the course: \n" +
                    "That course does not exist!\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Enter the quiz name\n" +
                    "Error! Quiz does not exist!\n" +
                    "Enter the quiz name\n" +
                    "quiz2\n" +
                    "1+4\n" +
                    "1. 3\n" +
                    "2. 5\n" +
                    "2+1\n" +
                    "1. 1\n" +
                    "2. 3\n" +
                    "You have answered every question. Would you like to submit or redo a question?\n" +
                    "1. Submit\n" +
                    "2. Change an answer\n" +
                    "Submitted!\n" +
                    "2022-04-11 15:41:55\n" +
                    "Select an option below:\n" +
                    "1. Take quiz\n" +
                    "2. View grades\n" +
                    "3. Exit\n" +
                    "Welcome to Management System!\n" +
                    "Login Options:\n" +
                    "1. Set up a Teacher account\n" +
                    "2. Set up a Student account\n" +
                    "3. Log-In\n" +
                    "4. Exit\n" +
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