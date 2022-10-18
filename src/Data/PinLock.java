package src.Data;

public class PinLock implements ILock {

    private String pinCode;

    public PinLock(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(String newPin) {
        this.pinCode = newPin;
    }
}
