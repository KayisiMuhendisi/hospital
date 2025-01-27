public class Staff extends Person {
    private String role;

    // Constructor
    public Staff(String name,String address, String id, String role) {
        super(name, id, address);
        this.role = role;
    }

    // Getter ve Setter metotları
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void displayInfo() {
        System.out.println("Personel Adı: " + getName() + ", Görev: " + role);
    }
}
