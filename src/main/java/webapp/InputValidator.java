package webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class InputValidator {


    private List<String> AllIsoCodes;



    InputValidator() {

        Properties properties = GetProperties();
        String path = properties.getProperty("JsonPath");
        JsonFileReader jsonread = new JsonFileReader();
        AllIsoCodes = jsonread.GetISONames(path);

    }

    Properties GetProperties()
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
    boolean ValidateInputCodes(String CountryCode, String BankCode)
    {
        Boolean CountryCodeCheck = ValidateCountryCode(CountryCode);
        Boolean BankCodeCheck = ValidateBankCode(BankCode);
        if(CountryCodeCheck&&BankCodeCheck) return  true;
        else return  false;
    }
    private Boolean ValidateCountryCode(String CountryCode)
    {
        for (String ISOCode: AllIsoCodes) {
            if(ISOCode.compareTo(CountryCode)==0) return true;
        }
        return false;
    }
    private Boolean ValidateBankCode(String BankCode)
    {
        if(BankCode.length()<2&&BankCode.length()>20) return false;
        try {
            Double.parseDouble(BankCode);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}
