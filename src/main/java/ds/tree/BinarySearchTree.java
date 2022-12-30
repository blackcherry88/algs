package ds.tree;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>> {
    private final Node<T> root;

    private BinarySearchTree(final Node<T> root) {
        this.root = root;
    }

    public static <E extends Comparable<E>> BinarySearchTree<E> of(E... values) {
        if (values.length <= 0) {
            throw new IllegalArgumentException("Of function get empty elements");
        }
        Node<E> root = new Node<>(values[0]);
        BinarySearchTree<E> tree = new BinarySearchTree<>(root);
        for(int i = 1; i < values.length; ++i) {
            tree.insert(values[i]);
        }
        return tree;
    }

    public void insert(T value) {
        _insert(root, value);
    }

    private void _insert(Node<T> p, T value) {
        if (value == null || p == null) {
            return;
        }
        int c = value.compareTo(p.getData());
        if (c == 0) {
            return;
        }

        if (c < 0) {
            if (p.getLeft() == null) {
                p.setLeft(new Node<>(value));
            } else {
                _insert(p.getLeft(), value);
            }
        } else {
            if (p.getRight() == null) {
                p.setRight(new Node<>(value));
            } else {
                _insert(p.getRight(), value);
            }
        }
    }


    private static class InOrderIterator<T extends Comparable<T>> implements Iterator<T> {
        private final Deque<Node<T>> stack;
        private Node<T> nextValue;
        protected InOrderIterator(Node<T> root) {
            stack = new LinkedList<>();
            nextValue = _findNext(root);
        }

        private Node<T> _findNext(Node<T> p) {
            while (p != null) {
                stack.push(p);
                p = p.getLeft();
            }
            return stack.isEmpty()? null : stack.pollLast();
        }

        @Override
        public boolean hasNext() {
            return nextValue != null;
        }

        @Override
        public T next() {
            if (nextValue == null)
                return null;

            T ret = nextValue.getData();
            nextValue = _findNext(nextValue.getRight());
            return ret;
        }
    }



}
