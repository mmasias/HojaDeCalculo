package v04;

import java.util.Scanner;

import librerias.Consola;

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

    private void mostrarHoja() {
        Consola.limpiarPantalla();
        mostrarOpciones();
        System.out.println();
        System.out.println();
        System.out.println();
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

    private void mostrarOpciones() {

        int filaActual = viewport.getFilaCursorGlobal();
        int columnaActual = viewport.getColumnaCursorGlobal();
        char letraColumna = (char) ('A' + columnaActual);

        System.out.print("[" + letraColumna + (filaActual + 1) + "] ");
        System.out.println("OPCIONES: desplazarse: wasd | editar: e | salir: q | ordenar: o |");
        System.out.println("COMANDO >");

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
            case 'Q':
                return false;
            case 'O':
                ordenarRango();
                break;
            default:
                System.out.println("Comando inválido. Intente nuevamente.");
        }
        return true;
    }

    private void editarCeldaActual() {
        Celda celdaActual = viewport.getCeldaCursor();
        Consola.posicionarse(2, 1);
        System.out.print("Ingrese el texto:");
        String texto = scanner.next();
        celdaActual.setContenido(texto);
    }

    private void ordenarRango() {
        Consola.posicionarse(2, 1);
        System.out.print("Ingrese rango (inicio fin): ");
        try {
            int inicio = Integer.parseInt(scanner.next()) - 1;
            int fin = Integer.parseInt(scanner.next()) - 1;
            Rango rango = new Rango(viewport.getColumnaCursorGlobal(), inicio, fin);
            ordenarRango(rango);
        } catch (NumberFormatException e) {
            System.out.print("Formato inválido. Use: numero numero");
        }
    }

    private void ordenarRango(Rango rango) {
        System.out.print("Ordenar (1:asc, 2:desc): ");
        try {
            int opcion = Integer.parseInt(scanner.next());
            if (opcion != 1 && opcion != 2) {
                throw new NumberFormatException();
            }
            boolean ascendente = opcion == 1;
            viewport.getHoja().ordenarColumnaEnRango(rango, ascendente);
        } catch (NumberFormatException e) {
            System.out.print("Opción inválida. Use 1 para ascendente o 2 para descendente");
        }
    }
}
