package webapp.functionality;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

public class StringFormatter {

    private Integer highestLength;

    String formatRepeatingCharacters(ArrayList<NumberInfo> numberLengthList) {
        StringBuilder result = new StringBuilder();

        for (NumberInfo numberInfo : numberLengthList) {

            if (numberInfo.length == highestLength) {
                result.append("(").append(StringUtils.repeat(String.valueOf(numberInfo.number), numberInfo.length)).append(")");
            } else result.append(StringUtils.repeat(String.valueOf(numberInfo.number), numberInfo.length));
        }

        return result.toString();
    }

    ArrayList<NumberInfo> getNumbersInfoList(String bankCode) {


        ArrayList<NumberInfo> numbersInfoList = new ArrayList<>();


        char currentChar;
        int index;
        int count;
        highestLength = 2;

        for (index = 0; index < bankCode.length(); index++) {
            currentChar = bankCode.charAt(index);

            if (index == 0 || numbersInfoList.get(numbersInfoList.size() - 1).number != currentChar) {
                numbersInfoList.add(new NumberInfo(currentChar, 1));
            } else {
                numbersInfoList.get(numbersInfoList.size() - 1).length++;

                count = numbersInfoList.get(numbersInfoList.size() - 1).length;
                if (count > highestLength) {
                    highestLength = count;
                }
            }
        }

        return numbersInfoList;

    }

    public String formatInput(String bankCode, String countryCode) {

        String result = countryCode + " ";
        ArrayList<NumberInfo> numbersInfoList = getNumbersInfoList(bankCode);
        String formattedBankCode = formatRepeatingCharacters(numbersInfoList);
        return result + formattedBankCode;
    }

    static class NumberInfo {
        char number;
        int length;

        NumberInfo(char number, int length) {
            this.number = number;
            this.length = length;
        }
    }

}
