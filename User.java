public class User {

    public String name;
    public String username;
    public String password;
    public boolean teacherPermission;

    public User(String name, String username, String password, boolean teacherPermission) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.teacherPermission = teacherPermission;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTeacherPermission() {
        return teacherPermission;
    }
    public String toString() {
        String str = "";
        str = username + "\n" + password + "\n" + teacherPermission + '\n';
        return str;
    }
}
