import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Products {
    static void addTheCats(){        
        pnlflex.removeAll();
        pnlflex.revalidate();
        pnlflex.repaint();
        
        pnlflex.add(createCategoryItem("Goods"));
        pnlflex.add(createCategoryItem("Collectibles"));
        pnlflex.add(createCategoryItem("Books"));        
    }    
    static void addTheProds(){
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        
        panel1.add(createProductItem("PAUL", "09497556327", "cars", "descriptions"));
        
        
        // Sample product details
        String[][] products = {
            {"Laptop", "001", "Electronics", "High performance laptop with 16GB RAM and 512GB SSD."},
            {"Smartphone", "002", "Electronics", "Latest smartphone with 5G capabilities and a 48MP camera."},
            {"Smartwatch", "003", "Accessories", "Smartwatch with fitness tracking and heart rate monitor."},
            {"Headphones", "004", "Electronics", "Noise-cancelling wireless headphones for superior sound."},
            {"Keyboard", "005", "Accessories", "Mechanical keyboard with RGB lighting and customizable keys."},
            {"Mouse", "006", "Accessories", "Wireless mouse with ergonomic design and high precision."},
            {"Monitor", "007", "Electronics", "24-inch full HD monitor with slim bezels and great color accuracy."},
        };

        // Create and add 10 product panels
        for (String[] product : products) {
            String name = product[0];
            String id = product[1];
            String cat = product[2];
            String desc = product[3];
            JPanel productPanel = createProductItem(name, id, cat, desc);
            panel1.add(productPanel);
        }
    }
    static void addCat(String name){
//        pnlflex.add(createCategoryItem("New"));
        pnlflex.add(createCategoryItem(name));
        pnlflex.add(pnlAddCategory);
        
        
        Main.refresh(Main.pnlProducts);
    }
    static void addCat(){
        addCat("");
    }
    static void addProd(String name, String id, String cat, String desc){        
//        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", catwidth, catheight));
        panel1.add(createProductItem(name, id, cat, desc));
        panel1.add(pnlAddProduct);
        
        Main.refresh(Main.pnlProducts);
    }
    static void addProd(){
        addProd("","","","");
    }    
    
    private static JPanel pnlProducts = new JPanel();
    static int catwidth = 200;
    static int catheight = 80;
    static int prodwidth = 200;
    static int prodheight = 225;
    
    private static JPanel pnlflex = Main.createFlexPanel(0, 10, 0);
    private static JPanel panel1 = new JPanel();
    private static JPanel pnlAddCategory = Main.createAddPanel("#0000", 195,75);
    private static JPanel pnlAddProduct = Main.createAddPanel("#0000", prodwidth,prodheight);    

    static JPanel createCategoryItem(String s){
        return Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", catwidth, catheight, s, "#ffffff");
    }
    static JPanel createProductItem(String name, String id, String cat, String desc){
        JPanel panel = Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", catwidth, catheight);
        panel.setLayout(null);        
        
        int width = prodwidth-10;
        int ninety = 75;
        
        JPanel pnlName = Dashboard.createScaleTextPanel(name, 40, "#ffffff", Font.BOLD);
        pnlName.setBounds(10, ninety-60, width, 50);
//        pnlName.setOpaque(true);
//        pnlName.setBackground(Color.red);
        panel.add(pnlName);
        
        JPanel line = Main.createWhitePanel();
        line.setBounds(10, ninety-5, width, 1);
        panel.add(line);
        
        String s = "Product ID: "+id;
        int size = 20;
        JPanel pnlID = Dashboard.createScaleTextPanel(s, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlID.setBounds(10, ninety, width, size);
        panel.add(pnlID);
        
        s = "Category: "+cat;
        JPanel pnlCat = Dashboard.createScaleTextPanel(s, size, "#ffffff", Font.PLAIN, SwingConstants.LEFT);
        pnlCat.setBounds(10, ninety+size, width, size);
        panel.add(pnlCat);
        
//        s = "Paul april a hofilena and company friends askhdaslj dlkasjdl asdjlaskjd laskdjaslkdklasjd asljd ladjklsadj lasjd klasd las djlasdjlkaasdas asd asdasdasd asd a";
        s=desc;
        JPanel pnlDesc = Dashboard.createTextPanel(s, size-3, "#ffffff", Font.PLAIN);
        pnlDesc.setBounds(10, ninety+size+size+10, width, 105);
        panel.add(pnlDesc);
        
        return panel;
    }
    
    public static JPanel pnlProducts() {
        pnlProducts.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlProducts.setLayout(null); // Using null layout for manual positioning
        pnlProducts.setBackground(Color.white);

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

        addTheProds();
                
        pnlAddProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addProd();
            }
        });
        panel1.add(pnlAddProduct);
        
        for(int i = panel1.getComponentCount();i<6;i++){
            panel1.add(Main.createHoverPanel("#ffffff", "#ffffff", prodwidth, prodheight));
        }
        
        pnlProducts.add(scrollPane1); // Add the scrollPane1 to the pnlProducts

        return pnlProducts;
    }
}
