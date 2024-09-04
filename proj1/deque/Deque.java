package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T o);
    public void addLast(T o);
    public void removeFirst();
    public void removeLast();
    public int size();
    public boolean isEmpty();
    public T get(int i);
    public void printDeque();

    public Iterator<T> iterator();

    public boolean equals();

}
