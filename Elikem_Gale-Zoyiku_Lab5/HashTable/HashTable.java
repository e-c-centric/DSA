package HashTable;
import java.util.Random;

/**
 * A hash table is a data structure that stores entries in an array. Each entry
 * is stored in a slot in the array.
 * <ul>
 * <li>The slot is determined by the hash code of
 * the entry's key. The hash code is calculated using a hash function. The hash
 * function is used to map the entry to an index in the hash table. The index is
 * calculated as the remainder of the hash code of the key divided by the
 * capacity of the hash table.</li>
 * <li>The capacity of the hash table is the number of
 * slots in the array. The capacity of the hash table is determined when the
 * hash table is created.</li>
 * <li>The capacity of the hash table is doubled, and the new
 * capacity is set to the next prime number after the doubled capacity when the
 * hash table is full. The hash table is full when the number of entries in the
 * hash table is equal to the capacity of the hash table. When the hash table is
 * full, the capacity of the hash table is increased to the next prime number
 * after 2 * the current capacity and the entries are rehashed
 * into the new hash table.</li>
 * <li>The hash table is rehashed by calculating the index
 * of each entry in the new hash table using the new capacity.</li>
 * <li>The hash table
 * uses linear probing to resolve collisions. Linear probing is a method of
 * resolving collisions by checking the next slot in the hash table when a
 * collision occurs.</li>
 * <li>If the next slot is occupied, it checks the next slot, and
 * so on, until it finds an empty slot. The hash table uses a step size to
 * determine the next slot to check when resolving collisions.</li>
 * <li>The step size is
 * calculated as 1 + (hash % (capacity - 1)). The step size is used to calculate
 * the index of the next slot to check in the hash table when resolving
 * collisions.</li>
 * <li>The step size is added to the index of the current slot to
 * calculate the index of the next slot to check. If the index of the next slot
 * is greater than or equal to the capacity of the hash table, the index is
 * calculated as the remainder of the index of the next slot divided by the
 * capacity of the hash table.
 * </li>
 * </ul>
 * 
 * @see Entry
 */
public class HashTable {
    private Entry[] table;
    private int size;
    private int capacity;

    /**
     * The prime number used for the hash function. The prime number is used to
     * generate a hash code for the key of an entry. The hash code is used to map
     * the entry to an index in the hash table. The prime number is also used to
     * calculate the step size for resolving collisions. This number is randomly
     * generated
     * in the constructor as the hash table is created, and then remains constant
     * for
     * the lifetime of the hash table. This introduces some unpredictability into
     * the
     * program, which helps to ensure that the hash table is not biased towards
     * certain
     * numbers, and ensures the hashing function is secure and robust.
     * 
     * @see #generateRandomPrime(int min, int max)
     */
    private final int prime;

    /**
     * Constructor for the HashTable class.
     * <ul>
     * <li>Invokes the generateRandomPrime method to generate a random prime number
     * between 10 and 90. I chose to generate a random prime number between 10 and
     * 90 because it is a small range of numbers, which makes it easier to test the
     * program. Also, it introudces some unpredictability into the program, which
     * helps to ensure that the hash table is not biased towards certain numbers,
     * and ensures the ashing function is secure and robust.
     * </li>
     * <li>Sets the capacity of the hash table to the specified
     * initial capacity.</li>
     * <li>Initializes the table to an array of Entry objects with
     * the specified initial capacity.</li>
     * <li>Sets the size of the hash table to 0.</li>
     * </ul>
     * 
     */
    public HashTable(int initialCapacity) {
        capacity = initialCapacity;
        table = new Entry[capacity];
        size = 0;
        this.prime = generateRandomPrime(10, 90);

    }

    /**
     * Returns the hash code of the specified key.
     * 
     * @param key the key
     * @return the hash code of the specified key
     */
    private int entryHash(String key) {
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = (prime * hash + key.charAt(i)) & 0xFFFFFFFF;
        }

        return hash;
    }

    /**
     * Returns the index of the specified key in the hash table. The index is
     * calculated as the remainder of the hash code of the key divided by the
     * capacity of the hash table. The index must be a positive integer less than
     * the capacity of the hash table. The index is used to determine the slot in
     * the hash table where the entry will be stored.
     * 
     * @param key the key
     * @return the index of the specified key in the hash table
     */
    private int primaryHash(String key) {
        int hash = entryHash(key);
        return Math.abs(hash % capacity);
    }

    /**
     * Returns the step size for the specified key. The step size is calculated as
     * 1 + (hash % (capacity - 1)). The step size must be a positive integer less
     * than the capacity of the hash table. The step size is used to calculate the
     * index of the next slot to check in the hash table when resolving collisions.
     * 
     * @param key the key
     * @return the step size for the specified key
     * @see #primaryHash(String key)
     */
    private int secondaryHash(String key) {
        int hash = entryHash(key);
        int step = 1 + (hash % (capacity - 1));
        return step;
    }

    /**
     * Inserts the specified entry into the hash table. Invokes the primaryHash and
     * secondaryHash methods to determine the index of the entry in the hash table.
     * If
     * the hash table is full, invokes the resizeTable method to increase the
     * capacity
     * of the hash table.
     * 
     * @param entry the entry to insert
     * @see #primaryHash(String key)
     * @see #secondaryHash(String key)
     * @see #resizeTable()
     * @see Entry#getKey()
     */
    public void insert(Entry entry) {
        if (size == capacity) {
            resizeTable();
        }

        String key = entry.getKey();
        int index = primaryHash(key);
        int step = secondaryHash(key);

        while (table[index] != null) {
            index = (index + step) % capacity;
        }

        table[index] = entry;
        size++;
    }

    /**
     * Returns the entry with the specified key from the hash table.
     * Invokes the primaryHash and secondaryHash methods to determine the index of
     * the entry in the hash table.
     * 
     * @param key the key of the entry to search for
     * @return the entry with the specified key from the hash table, or null if the
     *         entry is not found
     * @see #primaryHash(String key)
     * @see #secondaryHash(String key)
     */
    public Entry search(String key) {
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
     * Deletes the entry with the specified key from the hash table. Invokes the
     * primaryHash and secondaryHash methods to determine the index of the entry in
     * the hash table.
     * 
     * @param key the key of the entry to delete
     */
    public void delete(String key) {
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
     * <ul>
     * <li>Resizes the hash table to increase its capacity to the next prime number
     * after the current capacity multiplied by 2. Increasing the capacity of the
     * hash table to the next prime number after capacity * 2 helps to ensure a more
     * even distribution of entries and reduces the likelihood of collisions.</li>
     * 
     * <li>When the capacity of the hash table is doubled, it increases the number
     * of
     * available slots for storing entries. However, if the new capacity is not a
     * prime number, it may introduce patterns in the hash table that can lead to
     * more collisions.</li>
     * 
     * <li>By choosing the next prime number as the new capacity, it helps to
     * minimize
     * the chance of collisions and improves the performance of the hash table.
     * Prime numbers have fewer divisors, which reduces the likelihood of entries
     * being mapped to the same index in the hash table. This helps to distribute
     * the entries more evenly across the table, resulting in better performance for
     * operations such as insertion, search, and deletion.Rehashes all entries in
     * the hash
     * table.</li>
     * </ul>
     * 
     * @throws IllegalStateException if the maximum capacity is reached (unlikely)
     * @see #getNextPrime(int num)
     */
    private void resizeTable() {
        int newCapacity = getNextPrime(capacity * 2);
        if (newCapacity <= capacity) {
            throw new IllegalStateException("Cannot resize table. Maximum capacity reached.");
        }

        Entry[] newTable = new Entry[newCapacity];

        for (Entry entry : table) {
            if (entry != null) {
                if (entry.getKey() == null) {
                    throw new IllegalArgumentException("Cannot insert entry with null key.");
                }

                int index = primaryHash(entry.getKey());
                int step = secondaryHash(entry.getKey());

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
     * Generates a random prime number between the specified minimum and maximum
     * values.
     * 
     * @param min the minimum value
     * @param max the maximum value
     * @return a random prime number between the specified minimum and maximum
     *         values
     * @apiNote This method is not efficient for large numbers.
     * @see #isPrime(int)
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
     * Returns true if the specified number is prime, false otherwise.
     * 
     * @param num the number to check
     * @return true if the specified number is prime, false otherwise
     * @apiNote This method is not efficient for large numbers.
     * @apiNote Helper method for
     *          {@link #generateRandomPrime(int min, int max)} and
     *          {@link #getNextPrime(int num)}.
     * @see #generateRandomPrime(int, int)
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
     * Returns the next prime number after the specified number.
     * 
     * @param num the number
     * @return the next prime number after the specified number
     * @apiNote This method is not efficient for large numbers.
     * @see #isPrime(int)
     */
    private int getNextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    /**
     * Returns the number of entries the hash table can hold at any time.
     * 
     * @return the current capacity of the hash table
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the number of entries in the hash table. The size of the hash table
     * is the number of entries in the hash table. It differs from the capacity of
     * the hash table, which is the number of slots in the hash table. The size of
     * the hash table is less than or equal to the capacity of the hash table.
     * 
     * @return the number of entries in the hash table
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns a string representation of the hash table.
     * Invokes the toString method of each Entry in the hash table.
     * 
     * @return a string representation of the hash table
     * @see Entry#toString()
     */
    @Override
    public String toString() {
        String str = "";
        for (Entry entry : table) {
            if (entry != null) {
                str += entry + "\n";
            }
        }
        return str;
    }

}