import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class new_registration extends JFrame {

    // GUI components
    private JLabel titleLabel, nameLabel, courseLabel, semLabel;
    private JTextField nameField, courseField, semField;
    private JButton saveButton, closeButton;

    // Constructor to set up the GUI components
    public new_registration() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Student Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        titleLabel = new JLabel("Student Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titleLabel, gbc);

        nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameField, gbc);

        courseLabel = new JLabel("Course:");
        courseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(courseLabel, gbc);

        courseField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(courseField, gbc);

        semLabel = new JLabel("Semester:");
        semLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(semLabel, gbc);

        semField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(semField, gbc);

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(saveButton, gbc);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudentRecord();
            }
        });

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(closeButton, gbc);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void saveStudentRecord() {
        String name = nameField.getText();
        String course = courseField.getText();
        String sem = semField.getText();

        String url = "jdbc:mysql://localhost:3306/your_database"; // Update with your DB URL
        String user = "your_username"; // Update with your DB username
        String password = "your_password"; // Update with your DB password

        String query = "INSERT INTO student (name, course, sem) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, course);
            pst.setString(3, sem);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Record Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistration.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Failed to save record", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        courseField.setText("");
        semField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRegistration().setVisible(true);
            }
        });
    }
}
