package webapp.functionality;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


class JsonFileReader {


    List<String> getISONames(String path) throws IOException, ParseException {
        ArrayList<String> allIsoCodes = new ArrayList<>();

            JSONParser parser = new JSONParser();
            JSONArray allCountriesData = (JSONArray)parser.parse(getJsonFileReader(path));

           for (Object countryData : allCountriesData)
            {
                JSONObject country = (JSONObject) countryData;
                String iso = (String) country.get("alpha-2");
                allIsoCodes.add(iso);
            }

        return allIsoCodes;
    }

    Reader getJsonFileReader(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream jsonStream = classLoader.getResourceAsStream(path);
        assert jsonStream != null;
        return new InputStreamReader(jsonStream);

    }

}
