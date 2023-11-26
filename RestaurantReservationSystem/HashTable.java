package RestaurantReservationSystem;

import java.util.Arrays;
import java.util.Random;

public class HashTable<K, V extends Entry<K,V>> {
    private V[] table;
    private int size;
    private int capacity;
    private final int prime;

    public HashTable(int initialCapacity) {
        capacity = initialCapacity;
        table = (V[]) new Entry[capacity];
        size = 0;
        this.prime = generateRandomPrime(10, 90);
    }

    private int entryHash(K key) {
        int hash = 0;

        for (char c : key.toString().toCharArray()) {
            hash = (prime * hash + c) & 0xFFFFFFFF;
        }

        return hash;
    }

    private int primaryHash(K key) {
        int hash = entryHash(key);
        return Math.abs(hash % capacity);
    }

    private int secondaryHash(K key) {
        int hash = entryHash(key);
        int step = 1 + (hash % (capacity - 1));
        return step;
    }

    public void insert(V entry) {
        if (size == capacity) {
            resizeTable();
        }

        K key = entry.getKey();
        int index = primaryHash(key);
        int step = secondaryHash(key);

        while (table[index] != null) {
            index = (index + step) % capacity;
        }

        table[index] = entry;
        size++;
    }

    public V search(K key) {
        int index = primaryHash(key);
        int step = secondaryHash(key);

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index];
            }
            index = (index + step) % capacity;
        }

        return null;
    }

    public void delete(K key) {
        int index = primaryHash(key);
        int step = secondaryHash(key);

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                size--;
                return;
            }
            index = (index + step) % capacity;
        }
    }

    private void resizeTable() {
        int newCapacity = getNextPrime(capacity * 2);
        if (newCapacity <= capacity) {
            throw new IllegalStateException("Cannot resize table. Maximum capacity reached.");
        }

        V[] newTable = (V[]) new Entry[newCapacity];

        for (V entry : table) {
            if (entry != null) {
                if (entry.getKey() == null) {
                    throw new IllegalArgumentException("Cannot insert Entry with null key.");
                }

                K key = (K) entry.getKey(); // Cast to K
                int index = primaryHash(key);
                int step = secondaryHash(key);

                while (newTable[index] != null) {
                    index = (index + step) % newCapacity;
                }

                newTable[index] = entry;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    private int generateRandomPrime(int min, int max) {
        Random random = new Random();

        int candidate;
        boolean isPrime;

        do {
            candidate = random.nextInt(max - min + 1) + min;
            isPrime = isPrime(candidate);
        } while (!isPrime);

        return candidate;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getNextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (V entry : table) {
            if (entry != null) {
                str.append(entry).append("\n");
            }
        }
        return str.toString();
    }

    public Entry<K,V>[] getAllEntries() {
        return table;
    }
}
