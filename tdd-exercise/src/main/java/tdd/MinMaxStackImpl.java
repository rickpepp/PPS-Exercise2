package tdd;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private final Stack<Integer> internalStack;
    private final PriorityQueue<Integer> internalMinHeap;
    private final PriorityQueue<Integer> internalMaxHeap;

    public MinMaxStackImpl() {
        internalStack = new Stack<>();
        internalMinHeap = new PriorityQueue<>();
        internalMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    @Override
    public void push(int value) {
        internalStack.push(value);
        internalMinHeap.add(value);
        internalMaxHeap.add(value);
    }

    @Override
    public int pop() {
        checkEmptyCollection(this.internalStack, "Stack");
        int elementToPop = internalStack.peek();
        internalMinHeap.remove(elementToPop);
        internalMaxHeap.remove(elementToPop);
        return internalStack.pop();
    }

    @Override
    public int peek() {
        checkEmptyCollection(this.internalStack, "Stack");
        return internalStack.peek();
    }

    @Override
    public int getMin() {
        checkEmptyCollection(this.internalStack, "Stack");
        checkEmptyCollection(this.internalMaxHeap, "MinHeap");
        return internalMinHeap.peek();
    }

    @Override
    public int getMax() {
        checkEmptyCollection(this.internalStack, "Stack");
        checkEmptyCollection(this.internalMaxHeap, "MaxHeap");
        return internalMaxHeap.peek();
    }

    private <T> void checkEmptyCollection(Collection<T> collection, String objectName) {
        if (collection.isEmpty())
            throw new IllegalStateException(objectName + " is empty, invalid operation");
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
