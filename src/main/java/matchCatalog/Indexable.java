package matchCatalog;


public interface Indexable<E extends Enum<E>> {
    
    public abstract String getFilterValueOfCategory(E category);
    public abstract String getIdentifier();
}
