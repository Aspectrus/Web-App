package webapp;

import java.util.ArrayList;

public class StringFormatter {

    public Integer HighestLength;

    class NumberInfo
    {
        char Number;
        int Length;
        NumberInfo(char number, int length)
        {
            this.Number = number;
            this.Length = length;
        }
    }

    String FormatRepeatingCharacters(ArrayList<NumberInfo> NumberLengthList, String BankCode) {
        String result ="";

        for (NumberInfo numberLength: NumberLengthList) {

            if(numberLength.Length==HighestLength)
            {
                result+="("+(new String(new char[numberLength.Length]).replace('\u0000', numberLength.Number))+")";
            }
            else  result+=(new String(new char[numberLength.Length]).replace('\u0000', numberLength.Number));
        }

        return result;
    }

    ArrayList<NumberInfo> GetNumbersInfoList(String BankCode) {


        ArrayList<NumberInfo> numbersInfoList = new ArrayList<NumberInfo>();


        char leftchar , rightchar = 0;
        int  index;
        int count = 1;
        HighestLength =Integer.valueOf(2);
        for(index = 0; index<BankCode.length()-1; index++)
        {

            leftchar = BankCode.charAt(index);
            rightchar = BankCode.charAt(index+1);
            if(leftchar==rightchar)
            {
                count++;
            }
            else
            {
                numbersInfoList.add(new NumberInfo(leftchar,count));
                if(index+1!=BankCode.length())
                {
                    count=1;
                }
            }
            if(count>HighestLength)
            {
                HighestLength =Integer.valueOf(count);
            }
        }
        numbersInfoList.add(new NumberInfo(rightchar,count));

        return numbersInfoList;

    }

    public String FormatInput(String BankCode, String CountryCode) {

        String result = CountryCode+" ";
        ArrayList<NumberInfo> NumbersInfoList = GetNumbersInfoList(BankCode);
        String FormattedBankCode = FormatRepeatingCharacters(NumbersInfoList, BankCode);
        return result + FormattedBankCode;
        }

    }
