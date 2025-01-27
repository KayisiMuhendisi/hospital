import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsuranceBillingGUI extends JPanel {
    private JTextField patientIdField, insuranceProviderField, treatmentCostField, insuranceCoverageField, totalCostField;
    private JButton calculateButton, generateInvoiceButton;

    public InsuranceBillingGUI() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Sigorta ve Faturalandırma Yönetimi", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Form Paneli
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Form Alanları
        JLabel patientIdLabel = new JLabel("Hasta ID:");
        patientIdField = new JTextField();
        JLabel insuranceProviderLabel = new JLabel("Sigorta Sağlayıcı:");
        insuranceProviderField = new JTextField();
        JLabel treatmentCostLabel = new JLabel("Tedavi Maliyeti:");
        treatmentCostField = new JTextField();
        JLabel insuranceCoverageLabel = new JLabel("Sigorta Kapsamı (%):");
        insuranceCoverageField = new JTextField();
        JLabel totalCostLabel = new JLabel("Toplam Maliyet:");
        totalCostField = new JTextField();
        totalCostField.setEditable(false); // Bu alan sadece gösterim amaçlı

        formPanel.add(patientIdLabel);
        formPanel.add(patientIdField);
        formPanel.add(insuranceProviderLabel);
        formPanel.add(insuranceProviderField);
        formPanel.add(treatmentCostLabel);
        formPanel.add(treatmentCostField);
        formPanel.add(insuranceCoverageLabel);
        formPanel.add(insuranceCoverageField);
        formPanel.add(totalCostLabel);
        formPanel.add(totalCostField);

        // Butonlar
        calculateButton = new JButton("Fiyat Hesapla");
        generateInvoiceButton = new JButton("Fatura Oluştur");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalCost();
            }
        });

        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });

        // Buton Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        buttonPanel.add(generateInvoiceButton);

        // Eklemeler
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Sigorta kapsamı dışında kalan masrafları hesapla
    private void calculateTotalCost() {
        try {
            double treatmentCost = Double.parseDouble(treatmentCostField.getText());
            double insuranceCoverage = Double.parseDouble(insuranceCoverageField.getText());
            double insuranceAmount = treatmentCost * (insuranceCoverage / 100);
            double totalCost = treatmentCost - insuranceAmount;

            totalCostField.setText(String.format("%.2f", totalCost));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doğru şekilde doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fatura oluştur
    private void generateInvoice() {
        String patientId = patientIdField.getText();
        String insuranceProvider = insuranceProviderField.getText();
        double totalCost = Double.parseDouble(totalCostField.getText());

        // Fatura bilgilerini göstermek için bir dialog penceresi açalım
        String invoiceDetails = "Hasta ID: " + patientId + "\n" +
                "Sigorta Sağlayıcı: " + insuranceProvider + "\n" +
                "Toplam Maliyet: " + totalCost + " TL";

        JOptionPane.showMessageDialog(this, invoiceDetails, "Fatura Oluşturuldu", JOptionPane.INFORMATION_MESSAGE);
    }
}
