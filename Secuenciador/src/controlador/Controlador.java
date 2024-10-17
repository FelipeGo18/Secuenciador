package controlador;

import javax.swing.JOptionPane;
import modelo.ReadText;
import vista.InterfazApp;
import vista.PanelSeleccionar;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
            JOptionPane.showMessageDialog(null, "Contenido del archivo:\n" + contenidoArchivo,
                    "Contenido del Archivo", JOptionPane.INFORMATION_MESSAGE);

            // Mostrar el contenido en la interfaz
            ((InterfazApp) pnlSeleccionar.getTopLevelAncestor()).mostrarContenidoArchivo();

            // Procesar el contenido y generar la salida
            String resultado = procesarContenido(contenidoArchivo);

            // Crear el archivo de salida con el resultado
            crearArchivoSalida("resultado.txt", resultado);

        } catch (Exception ex) {
            // Manejar errores en la lectura del archivo
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para procesar el contenido del archivo y retornar el resultado.
     *
     * @param contenido Contenido del archivo leído.
     * @return Resultado del procesamiento del contenido.
     */
    private String procesarContenido(String contenido) {
        // Aquí iría la lógica para procesar el contenido y obtener el resultado
        // Por simplicidad, se devuelve el contenido original
        return contenido; // Cambia esto según tu lógica de procesamiento
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
