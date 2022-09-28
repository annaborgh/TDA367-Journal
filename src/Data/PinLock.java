package src.Data;

import java.util.Scanner;

public class PinLock {
    Scanner keypad = new Scanner(System.in);
    int setPinCode = keypad.nextInt();
    int pinCode = setPinCode();
    int inputPin = keypad.nextInt();
    int getPinCode = keypad.nextInt();


    public int setPinCode() {
        System.out.print("Enter Pin Code: ");
        // int input
        return setPinCode();
    }

        /* onödigt att få se sin pinkod?

        public int pinCode () {
        System.out.print("Din pinkod är: " + pinCode);
        return 0;
    }
        */

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public int getGetPinCode() {
            getPinCode = this.inputPinCode();
        return getPinCode;
    }

    private int pinCode() {
        this.pinCode = pinCode;
        return 0;
    }

    public int inputPinCode() {
        return 0;
    }

}
