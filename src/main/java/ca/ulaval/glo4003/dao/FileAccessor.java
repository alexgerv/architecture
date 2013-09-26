package ca.ulaval.glo4003.dao;

import java.io.File;


public class FileAccessor {
    
    public String[] getFilesNameInDirectory(String directory){
        File rootFolder = new File(directory);
        if(rootFolder.isDirectory()){
            return rootFolder.list();
        }
        else{
            throw new RepositoryException(directory + " is not a valid directory");
        }
    }
}
