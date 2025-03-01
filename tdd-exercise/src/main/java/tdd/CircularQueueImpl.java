package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl<T> implements CircularQueue<T> {

    private final List<T> circularQueue;
    private final int queueDimension;

    public CircularQueueImpl(int dimension) {
        circularQueue = new ArrayList<>(dimension);
        queueDimension = dimension;
    }

    @Override
    public boolean isEmpty() {
        return circularQueue.isEmpty();
    }

    @Override
    public int size() {
        return circularQueue.size();
    }

    @Override
    public T dequeue() {
        checkEmptyQueue("Can't dequeue from empty queue");
        return circularQueue.remove(0);
    }

    @Override
    public T peek() {
        checkEmptyQueue("Can't peek from empty queue");
        return circularQueue.get(0);
    }

    private void checkEmptyQueue(String s) {
        if (isEmpty())
            throw new IllegalStateException(s);
    }

    @Override
    public void enqueue(T value) {
        if (size() == queueDimension) {
            dequeue();
            enqueue(value);
        } else
            circularQueue.add(value);
    }
}
