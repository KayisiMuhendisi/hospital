import java.util.Set;

public abstract class Person {
    private String name;
    private String id;
    private String address;

    // Constructor
    public Person(String name, String id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    // Getter ve Setter metotları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Soyut metot: alt sınıflarda implement edilmesi gerekiyor
    public abstract void displayInfo();
}
