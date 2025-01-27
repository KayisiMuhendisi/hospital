import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreatmentManagementGUI extends JPanel {
    private JTextField treatmentNameField, patientIdField, doctorIdField;
    private JButton addTreatmentButton, updateTreatmentButton;

    public TreatmentManagementGUI() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Tedavi Adı:"));
        treatmentNameField = new JTextField();
        add(treatmentNameField);

        add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        add(patientIdField);

        add(new JLabel("Doktor ID:"));
        doctorIdField = new JTextField();
        add(doctorIdField);

        addTreatmentButton = new JButton("Tedavi Ekle");
        updateTreatmentButton = new JButton("Tedavi Güncelle");

        add(addTreatmentButton);
        add(updateTreatmentButton);

        addTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tedavi ekleme işlemi
            }
        });

        updateTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tedavi güncelleme işlemi
            }
        });
    }
}
