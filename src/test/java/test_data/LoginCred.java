package test_data;

public class LoginCred {

    private String username;
    private String password;

    public LoginCred(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
