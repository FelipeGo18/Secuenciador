package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import controlador.Controlador;

/**
 * La clase PanelSeleccionar representa un panel que permite al usuario seleccionar un archivo de texto.
 * Contiene un botón que abre un cuadro de diálogo para elegir el archivo y se comunica con el controlador.
 */
public class PanelSeleccionar extends JPanel {

    private JButton btnSeleccionar; // Botón para seleccionar el archivo
    private String archivoSeleccionado; // Ruta del archivo seleccionado
    private Controlador controlador; // Referencia al controlador

    /**
     * Constructor de la clase PanelSeleccionar.
     * Inicializa el botón y configura el diseño del panel.
     */
    public PanelSeleccionar() {
        setLayout(new BorderLayout());

        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(new Color(174, 214, 241));
        btnSeleccionar.setForeground(Color.BLACK);
        btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSeleccionar.addActionListener(createFileChooserListener()); // Añadir el listener al botón

        add(btnSeleccionar, BorderLayout.CENTER);
    }

    /**
     * Método para inyectar el controlador en el panel.
     *
     * @param controlador El controlador que maneja la lógica de negocio.
     */
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Crea un ActionListener para el botón de selección de archivos.
     * Abre un cuadro de diálogo para seleccionar archivos de texto.
     *
     * @return ActionListener para manejar el evento de selección de archivo.
     */
    private ActionListener createFileChooserListener() {
        return (ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            // Filtrar solo archivos de texto
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt", "text", "in", "out");
            fileChooser.setFileFilter(filter);

            // Abrir el cuadro de diálogo para seleccionar archivos
            int resultado = fileChooser.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                // Obtener el archivo seleccionado
                archivoSeleccionado = fileChooser.getSelectedFile().getAbsolutePath();
                if (controlador != null) { // Asegurarse de que el controlador no sea nulo
                    controlador.procesarArchivo(archivoSeleccionado); // Llamar al controlador para procesar el archivo
                }
            }
        };
    }

    /**
     * Devuelve la ruta del archivo seleccionado.
     *
     * @return La ruta del archivo seleccionado como String.
     */
    public String getArchivoSeleccionado() {
        return archivoSeleccionado;
    }
}
