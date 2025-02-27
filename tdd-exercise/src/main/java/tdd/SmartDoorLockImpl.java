package tdd;

import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {

    private boolean locked = false;
    private Optional<Integer> pin;
    private Integer maxAttempt;

    public SmartDoorLockImpl(int maxAttempt) {
        pin = Optional.empty();
        this.maxAttempt = maxAttempt;
    }

    public SmartDoorLockImpl() {
        this(3);
    }

    @Override
    public void setPin(int pin) {
        this.pin = Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        if (this.pin.isPresent() && this.pin.get() == pin)
            locked = false;
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
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempt;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
