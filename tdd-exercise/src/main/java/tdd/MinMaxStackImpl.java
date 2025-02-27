package tdd;

public class MinMaxStackImpl implements MinMaxStack{
    @Override
    public void push(int value) {

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
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }
}
