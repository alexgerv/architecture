package ca.ulaval.glo4003.persistence.json;

import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.repository.UserMarshaller;

public class JSONUserMarshaller extends JSONMarshaller<User> implements UserMarshaller {

    public JSONUserMarshaller() {
        super(User.class);
    }
}
