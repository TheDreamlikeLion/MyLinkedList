## Задание:
Реализовать свой LinkedList с методами вставки в начало, конец списка, получения размера списка и получения
элемента по индексу

## Решение:
### Создан класс для определения ноды с двумя связями:
public class Node<T> {
    public T element;
    public Node<T> linkToNext;
    public Node<T> linkToPrevious;

### Создан интерфейс MyLinkedList:
public interface MyLinkedListInterface<T> extends Iterable<T> {
    void addByIndex(T element, int index);
    void removeByIndex(int index);
    T getElementByIndex(int index);
    int getListSize ();
}

### Создан класс MyLinkedList<T>, который объединяет в двусвязанный список объекты класса T.
public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    public Node<T> first;
    public Node<T> last;
    private int listSize = 0;

### Определены методы:
### getList - возвращает текущий размер списка.
    public int getListSize () {
        return listSize;
    }

### addToFirst - добавляет элемент в начало списка. Происходит добавление ноды с переопределением связей. Также увеличивается размер списка.
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

### addToLast - аналогично добавляет элемент в конец списка.
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

### addByIndex - добавляет элемент в определенное место списка. Происходит сдвиг списка с переопределением связей в зависимости от места добавления. Увеличивается размер списка.
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
    
### removeLast - удаляет элемент в конце списка с последующим переопределением связей между нодами. Уменьшает размер списка.
    public void removeLast () {
        if (last != null) {
            last = last.linkToPrevious;
            last.linkToNext = null;
            listSize--;
        } else {
            System.out.println("Список пуст!");
        }
    }

### removeFirst - аналогично удаляет элемент из начала списка.
    public void removeFirst () {
        if (first != null) {
        first = first.linkToNext;
        first.linkToPrevious = null;
        listSize--;
        } else {
            System.out.println("Список пуст!");
        }
    }

### removeByIndex - удаляет элемент из определенного места списка. Происходит сдвиг списка с переопределением связей в зависимости от места удаления. Уменьшается размер списка.
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

### getElementByIndex - возвращает значение из ноды, находящейся в определенном месте списка.
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

### getLastElement - возвращает значение из последней ноды.
    public T getLastElement () {
        return last.element;
    }

### getFirstElement - возвращает значение из первой ноды.
    public T getFirstElement () {
        return first.element;
    }

### Переопределены методы
### Итератор - класс, позволяющий перебирать ноды в циклах for. 
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

### toString - собирает строку report, содержащую все элементы списка при помощи метода getElementByIndex(). 
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

