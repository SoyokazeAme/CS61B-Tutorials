package deque;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    T[] item;
    int size;
    int capacity;
    int nextFirst;
    int nextLast;

    public ArrayDeque(){
        capacity = 8;
        item = (T[])new Object[capacity];
        nextFirst =(int) (Math.random() * (capacity - 1));
        nextLast = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;
        size = 0;
    }

    private T[] resize(int nextCapacity){
        T[] newArray = (T[]) new Object[nextCapacity];
        int indexOfNewArray = 0;
        for (int i = 0; i < capacity; i++){
            if (item[i] != null){
                newArray[(nextCapacity / 4) + indexOfNewArray] = item[i];
                indexOfNewArray += 1;
            }
        }
        //System.arraycopy(item,0, newArray,(capacity / 4) + 1, size);
        item = newArray;
        nextFirst = nextCapacity / 4 - 1;
        nextLast = nextFirst + size + 1;
        capacity = nextCapacity;
        return item;
    }
    @Override
    public void addFirst(T o) {
        if (size == capacity){
            resize(capacity * 2);
        }
        item[nextFirst] = o;
        size += 1;
        nextFirst = (nextFirst - 1 < 0) ? (capacity - 1) : nextFirst - 1;
    }

    @Override
    public void addLast(T o) {
        if (size == capacity){
            resize(capacity * 2);
        }
        item[nextLast] = o;
        size += 1;
        nextLast = (nextLast + 1 > (capacity - 1)) ? 0 : nextLast + 1;
    }

    @Override
    public void removeFirst() {
        if (size < 1) return;
        if ((double) size / capacity <= 0.25 && capacity > 8){
            resize(capacity / 2);
        }
        int currFirst = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;
        item[currFirst] = null;
        size -= 1;
        nextFirst = currFirst;
    }

    @Override
    public void removeLast() {
        if (size < 1) return;
        if (size / capacity <= 0.25 && capacity > 8){
            resize(capacity / 2);
        }
        int currLast = (nextLast - 1 < 0) ? (capacity - 1) : nextLast - 1;
        item[currLast] = null;
        size -= 1;
        nextLast = currLast;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int i) {
        if (i < size){
            return item[i];
        }
        return null;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < capacity; i++){
            if (item[i] != null && i != capacity - 1){
                System.out.print(item[i] + "  ");
            }
        }
        System.out.println("\n");
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator<>();
    }

    private class ArrayDequeIterator<T> implements Iterator<T>{
        int currIndex;
        int remainNum;

        public ArrayDequeIterator(){
            currIndex = 0;
            remainNum = size;
        }
        @Override
        public boolean hasNext() {
            return remainNum != 0;
        }

        @Override
        public T next() {
            for (int i = currIndex; i < capacity; i++){
                if (item[i] != null){
                    currIndex = i;
                    currIndex += 1;
                    remainNum -= 1;
                    break;
                }
            }
            return (T)item[currIndex - 1];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) return false;
        ArrayDeque ad = (ArrayDeque) o;
        if (ad.size != size()) return false;
        int indexOfAd = 0;
        int indexOfItem = 0;
        lp1:for (int i = indexOfItem; i < capacity; i++){
            if (item[i] != null){
                lp2:for (int j = indexOfAd; j < ad.capacity; j++){
                    if (ad.item[j] != null){
                        if (item[i] != ad.item[j]){
                            return false;
                        }
                        indexOfAd = j + 1;
                        break;
                    }
                }
            }
        }
        return true;
    }
}
