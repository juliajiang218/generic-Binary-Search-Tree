/*
∗ @file: Node.java
∗ @description: This program implements a generic Node class with a comparable interface.
∗ @author: Julia Jiang
∗ @date: January 22, 2024
∗ @acknowledgement : worked by myself
 */

public class Node<E extends Comparable<E>>{
    //attributes:
    private E val;
    private Node<E> left;
    private Node<E> right;

    // Implement the constructor: there are three possible implementation of the Node constructor.
    Node(){val = null; left=right=null; }
    Node(E data){val = data; left = right = null;}
    Node(E data, Node<E> l, Node<E> r){ val = data; left = l; right = r; }

    // Implement the setElement method: Given an "e" value of any data type, this method sets the data value of the root as "e".
    void setElement(E e){ val = e;}

    // Implement the setLeft method:
    // Given a Node "l" with any data type, this method returns nothing and references its left Node to "l".
    void setLeft(Node<E> l) { left = l;};

    // Implement the setRight method:
    // Given a Node "r" with any data type, this method returns nothing and references its right Node to "r".
    void setRight(Node<E> r) { right = r; };

    // Implement the getLeft method: This method returns the left Node of its current Node.
    Node<E> getLeft(){ return left;}

    // Implement the getRight method: This method returns  the right Node of its current Node.
    Node<E> getRight(){ return right; }

    // Implement the getElement method: This method returns its current root value of any data type.
    E getElement(){ return val; }

    // Implement the isLeaf method: This method checks if its current Node is a leaf node and returns a boolean value.
    boolean isLeaf(){
        if ((left == null) && (right == null)) return true;
        return false;
    }
}