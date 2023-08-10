/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import convert.BaseNumberConverter;
import model.BaseType;
import validate.Validator;

/**
 *
 * @author PhuongNH
 */
public class BaseNumberController {

    private BaseType baseInput;
    private BaseType baseOutput;
    private String value = "";
    private String result = "";

    /**
     * Set base number input is entered by user
     *
     * @param (choice)
     */
    public void setBaseNumnerInput(int choice) {
        this.baseInput = (choice == 1) ? BaseType.HEXADECIMAL : ((choice == 2) ? BaseType.DECIMAL : BaseType.BINARY);
    }

    /**
     * Set base number output is entered by user
     *
     * @param (choice)
     */
    public void setBaseNumnerOutput(int choice) {
        this.baseOutput = (choice == 1) ? BaseType.HEXADECIMAL : ((choice == 2) ? BaseType.DECIMAL : BaseType.BINARY);
    }

    /**
     * Set input value is entered by user
     *
     */
    public void setInputValue() {
        this.value = Validator.inputValue("Enter value: ", baseInput);
    }

    /**
     * Program process
     *
     */
    public void convertBaseNumber() {
        this.result = BaseNumberConverter.convert(baseInput, baseOutput, value);
    }

    /**
     * Print output value
     *
     */
    public void displayValue() {
        System.out.println(this.value + "(" + baseInput.getIntValue() + ") = " + this.result + "(" + baseOutput.getIntValue() + ")");
    }

}
