package controlador;

import javax.swing.JOptionPane;
import modelo.ReadText;
import modelo.Secuenciador; // Importar el modelo
import vista.PanelSeleccionar;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Controlador que maneja la interacción entre la vista y el modelo en la
 * aplicación de secuenciador de archivos. Se encarga de leer el contenido de un
 * archivo de texto y crear un archivo de salida con los resultados procesados.
 */
public class Controlador {

    private PanelSeleccionar pnlSeleccionar; // Panel de selección de archivo
    private String contenidoArchivo; // Contenido del archivo leído

    /**
     * Constructor del controlador.
     *
     * @param pnlSeleccionar El panel desde el que se selecciona el archivo.
     */
    public Controlador(PanelSeleccionar pnlSeleccionar) {
        this.pnlSeleccionar = pnlSeleccionar;
        this.pnlSeleccionar.setControlador(this); // Inyectar el controlador en el panel
    }

    /**
     * Procesa el archivo seleccionado, lee su contenido y crea un archivo de
     * salida con los resultados.
     *
     * @param archivoSeleccionado Ruta del archivo a procesar.
     */
    public void procesarArchivo(String archivoSeleccionado) {
        ReadText readText = new ReadText(archivoSeleccionado); // Crear instancia para leer el archivo
        try {
            // Leer el contenido del archivo
            contenidoArchivo = readText.readFile();
            // Procesar el contenido y generar la salida
            List<String> resultado = Secuenciador.secuenciarCodigo(Secuenciador.leerArchivo(archivoSeleccionado));

            // Crear el archivo de salida con el resultado
            crearArchivoSalida("resultado1000993.txt", String.join("\n", resultado));

        } catch (Exception ex) {
            // Manejar errores en la lectura del archivo
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Crea un archivo de salida con el contenido procesado.
     *
     * @param nombreArchivo Nombre del archivo de salida.
     * @param contenido Contenido a escribir en el archivo.
     */
    private void crearArchivoSalida(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido); // Escribir el contenido en el archivo
            writer.flush(); // Asegurarse de que todos los datos se escriban
            JOptionPane.showMessageDialog(null, "Archivo creado: " + nombreArchivo,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            // Manejar errores en la creación del archivo
            JOptionPane.showMessageDialog(null, "Error al crear el archivo: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Devuelve el contenido del archivo leído.
     *
     * @return Contenido del archivo.
     */
    public String getContenidoArchivo() {
        return contenidoArchivo;
    }
}
