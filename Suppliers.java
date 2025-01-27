
/*
icon used:
https://www.flaticon.com/free-icon/supplier_18255220?term=supplier&page=1&position=21&origin=search&related_id=18255220
*/

import java.awt.*;    
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Suppliers {
    private static JPanel pnlSuppliers = new JPanel();
    public static JPanel pnlSuppliers(){
        pnlSuppliers.setSize(Main.pnlMainWidth, Main.pnlMainHeight);
        pnlSuppliers.setLayout(null);
        pnlSuppliers.setBackground(Color.blue);
        return pnlSuppliers;
    }
}
