package ca.ulaval.glo4003.matchCatalog.index;


public interface Indexable<E extends Enum<E>> {
    
    public abstract String getFilterValueOfCategory(E category);
    public abstract String getIdentifier();
}
