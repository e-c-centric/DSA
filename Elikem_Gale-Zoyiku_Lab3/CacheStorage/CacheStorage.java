package CacheStorage;

import java.util.Scanner;

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
 * @param <T> The type of data stored in the cache.
 * @see DoublyLinkedList
 * @see Node
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
     * If the cache is full, the least recently used item is evicted from the cache.
     * 
     * @see DoublyLinkedList#insert(Node, int)
     * @see CacheStorage#evictLeastRecentlyUsedItem()
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
     * If the item is found in the cache, it is moved to the head of the cache.
     * 
     * @see DoublyLinkedList#getHead(void)
     * @see DoublyLinkedList#setHead(Node)
     * @see DoublyLinkedList#setTail(Node)
     * 
     * @param data The item to be retrieved from the cache.
     * @return The retrieved item or null if the item is not found in the cache.
     */
    public T getItem(T data) {
        Node<T> current = cache.getHead();
        while (current != null) {
            // assumption is that the data type in the cache has an equals method
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
     * @see DoublyLinkedList#getTail(void)
     * @see DoublyLinkedList#setTail(Node)
     * @see DoublyLinkedList#setHead(void)
     * 
     * @param None
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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of the cache: ");
        int capacity = scanner.nextInt();
        CacheStorage<Node<Object>> cache = new CacheStorage<>(capacity);

        while (true) {
            System.out.println("\nCache Operations:");
            System.out.println("1. Add an item to the cache");
            System.out.println("2. Get an item from the cache");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter an item to add to the cache: ");
                    Node<Object> itemToAdd = new Node<Object>(scanner.next());
                    cache.addItem(itemToAdd);
                    System.out.println("Item added to the cache.");
                    break;

                case 2:
                    System.out.print("Enter an item to get from the cache: ");
                    Node<Object> itemToGet = new Node<Object>(scanner.next());
                    Object result = cache.getItem(itemToGet);
                    if (result != null) {
                        System.out.println("Item found in the cache: " + result);
                    } else {
                        System.out.println("Item not found in the cache.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }
}