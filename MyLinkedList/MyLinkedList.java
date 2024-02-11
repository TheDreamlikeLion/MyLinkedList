package MyLinkedList;

import java.util.Iterator;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    public Node<T> first;
    public Node<T> last;
    private int listSize = 0;

    public void addToFirst (T element) {
        Node<T> newNode = new Node<T>();
        newNode.element = element;
        newNode.linkToNext = first;
        newNode.linkToPrevious = null;

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            first.linkToPrevious = newNode;
            first = newNode;
        }
        listSize++;
    }

    public void addToLast (T element) {
        Node<T> newNode = new Node<T>();
        newNode.element = element;
        newNode.linkToNext = null;
        newNode.linkToPrevious = last;

        if (last == null) {
            last = newNode;
            first = newNode;
        } else {
            last.linkToNext = newNode;
            last = newNode;
        }
        listSize++;
    }

    public void addByIndex (T element, int index) {
        if (index < 0 || index >= listSize) {
            System.out.println("Неправильный индекс!");
            throw new IndexOutOfBoundsException("Размер листа: " + listSize + ", а выбран индекс: " + index + ".");
        }
        Node<T> newNode = new Node<T>();
        newNode.element = element;

        if (listSize == 0) {
            last = newNode;
            first = newNode;
            newNode.linkToNext = null;
            newNode.linkToPrevious = null;
        } else {
            Node<T> current = first;
            for (int i = 0; i < index; i++) {
                current = current.linkToNext;
            }
            if (current.linkToPrevious == null) {
                first = newNode;
            } else {
                current.linkToPrevious.linkToNext = newNode;
                newNode.linkToPrevious = current.linkToPrevious;
            }
            current.linkToPrevious = newNode;
            newNode.linkToNext = current;
            if (current.linkToNext == null) {
                last = current;
            }
        }
        listSize++;
    }

    public void removeLast () {
        if (last != null) {
            last = last.linkToPrevious;
            last.linkToNext = null;
            listSize--;
        } else {
            System.out.println("Список пуст!");
        }
    }

    public void removeFirst () {
        if (first != null) {
        first = first.linkToNext;
        first.linkToPrevious = null;
        listSize--;
        } else {
            System.out.println("Список пуст!");
        }
    }

    public void removeByIndex (int index) {
        if (index < 0 || index >= listSize) {
            System.out.println("Неправильный индекс!");
            throw new IndexOutOfBoundsException("Размер листа: " + listSize + ", а выбран индекс: " + index + ".");
        }
        Node<T> current = first;
        if (listSize == 1) {
            current.element = null;
            System.out.println("Теперь список пуст!");
        } else {
            for (int i = 0; i < index; i++) {
                current = current.linkToNext;
            }
            if (current.linkToPrevious == null) {
                current.linkToNext.linkToPrevious = null;
                first = current.linkToNext;
            } else if (current.linkToNext == null) {
                current.linkToPrevious.linkToNext = null;
                last = current.linkToPrevious;
            } else {
                current.linkToNext.linkToPrevious = current.linkToPrevious;
                current.linkToPrevious.linkToNext = current.linkToNext;
            }
        }
        listSize--;
    }

    public T getElementByIndex (int index) {
        if (index < 0 || index >= listSize) {
            System.out.println("Неправильный индекс!");
            throw new IndexOutOfBoundsException("Размер листа: " + listSize + ", а выбран индекс: " + index + ".");
        }
        if (index == 0) {
            return first.element;
        }
        if (index == listSize-1) {
            return last.element;
        }
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.linkToNext;
        }
        return current.element;
    }

    public T getLastElement () {
        return last.element;
    }
    public T getFirstElement () {
        return first.element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> previous = null;
            Node<T> current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public T next() {
                previous = current;
                current = current.linkToNext;
                return previous.element;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder("{ ");
        int index = 0;
        if (listSize == 0) {
            return "{}";
        } else {
            while (index != listSize) {
                report.append(getElementByIndex(index)).append(" ");
                index++;
            }
            report.append("}");
            return report.toString();
        }
    }
}