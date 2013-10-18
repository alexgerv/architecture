package ca.ulaval.glo4003.matchCatalog;


public interface MatchQueryFactory {

    public MatchQuery create(String serializedQuery);

}
