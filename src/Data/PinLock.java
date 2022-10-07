package src.Data;


public class PinLock implements ILock {

    private int pinCode;
    private int lockId = 0;
    private boolean lockState = false;

    public PinLock(int pinCode, int lockId) {
        this.pinCode = pinCode;
        this.lockId = lockId;
    }

    public int getPinCode() {
        return this.pinCode;
    }

    public void setPinCode(int newPin) {
        this.pinCode = newPin;

        public int getLockId () {
            return lockId;
        }

        public boolean isLockActive () {
            return lockActive;
        }

        public void setLockActive ( boolean lockActive){
            this.lockActive = lockActive;
        }

        @Override
        public void setLockState ( boolean newLockState){
            this.lockState = newLockState;
        }
    }
