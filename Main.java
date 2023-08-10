
import controller.BaseNumberController;
import validate.Validator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PhuongNH
 */
public class Main {

    public static void main(String[] args) {
        BaseNumberController baseNumberController = new BaseNumberController();
        int choice;
        while (true) {
            // step1: show list of base number
            showListOfBaseNumber();
            // step2: Required user choose the base number input
            choice = Validator.getInt("Choose the base number input: ", 1, 3);
            baseNumberController.setBaseNumnerInput(choice);
            
            // step3: show list of base number
            showListOfBaseNumber();
            // step4: Required user choose the base number output
            choice = Validator.getInt("Choose the base number output: ", 1, 3);
            baseNumberController.setBaseNumnerOutput(choice);
            
            // step5: Required user enter the input value
            baseNumberController.setInputValue();
            
            // step6: Program process
            baseNumberController.convertBaseNumber();
            
            // step7: print output value
            baseNumberController.displayValue();
            
            // step5: Ask user want to continue or not
            if (!Validator.choiceYesOrNo("Do you want to continue(Y) or not(N)?")) {
                return;
            }
        }
    }

    private static void showListOfBaseNumber() {
        System.out.println("====== List Base Number ======");
        System.out.println("1. Hexadecimal");
        System.out.println("2. Decimal");
        System.out.println("3. Binary");
    }
}
