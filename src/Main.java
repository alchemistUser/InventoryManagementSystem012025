
import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.*;

public class Main {
    
    public static void hiDialog(){
        JOptionPane.showMessageDialog(frame, "Hello World");
    }
    
    public static JFrame frame = new JFrame("Inventory Management System");
    
    public static JPanel panelBG = new JPanel();
    public static String stringcolorPanelBG = "#6f9276";
    public static Color colorPanelBG = Color.decode("#6f9276");
    public static Color colorSelectedButton = Color.decode("#fee4b6");
    
    public static JPanel pnlMain = new JPanel();
    public static int pnlMainWidth = 1243;
    public static int pnlMainHeight = 693;
    
    private static void removePanelInPanelMain(JPanel panel){
        pnlMain.remove(panel); // Remove the panel from pnlMain
        pnlMain.revalidate(); // Revalidate the layout
        pnlMain.repaint();    // Repaint the UI
        System.out.println("Removed " + panel + "in pnlMain success.");
    }
    private static void openPanelInPanelMain(JPanel panel){        
        pnlMain.removeAll(); // Remove the panel from pnlMain
        pnlMain.revalidate(); // Revalidate the layout
        pnlMain.repaint();    // Repaint the UI
        pnlMain.add(panel);
        System.out.println("Opened " + panel + "in pnlMain success.");
    }
    public static JPanel pnlDashboard = Dashboard.pnlDashboard();
    public static JPanel pnlProducts = Products.pnlProducts();
    public static JPanel pnlSuppliers = Suppliers.pnlSuppliers();
    public static JPanel pnlTotalSales = TotalSales.pnlTotalSales();
    public static JPanel pnlStocks = Stocks.pnlStocks();
    
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
    
    private static JPanel createWhitePanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        return panel;
    }
    
    private static void createSidePanelButtons(){
        
        JPanel panel1 = createRoundedPanel(
                true,
                colorPanelBG,
                "src/icons/dashboard_selected.png",
                "src/icons/dashboard_unselected.png",
                "Dashboard",
                colorSelectedButton,
                Color.black
        );
        panel1.setBounds(3,150,240,80);// Adding MouseListener to the panel
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPanelInPanelMain(pnlDashboard);
            }
        });
        panelBG.add(panel1);
        
        JPanel line1 = createWhitePanel();
        line1.setBounds(23, 240, 198, 1);
        panelBG.add(line1);
        JPanel panel2 = createRoundedPanel(
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
        panelBG.add(panel2);
        
        JPanel line2 = createWhitePanel();
        line2.setBounds(23, 340, 198, 1);
        panelBG.add(line2);
        
        JPanel panel3 = createRoundedPanel(
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
        panelBG.add(panel3);
        
        JPanel line3 = createWhitePanel();
        line3.setBounds(23, 440, 198, 1);
        panelBG.add(line3);
        
        JPanel panel4 = createRoundedPanel(
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
        panelBG.add(panel4);
        
        JPanel line4 = createWhitePanel();
        line4.setBounds(23, 540, 198, 1);
        panelBG.add(line4);
        
        JPanel panel5 = createRoundedPanel(
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
        panelBG.add(panel5);
        
        JPanel line5 = createWhitePanel();
        line5.setBounds(23, 640, 198, 1);
        panelBG.add(line5);
        
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
        panelBG.add(panel6);        
        // icon used: https://www.flaticon.com/free-icon/logout_1828427?term=logout&page=1&position=1&origin=search&related_id=1828427
    }
    
    public static void main(String[] args) {
        
        openPanelInPanelMain(pnlDashboard);
                
        //frame
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        panelBG.setBackground(colorPanelBG);
        panelBG.setLayout(null);
        frame.add(panelBG);
        
        //panel that is the main
        pnlMain.setBounds(250, 100, pnlMainWidth, pnlMainHeight);
        pnlMain.setBackground(Color.decode("#fefeff"));
        pnlMain.setLayout(null);
        frame.add(pnlMain);

        createSidePanelButtons();
                
        //just for the panels to layout
        frame.setComponentZOrder(pnlMain, 0);
        frame.setComponentZOrder(panelBG, 1);
        // Make the JFrame visible
        frame.setVisible(true);
    }
}
