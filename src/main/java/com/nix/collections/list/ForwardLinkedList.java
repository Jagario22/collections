package com.nix.collections.list;

import java.util.AbstractList;
import java.util.NoSuchElementException;


public class ForwardLinkedList<E extends Number> extends AbstractList<E> {
    private int size = 0;
    private ForwardLinkedList.Node<E> head;

    public ForwardLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    private E unlink(Node<E> x, Node<E> previous) {
        E element = x.item;

        if (previous != null) {
            previous.next = x.next;
        } else {
            this.head = x.next;
        }

        x.item = null;
        --this.size;
        ++this.modCount;
        return element;
    }

    private void linkAfter(E item, int index) {
        ForwardLinkedList.Node<E> x;
        x = node(index - 1);
        ForwardLinkedList.Node<E> temp = x;
        x.next = new ForwardLinkedList.Node(item, temp.next);
    }

    private E unlinkAfter(int index) {
        ForwardLinkedList.Node<E> x;
        x = node(index - 1);
        E result = x.next.item;
        this.unlink(node(index), node(index - 1));
        return result;
    }

    public boolean add(E e) {
        if (e == null)
            return false;

        ForwardLinkedList.Node<E> newNode = new ForwardLinkedList.Node(e, head);
        this.head = newNode;

        ++this.size;
        ++this.modCount;
        return true;
    }

    public void add(int index, E item) {
        this.checkPosition(index);

        if (index == 0) {
            ForwardLinkedList.Node<E> x = new ForwardLinkedList.Node<>(item, head);
            this.head = x;
        } else {
            this.linkAfter(item, index);
        }
        ++this.size;
        ++this.modCount;
    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean removeItem(E e) {
        ForwardLinkedList.Node<E> previous = null;
        ForwardLinkedList.Node<E> x;

        if (isEmpty() || !contains(e))
            return false;

        for (x = this.head; x != null; x = x.next) {
            if (e.equals(x.item)) {
                this.unlink(x, previous);
            } else
                previous = x;
        }

        return true;
    }

    public E remove(int index) {
        this.checkElementIndex(index);

        if (index == 0) {
            E result = head.item;
            unlink(this.head, null);
            return result;
        } else
            return unlinkAfter(index);

    }

    public boolean contains(E item) {
        ForwardLinkedList.Node<E> temp = head;

        while (temp != null) {
            if (temp.item.equals(item))
                return true;
            temp = temp.next;
        }
        return false;

    }

    @Override
    public E get(int index) {
        this.checkElementIndex(index);
        return this.node(index).item;
    }

    public void clear() {
        ForwardLinkedList.Node<E> next;
        ForwardLinkedList.Node<E> x;

        for (x = this.head; x != null; x = next) {
            next = x.next;
            x.item = null;
            x.next = null;
        }

        this.size = 0;
        ++this.modCount;
    }

    public E set(int index, E element) {
        this.checkElementIndex(index);
        ForwardLinkedList.Node<E> x = this.node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public int indexOf(Object o) {
        int index = 0;
        ForwardLinkedList.Node<E> x;

        for (x = this.head; x != null; x = x.next) {
            if (o.equals(x.item)) {
                return index;
            }

            ++index;
        }


        return -1;
    }

    public E element() {
        return this.getFirst();
    }

    public E getLast() {
        ForwardLinkedList.Node<E> l = node(size - 1);
        if (l == null) {
            throw new NoSuchElementException();
        } else {
            return l.item;
        }
    }

    public E getFirst() {
        ForwardLinkedList.Node<E> f = this.head;
        if (f == null) {
            throw new NoSuchElementException();
        } else {
            return f.item;
        }
    }

    public E peek() {
        ForwardLinkedList.Node<E> f = this.head;
        return f == null ? null : f.item;
    }

    public E poll() {
        ForwardLinkedList.Node<E> f = this.head;
        return f == null ? null : this.unlink(this.head, null);
    }

    public boolean offer(E e) {
        return this.add(e);
    }

    public boolean offerFirst(E e) {
        this.add(0,e);
        return true;
    }

    public boolean offerLast(E e) {
        this.add(size - 1,e);
        return true;
    }

    public E peekFirst() {
        ForwardLinkedList.Node<E> f = this.head;
        return f == null ? null : f.item;
    }

    public E peekLast() {
        ForwardLinkedList.Node<E> l = this.node(size -1);
        return l == null ? null : l.item;
    }

    public E pollFirst() {
        ForwardLinkedList.Node<E> f = this.node(size -1);
        return f == null ? null : this.unlink(this.head, null);
    }

    public E pollLast() {
        ForwardLinkedList.Node<E> l = this.node(size -1);
        return l == null ? null : this.unlink(node(size -1), node(size - 2));
    }

    public void push(E e) {
        this.add(0,e);
    }

    public E pop() {
        return this.unlink(this.head, null);
    }

    private ForwardLinkedList.Node<E> node(int index) {
        ForwardLinkedList.Node<E> x = this.head;
        int i = 0;
        while (i != index) {
            x = x.next;
            i++;
        }
        return x;
    }

    private void checkElementIndex(int index) {
        if (!this.isElementIndex(index)) {
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < this.size;
    }


    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }

    private void checkPosition(int index) {
        if (!this.isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= this.size;
    }

    private static class Node<E> {
        E item;
        ForwardLinkedList.Node<E> next;

        public Node(E item, ForwardLinkedList.Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();

        ForwardLinkedList.Node<E> temp = head;

        strB.append("[");
        while (temp != null) {
            if (temp.next != null)
                strB.append(temp.item).append(", ");
            else
                strB.append(temp.item);
            temp = temp.next;
        }

        strB.append("]");

        return strB.toString();
    }
}
