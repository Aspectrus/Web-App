package webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class InputValidator {


    private final List<String> allIsoCodes;



    public InputValidator() {

        Properties properties = getProperties();
        String path = properties.getProperty("JsonPath");
        JsonFileReader jsonread = new JsonFileReader();
        allIsoCodes = jsonread.getISONames(path);

    }

    Properties getProperties()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("foo.properties");
        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            Double.parseDouble(bankCode);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}
