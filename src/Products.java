
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
        pnlProducts.setBackground(Color.green);
        return pnlProducts;
    }
}
