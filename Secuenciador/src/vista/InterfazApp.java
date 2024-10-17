package vista;

import java.awt.Color;
import javax.swing.JFrame;

public class InterfazApp extends JFrame {

    private PanelSeleccionar pnlSeleccionar;

    public InterfazApp() {
        setTitle("Secuenciador");
        setSize(380, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(254, 224, 138));

        pnlSeleccionar = new PanelSeleccionar();
        pnlSeleccionar.setBounds(90, 100, 200, 50);
        add(pnlSeleccionar);

        setVisible(true);
    }

    public static void main(String[] args) {
        InterfazApp main = new InterfazApp();
    }

}
