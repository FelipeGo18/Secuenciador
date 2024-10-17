package vista;

import java.awt.Color;
import javax.swing.JFrame;
import controlador.Controlador;

public class InterfazApp extends JFrame {

    private PanelSeleccionar pnlSeleccionar;

    public InterfazApp() {
        setTitle("Secuenciador");
        setSize(380, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(224, 242, 241));

        pnlSeleccionar = new PanelSeleccionar();
        pnlSeleccionar.setBounds(90, 100, 200, 50);
        add(pnlSeleccionar);

        // Crear el controlador y conectar la vista
        Controlador controlador = new Controlador(pnlSeleccionar);

        setVisible(true);
    }

    public static void main(String[] args) {
        InterfazApp main = new InterfazApp();
    }
}
