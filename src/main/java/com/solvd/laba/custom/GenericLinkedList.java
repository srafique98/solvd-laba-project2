package com.solvd.laba.custom;

import com.solvd.laba.exceptions.InvalidIndexException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class GenericLinkedList<T> implements List { // <T> -- defines generic classes, methods, and interfaces.
    private static final Logger LOGGER = LogManager.getLogger(GenericLinkedList.class);
    private Node<T> head;
    private int size;

    public GenericLinkedList(Node<T> head, int size) {
        LOGGER.info("GenericLinkedList class has been created.");
        this.head = head;
        this.size = size;
    }

    public GenericLinkedList() {
        LOGGER.warn("Empty GenericLinkedList class has been created.");
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            array[index] = current.data;
            index++;
            current = current.next;
        }
        return array;
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot add null element to the list");
        }
        Node<T> newNode = new Node<>((T) o);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        int previousSize = size;
        for (Object obj : c) {
            add(obj);
        }
        return size != previousSize;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        int previousSize = size;
        int i = 0;
        Node<T> current = head;
        while (i < index) {
            current = current.next;
            i++;
        }
        for (Object obj : c) {
            Node<T> newNode = new Node<>((T) obj);
            newNode.next = current.next;
            current.next = newNode;
            size++;
            current = current.next;
        }
        return size != previousSize;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.data;
    }

    @Override
    public Object set(int index, Object element) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        T oldData = current.data;
        current.data = (T) element;
        return oldData;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        Node<T> newNode = new Node<>((T) element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> previous = head;
            int i = 1; // Skip the head node
            while (i < index) {
                previous = previous.next;
                i++;
            }
            newNode.next = previous.next;
            previous.next = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        Node<T> previous = null;
        Node<T> current = head;
        if (index == 0) {
            head = head.next;
        } else {
            int i = 0;
            while (i < index) {
                previous = current;
                current = current.next;
                i++;
            }

            previous.next = current.next;
        }
        T removedData = current.data;
        size--;
        return removedData;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }
        int lastIndex = -1;
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                lastIndex = index;
            }
            index++;
            current = current.next;
        }
        return lastIndex;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > size || toIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IllegalArgumentException("Invalid sublist indices: " + fromIndex + " to " + toIndex);
        }
        GenericLinkedList<T> subList = new GenericLinkedList<>();
        int index = 0;
        Node<T> current = head;
        while (index < fromIndex) {
            current = current.next;
            index++;
        }
        while (index < toIndex) {
            subList.add(current.data);
            current = current.next;
            index++;
        }
        return subList;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Cannot retainAll with null collection");
        }
        boolean changed = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (!c.contains(element)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Cannot removeAll with null collection");
        }
        boolean changed = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (c.contains(element)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Cannot containsAll with null collection");
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            a[index++] = current.data;
            current = current.next;
        }
        return a;
    }
}
