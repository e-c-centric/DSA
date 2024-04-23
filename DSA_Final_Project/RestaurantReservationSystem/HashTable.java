package RestaurantReservationSystem;

import java.util.Random;

/**
 * This class represents a generic hash table. It has a capacity, size, and an
 * array of
 * entries. The hash table uses double hashing to handle collisions. The hash
 * table uses the djb2 hash function to calculate the hash of the entry. The
 * hash table resizes itself when the size reaches the capacity.
 * 
 * The time complexity of the insert, search, and delete operations in the
 * HashTable class is O(1) on average. This is because the primaryHash and
 * secondaryHash methods calculate the index of the entry based on the key in
 * constant time.
 */
public class HashTable<K, V extends Entry<K, V>> {
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

    /**
     * This method calculates the hash of the entry using the djb2 hash function.
     * It iterates through the characters of the key and multiplies the hash by
     * a randomly generated and adds the character value to the hash. The hash is
     * then masked to 32 bits to prevent overflow. The hash is then returned.
     * 
     * The time complexity of this method is O(n) because it iterates through the
     * characters of the key.
     */
    private int entryHash(K key) {
        int hash = 0;

        for (char c : key.toString().toCharArray()) {
            hash = (prime * hash + c) & 0xFFFFFFFF;
        }

        return hash;
    }

    /**
     * This method calculates the index of the entry in the hash table using the
     * primary hash function. The primary hash function is the remainder of the
     * entry hash divided by the capacity of the table.
     * 
     * The time complexity is O(n) because it calls the entryHash method, which
     * has a time complexity of O(n). That is the dominant operation in this method.
     */
    private int primaryHash(K key) {
        int hash = entryHash(key);
        return Math.abs(hash % capacity);
    }

    /**
     * This method calculates the step size for the secondary hash function.
     * The step size is the number of steps to take when the primary hash function
     * results in a collision.
     * 
     * The time complexity is O(n) because it calls the entryHash method, which
     * has a time complexity of O(n). That is the dominant operation in this method.
     */
    private int secondaryHash(K key) {
        int hash = entryHash(key);
        int step = 1 + (hash % (capacity - 1));
        return step;
    }

    /**
     * This method inserts the entry into the hash table. It calculates the index
     * of the entry using the primary hash function. If the index is already
     * occupied, it uses the secondary hash function to calculate the step size
     * and iterates through the table until it finds an empty slot. It then
     * inserts the entry into the table and increments the size.
     * 
     * The time complexity is O(1) on average. This is because the primaryHash and
     * secondaryHash methods calculate the index of the entry based on the key in
     * constant time. In the worst case, the while loop will iterate through the
     * entire table, which is O(n). However, this is very unlikely to happen.
     */
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

    /**
     * This method searches for the entry with the specified key in the hash table.
     * It calculates the index of the entry using the primary hash function. If the
     * index is already occupied, it uses the secondary hash function to calculate
     * the step size and iterates through the table until it finds the entry with
     * the specified key. It then returns the entry.
     * 
     * The average time complexity is O(1) because the primaryHash and
     * secondaryHash methods calculate the index of the entry based on the key in
     * constant time. In the worst case, the while loop will iterate through the
     * entire table, which is O(n). However, this is very unlikely to happen.
     */
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

    /**
     * This method deletes the entry with the specified key from the hash table.
     * It calculates the index of the entry using the primary hash function. If the
     * index is occupied and is not the entry being searched for, it uses the
     * secondary hash function to calculate
     * the step size and iterates through the table until it finds the entry with
     * the specified key. It then deletes the entry and decrements the size.
     * 
     * The time complexity is O(1)
     */
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

    /**
     * This method resizes the hash table by doubling the capacity and selecting the
     * next prime number.
     * It then rehashes all the entries in the table and inserts them into the new
     * table. It then sets the table to the new table and sets the capacity to the
     * new capacity.
     * 
     * The time complexity is O(n) where n is the number of entries in the table
     * because
     * it iterates through all the entries in the table and inserts them into the
     * new table.
     * 
     * @throws IllegalStateException if the maximum capacity is reached
     */
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

                K key = (K) entry.getKey(); // Casting the key to type K
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

    /**
     * This method generates a random prime number between the specified minimum and
     * maximum values. It generates a random number between the minimum and maximum
     * values and checks if it is prime. If it is not prime, it generates another
     * random number and checks if it is prime. It repeats this process until it
     * finds a prime number.
     * 
     * The time complexity is O(n^(3/2)) where n is the difference between the
     * maximum and minimum values. This is because the while loop iterates through
     * all the numbers between the minimum and maximum values, and the isPrime
     * method iterates through all the numbers between 2 and the square root of the
     * candidate number.
     * 
     * @param min the minimum value of the random number
     * @param max the maximum value of the random number
     * @return a random prime number between the specified minimum and maximum
     */
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

    /**
     * This method checks if the specified number is prime. It iterates through all
     * the numbers between 2 and the square root of the number and checks if the
     * number is divisible by any of them. If it is divisible by any of them, it
     * returns false. If it is not divisible by any of them, it returns true.
     * 
     * The time complexity is O(n^(1/2)) where n is the specified number. This is
     * because the for loop iterates through all the numbers between 2 and the
     * square root of the number.
     * 
     * @param num the number to check if it is prime
     * @return true if the number is prime, false otherwise
     */
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

    /**
     * This method returns the next prime number after the specified number. It
     * increments the number by 1 and checks if it is prime. If it is not prime, it
     * increments the number by 1 and checks if it is prime. It repeats this process
     * until it finds a prime number.
     * 
     * The time complexity is O(n*(n^1/2)) where n is the specified number. This is
     * because the while loop iterates through all the numbers between the specified
     * number and the next prime number. Inside the while loop, the isPrime method
     * iterates through all the numbers between 2 and the square root of the number.
     * 
     * @param num the number to find the next prime number after
     * @return the next prime number after the specified number
     */
    private int getNextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    /**
     * This method returns the capacity of the hash table.
     * 
     * The time complexity is O(1) because it is a constant time operation (getting
     * the value of the capacity field)
     * 
     * @return the capacity of the hash table
     */
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

    /**
     * This method returns an array of all the entries in the hash table.
     * 
     * The time complexity is O(n) where n is the number of entries in the table
     * because
     * it iterates through all the entries in the table.
     * 
     * @return an array of all the entries in the hash table
     */
    public Entry<K, V>[] getAllEntries() {
        return table;
    }
}
