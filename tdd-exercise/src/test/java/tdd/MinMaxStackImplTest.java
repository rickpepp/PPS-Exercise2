package tdd;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    @Test
    public void isEmptyAtStart() {
        MinMaxStack stack = new MinMaxStackImpl();
        assertTrue(stack.isEmpty());
    }
}