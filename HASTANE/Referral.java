import java.time.LocalDate;

public class Referral {
    private Patient patient;          // Sevk edilen hasta
    private String referredHospital;  // Sevk edilen hastane
    private LocalDate referralDate;   // Sevk tarihi
    private String reason;            // Sevk nedeni
    private String report;            // Sevk raporu

    public Referral(Patient patient, String referredHospital, LocalDate referralDate, String reason, String report) {
        this.patient = patient;
        this.referredHospital = referredHospital;
        this.referralDate = referralDate;
        this.reason = reason;
        this.report = report;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getReferredHospital() {
        return referredHospital;
    }

    public void setReferredHospital(String referredHospital) {
        this.referredHospital = referredHospital;
    }

    public LocalDate getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(LocalDate referralDate) {
        this.referralDate = referralDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void displayReferralDetails() {
        System.out.println("Hasta: " + patient.getName() + ", Sevk Edilen Hastane: " + referredHospital + ", Sevk Tarihi: " + referralDate + ", Neden: " + reason);
    }
}
