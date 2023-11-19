package HashTable;
public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);
        System.out.println("Initial table capacity: " + hashTable.getCapacity());
        System.out.println("Initial table size: " + hashTable.getSize());

        hashTable.insert(new Entry("A123", 85));
        hashTable.insert(new Entry("B456", 92));
        hashTable.insert(new Entry("C789", 78));
        hashTable.insert(new Entry("D012", 90));

        Entry entry1 = hashTable.search("A123");
        System.out.println("Search Result: " + entry1);

        Entry entry2 = hashTable.search("C789");
        System.out.println("Search Result: " + entry2);

        Entry entry3 = hashTable.search("E345");
        System.out.println("Search Result: " + entry3);


        hashTable.delete("B456");
        Entry entry4 = hashTable.search("B456");
        System.out.println("Search Result: " + entry4);



        hashTable.insert(new Entry("F678", 88));
        hashTable.insert(new Entry("G901", 95));
        hashTable.insert(new Entry("H234", 82));
        hashTable.insert(new Entry("I567", 91));

        Entry entry5 = hashTable.search("F678");
        System.out.println("Search Result: " + entry5);

        Entry entry6 = hashTable.search("H234");
        System.out.println("Search Result: " + entry6);

        Entry entry7 = hashTable.search("J890");
        System.out.println("Search Result: " + entry7);

        hashTable.insert(new Entry("J890", 93));
        hashTable.insert(new Entry("K123", 87));
        hashTable.insert(new Entry("L456", 89));
        hashTable.insert(new Entry("M789", 94));
        hashTable.insert(new Entry("N012", 86));
        hashTable.insert(new Entry("O345", 90));
        hashTable.insert(new Entry("P678", 84));
        hashTable.insert(new Entry("Q901", 92));
        hashTable.insert(new Entry("R234", 83));
        hashTable.insert(new Entry("S567", 81));
        hashTable.insert(new Entry("T890", 80));
        hashTable.insert(new Entry("U123", 79));
        hashTable.insert(new Entry("V456", 77));

        System.out.println("Final table capacity: " + hashTable.getCapacity());
        System.out.println("Final table size: " + hashTable.getSize());
        
        System.out.println(hashTable);
    }
}