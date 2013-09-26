package ca.ulaval.glo4003.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

public class MatchFactoryFromJSON {

    public Match createMatch(String pathToJSON) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new BufferedReader(new FileReader(pathToJSON));
        
        Match match = gson.fromJson(reader, Match.class);
        
        return match;
    }
}
