package tdd;

import java.util.Objects;
import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final Integer MAX_ATTEMPT_DEFAULT = 3;

    private boolean locked = false;
    private Optional<Integer> pin;
    private final Integer maxAttempt;
    private Integer failedAttempt;

    public SmartDoorLockImpl(int maxAttempt) {
        pin = Optional.empty();
        if (maxAttempt <= 0)
            throw new IllegalArgumentException("Max Attempt must be greater than 0");
        this.maxAttempt = maxAttempt;
        failedAttempt = 0;
    }

    public SmartDoorLockImpl() {
        this(MAX_ATTEMPT_DEFAULT);
    }

    @Override
    public void setPin(int pin) {
        if (isBlocked() || isLocked())
            throw new IllegalStateException("Can't be set pin in blocking state");
        if (pin < 1000 || pin > 9999)
            throw new IllegalArgumentException("PIN must be of 4 digits");
        this.pin = Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        if (this.pin.isPresent() && this.pin.get() == pin)
            locked = false;
        else
            failedAttempt++;
    }

    @Override
    public void lock() {
        if (this.pin.isEmpty())
            throw new IllegalStateException("Can't be locked if the pin is not set");
        locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isBlocked() {
        return Objects.equals(failedAttempt, maxAttempt);
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempt;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempt;
    }

    @Override
    public void reset() {

    }
}
