import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReferralManagementGUI extends JPanel {
    private JTextField patientNameField, referralHospitalField, doctorNameField, referralReasonField, patientIdField;
    private JButton generateReferralButton, geriButton, showReportsButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Veritabanı bağlantısı

    public ReferralManagementGUI(CardLayout cardLayout, JPanel mainPanel, Database database) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.database = database;

        setLayout(new BorderLayout());

        // Form alanları
        JPanel formPanel = new JPanel(new GridLayout(6, 2)); // 6 satır (Hasta ID için ek satır)
        formPanel.add(new JLabel("Hasta Adı:"));
        patientNameField = new JTextField();
        formPanel.add(patientNameField);

        formPanel.add(new JLabel("Sevk Edilen Hastane:"));
        referralHospitalField = new JTextField();
        formPanel.add(referralHospitalField);

        formPanel.add(new JLabel("Doktor ID:"));
        doctorNameField = new JTextField();
        formPanel.add(doctorNameField);

        formPanel.add(new JLabel("Sevk Nedeni:"));
        referralReasonField = new JTextField();
        formPanel.add(referralReasonField);

        // Hasta ID alanı (mevcut raporları görüntülemek için)
        formPanel.add(new JLabel("Hasta ID (Mevcut Raporlar için):"));
        patientIdField = new JTextField();
        formPanel.add(patientIdField);

        add(formPanel, BorderLayout.CENTER);

        // Butonlar
        generateReferralButton = new JButton("Sevk Raporu Oluştur");
        geriButton = new JButton("Geri");
        showReportsButton = new JButton("Mevcut Raporları Göster");

        Dimension buttonSize = new Dimension(160, 40);
        generateReferralButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);
        showReportsButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(generateReferralButton);
        buttonPanel.add(showReportsButton);
        buttonPanel.add(geriButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Geri butonu işlevi
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        // Sevk raporu oluşturma işlemi
        generateReferralButton.addActionListener(e -> generateReferral());

        // Mevcut raporları gösterme işlemi
        showReportsButton.addActionListener(e -> showReportsByPatientId());
    }

    private void generateReferral() {
        try {
            String patientName = patientNameField.getText();
            String referralHospital = referralHospitalField.getText();
            int doctorId = Integer.parseInt(doctorNameField.getText()); // Doktor ID'si alınır
            String referralReason = referralReasonField.getText();
            int patientId=Integer.parseInt(patientIdField.getText());

            // Veritabanına kaydetme işlemi
            database.saveReferral(patientId,patientName, referralHospital, doctorId, referralReason);

            JOptionPane.showMessageDialog(this, "Sevk raporu başarıyla oluşturuldu!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçerli bir Doktor ID girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }



    // Mevcut raporları gösterme işlemi
    private void showReportsByPatientId() {
        try {
            int patientId = Integer.parseInt(patientIdField.getText());

            // Veritabanından sevk raporlarını al
            List<String> referralReports = database.getReferralReportsByPatientId(patientId);

            if (referralReports.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bu ID için sevk raporu bulunamadı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Raporları göster
            JPanel reportsPanel = new JPanel(new BorderLayout());
            JTextArea reportsArea = new JTextArea(15, 40);
            reportsArea.setEditable(false);

            for (String report : referralReports) {
                reportsArea.append(report + "\n\n");
            }

            JScrollPane scrollPane = new JScrollPane(reportsArea);
            reportsPanel.add(scrollPane, BorderLayout.CENTER);

            // Geri butonu
            JButton backButton = new JButton("Geri");
            backButton.addActionListener(e -> {
                // "ReferralManagement" kartına geçiş
                cardLayout.show(mainPanel, "Referral");
            });

            JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            backButtonPanel.add(backButton);
            reportsPanel.add(backButtonPanel, BorderLayout.SOUTH);

            // Paneli mainPanel'e ekle
            mainPanel.add(reportsPanel, "ReferralReports");

            // "ReferralReports" kartına geçiş
            cardLayout.show(mainPanel, "ReferralReports");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçerli bir Hasta ID girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

}
