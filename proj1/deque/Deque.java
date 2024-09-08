package deque;

public interface Deque<T> {
    void addFirst(T o);
    void addLast(T o);
    T removeFirst();
    T removeLast();
    int size();
    boolean isEmpty();
    T get(int i);
    void printDeque();
}
