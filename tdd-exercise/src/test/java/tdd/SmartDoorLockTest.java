package tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartDoorLockTest {

    private SmartDoorLock doorLock;

    @BeforeEach
    public void init() {
        doorLock = new SmartDoorLockImpl();
    }

    @Test
    public void isUnlockedAtStart() {
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void canBeLocked() {
        doorLock.setPin(12345);
        doorLock.lock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void canBeUnlockedAndSetPin() {
        doorLock.setPin(12345);
        doorLock.lock();
        doorLock.unlock(12345);
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void cantBeUnlockedWithoutRightPin() {
        doorLock.setPin(12345);
        doorLock.lock();
        doorLock.unlock(531);
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void cantBeLockedIfPinNotSet() {
        assertThrows(IllegalStateException.class,
                () -> doorLock.lock(),
                "Can't be locked if the pin is not set");
    }

}
