package deque;

import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void addTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addFirst(4);
    }

    @Test
    public void removeTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addFirst(4);

        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeFirst();
        ad.removeFirst();
    }

    @Test
    public void resizingTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 16; i++){
            ad.addFirst(i);
        }
        for (int i = 0; i < 15; i++){
            ad.removeFirst();
        }
    }
}
