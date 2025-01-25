
/*
icon used:
https://www.flaticon.com/free-icon/product_9252074?term=products&page=1&position=33&origin=search&related_id=9252074
*/

import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Products {
    private static JPanel pnlProducts = new JPanel();
    public static JPanel pnlProducts(){
        pnlProducts.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlProducts.setLayout(null);
        pnlProducts.setBackground(Color.white); 
        
        JPanel panel = Main.createRoundedPanel(45, Main.stringcolorSelectedButton);
        panel.setBounds(70, 30, Main.pnlMainWidth-140, 100);
        panel.setLayout(null);
        
        
        JPanel pnlflex = Main.createFlexPanel(10, 10);
        pnlflex.setBounds(10, 10, Main.pnlMainWidth-140-20, 80);
//        pnlflex.setBackground(Color.red);
                
        JPanel cat1 = Main.createHoverPanel(Main.stringcolorPanelBG, "#94C3A0");
        pnlflex.add(cat1);
        
        panel.add(pnlflex);
        
        
        
        pnlProducts.add(panel);
        
        return pnlProducts;
    }
}
