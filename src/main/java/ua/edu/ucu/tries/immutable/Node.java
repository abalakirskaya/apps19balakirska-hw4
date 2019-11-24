package ua.edu.ucu.tries.immutable;

final class Node  {
    public Object key;
    public Node next;
    public Node prev;

    public Node(){
        key = null;
        next = null;
        prev = null;
    }

    public Object getValue() {
        return key;
    }

    public void setValue(Object key) {
        this.key = key;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {

        return prev;
    }

    public void setPrevious(Node prev) {
        this.prev = prev;
    }


    Node(Object key) {
        this.key = key;
    }

    Node(Node node) {
        key = node.key;
        next = node.next;
        prev = node.prev;
    }

}