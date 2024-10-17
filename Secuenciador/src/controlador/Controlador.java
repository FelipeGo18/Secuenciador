package controlador;

import javax.swing.JOptionPane;
import modelo.ReadText;
import vista.PanelSeleccionar;

public class Controlador {

    private PanelSeleccionar pnlSeleccionar;

    public Controlador(PanelSeleccionar pnlSeleccionar) {
        this.pnlSeleccionar = pnlSeleccionar;
        this.pnlSeleccionar.setControlador(this); // Inyectar el controlador en el panel
    }

    public void procesarArchivo(String archivoSeleccionado) {
        ReadText readText = new ReadText(archivoSeleccionado);
        try {
            String contenido = readText.readFile();
            JOptionPane.showMessageDialog(null, "Contenido del archivo:\n" + contenido, "Contenido del Archivo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
