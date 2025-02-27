package tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack{

    private Stack<Integer> internalStack;

    public MinMaxStackImpl() {
        internalStack = new Stack<>();
    }

    @Override
    public void push(int value) {
        internalStack.push(value);
    }

    @Override
    public int pop() {
        if (isEmpty())
            throw new IllegalStateException("Can't pop empty stack");
        return internalStack.pop();
    }

    @Override
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException("Can't peek empty stack");
        return internalStack.peek();
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
        return internalStack.isEmpty();
    }

    @Override
    public int size() {
        return internalStack.size();
    }
}
