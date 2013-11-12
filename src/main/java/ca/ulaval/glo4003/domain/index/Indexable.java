package ca.ulaval.glo4003.domain.index;

public interface Indexable<E extends Enum<E>> {

    public String getAttributeValue(E category);

    public String getIdentifier();
}
