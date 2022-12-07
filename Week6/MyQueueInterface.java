package Week6;

public interface MyQueueInterface<E>{
    /** determine if queue is empty*/
    boolean isEmpty();
    E peek();
    void enqueue(E e);
    E dequeue();
    void display();
}
