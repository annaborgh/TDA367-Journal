package src.Data;

/**
 * @author Adam
 * @author Wilma
 *
 * Class that creates Lock object.
 */
public class PinLock implements ILock {

    private String pinCode;

    public PinLock(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Getter of the pin code.
     *
     * @return A string containing the pin code.
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * Setter of the pin code.
     *
     * @param newPin    The new pin code.
     */
    public void setPinCode(String newPin) {
        this.pinCode = newPin;
    }
}
