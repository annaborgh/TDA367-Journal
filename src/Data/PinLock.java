package src.Data;

import java.util.Scanner;

public class PinLock {
    Scanner keypad = new Scanner(System.in);
    String pinCode = null;
    String correctPinCode = setPinCode();

    public PinLock() {
    }

    public String setPinCode() {

        System.out.print("Enter Pin Code: ");

        correctPinCode = pinCode();

        return pinCode();
    }

    public String pinCode() {
        String pinCode = keypad.nextLine();
        System.out.print("Din pinkod är: " + pinCode);
        return null;
    }

    public void getPinCode() {


        }



    public String inputPinCode() {

        return null;
    }
}
