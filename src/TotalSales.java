import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;

public class TotalSales {

    private static JPanel pnlTotalSales = new JPanel();
    private static JTable salesTable = new JTable();
    private static JTable restockTable = new JTable();

    public static JPanel pnlTotalSales() {
        pnlTotalSales.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlTotalSales.setLayout(null);  // Keep null layout
        pnlTotalSales.setBackground(Color.white);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);  // Keep null layout
        buttonPanel.setBounds(20, 20, pnlTotalSales.getWidth() - 40, 60);  // Position button panel
        buttonPanel.setBackground(Color.white);

        JButton salesButton = new JButton("Add Sales Transaction");
        JButton restockButton = new JButton("Add Restocking Transaction");

        salesButton.setBounds(0, 10, 200, 40);  // Position sales button
        restockButton.setBounds(220, 10, 200, 40);  // Position restock button

        buttonPanel.add(salesButton);
        buttonPanel.add(restockButton);

        pnlTotalSales.add(buttonPanel);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);  // Keep null layout
        tablePanel.setBounds(20, 100, pnlTotalSales.getWidth() - 40, pnlTotalSales.getHeight() - 150);  // Position table panel
        tablePanel.setBackground(Color.white);

        // Label for Sales Table
        JLabel salesLabel = new JLabel("Sales Transactions", JLabel.CENTER);
        salesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        salesLabel.setBounds(0, 10, tablePanel.getWidth(), 25);  // Position sales label
        tablePanel.add(salesLabel);

        // Sales Table
        JScrollPane salesScrollPane = new JScrollPane(salesTable);
        salesScrollPane.setBounds(0, 40, tablePanel.getWidth(), (tablePanel.getHeight() / 2) - 30);  // Position sales table
        tablePanel.add(salesScrollPane);

        // Label for Restocking Table
        JLabel restockLabel = new JLabel("Restocking Transactions", JLabel.CENTER);
        restockLabel.setFont(new Font("Arial", Font.BOLD, 16));
        restockLabel.setBounds(0, (tablePanel.getHeight() / 2) + 10, tablePanel.getWidth(), 25);  // Position restock label
        tablePanel.add(restockLabel);

        // Restock Table
        JScrollPane restockScrollPane = new JScrollPane(restockTable);
        restockScrollPane.setBounds(0, (tablePanel.getHeight() / 2) + 40, tablePanel.getWidth(), (tablePanel.getHeight() / 2) - 30);  // Position restock table
        tablePanel.add(restockScrollPane);

        pnlTotalSales.add(tablePanel);

        // Open sales transaction dialog
        salesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransactionDialog("Sale");
            }
        });

        // Open restocking transaction dialog
        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransactionDialog("Restock");
            }
        });

        // Call refreshTables() to load data immediately after panel setup
        refreshTables();

        return pnlTotalSales;
    }

    private static void openTransactionDialog(String transactionType) {
        JDialog dialog = new JDialog((Frame) null, transactionType + " Transaction", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(null);

        // Layout setup for the dialog
        dialog.setLayout(null);  // Keep null layout

        // Fetch product names and supplier names
        ArrayList<String> productNames = getProductNames();
        ArrayList<String> supplierNames = getSupplierNames();

        // Create JComboBoxes for product and supplier selection
        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(20, 20, 100, 25);
        dialog.add(productLabel);
        JComboBox<String> productComboBox = new JComboBox<>(productNames.toArray(new String[0]));
        productComboBox.setBounds(120, 20, 250, 25);
        dialog.add(productComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 60, 100, 25);
        dialog.add(quantityLabel);
        JTextField quantityField = new JTextField();
        quantityField.setBounds(120, 60, 250, 25);
        dialog.add(quantityField);

        // Declare supplierComboBox outside the if block so it can be final or effectively final
        final JComboBox<String> supplierComboBox;

        // Show supplier selection only for Restock (not for Sale)
        if ("Restock".equals(transactionType)) {
            JLabel supplierLabel = new JLabel("Supplier:");
            supplierLabel.setBounds(20, 100, 100, 25);
            dialog.add(supplierLabel);
            supplierComboBox = new JComboBox<>(supplierNames.toArray(new String[0]));
            supplierComboBox.setBounds(120, 100, 250, 25);
            dialog.add(supplierComboBox);
        } else {
            supplierComboBox = null;
        }

        JButton addTransactionButton = new JButton("Add Transaction");
        addTransactionButton.setBounds(120, 140, 150, 30);  // Position add transaction button
        dialog.add(addTransactionButton);

        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = (String) productComboBox.getSelectedItem();
                String quantityStr = quantityField.getText();
                String supplierName = null;

                // Only get the supplier name for restock
                if ("Restock".equals(transactionType) && supplierComboBox != null) {
                    supplierName = (String) supplierComboBox.getSelectedItem();
                }

                if (productName == null || quantityStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        if (saveTransaction(productName, supplierName, quantity, transactionType)) {
                            JOptionPane.showMessageDialog(dialog, "Transaction added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dialog.dispose();
                            refreshTables();  // Refresh tables to show new data
                            refreshQtyDisplay();
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Failed to add transaction", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Invalid quantity", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        dialog.setVisible(true);
    }

    private static ArrayList<String> getProductNames() {
        ArrayList<String> productNames = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT name FROM products WHERE status = 1";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                productNames.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productNames;
    }

    private static ArrayList<String> getSupplierNames() {
        ArrayList<String> supplierNames = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT supplier_name FROM suppliers WHERE status = 1";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                supplierNames.add(resultSet.getString("supplier_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierNames;
    }

    private static boolean saveTransaction(String productName, String supplierName, int quantity, String transactionType) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        // Get the product_id based on the product name
        int productId = getProductIdByName(productName);

        if (productId == -1) {
            JOptionPane.showMessageDialog(null, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Generate a reference number (UUID) for each transaction
        String referenceNumber = "REF-" + UUID.randomUUID().toString();

        // SQL query to insert into transactions table
        String sql = "INSERT INTO transactions (product_id, transaction_type, quantity, transaction_date, reference_no, supplier_name) VALUES (?, ?, ?, NOW(), ?, ?)";

        // If the transaction is a sale, set supplier_name to null
        if ("Sale".equals(transactionType)) {
            supplierName = null;
        }

        // Start a transaction block to ensure both operations succeed or fail together
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);  // Start transaction

            // Insert transaction into the database
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);
                preparedStatement.setString(2, transactionType);
                preparedStatement.setInt(3, quantity);
                preparedStatement.setString(4, referenceNumber);
                preparedStatement.setString(5, supplierName);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    // If it's a restock, update the product stock by adding the quantity
                    if ("Restock".equals(transactionType)) {
                        // Fetch current stock of the product
                        int currentStock = getProductStockById(productId);

                        // Update stock by adding the restock quantity
                        String updateStockSql = "UPDATE products SET quantity_in_stock = ? WHERE product_id = ?";
                        try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockSql)) {
                            updateStockStmt.setInt(1, currentStock + quantity);  // Increase stock by quantity
                            updateStockStmt.setInt(2, productId);
                            updateStockStmt.executeUpdate();
                        }
                    }

                    // If it's a sale, update the product stock by subtracting the quantity
                    if ("Sale".equals(transactionType)) {
                        // Fetch current stock of the product
                        int currentStock = getProductStockById(productId);

                        // Ensure stock is sufficient before proceeding with sale
                        if (currentStock < quantity) {
                            JOptionPane.showMessageDialog(null, "Not enough stock for sale", "Error", JOptionPane.ERROR_MESSAGE);
                            connection.rollback();  // Rollback if there's not enough stock
                            return false;
                        }

                        // Update stock by subtracting the sold quantity
                        String updateStockSql = "UPDATE products SET quantity_in_stock = ? WHERE product_id = ?";
                        try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockSql)) {
                            updateStockStmt.setInt(1, currentStock - quantity);  // Decrease stock by quantity
                            updateStockStmt.setInt(2, productId);
                            updateStockStmt.executeUpdate();
                        }
                    }

                    connection.commit();  // Commit the transaction
                    return true;
                } else {
                    connection.rollback();  // Rollback if insertion failed
                    return false;
                }

            } catch (SQLException e) {
                connection.rollback();  // Rollback in case of an error
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int getProductStockById(int productId) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT quantity_in_stock FROM products WHERE product_id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("quantity_in_stock");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;  // Return 0 if product is not found
    }

    private static int getProductIdByName(String productName) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT product_id FROM products WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("product_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Method to refresh both sales and restock tables
    private static void refreshTables() {
        // Refresh Sales Table with column headers
        DefaultTableModel salesTableModel = new DefaultTableModel();
        salesTableModel.setColumnIdentifiers(new Object[] {
            "Product Name", "Quantity", "Transaction Type", "Date", "Reference No"
        });
        salesTable.setModel(salesTableModel);

        // Refresh Restock Table with column headers
        DefaultTableModel restockTableModel = new DefaultTableModel();
        restockTableModel.setColumnIdentifiers(new Object[] {
            "Product Name", "Quantity", "Transaction Type", "Date", "Reference No", "Supplier"
        });
        restockTable.setModel(restockTableModel);

        // Fetch and add Sales records
        fetchTransactionData("Sale", salesTableModel);

        // Fetch and add Restock records
        fetchTransactionData("Restock", restockTableModel);
    }

    // Method to fetch transaction data and populate the tables
    private static void fetchTransactionData(String transactionType, DefaultTableModel tableModel) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "SELECT products.name, transactions.quantity, transactions.transaction_type, transactions.transaction_date, transactions.reference_no, transactions.supplier_name FROM transactions JOIN products ON transactions.product_id = products.product_id WHERE transactions.transaction_type = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, transactionType);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] rowData = new Object[] {
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("transaction_type"),
                        resultSet.getTimestamp("transaction_date"),
                        resultSet.getString("reference_no"),
                        resultSet.getString("supplier_name") == null ? "" : resultSet.getString("supplier_name")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to refresh the Products panel
    private static void refreshQtyDisplay() {
        Main.qtyRefresh();
    }
}