package ca.ulaval.glo4003.infrastructure.persistence;

import ca.ulaval.domain.persistence.UserMarshaller;
import ca.ulaval.glo4003.domain.user.User;

public class JSONUserMarshaller extends JSONMarshaller<User> implements UserMarshaller {

    public JSONUserMarshaller() {
        super(User.class);
    }
}
