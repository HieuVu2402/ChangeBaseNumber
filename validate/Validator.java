/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.util.Scanner;
import model.BaseType;

/**
 *
 * @author PhuongNH
 */
public class Validator {

    private static Scanner in = new Scanner(System.in);
    private static final String DIGITS = "0123456789ABCDEF";

    public static boolean choiceYesOrNo(String message) {
        // Loop until user input correct
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty. Must be enter Y/y or N/n. Please re-enter!");
            } else if (result.equalsIgnoreCase("y")) {
                return true;
            } else if (result.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.err.println("Invalid! Must be enter Y/y or N/n. Please re-enter!");
            }
        }
    }

    public static int getInt(String message, int min, int max) {
        //loop until user enter correct
        while (true) {
            System.out.print(message);
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                // result is only valid in range [min, max]
                if (result < min || result > max) {
                    throw new Exception();
                }
                return result;
            } catch (Exception e) {
                System.err.println("Invalid! Please enter an integer number in the range[" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String inputValue(String message, BaseType baseInput) {
        // Loop until user input correct
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim().toUpperCase();
            if (result.isEmpty()) {
                System.out.println("Not empty. Please re-enter!");
                continue;
            }
            // regex = "^[0-(base-1)]*$"
            // base : 2 / 10 / 16
            // Using to check the number have matches with regex or not
            String regex = "^[" + DIGITS.substring(0, baseInput.getIntValue()) + "]*$";
            // Check if the number just contain digits in the range from 0 to (base - 1) or not
            if (result.matches(regex)) {
                return result;
            } else {
                System.out.println("Invalid! This is not a number of base " + baseInput.getIntValue() + ". Please re-enter!");
            }
        }
    }

}
