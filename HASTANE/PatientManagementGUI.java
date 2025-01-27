import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class PatientManagementGUI extends JPanel {
    private JTextField patientNameField, patientIdField;
    private JButton addPatientButton, removePatientButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Veritabanı bağlantısı için Database nesnesi

    public PatientManagementGUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Veritabanı bağlantısını başlat
        this.database = new Database();

        setLayout(new BorderLayout()); // BorderLayout kullanarak ana düzeni ayarladık

        // Form alanlarını oluştur
        JPanel formPanel = new JPanel(new GridLayout(2, 2)); // 2 satır, 2 sütun
        formPanel.add(new JLabel("Hasta Adı:"));
        patientNameField = new JTextField();
        patientNameField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(patientNameField);

        formPanel.add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        patientIdField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(patientIdField);

        add(formPanel, BorderLayout.CENTER); // Form panelini merkeze ekle

        // Butonları oluştur
        addPatientButton = new JButton("Hasta Ekle");
        removePatientButton = new JButton("Hasta Sil");
        geriButton = new JButton("Geri");

        // Butonların boyutlarını ayarla
        Dimension buttonSize = new Dimension(120, 40);
        addPatientButton.setPreferredSize(buttonSize);
        removePatientButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        // Butonları içeren paneli oluştur
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Butonları ortala
        buttonPanel.add(addPatientButton);
        buttonPanel.add(removePatientButton);
        buttonPanel.add(geriButton);

        add(buttonPanel, BorderLayout.SOUTH); // Buton panelini alt kısma ekle

        // Geri butonunun işlevi
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ana menüye geri dön
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        // Hasta ekleme işlemi
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                String patientIdText = patientIdField.getText();
                try {
                    int patientId = Integer.parseInt(patientIdText); // ID'yi int'e çevir
                    if (!patientName.isEmpty()) {
                        // Veritabanına hasta ekle
                        database.addPatient(patientId, patientName);
                        JOptionPane.showMessageDialog(
                                PatientManagementGUI.this,
                                "Hasta başarıyla eklendi: \nAdı: " + patientName + "\nID: " + patientId,
                                "Bilgi",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        patientNameField.setText("");
                        patientIdField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(
                                PatientManagementGUI.this,
                                "Lütfen hasta adı girin!",
                                "Hata",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            PatientManagementGUI.this,
                            "Lütfen geçerli bir Hasta ID'si girin!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Hasta silme işlemi
        removePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientIdText = patientIdField.getText();
                try {
                    int patientId = Integer.parseInt(patientIdText); // ID'yi int'e çevir
                    if (patientId > 0) {
                        // Veritabanından hastayı sil
                        database.deletePatient(patientId);
                        JOptionPane.showMessageDialog(
                                PatientManagementGUI.this,
                                "Hasta başarıyla silindi: ID = " + patientId,
                                "Bilgi",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        patientIdField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(
                                PatientManagementGUI.this,
                                "Lütfen geçerli bir Hasta ID'si girin!",
                                "Hata",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            PatientManagementGUI.this,
                            "Lütfen geçerli bir Hasta ID'si girin!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}
