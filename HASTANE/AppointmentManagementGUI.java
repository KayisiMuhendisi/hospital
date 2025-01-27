import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppointmentManagementGUI extends JPanel {
    private JTextField appointmentDateField, patientIdField, doctorIdField;
    private JButton scheduleAppointmentButton, cancelAppointmentButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Database nesnesi

    public AppointmentManagementGUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.database = new Database(); // Veritabanı bağlantısını başlatıyoruz

        setLayout(new BorderLayout()); // BorderLayout kullanarak ana düzeni ayarladık

        // Form alanlarını oluştur
        JPanel formPanel = new JPanel(new GridLayout(3, 2)); // 3 satır, 2 sütun
        formPanel.add(new JLabel("Randevu Tarihi (yyyy-MM-dd HH:mm:ss):"));
        appointmentDateField = new JTextField();
        appointmentDateField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(appointmentDateField);

        formPanel.add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        patientIdField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Doktor ID:"));
        doctorIdField = new JTextField();
        doctorIdField.setPreferredSize(new Dimension(150, 25));
        formPanel.add(doctorIdField);

        add(formPanel, BorderLayout.CENTER);

        // Butonları oluştur
        scheduleAppointmentButton = new JButton("Randevu Oluştur");
        cancelAppointmentButton = new JButton("Randevu İptal");
        geriButton = new JButton("Geri");

        // Butonların boyutlarını ayarla
        Dimension buttonSize = new Dimension(150, 40);
        scheduleAppointmentButton.setPreferredSize(buttonSize);
        cancelAppointmentButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        // Butonları içeren paneli oluştur
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(scheduleAppointmentButton);
        buttonPanel.add(cancelAppointmentButton);
        buttonPanel.add(geriButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Geri butonunun işlevi
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainMenu"); // Ana menüye dön
            }
        });

        // Randevu oluşturma işlemi
        scheduleAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentDate = appointmentDateField.getText();
                String patientIdText = patientIdField.getText();
                String doctorIdText = doctorIdField.getText();

                if (!appointmentDate.isEmpty() && !patientIdText.isEmpty() && !doctorIdText.isEmpty()) {
                    try {
                        int patientId = Integer.parseInt(patientIdText);
                        int doctorId = Integer.parseInt(doctorIdText);

                        // Veritabanına randevu ekle
                        database.addAppointment(appointmentDate, patientId, doctorId);

                        JOptionPane.showMessageDialog(
                                AppointmentManagementGUI.this,
                                "Randevu başarıyla oluşturuldu:\nTarih: " + appointmentDate +
                                        "\nHasta ID: " + patientId +
                                        "\nDoktor ID: " + doctorId,
                                "Bilgi",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        appointmentDateField.setText("");
                        patientIdField.setText("");
                        doctorIdField.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                AppointmentManagementGUI.this,
                                "Lütfen geçerli bir Hasta ID ve Doktor ID'si girin!",
                                "Hata",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            AppointmentManagementGUI.this,
                            "Lütfen tüm alanları doldurun!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Randevu iptal etme işlemi
        cancelAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentDate = appointmentDateField.getText();
                String patientIdText = patientIdField.getText();

                if (!appointmentDate.isEmpty() && !patientIdText.isEmpty()) {
                    try {
                        int patientId = Integer.parseInt(patientIdText);

                        // Veritabanından randevu sil
                        database.cancelAppointment(patientId, appointmentDate);

                        JOptionPane.showMessageDialog(
                                AppointmentManagementGUI.this,
                                "Randevu başarıyla iptal edildi:\nTarih: " + appointmentDate +
                                        "\nHasta ID: " + patientId,
                                "Bilgi",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        appointmentDateField.setText("");
                        patientIdField.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                AppointmentManagementGUI.this,
                                "Lütfen geçerli bir Hasta ID'si girin!",
                                "Hata",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            AppointmentManagementGUI.this,
                            "Lütfen randevu tarihi ve hasta ID'sini girin!",
                            "Hata",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}
