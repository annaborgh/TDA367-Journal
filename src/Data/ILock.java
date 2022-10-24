package src.Data;

/**
 * Interface for PinLock class.
 *
 * @author Adam Wikström
 * @author Wilma Nordlund
 */

public interface ILock {
    int lockId = 0;

    String getPinCode();

    void setPinCode(String s);
}
