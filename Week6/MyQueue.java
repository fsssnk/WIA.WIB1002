package Week6;

import java.util.Arrays;

import java.util.NoSuchElementException;
/*
* Implementing Queue with Array (with circular Array/Pointers):
* We resume at the beginning of the array once we reach its end.
* Both the enqueue and dequeue operations fall under this.
* This prevents from moving the array's initial leftmost element and the remaining items.
* Especially for big arrays, this process is costly.
* */

public class MyQueue<E> implements MyQueueInterface<E>{

    public static final int MAX_SIZE = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int headIndex;
    private int tailIndex;
    private int numberOfElements;

    public MyQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    public MyQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("");
        }

        elements = new Object[capacity];
    }

    @Override
    public void enqueue(Object element) {
        if (numberOfElements == elements.length) {
            grow();
        }
        elements[tailIndex] = element;
        tailIndex++;
        if (tailIndex == elements.length) {
            tailIndex = 0;
        }
        numberOfElements++;
    }

    private void grow() {
        int newCapacity = calculateNewCapacity(elements.length);
        growToNewCapacity(newCapacity);
    }

    static int calculateNewCapacity(int currentCapacity) {
        if (currentCapacity == MAX_SIZE) {
            throw new IllegalStateException();
        }

        int newCapacity = currentCapacity + calculateIncrement(currentCapacity);

        if (newCapacity > MAX_SIZE || newCapacity < 0) {
            newCapacity = MAX_SIZE;
        }

        return newCapacity;
    }

    private static int calculateIncrement(int currentCapacity) {
        return currentCapacity < 64 ? currentCapacity : currentCapacity / 2;
    }

    private void growToNewCapacity(int newCapacity) {
        Object[] newArray = new Object[newCapacity];

        // Copy to the beginning of the new array
        int oldArrayLength = elements.length;
        int numberOfElementsAfterTail = oldArrayLength - tailIndex;
        System.arraycopy(elements, tailIndex, newArray, 0, numberOfElementsAfterTail);

        // Append to new array
        if (tailIndex > 0) {
            System.arraycopy(elements, 0, newArray, numberOfElementsAfterTail, tailIndex);
        }

        // Adjust head and tail
        headIndex = 0;
        tailIndex = oldArrayLength;
        elements = newArray;
    }

    @Override
    public E dequeue() {
        final E element = elementAtHead();
        elements[headIndex] = null;
        headIndex++;
        if (headIndex == elements.length) {
            headIndex = 0;
        }
        numberOfElements--;
        return element;
    }

    @Override
    public E peek() {
        return elementAtHead();
    }

    private E elementAtHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        @SuppressWarnings("Unchecked")
        E element = (E) elements[headIndex];

        return element;
    }
    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public void display() {
        System.out.println(Arrays.deepToString(elements));
    }

    public void displayArrow(){
        Arrays.stream(elements).filter(o -> o!=null).forEach(o -> System.out.print(o + " --> "));
    }
    public MyQueue<E> copy(){
        MyQueue<E> temp = new MyQueue<>();
        Arrays.stream(elements).forEach(o -> temp.enqueue(o));
        return temp;
    }
}