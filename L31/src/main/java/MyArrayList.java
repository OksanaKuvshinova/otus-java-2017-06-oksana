import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY_POOL = 10;

    private Object[] elements;

    private int size;

    public MyArrayList() {
        elements = new Object[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean contains(Object o) {
        throw new NotImplementedException();
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new NotImplementedException();
    }

    public boolean add(T element) {
        fixCapacity(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    public void add(int index, T element) {
        throw new NotImplementedException();
    }

    private void fixCapacity(int newSize) {
        if (elements.length < newSize) {
            elements = Arrays.copyOf(elements, newSize + DEFAULT_CAPACITY_POOL);
        }
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new NotImplementedException();
    }
    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }

    public T get(int index) {
        return (T) elements[index];
    }

    public T set(int index, T element) {
        elements[index] = element;
        return (T) elements[index];
    }

    public T remove(int index) {
        throw new NotImplementedException();
    }

    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    public Iterator<T> iterator() {
        return new MyListIterator<>(0);
    }

    public ListIterator<T> listIterator() {
        return new MyListIterator<>(0);
    }

    public ListIterator<T> listIterator(int index) {
        throw new NotImplementedException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }

    private class MyListIterator<E> implements ListIterator<E> {

        private int index;

        public MyListIterator(int index) {
            this.index = index - 1;
        }

        @Override
        public boolean hasNext() {
            return index + 1 < MyArrayList.this.size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            index++;
            return (E) MyArrayList.this.elements[index];
        }

        @Override
        public boolean hasPrevious() {
            throw new NotImplementedException();
        }

        @Override
        public E previous() {
            throw new NotImplementedException();
        }

        @Override
        public int nextIndex() {
            throw new NotImplementedException();
        }

        @Override
        public int previousIndex() {
            throw new NotImplementedException();
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }

        @Override
        public void set(E e) {
            MyArrayList.this.elements[index] = e;
        }

        @Override
        public void add(E e) {
            throw new NotImplementedException();
        }
    }
}
