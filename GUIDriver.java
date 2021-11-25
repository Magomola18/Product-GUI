package Product_GUI;


import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class GUIDriver {
    public static void main(String args[]) {

        ProductGUI aFrame = new ProductGUI();
        aFrame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }
}


