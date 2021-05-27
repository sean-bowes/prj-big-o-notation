package com.koisoftware.linkedlists;

import java.util.Comparator;

public interface List<E> {
    void add(E element, int offset);

    void addFirst(E element);

    void addLast(E element);

    E removeFirst();

    E removeLast();

    E remove(int offset);

    void remove(E element, Comparator<E> comparator);

    boolean search(E element);

    E get(int offset);

    E getFirst();

    E getLast();

    int size();

    void reverse();
}
