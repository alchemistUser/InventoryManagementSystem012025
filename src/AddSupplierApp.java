
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSupplierApp {

    public static void showAddSupplierDialog() {
        showSupplierDialog(null, null, null, null, null, false); // Default status is false for new suppliers
    }

    public static void showEditSupplierDialog(String supplierId, String supplierName, String companyName, String contact, String email, boolean status) {
        showSupplierDialog(supplierId, supplierName, companyName, contact, email, status);
    }

    private static void showSupplierDialog(String supplierId, String supplierName, String companyName, String contact, String email, boolean status) {
        JDialog dialog = new JDialog(Main.frame, supplierId == null ? "Add Supplier" : "Edit Supplier", true);
        dialog.setSize(400, 350); // Increased height to accommodate the checkbox
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(Main.frame);
        dialog.getContentPane().setBackground(new Color(0xff, 0xe5, 0xb6));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField supplierNameField = new JTextField(20);
        JTextField companyNameField = new JTextField(20);
        JTextField contactField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JCheckBox statusCheckbox = new JCheckBox("Active", status); // Checkbox for status

        supplierNameField.setBorder(new EmptyBorder(3, 3, 3, 3));
        companyNameField.setBorder(new EmptyBorder(3, 3, 3, 3));
        contactField.setBorder(new EmptyBorder(3, 3, 3, 3));
        emailField.setBorder(new EmptyBorder(3, 3, 3, 3));

        // Pre-fill fields if editing
        if (supplierId != null) {
            supplierNameField.setText(supplierName);
            companyNameField.setText(companyName);
            contactField.setText(contact);
            emailField.setText(email);
            statusCheckbox.setSelected(status); // Set checkbox state
        }

        JButton saveButton = new JButton(supplierId == null ? "Add" : "Update");
        saveButton.setPreferredSize(new Dimension(80, 30));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(0x16, 0x9d, 0x53));
        saveButton.setFocusable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("SUPPLIER NAME:"), gbc);
        gbc.gridx = 1;
        dialog.add(supplierNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("COMPANY NAME:"), gbc);
        gbc.gridx = 1;
        dialog.add(companyNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("CONTACT NO:"), gbc);
        gbc.gridx = 1;
        dialog.add(contactField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("EMAIL:"), gbc);
        gbc.gridx = 1;
        dialog.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(new JLabel("STATUS:"), gbc);
        gbc.gridx = 1;
        dialog.add(statusCheckbox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        dialog.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSupplierName = supplierNameField.getText();
                String newCompanyName = companyNameField.getText();
                String newContact = contactField.getText();
                String newEmail = emailField.getText();
                boolean newStatus = statusCheckbox.isSelected(); // Get checkbox state

                if (newSupplierName.isEmpty() || newCompanyName.isEmpty() || newContact.isEmpty() || newEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (supplierId == null) {
                        // Add new supplier
                        if (saveNewSupplier(newSupplierName, newCompanyName, newContact, newEmail, newStatus)) {
                            JOptionPane.showMessageDialog(dialog, "New supplier added: " + newSupplierName, "Success", JOptionPane.INFORMATION_MESSAGE);
                            dialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Failed to add supplier.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Update existing supplier
                        if (updateSupplier(supplierId, newSupplierName, newCompanyName, newContact, newEmail, newStatus)) {
                            JOptionPane.showMessageDialog(dialog, "Supplier updated: " + newSupplierName, "Success", JOptionPane.INFORMATION_MESSAGE);
                            Suppliers.addTheSuppliers(); // Refresh the supplier list
                            dialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Failed to update supplier.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        dialog.setVisible(true);
    }

    private static boolean saveNewSupplier(String supplierName, String companyName, String contact, String email, boolean status) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO suppliers (supplier_name, supplier_company_name, contact_no, email, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, supplierName);
            preparedStatement.setString(2, companyName);
            preparedStatement.setString(3, contact);
            preparedStatement.setString(4, email);
            preparedStatement.setBoolean(5, status); // Set status

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean updateSupplier(String supplierId, String supplierName, String companyName, String contact, String email, boolean status) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "UPDATE suppliers SET supplier_name = ?, supplier_company_name = ?, contact_no = ?, email = ?, status = ? WHERE supplier_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, supplierName);
            preparedStatement.setString(2, companyName);
            preparedStatement.setString(3, contact);
            preparedStatement.setString(4, email);
            preparedStatement.setBoolean(5, status); // Set status
            preparedStatement.setString(6, supplierId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
