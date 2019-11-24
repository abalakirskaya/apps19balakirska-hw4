package ua.edu.ucu.tries.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    public int size;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    @Override
    public ImmutableLinkedList add(Object e) {
        return add(this.size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        size_checking(index);
        ImmutableLinkedList NewImmutableLinkedList = new ImmutableLinkedList();
        NewImmutableLinkedList.head = head;
        NewImmutableLinkedList.tail = tail;
        NewImmutableLinkedList.size = size+1;
        if (index==0){
            Node newNode = new Node(e, NewImmutableLinkedList.head);
            NewImmutableLinkedList.head = newNode;
        }
        else{
            Node hd = new Node(head.key, head.next);
            NewImmutableLinkedList.head = hd;
            Node temp = NewImmutableLinkedList.head;
            for (int i = 0; i < index - 1; i++){
                temp = temp.next;
            }
            Node nextEl = temp.next;
            temp.next = new Node(e, nextEl);
        }
        return NewImmutableLinkedList;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        size_checking(index);
        ImmutableLinkedList TempImmutableLinkedList = new ImmutableLinkedList();
        ImmutableLinkedList NewImmutableLinkedList;
        TempImmutableLinkedList.head = head;
        TempImmutableLinkedList.tail = tail;
        TempImmutableLinkedList.size = size;
        NewImmutableLinkedList = TempImmutableLinkedList;
        for (int i = 0; i < c.length; i++) {
            NewImmutableLinkedList = NewImmutableLinkedList.add(index, c[i]);
            index++;
        }
        return NewImmutableLinkedList;
    }

    @Override
    public Object get(int index) {
        size_checking(index);
        Node temp = new Node(head.key, head.next);
        for (int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.key;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        size_checking(index);
        ImmutableLinkedList NewImmutableLinkedList = new ImmutableLinkedList();
        NewImmutableLinkedList.head = head;
        NewImmutableLinkedList.tail = tail;
        NewImmutableLinkedList.size = size-1;
        if (index==0){
            NewImmutableLinkedList.head = NewImmutableLinkedList.head.next;
            return NewImmutableLinkedList;
        }
        else {
            Node hd = head;
            NewImmutableLinkedList.head = new Node(hd.key, hd.next);
            Node change = NewImmutableLinkedList.head;
            hd = hd.next;
            int counter = 0;
            while (counter < index - 1) {
                change.next = new Node(hd.key, hd.next);
                change = change.next;
                hd = hd.next;
                counter += 1;
            }
            hd = hd.next;
            while (hd != null) {
                change.next = new Node(hd.key, hd.next);
                change = change.next;
                hd = hd.next;
            }
            return NewImmutableLinkedList;
        }
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        size_checking(index);
        ImmutableLinkedList NewImmutableLinkedList = new ImmutableLinkedList();
        NewImmutableLinkedList.head = head;
        NewImmutableLinkedList.tail = tail;
        NewImmutableLinkedList.size = size;
        return (ImmutableLinkedList) NewImmutableLinkedList.remove(index).add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        Node temp;
        temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.key.equals(e)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public ImmutableLinkedList clear() {

        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }



    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {

        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {

        return head.key;
    }

    public Object getLast() {
        return get(size()-1);
    }

    public ImmutableLinkedList removeFirst() {

        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(size - 1);
    }

    private void size_checking(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] NewImmutableLinkedList = new Object[size];
        Node temp = head;
        for (int i = 0; i < size; i++) {
            NewImmutableLinkedList[i] = temp.key;
            temp = temp.next;
        }
        return NewImmutableLinkedList;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
