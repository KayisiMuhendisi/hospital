import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private  Connection connection;

    // Constructor: Veritabanı bağlantısını kuruyor
    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("database bağlantısı");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Veritabanı bağlantısını döndüren metod
    public   Connection getConnection() {
        return connection;
    }

    // Hasta ekleme işlemi
    public void addPatient(int patientId, String patientName) {
        String sql = "INSERT INTO patients (patient_id, patient_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, patientName);
            pstmt.executeUpdate(); // Veriyi veritabanına ekle
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hasta silme işlemi
    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.executeUpdate(); // Veritabanından hastayı sil
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Doktor ekleme işlemi
    public void addDoctor(int doctorId, String doctorName) {
        String sql = "INSERT INTO doctors (doctor_id, doctor_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.setString(2, doctorName);
            pstmt.executeUpdate(); // Veriyi veritabanına ekle
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Doktor silme işlemi
    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.executeUpdate(); // Veritabanından doktoru sil
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Randevu ekleme işlemi
    public void addAppointment(String appointmentDate, int patientId, int doctorId) {
        String query = "INSERT INTO appointments (appointment_date, patient_id, doctor_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, Timestamp.valueOf(appointmentDate));
            stmt.setInt(2, patientId);
            stmt.setInt(3, doctorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Randevu silme fonksiyonu
    public void cancelAppointment(int patientId, String appointmentDate) {
        String query = "DELETE FROM appointments WHERE patient_id = ? AND appointment_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setTimestamp(2, Timestamp.valueOf(appointmentDate));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tedavi kaydetme fonksiyonu
    public void addTreatment(int patientId, String treatmentPlan, String prescribedMedication, int medicationStock) {
        String sql = "INSERT INTO treatments (patient_id, treatment_plan, prescribed_medication, medication_stock_status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, treatmentPlan);
            pstmt.setString(3, prescribedMedication);
            pstmt.setInt(4, medicationStock);
            pstmt.executeUpdate(); // Veritabanına ekle
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // İlaç stok durumunu güncelleme fonksiyonu
    public void updateMedicationStock(String medication) {
        String sql = "UPDATE medications SET stock_quantity = stock_quantity - 1 WHERE medication_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, medication);
            pstmt.executeUpdate(); // Stok miktarını 1 azalt
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tedavi geçmişini getiren fonksiyon
    public List<Treatment> getTreatmentHistory(int patientId) {
        List<Treatment> treatments = new ArrayList<>();
        String sql = "SELECT * FROM treatments WHERE patient_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String treatmentPlan = rs.getString("treatment_plan");
                String prescribedMedication = rs.getString("prescribed_medication");
                int medicationStockStatus = rs.getInt("medication_stock_status");

                treatments.add(new Treatment(id,patientId, treatmentPlan, prescribedMedication, medicationStockStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }

    // Görev ekleme
    public void assignTask(int nurseId, String taskDescription) {
        String sql = "INSERT INTO tasks (nurse_id, task_description, assigned_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nurseId);
            stmt.setString(2, taskDescription);
            stmt.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atanmış görevleri alma
    public List<Task> getAssignedTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int nurseId = rs.getInt("nurse_id");
                String taskDescription = rs.getString("task_description");
                String assignedDate = rs.getString("assigned_date");

                tasks.add(new Task(id, nurseId, taskDescription, assignedDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    // Acil Durum Atama: Bu metod ile doktor ve hasta bilgileri ilişkili olarak acil duruma atanır.
    public void assignEmergencyCase(int patientId, int doctorId) {
        String query = "INSERT INTO emergency_cases (patient_id, doctor_id, case_date) VALUES (?, ?, CURRENT_DATE)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            statement.setInt(2, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Acil Durumları Görüntüleme: Acil durumlar ile ilgili hastalar ve doktorları birlikte görüntüler.
    public List<String> getEmergencyCases() {
        List<String> cases = new ArrayList<>();
        String query = "SELECT emergency_cases.patient_id, emergency_cases.doctor_id, emergency_cases.case_date, " +
                "patients.patient_name AS patient_name, doctors.doctor_name AS doctor_name " +
                "FROM emergency_cases " +
                "JOIN patients ON emergency_cases.patient_id = patients.patient_id " +
                "JOIN doctors ON emergency_cases.doctor_id = doctors.doctor_id";



        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                String patientName = resultSet.getString("patient_name");
                int doctorId = resultSet.getInt("doctor_id");
                String doctorName = resultSet.getString("doctor_name");
                Date caseDate = resultSet.getDate("case_date");
                cases.add("Hasta: " + patientName + " (ID: " + patientId + ") - Doktor: " + doctorName + " (ID: " + doctorId + ") - Tarih: " + caseDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cases;
    }
    // Faturayı veritabanına kaydetme
    public void saveInvoice(int patientId, double treatmentCost, double extraCost, double totalCost) {
        String query = "INSERT INTO invoices (patient_id, treatment_cost, extra_cost, total_cost) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            preparedStatement.setDouble(2, treatmentCost);
            preparedStatement.setDouble(3, extraCost);
            preparedStatement.setDouble(4, totalCost);

            preparedStatement.executeUpdate();
            System.out.println("Fatura başarıyla kaydedildi.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fatura kaydedilemedi.");
        }
    }

    // Hastayı veritabanından almak
    public boolean patientExists(int patientId) {
        String query = "SELECT COUNT(*) FROM patients WHERE patient_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;  // Eğer hasta varsa, 1 döner
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Hasta yoksa
    }
    // Test sonucu kaydetme
    public boolean saveTestResult(int patientId, String testType, String result) {
        String query = "INSERT INTO test_results (patient_id, test_type, result) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, testType);
            pstmt.setString(3, result);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hasta ID'sine göre test sonuçlarını alır
    public ResultSet getTestResults(int patientId) {
        String query = "SELECT * FROM test_results WHERE patient_id = ?";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, patientId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void saveReferral(int patientId, String patientName, String referralHospital, int doctorID, String referralReason) {
        String query = "INSERT INTO referrals (patient_id, patient_name, referral_hospital, doctor_id, referral_reason) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId); // Hasta ID'si ekleniyor
            stmt.setString(2, patientName);
            stmt.setString(3, referralHospital);
            stmt.setInt(4, doctorID); // doctorID burada int olarak ayarlanmalı
            stmt.setString(5, referralReason);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<String> getReferralReportsByPatientId(int patientId) {
        List<String> reports = new ArrayList<>();
        String query = "SELECT * FROM referrals WHERE patient_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String report = "Hasta Adı: " + rs.getString("patient_name") +
                        "\nSevk Edilen Hastane: " + rs.getString("referral_hospital") +
                        "\nDoktor ID: " + rs.getInt("doctor_id") + // doctor_id burada int olarak alınmalı
                        "\nSevk Nedeni: " + rs.getString("referral_reason");
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }



}
