public class Doctor extends Person {
    private String specialization;
    private String workingHours;

    // Constructor
    public Doctor(String name, String id, String specialization, String workingHours,String address) {
        super(name, id, address);
        this.specialization = specialization;
        this.workingHours = workingHours;
    }

    // Getter ve Setter metotları
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public void displayInfo() {
        System.out.println("Doktor Adı: " + getName() + ", Uzmanlık: " + specialization);
    }
}
