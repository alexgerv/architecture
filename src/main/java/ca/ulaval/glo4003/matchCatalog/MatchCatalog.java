package ca.ulaval.glo4003.matchCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.matchCatalog.index.Filter;
import ca.ulaval.glo4003.matchCatalog.index.Index;
import ca.ulaval.glo4003.matchCatalog.index.IndexWithList;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;

public class MatchCatalog {

    private static final String MATCHES_PATH = "./matches/";
    private static MatchCatalog matchCatalog;

    private QueryResolver<MatchFilterCategories> queryResolver;
    private MatchRepository matchRepository;
    private Index<MatchFilterCategories> index;

    protected MatchCatalog(QueryResolver<MatchFilterCategories> queryResolver, Index<MatchFilterCategories> index,
                           MatchRepository matchRepository) {
        this.queryResolver = queryResolver;
        this.index = index;
        this.matchRepository = matchRepository;

        loadAllMatchFrom(MATCHES_PATH);
        Query<MatchFilterCategories> query = new Query<MatchFilterCategories>();
        query.addFilterValue(MatchFilterCategories.SPORT, "Basketball");
        getMatchesFromQuery(query);
    }

    // Creer un builder
    public static MatchCatalog getInstance(MatchRepository matchRepository) {
        if (matchCatalog == null) {

            List<Filter<MatchFilterCategories>> filterListByCategories = new ArrayList<Filter<MatchFilterCategories>>();
            for (MatchFilterCategories category : MatchFilterCategories.values()) {
                filterListByCategories.add(new Filter<MatchFilterCategories>(category));
            }
            Index<MatchFilterCategories> index = new IndexWithList<MatchFilterCategories>(filterListByCategories);
            QueryResolver<MatchFilterCategories> queryResolver = new QueryResolver<MatchFilterCategories>(index);

            matchCatalog = new MatchCatalog(queryResolver, index, matchRepository);
        }
        return matchCatalog;
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

    public Map<String, Match> getMatchesFromQuery(Query<MatchFilterCategories> aMatchQuery) {
        List<String> matchesIdentifier = queryResolver.resolve(aMatchQuery);

        return matchRepository.getMatchesByIdentifier(matchesIdentifier);
    }

    public void add(Match match) {
        index.add(match);
        matchRepository.add(match);
    }

}
