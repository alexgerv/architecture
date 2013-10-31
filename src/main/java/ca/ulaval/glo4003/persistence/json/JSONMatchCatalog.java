package ca.ulaval.glo4003.persistence.json;

import java.io.File;
import java.io.FileNotFoundException;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.persistence.FileAccessor;
import ca.ulaval.glo4003.repository.MatchRepository;

public class JSONMatchCatalog extends MatchCatalog {

    private static final String MATCHES_PATH = "./matches/";

    private JSONMatchMapper mapper;

    private FileAccessor fileAccessor;

    public JSONMatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository,
                            JSONMatchMapper mapper, FileAccessor fileAccessor) {
        super(queryResolver, index, matchRepository);
        this.mapper = mapper;
        this.fileAccessor = fileAccessor;
        loadAllMatchFrom(MATCHES_PATH);
    }

    private void loadAllMatchFrom(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null)
            return;

        for (File f : list) {
            if (f.isDirectory()) {
                loadAllMatchFrom(f.getAbsolutePath());
            } else {
                try {
                    Match newMatch = mapper.load(f.getAbsolutePath());
                    add(newMatch);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        // for (String file : fileAccessor.getFilesNameInDirectory(path)) {
        // File testFile = new File(path + file);
        // if (!testFile.isDirectory()) {
        // try {
        // Match newMatch = mapper.load(path + file);
        // add(newMatch);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
        // } else {
        // loadAllMatchFrom(path + file);
        // }
        // }
    }

}
