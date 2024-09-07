package deque;

import java.util.Iterator;

public interface Deque<T> {
    void addFirst(T o);
    void addLast(T o);
    void removeFirst();
    void removeLast();
    int size();
    boolean isEmpty();
    T get(int i);
    void printDeque();

    Iterator<T> iterator();

}
