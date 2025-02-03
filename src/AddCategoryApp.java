import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCategoryApp {

    public static void showAddCategoryDialog() {
        showCategoryDialog(null, null);
    }

    public static void showEditCategoryDialog(String categoryId, String categoryName) {
        showCategoryDialog(categoryId, categoryName);
    }

    private static void showCategoryDialog(String categoryId, String categoryName) {
        JDialog dialog = new JDialog(Main.frame, categoryId == null ? "Add Category" : "Edit Category", true);
        dialog.setSize(350, 200);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(Main.frame);
        dialog.getContentPane().setBackground(new Color(0xff, 0xe5, 0xb6));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField categoryNameField = new JTextField(20);
        categoryNameField.setBorder(new EmptyBorder(3, 3, 3, 3));

        // Pre-fill the category name if editing
        if (categoryName != null) {
            categoryNameField.setText(categoryName);
        }

        JButton saveButton = new JButton(categoryId == null ? "Add" : "Update");
        saveButton.setPreferredSize(new Dimension(80, 30));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(0x16, 0x9d, 0x53));
        saveButton.setFocusable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("CATEGORY NAME:"), gbc);
        gbc.gridx = 1;
        dialog.add(categoryNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        dialog.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCategoryName = categoryNameField.getText().trim();

                if (newCategoryName.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Category name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (categoryId == null) {
                        // Add new category
                        if (saveNewCategory(newCategoryName)) {
                            JOptionPane.showMessageDialog(dialog, "New category added: " + newCategoryName, "Success", JOptionPane.INFORMATION_MESSAGE);
                            dialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Failed to add category.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Update existing category
                        if (updateCategory(categoryId, newCategoryName)) {
                            JOptionPane.showMessageDialog(dialog, "Category updated: " + newCategoryName, "Success", JOptionPane.INFORMATION_MESSAGE);
                            
                            Products.addTheCats();
                            Products.cataddpanel();
                            
                            dialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Failed to update category.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        dialog.setVisible(true);
    }

    private static boolean saveNewCategory(String categoryName) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO categories (category_name) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, categoryName);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean updateCategory(String categoryId, String categoryName) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, categoryName);
            preparedStatement.setString(2, categoryId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}