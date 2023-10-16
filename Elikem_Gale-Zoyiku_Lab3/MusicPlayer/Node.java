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

    public boolean equals(Node<T> other) {
        return this.data.equals(other.data);
    }
}