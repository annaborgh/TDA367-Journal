package src.Data;

import java.util.Scanner;

public class PinLock {
    Scanner keypad = new Scanner(System.in);
    int setPinCode = keypad.nextInt();
    int pinCode = setPinCode();
    int getPinCode = keypad.nextInt();
    int inputPin = keypad.nextInt();

        public int setPinCode () {

        System.out.print("Enter Pin Code: ");

    // int input

        return setPinCode();
    }

    // onödigt att få se sin pinkod?

        public int pinCode () {
        System.out.print("Din pinkod är: " + pinCode);
        return 0;
    }


    public int getGetPinCode() {
            getPinCode = this.pinCode();
        return getPinCode;
    }

    public void setGetPinCode(int getPinCode) {
        this.getPinCode = getPinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public int inputPinCode() {

        return 0;
    }

}
