/**
 * This package contains the classes that implement the CacheStorage application.
 * The CacheStorage application is a simple cache storage system that stores
 * data in a doubly linked list.
 * It has a <code>CacheStorage</code> class that represents the cache storage
 * system.
 * It has a <code>DoublyLinkedList</code> class that represents a doubly linked
 * list.
 * It has a <code>Node</code> class that represents a node in a doubly linked
 * list.
 * In this application, a cache is implemented using a doubly linked list. The
 * cache has a maximum size set during instantiation. When the cache is full and
 * a new item is to be added, the least recently used item is removed from the
 * cache. The cache is implemented using a doubly linked list because it allows
 * for constant time insertion and deletion at the head and tail of the list.
 * 
 * @see Node
 * @see DoublyLinkedList
 * @see CacheStorage
 * 
 * @author Elikem Asudo Gale-Zoyiku
 */
package CacheStorage;
