package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {

        System.out.println("Тест коммита");
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        System.out.println("Первый i.next() возвращает: " + it.next());
        System.out.println("Второй i.next() возвращает: " + it.next());
        System.out.println("Второй i.next() возвращает: " + it.next());
        System.out.println("Второй i.next() возвращает: " + it.next());

        int count = 0;



        for (Integer i : list) {
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
//        return super.iterator();
        return new RoundIterator();
    }

    public class RoundIterator<E> implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount; // modCount будет меняться "снаружи"

        RoundIterator() {}

        public boolean hasNext() {
            return size() > 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;

            if (i >= size())
                i = 0;


//            Object[] elementData = ArrayList.this.elementData;
            // TODO надо переписать, т.к. у текущего класса нет такого поля
            Object[] elementData;
            try {
                Field elementDataField = ArrayList.class.getDeclaredField("elementData");
                elementDataField.setAccessible(true);
                elementData = (Object[]) elementDataField.get(Solution.this);

            } catch (NoSuchFieldException | IllegalAccessException e) {
            }





            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;

            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            final int size = ArrayList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount) {
                consumer.accept((E) elementData[i++]);
            }
            // update once at end of iteration to reduce heap write traffic
            cursor = i;
            lastRet = i - 1;
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
