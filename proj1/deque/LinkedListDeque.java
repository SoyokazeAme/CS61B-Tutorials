package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable, Deque{
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
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

    public void addFirst(T item){
        sentinel.next = new LinkNode<>(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        last.prev = new LinkNode<>(last.prev, item, last);
        last.prev.prev.next = last.prev;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        while (sentinel.next.item != null){
            System.out.print(sentinel.next.item + " ");
            sentinel.next = sentinel.next.next;
        }
        System.out.println("\n");
        System.out.println("print completed");
    }

    public void removeFirst(){
        if (size != 0){
            sentinel.next = sentinel.next.next;
            size -= 1;
        }
    }

    public void removeLast(){
        if (size != 0){
            last.prev = last.prev.prev;
            size -= 1;
        }
    }

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
    public boolean equals(Object obj) {
        if (obj instanceof )
        return super.equals(obj);
    }
}
