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

class ElectronicProduct extends Product<ElectronicProduct> {
    private String brand;
    private String model;

    public ElectronicProduct(String name, double price, int quantity, String brand, String model) {
        super(name, price, quantity);
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "ElectronicProduct[\nName: " + getName() + ",\nPrice: " + getPrice() + ",\nQuantity: " + getQuantity() + ",\nBrand: " + brand + ",\nModel: " + model + "\n]";
    }
}

class ClothingProduct extends Product<ClothingProduct> {
    private String size;
    private String color;

    public ClothingProduct(String name, double price, int quantity, String size, String color) {
        super(name, price, quantity);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ClothingProduct[\nName: " + getName() + ",\nPrice: " + getPrice() + ",\nQuantity: " + getQuantity() + ",\nSize: " + size + ",\nColor: " + color + "\n]";
    }
}

class BookProduct extends Product<BookProduct> {
    private String author;
    private int pages;

    public BookProduct(String name, double price, int quantity, String author, int pages) {
        super(name, price, quantity);
        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "BookProduct[\nName: " + getName() + ",\nPrice: " + getPrice() + ",\nQuantity: " + getQuantity() + ",\nAuthor: " + author + ",\nPages: " + pages + "\n]";
    }
}
public class ProductMain {
    public static void main(String[] args) {
    StoreInventory<ElectronicProduct> electronicInventory = new StoreInventory<>();
    electronicInventory.addProduct(new ElectronicProduct("Laptop", 899.99, 10, "Dell", "XPS 13"));
    electronicInventory.addProduct(new ElectronicProduct("Smartphone", 499.99, 20, "Samsung", "Galaxy S21"));

    StoreInventory<ClothingProduct> clothingInventory = new StoreInventory<>();
    clothingInventory.addProduct(new ClothingProduct("T-Shirt", 19.99, 50, "M", "Red"));
    clothingInventory.addProduct(new ClothingProduct("Jeans", 39.99, 30, "L", "Blue"));

    StoreInventory<BookProduct> bookInventory = new StoreInventory<>();
    bookInventory.addProduct(new BookProduct("Java Programming", 29.99, 15, "Elikem Gal-Zoyiku", 500));
    bookInventory.addProduct(new BookProduct("Design Patterns", 39.99, 10, "Kafui Gale-Zoyiku", 300));

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
