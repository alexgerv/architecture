package ca.ulaval.glo4003.matchCatalog.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter<E extends Enum<E>> {
    
    private E category;
    private Map<String, List<String>> identifiers = new HashMap<String, List<String>>();

    public Filter(E category) {
        this.category = category;
    }

    public void add(Indexable<E> anIndexable) {
        String filterValue = anIndexable.getFilterValueOfCategory(category);
        String identifier = anIndexable.getIdentifier();
        addInMap(filterValue, identifier);
    }

    private void addInMap(String filterValue, String identifier) {
        if(!identifiers.containsKey(filterValue)) {
            identifiers.put(filterValue, new ArrayList<String>());
        }
        identifiers.get(filterValue).add(identifier);        
    }

    public boolean isOfCategory(E category) {
        return this.category == category;
    }

    public List<String> getIdentifiersFor(String filterValue) {
        if(identifiers.containsKey(filterValue)){
            return identifiers.get(filterValue);   
        }
        else{
            return new ArrayList<String>();
        }
  
    }

}
