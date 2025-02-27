package tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartDoorLockTest {

    private SmartDoorLock doorLock;
    private static final int CORRECT_PIN = 12345;
    private static final int INCORRECT_PIN = 531;

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
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void canBeUnlockedAndSetPin() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        doorLock.unlock(CORRECT_PIN);
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void cantBeUnlockedWithoutRightPin() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        doorLock.unlock(INCORRECT_PIN);
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void cantBeLockedIfPinNotSet() {
        assertThrows(IllegalStateException.class,
                () -> doorLock.lock(),
                "Can't be locked if the pin is not set");
    }

    @Test
    public void canBeSetMaxAttempt() {
        SmartDoorLock doorLock1 = new SmartDoorLockImpl(1);
        assert(doorLock1.getMaxAttempts() == 1);
    }

    @Test
    public void cantBeSetMaxAttemptEqualZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new SmartDoorLockImpl(0),
                "Max Attempt must be greater than 0");

    }

    @Test
    public void cantBeSetMaxAttemptMinorZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new SmartDoorLockImpl(-1),
                "Max Attempt must be greater than 0");
    }

    @Test
    public void startNoFailedAttempt() {
        assert(doorLock.getFailedAttempts() == 0);
    }

    @Test
    public void countFailedAttempt() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        doorLock.unlock(INCORRECT_PIN);
        assert(doorLock.getFailedAttempts() == 1);
    }

    @Test
    public void atStartIsNotBlocked() {
        assertFalse(doorLock.isBlocked());
    }

    @Test
    public void canBeBlocked() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        for (int i = 0; i < doorLock.getMaxAttempts(); i++)
            doorLock.unlock(INCORRECT_PIN);
        assertTrue(doorLock.isBlocked());
    }

    @Test
    public void cantBeSetPinInBlockingState() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        for (int i = 0; i < doorLock.getMaxAttempts(); i++)
            doorLock.unlock(INCORRECT_PIN);
        assertThrows(IllegalStateException.class,
                () -> doorLock.setPin(INCORRECT_PIN),
                "Can't be set pin in blocking state");
    }

    @Test
    public void cantBeSetPinInLockingState() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        assertThrows(IllegalStateException.class,
                () -> doorLock.setPin(INCORRECT_PIN),
                "Can't be set pin in blocking state");
    }
}
