package ca.ulaval.glo4003.infrastructure.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.domain.persistence.FileAccessor;
import ca.ulaval.glo4003.domain.persistence.UserMarshaller;
import ca.ulaval.glo4003.domain.repository.UserRepository;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.infrastructure.persistence.JSONUserMarshaller;

@Repository
@Singleton
public class JSONUserRepository extends UserRepository {

    private static final String ROOT_REPOSITORY = "./users/";
    private FileAccessor fileAccessor = new FileAccessor();
    private UserMarshaller userMarshaller = new JSONUserMarshaller();

    public JSONUserRepository() {
        this.loadAll();
    }

    public void loadAll() {
        for (String pathToUser : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            User newUser;
            try {
                newUser = userMarshaller.load(ROOT_REPOSITORY + pathToUser);
                users.add(newUser);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    protected void saveUser(User user) {
        try {
            userMarshaller.save(user, ROOT_REPOSITORY + "/" + user.getEmailAddress() + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected JSONUserRepository(FileAccessor fileAccessor, JSONUserMarshaller userConverter) {
        this.fileAccessor = fileAccessor;
        this.userMarshaller = userConverter;
    }

}
