/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import java.math.BigInteger;
import model.BaseType;

/**
 *
 * @author PhuongNH
 */
public class BaseNumberConverter {

    private static final String DIGITS = "0123456789ABCDEF";

    /**
     * @covert value from base number input to base number output
     *
     * @param (baseInput, baseOutput, value)
     * @return String
     */
    public static String convert(BaseType baseInput, BaseType baseOutput, String value) {
        // Using regex to check if the number equals 0 or not 
        // and base input equals base output base or not
        if (value.matches("^(0)*$") || baseInput == baseOutput) {
            return value;
        }
        String result;
        // if baseInput = 10 <=> the base input number is decimal.
        // implement covert from decimal to binary or hex
        if (baseInput == BaseType.DECIMAL) {
            result = convertFromDecimalToOthers(value, baseOutput);
        } // if baseOutput = 10 <=> the base output number is decimal
        // implement covert from binary or hex to decimal
        else if (baseOutput == BaseType.DECIMAL) {
            result = convertToDecimal(value, baseInput);
        } /* if baseInput = 2 <=> the base input number is binary
                        // implement covert from binary to hex
                   else implement covert from hex to binary
         */ else if (baseInput == BaseType.BINARY) {
            result = convertFromBinaryToHex(value);
        } else {
            result = convertFromHexToBinary(value);
        }
        return result;
    }

    /**
     * @covert from decimal to binary or hex
     *
     * @param (value, baseOutput)
     * @return String
     */
    private static String convertFromDecimalToOthers(String value, BaseType baseOutput) {
        BigInteger newValue = new BigInteger(value);
        StringBuilder result = new StringBuilder("");
        // number > 0
        while (newValue.compareTo(new BigInteger("0")) > 0) {
            int remainder = Integer.parseInt(newValue.mod(new BigInteger(String.valueOf(baseOutput.getIntValue()))).toString()); // number % base
            result = result.append(DIGITS.charAt(remainder)); //concating digit at the end of the result
            newValue = newValue.divide(new BigInteger(String.valueOf(baseOutput.getIntValue()))); //number = number / base
        }
        // reverse to result and return 
        return result.reverse().toString();
    }

    /**
     * @covert from binary or hex to decimal
     *
     * @param (value, baseInput)
     * @return String
     */
    private static String convertToDecimal(String value, BaseType baseInput) {
        BigInteger result = new BigInteger("0");
        BigInteger pow = new BigInteger("1");

        // Get digits from the rightmost to the leftmost digit of the value
        // result = a*base^0 + b*base^1 + ... + b*base^(length-1)
        for (int i = value.length() - 1; i >= 0; i--) {
            int digit = DIGITS.indexOf(value.charAt(i));
            result = result.add(pow.multiply(new BigInteger(String.valueOf(digit))));
            pow = pow.multiply(new BigInteger(Integer.toString(baseInput.getIntValue())));
        }
        return result.toString();
    }

    private static String convertFromBinaryToHex(String value) {
        StringBuilder result = new StringBuilder("");
        String subBinary;
        int length = value.length();

        int step = (int) Math.ceil(1.0 * length / 4);

        System.out.println("step=" + step);
        for (int i = step - 1; i >= 0; i--) {
            if (i == 0) {
                subBinary = value.substring(0, length);
            } else {
                subBinary = value.substring(length - 4, length);
            }

            int digit = Integer.parseInt(convertToDecimal(subBinary, BaseType.BINARY));

            result = result.append(DIGITS.charAt(digit));

            length -= 4;
        }
        return result.reverse().toString();
    }

    private static String convertFromHexToBinary(String value) {
        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < value.length(); i++) {
            String subBinary = "";

            int digit = DIGITS.indexOf(value.charAt(i));

            subBinary = subBinary.concat(convertFromDecimalToOthers(Integer.toString(digit), BaseType.BINARY));

            if (i > 0 && subBinary.length() < 4) {
                for (int j = 0; j < 4 - subBinary.length(); j++) {
                    result = result.append("0");
                }
            }

            result = result.append(subBinary);
        }
        return result.toString();
    }

}
