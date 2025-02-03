import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddProductApp {

    private static JTextField nameField;
    private static JComboBox<String> categoryDropdown;
    private static JTextField priceField;
    private static JTextField reorderField;
    private static JCheckBox statusCheckbox;
    private static JTextArea detailsField;
    private static JButton updateButton;
    private static String productId; // To store the ID of the product being updated

    public static void showAddProductDialog() {
        showProductDialog(null);
    }

    public static void showEditProductDialog(String id) {
        productId = id;
        showProductDialog(id);
    }

    private static void showProductDialog(String id) {
        JDialog dialog = new JDialog(Main.frame, id == null ? "Add Product" : "Edit Product", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(Main.frame);
        dialog.getContentPane().setBackground(new Color(0xff, 0xe5, 0xb6));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        nameField = new JTextField(20);
        categoryDropdown = new JComboBox<>(getCategories());
        priceField = new JTextField(20);
        reorderField = new JTextField(20);
        statusCheckbox = new JCheckBox("Available", true);
        detailsField = new JTextArea(3, 20);
        detailsField.setLineWrap(true);
        detailsField.setWrapStyleWord(true);
        JScrollPane detailsScrollPane = new JScrollPane(detailsField);

        JButton addButton = new JButton(id == null ? "Add" : "Update");
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(0x16, 0x9d, 0x53));

        if (id != null) {
            // Pre-fill the dialog with product data
            prefillProductData(id);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("PRODUCT NAME:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("CATEGORY:"), gbc);
        gbc.gridx = 1;
        dialog.add(categoryDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("PRICE:"), gbc);
        gbc.gridx = 1;
        dialog.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("REORDER LEVEL:"), gbc);
        gbc.gridx = 1;
        dialog.add(reorderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(new JLabel("STATUS:"), gbc);
        gbc.gridx = 1;
        dialog.add(statusCheckbox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        dialog.add(new JLabel("DETAILS:"), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 2;
        dialog.add(detailsScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        dialog.add(addButton, gbc);

        addButton.addActionListener((ActionEvent e) -> {
            String productName = nameField.getText().trim();
            String category = (String) categoryDropdown.getSelectedItem();
            String priceText = priceField.getText().trim();
            String reorderText = reorderField.getText().trim();
            boolean status = statusCheckbox.isSelected();
            String details = detailsField.getText().trim();

            if (productName.isEmpty() || category == null || priceText.isEmpty() || reorderText.isEmpty() || details.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                int reorderLevel = Integer.parseInt(reorderText);

                if (id == null) {
                    // Add new product
                    if (saveNewProduct(productName, category, price, reorderLevel, status, details)) {
                        JOptionPane.showMessageDialog(dialog, "New product added: " + productName, "Success", JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Update existing product
                    if (updateProduct(productId, productName, category, price, reorderLevel, status, details)) {
                        JOptionPane.showMessageDialog(dialog, "Product updated: " + productName, "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                        Products.addTheProds();
                        Products.prodaddpanel();
                        Stocks.functionTable();
                        dialog.dispose();
                                                
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Failed to update product.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Price and reorder level must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private static void prefillProductData(String id) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT name, category, price, reorder_level, status, description FROM products WHERE product_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                categoryDropdown.setSelectedItem(resultSet.getString("category"));
                priceField.setText(String.valueOf(resultSet.getDouble("price")));
                reorderField.setText(String.valueOf(resultSet.getInt("reorder_level")));
                statusCheckbox.setSelected(resultSet.getBoolean("status"));
                detailsField.setText(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean saveNewProduct(String productName, String category, double price, int reorderLevel, boolean status, String details) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO products (name, category, price, reorder_level, status, description) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, category);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, reorderLevel);
            preparedStatement.setBoolean(5, status);
            preparedStatement.setString(6, details);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean updateProduct(String id, String productName, String category, double price, int reorderLevel, boolean status, String details) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "UPDATE products SET name = ?, category = ?, price = ?, reorder_level = ?, status = ?, description = ? WHERE product_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, category);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, reorderLevel);
            preparedStatement.setBoolean(5, status);
            preparedStatement.setString(6, details);
            preparedStatement.setString(7, id);
                        
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    private static String[] getCategories() {
        List<String> categories = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT category_name FROM categories";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                categories.add(resultSet.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories.toArray(new String[0]);
    }
}