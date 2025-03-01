package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue<T> {

    /**
     * Check if the circular queue is empty
     * @return true if the circular queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Gets the number of elements currently in the circular queue.
     * @return The size of the queue
     */
    int size();

    /**
     * Add value to the end of the queue, if the queue is full, overwrite the oldest element
     * @param value The T value to add at the end of the queue
     */
    void enqueue(T value);

    /**
     * Return and remove the oldest element in the queue
     * @return The oldest element of the queue
     */
    T dequeue();

    /**
     * Return the oldest element in the queue
     * @return The oldest element of the queue
     */
    T peek();

}