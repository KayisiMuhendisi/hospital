public class Invoice {
    private Patient patient;
    private Treatment treatment;
    private Medication medication;
    private Insurance insurance;
    private double treatmentCost;
    private double medicationCost;
    private double totalCost;

    public Invoice(Patient patient, Treatment treatment, Medication medication, Insurance insurance) {
        this.patient = patient;
        this.treatment = treatment;
        this.medication = medication;
        this.insurance = insurance;
        calculateCosts();
    }

    // Tedavi ve ilaç masraflarını hesapla
    private void calculateCosts() {
        treatmentCost = treatment != null ? treatment.getCost() : 0;
        medicationCost = medication != null ? medication.getPrice() : 0;
        double totalBeforeInsurance = treatmentCost + medicationCost;
        double coverage = totalBeforeInsurance * (insurance.getCoveragePercentage() / 100);
        totalCost = totalBeforeInsurance - coverage;
    }

    // Fatura detaylarını göster
    public void displayInvoiceDetails() {
        System.out.println("Hasta Adı: " + patient.getName());
        System.out.println("Tedavi: " + treatment.getName());
        System.out.println("İlaç: " + medication.getName());
        System.out.println("Sigorta Şirketi: " + insurance.getInsuranceCompany());
        System.out.println("Sigorta Kapsamı: %" + insurance.getCoveragePercentage());
        System.out.println("Tedavi Ücreti: " + treatmentCost);
        System.out.println("İlaç Ücreti: " + medicationCost);
        System.out.println("Toplam Masraf: " + totalCost);
    }

    public double getTotalCost() {
        return totalCost;
    }
}
