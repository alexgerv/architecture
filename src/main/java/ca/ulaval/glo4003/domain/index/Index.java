package ca.ulaval.glo4003.domain.index;

import java.util.List;

public interface Index<E extends Enum<E>, O extends Indexable<E>> {

    public abstract void add(O indexable);

    public abstract List<String> getIdentifiersFor(E category, String filterOfCategory);

}