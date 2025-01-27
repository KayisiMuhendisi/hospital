import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Yapıcı metot: CardLayout ve JPanel parametreleri alır
    public MainMenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        // Butonlar ve event listener'lar
        JButton patientManagementButton = new JButton("Hasta Yönetimi");
        patientManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("PatientManagement")) {
                    PatientManagementGUI patientPanel = new PatientManagementGUI(cardLayout, mainPanel);
                    mainPanel.add(patientPanel, "PatientManagement");
                }
                cardLayout.show(mainPanel, "PatientManagement");
            }
        });

        JButton doctorManagementButton = new JButton("Doktor Yönetimi");
        doctorManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("DoctorManagement")) {
                    DoctorManagementGUI doctorPanel = new DoctorManagementGUI(cardLayout, mainPanel);
                    mainPanel.add(doctorPanel, "DoctorManagement");
                }
                cardLayout.show(mainPanel, "DoctorManagement");
            }
        });

        JButton appointmentManagementButton = new JButton("Randevu Yönetimi");
        appointmentManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("AppointmentManagement")) {
                    AppointmentManagementGUI appointmentPanel = new AppointmentManagementGUI(cardLayout, mainPanel);
                    mainPanel.add(appointmentPanel, "AppointmentManagement");
                }
                cardLayout.show(mainPanel, "AppointmentManagement");
            }
        });

        // Yeni GUI'ler için butonlar
        JButton treatmentManagementButton = new JButton("Tedavi ve İlaç Yönetimi");
        treatmentManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("TreatmentManagement")) {
                    TreatmentAndMedicationManagementGUI treatmentPanel = new TreatmentAndMedicationManagementGUI(cardLayout, mainPanel,new Database());
                    mainPanel.add(treatmentPanel, "TreatmentManagement");
                }
                cardLayout.show(mainPanel, "TreatmentManagement");
            }
        });

        JButton taskManagementButton = new JButton("Görev Yönetimi");
        taskManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("TaskManagement")) {
                    TaskManagementGUI taskPanel = new TaskManagementGUI(cardLayout, mainPanel,new Database());
                    mainPanel.add(taskPanel, "TaskManagement");
                }
                cardLayout.show(mainPanel, "TaskManagement");
            }
        });

        JButton emergencyManagementButton = new JButton("Acil Durum Yönetimi");
        emergencyManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("EmergencyManagement")) {
                    EmergencyManagementGUI emergencyPanel = new EmergencyManagementGUI(cardLayout, mainPanel,new Database());
                    mainPanel.add(emergencyPanel, "EmergencyManagement");
                }
                cardLayout.show(mainPanel, "EmergencyManagement");
            }
        });

        JButton billingButton = new JButton("Sigorta ve Faturalandırma");
        billingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("Billing")) {
                    BillingInsuranceManagementGUI billingPanel = new BillingInsuranceManagementGUI(cardLayout, mainPanel);
                    mainPanel.add(billingPanel, "Billing");
                }
                cardLayout.show(mainPanel, "Billing");
            }
        });

        JButton labResultsButton = new JButton("Laboratuvar Sonuçları");
        labResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("LabResults")) {
                    LabResultManagementGUI labResultsPanel = new LabResultManagementGUI(cardLayout, mainPanel);
                    mainPanel.add(labResultsPanel, "LabResults");
                }
                cardLayout.show(mainPanel, "LabResults");
            }
        });

        JButton referralButton = new JButton("Hasta Yönlendirme");
        referralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPanelAdded("Referral")) {
                    ReferralManagementGUI referralPanel = new ReferralManagementGUI(cardLayout, mainPanel,new Database());
                    mainPanel.add(referralPanel, "Referral");
                }
                cardLayout.show(mainPanel, "Referral");
            }
        });


        // Butonlar için panel oluşturma
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 1, 5, 5)); // 9 buton ekleniyor, aralarına boşluk koyduk
        buttonPanel.add(patientManagementButton);
        buttonPanel.add(doctorManagementButton);
        buttonPanel.add(appointmentManagementButton);
        buttonPanel.add(treatmentManagementButton);
        buttonPanel.add(taskManagementButton);
        buttonPanel.add(emergencyManagementButton);
        buttonPanel.add(billingButton);
        buttonPanel.add(labResultsButton);
        buttonPanel.add(referralButton);


        add(buttonPanel, BorderLayout.CENTER);
    }

    // Panelin eklenip eklenmediğini kontrol eden metot
    private boolean isPanelAdded(String panelName) {
        Component[] components = mainPanel.getComponents();
        for (Component component : components) {
            if (component.getName() != null && component.getName().equals(panelName)) {
                return true; // Panel zaten eklenmiş
            }
        }
        return false; // Panel eklenmemiş
    }
}
