package v04;

import java.util.Scanner;
import librerias.Consola;
import java.util.ArrayList;
import java.util.Collections;

public class VisiCalcUI {
    private Viewport viewport;
    private Scanner scanner;

    public VisiCalcUI(HojaDeCalculo hoja) {
        this.viewport = new Viewport(hoja, 15, 10);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean estaOperativo = true;
        while (estaOperativo) {
            mostrarHoja();
            char comando = scanner.next().toUpperCase().charAt(0);
            estaOperativo = procesarComando(comando);
        }
        System.out.println("Saliendo del programa.");
        scanner.close();
    }

    private boolean procesarComando(char comando) {
        switch (comando) {
            case 'W':
                viewport.moverCursor(-1, 0);
                break;
            case 'A':
                viewport.moverCursor(0, -1);
                break;
            case 'S':
                viewport.moverCursor(1, 0);
                break;
            case 'D':
                viewport.moverCursor(0, 1);
                break;
            case 'E':
                editarCeldaActual();
                break;
            case 'O':
                ordenarColumna();
                break;
            case 'Q':
                return false;
            default:
                System.out.println("Comando inválido. Intente nuevamente.");
        }
        return true;
    }

    private void mostrarHoja() {
        Consola.limpiarPantalla();
        System.out.println("OPCIONES: desplazarse: wasd | editar: e | ordenar columna: o | salir: q");
        System.out.print("      ");
        for (int j = 0; j < viewport.getColumnasViewport(); j++) {
            char letraColumna = (char) ('A' + viewport.getColumnaInicio() + j);
            System.out.printf("%-8s", letraColumna);
        }
        System.out.println();

        for (int i = 0; i < viewport.getFilasViewport(); i++) {
            System.out.printf("%-5d|", viewport.getFilaInicio() + i + 1);

            for (int j = 0; j < viewport.getColumnasViewport(); j++) {
                String celda = viewport.getCelda(i, j).getContenido();
                celda = celda.length() > 5 ? celda.substring(0, 5) : String.format("%-5s", celda);

                if (i == viewport.getFilaCursorGlobal() - viewport.getFilaInicio()
                        && j == viewport.getColumnaCursorGlobal() - viewport.getColumnaInicio()) {
                    System.out.print("[" + celda + "]");
                } else {
                    System.out.print(" " + celda + " ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        Consola.posicionarse(2, 10);
    }

    private void editarCeldaActual() {
        Celda celdaActual = viewport.getCeldaCursor();
        Consola.posicionarse(2, 1);
        System.out.print("Ingrese el texto: ");
        String texto = scanner.next();
        celdaActual.setContenido(texto);
    }

    private void ordenarColumna() {
        System.out.print("Ingrese la columna a ordenar (A-Z): ");
        char columnaChar = scanner.next().toUpperCase().charAt(0);
        int columna = columnaChar - 'A';

        System.out.print("Ingrese la fila de inicio: ");
        int filaInicio = scanner.nextInt() - 1;

        System.out.print("Ingrese la fila de fin: ");
        int filaFin = scanner.nextInt() - 1;

        System.out.print("Orden ascendente? (S/N): ");
        boolean ascendente = scanner.next().equalsIgnoreCase("S");

        ArrayList<Integer> valores = new ArrayList<>();
        for (int i = filaInicio; i <= filaFin; i++) {
            String contenido = viewport.getCelda(i - viewport.getFilaInicio(), columna - viewport.getColumnaInicio())
                    .getContenido();
            try {
                valores.add(Integer.parseInt(contenido.trim()));
            } catch (NumberFormatException e) {
                valores.add(0);
            }
        }

        if (ascendente) {
            Collections.sort(valores);
        } else {
            valores.sort(Collections.reverseOrder());
        }

        for (int i = filaInicio; i <= filaFin; i++) {
            viewport.getCelda(i - viewport.getFilaInicio(), columna - viewport.getColumnaInicio())
                    .setContenido(String.valueOf(valores.get(i - filaInicio)));
        }
    }
}
