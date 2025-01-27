import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TreatmentAndMedicationManagementGUI extends JPanel {
    private JTextField patientIdField, treatmentPlanField, medicationField, medicationStockField;
    private JButton saveTreatmentButton, viewTreatmentHistoryButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Veritabanı bağlantısı

    public TreatmentAndMedicationManagementGUI(CardLayout cardLayout, JPanel mainPanel, Database database) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.database = database;

        setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(7, 2, 10, 10));

        contentPanel.add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        contentPanel.add(patientIdField);

        contentPanel.add(new JLabel("Tedavi Planı:"));
        treatmentPlanField = new JTextField();
        contentPanel.add(treatmentPlanField);

        contentPanel.add(new JLabel("Reçete Edilen İlaç:"));
        medicationField = new JTextField();
        contentPanel.add(medicationField);

        contentPanel.add(new JLabel("İlaç Stok Durumu:"));
        medicationStockField = new JTextField();
        medicationStockField.setEditable(false);
        contentPanel.add(medicationStockField);

        saveTreatmentButton = new JButton("Tedavi Kaydet");
        viewTreatmentHistoryButton = new JButton("Tedavi Geçmişi");
        geriButton = new JButton("Geri");

        Dimension buttonSize = new Dimension(160, 40);
        saveTreatmentButton.setPreferredSize(buttonSize);
        viewTreatmentHistoryButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveTreatmentButton);
        buttonPanel.add(viewTreatmentHistoryButton);
        buttonPanel.add(geriButton);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        saveTreatmentButton.addActionListener(e -> saveTreatment());

        viewTreatmentHistoryButton.addActionListener(e -> showTreatmentHistory());
    }

    private void saveTreatment() {
        String patientId = patientIdField.getText();
        String treatmentPlan = treatmentPlanField.getText();
        String medication = medicationField.getText();

        if (patientId.isEmpty() || treatmentPlan.isEmpty() || medication.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int patientIdInt = Integer.parseInt(patientId);

            // Tedavi kaydını veritabanına ekle
            database.addTreatment(patientIdInt, treatmentPlan, medication, 10);  // Örnek olarak 10 adet stok

            // Reçetelenen ilacın stok durumu güncellenir
            database.updateMedicationStock(medication);

            // Tedavi kaydının başarıyla yapıldığını göster
            JOptionPane.showMessageDialog(this, "Tedavi Kaydedildi: " + patientId + " - " + treatmentPlan);

            // Alanları temizle
            patientIdField.setText("");
            treatmentPlanField.setText("");
            medicationField.setText("");
            medicationStockField.setText("");  // Stok bilgisi temizlendi
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçerli bir Hasta ID'si girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showTreatmentHistory() {
        String patientIdText = patientIdField.getText();
        if (patientIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen hasta ID'sini girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int patientId = Integer.parseInt(patientIdText);
            List<Treatment> treatments = database.getTreatmentHistory(patientId);

            // Yeni panel oluştur
            JPanel treatmentHistoryPanel = new JPanel(new BorderLayout());

            JLabel label = new JLabel("Tedavi Geçmişi", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            treatmentHistoryPanel.add(label, BorderLayout.CENTER);

            // Tedavi geçmişi bilgilerini ekle
            JTextArea historyArea = new JTextArea(15, 40);
            historyArea.setEditable(false);
            for (Treatment treatment : treatments) {
                historyArea.append(treatment.toString() + "\n\n");
            }
            JScrollPane scrollPane = new JScrollPane(historyArea);
            treatmentHistoryPanel.add(scrollPane, BorderLayout.CENTER);

            // Geri butonu oluştur
            JButton backButton = new JButton("Geri");
            backButton.setPreferredSize(new Dimension(100, 40));
            backButton.addActionListener(e -> cardLayout.show(mainPanel, "TreatmentManagement"));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(backButton);

            treatmentHistoryPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Paneli mainPanel'e ekle
            mainPanel.add(treatmentHistoryPanel, "TreatmentHistory");

            // Yeni paneli göster
            cardLayout.show(mainPanel, "TreatmentHistory");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçerli bir Hasta ID'si girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}
