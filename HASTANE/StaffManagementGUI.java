import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffManagementGUI extends JPanel {
    private JTextField nameField, idField, roleField;
    private JPanel formPanel;

    public StaffManagementGUI(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        // Başlık
        JLabel titleLabel = new JLabel("Personel Yönetimi", JLabel.LEFT); // Sol hizalama
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Butonlar Paneli
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10)); // Butonlar için GridLayout

        JButton addButton = new JButton("Personel Ekle");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddForm();
            }
        });

        JButton updateButton = new JButton("Personel Güncelle");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateForm();
            }
        });

        JButton deleteButton = new JButton("Personel Sil");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteForm();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.NORTH); // Butonları üst tarafa ekliyoruz

        // Form Paneli (Başlangıçta görünmesin)
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Form düzeni

        // Form alanları
        JLabel nameLabel = new JLabel("Adı:");
        nameField = new JTextField();
        JLabel idLabel = new JLabel("Kimlik Numarası:");
        idField = new JTextField();
        JLabel roleLabel = new JLabel("Görev:");
        roleField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(roleLabel);
        formPanel.add(roleField);

        // Butonları ekliyoruz
        JButton saveButton = new JButton("Onayla");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Personel ekleme veya güncelleme işlemi
                if (nameField.isEditable()) {
                    showToast("Personel Kaydedildi");
                } else {
                    // Personel silme işlemi
                    performDeleteAction();
                }
            }
        });

        JButton cancelButton = new JButton("İptal");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Formu kapatma veya işlemi iptal etme
                clearForm();
                hideForm();
            }
        });

        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        // Form Paneli başlangıçta görünmesin
        formPanel.setVisible(false);

        add(formPanel, BorderLayout.CENTER); // Form alanını ekliyoruz

        // Geri Butonu
        JButton backButton = new JButton("Geri");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ana menüye dönme işlemi
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Personel Ekle formunu göster
    private void showAddForm() {
        // Formu görsel olarak göster
        clearForm();
        nameField.setEditable(true);
        idField.setEditable(true);
        roleField.setEditable(true);
        setFormVisible(true);
    }

    // Personel Güncelle formunu göster
    private void showUpdateForm() {
        // Formu görsel olarak göster
        clearForm();
        nameField.setEditable(true);
        idField.setEditable(true);
        roleField.setEditable(true);
        setFormVisible(true);
    }

    // Personel Sil formunu göster
    private void showDeleteForm() {
        // Formu görsel olarak göster
        clearForm();
        nameField.setEditable(false); // Silme işleminde sadece kimlik numarası düzenlenebilir
        idField.setEditable(true);
        roleField.setEditable(false);
        setFormVisible(true);
    }

    // Formu gösterme işlemi
    private void setFormVisible(boolean isVisible) {
        formPanel.setVisible(isVisible); // Burada sadece formPanel'in görünürlüğünü kontrol ediyoruz
    }

    // Formu temizle
    private void clearForm() {
        nameField.setText("");
        idField.setText("");
        roleField.setText("");
    }

    // Toast mesajını gösteren fonksiyon
    private void showToast(String message) {
        JOptionPane.showMessageDialog(this, message, "İşlem Sonucu", JOptionPane.INFORMATION_MESSAGE);
    }

    // Formu gizle
    private void hideForm() {
        setFormVisible(false);
    }

    // Personel Silme işlemi için doğru mesajı göster
    private void performDeleteAction() {
        // Personel silme işlemi burada yapılır (örneğin, veritabanından silme)
        String idToDelete = idField.getText();
        if (idToDelete.isEmpty()) {
            showToast("Lütfen silinecek personelin kimlik numarasını girin.");
        } else {
            showToast("Personel Silindi"); // Bu mesaj silme işlemine göre değiştirilebilir
            clearForm();
            hideForm();
        }
    }
}
