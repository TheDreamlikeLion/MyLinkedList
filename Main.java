import MyLinkedList.MyLinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест с Int-ами.");

        MyLinkedList<Integer> integerList = new MyLinkedList<Integer>();
        integerList.addToFirst(789);
        System.out.println(integerList);
        integerList.addToFirst(123);
        System.out.println(integerList);
        integerList.addToLast(0);
        System.out.println(integerList);
        integerList.addByIndex(456, 1);
        System.out.println(integerList);
        System.out.println("Тест с Char-ами.");

        MyLinkedList<Character> characterList = new MyLinkedList<Character>();
        characterList.addToFirst('H');
        System.out.println(characterList);
        characterList.addToLast('y');
        System.out.println(characterList);
        characterList.addByIndex('e', 1);
        System.out.println(characterList);
        characterList.addToFirst('!');
        System.out.println(characterList);
        characterList.removeByIndex(0);
        System.out.println(characterList);
        characterList.addToLast('!');
        System.out.println(characterList);
    }
}
