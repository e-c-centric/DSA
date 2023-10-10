import MusicPlayer.DoublyLinkedList;
import MusicPlayer.Node;

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
    public String toString() {
        return data.toString();
    }
}

/**
 * The <code>DoublyLinkedList</code> class represents a doubly linked list.
 * It has a <code>size</code> field that stores the size of the list.
 * It also has a <code>head</code> field that stores the reference to the head
 * node.
 * It also has a <code>tail</code> field that stores the reference to the tail
 * node.
 * It has a <code>insert()</code> method that inserts a node at a given
 * position.
 * It has a <code>delete()</code> method that deletes a node at a given
 * position.
 * 
 * @param <T> The type of data stored in the nodes of the list..
 * @see <code>Node</code> class
 * @author Elikem Asudo Gale-Zoyiku
 */
class DoublyLinkedList<T extends Node<?>> {
    int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * The <code>DoublyLinkedList</code> constructor initializes the
     * <code>head</code> and <code>tail</code> fields to null.
     * 
     * @param None
     * @return A new <code>DoublyLinkedList</code> object.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * The <code>insert</code> method inserts a node at a given position.
     * 
     * @param data The song to be stored in the node.
     * @param pos  The position at which the node is to be inserted.
     * @return None
     */
    public void insert(T data, int pos) {
        Node<T> newNode = new Node<T>(data);
        if (pos == 1) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                head.prev = newNode;
                newNode.next = head;
                newNode.prev = null;
                head = newNode;
            }
            size++;
        } else if (pos == size + 1) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
            newNode.next = null;
            this.tail = newNode;
            size++;
        } else {
            Node<T> current = head;
            int count = 1;
            while (count < (pos - 1)) {
                current = current.next;
                count++;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }

    /**
     * The <code>delete</code> method deletes a song at a given position.
     * 
     * @param pos The position of the node to be deleted.
     * @return None
     */
    public void delete(int pos) {
        if (head == null) {
            return;
        } else if (pos == 1) {
            head = head.next;
            head.prev = null;
            size--;
        } else if (pos == size + 1) {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.prev.next = null;
            current.prev = current.prev.prev;
            size--;
        } else {
            Node<T> current = head;
            int count = 1;
            while (count < (pos - 1)) {
                current = current.next;
                count++;
            }
            current.next = current.next.next;
            current.next.prev = current;
            size--;
        }
    }

    /**
     * The <code>getNode</code> method returns the node at a given position.
     * 
     * @param pos The position of the node to be returned.
     * @return The node at the given position.
     */
    public Node<T> getNode(int pos) {
        Node<T> current = head;
        int count = 1;
        while (count < pos) {
            current = current.next;
            count++;
        }
        return current;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setHead(Node<T> head) {
        this.insert(head.data, 1);
    }

    public void setTail(Node<T> tail) {
        this.insert(tail.data, size + 1);
    }

    /**
     * The <code>CacheStorage</code> class represents a cache that stores a fixed
     * number of items.
     * It uses a doubly linked list to store the items.
     * It has a <code>size</code> field that stores the size of the cache.
     * It also has a <code>capacity</code> field that stores the maximum number of
     * items
     * that can be stored in the cache.
     * It also has a <code>cache</code> field that stores the reference to the head
     * node of the doubly linked list.
     * It has a <code>addItem()</code> method that adds an item to the cache.
     * It has a <code>getItem()</code> method that gets an item from the cache.
     * It has a <code>evictLeastRecentlyUsedItem()</code> method that evicts the
     * least recently used item from the cache.
     * 
     * @param <T> The type of data stored in the nodes of the list..
     * @see <code>DoublyLinkedList</code> class
     * @author Elikem Asudo Gale-Zoyiku
     */
    public class CacheStorage<T extends Node<?>> {
        int size = 0;
        private int capacity;
        private DoublyLinkedList<T> cache;

        /**
         * The <code>CacheStorage</code> constructor initializes the
         * <code>cache</code> field to an empty doubly linked list.
         * 
         * @param capacity The maximum number of items that can be stored in the cache.
         * @return A new <code>CacheStorage</code> object.
         */
        public CacheStorage(int capacity) {
            this.capacity = capacity;
            this.cache = new DoublyLinkedList<T>();
        }

        /**
         * The <code>addItem</code> method adds an item to the cache.
         * 
         * @param data The item to be stored in the cache.
         * @return None
         */
        public void addItem(T data) {
            if (size == capacity) {
                evictLeastRecentlyUsedItem();
            }
            cache.insert(data, 1);
            size++;
        }

        /**
         * The <code>getItem</code> method gets an item from the cache.
         * 
         * @param data The item to be retrieved from the cache.
         * @return The retrieved item or null if the item is not found in the cache.
         */
        public T getItem(T data) {
            Node<T> current = cache.getHead();
            while (current != null) {
                if (current.data.equals(data)) {
                    if (current != cache.getHead()) {
                        current.prev.next = current.next;
                        if (current.next != null) {
                            current.next.prev = current.prev;
                        } else {
                            cache.setTail(current.prev);
                        }
                        current.next = cache.getHead();
                        current.prev = null;
                        cache.getHead().prev = current;
                        cache.setHead(current);
                    }
                    return current.data;
                }
                current = current.next;
            }
            return null;
        }

        /**
         * The <code>evictLeastRecentlyUsedItem</code> method evicts the least recently
         * used item from the cache.
         * 
         * @param None
         * @return None
         */
        public void evictLeastRecentlyUsedItem() {
            Node<T> tail = cache.getTail();
            if (tail != null) {
                if (tail.prev != null) {
                    tail.prev.next = null;
                    cache.setTail(tail.prev);
                } else {
                    cache.setHead(null);
                    cache.setTail(null);
                }
                size--;
            }
        }
    }

public static void main(String[] args) {}}
