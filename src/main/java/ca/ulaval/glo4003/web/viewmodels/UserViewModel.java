package ca.ulaval.glo4003.web.viewmodels;

public class UserViewModel {

    private String email;
    private String password;

    public String getEmailAddress() {
        return email;
    }

    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
