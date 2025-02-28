package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    public static final int SINGLE_VALUE_TO_ENQUEUE = 5;
    public static final int CIRCULAR_QUEUE_SIZE = 5;
    private CircularQueue<Integer> circularQueue;

    @BeforeEach
    public void init() {
        circularQueue = new CircularQueueImpl<Integer>(CIRCULAR_QUEUE_SIZE);
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

    @Test
    public void canEnqueueAndDequeue() {
        circularQueue.enqueue(SINGLE_VALUE_TO_ENQUEUE);
        assertEquals(SINGLE_VALUE_TO_ENQUEUE, circularQueue.dequeue());
    }

    @Test
    public void dequeueRemoveElement() {
        circularQueue.enqueue(SINGLE_VALUE_TO_ENQUEUE);
        circularQueue.dequeue();
        assertTrue(circularQueue.isEmpty());
    }

    @Test
    public void enqueueIncreaseQueueSize() {
        circularQueue.enqueue(SINGLE_VALUE_TO_ENQUEUE);
        assertEquals(1, circularQueue.size());
    }

    @Test
    public void dequeueDecreaseQueueSize() {
        circularQueue.enqueue(SINGLE_VALUE_TO_ENQUEUE);
        circularQueue.dequeue();
        assertEquals(0, circularQueue.size());
    }

    @Test
    public void canPeekElement() {
        circularQueue.enqueue(SINGLE_VALUE_TO_ENQUEUE);
        assertEquals(SINGLE_VALUE_TO_ENQUEUE, circularQueue.peek());
    }

}
