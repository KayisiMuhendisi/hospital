import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LabResultManagementGUI extends JPanel {
    private JTextField patientIdField, resultField;
    private JComboBox<String> testTypeComboBox;
    private JButton saveResultButton, geriButton, showResultsButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database;  // Database nesnesi

    public LabResultManagementGUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new GridLayout(7, 2)); // 7 satır, 2 sütun düzeni

        // Database nesnesi oluşturuluyor
        this.database = new Database();

        // Hasta ID Girişi
        add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        add(patientIdField);

        // Test Türü Seçimi
        add(new JLabel("Test Türü:"));
        String[] testTypes = { "Kan Testi", "Röntgen", "MR", "Diğer" };
        testTypeComboBox = new JComboBox<>(testTypes); // ComboBox ile test türünü seç
        add(testTypeComboBox);

        // Sonuç
        add(new JLabel("Sonuç:"));
        resultField = new JTextField();
        add(resultField);

        // Sonuç kaydet butonu
        saveResultButton = new JButton("Sonucu Kaydet");
        add(saveResultButton);

        // Sonuçlar butonu
        showResultsButton = new JButton("Sonuçları Göster");
        add(showResultsButton);

        // Geri butonu
        geriButton = new JButton("Geri");
        add(geriButton);

        // Geri butonunun işlevi
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // MainMenuPanel'e geri dön
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        // Sonuç kaydetme işlemi
        saveResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTestResult();
            }
        });

        // Sonuçları gösterme işlemi
        showResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTestResults();
            }
        });
    }

    private void saveTestResult() {
        String patientId = patientIdField.getText(); // Girilen hasta ID
        String testType = (String) testTypeComboBox.getSelectedItem(); // Seçilen test türü
        String result = resultField.getText();

        // Sonuçları kaydetmek için işlemler yapılabilir
        if (patientId.isEmpty() || result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen hasta ID'si ve sonuç girin.");
        } else {
            boolean isSaved = database.saveTestResult(Integer.parseInt(patientId), testType, result);  // Nesne üzerinden işlem
            if (isSaved) {
                JOptionPane.showMessageDialog(this, "Test Sonucu Kaydedildi: Hasta ID: " + patientId + " - Test Türü: " + testType + " - Sonuç: " + result);
            } else {
                JOptionPane.showMessageDialog(this, "Test sonucu kaydedilemedi.");
            }
        }
    }

    private void showTestResults() {
        String patientId = patientIdField.getText(); // Girilen hasta ID

        if (patientId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen hasta ID'sini girin.");
            return;
        }

        // Test sonuçlarını veritabanından al
        ResultSet rs = database.getTestResults(Integer.parseInt(patientId));  // Nesne üzerinden işlem

        if (rs == null) {
            JOptionPane.showMessageDialog(this, "Bu hasta için test sonucu bulunmamaktadır.");
            return;
        }

        try {
            StringBuilder resultMessage = new StringBuilder("Hasta ID: " + patientId + " için test sonuçları:\n");
            while (rs.next()) {
                String testType = rs.getString("test_type");
                String result = rs.getString("result");
                resultMessage.append("Test Türü: " + testType + " - Sonuç: " + result + "\n");
            }
            JOptionPane.showMessageDialog(this, resultMessage.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
