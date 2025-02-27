package tdd;

public class MinMaxStackImpl implements MinMaxStack{

    private int size = 0;

    @Override
    public void push(int value) {
        size++;
    }

    @Override
    public int pop() {
        throw new IllegalStateException("Can't pop empty stack");
    }

    @Override
    public int peek() {
        throw new IllegalStateException("Can't peek empty stack");
    }

    @Override
    public int getMin() {
        throw new IllegalStateException("Can't getMin from an empty stack");
    }

    @Override
    public int getMax() {
        throw new IllegalStateException("Can't getMax from an empty stack");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return size;
    }
}
