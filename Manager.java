import java.util.*;

public class Manager {
    
    private ArrayList<Course> courses;
    private ArrayList<User> users;
    
    public Manager(ArrayList<Course> courses, ArrayList<User> users) {

        this.courses = courses;
        this.users = users;
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public User getUser(int i) {
        return this.users.get(i);
    }

    public User getUser(String username, String password) {

        User u = null;

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                u = users.get(i);
                break;
            }
        }

        return u;
    }

    public Course getCourse(int i) {
        return this.courses.get(i);
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean isUser(String username, String password) {
        
        boolean isU = false;

        for (int i = 0; i < users.size(); i++) {
            // System.out.println(users.get(i).getUsername());
            // System.out.println(username);
            // System.out.println(users.get(i).getPassword());
            // System.out.println(password);
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                isU = true;
            }
        }

        return isU;   
    }

    public boolean addUser(User user) {

        boolean wasAdded = false;

        if (!containsUser(user)) {
            users.add(user);
            wasAdded = true;
        }

        return wasAdded;
    }

    public boolean containsUser(User user) {

        // Variable to determine if the user is in the list of users
        boolean doesContain = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                doesContain = true;
                break;
            }
        }
        
        return doesContain;
    }

    public boolean addCourse(Course course) {

        boolean wasAdded = false;

        if (!containsCourse(course)) {
            courses.add(course);
            wasAdded = true;
        }

        return wasAdded;
    }

    public boolean containsCourse(Course course) {

        // Variable to determine if the course is in the list of courses
        boolean doesContain = false;

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getName().equals(course.getName())) {
                doesContain = true;
                break;
            }
        }
        
        return doesContain;
    }
}
