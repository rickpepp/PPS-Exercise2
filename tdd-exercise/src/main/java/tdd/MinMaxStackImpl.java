package tdd;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack{

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
        CheckEmptyStack("Can't pop empty stack");
        int elementToPop = internalStack.peek();
        internalMinHeap.remove(elementToPop);
        internalMaxHeap.remove(elementToPop);
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
        CheckEmptyHeap(internalMinHeap, "Internal Min Heap is empty");
        return internalMinHeap.peek();
    }

    private void CheckEmptyStack(String s) {
        if (isEmpty())
            throw new IllegalStateException(s);
    }

    @Override
    public int getMax() {
        CheckEmptyStack("Can't getMax from an empty stack");
        CheckEmptyHeap(internalMaxHeap, "Internal Max Heap is empty");
        return internalMaxHeap.peek();
    }

    private void CheckEmptyHeap(PriorityQueue<Integer> internalHeap, String message) {
        if (internalHeap.isEmpty())
            throw new IllegalStateException(message);
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
