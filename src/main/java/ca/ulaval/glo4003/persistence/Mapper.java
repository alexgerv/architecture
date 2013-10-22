package ca.ulaval.glo4003.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface Mapper <T> {
    public T load(String path) throws FileNotFoundException;
    public void save(T object, String path) throws IOException;
}
