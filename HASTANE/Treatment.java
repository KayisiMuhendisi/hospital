public class Treatment {
    private int id;
    private String name;
    private int patientId;
    private String treatmentPlan;
    private String prescribedMedication;
    private int medicationStockStatus;
    private double cost;

    // Constructor
    public Treatment(int id, int patientId, String treatmentPlan, String prescribedMedication, int medicationStockStatus) {
        this.id = id;
        this.patientId = patientId;
        this.treatmentPlan = treatmentPlan;
        this.prescribedMedication = prescribedMedication;
        this.medicationStockStatus = medicationStockStatus;
    }

    // Getter ve Setter metotları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getPrescribedMedication() {
        return prescribedMedication;
    }

    public void setPrescribedMedication(String prescribedMedication) {
        this.prescribedMedication = prescribedMedication;
    }

    public int getMedicationStockStatus() {
        return medicationStockStatus;
    }

    public void setMedicationStockStatus(int medicationStockStatus) {
        this.medicationStockStatus = medicationStockStatus;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // toString metodu
    @Override
    public String toString() {
        return  "Hasta ID: " + patientId + "\nTedavi Planı: " + treatmentPlan +
                "\nReçete Edilen İlaç: " + prescribedMedication + "\nİlaç Stok Durumu: " + medicationStockStatus ;
    }
}
