package ca.ulaval.glo4003.persistence.json;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.persistence.FileAccessor;
import ca.ulaval.glo4003.repository.UserMarshaller;
import ca.ulaval.glo4003.repository.UserRepository;

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
            userMarshaller.save(user, ROOT_REPOSITORY + "/" + user.getUsername() + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected JSONUserRepository(FileAccessor fileAccessor, JSONUserMarshaller userConverter) {
        this.fileAccessor = fileAccessor;
        this.userMarshaller = userConverter;
    }

}
