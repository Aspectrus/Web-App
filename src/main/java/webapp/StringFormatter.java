package webapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StringFormatter {

    static String FormatRepeatingCharacters(int count, char character) {

        String SameCharacterString = new String(new char[count]).replace('\0', character);
        SameCharacterString = "("+SameCharacterString+")";
        return SameCharacterString;
    }

    static public String FormatInput(String BankCode, String CountryCode) {

            String result = CountryCode+" ";
            String temp="";
            char leftchar = 0, rightchar;
            int left = 0, right = 0, count =0;
            while(right<BankCode.length())
            {
                leftchar = BankCode.charAt(left);
                rightchar = BankCode.charAt(right);

                if(leftchar==rightchar)
                {
                    count++;
                    right++;
                }
                else if(count>1)
                {
                    temp +=FormatRepeatingCharacters(count, leftchar);
                    count = 0;
                    left = right;
                }
                else
                {
                    temp+=leftchar;
                    left++;
                    right++;
                }
            }
            if(count>1) temp +=FormatRepeatingCharacters(count, BankCode.charAt(left));
            else temp+=BankCode.charAt(left);

            return result + temp;
        }
    }
