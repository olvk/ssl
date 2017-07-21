package SinglyLL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SinglyLLTest {

    private List<String> list;
    @Before
    public void setup() {
        list = new SinglyLinkedList2<>();
    }

    public void addStrings(int i) {
        for (int j = 1; j != i + 1; j++) {
            System.out.println(j);
            String s = "Added string " + j;
            list.add(s);
        }
    }

//
//tests for .node()
//

//    @Test(expected = IndexOutOfBoundsException.class)
//            public void testNodeWithException() {
//            list.add("Added string 1");
//            Node<E> value = new Node<E>("Added string 1", null);
//            assertEquals(value, list.node(-7));
//    assertEquals(value, list.node());
//}
//
//
//    @Test
//    public void testNode() {
//        list.add("Added string 1");
//        list.add("Added string 2");
//        Node<E> value = new Node<E>("Added strind 1", list.get(1));
//                assertEquals(value, list.node(0));
//    }
//
//    @Test
//    public void testLinkFirst() {
//        //when
//        assertEquals("Added string 1", list.linkFirst("Added string 1"));
//        assertEquals("Added string 2", list.linkFirst("Added string 2"));
//        //then
//        assertFalse(list.isEmpty());
//        assertEquals(2, list.size());
//    }
//
//    @Test
//    public void testLinkLast() {
//        //when
//        assertEquals("Added string 1", list.linkLast("Added string 1"));
//        assertEquals("Added string 2", list.linkLast("Added string 2"));
//        //then
//        assertEquals(2, list.size());
//        assertFalse(list.isEmpty());
//    }


    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add("Added string");
        assertEquals(1, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("Added string");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testContains() {
        addStrings(6);
//        list.add("Added string 10");
//        list.add("Added string 1");
//        list.add("Added string 4");
//        list.add("Added string 6");
        list.add("Added string 8");
        assertTrue(list.contains("Added string 1"));
        assertTrue(list.contains("Added string 4"));
        assertTrue(list.contains("Added string 6"));
        assertFalse(list.contains("Added string 7"));
    }


    //
//tests for .toArray()
//

    public void setupForToArray ( int v, int sslV){
        addStrings(v);
        String[] values = new String[v];
        String[] sslValues = new String[sslV];
        for (int i = 0; i < v; i++) {
            values[i] = "Added string " + (++i);
        }
        assertEquals(values, list.toArray(sslValues));
    }


    @Test
    public void testToArraySameSize() {
        setupForToArray(6, 6);
        setupForToArray(6, 4);
        setupForToArray(6, 13);
    }

//    @Test
//    public void testToArraySameSize() {
//        SetupForToArray s = new SetupForToArray();
//        SetupForToArray(6, 6);
//        assertEquals(s.values, list.toArray(sslValues));
//    }
//
//    @Test
//    public void testToArraySmaller() {
//        setupForArray(6, 4);
//        assertEquals(values, list.toArray(sslValues));
//    }
//
//    @Test
//    public void testToArrayBigger() {
//        setupForArray(6, 13);
//        assertEquals(values, list.toArray(sslValues));
//    }

    @Test
    public void testAdd() {
        //when
        assertEquals(true, list.add("Added string"));
        //then
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    //
    //tests for.remove()
    //
    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexLessThanZero() {
        assertEquals(true, list.remove(-5));
    }

    //а можно не создавать два разных теста для одного эксепшена?
    @Test (expected = IndexOutOfBoundsException.class)
    @Ignore
    public void testRemoveIndexBiggerThanSize() {
        list.add("Added String");
                assertEquals(true, list.remove(3));
    }

    @Test
    public void testRemove() {
        //when
        list.add("Added string 1");
        list.add("Added string 2");
        list.add("Added string 3");
        assertEquals(1, list.remove(1));
        //then
        assertEquals(2, list.size());
    }

    //throws IndexOutOfBoundsException:Index is less than 0 or more than the number of elements. Index = 0
    @Test (expected = NullPointerException.class)
    public void testClear() {
        //setup
        list.add("Added string 1");
        list.add("Added string 2");
        list.add("Added string 3");
        //when
        list.clear();
        //then
        assertEquals(0, list.size());
        assertEquals("Added string 1", list.get(0));
        assertEquals("Added string 2", list.get(1));
        assertEquals("Added string 3", list.get(2));
    }

    //
//tests for .get()
//
//надо будет откорректировать get
    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetIndexLessThanZero() {
        assertEquals("Something", list.get(-1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetIndexBiggerThanSize() {
        list.add("Added string 1");
        assertEquals("Something", list.get(1));
    }


    @Test
    public void testGet() {
        list.add("Added string 1");
        list.add("Added string 2");
        assertEquals("Added string 2", list.get(1));
        assertEquals("Added string 1", list.get(0));
    }

    //
    //tests for .set()
    //
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetWithExceptions() {
        addStrings(3);
        assertEquals("Changed string", list.set(-1, "Changed string"));
        assertEquals("Changed string", list.set(8, "Changed string"));
    }

    @Test
    public void testSet(){
        addStrings(3);
        assertEquals("Changed string 1", list.set(0, "Changed string 1"));
        assertEquals("Changed string 2", list.set(1, "Changed string 2"));
        assertEquals("Changed string 3", list.set(2, "Changed string 3"));
    }

    //
    //tests for .indexOf()
    //
    //думаю что этот тест мы не пройдем
    @Test(expected = NullPointerException.class)
    public void testIndexOfWithException() {
        addStrings(8);
        assertEquals(13, list.indexOf("Added string 12"));
    }


    //java.lang.NullPointerException
    @Test
    public void testIndexOf() {
        //addStrings(8);
        list.add("Added string 9");
        list.add("Added string 5");
        list.add("Added string 6");
        list.add("Added string 1");
        list.add("Added string 2");
        list.add("Added string 3");
        //assertEquals(0, list.indexOf("Added string 1"));
        assertEquals(5, list.indexOf("Added string 6"));
        //assertEquals(7, list.indexOf("Added string 8"));
    }


//
//tests for .lastIndexOf()

    //Unexpected exception, expected<java.lang.NullPointerException> but was<java.lang.AssertionError>
    @Test(expected = NullPointerException.class)
    public void testLastIndexOfWithException() {
        addStrings(4);
        addStrings(4);
        assertEquals(15, list.lastIndexOf("Added string 8"));
    }

    //Unexpected exception, expected<java.lang.NullPointerException> but was<java.lang.AssertionError>
    @Test
    public void testLastIndexOf() {
        addStrings(4);
        addStrings(4);
        addStrings(8);
        assertEquals(11, list.lastIndexOf("Added string 4"));
        assertEquals(15, list.lastIndexOf("Added string 8"));
    }

    //
//tests for .subList()
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListWithException() {
        addStrings(8);
        ArrayList<String> a = new ArrayList<>();
        assertEquals(a, list.subList(-1, 5));
        assertEquals(a, list.subList(7, 5));
        assertEquals(a, list.subList(0, 13));
    }


    //java.lang.AssertionError:
//    Expected :[Added String 1, Added String 2, Added String 3]
//    Actual   :[Added string 1, Added string 2]
    @Test
    public void testSubList() {
        addStrings(5);

        ArrayList<String> a = new ArrayList<>();
        a.add("Added string 1");
        a.add("Added string 2");
        a.add("Added string 3");
        assertEquals(a, list.subList(0, 2));

        ArrayList<String> b = new ArrayList<>();
        b.add("Added string 2");
        b.add("Added string 3");
        b.add("Added string 4");
        assertEquals(b, list.subList(1, 3));

        ArrayList<String> c = new ArrayList<>();
        c.add("Added string 3");
        c.add("Added string 4");
        c.add("Added string 5");
        assertEquals(c, list.subList(2, 4));
    }


}
