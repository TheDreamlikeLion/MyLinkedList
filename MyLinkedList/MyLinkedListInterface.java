package MyLinkedList;

public interface MyLinkedListInterface<T> extends Iterable<T> {
    void addByIndex(T element, int index);
    void removeByIndex(int index);
    T getElementByIndex(int index);
    int getListSize ();
}
