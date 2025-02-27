package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private CircularQueue circularQueue;

    @BeforeEach
    public void init() {
        circularQueue = new CircularQueueImpl(5);
    }

    @Test
    public void isEmptyAtStart() {
        assertTrue(circularQueue.isEmpty());
    }

}
