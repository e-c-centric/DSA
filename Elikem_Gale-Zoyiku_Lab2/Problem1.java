import java.util.ArrayList;
import java.util.List;

// Generic Product class
class Product<T> {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }
}

// Generic StoreInventory class that can store any type of product
class StoreInventory<T extends Product<?>> {
    private List<T> inventory = new ArrayList<>();

    public void addProduct(T product) {
        inventory.add(product);
    }

    public void removeProduct(String productName) {
        inventory.removeIf(product -> product.getName().equalsIgnoreCase(productName));
    }

    public T findProduct(String productName) {
        for (T product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public void listAllProducts() {
        for (T product : inventory) {
            System.out.println(product);
        }
    }

    public double getTotalValue() {
        double totalValue = 0;
        for (T product : inventory) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }
    public void updateProductQuantity(String productName, int newQuantity) {
    T product = findProduct(productName);
    if (product != null) {
        product.setQuantity(newQuantity);
    }
}

public void updateProductPrice(String productName, double newPrice) {
    T product = findProduct(productName);
    if (product != null) {
        product.setPrice(newPrice);
    }
}

public T findMostExpensiveProduct() {
    if (inventory.isEmpty()) {
        return null;
    }
    
    T mostExpensive = inventory.get(0);
    for (T product : inventory) {
        if (product.getPrice() > mostExpensive.getPrice()) {
            mostExpensive = product;
        }
    }
    return mostExpensive;
}
}

// Specific product classes that extend the generic Product class
class ElectronicProduct extends Product<ElectronicProduct> {
    public ElectronicProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}

class ClothingProduct extends Product<ClothingProduct> {
    public ClothingProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}

class BookProduct extends Product<BookProduct> {
    public BookProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}

public class Problem1 {
    public static void main(String[] args) {
    StoreInventory<ElectronicProduct> electronicInventory = new StoreInventory<>();
    electronicInventory.addProduct(new ElectronicProduct("Laptop", 899.99, 10));
    electronicInventory.addProduct(new ElectronicProduct("Smartphone", 499.99, 20));

    StoreInventory<ClothingProduct> clothingInventory = new StoreInventory<>();
    clothingInventory.addProduct(new ClothingProduct("T-Shirt", 19.99, 50));
    clothingInventory.addProduct(new ClothingProduct("Jeans", 39.99, 30));

    StoreInventory<BookProduct> bookInventory = new StoreInventory<>();
    bookInventory.addProduct(new BookProduct("Java Programming", 29.99, 15));
    bookInventory.addProduct(new BookProduct("Design Patterns", 39.99, 10));

    // Testing methods created in StoreInventory class
    System.out.println("Electronic Inventory:");
    electronicInventory.listAllProducts();
        System.out.printf("Total value of electronic inventory: GHc%.2f\n", electronicInventory.getTotalValue());
        electronicInventory.updateProductQuantity("Laptop", 5);
        electronicInventory.updateProductPrice("Smartphone", 599.99);
        electronicInventory.removeProduct("Laptop");
        System.out.println("Most expensive electronic product: " + electronicInventory.findMostExpensiveProduct());

        System.out.println("\nClothing Inventory:");
        clothingInventory.listAllProducts();
        System.out.printf("Total value of clothing inventory: GHc%.2f\n", clothingInventory.getTotalValue());
        clothingInventory.removeProduct("T-Shirt");
        System.out.println("Most expensive clothing product: " + clothingInventory.findMostExpensiveProduct());

        System.out.println("\nBook Inventory:");
        bookInventory.listAllProducts();
        System.out.printf("Total value of book inventory: GHc%.2f\n", bookInventory.getTotalValue());
        System.out.println("Most expensive book: " + bookInventory.findMostExpensiveProduct());
    }

    }
