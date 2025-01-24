
/*
icon used:
https://www.flaticon.com/free-icon/warehouse_3045528?term=stocks&page=1&position=36&origin=search&related_id=3045528
*/

import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Stocks {
    private static JPanel pnlStocks = new JPanel();
    public static JPanel pnlStocks(){
        pnlStocks.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlStocks.setLayout(null);
        pnlStocks.setBackground(Color.pink);
        return pnlStocks;
    }
}
