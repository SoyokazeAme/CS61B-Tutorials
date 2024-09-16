package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable, Deque<T>{
    @Override
    public Iterator iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Object>{
        int currIndex;
        LinkNode iteratedObject;

        LinkedListDequeIterator(){
            currIndex = 1;
            iteratedObject = sentinel;
        }
        @Override
        public boolean hasNext() {
            return currIndex <= size;
        }

        @Override
        public T next() {
            iteratedObject = iteratedObject.next;
            currIndex += 1;
            return (T)iteratedObject.item;
        }
    }

    static class LinkNode<T>{
        private LinkNode prev;
        private T item;
        private LinkNode next;

        public LinkNode(LinkNode prev, T item, LinkNode next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        };

        public LinkNode(){
            this.prev = null;
            this.item = null;
            this.next = null;
        }
    }

    private LinkNode sentinel = new LinkNode<>();
    private LinkNode last = new LinkNode<>();
    private int size;

    public LinkedListDeque(T item){
        sentinel.next = new LinkNode<>(sentinel, item, last);
        last.prev = sentinel.next;
        size += 1;
    }

    public LinkedListDeque(){
        size = 0;
    }

    @Override
    public void addFirst(T item){
        if (sentinel.next == null){
            sentinel.next = new LinkNode<>(sentinel, item, last);
            last.prev = sentinel.next;
        }else {
            sentinel.next = new LinkNode<>(sentinel, item, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(T item){
        if (sentinel.next == null){
            sentinel.next = new LinkNode<>(sentinel, item, last);
            last.prev = sentinel.next;
        }else {
            last.prev = new LinkNode<>(last.prev, item, last);
            last.prev.prev.next = last.prev;
        }
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        while (sentinel.next.item != null){
            System.out.print(sentinel.next.item + " ");
            sentinel.next = sentinel.next.next;
        }
        System.out.println("\n");
        System.out.println("print completed");
    }

    @Override
    public T removeFirst(){
        if (size != 0){
            T removedItem = (T)sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return removedItem;
        }
        return null;
    }

    @Override
    public T removeLast(){
        if (size != 0){
            T removedItem = (T)last.prev.item;
            last.prev = last.prev.prev;
            last.prev.next = last;
            size -= 1;
            return removedItem;
        }
        return null;
    }

    @Override
    public T get(int index){
        if (index < size){
            int currIndex = -1;
            while (currIndex != index){
                LinkNode p = sentinel;
                for (int i = 0; i <= index; i++){
                    p = p.next;
                }
                currIndex += 1;
                return (T) p.item;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) return false;
        LinkNode p = sentinel.next;
        LinkedListDeque comparedObject = (LinkedListDeque) o;
        if (comparedObject.size != size()) return false;
        while (comparedObject.sentinel.next != null){
            if (comparedObject.sentinel.next.item != p.item){
                return false;
            }
            comparedObject.sentinel.next = comparedObject.sentinel.next.next;
            p = p.next;
        }
        return true;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(LinkNode p, int index){
        if (index >= size) return null;
        if (index == 0){
            return (T) p.item;
        }else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }
}
