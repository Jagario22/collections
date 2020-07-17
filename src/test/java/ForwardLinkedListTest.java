
import com.nix.collections.list.ForwardLinkedList;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ForwardLinkedListTest {
    private ForwardLinkedList<Integer> list = new ForwardLinkedList<>();
    private List<Integer> testList = new LinkedList<>();


    @Test
    void add() {
        list.add(100);
        list.add(200);
        list.add(300);

        testList.add(400);
        testList.add(300);
        testList.add(200);
        testList.add(100);

        list.add(400);
        assertEquals(testList.toString(), list.toString());
    }

    @Test
    void remove() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);

        testList.add(300);
        testList.add(200);
        testList.add(100);

        list.removeItem(400);
        assertEquals(testList.toString(), list.toString());
    }

    @Test
    void get() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);

        testList.add(400);
        testList.add(300);
        testList.add(200);
        testList.add(100);


        Integer i = list.get(1);
        Integer expectedResults = testList.get(1);
        assertEquals(expectedResults, i);
    }

    @Test
    void size() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);

        testList.add(400);
        testList.add(300);
        testList.add(200);
        testList.add(100);

        assertEquals(testList.size(), list.size());

        list.removeItem(100);
        testList.remove(1);

        assertEquals(testList.size(), list.size());

        list.add(500);
        testList.add(500);
        assertEquals(testList.size(), list.size());

    }
}
