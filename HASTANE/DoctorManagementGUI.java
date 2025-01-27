import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorManagementGUI extends JPanel {
    private JTextField doctorNameField, doctorIdField;
    private JButton addDoctorButton, removeDoctorButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Veritabanı bağlantısı için Database nesnesi

    public DoctorManagementGUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // Veritabanı bağlantısını başlat
        this.database = new Database();

        setLayout(new BorderLayout()); // BorderLayout kullanarak ana düzeni ayarladık

        // Form alanlarını oluştur
        JPanel formPanel = new JPanel(new GridLayout(2, 2)); // 2 satır, 2 sütun
        formPanel.add(new JLabel("Doktor Adı:"));
        doctorNameField = new JTextField();
        doctorNameField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(doctorNameField);

        formPanel.add(new JLabel("Doktor ID:"));
        doctorIdField = new JTextField();
        doctorIdField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(doctorIdField);

        add(formPanel, BorderLayout.CENTER); // Form panelini merkeze ekle

        // Butonları oluştur
        addDoctorButton = new JButton("Doktor Ekle");
        removeDoctorButton = new JButton("Doktor Sil");
        geriButton = new JButton("Geri");

        // Butonların boyutlarını ayarla
        Dimension buttonSize = new Dimension(120, 40);
        addDoctorButton.setPreferredSize(buttonSize);
        removeDoctorButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        // Butonları içeren paneli oluştur
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Butonları ortala
        buttonPanel.add(addDoctorButton);
        buttonPanel.add(removeDoctorButton);
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

        // Doktor ekleme işlemi
        addDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorName = doctorNameField.getText();
                String doctorIdText = doctorIdField.getText();
                try {
                    int doctorId = Integer.parseInt(doctorIdText); // ID'yi int'e çevir
                    if (!doctorName.isEmpty()) {
                        // Veritabanına doktor ekle
                        database.addDoctor(doctorId, doctorName); // addPatient fonksiyonu yerine addDoctor kullanılabilir
                        JOptionPane.showMessageDialog(
                                DoctorManagementGUI.this,
                                "Doktor başarıyla eklendi: \nAdı: " + doctorName + "\nID: " + doctorId,
                                "Bilgi",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        doctorNameField.setText("");
                        doctorIdField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(
                                DoctorManagementGUI.this,
                                "Lütfen doktor adı girin!",
                                "Hata",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            DoctorManagementGUI.this,
                            "Lütfen geçerli bir Doktor ID'si girin!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Doktor silme işlemi
        removeDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorIdText = doctorIdField.getText();
                try {
                    int doctorId = Integer.parseInt(doctorIdText); // ID'yi int'e çevir
                    if (doctorId > 0) {
                        // Veritabanından doktoru sil
                        database.deleteDoctor(doctorId); // deletePatient fonksiyonu yerine deleteDoctor kullanılabilir
                        JOptionPane.showMessageDialog(
                                DoctorManagementGUI.this,
                                "Doktor başarıyla silindi: ID = " + doctorId,
                                "Bilgi",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        doctorIdField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(
                                DoctorManagementGUI.this,
                                "Lütfen geçerli bir Doktor ID'si girin!",
                                "Hata",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            DoctorManagementGUI.this,
                            "Lütfen geçerli bir Doktor ID'si girin!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}
