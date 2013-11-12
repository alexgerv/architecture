package ca.ulaval.domain.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface Marshaller <T> {
    public T load(String path) throws FileNotFoundException;
    public void save(T object, String path) throws IOException;
}
