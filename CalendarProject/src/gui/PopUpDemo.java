package gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpDemo extends JPopupMenu {
    JMenuItem anItem;
    public PopUpDemo(){
        anItem = new JMenuItem("Click Me!");
        add(anItem);
    }
}