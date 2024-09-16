package deque;

public interface Deque<T> {
    void addFirst(T o);
    void addLast(T o);
    T removeFirst();
    T removeLast();
    int size();
    default boolean isEmpty(){
        return size() == 0;
    };
    T get(int i);
    void printDeque();
}
