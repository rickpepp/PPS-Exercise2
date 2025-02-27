package tdd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartDoorLockTest {

    private SmartDoorLock doorLock;
    private static final int CORRECT_PIN = 1345;
    private static final int INCORRECT_PIN = 5331;
    private static final int INVALID_PIN_LENGTH = 24234;

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
        setDoorInBlockingState();
        assertTrue(doorLock.isBlocked());
    }

    private void setDoorInBlockingState() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        for (int i = 0; i < doorLock.getMaxAttempts(); i++)
            doorLock.unlock(INCORRECT_PIN);
    }

    @Test
    public void cantBeSetPinInBlockingState() {
        setDoorInBlockingState();
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

    @Test
    public void pinCantBeSetIfLengthDifferentFrom4Digit() {
        assertThrows(IllegalArgumentException.class,
                () -> doorLock.setPin(INVALID_PIN_LENGTH),
                "PIN must be of 4 digits");
    }

    @Test
    public void cantBeUnlockedIfBlocked() {
        setDoorInBlockingState();
        doorLock.unlock(CORRECT_PIN);
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void checkOpenStateAfterReset() {
        setDoorInBlockingState();
        doorLock.reset();
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void checkOpenStateAfterResetNoBlockingState() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.reset();
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void checkFailedAttemptsAfterReset() {
        setDoorInBlockingState();
        doorLock.reset();
        assert(doorLock.getFailedAttempts() == 0);
    }

    @Test
    public void checkNoBlockingStateAfterReset() {
        setDoorInBlockingState();
        doorLock.reset();
        assertFalse(doorLock.isBlocked());
    }

    @Test
    public void checkNoPINAfterReset() {
        setDoorInBlockingState();
        doorLock.reset();
        assertThrows(IllegalStateException.class,
                () -> doorLock.lock(),
                "Can't be locked if the pin is not set");
    }
}
