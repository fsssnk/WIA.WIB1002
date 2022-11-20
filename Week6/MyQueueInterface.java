package Week6;

public interface MyQueueInterface<E>{

    boolean isEmpty();
    E peek();
    void enqueue(E e);
    E dequeue();
    void display();
}
