/*
∗ @file: BST.java
∗ @description: This program implements a generic BST class with comparable interface.
∗ @author: Julia Jiang
∗ @date: January 22, 2024
∗ @acknowledgement : worked by myself
 */

import java.util.*;
import java.util.Iterator;

public class BST <E extends Comparable <E>> implements Iterable<Node<E>> {
    //attributes:
    private Node<E> root;
    private int nodecount;

    // Implement the constructor:
    BST() {
        root = null;
        nodecount = 0;
    }

    //Implement the clear method: This method resets its root to null and clears its nodecounts. It returns nothing.
    void clear() {
        root = null;
        nodecount = 0;
    }

    //Implement the size method: This method returns the nodecounts in the BST.
    int size() {
        return nodecount;
    }

    //Implement the insert method:
    //Given a node root and a value "v" of any data type, this method inserts "v" into the given root and returns nothing.
    public void insert(E v) {
        this.root = insertH(this.root, v);
        nodecount++;
    }

    //this is the helper method
    private Node<E> insertH(Node<E> root, E v) {
        if (root == null) return new Node<E>(v);
        if (root.getElement().compareTo(v) < 0) root.setRight(insertH(root.getRight(), v));
        if (root.getElement().compareTo(v) > 0) root.setLeft(insertH(root.getLeft(), v));
        if (root.getElement().compareTo(v) == 0) root.setLeft(insertH(root.getLeft(), v));
        return root;
    }

    /*
        Implement the remove method:
        This method takes 1 parameter: a key value of any data type.
        This method first searches the key value in the given root and deletes the node with key value from the root if found.
        This method returns a Node.

     */

    public E remove(E key) {
        E temp = findH(root, key);
        if (temp != null) {
            root = removeH(root, key);
            nodecount--;
        }
        return temp;
    }

    //This is the helper method for remove.
    private Node<E> getMax(Node<E> root) {
        if (root.getRight() == null) return root;
        return getMax(root.getRight());
    }

    //This is the helper method for remove.
    private Node<E> deleteMax(Node<E> root) {
        if (root.getRight() == null) return root.getRight();
        root.setRight(deleteMax(root.getRight()));
        return root;
    }

    //This is the helper method for remove.
    private Node<E> removeH(Node<E> root, E key) {
        if (root == null) return null;
        if (root.getElement().compareTo(key) < 0) root.setRight(removeH(root.getRight(), key));
        else if (root.getElement().compareTo(key) > 0) root.setLeft(removeH(root.getLeft(), key));
        else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            } else {
                Node<E> max = getMax(root.getLeft());
                root.setElement(max.getElement());
                root.setLeft(deleteMax(root.getLeft()));
            }
        }
        return root;
    }

    /*
    Implement the find method:
    This method takes a value key of any data type as parameter.
    It returns the record with the value key if the key exists and null if it doesn't.
     */

    public E find(E key) {
        return findH(root, key);
    }

    //helper method:
    private E findH(Node<E> r, E key) {
        if (r == null) return null;
        if (r.getElement().compareTo(key) < 0) return findH(r.getRight(), key);
        else if (r.getElement().compareTo(key) > 0) return findH(r.getLeft(), key);
        else {
            return key;
        }
    }

    //This constructor implements the iterator method.
    Stack nodestack = new Stack<Node<E>>();
    @Override
    public Iterator<Node<E>> iterator() {
        moveLeft(root);
        return new BSTIterator();
    }

    //Given a node current, this method moves the node to its leftest node.
    private void moveLeft(Node<E> current) {

        while (current != null) {
            nodestack.push(current);
            current = current.getLeft();
        }
    }
    // Implement the BSTIterator class:
    private class BSTIterator implements Iterator<Node<E>> {
        //This method checks if there is a next node in the given BST.
        @Override
        public boolean hasNext() {
                return !nodestack.isEmpty();
            }
        @Override
        //This method returns the next Node in the given BST and returns null if there is no next Node.
        public Node<E> next() {
            if (!hasNext()) throw new NoSuchElementException("Empty tree!");
            Node<E> current = (Node<E>) nodestack.pop();
            if (current.getRight() != null) {
                    moveLeft(current.getRight());
            }
            return current;
        }
    }
}

