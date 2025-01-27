import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class MainMenu extends JFrame {
    private CardLayout cardLayout;
    private JPanel panel;

    public MainMenu() {
        // MainMenu'nin yapıcı metodunda CardLayout ve JPanel oluşturuluyor
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        // MainMenuPanel'i oluştururken CardLayout ve JPanel'i geçiyoruz
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout, panel);

        // Ana pencereye MainMenuPanel'i ekliyoruz
        add(mainMenuPanel, BorderLayout.CENTER);

        // Ana pencerenin özelliklerini ayarlıyoruz
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir
    }


}
