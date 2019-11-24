package ua.edu.ucu.tries.immutable;

public class Queue {
    private ImmutableLinkedList lst;

    public Queue() {
        lst = new ImmutableLinkedList();
    }

    public Object peek() {
        return lst.getFirst();
    }

    public Object dequeue() {
        Object n = lst.getFirst();
        lst = lst.removeFirst();
        return n;
    }

    public int size(){
        return lst.size();
    }

    public void enqueue(Object n) {
        lst = lst.addLast(n);
    }
}
