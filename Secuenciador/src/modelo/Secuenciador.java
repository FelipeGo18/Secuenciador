package modelo;

import java.io.*;
import java.util.*;

public class Secuenciador {

    public static List<String> leerArchivo(String archivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea.trim());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    public static void secuenciarCodigo(List<String> codigo) {
        Stack<BloqueInfo> pilaBloques = new Stack<>(); // Pila para almacenar la información de cualquier bloque
        List<String> salida = new ArrayList<>();
        int contadorLinea = 1;

        for (String linea : codigo) {
            if (linea.startsWith("if")) {
                String condicion = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                salida.add(String.format("%d\t%s\t-", contadorLinea, condicion));
                pilaBloques.push(new BloqueInfo("if", contadorLinea));
                contadorLinea++;
            } else if (linea.startsWith("else")) {
                salida.add(String.format("%d\t-\t-", contadorLinea));
                pilaBloques.push(new BloqueInfo("else", contadorLinea));
                contadorLinea++;
            } else if (linea.startsWith("while")) {
                String condicion = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                salida.add(String.format("%d\t%s\t-", contadorLinea, condicion));
                pilaBloques.push(new BloqueInfo("while", contadorLinea));
                contadorLinea++;
            } else if (linea.startsWith("for")) {
                String partesFor = linea.substring(linea.indexOf('(') + 1, linea.lastIndexOf(')'));
                String[] partes = partesFor.split(";");

                // Parte 1 (inicialización)
                salida.add(String.format("%d\t%s\t-", contadorLinea, partes[0].trim()));
                contadorLinea++;

                // Parte 2 (condición) - Salto si no cumple la condición
                salida.add(String.format("%d\t%s\t-", contadorLinea, partes[1].trim()));
                int lineaCondicion = contadorLinea;
                contadorLinea++;

                // Parte 3 (incremento) - Guardamos para más tarde
                String incremento = partes[2].trim();
                pilaBloques.push(new BloqueInfo("for", lineaCondicion, incremento)); // Almacenamos la condición e incremento del for

            } else if (linea.equals("{")) {
                // Apertura de bloque, no se hace nada
            } else if (linea.equals("}")) {
                // Cierre de bloques
                if (!pilaBloques.isEmpty()) {
                    BloqueInfo bloque = pilaBloques.pop();

                    switch (bloque.tipo) {
                        case "if", "else" -> salida.set(bloque.linea - 1, salida.get(bloque.linea - 1).replace("-", String.valueOf(contadorLinea)));
                        case "while" -> {
                            salida.add(String.format("%d\tjump\t%d", contadorLinea, bloque.linea));
                            salida.set(bloque.linea - 1, salida.get(bloque.linea - 1).replace("-", String.valueOf(contadorLinea + 1)));
                            contadorLinea++;
                        }
                        case "for" -> {
                            // Añadimos el incremento en lugar del `end`
                            salida.add(String.format("%d\t%s\t%d", contadorLinea, bloque.incremento, bloque.linea));
                            contadorLinea++;

                            // Actualizamos la condición del `for` para que salte al final si no se cumple
                            salida.set(bloque.linea - 1, salida.get(bloque.linea - 1).replace("-", String.valueOf(contadorLinea)));
                        }
                    }
                }
            } else {
                // Instrucción normal
                salida.add(String.format("%d\t%s\t-", contadorLinea, linea));
                contadorLinea++;
            }
        }

        // Imprimir el resultado final
        for (String s : salida) {
            System.out.println(s);
        }
    }

    // Clase auxiliar para almacenar la información de los bloques
    static class BloqueInfo {

        String tipo;
        int linea;
        String incremento; // Solo se usa para los for

        BloqueInfo(String tipo, int linea) {
            this.tipo = tipo;
            this.linea = linea;
        }

        BloqueInfo(String tipo, int linea, String incremento) {
            this.tipo = tipo;
            this.linea = linea;
            this.incremento = incremento;
        }
    }
}
