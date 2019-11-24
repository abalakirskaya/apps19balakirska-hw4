package ua.edu.ucu.tries.immutable;

public class Node {
    public Object key;
    public Node next;

    public Node(Object key, Node next){
        this.key = key;
        this.next = next;
    }

}
