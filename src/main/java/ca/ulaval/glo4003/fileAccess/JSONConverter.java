package ca.ulaval.glo4003.fileAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

public class JSONConverter<T>  {
    
    private Class<T> type;
    
    public JSONConverter (Class<T> classType) {
        this.type = classType;
    }

    public T load(String pathToJSON) throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(pathToJSON));

        return gson.fromJson(reader, type);
    }
    
    public void save(T object, String path) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(path);
        writer.write(gson.toJson(object));
        writer.close();
    }
}
