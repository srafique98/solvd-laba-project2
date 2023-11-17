package com.solvd.laba.custom;

import com.solvd.laba.billing.Cost;
import com.solvd.laba.exceptions.InvalidIndexException;
import com.solvd.laba.custom.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class GenericLinkedList<T> { // <T> -- defines generic classes, methods, and interfaces.
    private static final Logger LOGGER = LogManager.getLogger(GenericLinkedList.class);
    private Node<T> head;
    private int size;

    public GenericLinkedList(Node<T> head, int size) {
        this.head = head;
        this.size = size;
    }

    public GenericLinkedList() {
        this.head = null;
        this.size = 0;
        LOGGER.info("Empty Link List created");
    }

    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T getElementByIndex(int index) throws InvalidIndexException {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void removeByIndex(int index) throws InvalidIndexException {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Invalid index: " + index);
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            previous.next = previous.next.next;
        }
        size--;
    }

    public boolean inLinkedList(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data == element) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericLinkedList<?> that = (GenericLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }
}
