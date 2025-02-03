import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RestockingTransactionDialog extends JDialog {
    private JTextField productIdField;
    private JTextField quantityField;
    private JComboBox<String> transactionTypeBox;
    private JButton addTransactionButton;

    public RestockingTransactionDialog(Frame parent) {
        super(parent, "Restocking Transaction", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);

        // Layout setup
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        add(productIdField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Transaction Type:"));
        transactionTypeBox = new JComboBox<>(new String[]{"Restock"});
        add(transactionTypeBox);

        addTransactionButton = new JButton("Add Transaction");
        add(addTransactionButton);

        // Add action listener to the button
        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String quantity = quantityField.getText();

                if (productId.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(RestockingTransactionDialog.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (saveTransaction(productId, Integer.parseInt(quantity), "Restock")) {
                        JOptionPane.showMessageDialog(RestockingTransactionDialog.this, "Transaction added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(RestockingTransactionDialog.this, "Failed to add transaction", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private boolean saveTransaction(String productId, int quantity, String transactionType) {
        String url = "jdbc:mysql://localhost:3306/imsadmin_imsfd";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO transactions (product_id, transaction_type, quantity, transaction_date) VALUES (?, ?, ?, NOW())";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, Integer.parseInt(productId));
            preparedStatement.setString(2, transactionType);
            preparedStatement.setInt(3, quantity);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
