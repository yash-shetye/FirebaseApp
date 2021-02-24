package v.abhijeet.firebaselogin;

public class User {
    public String name , mobile, username,email,password;
    User(){

    }

    public User(String name, String mobile, String username, String email, String password) {
        this.name = name;
        this.mobile = mobile;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
