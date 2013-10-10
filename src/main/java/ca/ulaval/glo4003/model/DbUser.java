package ca.ulaval.glo4003.model;

public class DbUser {

    private String username;
    private String password;

    public DbUser(String username) {
        this.username = username;
    }

    public DbUser() {

    }

    public boolean hasUsername(String username) {
        return username.equals(this.username);
    }

    /**
     * Access level of the user. 1 = Admin user 2 = Regular user
     */
    private Integer access;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

}
