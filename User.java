public class User {
    public String username;
    public String password;
    public boolean teacherPermission;

    public User(String username, String password, boolean teacherPermission) {
        this.username = username;
        this.password = password;
        this.teacherPermission = teacherPermission;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
