package src.Data;

/**
 * @author Adam Wikström
 * @author Wilma Nordlund
 *
 * Class that creates Lock object.
 */
public class PinLock implements ILock {

    private String pinCode;

    /**
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * A method to create a pin code.
     *
     * @param pinCode   A String which contains the pin code.
     */
    public PinLock(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Getter of the pin code.
     *
     * @return A String containing the pin code.
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * A method to set the pin code.
     *
     * @param newPin    A String which contains the new pin code.
     */
    public void setPinCode(String newPin) {
        this.pinCode = newPin;
    }
}
