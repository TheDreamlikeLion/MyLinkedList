//package MyLinkedList;
//
//import java.util.Iterator;
//
//public class MyIterator<T> implements Iterator<T> {
//    Node<T> previous = null;
//    Node<T> current = first;
//    private T[] elements = null;
//    int index = 0;
//    public MyIterator (T[] elements) {
//        this.elements = elements;
//        this.index = 0;
//    }
//
//    public <T> MyIterator(Node<T>) {
//    }
//
//    @Override
//    public boolean hasNext() {
//        return (index < elements.length);
//    }
//
//    @Override
//    public T next() {
//        return elements[index++];
//    }
//
//    @Override
//    public void remove() {
//        Iterator.super.remove();
//    }
