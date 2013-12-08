package ca.ulaval.glo4003.infrastructure.matchCatalog;

import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4003.domain.match.MatchAttribute;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQuery;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryFactory;

public class JSONMatchQueryFactory implements MatchQueryFactory {

    Logger logger = LogManager.getLogger("errorLogger");

    public MatchQuery create(String serializedQuery) {
        MatchQuery query = new MatchQuery();

        JSONObject unSerializeQuery;
        try {
            unSerializeQuery = new JSONObject(serializedQuery);
            Iterator<?> categories = unSerializeQuery.keys();

            while (categories.hasNext()) {
                String filterCategory = (String) categories.next();
                JSONArray categoryValues = unSerializeQuery.getJSONArray(filterCategory);

                for (int i = 0; i < categoryValues.length(); i++) {
                    JSONObject filter = categoryValues.getJSONObject(i);

                    MatchAttribute cat = MatchAttribute.valueOf(filterCategory);
                    String filterValue = (String) filter.get("name");
                    query.addFilterValue(cat, filterValue);
                }
            }
        } catch (JSONException e) {
            logger.info(e.getMessage());
        }
        return query;
    }
}
