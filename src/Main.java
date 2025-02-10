// progress: products commented color.red

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

    public static void hiDialog() {
        JOptionPane.showMessageDialog(frame, "Hello World");
    }

    public static JFrame frame = new JFrame("Inventory Management System");

    public static JPanel pnlBackground = new JPanel();
    public static String stringcolorPanelBG = "#6f9276";
    public static Color colorPanelBG = Color.decode("#6f9276");
    public static Color colorSelectedButton = Color.decode("#fee4b6");
    public static String stringcolorSelectedButton = "#fee4b6";
    public static String stringthegreencolor = "#179c53";

    public static JPanel pnlMain = new JPanel();
    public static int pnlMainWidth = 1243;
    public static int pnlMainHeight = 693;

    private static void removePanelInPanelMain(JPanel panel) {
        pnlMain.remove(panel); // Remove the panel from pnlMain
        pnlMain.revalidate(); // Revalidate the layout
        pnlMain.repaint();    // Repaint the UI
        System.out.println("Removed " + panel + "in pnlMain success.");
    }

    public static void openPanelInPanelMain(JPanel panel) {
        pnlMain.removeAll(); // Remove the panel from pnlMain
        pnlMain.revalidate(); // Revalidate the layout
        pnlMain.repaint();    // Repaint the UI
        pnlMain.add(panel);
        System.out.println("Opened " + panel + "in pnlMain success.");
    }

    public static void triggerPanelClick(JPanel panel) {
        MouseEvent clickEvent = new MouseEvent(panel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
        for (MouseListener listener : panel.getMouseListeners()) {
            listener.mouseClicked(clickEvent);
        }
    }

    public static JPanel createImagePanel(String imagePath) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage();
                // Draw the image
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        return panel;
    }

    public static JPanel pnlDashboard;

    static {
        try {
            pnlDashboard = Dashboard.pnlDashboard();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception (e.g., log it or display a message)
            pnlDashboard = new JPanel(); // Default to an empty panel to avoid null issues
        }
    }
    public static JPanel pnlProducts = Products.pnlProducts();
    public static JPanel pnlSuppliers = Suppliers.pnlSuppliers();
    public static JPanel pnlTotalSales = TotalSales.pnlTotalSales();
    public static JPanel pnlStocks = Stocks.pnlStocks();

    public static void refresh(JPanel panel) {
        pnlDashboard.revalidate();
        pnlDashboard.repaint();
        pnlProducts.revalidate();
        pnlProducts.repaint();
        pnlSuppliers.revalidate();
        pnlSuppliers.repaint();
        pnlTotalSales.revalidate();
        pnlTotalSales.repaint();
        pnlStocks.revalidate();
        pnlStocks.repaint();

        openPanelInPanelMain(panel);
        System.out.println("Refreshed.");
    }

    public static void qtyRefresh() {
        Stocks.refreshTableData();
        Products.qtyRefreshProd();
    }

    // A static variable to track the currently selected panel (the one with the border)
    // here
    private static RoundedPanel selectedPanel = null;

    private static JPanel createRoundedPanel(boolean isSelectable, Color customBackgroundColor, String image1Path, String image2Path, String labelText, Color selectedTextColor, Color unselectedTextColor) {
        int cornerRadius = 50;
        Color borderColor = Color.WHITE; // Border color
        int borderThickness = 8;

        // Use the custom background color or a default one
        Color backgroundColor = (customBackgroundColor != null) ? customBackgroundColor : Color.GRAY;

        // Create a RoundedPanel with custom painting, label, and the isSelectable flag
        RoundedPanel panel = new RoundedPanel(cornerRadius, backgroundColor, borderColor, borderThickness, isSelectable, image1Path, image2Path, labelText, selectedTextColor, unselectedTextColor);

        // Add MouseListener to toggle the border visibility, image state, and label color
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.isSelectable()) {
                    // Deselect the currently selected panel
                    if (selectedPanel != null) {
                        selectedPanel.setBorderVisible(false);
                        selectedPanel.setSelected(false);
                        selectedPanel.repaint();
                    }

                    // Select the current panel
                    selectedPanel = panel;
                    selectedPanel.setBorderVisible(true);
                    selectedPanel.setSelected(true);
                    panel.repaint();
                }
            }
        });

        // Set the panel as selected by default (on program start)
        if (selectedPanel == null) {
            selectedPanel = panel;
            selectedPanel.setBorderVisible(true);
            selectedPanel.setSelected(true);
            panel.repaint();
        }

        return panel;
    }

    static JButton createHoverButton(String add_Sales_Transaction, String b9d54, String cfca93, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // RoundedPanel class with image handling
    private static class RoundedPanel extends JPanel {

        private boolean borderVisible = false;
        private boolean isSelected = false; // Default unselected state
        private final int cornerRadius;
        private final Color backgroundColor;
        private final Color borderColor;
        private final int borderThickness;
        private final boolean isSelectable;
        private final String image1Path; // Path to the selected image
        private final String image2Path; // Path to the unselected image
        private final JLabel label; // Label for text
        private final Color selectedTextColor;
        private final Color unselectedTextColor;

        public RoundedPanel(int cornerRadius, Color backgroundColor, Color borderColor, int borderThickness, boolean isSelectable,
                String image1Path, String image2Path, String labelText, Color selectedTextColor, Color unselectedTextColor) {
            this.cornerRadius = cornerRadius;
            this.backgroundColor = backgroundColor;
            this.borderColor = borderColor;
            this.borderThickness = borderThickness;
            this.isSelectable = isSelectable;
            this.image1Path = image1Path;
            this.image2Path = image2Path;
            this.selectedTextColor = selectedTextColor;
            this.unselectedTextColor = unselectedTextColor;

            setLayout(null); // Absolute layout
            setOpaque(false); // Make the panel transparent

            // Initialize the label
            label = new JLabel(labelText);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setBounds(85, 25, 200, 30); // Adjust position and size
            label.setForeground(unselectedTextColor); // Default to unselected color
            add(label);
        }

        public void setBorderVisible(boolean borderVisible) {
            this.borderVisible = borderVisible;
        }

        public boolean isSelectable() {
            return isSelectable;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
            // Update the label color based on the selection state
            label.setForeground(isSelected ? selectedTextColor : unselectedTextColor);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the background with rounded corners
            g2.setColor(backgroundColor);
            g2.fill(new RoundRectangle2D.Double(borderThickness / 2.0, borderThickness / 2.0,
                    getWidth() - borderThickness, getHeight() - borderThickness,
                    cornerRadius, cornerRadius));

            // Draw the border with rounded corners if visible
            if (borderVisible) {
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(borderThickness));
                g2.draw(new RoundRectangle2D.Double(borderThickness / 2.0, borderThickness / 2.0,
                        getWidth() - borderThickness, getHeight() - borderThickness,
                        cornerRadius, cornerRadius));
            }

            // Draw the image based on the selection state
            String imagePath = isSelected ? image1Path : image2Path;
            if (imagePath != null) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();
                g2.drawImage(image, 40, 25, 32, 32, this); // Adjusted position for the image
            }

            g2.dispose();
        }
    }

    public static JPanel createRoundedPanel(int radius, String hex) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Decode the hex color
                Color color = Color.decode(hex);
                g2.setColor(color);

                // Draw rounded background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.dispose();
            }
        };
        panel.setOpaque(false); // Make the panel transparent
        return panel;
    }

    public static JPanel createRoundedPanel(int radius) {
        return createRoundedPanel(radius, "#FF0000");
    }

    public static JPanel createWhitePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        return panel;
    }

    public static JPanel createAddPanel(String borderColor, int width, int height) {
        JPanel panel = new JPanel() {
            // Custom painting for rounded corners and dashed border
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a rounded rectangle with 30-pixel corner radius
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);

                // Set the color for the dashed border
                g2d.setColor(Color.decode(borderColor));
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0));
                g2d.draw(roundedRectangle); // Draw the dashed border
            }
        };

        // Set the panel's layout manager
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        panel.setPreferredSize(new Dimension(width, height));
        panel.setOpaque(false); // Make it transparent so the custom painting is visible

        // Create the label with the "+" symbol
        JLabel label = new JLabel("+");
        label.setFont(new Font("Arial", Font.PLAIN, 48)); // Set font size and style
        label.setForeground(Color.decode("#000")); // Set the color of the "+" symbol

        // Add the label to the panel
        panel.add(label);

        return panel;
    }

    public static JPanel createProductCard() {
        JPanel panel = new JPanel();

        return panel;
    }

    // Function to create a hover panel with custom size and colors
    public static JPanel createHoverPanel(String colorDefault, String colorHover, int width, int height, String s, String textcolor) {
        JPanel panel = new JPanel() {
            // Custom painting for rounded corners
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a rounded rectangle with 30-pixel corner radius
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.setColor(getBackground()); // Use the panel's background color
                g2d.fill(roundedRectangle); // Fill the shape
            }
        };

        panel.setLayout(null);

        // Set the initial size
        panel.setPreferredSize(new Dimension(width, height));
//        panel.setSize(width, height);

        // Set the initial background color
        panel.setBackground(Color.decode(colorDefault));

        // Set the panel to non-opaque so custom painting is visible
        panel.setOpaque(false);

        // Add mouse listeners for hover effect
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.decode(colorHover)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.decode(colorDefault)); // Reset color on exit
            }
        });

        JPanel word = Dashboard.createScaleTextPanel(s, 20, textcolor);
        word.setBounds(0, 0, width, height);
        panel.add(word);

        return panel;
    }

    public static JPanel createHoverPanel(String colorDefault, String colorHover, int width, int height, String s) {
        return createHoverPanel(colorDefault, colorHover, width, height, s, "#000000");
    }

    // Overloaded method with default hover colors and size
    public static JPanel createHoverPanel() {
        return createHoverPanel(stringcolorSelectedButton, "#d1bc95", 200, 100, ""); // Default colors and size
    }

    public static JPanel createHoverPanel(String colorDefault, String colorHover, int width, int height) {
        return createHoverPanel(colorDefault, colorHover, width, height, ""); // Default colors and size
    }

    public static JPanel createGradientHoverPanel(
            String colorDefault1,
            String colorDefault2,
            String colorHover1,
            String colorHover2,
            int width, int height,
            String text,
            String textColor) {

        JPanel panel = new JPanel() {
            private boolean isHovered = false; // Move isHovered inside the class

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Define gradient colors based on hover state
                Color startColor = isHovered ? Color.decode(colorHover1) : Color.decode(colorDefault1);
                Color endColor = isHovered ? Color.decode(colorHover2) : Color.decode(colorDefault2);

                // Create a gradient paint
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);

                // Fill the panel with the gradient
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners
                g2d.dispose();
            }

            // Add mouse listeners for hover effect
            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        isHovered = true;
                        repaint(); // Repaint to apply hover gradient
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        isHovered = false;
                        repaint(); // Repaint to reset to default gradient
                    }
                });
            }
        };

        panel.setLayout(null);

        // Set the initial size
        panel.setPreferredSize(new Dimension(width, height));

        // Set the panel to non-opaque so custom painting is visible
        panel.setOpaque(false);

        // Add text to the panel
        JPanel word = Dashboard.createScaleTextPanel(text, 20, textColor);
        word.setBounds(0, 0, width, height);
        panel.add(word);

        return panel;
    }

    public static JPanel createGradientHoverPanel(
            String colorDefault1,
            String colorDefault2,
            String colorHover1,
            String colorHover2,
            int width, int height) {
        return createGradientHoverPanel(colorDefault1, colorDefault2, colorHover1, colorHover2, width, height, " ", "#ffffff");
    }

    // Method to create a panel with flex-like layout (horizontal layout) with margin and padding
    public static JPanel createFlexPanel(int marginLeft, int marginTop, int marginRight, int marginBottom, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        JPanel panel = new JPanel();

        // Setting a horizontal FlowLayout (flex layout) with gap between elements
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(10);  // Set horizontal gap between components
        flowLayout.setVgap(5);   // Set vertical gap between components
        panel.setLayout(flowLayout);

        // Apply margin to the panel (the space outside the panel)
        panel.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, marginBottom, marginRight));

        // Apply padding to the components inside the panel
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, paddingLeft, paddingTop));

        return panel;
    }

    public static JPanel createFlexPanel(int margin, int paddingx, int paddingy) {
        return createFlexPanel(margin, margin, margin, margin, paddingx, paddingy, paddingx, paddingy);
    }

    public static JPanel panel1 = new JPanel();
    public static JPanel panel2 = new JPanel();
    public static JPanel panel3 = new JPanel();
    public static JPanel panel4 = new JPanel();
    public static JPanel panel5 = new JPanel();

    private static void createSidePanelButtons() throws IOException {

        panel1 = createRoundedPanel(
                true,
                colorPanelBG,
                "src/icons/dashboard_selected.png",
                "src/icons/dashboard_unselected.png",
                "Dashboard",
                colorSelectedButton,
                Color.black
        );
        panel1.setBounds(3, 150, 240, 80);// Adding MouseListener to the panel
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPanelInPanelMain(pnlDashboard);
            }
        });
        pnlBackground.add(panel1);

        JPanel line1 = createWhitePanel();
        line1.setBounds(23, 240, 198, 1);
        pnlBackground.add(line1);
        panel2 = createRoundedPanel(
                true,
                colorPanelBG,
                "src/icons/product_selected.png",
                "src/icons/product_unselected.png",
                "Products",
                colorSelectedButton,
                Color.black
        );
        panel2.setBounds(3, 250, 240, 80);
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPanelInPanelMain(pnlProducts);
            }
        });
        pnlBackground.add(panel2);

        JPanel line2 = createWhitePanel();
        line2.setBounds(23, 340, 198, 1);
        pnlBackground.add(line2);

        panel3 = createRoundedPanel(
                true,
                colorPanelBG,
                "src/icons/supplier_selected.png",
                "src/icons/supplier_unselected.png",
                "Suppliers",
                colorSelectedButton,
                Color.black
        );
        panel3.setBounds(3, 350, 240, 80);
        panel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPanelInPanelMain(pnlSuppliers);
            }
        });
        pnlBackground.add(panel3);

        JPanel line3 = createWhitePanel();
        line3.setBounds(23, 440, 198, 1);
        pnlBackground.add(line3);

        panel4 = createRoundedPanel(
                true,
                colorPanelBG,
                "src/icons/sales_selected.png",
                "src/icons/sales_unselected.png",
                "Total Sales",
                colorSelectedButton,
                Color.black
        );
        panel4.setBounds(3, 450, 240, 80);
        panel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPanelInPanelMain(pnlTotalSales);
            }
        });
        pnlBackground.add(panel4);

        JPanel line4 = createWhitePanel();
        line4.setBounds(23, 540, 198, 1);
        pnlBackground.add(line4);

        panel5 = createRoundedPanel(
                true,
                colorPanelBG,
                "src/icons/stocks_selected.png",
                "src/icons/stocks_unselected.png",
                "Stocks",
                colorSelectedButton,
                Color.black
        );
        panel5.setBounds(3, 550, 240, 80);
        panel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPanelInPanelMain(pnlStocks);
            }
        });
        pnlBackground.add(panel5);

        JPanel line5 = createWhitePanel();
        line5.setBounds(23, 640, 198, 1);
        pnlBackground.add(line5);

        JPanel panel6 = createRoundedPanel(
                false,
                colorPanelBG,
                "src/icons/logout.png",
                "src/icons/logout.png",
                "Logout",
                null,
                null
        );
        panel6.setBounds(3, 650, 170, 80);
        panel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hiDialog();
            }
        });
        pnlBackground.add(panel6);
        // icon used: https://www.flaticon.com/free-icon/logout_1828427?term=logout&page=1&position=1&origin=search&related_id=1828427
    }

    public static int getImageWidth(String path, double whscaler) throws IOException {
        //load image file        
        File imageFile = new File(path);
        BufferedImage image = ImageIO.read(imageFile);
        int width = image.getWidth();
        int widthLogo = (int) (whscaler * width);
        return widthLogo;
    }

    public static int getImageWidth(String path) throws IOException {
        return getImageWidth(path, 1);
    }

    public static int getImageHeight(String path, double whscaler) throws IOException {
        //load image file        
        File imageFile = new File(path);
        BufferedImage image = ImageIO.read(imageFile);
        int height = image.getHeight();
        int heightLogo = (int) (whscaler * height);
        return heightLogo;
    }

    public static int getImageHeight(String path) throws IOException {
        return getImageHeight(path, 1);
    }

    public static JLabel createImage(String path, double whscaler) throws IOException {
        // Scale the image to the desired size        
        ImageIcon originalLogo = new ImageIcon(path);
        Image scaledImage = originalLogo.getImage().getScaledInstance(getImageWidth(path, whscaler), getImageHeight(path, whscaler), Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);
        JLabel lbllogo = new JLabel(scaledLogo);
        return lbllogo;
    }

    public static JLabel createImage(String path) throws IOException {
        return createImage(path, 1);
    }

    public static void main(String args[]) throws IOException {
//    public static void main() throws IOException {

        openPanelInPanelMain(pnlDashboard);

        //frame
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        pnlBackground.setBackground(colorPanelBG);
        pnlBackground.setLayout(null);
        frame.add(pnlBackground);

        String logopath = "src/icons/invensync.png";
        double scaler = 0.4;
        JLabel logo = createImage(logopath, scaler);
        logo.setBounds(0, 15, getImageWidth(logopath, scaler), getImageHeight(logopath, scaler));
        pnlBackground.add(logo);

        //panel that is the main
        pnlMain.setBounds(250, 100, pnlMainWidth, pnlMainHeight);
        pnlMain.setBackground(Color.decode("#fefeff"));
        pnlMain.setLayout(null);
        frame.add(pnlMain);

        createSidePanelButtons();

        //just for the panels to layout
        frame.setComponentZOrder(pnlMain, 0);
        frame.setComponentZOrder(pnlBackground, 1);
        // Make the JFrame visible
        frame.setVisible(true);
    }
}
