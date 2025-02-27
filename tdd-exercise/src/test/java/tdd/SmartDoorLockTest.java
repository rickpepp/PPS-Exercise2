package tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        doorLock.lock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void canBeUnlocked() {
        doorLock.lock();
        doorLock.unlock(1234);
        assertFalse(doorLock.isLocked());
    }
}
