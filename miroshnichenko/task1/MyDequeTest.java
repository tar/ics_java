
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;


public class MyDequeTest {
    public static void main(String[] args) {
        System.out.println("Создаем новый дек");
        Deque<String> dek = new MyDeque<>();
        System.out.println("Дек пуст? -" +dek.isEmpty());
        System.out.println(dek.size());
        System.out.println("Вставляем 5 элементов");
        dek.add("first");
        dek.add("second");
        dek.add("third");
        dek.add("fourth");
        dek.add("fifth");
        System.out.println(dek.size());
        System.out.println("Дек пуст? -" +dek.isEmpty());
        System.out.println("Содержимое дека (просмотр с удалением):");
        displayDeque(dek);
        System.out.println("Дек пуст? -" +dek.isEmpty());
        System.out.println("Вставляем еще 4 элемента");
        String[] temp = {"first","second","third","forth"};
        dek.addAll(Arrays.asList(temp));
        System.out.println("Содержимое дека в обратном порядке (просмотр через итератор):");
        displayInvertedDeque(dek);
        System.out.println("Содержит ли дек \"third\"? -" + dek.contains("third"));
        System.out.println("A \"100500\"? -" + dek.contains("100500"));
        System.out.println("Пробум добавить null");
        dek.add(null);

    }

    private static void displayDeque(Deque<String> dek) {
        System.out.print("[");
        for(String s: dek) {
            System.out.print(dek.remove() + " ");
        }
        System.out.println("]");
    }

    private static void displayInvertedDeque(Deque<String> dek) {
        System.out.print("[");

        for(Iterator<String> i = dek.descendingIterator(); i.hasNext();) {
            System.out.print(i.next() + " ");
        }
        System.out.println("]");
    }


}
