package ca.ulaval.glo4003.model;

public class UserJSONConverter extends JSONConverter<User> {
     
    public UserJSONConverter() {
        super(User.class);
    }
}