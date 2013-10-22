package ca.ulaval.glo4003.persistence.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4003.index.Index;
import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.matchCatalog.MatchQuery;
import ca.ulaval.glo4003.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.persistence.FileAccessor;
import ca.ulaval.glo4003.repository.MatchRepository;

public class JSONMatchCatalog implements MatchCatalog {

    private static final String MATCHES_PATH = "./matches/";

    private MatchQueryResolver queryResolver;

    private MatchRepository matchRepository;

    private Index<MatchFilterCategories> index;

    public JSONMatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository) {
        this.queryResolver = queryResolver;
        this.index = index;
        this.matchRepository = matchRepository;
        loadAllMatchFrom(MATCHES_PATH);
    }

    // Mettre dans le builder ou trouver autre chose
    private void loadAllMatchFrom(String path) {
        FileAccessor fileAccessor = new FileAccessor();
        JSONMatchConverter converter = new JSONMatchConverter();
        for (String file : fileAccessor.getFilesNameInDirectory(path)) {
            File testFile = new File(path + file);
            if (!testFile.isDirectory()) {
                try {
                    Match newMatch = converter.load(path + file);
                    add(newMatch);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String, Match> getMatchesFromQuery(MatchQuery aMatchQuery) {
        List<String> matchesIdentifier = queryResolver.resolve(aMatchQuery);

        return matchRepository.getMatchesByIdentifier(matchesIdentifier);
    }

    public void add(Match match) {
        index.add(match);
        matchRepository.add(match);
    }

}
