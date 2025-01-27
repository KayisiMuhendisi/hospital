import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ManagementSystemsPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ManagementSystemsPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        // Başlık
        JLabel titleLabel = new JLabel("Yönetim Sistemleri", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Yönetim Butonları Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Dikey düzen

        // Personel Yönetimi Butonu
        JButton staffButton = new JButton("Personel Yönetimi");
        staffButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "StaffManagement"); // Personel yönetimine geçiş
        });

        // Hasta Yönetimi Butonu
        JButton patientButton = new JButton("Hasta Yönetimi");
        patientButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "PatientManagement"); // Hasta yönetimine geçiş
        });

        // Doktor Yönetimi Butonu
        JButton doctorButton = new JButton("Doktor Yönetimi");
        doctorButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "DoctorManagement"); // Doktor yönetimine geçiş
        });

        // Tedavi Yönetimi Butonu
        JButton treatmentButton = new JButton("Tedavi Yönetimi");
        treatmentButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "TreatmentManagement"); // Tedavi yönetimine geçiş
        });

        // Randevu Yönetimi Butonu
        JButton appointmentButton = new JButton("Randevu Yönetimi");
        appointmentButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "AppointmentManagement"); // Randevu yönetimine geçiş
        });

        // Butonları ekliyoruz
        buttonPanel.add(staffButton);
        buttonPanel.add(patientButton);
        buttonPanel.add(doctorButton);
        buttonPanel.add(treatmentButton);
        buttonPanel.add(appointmentButton);

        // Button Paneli ekleniyor
        add(buttonPanel, BorderLayout.CENTER);

        // Geri Butonu
        JButton backButton = new JButton("Geri");
        backButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel, "MainMenu"); // Ana menüye dönüş
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
