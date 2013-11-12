package ca.ulaval.glo4003.domain.matchCatalog;

public interface MatchQueryFactory {

    public MatchQuery create(String serializedQuery);
}
