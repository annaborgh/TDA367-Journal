package src.Data;

public interface ILock {
    boolean lockState = false;
    boolean lockActive = false;
    int lockId = 0;
    boolean getLockState = false;
}

