/**
 * The MusicPlayer package contains classes for creating a simple music player.
 */
package MusicPlayer;

/**
 * This class represents a node in a doubly linked list.
 * Each node contains a piece of data of type T, and references to the previous and next nodes in the list.
 *
 * @param <T> the type of data stored in the node
 * @see DoublyLinkedList
 * 
 * @author Elikem Asudo GAle-Zoyiku
 */
public class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    /**
     * Constructs a new node with the specified data.
     *
     * @param data the data to be stored in the node
     */
    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
/** 
 * The equals method compares two nodes to see if they are equal
 * @param other The node to be compared to
 * @return boolean
 * @see Song#equalsSong(Node)
 */
@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node<T> other = (Node<T>) obj;
        if (other.data instanceof Song && this.data instanceof Song) {
            Song song = (Song) other.data;
            Song thisSong = (Song) this.data;
            return thisSong.equalsSong(song);
        }
        return false;
    }
}