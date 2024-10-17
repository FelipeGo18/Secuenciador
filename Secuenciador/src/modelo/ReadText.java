package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * La clase ReadText se encarga de leer archivos de texto y devolver su
 * contenido.
 */
public class ReadText {

    private String filePath; // Ruta del archivo a leer

    /**
     * Constructor que inicializa la ruta del archivo.
     *
     * @param filePath Ruta del archivo a leer.
     */
    public ReadText(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Método para leer el archivo de texto y devolver su contenido como una
     * cadena.
     *
     * @return Contenido del archivo leído.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public String readFile() throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            // Manejo de errores mejorado
            throw new IOException("Error al leer el archivo: " + e.getMessage(), e);
        }

        return content.toString().trim(); // Se elimina el último salto de línea
    }
}
