package test_data.models;

public class LoginCred {
    private String email;
    private String password;

    public LoginCred(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
