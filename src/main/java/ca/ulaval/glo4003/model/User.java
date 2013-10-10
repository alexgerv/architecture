package ca.ulaval.glo4003.model;


public class User {

    private String username;
    
    public User(String username) {
        this.username = username;
    }

    public boolean hasUsername(String username) {
        return username.equals(this.username);
    }

    public String getUsername() {
        return this.username;
    }
    
}
