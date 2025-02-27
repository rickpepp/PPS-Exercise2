package tdd;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack{

    private Stack<Integer> internalStack;
    private PriorityQueue<Integer> internalMinHeap;

    public MinMaxStackImpl() {
        internalStack = new Stack<>();
        internalMinHeap = new PriorityQueue<>();
    }

    @Override
    public void push(int value) {
        internalStack.push(value);
        internalMinHeap.add(value);
    }

    @Override
    public int pop() {
        CheckEmptyStack("Can't pop empty stack");
        internalMinHeap.remove(internalStack.peek());
        return internalStack.pop();
    }

    @Override
    public int peek() {
        CheckEmptyStack("Can't peek empty stack");
        return internalStack.peek();
    }

    @Override
    public int getMin() {
        CheckEmptyStack("Can't getMin from an empty stack");
        return internalMinHeap.remove();
    }

    private void CheckEmptyStack(String s) {
        if (isEmpty())
            throw new IllegalStateException(s);
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
