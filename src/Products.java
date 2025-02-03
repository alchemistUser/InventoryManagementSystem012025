import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Products {
    private static JCheckBox showUnavailableCheckbox = new JCheckBox("Show Unavailable");
    
    public static void addTheCats() {        
        pnlflex.removeAll();
        pnlflex.revalidate();
        pnlflex.repaint();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imsadmin_imsfd", "root", "");

            // SQL query to fetch categories
            String sql = "SELECT category_id, category_name FROM Categories";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and add categories
            while (resultSet.next()) {
                String categoryId = resultSet.getString("category_id");
                String categoryName = resultSet.getString("category_name");
                pnlflex.add(createCategoryItem(categoryId, categoryName));
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

        pnlflex.revalidate();
        pnlflex.repaint();
    }    

    public static void addTheProds() {
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imsadmin_imsfd", "root", "");

            // Fetch product details including the product_id
            String sql;
            if (showUnavailableCheckbox.isSelected()) {
                // Show all products (both available and unavailable)
                sql = "SELECT product_id, name, category, price, quantity_in_stock, reorder_level, status, description FROM products";
            } else {
                // Show only available products
                sql = "SELECT product_id, name, category, price, quantity_in_stock, reorder_level, status, description FROM products WHERE status = '1'";
            }

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String productId = resultSet.getString("product_id"); // Fetch the product ID
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String price = resultSet.getString("price");
                String quantity = resultSet.getString("quantity_in_stock");
                String reorder = resultSet.getString("reorder_level");
                String status = resultSet.getString("status");
                String description = resultSet.getString("description");

                // Ensure status is not null
                if (status == null) {
                    status = "0"; // Default to "Unavailable" if NULL
                }

                // Create the product panel with the product_id
                JPanel productPanel = createProductItem(productId, name, category, price, quantity, reorder, status, description);
                panel1.add(productPanel);
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

        panel1.revalidate();
        panel1.repaint();
    }    
    
    public static void cataddpanel(){
        pnlflex.add(pnlAddCategory);
    }
    public static void prodaddpanel(){
        panel1.add(pnlAddProduct);
    }

    static void addCat(String name) {
        AddCategoryApp.showAddCategoryDialog();
        addTheCats();
        pnlflex.add(pnlAddCategory);
        Main.refresh(Main.pnlProducts);
    }

    static void addCat() {
        addCat("");
    }

    static void addProd(String name, String productId, String cat, String desc) {
        AddProductApp.showAddProductDialog();
        addTheProds();
        panel1.add(pnlAddProduct);
        Main.refresh(Main.pnlProducts);
    }

    static JPanel createProductItem(String productId, String name, String category, String price, String quantity, String reorder, String status, String description) {
//        JPanel panel = Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", catwidth, catheight);
        JPanel panel = Main.createGradientHoverPanel("#1b9d54", "#cfca93", "#76c498","#e2dfbe",catwidth,catheight);

        panel.setLayout(null);

        int width = prodwidth - 10;
        int ninety = 75;

        // Display Product ID
        JPanel pnlId = Dashboard.createScaleTextPanel("ID: " + productId, 15, "#ffffff", Font.BOLD, SwingConstants.LEFT);
        pnlId.setBounds(10, 10, width, 20); // Positioned at the top
        panel.add(pnlId);

        // Display Product Name
        JPanel pnlName = Dashboard.createScaleTextPanel(name, 40, "#ffffff", Font.BOLD);
        pnlName.setBounds(10, 30, width, 50); // Positioned below the ID
        panel.add(pnlName);

        // Add a horizontal line separator
        JPanel line = Main.createWhitePanel();
        line.setBounds(10, 80, width, 1); // Positioned below the name
        panel.add(line);

        // Display Category
        int size = 20;
        JPanel pnlCategory = Dashboard.createScaleTextPanel("Category: " + category, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlCategory.setBounds(10, 90, width, size); // Positioned below the line
        panel.add(pnlCategory);

        // Display Price
        JPanel pnlPrice = Dashboard.createScaleTextPanel("Price: $" + price, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlPrice.setBounds(10, 90 + size, width, size); // Positioned below the category
        panel.add(pnlPrice);

        // Display Quantity in Stock
        JPanel pnlQuantity = Dashboard.createScaleTextPanel("Stock: " + quantity, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlQuantity.setBounds(10, 90 + size * 2, width, size); // Positioned below the price
        panel.add(pnlQuantity);

        // Display Reorder Level
        JPanel pnlReorder = Dashboard.createScaleTextPanel("Reorder Level: " + reorder, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlReorder.setBounds(10, 90 + size * 3, width, size); // Positioned below the quantity
        panel.add(pnlReorder);

        // Display Status
        String statusText = (status != null && status.equals("1")) ? "Available" : "Unavailable";
        JPanel pnlStatus = Dashboard.createScaleTextPanel("Status: " + statusText, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlStatus.setBounds(10, 90 + size * 4, width, size); // Positioned below the reorder level
        panel.add(pnlStatus);

        // Display Description
        JPanel pnlDesc = Dashboard.createTextPanel(description, size - 3, "#ffffff", Font.PLAIN);
        pnlDesc.setBounds(10, 90 + size * 5 + 10, width, 105); // Positioned below the status
        panel.add(pnlDesc);

        // Add click event to open edit dialog
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddProductApp.showEditProductDialog(productId); // Pass the product ID to the edit dialog
            }
        });

        return panel;
    }

    static JPanel createCategoryItem(String categoryId, String categoryName) {
        JPanel panel = Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", catwidth, catheight, categoryName, "#ffffff");
        panel.setLayout(null);

        // Add an invisible label to store the category ID
        JLabel idLabel = new JLabel(categoryId);
        idLabel.setVisible(false); // Make it invisible
        panel.add(idLabel);

        // Add click event to open edit dialog
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Fetch the category ID from the invisible label
                String id = idLabel.getText();
                // Open the edit dialog with the category ID and name
                AddCategoryApp.showEditCategoryDialog(id, categoryName);
            }
        });

        return panel;
    }

    static void addProd() {
        addProd("", "", "", "");
    }    

    private static JPanel pnlProducts = new JPanel();
    static int catwidth = 200;
    static int catheight = 80;
    static int prodwidth = 200;
    static int prodheight = 225;

    private static JPanel pnlflex = Main.createFlexPanel(0, 10, 0);
    private static JPanel panel1 = new JPanel();
    private static JPanel pnlAddCategory = Main.createAddPanel("#0000", 195, 75);
    private static JPanel pnlAddProduct = Main.createAddPanel("#0000", prodwidth, prodheight);

    public static JPanel pnlProducts() {
        pnlProducts.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlProducts.setLayout(null); // Using null layout for manual positioning
        pnlProducts.setBackground(Color.white);
        
        JLabel lblcat = new JLabel("Categories");
        lblcat.setBounds(80, 11, 65, 20);
//        lblcat.setBackground(Color.red);
//        lblcat.setOpaque(true);
        pnlProducts.add(lblcat);

        // Create the first panel
        JPanel panel = Main.createRoundedPanel(45, Main.stringcolorSelectedButton);
        panel.setBounds(70, 30, Main.pnlMainWidth - 140, 110);
        panel.setLayout(null);

        // Create the flex panel
        pnlflex.setBounds(10, 10, Main.pnlMainWidth - 140 - 20, 80);
        pnlflex.setOpaque(false);

        // Add hover panels to pnlflex
        addTheCats();

        pnlAddCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addCat();
            }
        });
        pnlflex.add(pnlAddCategory);

        // Add the pnlflex to a JScrollPane with horizontal scrolling enabled
        JScrollPane scrollPane = new JScrollPane(pnlflex, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 10, Main.pnlMainWidth - 140 - 20, 100); // Set the same bounds as before

        // Make the vertical scrollbar completely invisible
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(0, 0)); // Remove vertical scrollbar

        // Make all parts of the horizontal scrollbar (track, arrows) completely transparent except the thumb
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setPreferredSize(new Dimension(0, 10)); // Adjust height of the scrollbar

        // Set thumb to default and hide other parts of the scrollbar
        horizontalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                super.configureScrollBarColors();
                // Make sure only the thumb is visible and everything else is invisible
                this.thumbColor = new Color(192, 192, 192); // Light grey thumb color
                this.trackColor = new Color(0, 0, 0, 0);  // Transparent track
                this.thumbHighlightColor = new Color(0, 0, 0, 0);  // Transparent thumb highlight
                this.thumbLightShadowColor = new Color(0, 0, 0, 0);  // Transparent thumb shadow
                this.thumbDarkShadowColor = new Color(0, 0, 0, 0);  // Transparent thumb dark shadow
                this.trackHighlightColor = new Color(0, 0, 0, 0);  // Transparent track highlight
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setPreferredSize(new Dimension(0, 0)); // Make the decrement button invisible
                button.setVisible(false); // Ensure button is not visible
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setPreferredSize(new Dimension(0, 0)); // Make the increment button invisible
                button.setVisible(false); // Ensure button is not visible
                return button;
            }
        });

        scrollPane.setOpaque(false); // Optional: to maintain transparency
        scrollPane.getViewport().setOpaque(false); // Optional: to maintain transparency
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Optional: remove borders

        // Add the scrollPane to the first panel
        panel.add(scrollPane);

        // Add the first panel to the main panel
        pnlProducts.add(panel);

        //----------------------

        // Create the second panel (panel1)
        panel1.setBounds(70, 160, Main.pnlMainWidth - 140, Main.pnlMainHeight - 180);
        panel1.setBackground(Color.white);

        // Set FlowLayout to allow dynamic resizing and add margin
        GridLayout gridLayout = new GridLayout(0, 5, 10, 10); // 10px horizontal and vertical margin
        panel1.setLayout(gridLayout);

        // Create a JScrollPane and set panel1 as the content
        JScrollPane scrollPane1 = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane1.setBounds(70, 160, Main.pnlMainWidth - 140, Main.pnlMainHeight - 180); // Set bounds for scrollPane1
        scrollPane1.setBorder(null);

        // Make the vertical scrollbar with the same style as scrollPane
        JScrollBar verticalScrollBar1 = scrollPane1.getVerticalScrollBar();
        verticalScrollBar1.setPreferredSize(new Dimension(10, 10)); // Remove vertical scrollbar

        // Make all parts of the horizontal scrollbar (track, arrows) completely transparent except the thumb
        JScrollBar horizontalScrollBar1 = scrollPane1.getHorizontalScrollBar();
        horizontalScrollBar1.setOpaque(false);
        horizontalScrollBar1.setPreferredSize(new Dimension(0, 0)); // Adjust height of the scrollbar

        // Set thumb to default and hide other parts of the scrollbar
        verticalScrollBar1.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                super.configureScrollBarColors();
                // Make sure only the thumb is visible and everything else is invisible
                this.thumbColor = new Color(192, 192, 192); // Light grey thumb color
                this.trackColor = new Color(0, 0, 0, 0);  // Transparent track
                this.thumbHighlightColor = new Color(0, 0, 0, 0);  // Transparent thumb highlight
                this.thumbLightShadowColor = new Color(0, 0, 0, 0);  // Transparent thumb shadow
                this.thumbDarkShadowColor = new Color(0, 0, 0, 0);  // Transparent thumb dark shadow
                this.trackHighlightColor = new Color(0, 0, 0, 0);  // Transparent track highlight
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setPreferredSize(new Dimension(0, 0)); // Make the decrement button invisible
                button.setVisible(false); // Ensure button is not visible
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setPreferredSize(new Dimension(0, 0)); // Make the increment button invisible
                button.setVisible(false); // Ensure button is not visible
                return button;
            }
        });

        // Optional: Make the scrollPane1 and viewport transparent
        scrollPane1.setOpaque(false); // Optional: to maintain transparency
        scrollPane1.getViewport().setOpaque(false); // Optional: to maintain transparency

        // Add the "Show Unavailable" checkbox
        showUnavailableCheckbox.setBounds(70, 140, 200, 20);
        showUnavailableCheckbox.setBackground(Color.white);
        showUnavailableCheckbox.setFocusable(false);
        showUnavailableCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Refresh the product panels based on the checkbox state
                addTheProds();
                prodaddpanel();
            }
        });
        pnlProducts.add(showUnavailableCheckbox);

        addTheProds();

        pnlAddProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addProd();
            }
        });
        panel1.add(pnlAddProduct);

        pnlProducts.add(scrollPane1); // Add the scrollPane1 to the pnlProducts

        return pnlProducts;
    }
    public static void qtyRefreshProd(){
        addTheProds();
        prodaddpanel();
    }
}