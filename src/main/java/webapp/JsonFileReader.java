package webapp;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class JsonFileReader {

    List<String> GetISONames(String path)
    {
        ArrayList<String> AllIsoCodes = new ArrayList <String>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray AllCountriesData = (JSONArray)parser.parse(new FileReader(path));
           for (Object CountryData : AllCountriesData)
            {
                JSONObject Country = (JSONObject) CountryData;
                String ISO = (String) Country.get("alpha-2");
                AllIsoCodes.add(ISO);
            }
        }
        catch (Exception e) {

        }
        return AllIsoCodes;
    }

}
