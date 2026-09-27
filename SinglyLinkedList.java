import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size < 2) {
            return;
        }

        // Keep the original positions while sorting a second view of the nodes.
        // A node at rank i is exchanged with the node at rank size - 1 - i.
        List<Node<E>> originalOrder = new ArrayList<>(size);
        Node<E> current = head;
        while (current != null) {
            originalOrder.add(current);
            current = current.getNext();
        }

        List<Node<E>> sortedNodes = new ArrayList<>(originalOrder);
        sortedNodes.sort((firstNode, secondNode) ->
            firstNode.getElement().compareTo(secondNode.getElement()));

        Map<Node<E>, Node<E>> swappedNode = new IdentityHashMap<>();
        for (int i = 0; i < size; i++) {
            swappedNode.put(sortedNodes.get(i), sortedNodes.get(size - 1 - i));
        }

        head = swappedNode.get(originalOrder.get(0));
        current = head;
        for (int i = 1; i < size; i++) {
            Node<E> next = swappedNode.get(originalOrder.get(i));
            current.setNext(next);
            current = next;
        }

        tail = current;
        tail.setNext(null);

    }
   
}

