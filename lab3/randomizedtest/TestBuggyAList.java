package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testBuggyList(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber){
                case 0:{
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                broken.addLast(randVal);
                break;
                }
                case 1: {
                // size
                assertEquals(L.size(), broken.size());
                break;
            }
                case 2: {
                    if (L.size() >= 1 && broken.size() >= 1){
                        // getLast
                        assertEquals(L.getLast(), broken.getLast());
                        break;
                    }
                    break;
                }
                case 3: {
                    if (L.size() >= 1 && broken.size() >= 1){
                        // removeLast
                        assertEquals(L.removeLast(), broken.removeLast());
                        break;
                    }
                break;
                }
        }}
    }
}
