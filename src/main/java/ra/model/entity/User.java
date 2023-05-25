package ra.model.entity;

public class User {
    int id;
   String userName;
   String email;
   String password;
   boolean userStatus;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public User(int id, String userName, String email, String password, boolean userStatus) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User(String username, String password) {
        this.userName= username;
        this.password = password;
    }

    public User(String userName, String email, String password, boolean userStatus) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
