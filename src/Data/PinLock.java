package src.Data;

/**
 * Class that creates Lock object.
 *
 * @author Adam Wikström
 * @author Wilma Nordlund
 */
public class PinLock implements ILock {

    private String pinCode;

    /**
     * A method to create a pin code.
     *
     * @param pinCode   A String which contains the pin code.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public PinLock(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Getter of the pin code.
     *
     * @return A String containing the pin code.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * A method to set the pin code.
     *
     * @param newPin    A String which contains the new pin code.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public void setPinCode(String newPin) {
        this.pinCode = newPin;
    }
}
