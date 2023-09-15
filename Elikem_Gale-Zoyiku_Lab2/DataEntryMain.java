import java.util.ArrayList;
import java.util.List;

class DataEntry<T> {
    private String identifier;
    private T data;
    private long timestamp;

    public DataEntry(String identifier, T data, long timestamp) {
        this.identifier = identifier;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getIdentifier() {
        return identifier;
    }

    public T getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "DataEntry [identifier=" + identifier + ", data=" + data + ", timestamp=" + timestamp + "]";
    }
}

// Generic DataStorage class
class DataStorage<T> {
    private List<DataEntry<T>> storage = new ArrayList<>();

    public void addEntry(DataEntry<T> entry) {
        storage.add(entry);
    }

    public DataEntry<T> retrieveEntry(String identifier) {
        for (DataEntry<T> entry : storage) {
            if (entry.getIdentifier().equals(identifier)) {
                return entry;
            }
        }
        return null;
    }

    public int getEntryCount() {
        return storage.size();
    }

    public double getAverageTimestamp() {
        if (storage.isEmpty()) {
            return 0;
        }

        long totalTimestamp = 0;
        for (DataEntry<T> entry : storage) {
            totalTimestamp += entry.getTimestamp();
        }

        double averageTimestamp = (double) totalTimestamp / storage.size();
        return Double.parseDouble(String.format("%.3f", averageTimestamp));
    }

    public void removeEntry(String identifier) {
        storage.removeIf(entry -> entry.getIdentifier().equals(identifier));
    }

    public List<DataEntry<T>> listAllEntries() {
        return new ArrayList<>(storage);
    }

    public DataEntry<T> findEarliestEntry() {
        if (storage.isEmpty()) {
            return null;
        }

        DataEntry<T> earliest = storage.get(0);
        for (DataEntry<T> entry : storage) {
            if (entry.getTimestamp() < earliest.getTimestamp()) {
                earliest = entry;
            }
        }
        return earliest;
    }

    public DataEntry<T> findLatestEntry() {
        if (storage.isEmpty()) {
            return null;
        }

        DataEntry<T> latest = storage.get(0);
        for (DataEntry<T> entry : storage) {
            if (entry.getTimestamp() > latest.getTimestamp()) {
                latest = entry;
            }
        }
        return latest;
    }

}

public class DataEntryMain {
        public static void main(String[] args) {
            DataStorage<Integer> dataStorage = new DataStorage<>();
            dataStorage.addEntry(new DataEntry<>("Entry1", 42, System.currentTimeMillis()));
            dataStorage.addEntry(new DataEntry<>("Entry2", 78, System.currentTimeMillis() - 10000)); // An entry with an erlier timestamp

            DataStorage<String> stringDataStorage = new DataStorage<>();
            stringDataStorage.addEntry(new DataEntry<>("StringEntry1", "Hello", System.currentTimeMillis()));
            stringDataStorage.addEntry(new DataEntry<>("StringEntry2", "World", System.currentTimeMillis() - 5000)); // An
                                                                                                                     // entry
                                                                                                                     // with
                                                                                                                     // an
                                                                                                                     // earlier
                                                                                                                     // timestamp

            // Test methods
            System.out.println("Integer Data Storage:");
            System.out.println("Entry count: " + dataStorage.getEntryCount());
            System.out.println("Average timestamp: " + dataStorage.getAverageTimestamp());
            System.out.println("Retrieved Entry1: " + dataStorage.retrieveEntry("Entry1"));
            System.out.println("Earliest entry: " + dataStorage.findEarliestEntry());
            System.out.println("Latest entry: " + dataStorage.findLatestEntry());

            System.out.println("\nString Data Storage:");
            System.out.println("Entry count: " + stringDataStorage.getEntryCount());
            System.out.println("Average timestamp: " + stringDataStorage.getAverageTimestamp());
            System.out.println("Retrieved StringEntry2: " + stringDataStorage.retrieveEntry("StringEntry2"));
            System.out.println("Earliest entry: " + stringDataStorage.findEarliestEntry());
            System.out.println("Latest entry: " + stringDataStorage.findLatestEntry());

            // Remove an entry and list all entries
            dataStorage.removeEntry("Entry2");
            System.out.println("\nInteger Data Storage after removing Entry2:");
            System.out.println("Entry count: " + dataStorage.getEntryCount());
            System.out.println("All entries: " + dataStorage.listAllEntries());
        }
    }


