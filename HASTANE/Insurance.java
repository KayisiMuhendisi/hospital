public class Insurance {
    private String insuranceCompany;
    private String policyNumber;
    private double coveragePercentage;  // Sigorta kapsamında karşılanan oran

    public Insurance(String insuranceCompany, String policyNumber, double coveragePercentage) {
        this.insuranceCompany = insuranceCompany;
        this.policyNumber = policyNumber;
        this.coveragePercentage = coveragePercentage;
    }

    // Getter ve Setter metotları
    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public double getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(double coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }
}
