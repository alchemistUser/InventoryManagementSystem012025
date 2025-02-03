
/*
icon used:
https://www.flaticon.com/free-icon/dashboard_9055107?term=dashboard&page=1&position=53&origin=search&related_id=9055107
*/

import java.awt.*;    
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.*;

/*

decode("#00bf63")

*/

public class Dashboard {
    
    public static JPanel createTextPanel(String labelText, int size, String color, int style) {        
        JPanel panel = new JPanel(new BorderLayout()); // Use BorderLayout for full width
        panel.setPreferredSize(new Dimension(200, 100)); // Set panel size

        // Create the label and set text wrapping behavior
        JLabel label = new JLabel("<html><body style='width: 100%; word-wrap: break-word;'>" + labelText + "</body></html>");
        label.setForeground(Color.decode(color));
        label.setFont(new Font("Arial", style, size));

        // Align text to top-left
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.TOP);

        // Add to panel
        panel.setOpaque(false);
        panel.add(label, BorderLayout.NORTH);

        return panel;
    }



    // Function that returns the custom JPanel   
    public static JPanel createScaleTextPanel(String labelText, int initialFontSize, String color, int style, int horizontalalign) {
        JPanel panel = new JPanel(new BorderLayout());

        // Create the label with the given text
        JLabel label = new JLabel(labelText, horizontalalign);
        label.setForeground(Color.decode(color));
        
        // Try to apply color based on the value of labelText
        try {
            double i = Integer.parseInt(labelText); // Try to parse the string as an integer
            if (i > 0) {
                label.setForeground(Color.decode("#00bf63"));
            } else if (i < 0) {
                label.setForeground(Color.red);
            } else {
                label.setForeground(Color.decode(color));
            }
        } catch (NumberFormatException e) {
//            System.out.println("dashboard button text not a number!");
        }

        // Set the initial font size
        label.setFont(new Font("Arial", style, initialFontSize));

        // Add the label to the center of the panel
        panel.add(label, BorderLayout.CENTER);
        
        panel.setOpaque(false);

        // Add a component listener to adjust the font size when the panel is resized
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the panel
                int panelWidth = panel.getWidth();
                int panelHeight = panel.getHeight();

                // Get the width of the text using the label's font and the text
                FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
                int textWidth = fontMetrics.stringWidth(label.getText());
                int textHeight = fontMetrics.getHeight();

                // Use the initial font size if the text fits within the panel
                int scaledFontSize = initialFontSize;

                // If the text is wider than the panel, reduce the font size
                while (textWidth > panelWidth && scaledFontSize > 10) {
                    scaledFontSize--; // Decrease the font size gradually
                    label.setFont(new Font("Arial", style, scaledFontSize)); // Update the font size
                    fontMetrics = label.getFontMetrics(label.getFont()); // Recalculate font metrics
                    textWidth = fontMetrics.stringWidth(label.getText()); // Recalculate the text width with the new font size
                }

                // If the text height is larger than the panel's height, adjust the font size further
                while (textHeight > panelHeight && scaledFontSize > 10) {
                    scaledFontSize--; // Decrease the font size gradually
                    label.setFont(new Font("Arial", style, scaledFontSize)); // Update the font size
                    fontMetrics = label.getFontMetrics(label.getFont()); // Recalculate font metrics
                    textHeight = fontMetrics.getHeight(); // Recalculate text height
                }

                // Ensure the font size doesn't go below a reasonable value
                scaledFontSize = Math.max(scaledFontSize, 10); // Min font size limit

                // Set the new font size for the label
                label.setFont(new Font("Arial", style, scaledFontSize));
            }
        });


        return panel;
    }
    public static JPanel createScaleTextPanel(String labelText, int initialFontSize, String color, int style){
        return createScaleTextPanel(labelText, initialFontSize, color, style, SwingConstants.CENTER);
    }
    public static JPanel createScaleTextPanel(String labelText, int initialFontSize, String color){
        return createScaleTextPanel(labelText, initialFontSize, color, Font.BOLD);
    }
    public static JPanel createScaleTextPanel(String labelText){
        return createScaleTextPanel(labelText, 20);
    }
    public static JPanel createScaleTextPanel(String labelText, int initialFontSize){
        return createScaleTextPanel(labelText, initialFontSize, "#000000");
    }
    public static JPanel createNumberPanel(String labelText){
        return createScaleTextPanel(labelText, 50);
    }
    
    public static String valSystemUsers = new String();    
    public static String valProducts = new String();
    public static String valSuppliers = new String();
    public static String valTotalSales = new String();
    public static String valStocks = new String();
    
    private static final JPanel pnlDashboard = new JPanel();
    public static JPanel pnlDashboard() throws IOException{
        valSystemUsers="50";
        valProducts="51";
        valSuppliers="52";
        valTotalSales="53";
        valStocks="54";
        
        pnlDashboard.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlDashboard.setLayout(null);
        pnlDashboard.setBackground(Color.white);
        
        String path;
        
        // icon used: <a href="https://www.flaticon.com/free-icons/user-management" title="user management icons">User management icons created by Vectorslab - Flaticon</a>
        JPanel panel1 = Main.createHoverPanel();
        panel1.setBounds(980, 30, 200, 100);
        path = "src/icons/user-management.png";
        JLabel logo1 = Main.createImage(path);
        logo1.setBounds(20, 20, Main.getImageWidth(path), Main.getImageHeight(path));
        panel1.add(logo1);
//        JPanel pnlNum1 = createNumberPanel(valSystemUsers);
//        pnlNum1.setBounds(94, 5, 100, 50);
//        panel1.add(pnlNum1);
        JPanel pnlLabel1 = createScaleTextPanel("ABOUT");
        pnlLabel1.setBounds(85, 39, 100, 20);
        panel1.add(pnlLabel1);        
        // Add mouse listener for hover effect
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a message dialog when clicked
                JOptionPane.showMessageDialog(Main.frame, "About", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        pnlDashboard.add(panel1);
        
        JPanel panel2 = Main.createHoverPanel();
        panel2.setBounds(60, 30, 200, 100);
        path = "src/icons/product1.png";
        JLabel logo2 = Main.createImage(path);
        logo2.setBounds(20, 20, Main.getImageWidth(path), Main.getImageHeight(path));
        panel2.add(logo2);
//        JPanel pnlNum2 = createNumberPanel(valProducts);
//        pnlNum2.setBounds(94, 5, 100, 50);
//        panel2.add(pnlNum2);
        JPanel pnlLabel2 = createScaleTextPanel("PRODUCTS");
        pnlLabel2.setBounds(85, 39, 100, 20);
        panel2.add(pnlLabel2);   
        // Add mouse listener for hover effect
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a message dialog when clicked
                Main.triggerPanelClick(Main.panel2);
            }
        });
        pnlDashboard.add(panel2);
        
        JPanel panel3 = Main.createHoverPanel();
        panel3.setBounds(290, 30, 200, 100);
        path = "src/icons/supplier1.png";
        JLabel logo3 = Main.createImage(path);
        logo3.setBounds(20, 20, Main.getImageWidth(path), Main.getImageHeight(path));
        panel3.add(logo3);
//        JPanel pnlNum3 = createNumberPanel(valSuppliers);
//        pnlNum3.setBounds(94, 5, 100, 50);
//        panel3.add(pnlNum3);
        JPanel pnlLabel3 = createScaleTextPanel("SUPPLIERS");
        pnlLabel3.setBounds(85, 39, 100, 20);
        panel3.add(pnlLabel3);
        // Add mouse listener for hover effect
        panel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a message dialog when clicked
                Main.triggerPanelClick(Main.panel3);
            }
        });
        pnlDashboard.add(panel3);
        
        JPanel panel4 = Main.createHoverPanel();
        panel4.setBounds(520, 30, 200, 100);
        path = "src/icons/sales1.png";
        JLabel logo4 = Main.createImage(path);
        logo4.setBounds(20, 20, Main.getImageWidth(path), Main.getImageHeight(path));
        panel4.add(logo4);
//        JPanel pnlNum4 = createNumberPanel(valTotalSales);
//        pnlNum4.setBounds(94, 5, 100, 50);
//        panel4.add(pnlNum4);
        JPanel pnlLabel4 = createScaleTextPanel("TOTAL SALES");
        pnlLabel4.setBounds(85, 39, 100, 20);
        panel4.add(pnlLabel4);
        // Add mouse listener for hover effect
        panel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a message dialog when clicked
                Main.triggerPanelClick(Main.panel4);
            }
        });
        pnlDashboard.add(panel4);
        
        JPanel panel5 = Main.createHoverPanel();
        panel5.setBounds(750, 30, 200, 100);
        path = "src/icons/warehouse1.png";
        JLabel logo5 = Main.createImage(path);
        logo5.setBounds(20, 20, Main.getImageWidth(path), Main.getImageHeight(path));
        panel5.add(logo5);
//        JPanel pnlNum5 = createNumberPanel(valStocks);
//        pnlNum5.setBounds(94, 5, 100, 50);
//        panel5.add(pnlNum5);
        JPanel pnlLabel5 = createScaleTextPanel("STOCKS");
        pnlLabel5.setBounds(85, 39, 100, 20);
        panel5.add(pnlLabel5);
        // Add mouse listener for hover effect
        panel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a message dialog when clicked
                Main.triggerPanelClick(Main.panel5);
            }
        });
        pnlDashboard.add(panel5);
        
        return pnlDashboard;
    }
}
