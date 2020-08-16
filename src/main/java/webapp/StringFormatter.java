package webapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StringFormatter {

    static String FormatRepeatingCharacters(int[] RepeatingIndexes, String BankCode) {

        if((RepeatingIndexes[1]-RepeatingIndexes[0])<2) return BankCode;
        String result = BankCode.substring(0,RepeatingIndexes[0])+
                "(" +
                BankCode.substring(RepeatingIndexes[0],RepeatingIndexes[1])+
                ")"+BankCode.substring(RepeatingIndexes[1]);

        return result;
    }

    int[] FindMostRepeatingCharacterIndexes(String BankCode) {


        int[] MostRepeatingIndexes = {0,0};

        char leftchar , rightchar;
        int maxCount=1 , j;
        for(int i = 0; i<BankCode.length(); i++)
        {
            int count = 1;
            leftchar = BankCode.charAt(i);

            for(j=i+1; j<BankCode.length(); j++)
            {
                rightchar = BankCode.charAt(j);
                if(leftchar !=rightchar)
                {
                    break;
                }
                count++;
            }
            if(count>maxCount)
            {
                maxCount =count;
                MostRepeatingIndexes[0]=i;
                MostRepeatingIndexes[1]=j;
            }

        }
        return MostRepeatingIndexes;

    }

    public String FormatInput(String BankCode, String CountryCode) {

        String result = CountryCode+" ";
        int[] MostRepeatingIndexes = FindMostRepeatingCharacterIndexes(BankCode);
        String FormattedBankCode = FormatRepeatingCharacters(MostRepeatingIndexes, BankCode);
        return result + FormattedBankCode;
        }

    }
