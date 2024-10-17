package vista;

import java.awt.Color;
import javax.swing.JFrame;
import controlador.Controlador;

/**
 * La clase InterfazApp representa la interfaz gráfica del secuenciador. Permite
 * al usuario seleccionar un archivo de texto y muestra su contenido.
 */
public class InterfazApp extends JFrame {

    private PanelSeleccionar pnlSeleccionar; // Panel para seleccionar el archivo
    private Controlador controlador; // Controlador para manejar la lógica del secuenciador

    /**
     * Constructor de la clase InterfazApp. Inicializa la ventana de la
     * aplicación y configura los componentes.
     */
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
        controlador = new Controlador(pnlSeleccionar);

        setVisible(true);
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta
     * aplicación).
     */
    public static void main(String[] args) {
        InterfazApp main = new InterfazApp();
    }
}
