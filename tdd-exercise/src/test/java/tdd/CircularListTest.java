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

    @Test
    public void peekElementCantChangeQueueSize() {
        circularQueue.enqueue(SINGLE_VALUE_TO_ENQUEUE);
        assertEquals(1, circularQueue.size());
    }

    @Test
    public void canEnqueueAndDequeueMultipleElement() {
        int[] elements = {5, 10, 7, 2, 32};
        initializeEnqueue(elements);
        checkEnqueueValues(elements);
    }

    private void checkEnqueueValues(int[] elements) {
        for (int i = 0; i < CIRCULAR_QUEUE_SIZE; i++) {
            assertEquals(elements[i], circularQueue.dequeue());
        }
    }

    private void initializeEnqueue(int[] elements) {
        for (int element: elements) {
            circularQueue.enqueue(element);
        }
    }

    @Test
    public void circularEnqueueFunctionality() {
        int[] elements = {5, 10, 7, 2, 32, 100};
        int[] elementsExpected = {10, 7, 2, 32, 100};
        initializeEnqueue(elements);
        checkEnqueueValues(elementsExpected);
    }

    @Test
    public void maxSizeIsRespected() {
        int[] elements = {5, 10, 7, 2, 32, 100};
        initializeEnqueue(elements);
        assertEquals(CIRCULAR_QUEUE_SIZE, circularQueue.size());
    }

}
