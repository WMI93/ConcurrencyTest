package Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyTest {
    public static void main(String[] args) {
        //EG CopyOnWriteArrayList  may avoid infinite loops.
        CopyOnWriteArrayList<Integer> iL =
                new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        for (Integer integer : iL) {
            iL.add(4);
        System.out.println(iL);
        }
    }
}