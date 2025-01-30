
/*
icon used:
https://www.flaticon.com/free-icon/warehouse_3045528?term=stocks&page=1&position=36&origin=search&related_id=3045528
*/

import java.awt.*;    
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

public class Stocks {
    private static JPanel pnlStocks = new JPanel();

    public static JComboBox<String> createModernComboBox(String[] items) {
        // Create a JComboBox with the given items
        JComboBox<String> comboBox = new JComboBox<>(items);

        // Set the initial background and font
        comboBox.setBackground(new Color(240, 240, 240)); // Light grey background
        comboBox.setForeground(Color.BLACK); // Text color
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Initial font size

        // Remove border and add custom border for a clean look
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Set the UI for modern design
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                ComboPopup popup = super.createPopup();
                JList<?> list = popup.getList();
                
                // Set the background of the list
                list.setBackground(new Color(245, 245, 245)); // Light grey dropdown
                list.setSelectionBackground(new Color(100, 149, 237)); // Modern selection color
                list.setSelectionForeground(Color.WHITE); // White text on selection
                return popup;
            }
        });

        // Add a component listener to adapt the font size and combo box size when resized
        comboBox.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the combo box
                int width = comboBox.getWidth();
                int height = comboBox.getHeight();

                // Adjust the font size based on the combo box width and height
                int fontSize = Math.max(12, Math.min(width / 10, height / 5)); // Make font size responsive
                comboBox.setFont(new Font("Arial", Font.PLAIN, fontSize));

                // Recalculate the preferred size to fit the combo box contents
                comboBox.setPreferredSize(new Dimension(width, height));
            }
        });

        // Set the preferred size for the combo box based on the contents and the font size
        comboBox.setPreferredSize(new Dimension(150, 30)); // Initial preferred size

        return comboBox;
    }
    
    public static JPanel pnlStocks() throws IOException{
        pnlStocks.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlStocks.setLayout(null);
        pnlStocks.setBackground(Color.white);
        
        // Create the first panel
        int width = Main.pnlMainWidth - 140;
        int height = 110;
        JPanel panel = Main.createRoundedPanel(45, Main.stringcolorSelectedButton);
        panel.setBounds(70, 30, width, height);
        panel.setLayout(null);
        
        JTextField tfSearchBar = new JTextField();
        tfSearchBar.setBounds(width-80-500, 30, 500, 50);
        // Create a new Font with the desired style and size
        Font newFont = new Font("Arial", Font.PLAIN, 18); // Font name, style, and size
        // Set the font of the text field
        tfSearchBar.setFont(newFont);
        panel.add(tfSearchBar);        
        
        // icon used: https://www.flaticon.com/free-icon/magnifying-glass_151773?term=search&page=1&position=4&origin=search&related_id=151773
        JButton btnSearch = new JButton();
        btnSearch.setBounds(width-80, 30, 50, 50);
        btnSearch.setIcon(new ImageIcon("src/icons/search.png"));
        btnSearch.addActionListener(e -> {
            // Action when the button is clicked
            System.out.println("Button clicked!");
        });
        panel.add(btnSearch);
        
        String logopath = "src/icons/warehousegreen.png";
        double scaler = 1;
        JLabel logo = Main.createImage(logopath, scaler);
        logo.setBounds(30, 7, 100, 100);
        panel.add(logo);
        
        JPanel pnlTitle = Dashboard.createScaleTextPanel("STOCKS", 50, Main.stringthegreencolor, Font.BOLD);
        pnlTitle.setBounds(130, 7, 225, 100);
//        pnlTitle.setOpaque(true);
//        pnlTitle.setBackground(Color.red);
        panel.add(pnlTitle);
        
        
        // Create items for the combo box
        String[] items = {"Option 1", "Option 2", "Option 3", "Option 4"};

        // Create and add the modern combo box to the frame
        JComboBox<String> comboBox = createModernComboBox(items);
        comboBox.setBounds(380, 40, 100, 30); // Set bounds for the combo box (x, y, width, height)
        panel.add(comboBox);
        
        pnlStocks.add(panel);
        
        return pnlStocks;
    }
}
