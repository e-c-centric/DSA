package RestaurantReservationSystem;

/**
 * The Entry class represents a key-value pair. The key and value can be any
 * type. The key is immutable, but the value can be changed.
 * 
 * The Entry class does not contain
 * any methods or operations that depend on the size of the input. The time
 * complexity of all the methods in this class are constant, or O(1). This
 * means that the time taken and memory used by the class does not increase with
 * the size of the input. The constructor and getter/setter methods all perform
 * simple assignments or return statements, which have a constant time and space
 * complexity. The toString method also has a constant time complexity because
 * it concatenates the string representations of the key and value, without
 * performing any operations that depend on the size of the input.
 */
public class Entry<K, V> {
    private final K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}
