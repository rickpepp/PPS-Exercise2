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
}