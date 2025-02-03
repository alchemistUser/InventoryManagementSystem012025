import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

public class Stocks {
    private static JPanel pnlStocks = new JPanel();
    private static JTable table; // Make the table a class-level variable
    private static DefaultTableModel model; // Make the table model a class-level variable
    private static TableRowSorter<TableModel> sorter; // Make the sorter a class-level variable
    private static JTextField tfSearchBar; // Make the search bar a class-level variable
    private static JCheckBox showUnavailableCheckbox = new JCheckBox("Show Unavailable"); // Add the checkbox

    public static void functionTable() {
        // Clear only the table-related components, not the entire panel
        Component[] components = pnlStocks.getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                pnlStocks.remove(component); // Remove only the JScrollPane (table)
            }
        }

        int width = Main.pnlMainWidth - 140;
        int height = 110; // Example height

        // Creating column names
        String[] columnNames = {"ID", "Name", "Category", "Price", "Quantity in Stock", "Reorder Level", "Status", "Action"};

        // Create an empty table model
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Disable editing for all columns except for the last one (Action column)
                return column == 7; // Only allow editing on the last column (Action)
            }
        };

        // Fetch data from the database
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imsadmin_imsfd", "root", "");

            // SQL query to fetch product details
            String sql = "SELECT product_id, name, category, price, quantity_in_stock, reorder_level, status FROM products";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String productId = resultSet.getString("product_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity_in_stock");
                String reorder = resultSet.getString("reorder_level");
                String status = resultSet.getString("status");

                // Convert status to "Available" or "Unavailable"
                String statusText = status.equals("1") ? "Available" : "Unavailable";

                // Add the row to the table model
                model.addRow(new Object[]{productId, name, category, price, quantity, reorder, statusText, "Edit"});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage());
            }
        }

        // Create the table with the populated model
        table = new JTable(model);

        // Add TableRowSorter to enable sorting when headers are clicked
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Set a default filter to show only available products (status = 'Available')
        sorter.setRowFilter(RowFilter.regexFilter("Available", 6)); // Column 6 is the status column

        // Set a custom TableCellRenderer for the last column (where the buttons will be)
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());

        // Set a custom TableCellEditor for the last column to make buttons interactive
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));

        // Set column widths
        int colwidth = 180;
        TableColumn column = table.getColumnModel().getColumn(4); // Quantity in Stock
        column.setMinWidth(colwidth);
        column.setMaxWidth(colwidth);
        column = table.getColumnModel().getColumn(5); // Reorder Level
        column.setMinWidth(colwidth);
        column.setMaxWidth(colwidth);
        column = table.getColumnModel().getColumn(0); // ID
        column.setMinWidth(100);
        column.setMaxWidth(100);

        // Design the table header and other properties
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.getTableHeader().setBackground(Color.decode("#4CAF50")); // Customize header color
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width, 40));
        table.setGridColor(Color.white);
        table.setBackground(Color.decode("#E4E4E4"));
        Font font = new Font("Arial", Font.PLAIN, 16);
        table.setFont(font);
        table.setRowHeight(40);
        table.setSelectionBackground(Color.white);

        // Create a custom cell renderer with padding
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Add horizontal padding by setting an EmptyBorder
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
                    label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Padding
                }

                return c;
            }
        });

        // Create the table inside a JScrollPane
        table.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 160, width, 490);

        // Add the scroll pane (table) to your panel
        pnlStocks.add(scrollPane);

        // Revalidate and repaint the panel to reflect changes
        pnlStocks.revalidate();
        pnlStocks.repaint();
    }

    // Custom TableCellRenderer for the button
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true); // Make button opaque so it displays properly in the table
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString()); // Set the button text from the table cell data
            return this;
        }
    }

    // Custom TableCellEditor for the button
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String text;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setFocusable(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped(); // Stop editing when the button is clicked
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            text = (value == null) ? "" : value.toString();
            button.setText(text); // Set the button text
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Handle the button click event
                int row = table.getEditingRow();
                String productId = table.getModel().getValueAt(row, 0).toString(); // Get the product ID from the first column
                AddProductApp.showEditProductDialog(productId); // Open the edit dialog
            }
            isPushed = false;
            return text;
        }
    }

    public static JPanel pnlStocks() {
        pnlStocks.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlStocks.setLayout(null);
        pnlStocks.setBackground(Color.white);

        // Create the first panel (search bar, title, and image)
        int width = Main.pnlMainWidth - 140;
        int height = 110;
        JPanel panel = Main.createRoundedPanel(45, Main.stringcolorSelectedButton);
        panel.setBounds(70, 30, width, height);
        panel.setLayout(null);

        // Search bar
        tfSearchBar = new JTextField();
        tfSearchBar.setBounds(width - 80 - 500, 30, 500, 50);
        tfSearchBar.setFont(new Font("Arial", Font.PLAIN, 18));
        tfSearchBar.setFocusable(true); // Make the search bar focusable
        panel.add(tfSearchBar);

        // Search button
        JButton btnSearch = new JButton();
        btnSearch.setBounds(width - 80, 30, 50, 50);
        btnSearch.setIcon(new ImageIcon("src/icons/search.png"));
        btnSearch.addActionListener(e -> {
            // Filter the table based on the search text
            String searchText = tfSearchBar.getText().trim();
            if (searchText.isEmpty()) {
                sorter.setRowFilter(null); // Clear the filter if the search bar is empty
            } else {
                // Filter rows based on the search text
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });
        panel.add(btnSearch);

        // Refresh button
        JButton btnRefresh = new JButton();
        btnRefresh.setBounds(width - 80 - 60, 30, 50, 50); // Positioned to the left of the search button
        btnRefresh.setIcon(new ImageIcon("src/icons/refresh.png")); // Add a refresh icon
        btnRefresh.addActionListener(e -> {
            tfSearchBar.setText(""); // Clear the search bar
            sorter.setRowFilter(null); // Clear the filter
        });
        panel.add(btnRefresh);

        // Add the "Show Unavailable" checkbox
        showUnavailableCheckbox.setBounds(70, 140, 200, 20); // Positioned below the search bar
        showUnavailableCheckbox.setOpaque(false);
        showUnavailableCheckbox.setFocusable(false);
        showUnavailableCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter the table based on the checkbox state
                if (showUnavailableCheckbox.isSelected()) {
                    // Show all products (both available and unavailable)
                    sorter.setRowFilter(null); // Remove the filter
                } else {
                    // Show only available products (status = 'Available')
                    sorter.setRowFilter(RowFilter.regexFilter("Available", 6)); // Column 6 is the status column
                }
            }
        });
        pnlStocks.add(showUnavailableCheckbox);

        // Logo
        JLabel logo = new JLabel();
        try {
            logo = Main.createImage("src/icons/warehousegreen.png", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logo.setBounds(30, 7, 100, 100);
        panel.add(logo);

        // Title
        JPanel pnlTitle = Dashboard.createScaleTextPanel("STOCKS", 50, Main.stringthegreencolor, Font.BOLD);
        pnlTitle.setBounds(130, 7, 225, 100);
        panel.add(pnlTitle);

        pnlStocks.add(panel);

        // Load the table data
        functionTable();

        return pnlStocks;
    }

    // Custom Rounded Border Class
    static class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.draw(new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius));
            g2.dispose();
        }
    }
    
    public static void refreshTableData() {
        // Clear the existing rows in the table model
        model.setRowCount(0); // This will remove all the rows from the table model

        // Re-fetch data from the database and update the model
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imsadmin_imsfd", "root", "");

            // SQL query to fetch product details
            String sql = "SELECT product_id, name, category, price, quantity_in_stock, reorder_level, status FROM products";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and add rows to the table model
            while (resultSet.next()) {
                String productId = resultSet.getString("product_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity_in_stock");
                String reorder = resultSet.getString("reorder_level");
                String status = resultSet.getString("status");

                // Convert status to "Available" or "Unavailable"
                String statusText = status.equals("1") ? "Available" : "Unavailable";

                // Add the row to the table model
                model.addRow(new Object[]{productId, name, category, price, quantity, reorder, statusText, "Edit"});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage());
            }
        }

        // Ensure the table reflects the new model
        table.setModel(model);

        // If you have any filters applied (like showing only available products), apply the filter again
        sorter.setRowFilter(RowFilter.regexFilter("Available", 6)); // This is just an example of filtering available products

        // Revalidate and repaint the table to reflect the updated data
        table.revalidate();
        table.repaint();
    }

}