package ca.ulaval.glo4003.domain.user;

public class User {

    private String username;
    private String password;
    private Integer access;

    public User(String username, String password, Integer access) {
        this.username = username;
        this.password = password;
        this.access = access;
    }

    public boolean hasUsername(String username) {
        return username.equals(this.username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAccess() {
        return access;
    }
}
