package ca.ulaval.glo4003.matchCatalog;

public interface MatchQueryFactory {

    public Query<MatchFilterCategories> create(String serializedQuery);

}
