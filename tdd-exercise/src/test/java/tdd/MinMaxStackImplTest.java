package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    private MinMaxStack stack;

    @BeforeEach
    public void init() {
        stack = new MinMaxStackImpl();
    }

    @Test
    public void isEmptyAtStart() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void popEmptyStack() {
        assertThrows(IllegalStateException.class,
                () -> stack.pop(),
                "Pop empty stack must return IllegalStateException");
    }

    @Test
    public void peekEmptyStack() {
        assertThrows(IllegalStateException.class,
                () -> stack.peek(),
                "Peek empty stack must return IllegalStateException");
    }

    @Test
    public void getMinEmptyStack() {
        assertThrows(IllegalStateException.class,
                () -> stack.getMin(),
                "Get Min empty stack must return IllegalStateException");
    }

    @Test
    public void getMaxEmptyStack() {
        assertThrows(IllegalStateException.class,
                () -> stack.getMax(),
                "Get Max empty stack must return IllegalStateException");
    }

    @Test
    public void sizeEmptyStack() {
        assert(stack.size() == 0);
    }

    @Test
    public void canPushStack() {
        stack.push(0);
        assert(stack.size() == 1);
    }

    @Test
    public void pushAndPopValue() {
        stack.push(0);
        assert(stack.pop() == 0);
    }

    @Test
    public void isNotEmptyAfterPush() {
        stack.push(0);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void pushAndPeekValue() {
        stack.push(0);
        assert(stack.peek() == 0);
    }

    @Test
    public void checkPeekNotDeleteNothing() {
        stack.push(0);
        stack.peek();
        assert(stack.pop() == 0);
    }

    @Test
    public void pushAndPopMultipleValue() {
        int[] values = {1, 5, 7, 3, 1, 6, 3, 2};

        for (int value : values) {
            stack.push(value);
        }

        for (int i = values.length - 1; i >= 0; i--) {
            assert(stack.pop() == values[i]);
        }
    }
}