package ca.ulaval.glo4003.persistence.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class JSONMapper<T>  {
    
    private Class<T> type;
    
    public JSONMapper (Class<T> classType) {
        this.type = classType;
    }

    public T load(String pathToJSON) throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(pathToJSON));

        return gson.fromJson(reader, type);
    }
    
    public void save(T object, String path) throws IOException {
        createMissingDirectoriesInPath(path);
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(path);
        writer.write(gson.toJson(object));
        writer.close();
    }
    
    private void createMissingDirectoriesInPath(String path){
        File file = new File(path);
        File parent_directory = file.getParentFile();
        if (parent_directory != null)
        {
            parent_directory.mkdirs();
        }
    }
}
