package tdd;

public class CircularQueueImpl implements CircularQueue {
    public CircularQueueImpl(int i) {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void dequeue() {
        throw new IllegalStateException("Can't dequeue from empty queue");
    }
}
