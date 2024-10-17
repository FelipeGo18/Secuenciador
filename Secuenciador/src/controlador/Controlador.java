package controlador;

import javax.swing.JOptionPane;
import modelo.ReadText;
import vista.InterfazApp;
import vista.PanelSeleccionar;

/**
 * La clase Controlador se encarga de gestionar la lógica de la aplicación
 * relacionada con la selección y lectura de archivos de texto. Actúa como
 * intermediario entre la vista (PanelSeleccionar) y el modelo (ReadText).
 */
public class Controlador {

    private PanelSeleccionar pnlSeleccionar; // Panel de selección de archivos
    private String contenidoArchivo; // Almacena el contenido del archivo leído

    /**
     * Constructor de la clase Controlador.
     *
     * @param pnlSeleccionar El panel de selección donde se encuentra el botón
     *                       para seleccionar archivos.
     */
    public Controlador(PanelSeleccionar pnlSeleccionar) {
        this.pnlSeleccionar = pnlSeleccionar; // Inicializa el panel de selección
        this.pnlSeleccionar.setControlador(this); // Establece el controlador en el panel
    }

    /**
     * Procesa el archivo seleccionado. Lee su contenido utilizando la clase
     * ReadText y muestra el contenido en un cuadro de diálogo. También
     * notifica a la interfaz principal sobre el contenido leído.
     *
     * @param archivoSeleccionado La ruta del archivo seleccionado por el
     *                            usuario.
     */
    public void procesarArchivo(String archivoSeleccionado) {
        ReadText readText = new ReadText(archivoSeleccionado); // Crea una instancia de ReadText
        try {
            contenidoArchivo = readText.readFile(); // Lee el contenido del archivo
            JOptionPane.showMessageDialog(null, "Contenido del archivo:\n" + contenidoArchivo,
                    "Contenido del Archivo", JOptionPane.INFORMATION_MESSAGE);
            // Llama al método para mostrar el contenido en la interfaz principal
            ((InterfazApp) pnlSeleccionar.getTopLevelAncestor()).mostrarContenidoArchivo();
        } catch (Exception ex) {
            // Maneja errores al leer el archivo
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Devuelve el contenido del archivo leído.
     *
     * @return Una cadena que representa el contenido del archivo, o null si
     *         aún no se ha leído ningún archivo.
     */
    public String getContenidoArchivo() {
        return contenidoArchivo; // Retorna el contenido almacenado
    }
}