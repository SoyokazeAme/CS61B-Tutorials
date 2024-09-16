package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> implements Deque<T>, Iterable<Object> {
    T[] item;
    int size;
    int capacity;
    int nextFirst;
    int nextLast;
    Comparator<T> comparator;

    public MaxArrayDeque(){
        capacity = 8;
        item = (T[])new Object[capacity];
        nextFirst = capacity / 2;
        nextLast = nextFirst + 1;
        size = 0;
    }

    public MaxArrayDeque(Comparator<T> comparator){
        capacity = 8;
        item = (T[])new Object[capacity];
        nextFirst = capacity / 2;
        nextLast = nextFirst + 1;
        size = 0;
        this.comparator = comparator;
    }

    public T max(){
        int currFirst = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;
        T max = item[currFirst];
        int remainNum = size;
        for (int i = currFirst; remainNum > 0; i++, remainNum--) {
            if (i >= capacity) {i -= capacity;}
            max = comparator.compare(max, item[i]) >= 0 ? max : item[i];
        }
        return max;
    }

    public T max(Comparator c){
        int currFirst = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;
        T max = item[currFirst];
        int remainNum = size;
        for (int i = currFirst; remainNum > 0; i++, remainNum--) {
            if (i >= capacity) {i -= capacity;}
            max = c.compare(max, item[i]) >= 0 ? max : item[i];
        }
        return max;
    }

    private T[] resize(int nextCapacity){
        T[] newArray = (T[]) new Object[nextCapacity];
        int indexOfNewArray = 1;
        int remainNum = size;
        for (int i = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1; remainNum > 0; i++, remainNum--){
            if (i >= capacity){i -= capacity;}
            if (item[i] != null){
                newArray[(nextCapacity / 4) + indexOfNewArray] = item[i];
                indexOfNewArray += 1;
            }
        }

        //System.arraycopy(item,0, newArray,(capacity / 4) + 1, size);
        item = newArray;
        nextFirst = nextCapacity / 4;
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
    public T removeFirst() {
        if (size < 1) return null;
        if ((double) size / capacity <= 0.25 && capacity > 8){
            resize(capacity / 2);
        }
        int currFirst = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;
        T removedItem = item[currFirst];
        item[currFirst] = null;
        size -= 1;
        nextFirst = currFirst;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (size < 1) return null;
        if (size / capacity <= 0.25 && capacity > 8){
            resize(capacity / 2);
        }
        int currLast = (nextLast - 1 < 0) ? (capacity - 1) : nextLast - 1;
        T removedItem = item[currLast];
        item[currLast] = null;
        size -= 1;
        nextLast = currLast;
        return removedItem;
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
        if (i >= size) return null;
        int currFirst = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;
        i = currFirst + i;
        if (i >= capacity){i -= capacity;}
        return item[i];
    }

    @Override
    public void printDeque() {
        int remainNum = size;
        for (int i = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1; remainNum > 0; i++, remainNum--){
            if (i >= capacity){i -= capacity;}
            if (item[i] != null){
                System.out.print(item[i] + "  ");
            }
        }
        System.out.println("\n");
    }


    @Override
    public Iterator<Object> iterator() {
        return new MaxArrayDequeIterator<>();
    }

    private class MaxArrayDequeIterator<T> implements Iterator<T>{
        int currIndex;
        int remainNum;

        public MaxArrayDequeIterator(){
            currIndex = (nextFirst + 1 > (capacity - 1)) ? 0 : nextFirst + 1;;
            remainNum = size;
        }
        @Override
        public boolean hasNext() {
            return remainNum != 0;
        }

        @Override
        public T next() {
            for (int i = currIndex; remainNum != 0; i++){
                if (i >= capacity){i -= capacity;}
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
        MaxArrayDeque ad = (MaxArrayDeque) o;
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
