package src.Data;

/**
 * @author Adam
 * @author Wilma
 *
 * Interface for Lock class.
 */

public interface ILock {
    int lockId = 0;

    String getPinCode();

    void setPinCode(String s);
}
