import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskManagementGUI extends JPanel {
    private JTextField nurseIdField, taskDescriptionField;
    private JButton assignTaskButton, viewAssignedTasksButton, geriButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Database database; // Veritabanı bağlantısı

    public TaskManagementGUI(CardLayout cardLayout, JPanel mainPanel, Database database) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.database = database;

        setLayout(new GridLayout(5, 2, 10, 10));

        // Hemşire ID
        add(new JLabel("Hemşire ID:"));
        nurseIdField = new JTextField();
        add(nurseIdField);

        // Görev Açıklaması
        add(new JLabel("Görev Açıklaması:"));
        taskDescriptionField = new JTextField();
        add(taskDescriptionField);

        // Butonları tanımlama
        assignTaskButton = new JButton("Görev Ata");
        viewAssignedTasksButton = new JButton("Atanmış Görevler");
        geriButton = new JButton("Geri");

        // Buton boyutlarını daha da genişletme
        Dimension buttonSize = new Dimension(160, 40);
        assignTaskButton.setPreferredSize(buttonSize);
        viewAssignedTasksButton.setPreferredSize(buttonSize);
        geriButton.setPreferredSize(buttonSize);

        // Butonları panelde yerleştirme
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(assignTaskButton);
        buttonPanel.add(viewAssignedTasksButton);
        buttonPanel.add(geriButton);

        // Buton panelini ekleme
        add(buttonPanel);

        // Geri butonunun işlevi
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        // Görev Atama işlemi
        assignTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTask();
            }
        });

        // Atanmış görevleri görüntüleme işlemi
        viewAssignedTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAssignedTasks();
            }
        });
    }

    private void assignTask() {
        String nurseId = nurseIdField.getText();
        String taskDescription = taskDescriptionField.getText();

        if (nurseId.isEmpty() || taskDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int nurseIdInt = Integer.parseInt(nurseId);

            // Görevi veritabanına kaydet
            database.assignTask(nurseIdInt, taskDescription);

            // Başarı mesajı
            JOptionPane.showMessageDialog(this, "Görev Atandı: Hemşire ID " + nurseIdInt + " - " + taskDescription);

            // Alanları temizle
            nurseIdField.setText("");
            taskDescriptionField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçerli bir Hemşire ID'si girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAssignedTasks() {
        // Atanmış görevleri veritabanından al
        List<Task> tasks = database.getAssignedTasks();

        StringBuilder tasksInfo = new StringBuilder();
        for (Task task : tasks) {
            tasksInfo.append(task.toString()).append("\n\n");
        }

        if (tasksInfo.length() > 0) {
            JOptionPane.showMessageDialog(this, tasksInfo.toString(), "Atanmış Görevler", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Henüz atanmış görev yok.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
