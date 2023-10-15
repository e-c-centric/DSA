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
public class CacheStorage<T> {
    private int capacity;
    private DoublyLinkedList<Node<T>> cache;

    /**
     * The <code>CacheStorage</code> constructor initializes the
     * <code>cache</code> field to an empty doubly linked list.
     * 
     * @param capacity The maximum number of items that can be stored in the cache.
     * @return A new <code>CacheStorage</code> object.
     */
    public CacheStorage(int capacity) {
        this.capacity = capacity;
        this.cache = new DoublyLinkedList<>();
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
        if (cache.size >= capacity) {
            evictLeastRecentlyUsedItem();
        }
        Node<T> node = new Node<>(data); // Create a Node for the data
        cache.insert(node, 1);
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
    /**
     * The <code>getItem</code> method gets an item from the cache.
     * If the item is found in the cache, it is moved to the head of the cache.
     *
     * @param The item to be retrieved from the cache.
     * @return The retrieved item or null if the item is not found in the cache.
     */
    public T getItem(int position) {
        if (position < 1 || position > cache.size) {
            System.out.println("Invalid position. Please choose a valid position.");
            return null;
        }
        Node<T> selectedNode = cache.getNode(position).data;
        if (selectedNode != null) {
            // Move the selectedNode to the head of the list
            cache.deleteByElement(selectedNode);
            cache.insert(selectedNode, 1);
            return selectedNode.data;
        } else {
            System.out.println("Item not found at the selected position.");
            return null;
        }
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
    public T evictLeastRecentlyUsedItem() {
        if (cache.size == 0) {
            return null; // Cache is empty
        }

        Node<Node<T>> tail = cache.getTail();
        T evictedData = tail.data.data;
        cache.delete(cache.size); // Remove the tail node

        return evictedData;
    }

    public void display() {
        cache.display();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity of the cache: ");
        int capacity = scanner.nextInt();
        CacheStorage<String> cache = new CacheStorage<>(capacity);

        while (true) {
            System.out.println("\nCache Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Get Item");
            System.out.println("3. Evict Least Recently Used Item");
            System.out.println("4. Display Cache");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            System.out.println("\n");

            switch (choice) {
                case 1:
                    System.out.print("Enter item to add: ");
                    String item = scanner.next();
                    cache.addItem(item);
                    System.out.println("Item added to cache.");
                    System.out.println("Current cache contents:\n");
                    cache.display();
                    break;
                case 2:
                    System.out.println("Current cache contents:\n");
                    cache.display();
                    System.out.print("Enter the position of the item to retrieve: ");
                    int positionToRetrieve = scanner.nextInt();
                    String retrievedItem = cache.getItem(positionToRetrieve);
                    if (retrievedItem != null) {
                        System.out.println("Retrieved item: " + retrievedItem);
                    }
                    break;
                case 3:
                    String evictedItem = cache.evictLeastRecentlyUsedItem();
                    if (evictedItem != null) {
                        System.out.println("Evicted item: " + evictedItem);
                    } else {
                        System.out.println("Cache is empty.");
                    }
                    break;
                case 4:
                    System.out.println("Cache contents:");
                    cache.display();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}