import java.time.LocalDate;

public class LabTest {
    private String testType;  // Kan testi, röntgen, MR vb.
    private String result;
    private LocalDate testDate;

    // Constructor
    public LabTest(String testType, String result, LocalDate testDate) {
        this.testType = testType;
        this.result = result;
        this.testDate = testDate;
    }

    // Getter ve Setter metotları
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    // Test detaylarını ekranda göstermek için bir metod
    public void displayTestDetails() {
        System.out.println("Test Türü: " + testType + ", Sonuç: " + result + ", Tarih: " + testDate);
    }
}
