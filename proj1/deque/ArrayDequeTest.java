package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

public class ArrayDequeTest {
    @Test
    public void maxTest(){
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        ad.addLast(5);
        ad.addLast(1);
        ad.addFirst(3);
        ad.addFirst(2);

        ad.max();
    }

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

    @Test
    public void getTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addFirst(4);

        System.out.println(ad.get(0));
        System.out.println(ad.get(1));
        System.out.println(ad.get(2));
        System.out.println(ad.get(3));
    }

    @Test
    public void iteratorTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addFirst(4);

        Iterator<Integer> iterator = ad.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void printDequeTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(4);
        ad.addFirst(4);
        ad.addFirst(4);
        ad.addFirst(4);
        ad.addFirst(4);

        ad.printDeque();
    }

    @Test
    public void equalsTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addFirst(3);
        ad.addFirst(4);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(1);
        ad2.addLast(4);
        ad2.addFirst(3);

        ArrayDeque<Integer> ad3 = new ArrayDeque<>();
        ad3.addLast(1);
        ad3.addLast(2);
        ad3.addFirst(4);
        ad3.addFirst(3);

        ArrayDeque<Integer> ad4 = new ArrayDeque<>();
        ad4.addLast(1);
        ad4.addLast(2);
        ad4.addFirst(3);
        ad4.addFirst(4);

        System.out.println(ad.equals(ad2));
        System.out.println(ad.equals(ad3));
        System.out.println(ad.equals(ad4));
    }

    @Test
    public void testBuggyList(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber){
                case 0:{
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    ad.addLast(randVal);
                    lld.addLast(randVal);
                    break;
                }
                case 1: {
                    // size
                    ad.size();
                    lld.size();
                    break;
                }
                case 2: {
                    int randVal = StdRandom.uniform(0, 100);
                    ad.addFirst(randVal);
                    lld.addFirst(randVal);
                    break;
                }
                case 3: {
                    if (ad.size() >= 1 && lld.size() >= 1){
                        // removeLast
                        ad.removeFirst();
                        ad.removeLast();
                        lld.removeFirst();
                        lld.removeLast();
                        break;
                    }
                    break;
                }
            }}
    }
}
