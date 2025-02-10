
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Suppliers {

    private static JPanel pnlSuppliers = new JPanel();
    private static JPanel panel1 = new JPanel();
    static int suppwidth = 200;
    static int suppheight = 225;
    private static JPanel pnlAddSupplier = Main.createAddPanel("#0000", suppwidth, suppheight);
    private static JCheckBox showUnavailableCheckbox = new JCheckBox("Show Unavailable");

    public static void addTheSuppliers() {
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();

        boolean showUnavailable = showUnavailableCheckbox.isSelected();
        String query = "SELECT supplier_id, supplier_name, supplier_company_name, contact_no, email, status FROM suppliers";
        if (!showUnavailable) {
            query += " WHERE status = 1"; // Only show active suppliers
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imsadmin_imsfd", "root", ""); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String supplierId = rs.getString("supplier_id");
                String name = rs.getString("supplier_name");
                String company = rs.getString("supplier_company_name");
                String contact = rs.getString("contact_no");
                String email = rs.getString("email");
                boolean status = rs.getBoolean("status");

                panel1.add(createSupplier(supplierId, name, company, contact, email, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        panel1.add(pnlAddSupplier);
    }

    static JPanel createSupplier(String supplierId, String name, String company, String contactno, String email, boolean status) {
        JPanel panel = Main.createGradientHoverPanel(
                "#1b9d54", "#cfca93",
                "#76c498", "#e2dfbe",
                suppwidth, suppheight
        );
        panel.setLayout(null);

        int width = suppwidth - 10;
        int ninety = 75;

        JPanel pnlName = Dashboard.createScaleTextPanel(name, 40, "#ffffff", Font.BOLD);
        pnlName.setBounds(10, ninety - 60, width, 50);
        panel.add(pnlName);

        JPanel line = Main.createWhitePanel();
        line.setBounds(10, ninety - 5, width, 1);
        panel.add(line);

        JPanel pnlCompany = Dashboard.createScaleTextPanel(company, 20, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlCompany.setBounds(10, ninety, width, 20);
        panel.add(pnlCompany);

        JPanel pnlContactno = Dashboard.createScaleTextPanel("Contact No.: " + contactno, 20, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlContactno.setBounds(10, ninety + 20, width, 20);
        panel.add(pnlContactno);

        JPanel pnlEmail = Dashboard.createScaleTextPanel("Email: " + email, 20, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlEmail.setBounds(10, ninety + 40, width, 20);
        panel.add(pnlEmail);

        String statusText = status ? "Active" : "Inactive";
        JPanel pnlStatus = Dashboard.createScaleTextPanel("Status: " + statusText, 20, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlStatus.setBounds(10, ninety + 60, width, 20);
        panel.add(pnlStatus);

        JLabel idLabel = new JLabel(supplierId);
        idLabel.setVisible(false);
        panel.add(idLabel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = idLabel.getText();
                AddSupplierApp.showEditSupplierDialog(id, name, company, contactno, email, status);
            }
        });

        return panel;
    }

    public static JPanel pnlSuppliers() {
        pnlSuppliers.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlSuppliers.setLayout(null);
        pnlSuppliers.setBackground(Color.white);

        int width = Main.pnlMainWidth - 140;
        int height = 110;
        JPanel panel = Main.createRoundedPanel(45, Main.stringcolorSelectedButton);
        panel.setBounds(70, 30, width, height);
        panel.setLayout(null);

        JTextField tfSearchBar = new JTextField();
        tfSearchBar.setBounds(width - 580, 30, 500, 50);
        tfSearchBar.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(tfSearchBar);

        JButton btnSearch = new JButton();
        btnSearch.setBounds(width - 80, 30, 50, 50);
        btnSearch.setFocusable(false);
        btnSearch.setIcon(new ImageIcon("src/icons/search.png"));
        panel.add(btnSearch);

        JLabel logo = new JLabel();
        try {
            logo = Main.createImage("src/icons/suppliergreen.png", 1);
        } catch (IOException ex) {
            Logger.getLogger(Suppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        logo.setBounds(30, 7, 100, 100);
        panel.add(logo);

        JPanel pnlTitle = Dashboard.createScaleTextPanel("SUPPLIERS", 50, Main.stringthegreencolor, Font.BOLD);
        pnlTitle.setBounds(130, 7, 300, 100);
        panel.add(pnlTitle);

        showUnavailableCheckbox.setBounds(70, 140, 200, 20);
        showUnavailableCheckbox.setBackground(Color.white);
        showUnavailableCheckbox.setFocusable(false);
        showUnavailableCheckbox.addActionListener(e -> addTheSuppliers());
        pnlSuppliers.add(showUnavailableCheckbox);

        pnlSuppliers.add(panel);

        panel1.setBounds(70, 160, Main.pnlMainWidth - 140, Main.pnlMainHeight - 180);
        panel1.setBackground(Color.white);
        panel1.setLayout(new GridLayout(0, 5, 10, 10));

        JScrollPane scrollPane1 = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.setBounds(70, 160, Main.pnlMainWidth - 140, Main.pnlMainHeight - 180);
        scrollPane1.setBorder(null);
        scrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10));

        addTheSuppliers();

        pnlAddSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddSupplierApp.showAddSupplierDialog();
                addTheSuppliers();
            }
        });

        panel1.add(pnlAddSupplier);
        pnlSuppliers.add(scrollPane1);

        return pnlSuppliers;
    }
}
