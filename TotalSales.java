
/*
icon used:
https://www.flaticon.com/free-icon/sales_10139618?term=sales&page=1&position=17&origin=search&related_id=10139618
*/

import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class TotalSales {
    private static JPanel pnlTotalSales = new JPanel();
    public static JPanel pnlTotalSales(){
        pnlTotalSales.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlTotalSales.setLayout(null);
        pnlTotalSales.setBackground(Color.yellow);
        return pnlTotalSales;
    }
}
