import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillingInsuranceManagementGUI extends JPanel {
    private JTextField patientIdField, insuranceField, treatmentCostField, totalCostField;
    private JButton generateInvoiceButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public BillingInsuranceManagementGUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout()); // Use BorderLayout for the main layout

        // Create a panel for form fields (patient ID, insurance, treatment cost, total cost)
        JPanel formPanel = new JPanel(new GridLayout(5, 2)); // 5 rows, 2 columns for extra fields
        formPanel.add(new JLabel("Hasta ID:"));
        patientIdField = new JTextField();
        patientIdField.setPreferredSize(new Dimension(150, 25)); // Set smaller size for input field
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Sigorta:"));
        insuranceField = new JTextField();
        insuranceField.setPreferredSize(new Dimension(150, 25)); // Set smaller size for input field
        formPanel.add(insuranceField);

        formPanel.add(new JLabel("Tedavi Ücreti:"));
        treatmentCostField = new JTextField();
        treatmentCostField.setPreferredSize(new Dimension(150, 25)); // Set smaller size for input field
        formPanel.add(treatmentCostField);

        formPanel.add(new JLabel("Sigorta Kapsamı Dışındaki Ücret:"));
        JTextField extraCostField = new JTextField();
        extraCostField.setPreferredSize(new Dimension(150, 25)); // Set smaller size for input field
        formPanel.add(extraCostField);

        formPanel.add(new JLabel("Toplam Ücret:"));
        totalCostField = new JTextField();
        totalCostField.setEditable(false);
        totalCostField.setPreferredSize(new Dimension(150, 25)); // Set smaller size for input field
        formPanel.add(totalCostField);

        add(formPanel, BorderLayout.CENTER); // Add form panel to the center

        // Create buttons
        generateInvoiceButton = new JButton("Fatura Oluştur");
        geriButton = new JButton("Geri");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(120, 40);
        generateInvoiceButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        // Create a panel for buttons and set FlowLayout to center them
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the buttons
        buttonPanel.add(generateInvoiceButton);
        buttonPanel.add(geriButton);

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom (south)

        // Geri butonunun işlevi
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // MainMenuPanel'e geri dön
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        // Fatura oluşturma işlemi
        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice(extraCostField);  // Pass the extra cost field
            }
        });
    }

    private void generateInvoice(JTextField extraCostField) {
        try {
            double treatmentCost = Double.parseDouble(treatmentCostField.getText());
            double extraCost = Double.parseDouble(extraCostField.getText());
            double totalCost = treatmentCost + extraCost;  // Calculate the total cost including extra cost
            totalCostField.setText(String.valueOf(totalCost));

            // Faturayı veritabanına kaydetme
            int patientId = Integer.parseInt(patientIdField.getText());
            saveInvoiceToDatabase(patientId, treatmentCost, extraCost, totalCost);

            // Fatura kaydını başarıyla eklediğinde mesaj göster
            JOptionPane.showMessageDialog(this, "Fatura Oluşturuldu: Toplam Ücret = " + totalCost);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bir ücret girin.");
        }
    }

    private void saveInvoiceToDatabase(int patientId, double treatmentCost, double extraCost, double totalCost) {
        String url = "jdbc:postgresql://localhost:5432/hastane_otomasyonu"; // PostgreSQL bağlantı URL'nizi buraya yazın
        String user = "postgres";  // PostgreSQL kullanıcı adınızı buraya yazın
        String password = "270892";  // PostgreSQL şifrenizi buraya yazın

        // SQL sorgusu
        String sql = "INSERT INTO invoices (patient_id, treatment_cost, extra_cost, total_cost) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, patientId);  // Hasta ID'si
            preparedStatement.setDouble(2, treatmentCost);  // Tedavi ücreti
            preparedStatement.setDouble(3, extraCost);  // Sigorta dışındaki ücret
            preparedStatement.setDouble(4, totalCost);  // Toplam ücret

            // SQL sorgusunu çalıştır
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanına fatura kaydedilemedi.");
        }
    }
}
