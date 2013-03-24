
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dm
 */
public class MyDeque<T> implements Deque<T> {

    private Entry head; // начало очереди
    private Entry tail; // конец очереди
    private int size; // текущий размер очереди

    @Override
    public void addFirst(T e) {
        ++size;
        Entry newEntry = new Entry(e);
        if (isEmpty()) {
            tail = newEntry; // если очередь была пуста, то новый элемент автоматически становится и последим
        } else {
            head.previous = newEntry; // иначе вставляем перед первым
        }
        newEntry.next = head; // связываем новый элемент с очередью (не важно была ли она пуста или нет)
        head = newEntry; // и устанавливаем новый элемент в качестве первого

    }

    @Override
    public void addLast(T e) {
        ++size;
        Entry newEntry = new Entry(e);
        if (isEmpty()) {
            head = newEntry; // аналогично методу addFirst, если очередь была пуста, значит новый элемент будет первым
        } else {
            tail.next = newEntry; // иначе вставляем после последнего
            newEntry.previous = tail; // связываем с тем, элементом, который был последним
        }
        tail = newEntry; //и устанавливаем новый элемент в качестве последнего

    }

    @Override
    public boolean offerFirst(T e) {
        // этот метод используется с очередями, у которых есть ограничение на макс. кол-во элементов
        // у нас его нет, так что всегда возвращаем true;
        addFirst(e); // размер меняется внутри этого метода
        return true;
    }

    @Override
    public boolean offerLast(T e) {
        // этот метод используется с очередями, у которых есть ограничение на макс. кол-во элементов
        // у нас его нет, так что всегда возвращаем true;
        addLast(e); // размер меняется внутри этого метода
        return true;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("очередь пуста!");
        }
        // размер меняется внутри pooFirst
        return pollFirst(); // точно не вернет null, так как если дошли сюда, то очередь не пуста
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("очередь пуста!");
        }
        // размер меняется внутри pollLast
        return pollLast(); // точно не вернет null, так как если дошли сюда, то очередь не пуста

    }

    @Override
    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = head.data;
        // модифицируем очередь, чтобы удалить первый элемент
        if (head.next == null) { // если это был единственный элемент в очереди
            tail = null; // то он же был и последним, поэтому удаляем его
        } else {
            head.next.previous = null; // "говорим" второму элементу, что у него нет предыдущего, т.е. он становится первым
        }
        head = head.next; // голова теперь ссылается на новый первый элемент
        --size;
        return res;
    }

    @Override
    public T pollLast() {
        if (isEmpty()) {
            return null;
        }
        T res = tail.data;
        if (head.next == null) {
            head = null;
        } else {
            tail.previous.next = null; // "говорим" предпосленему элементу, что у него нет следующего, т.е. он последний
        }
        tail = tail.previous;
        --size;
        return res;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("очередь пуста");
        }
        return peekFirst();
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("очередь пуста");
        }
        return peekLast();
    }

    @Override
    public T peekFirst() {
        return isEmpty() ? null : head.data;
    }

    @Override
    public T peekLast() {
        return isEmpty() ? null : tail.data;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (o == null || isEmpty()) {
            return false;
        }

        Entry current = head;
        while (!current.data.equals(o)) { // идем по списку и ищем элемент для удаления
            current = current.next;
            if (current == null) { // если дошли до конца и ничего не нашли
                return false;
            }
        }
        // меняем ссылки у соседей найденного элемента, так чтобы исключить его из списка
        if (current == head) { // если надо удалить первый элемент
            head = head.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == tail) { // если надо удалить последний элемент
            tail = tail.previous;
        } else {
            current.next.previous = current.previous;
        }
        --size;
        return true;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null || isEmpty()) {
            return false;
        }
        Entry current = tail;
        while (!current.data.equals(o)) {
            current = current.previous;
            if (current == null) {
                return false;
            }
        }
        // меняем ссылки у соседей найденного элемента, так чтобы исключить его из списка
        if (current == head) {
            head = head.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == tail) {
            tail = tail.previous;
        } else {
            current.next.previous = current.previous;
        }
        --size;
        return true;
    }

    @Override
    public boolean add(T e) {
        // очередь не ограничена по размеру
        return offerLast(e); // размер меняется внутри
    }

    @Override
    public boolean offer(T e) {
        return offerLast(e); // размер меняется внутри
    }

    @Override
    public T remove() {
        return removeFirst(); // размер меняется внутри
    }

    @Override
    public T poll() {
        return pollFirst(); // размер меняется внутри
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public void push(T e) {
        addFirst(e); // размер меняется внутри
    }

    @Override
    public T pop() {
        return removeFirst(); // размер меняется внутри
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o); // размер меняется внутри
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }
        Entry current = head;
        while (!current.data.equals(o)) {
            current = current.next;
            if (current == null) { // если дошли до конца списка
                return false;
            }
        }
        // раз не вышли раньше, значит что-то нашли
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyDequeueIterator(head);
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new MyDequeueDescIterator(tail);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Object[] toArray() {
        //БЕДА, документация говорит, что надо создать массив из новых элементов!
        // а как это сделать, если мы не знаем типа???
        Object[] res = new Object[size];
        if (isEmpty()) {
            return res; // возвращаем массив нулевой длинны, если очередь пуста
        }
        Entry current = head;
        int i = 0;
        while (current != null) {
            res[i++] = current.data;
            current = current.next;
        }
        return res;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        // по-хорошему надо бы типы проверить
        Iterator<?> i = clctn.iterator();
        while (i.hasNext()) {
            if (!contains(i.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        // по-хорошему надо бы типы проверить
        Iterator<?> i = clctn.iterator();
        boolean res = false;
        while (i.hasNext()) {
            res |= add((T) i.next());
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        // по-хорошему надо бы типы проверить
        Iterator<?> i = clctn.iterator();
        boolean res = false;
        while (i.hasNext()) {
            res |= remove(i.next());
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        //пока пусть будет unsupported
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Entry {
        // область видимости переменных пакетная, т.к. класс private, и доступ к нему
        // будем иметь только из MyDequeue, следовательно нафиг нам лишние геттеры/сеттеры

        T data;
        Entry next;
        Entry previous;

        public Entry(T data) {
            this.data = data;
        }
    }

    private class MyDequeueIterator implements Iterator<T> {

        Entry head;
        Entry current;

        public MyDequeueIterator(Entry head) {
            this.head = head;
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException("конец очереди!");
            }
            T res = current.data;
            current = current.next;
            return res;
        }

        @Override
        public void remove() {
            // пока оставлю неподдерживаемой
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class MyDequeueDescIterator implements Iterator<T> {

        Entry tail;
        Entry current;

        public MyDequeueDescIterator(Entry tail) {
            this.tail = tail;
            current = tail;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException("конец очереди!");
            }
            T res = current.data;
            current = current.previous;
            return res;
        }

        @Override
        public void remove() {
            // пока оставлю неподдерживаемой
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
