package webapp;

import java.io.*;
import java.util.ArrayList;
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
            JSONArray AllCountriesData = (JSONArray)parser.parse(GetJsonFileReader(path));

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

    Reader GetJsonFileReader(String path)
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream JsonStream = classLoader.getResourceAsStream(path);
        Reader targetReader = new InputStreamReader(JsonStream);
        return  targetReader;

    }

}
