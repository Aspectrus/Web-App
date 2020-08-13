package webapp;

import java.util.List;

public class InputValidator {


    private List<String> AllIsoCodes;


    InputValidator() {
        JsonFileReader jsonread = new JsonFileReader();
        AllIsoCodes = jsonread.GetISONames("../Countries-ISO-Codes.json");
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
