package webapp.functionality;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class InputValidator {


    private final List<String> allIsoCodes;



    public InputValidator() throws IOException, ParseException {

        Properties properties = getProperties();
        String path = properties.getProperty("JsonPath");
        JsonFileReader jsonread = new JsonFileReader();
        allIsoCodes = jsonread.getISONames(path);

    }

    Properties getProperties() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("json.properties");
        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }
    public boolean validateInputCodes(String countryCode, String bankCode)
    {
        Boolean countryCodeCheck = validateCountryCode(countryCode);
        Boolean bankCodeCheck = validateBankCode(bankCode);
        return countryCodeCheck && bankCodeCheck;
    }
    private Boolean validateCountryCode(String countryCode)
    {
        for (String isoCode: allIsoCodes) {
            if(isoCode.compareTo(countryCode)==0) return true;
        }
        return false;
    }
    private Boolean validateBankCode(String bankCode)
    {
        if(bankCode.length()<2||bankCode.length()>20) return false;
        try {
            Double.parseDouble(String.valueOf(bankCode.charAt(bankCode.length()-1))); // if code end with: d/f/D/F
            Double.parseDouble(bankCode);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}
