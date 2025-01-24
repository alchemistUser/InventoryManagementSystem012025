
/*
icon used:
https://www.flaticon.com/free-icon/dashboard_9055107?term=dashboard&page=1&position=53&origin=search&related_id=9055107
*/

import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Dashboard {
    // Function that returns the custom JPanel
    private static JPanel createHoverPanel() {
        JPanel panel = new JPanel() {
            // Set rounded corners using RoundRectangle2D
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create rounded rectangle with desired corner radius
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.setColor(getBackground()); // Set the background color for the panel
                g2d.fill(roundedRectangle); // Fill the rounded rectangle
            }
        };

        panel.setLayout(null);
        
        // Set the initial color
        panel.setBackground(Main.colorSelectedButton);

        // Set the panel to non-opaque so that the custom painting is visible
        panel.setOpaque(false);

        // Add mouse listener for hover effect
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.decode("#d1bc95")); // Change color when hovered
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Main.colorSelectedButton); // Reset color when not hovered
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a message dialog when clicked
                JOptionPane.showMessageDialog(panel, "Panel clicked!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return panel;
    }
    
    private static final JPanel pnlDashboard = new JPanel();
    public static JPanel pnlDashboard(){
        pnlDashboard.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlDashboard.setLayout(null);
        pnlDashboard.setBackground(Color.white);
        
        JPanel panel1 = createHoverPanel();
        panel1.setBounds(60, 30, 200, 100);
        pnlDashboard.add(panel1);
        
        JPanel panel2 = createHoverPanel();
        panel2.setBounds(290, 30, 200, 100);
        pnlDashboard.add(panel2);
        
        JPanel panel3 = createHoverPanel();
        panel3.setBounds(520, 30, 200, 100);
        pnlDashboard.add(panel3);
        
        JPanel panel4 = createHoverPanel();
        panel4.setBounds(750, 30, 200, 100);
        pnlDashboard.add(panel4);
        
        JPanel panel5 = createHoverPanel();
        panel5.setBounds(980, 30, 200, 100);
        pnlDashboard.add(panel5);
        
        return pnlDashboard;
    }
}
