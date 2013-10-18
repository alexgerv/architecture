package ca.ulaval.glo4003.index;

public interface Indexable<E extends Enum<E>> {

    public String getFilterValueOfCategory(E category);

    public String getIdentifier();
}
