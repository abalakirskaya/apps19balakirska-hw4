package ua.edu.ucu.tries.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int size;

    public ImmutableLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    public ImmutableLinkedList(Object[] arr){
        Node [] elements = make_nodes(arr);
        head = elements[0];
        tail = elements[elements.length - 1];
        size = elements.length;
    }


    public ImmutableLinkedList(ImmutableLinkedList linkedList) {
        if (linkedList.size == 1) {
            if (linkedList.head != null) {
                Node node = new Node(linkedList.head.key);
                head = node;
            }
            else{
                Node node = new Node();
                head = node;
            }
        } else {
            head = new Node(linkedList.head);
            Node previousNode = head;
            Node currentNode = linkedList.head.getNext();
            Node newNode = null;
            while (currentNode.getNext() != null) {
                newNode = new Node(currentNode);
                previousNode.setNext(newNode);
                newNode.setPrevious(previousNode);
                previousNode = newNode;
                currentNode = currentNode.getNext();
            }
            tail = new Node(currentNode);
            tail.setPrevious(previousNode);
        }
    }


    private Node[] make_nodes(Object[] arr){
        Node[] elements = new Node[arr.length];
        for (int i = 0; i < arr.length; i++){
            elements[i] = new Node(arr[i]);
            if (i - 1 >= 0){
                elements[i - 1].setNext(elements[i]);
            }
        }
        return elements;
    }

    public Object getFirst(){
        if (head != null){
            return head.key;
        } else{
            return null;
        }
    }

    public Object getLast(){
        if (tail != null){
            return tail.key;
        } else{
            return null;
        }
    }

    public ImmutableLinkedList addFirst(Object n){
        return (ImmutableLinkedList) add(0, n);
    }

    public ImmutableLinkedList addLast(Object n){
        return (ImmutableLinkedList) add(n);
    }

    public ImmutableLinkedList removeFirst(){
        ImmutableLinkedList linked_lst = new ImmutableLinkedList(this);
        linked_lst.head = linked_lst.head.next;
        linked_lst.head.prev = null;
        linked_lst.size--;
        return linked_lst;
    }


    public ImmutableLinkedList removeLast(){
        ImmutableLinkedList linked_lst = new ImmutableLinkedList(this);
        linked_lst.tail = linked_lst.tail.prev;
        linked_lst.tail.next = null;
        linked_lst.size--;
        return linked_lst;
    }

    @Override
    public ImmutableLinkedList remove(int i){
        if (i < this.size){
            ImmutableLinkedList linked_list = new ImmutableLinkedList(this);
            Node our_node = linked_list.getNode(i+1, this.size);
            Node prev = our_node.getPrevious();
            Node next = our_node.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            linked_list.size--;
            return linked_list;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList add(Object n){
        return add(size(), n);
    }

    @Override
    public ImmutableLinkedList add(int i, Object n){
        Object[] arr = new Object[1];
        arr[0] = n;
        return addAll(i, arr);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] n){
        return addAll(size(), n);
    }


    @Override
    public ImmutableLinkedList addAll(int i, Object[] n){
        if (this.size == 0) {
            ImmutableLinkedList linkedList = new ImmutableLinkedList(n);
            return linkedList;
        }
        if (i <= this.size){
            ImmutableLinkedList linked_list = new ImmutableLinkedList(this);
            linked_list.size += this.size;
            Node[] elements = new Node[n.length];
            for (int k = 0; k < n.length; k++){
                elements[k] = new Node(n[k]);
                if (k - 1 >=0){
                    elements[k - 1].next = elements[i];
                    if (elements[i] != null) {
                        elements[i].prev = elements[k - 1];
                    }
                }
            }
            int k = 0;
            Node oldNode = linked_list.head;
            while (k != i - 1){
                if (k == linked_list.size-1){
                    oldNode.next = new Node();
                }else {
                    if (oldNode.next != null) {
                        oldNode = oldNode.next;
                    }
                }
                k++;
            }
            Node nextNode = oldNode.next;
            for(int j = 0; j < elements.length; j++){
                oldNode.next = elements[j];
                oldNode = oldNode.next;
            }
            oldNode.next = nextNode;
            Node previous = linked_list.head;
            if (i + n.length < linked_list.size){
                Node result = linked_list.getNode(i + n.length , this.size);
                result.prev = elements[elements.length - 1];
            }
            if (i != 0){
                Node first = linked_list.getNode(i, this.size);
                first.next = elements[0];
                elements[0].prev = first;

            } else{
                elements[elements.length - 1].next = linked_list.head;
            }

            linked_list.size += n.length;



            Node curr = linked_list.head;
            for(int y = 0; y < linked_list.size; y++){
                curr = curr.next;



            }

            return (ImmutableLinkedList) linked_list;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableLinkedList set(int i, Object n){
        if (i >= size()){
            throw new IndexOutOfBoundsException();
        } else{
            ImmutableLinkedList linked_list = new ImmutableLinkedList(this);
            Node our_node = linked_list.getNode(i, this.size);
            our_node.key = n;
            return (ImmutableLinkedList) linked_list;
        }
    }

    @Override
    public Object get(int i){
        Node our_node = new Node();
        if (this.head != null) {
            our_node = this.head;
        }
        if (i >= this.size()){
            throw new IndexOutOfBoundsException();
        } else{
            int curr = 0;
            while (i != curr){
                our_node = our_node.next;
                curr++;
            }
            return our_node.key;

        }
    }

    private Node getNode(int i, int size){
        if (i > size){
            throw new IndexOutOfBoundsException();
        } else{
            int curr = 0;
            Node our_node;
            if (this.head != null) {
                our_node = new Node(this.head);
            } else{
                our_node = new Node();
            }
            while(i-1 != curr){
                if (our_node.next != null) {
                    our_node = our_node.next;
                }
                curr++;
            }

            return our_node;
        }
    }
    @Override
    public int indexOf(Object n){
        int i = 0;
        Node our_node = this.head;
        while( this.size() != i){
            if(our_node.key.equals(n)){
                return i;
            } else{
                i++;
                our_node = our_node.next;
            }
        }
        if(i == this.size()){
            i = -1;
        }
        return i;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public ImmutableLinkedList clear(){
        return (ImmutableLinkedList) new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public Object[] toArray(){
        Node our_node = this.head;
        int counter = 0;
        while(our_node != null){
            our_node = our_node.next;
            counter++;
        }
        our_node = this.head;
        Object[] arr = new Object[counter];
        for (int i = 0; i < counter; i++){
            arr[i] = our_node.key;
            our_node = our_node.next;
        }
        return arr;
    }
}
