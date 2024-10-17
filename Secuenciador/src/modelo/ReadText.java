package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadText {

    private String filePath;

    public ReadText(String filePath) {
        this.filePath = filePath;
    }

    // Método para leer el archivo de texto y devolver su contenido
    public String readFile() {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Eliminar el último salto de línea adicional
        if (content.length() > 0) {
            content.setLength(content.length() - System.lineSeparator().length());
        }

        return content.toString();
    }
}
