package ua.edu.ucu.tries;

import ua.edu.ucu.tries.immutable.ImmutableLinkedList;
import ua.edu.ucu.tries.immutable.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    public ImmutableLinkedList queue;

    public Queue(){
        queue= new ImmutableLinkedList();
    }

    public Object peek() {
        return queue.getFirst();
    }

    public Object dequeue() {
        if (queue.size == 0){
            throw new IndexOutOfBoundsException();
        }
        Object el = queue.getFirst();
        queue = queue.removeFirst();
        return el;
    }

    public void enqueue(Object e) {
        queue = queue.addLast(e);
    }

    public int size() {
        return queue.size;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = (Node) queue.getFirst();
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) current.key;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }

}
