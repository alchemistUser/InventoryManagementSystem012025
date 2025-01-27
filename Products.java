import java.awt.*;
import javax.swing.*;

public class Products {
    private static JPanel pnlProducts = new JPanel();

    public static JPanel pnlProducts() {
        pnlProducts.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlProducts.setLayout(null); // Using null layout for manual positioning
        pnlProducts.setBackground(Color.white);

        // Create the first panel
        JPanel panel = Main.createRoundedPanel(45, Main.stringcolorSelectedButton);
        panel.setBounds(70, 30, Main.pnlMainWidth - 140, 110);
        panel.setLayout(null);

        // Create the flex panel
        JPanel pnlflex = Main.createFlexPanel(0, 10, 0);
        pnlflex.setBounds(10, 10, Main.pnlMainWidth - 140 - 20, 80);
        pnlflex.setOpaque(false);

        // Add hover panels to pnlflex
        pnlflex.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, 80));
        pnlflex.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, 80));
        pnlflex.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, 80));
        
        JPanel pnlAddCategory = Main.createAddPanel("#0000", 195,75);
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
        JPanel panel1 = new JPanel();
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

        
        int height=225;
        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, height));
        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, height));
        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, height));
        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, height));
        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, height));
        panel1.add(Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0", 200, height));
                
        JPanel pnlAddProduct = Main.createAddPanel("#0000", 200,height);
        panel1.add(pnlAddProduct);
        
        for(int i = panel1.getComponentCount();i<6;i++){
            panel1.add(Main.createHoverPanel("#ffffff", "#ffffff", 200, height));
        }
        
        pnlProducts.add(scrollPane1); // Add the scrollPane1 to the pnlProducts

        return pnlProducts;
    }
}
