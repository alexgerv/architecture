package ca.ulaval.glo4003.domain.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4003.domain.repository.RepositoryException;

public class FileAccessor {

    public List<String> getFilesNameInDirectory(String directory) {
        File rootFolder = new File(directory);
        if (rootFolder.isDirectory()) {
            List<String> list = Arrays.asList(rootFolder.list());
            List<String> filteredList = removeHiddenFiles(list);
            return filteredList;
        } else {
            throw new RepositoryException(directory + " is not a valid directory");
        }
    }

    private List<String> removeHiddenFiles(List<String> list) {
        List<String> filteredList = new ArrayList<String>();
        for (String filename : list) {
            if (!filename.startsWith(".")) {
                filteredList.add(filename);
            }
        }
        return filteredList;
    }
}
