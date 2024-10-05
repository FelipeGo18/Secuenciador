package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.ReadText;

public class PanelSeleccionar extends JPanel {

    private JButton btnSeleccionar;
    private String archivoSeleccionado;

    public PanelSeleccionar() {
        setLayout(new BorderLayout());

        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(new Color(51, 116, 250));
        btnSeleccionar.setForeground(Color.WHITE);
        btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSeleccionar.addActionListener(createFileChooserListener());

        add(btnSeleccionar, BorderLayout.CENTER);
    }

    private ActionListener createFileChooserListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // Abrir el cuadro de diálogo para seleccionar archivos
                int resultado = fileChooser.showOpenDialog(null);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    // Obtener el archivo seleccionado
                    archivoSeleccionado = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!archivoSeleccionado.toLowerCase().endsWith(".txt")) {
                        JOptionPane.showMessageDialog(null, "Por favor, selecciona un archivo .txt.", "Error", JOptionPane.ERROR_MESSAGE);
                        archivoSeleccionado = null; // Limpiar la variable si no es .txt
                    } else {
                        // Crear una instancia de ReadText y leer el archivo
                        ReadText readText = new ReadText(archivoSeleccionado);
                        String contenido = readText.readFile();
                        
                        JOptionPane.showMessageDialog(null, "Contenido del archivo:\n" + contenido, "Contenido del Archivo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };
    }

    public String getArchivoSeleccionado() {
        return archivoSeleccionado;
    }
}



