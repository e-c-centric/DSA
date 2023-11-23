package CacheStorage;

/**
 * The <code>Node</code> class represents a node in a doubly linked list.
 * It has a <code>data</code> field that stores the data of the node.
 * It also has a <code>next</code> field that stores the reference to the next
 * node.
 * It also has a <code>prev</code> field that stores the reference to the
 * previous node.
 * It has a <code>toString()</code> method that returns the data of the node.
 * 
 * @param <T> The type of data stored in the node.
 * @author Elikem Asudo Gale-Zoyiku
 */
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    /**
     * The <code>Node</code> constructor takes in data and initializes the
     * <code>data</code> field with the given data.
     * 
     * @param data The data to be stored in the node.
     * @return A new <code>Node</code> object.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * The <code>toString</code> method returns the data of the node.
     * This method invokes the <code>toString</code> method of the type of data,
     * that is <code>T.toString()</code>.
     * 
     * @param None
     * @return The data of the node.
     */
    @Override
    public String toString() {
        return data.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node<T> other = (Node<T>) obj;
        if (other.data instanceof T && this.data instanceof T) {
            T song = (T) other.data;
            T thisSong = (T) this.data;
            return thisSong.equals(song);
        }
        return false;
    }
}