package ca.ulaval.glo4003.infrastructure.matchCatalog;

import java.io.File;
import java.io.FileNotFoundException;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.infrastructure.match.JSONMatchMarshaller;

public class JSONMatchCatalog extends MatchCatalog {

    private static final String MATCHES_PATH = "./matches/";

    private JSONMatchMarshaller marshaller;

    public JSONMatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository,
                            JSONMatchMarshaller marshaller) {
        super(queryResolver, index, matchRepository);
        this.marshaller = marshaller;
        loadAllMatchFrom(MATCHES_PATH);
    }

    private void loadAllMatchFrom(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        for (File f : list) {
            if (!f.isHidden()) {
                if (f.isDirectory()) {
                    loadAllMatchFrom(f.getAbsolutePath());
                } else {
                    try {
                        Match newMatch = marshaller.load(f.getAbsolutePath());
                        add(newMatch);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
