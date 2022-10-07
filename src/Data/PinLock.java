package src.Data;


public class PinLock implements ILock {

    private String pinCode;
    private int lockId = 0;

    public PinLock(String pinCode, int lockId) {
        this.pinCode = pinCode;
        this.lockId = lockId;
    }

    public String getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(String newPin) {
        this.pinCode = newPin;
    }

        public int getLockId() {
            return lockId;
        }

    }
