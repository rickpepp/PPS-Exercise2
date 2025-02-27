package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void isSizeEqualZero() {
        assertEquals(0, circularQueue.size());
    }

    @Test
    public void cantDequeueEmptyQueue() {
        assertThrows(IllegalStateException.class,
                () -> circularQueue.dequeue(),
                "Can't dequeue from empty queue");
    }

    @Test
    public void cantPeekEmptyQueue() {
        assertThrows(IllegalStateException.class,
                () -> circularQueue.peek(),
                "Can't peek from empty queue");
    }

}
