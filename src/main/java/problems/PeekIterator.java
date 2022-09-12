package problems;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekIterator<E> {
    private Iterator<E> it;
    private boolean inPeek = false;
    private E peekVal = null;

    public PeekIterator(Iterator<E> it) {
        this.it = it;
    }

    public E peek() {
        if (inPeek) {
            return peekVal;
        }
        inPeek = true;
        if (it.hasNext()) {
            peekVal = it.next();
            return peekVal;
        }
        inPeek = false;
        peekVal = null;
        throw new NoSuchElementException("Can't peek");
    }

    public boolean hasNext() {
        if (inPeek) {
            return true;
        }
        return it.hasNext();
    }

    public E next() {
        if (inPeek) {
            inPeek = false;
            return peekVal;
        }
        inPeek = false;
        return it.next();
    }
}
