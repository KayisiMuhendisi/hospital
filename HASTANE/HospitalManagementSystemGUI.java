import javax.swing.*;
import java.awt.*;

public class HospitalManagementSystemGUI extends JFrame {

    public HospitalManagementSystemGUI() {
        setTitle("Hastane Yönetim Sistemi");

        // CardLayout ve JPanel parametrelerini oluşturuyoruz
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // MainMenuPanel'i doğru parametrelerle ekliyoruz
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout, mainPanel);
        mainPanel.add(mainMenuPanel, "MainMenu");

        // Ana paneli ekliyoruz
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Main method
        new HospitalManagementSystemGUI();
    }
}
