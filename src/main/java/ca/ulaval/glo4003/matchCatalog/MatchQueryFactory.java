package ca.ulaval.glo4003.matchCatalog;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


public class MatchQueryFactory {

    public Query<MatchFilterCategories> create(String serializedQuery) {
        Query<MatchFilterCategories> query = new Query<MatchFilterCategories>();
        
        try {
            JSONObject unSerializeQuery = new JSONObject(serializedQuery);
            Iterator<?> categories = unSerializeQuery.keys();
            
            while(categories.hasNext()){
                String filterCategory = (String)categories.next();
                JSONArray categoryValues = unSerializeQuery.getJSONArray(filterCategory);
                
                for(int i=0; i<categoryValues.length(); i++){
                    JSONObject filter = categoryValues.getJSONObject(i);
                    
                    MatchFilterCategories cat = MatchFilterCategories.valueOf(filterCategory);
                    String filterValue = (String) filter.get("name");
                    query.addFilterValue(cat, filterValue);
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return query;
    }

}
