public class Medication {
    private String name;
    private int stock;
    private double price;

    // Constructor
    public Medication(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    // Getter ve Setter metotları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayMedicationDetails() {
        System.out.println("İlaç: " + name + ", Stok: " + stock + ", Fiyat: " + price);
    }
}
