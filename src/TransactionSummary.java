
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;

public class TransactionSummary {

    private static JPanel pnlSummary = new JPanel();
    private static JTable summaryTable = new JTable();
    private static DefaultTableModel summaryTableModel;
    private static TableRowSorter<DefaultTableModel> sorter;
    private static JTextField tfSearchBar;

    public static JPanel createSummaryPanel() {
        // Set up the panel
        pnlSummary.setSize(800, 600); // Placeholder size
        pnlSummary.setLayout(null);
        pnlSummary.setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = createRoundedPanel(45, Color.decode("#4CAF50"));
        headerPanel.setBounds(20, 20, pnlSummary.getWidth() - 40, 100);
        headerPanel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Transaction Summary", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 10, headerPanel.getWidth(), 40);
        headerPanel.add(titleLabel);

        // Search Bar
        tfSearchBar = new JTextField();
        tfSearchBar.setBounds(headerPanel.getWidth() - 550, 60, 400, 30);
        tfSearchBar.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(tfSearchBar);

        // Search Button
        JButton btnSearch = new JButton();
        btnSearch.setBounds(headerPanel.getWidth() - 100, 60, 30, 30);
        btnSearch.setIcon(new ImageIcon("src/icons/search.png"));
        btnSearch.addActionListener(e -> {
            String searchText = tfSearchBar.getText().trim();
            if (searchText.isEmpty()) {
                sorter.setRowFilter(null); // Clear the filter if the search bar is empty
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });
        headerPanel.add(btnSearch);

        pnlSummary.add(headerPanel);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);
        tablePanel.setBounds(20, 140, pnlSummary.getWidth() - 40, pnlSummary.getHeight() - 180);
        tablePanel.setBackground(Color.WHITE);

        // Table Setup
        summaryTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        summaryTableModel.setColumnIdentifiers(new Object[]{
            "Product Name", "Quantity", "Type", "Date", "Reference No"
        });
        summaryTable.setModel(summaryTableModel);

        // Add TableRowSorter for sorting
        sorter = new TableRowSorter<>(summaryTableModel);
        summaryTable.setRowSorter(sorter);

        // Style the table
        styleTable(summaryTable);

        JScrollPane scrollPane = new JScrollPane(summaryTable);
        scrollPane.setBounds(0, 0, tablePanel.getWidth(), tablePanel.getHeight());
        tablePanel.add(scrollPane);

        pnlSummary.add(tablePanel);

        // Load sample data into the table (replace this with actual database retrieval)
        loadSampleData();

        return pnlSummary;
    }

    // Helper Method to Style Tables
    private static void styleTable(JTable table) {
        // Set custom header renderer
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.getTableHeader().setBackground(Color.decode("#4CAF50")); // Customize header color
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width, 40));

        // Set table properties
        table.setGridColor(Color.WHITE);
        table.setBackground(Color.decode("#E4E4E4"));
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(40);
        table.setSelectionBackground(Color.WHITE);

        // Add padding to cells
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
                    label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Padding
                }
                return c;
            }
        });
    }

    // Helper Method to Create Rounded Panels
    private static JPanel createRoundedPanel(int radius, Color color) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBackground(color);
        return panel;
    }

    // Load Sample Data into the Table
    private static void loadSampleData() {
        Object[][] data = {
            {"Product A", 10, "Sale", "2023-10-01", "REF-12345"},
            {"Product B", 5, "Restock", "2023-10-02", "REF-67890"},
            {"Product C", 20, "Sale", "2023-10-03", "REF-11223"}
        };

        for (Object[] rowData : data) {
            summaryTableModel.addRow(rowData);
        }
    }
}
