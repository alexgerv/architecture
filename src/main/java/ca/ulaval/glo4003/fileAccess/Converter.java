package ca.ulaval.glo4003.fileAccess;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface Converter <T> {
    public T load(String path) throws FileNotFoundException;
    public void save(T object, String path) throws IOException;
}
