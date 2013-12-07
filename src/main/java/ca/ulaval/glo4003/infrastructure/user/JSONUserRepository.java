package ca.ulaval.glo4003.infrastructure.user;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Singleton;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserRepository;
import ca.ulaval.glo4003.infrastructure.persistence.FileAccessor;

@Repository
@Singleton
public class JSONUserRepository extends UserRepository {

    private static final String ROOT_REPOSITORY = "./users/";
    private FileAccessor fileAccessor = new FileAccessor();
    private JSONUserMarshaller userMarshaller = new JSONUserMarshaller();
    private Logger logger = LogManager.getRootLogger();

    public JSONUserRepository() {
        this.loadAll();
    }

    public void loadAll() {
        for (String pathToUser : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            loadUser(pathToUser);
        }
    }

    private void loadUser(String pathtoUser) {
        try {
            User newUser = userMarshaller.load(ROOT_REPOSITORY + pathtoUser);
            users.add(newUser);
        } catch (FileNotFoundException e) {
            Logger logger = LogManager.getLogger("Exception");
            logger.info(e.getMessage());
        }
    }

    protected void saveUser(User user) {
        try {
            userMarshaller.save(user, ROOT_REPOSITORY + "/" + user.getEmailAddress() + ".json");
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    protected JSONUserRepository(FileAccessor fileAccessor, JSONUserMarshaller userConverter) {
        this.fileAccessor = fileAccessor;
        this.userMarshaller = userConverter;
    }

}
