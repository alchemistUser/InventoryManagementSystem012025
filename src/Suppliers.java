
/*
icon used:
https://www.flaticon.com/free-icon/supplier_18255220?term=supplier&page=1&position=21&origin=search&related_id=18255220
*/

import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.*;

public class Suppliers {
    private static JPanel pnlSuppliers = new JPanel();
    private static JPanel panel1 = new JPanel();
    static int suppwidth = 200;
    static int suppheight = 225;
    private static JPanel pnlAddSupplier = Main.createAddPanel("#0000", suppwidth,suppheight);    
    
    static void addTheSuppliers(){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        
        panel1.add(createSupplier());
    }
    
    static void addSupplier(String name, String company, String location, String contactno, String email){
        panel1.add(createSupplier(name, company, location, contactno, email));
        panel1.add(pnlAddSupplier);        
                   
        Main.refresh(Main.pnlSuppliers);
    }
    static void addSupplier(){
        addSupplier("","","","","");
    }
    
    static JPanel createSupplier(String name, String company, String location, String contactno, String email){
        JPanel panel = Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", suppwidth, suppheight);
        panel.setLayout(null);        
        
        int width = suppwidth-10;
        int ninety = 75;
        
        JPanel pnlName = Dashboard.createScaleTextPanel(name, 40, "#ffffff", Font.BOLD);
        pnlName.setBounds(10, ninety-60, width, 50);
//        pnlName.setOpaque(true);
//        pnlName.setBackground(Color.red);
        panel.add(pnlName);
        
        JPanel line = Main.createWhitePanel();
        line.setBounds(10, ninety-5, width, 1);
        panel.add(line);
        
        String s = company;
        int size = 20;
        JPanel pnlCompany = Dashboard.createScaleTextPanel(s, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlCompany.setBounds(10, ninety, width, size);
        panel.add(pnlCompany);
        
        s = "Location: "+location;
        JPanel pnlLocation = Dashboard.createScaleTextPanel(s, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlLocation.setBounds(10, ninety+size, width, size);
        panel.add(pnlLocation);
        
        s = "Contact No.: "+contactno;
        JPanel pnlContactno = Dashboard.createScaleTextPanel(s, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlContactno.setBounds(10, ninety+size*2, width, size);
        panel.add(pnlContactno);
        
        s = "Email: "+email;
        JPanel pnlEmail = Dashboard.createScaleTextPanel(s, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlEmail.setBounds(10, ninety+size*3, width, size);
        panel.add(pnlEmail);
        
        return panel;
    }
    static JPanel createSupplier(){
        return Suppliers.createSupplier("","","","","");
    }
    
    public static JPanel pnlSuppliers() throws IOException{
        pnlSuppliers.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlSuppliers.setLayout(null);
        pnlSuppliers.setBackground(Color.white);
                
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
        
        String logopath = "src/icons/suppliergreen.png";
        double scaler = 1;
        JLabel logo = Main.createImage(logopath, scaler);
        logo.setBounds(30, 7, 100, 100);
        panel.add(logo);
        
        JPanel pnlTitle = Dashboard.createScaleTextPanel("SUPPLIERS", 50, Main.stringthegreencolor, Font.BOLD);
        pnlTitle.setBounds(130, 7, 300, 100);
//        pnlTitle.setOpaque(true);
//        pnlTitle.setBackground(Color.red);
        panel.add(pnlTitle);
        
        
        pnlSuppliers.add(panel);
        
        
        
// Create the second panel (panel1)
        panel1.setBounds(70, 160, Main.pnlMainWidth - 140, Main.pnlMainHeight - 180);
//        panel1.setBackground(Color.red);
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

//        addTheProds();
        addTheSuppliers();
                
        pnlAddSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                addProd();
                addSupplier();
            }
        });
        panel1.add(pnlAddSupplier);
        
        pnlSuppliers.add(scrollPane1); // Add the scrollPane1 to the pnlProducts

        return pnlSuppliers;
    }
}
