import java.util.UUID;

public class Administrator extends Person {
    public Administrator(String name, String id, String address) {
        super(name, id, address);
    }

    @Override
    public void displayInfo() {
        // UUID yerine başka bir alternatif ID oluşturabilirsiniz
        System.out.println("Administrator Name: " + getName());
        System.out.println("Administrator ID: " + generateId());
        System.out.println("Address: " + getAddress());
    }

    // Alternatif bir getId() işlevi
    private String generateId() {
        // Benzersiz bir ID oluşturmak için UUID kullanıyoruz
        return UUID.randomUUID().toString();
    }

    public void manageSystem() {
        System.out.println("System is being managed.");
    }
}
