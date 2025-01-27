import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmergencyManagementGUI extends JPanel {
    private JTextField patientIdField, doctorIdField;
    private JButton assignToDoctorButton, viewEmergencyCasesButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Veritabanı bağlantısı

    public EmergencyManagementGUI(CardLayout cardLayout, JPanel mainPanel, Database database) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.database = database;

        setLayout(new GridLayout(5, 2, 10, 10)); // 5 satır, 2 sütun düzeni, 10px aralık

        // Hasta ID
        add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        add(patientIdField);

        // Doktor ID
        add(new JLabel("Doktor ID:"));
        doctorIdField = new JTextField();
        add(doctorIdField);

        // Butonları tanımlama
        assignToDoctorButton = new JButton("Acil Durum Ata");
        viewEmergencyCasesButton = new JButton("Acil Durumlar");
        geriButton = new JButton("Geri");

        // Buton boyutlarını küçültme
        Dimension buttonSize = new Dimension(120, 40); // Buton boyutları
        assignToDoctorButton.setPreferredSize(buttonSize);
        viewEmergencyCasesButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        // Butonları panelde yerleştirme
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Ortalamak için
        buttonPanel.add(assignToDoctorButton);
        buttonPanel.add(viewEmergencyCasesButton);
        buttonPanel.add(geriButton);

        // Buton panelini ekleme
        add(buttonPanel);

        // Geri butonunun işlevi
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));

        // Acil durumu doktora atama işlemi
        assignToDoctorButton.addActionListener(e -> assignToDoctor());

        // Acil durumları görüntüleme işlemi
        viewEmergencyCasesButton.addActionListener(e -> viewEmergencyCases());
    }

    private void assignToDoctor() {
        String patientId = patientIdField.getText();
        String doctorId = doctorIdField.getText();

        // Acil durumu doktora atama işlemi
        try {
            int patientIdInt = Integer.parseInt(patientId);
            int doctorIdInt = Integer.parseInt(doctorId);

            // Veritabanına kaydet
            database.assignEmergencyCase(patientIdInt, doctorIdInt);

            JOptionPane.showMessageDialog(this, "Acil Durum Atandı: Hasta ID " + patientId + " - Doktor ID " + doctorId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçerli bir Hasta ID ve Doktor ID'si girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewEmergencyCases() {
        // Acil durumları görüntülemek için veritabanından verileri al
        List<String> emergencyCases = database.getEmergencyCases();

        // Acil durumu görüntülemek için yeni bir panel oluştur
        JPanel emergencyPanel = new JPanel(new BorderLayout());

        // TestArea oluşturma
        JTextArea historyArea = new JTextArea(15, 40);
        historyArea.setEditable(false);
        for (String emergencyCase : emergencyCases) {
            historyArea.append(emergencyCase + "\n");
        }

        // ScrollPane ile ekrana sığdırma
        JScrollPane scrollPane = new JScrollPane(historyArea);
        emergencyPanel.add(scrollPane, BorderLayout.CENTER);

        // Geri butonunu oluşturma
        JButton backButton = new JButton("Geri");
        backButton.setPreferredSize(new Dimension(120, 40));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "EmergencyManagement"));  // "EmergencyManagement" ekrana geri dönüş

        // Buton panelini oluşturma ve yerleştirme
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel.add(backButton);

        emergencyPanel.add(backButtonPanel, BorderLayout.SOUTH); // Geri butonunu alt kısma ekliyoruz

        // Paneli mainPanel'e ekle
        mainPanel.add(emergencyPanel, "EmergencyCases");

        // Yeni paneli göster
        cardLayout.show(mainPanel, "EmergencyCases");
    }

}
