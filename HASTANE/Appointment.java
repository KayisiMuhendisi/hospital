import java.time.LocalDateTime;

public class Appointment {
    private LocalDateTime dateTime;
    private Doctor doctor;
    private Patient patient;

    // Constructor
    public Appointment(LocalDateTime dateTime, Doctor doctor, Patient patient) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
    }

    // Getter ve Setter metotları
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void displayAppointmentDetails() {
        System.out.println("Randevu: " + patient.getName() + " ile " + doctor.getName() + ", Tarih: " + dateTime);
    }
}
