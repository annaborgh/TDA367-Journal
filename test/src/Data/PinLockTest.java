package Data;

import org.junit.Test;
import src.Data.ILock;
import src.Data.PinLock;

import static org.junit.Assert.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the PinLock class
 */
public class PinLockTest {

    ILock pinLock = new PinLock("1234");
    /**
     * Method to test the constructor of the PinLock Class
     */
    @Test
    public void testConstructor(){

        // Testing so that the constructor creates an PinLock object of type ILock
        ILock pinLock2 = new PinLock("1234");
        assertTrue(pinLock2 instanceof ILock);

    }

    /**
     * Method to test the getters of the PinLock class
     */
    @Test
    public void testGetters(){

        // Testing the method getPinCode
        assertEquals("1234", pinLock.getPinCode());
    }

    /**
     * Method to test the setters of the PinLock class
     */
    @Test
    public void testSetters(){

        // Testing the method setPinCode
        pinLock.setPinCode("1234567890");
        assertEquals("1234567890", pinLock.getPinCode());
    }

}