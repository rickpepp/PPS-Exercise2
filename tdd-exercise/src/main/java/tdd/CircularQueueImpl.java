package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl<T> implements CircularQueue<T> {

    private final List<T> circularQueue;

    public CircularQueueImpl(int i) {
        circularQueue = new ArrayList<>(i);
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
        if (isEmpty())
            throw new IllegalStateException("Can't dequeue from empty queue");
        return circularQueue.remove(0);
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Can't peek from empty queue");
        return circularQueue.get(0);
    }

    @Override
    public void enqueue(T value) {
        circularQueue.add(value);
    }
}
