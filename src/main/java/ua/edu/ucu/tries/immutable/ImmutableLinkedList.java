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
    public Object get(int index) {
        size_checking(index);
        Node node = new Node(head.key, head.next);
        int count = 0;
        while(count < index){
            node = node.next;
            count++;
        }
        return node.key;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        size_checking(index);
        ImmutableLinkedList new_list = new ImmutableLinkedList();
        new_list.head = head;
        new_list.tail = tail;
        new_list.size = size-1;
        if (index==0){
            new_list.head = new_list.head.next;
            return new_list;
        }
        else {
            Node new_head = head;
            new_list.head = new Node(new_head.key, new_head.next);
            Node node = new_list.head;
            new_head = new_head.next;
            int counter = 0;
            while (counter < index - 1) {
                node.next = new Node(new_head.key, new_head.next);
                node = node.next;
                new_head = new_head.next;
                counter += 1;
            }
            new_head = new_head.next;
            while (new_head != null) {
                node.next = new Node(new_head.key, new_head.next);
                node = node.next;
                new_head = new_head.next;
            }
            return new_list;
        }
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        size_checking(index);
        ImmutableLinkedList new_list = new ImmutableLinkedList();
        new_list.head = head;
        new_list.tail = tail;
        new_list.size = size;
        return (ImmutableLinkedList) new_list.remove(index).add(index, e);
    }
    @Override
    public ImmutableLinkedList add(Object e) {
        return add(this.size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        size_checking(index);
        ImmutableLinkedList new_list = new ImmutableLinkedList();
        new_list.head = head;
        new_list.tail = tail;
        new_list.size = size+1;
        if (index==0){
            Node new_node = new Node(e, new_list.head);
            new_list.head = new_node;
        }
        else{
            Node new_head = new Node(head.key, head.next);
            new_list.head = new_head;
            Node node = new_list.head;
            int count = 0;
            while(count < index - 1){
                node = node.next;
                count++;
            }
            Node next = node.next;
            node.next = new Node(e, next);
        }
        return new_list;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        size_checking(index);
        ImmutableLinkedList new_list = new ImmutableLinkedList();
        ImmutableLinkedList list2;
        new_list.head = head;
        new_list.tail = tail;
        new_list.size = size;
        list2 = new_list;
        for (int i = 0; i < c.length; i++) {
            list2 = list2.add(index, c[i]);
            index++;
        }
        return list2;
    }

    @Override
    public int indexOf(Object e) {
        Node node;
        node = head;
        int count = 0;
        while(count < size){
            if (node.key.equals(e)) {
                return count;
            }
            node = node.next;
            count++;
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


    public ImmutableLinkedList addLast(Object e) {

        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {

        return head.key;
    }


    public ImmutableLinkedList removeFirst() {

        return (ImmutableLinkedList) remove(0);
    }


    private void size_checking(int index) throws IndexOutOfBoundsException {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] NewImmutableLinkedList = new Object[size];
        Node node = head;
        int count = 0;
        while (count < size){
            NewImmutableLinkedList[count] = node.key;
            node = node.next;
            count++;
        }
        return NewImmutableLinkedList;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
