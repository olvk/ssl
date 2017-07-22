package SinglyLL;

import java.util.*;

public class SinglyLinkedList2<E> implements List {

    public int size = 0;


    public SinglyLinkedList2() {
    }

    public SinglyLinkedList2(Collection<? extends E> c) {
//        this();
//        addAll(c);
    }

    public static class Node<E> {
        E item; //значение
        Node<E> next; //ссылка на след

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    Node<E> first;
    Node<E> last;

    //возвращает (весь) эл-т по индексу
    //для возвращения только значения есть get
    public Node<E> node(int index) {
        checkIndex(index);
        int i = 0;
        Node<E> value = first;
        while (i != index) {
            value = value.next;
            i++;
        }
        return value;
    }

    //а он мне все еще нужен?
    //возвращает "предыдущий" элемент
    private Node<E> nodeByNext(Node<E> a) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.next == a) return x;
        }
        return null;
    }

    //вставляем в начало
    private E linkFirst(E e) {
        final Node<E> f = first; //перекладываем в новосозданный ф то, что было в ферст
        final Node<E> newNode = new Node<>(e, f); //создаем новый с value е и ссылкой на ф
        first = newNode; //кладем новосозданный в ферст
        if (f == null) last = newNode;
        size++;
        return e;
    }

    //вставляет в конец
//    public Object linkLast(Object o) {
//        Node<E> l = last;
//        Node<E> newNode = new Node<>((E) o, null);
//        last = newNode;
//        l.next = last;
//        if (l == null) first = newNode;
//        size++;
//        return o;
//    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        } else {return false;}
    }

    @Override
    public boolean contains(Object o) {
//        Node<E> x = first;
//        while (x != null) {
//
//        }




        for (Node<E> x = first; x != null ; x = x.next) {
            if (x.item.equals((E) o)) {
                return true;
            }
        }
        return false;
    }


    //?

    @Override
    public Iterator iterator() {
        return null;
    }


    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        int i =0;
        for (Node<E> x = first; x != null && i < size; x = x.next) {
            a[i] = x.item;
            i++;
            }
        return a;
    }

    @Override
    public Object[] toArray(Object[] a) {
        int l = a.length;
        for (Node<E> x = first; x != null; x = x.next) {
            for (int i = 0; i < size; i++) {
                a[i] = x.item;
            }
            //вот следующая часть вообще нужна?
            if (l > size) {
                for (int i = size; i < l; ++i) {
                    a[i] = null;
                }
            }
        }
        return a;
    }

    @Override
    public boolean add(Object o) {

        final Node<E> l = last;
        final Node<E> newNode = new Node(o, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;


//        Node<E> newNode = new Node<>((E) o, null);
//        if (size == 0) {
//            first = newNode;
//            last = newNode;
//        } else {
//            last.next = newNode;
//            //System.out.println(first.next.item);
//            last = newNode;
//        }
//        size++;
//        return true;
//        E e = (E) o;
//        linkLast(e);
//        return true;
    }

    public boolean remove(Object o) {
        remove(indexOf(o));
        return true;
    }


    @Override
    public Object remove(int index) {
        checkIndex(index);
        if (size == 1) {
            first = last = null;
        }
        int currInd = 0;
        Node<E> curr = first;
        Node<E> prev = null;
        while (currInd != index) {
            curr = curr.next;
            prev = curr;
            currInd++;
        }
        prev = curr.next;
        size--;
        return index;
    }

//    @Override
//    public Object remove(int index) {
//        nodeByNext(node(index)).next = node(index).next;
//        node(index).next = null;
//        node(index).item = null;
//        return null;
//    }

    @Override
    public boolean containsAll(Collection c) {
        int f = 0;
        for (Object object : c) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x == c) f = 1;
            }
            if (f == 0) return false;
        }
        return true;
    }


    @Override
    public boolean addAll(Collection c) {
        for (Object object : c) {
            add(object);
        }
        return true;
    }



    @Override
    public boolean addAll(int index, Collection c) {

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) return false;

        for(Object object : c) {
            add(index, c);
            ++index;
        }
        return true;
    }


    @Override
    public boolean removeAll(Collection c) {
        Node<E> x = first;
        Node<E> next = x.next;
        for (Object object : c) {
            if (x.item == object) {
                nodeByNext(node(indexOf(x))).next = x.next;
                x.next = null;
                x = next;
                size--;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        Node<E> x = first;
        Node<E> next = x.next;
        for (Object object : c) {
            if (x.item != object) {
                nodeByNext(node(indexOf(x))).next = x.next;
                        x.item = null;
                x.next = null;
                x = next;
                size--;
            }
        }
        return true;
    }

    @Override
    public void clear() {

        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next; //создаем его заранее потому что ты не можешь сослаться на x.next
            // если ты только что удалила x.next
            x.item = null;
            x.next = null;
            x = next;
        }
        last = first = null;
        size = 0;

    }

    public void checkIndex(int index) {
        if (index > size - 1 || index < 0)
            throw new IndexOutOfBoundsException("Index is less than 0 or more than the number of elements. " +
                    "Index = " + index);
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return node(index).item;
        //.item тк надо наверное вернуть только значение, а не весь эл-т
    }

    @Override
    public Object set(int index, Object element) {
        checkIndex(index);
        node(index).item = (E) element;
        return element;
    }

    @Override
    public void add(int index, Object element) {
        Node<E> newNode = new Node<E>((E) element, (node(index - 1).next)); //почему redundant?
        node(index - 1).next = newNode;
        size++;
    }


    //криво? криво. но работает. (вроде)
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (size == 0) {
            if (first.item.equals(o)) {
                return index;
            } else throw new IndexOutOfBoundsException("No such object. " +
                    "Also someone needs to write another exception for this case.");
            }
        for (Node<E> x = first; !(x.item.equals(o)) && (x.next != null); x = x.next) {
            index++;
        }
        if ((index == size - 1) && !(last.item.equals(o))) throw new IndexOutOfBoundsException("No such object. " +
                "Also someone needs to write another exception for this case.");
            return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        int i = 0;
//        if (last.item.equals(o))
//            {return indexOf(last);
//        } else
            if (size == 0) {
            if (first.item.equals(o)) {
                return 0;
            } else throw new IndexOutOfBoundsException("No such object. " +
                    "Also someone needs to write another exception for this case.");
        } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item.equals(o)) {
                index = i;
            }
            i++;
        }
        if ((index == -1) && (i == size) && !(last.item.equals(o))) throw new IndexOutOfBoundsException("No such object. " +
                "Also someone needs to write another exception for this case.");
        else return index;
        }
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || fromIndex > toIndex || toIndex > size - 1)
        throw new IndexOutOfBoundsException("fromIndex must be equal or more than 0, " +
                "to Index must be more than fromIndex and less than number of elements. fromIndex = "
                + fromIndex + "toIndex = " + toIndex);

        int c = toIndex + 1 - fromIndex;
        List<E> b = new ArrayList<>();
        for (int i = 0; i < c; ++i) {
            b.add((E) (get(fromIndex + i)));
        }
        return b;
    }
}
