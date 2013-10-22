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

    private Index<MatchFilterCategories> index;

    private MatchRepository matchRepository;

    private JSONMatchConverter converter;

    private FileAccessor fileAccessor;

    public JSONMatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository,
                            JSONMatchConverter converter, FileAccessor fileAccessor) {
        this.queryResolver = queryResolver;
        this.index = index;
        this.matchRepository = matchRepository;
        this.converter = converter;
        this.fileAccessor = fileAccessor;
        loadAllMatchFrom(MATCHES_PATH);
    }

    private void loadAllMatchFrom(String path) {
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
