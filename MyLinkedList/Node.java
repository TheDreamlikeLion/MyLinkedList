package MyLinkedList;

public class Node<T> {
    public T element;
    public Node<T> linkToNext;
    public Node<T> linkToPrevious;

    @Override
    public String toString() {
        return element.toString();
    }
}
