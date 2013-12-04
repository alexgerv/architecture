package ca.ulaval.glo4003.infrastructure.matchCatalog;

import java.io.File;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryResolver;

public class JSONMatchCatalog extends MatchCatalog {

    private static final String MATCHES_PATH = "./matches/";

    public JSONMatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository) {
        super(queryResolver, index, matchRepository);
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
                    String matchName = f.getName();
                    String folderName = f.getParentFile().getName();
                    String matchIdentifier = folderName + "/" + matchName;
                    Match newMatch = matchRepository.getMatchByIdentifier(matchIdentifier);
                    add(newMatch);
                }
            }
        }
    }

}
