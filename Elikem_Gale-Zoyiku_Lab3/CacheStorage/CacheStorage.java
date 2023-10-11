package CacheStorage;
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
 * @see DoublyLinkedList 
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


    public static void main(String[] args) {
        CacheStorage<Node<Integer>> cache = new CacheStorage<>(3);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);

        cache.addItem(node1);
        cache.addItem(node2);
        cache.addItem(node3);

        System.out.println(cache.getItem(node1)); 
        System.out.println(cache.getItem(node2)); 

        cache.addItem(node4);

        System.out.println(cache.getItem(node1)); 
        System.out.println(cache.getItem(node2)); 
        System.out.println(cache.getItem(node3)); 
        System.out.println(cache.getItem(node4)); 
    }
}
