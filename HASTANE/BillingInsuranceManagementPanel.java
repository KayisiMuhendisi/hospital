import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BillingInsuranceManagementPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public BillingInsuranceManagementPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        // Başlık
        JLabel titleLabel = new JLabel("Faturalandırma Sistemi", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Faturalandırma Butonları Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Dikey düzen

        // Sigorta Bilgisi Butonu
        JButton insuranceButton = new JButton("Sigorta Bilgisi");
        insuranceButton.addActionListener((ActionEvent e) -> {
            // Sigorta bilgisi ekranına yönlendirme işlemi
        });

        // Fatura Oluştur Butonu
        JButton createInvoiceButton = new JButton("Fatura Oluştur");
        createInvoiceButton.addActionListener((ActionEvent e) -> {
            // Fatura oluşturma işlemi
        });

        // Sigorta kapsamı dışı masraflar Butonu
        JButton extraCostButton = new JButton("Sigorta Kapsamı Dışı Masraflar");
        extraCostButton.addActionListener((ActionEvent e) -> {
            // Sigorta kapsamı dışındaki masrafları gösterme
        });

        // Butonları ekliyoruz
        buttonPanel.add(insuranceButton);
        buttonPanel.add(createInvoiceButton);
        buttonPanel.add(extraCostButton);

        // Button Paneli ekleniyor
        add(buttonPanel, BorderLayout.CENTER);

        // Geri Butonu
        JButton backButton = new JButton("Geri");
        backButton.addActionListener((ActionEvent e) -> {
            // Ana menüye dönüş
            cardLayout.show(mainPanel, "MainMenu");
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
