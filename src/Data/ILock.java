package src.Data;

/**
 * @author Adam Wikström
 * @author Wilma Nordlund
 *
 * Interface for PinLock class.
 */

public interface ILock {
    int lockId = 0;

    String getPinCode();

    void setPinCode(String s);
}
