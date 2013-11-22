package ca.ulaval.glo4003.domain.user;

public class User {

    private String email;
    private String password;
    private Integer access;

    public User(String username, String password, Integer access) {
        this.email = username;
        this.password = password;
        this.access = access;
    }

    public boolean hasEmailAddress(String emailAddress) {
        return emailAddress.equals(this.email);
    }

    public String getEmailAddress() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAccess() {
        return access;
    }

}
