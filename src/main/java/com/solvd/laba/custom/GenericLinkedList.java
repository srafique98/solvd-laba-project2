package com.solvd.laba.custom;

import com.solvd.laba.exceptions.InvalidIndexException;
import com.solvd.laba.custom.Node;

public class GenericLinkedList<T> { // <T> -- defines generic classes, methods, and interfaces.
    private Node<T> head;
    private int size;

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

    public int getSize() {
        return size;
    }

}
