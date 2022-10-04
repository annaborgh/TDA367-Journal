package src.Data;

public interface ILock {
    boolean lockState = false;
    boolean lockActive = false;
    int lockId = 0;

   void setLockState(boolean newLockState);

    /*
    PinLock (int, int)
    getPinCode(): int
    setPinCode(int): void
    pinCode: int
    lockId: int = 0
    lockState: boolean = false
    lockActive: boolean = false
     */

}

