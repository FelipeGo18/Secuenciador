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

public class PanelSeleccionar extends JPanel {

    private JButton btnSeleccionar;
    private String archivoSeleccionado;
    private Controlador controlador; // Referencia al controlador

    public PanelSeleccionar() {
        setLayout(new BorderLayout());

        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(new Color(174, 214, 241));
        btnSeleccionar.setForeground(Color.BLACK);
        btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSeleccionar.addActionListener(createFileChooserListener());

        add(btnSeleccionar, BorderLayout.CENTER);
    }

    // Método para inyectar el controlador
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

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
                    controlador.procesarArchivo(archivoSeleccionado); // Llamar al controlador
                }
            }
        };
    }

    public String getArchivoSeleccionado() {
        return archivoSeleccionado;
    }
}
