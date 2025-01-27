public class Patient extends Person {
    private int age;
    private String address;
    private String medicalHistory;

    // Constructor
    public Patient(String name, String id, int age, String address, String medicalHistory) {
        super(name, id, address);
        this.age = age;
        this.address = address;
        this.medicalHistory = medicalHistory;
    }

    // Getter ve Setter metotları
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public void displayInfo() {
        System.out.println("Hasta Adı: " + getName() + ", ID: " + getId() + ", Yaş: " + age);
    }
}
