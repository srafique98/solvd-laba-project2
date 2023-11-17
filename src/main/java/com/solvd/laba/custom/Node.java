package com.solvd.laba.custom;

public class Node<T> {
        protected T data;
        protected Node<T> next;
        public Node(T data) {
            this.data = data;
            this.next = null;
    }
}
