/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author PhuongNH
 */
public enum BaseType {
    BINARY, DECIMAL, HEXADECIMAL;

    public int getIntValue() {
        switch (this) {
            case BINARY:
                return 2;
            case DECIMAL:
                return 10;
            case HEXADECIMAL:
                return 16;
            default:
                throw new UnsupportedOperationException();

        }
    }
}
