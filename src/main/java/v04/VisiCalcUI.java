package v04;

import java.util.Scanner;

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
            mostrarOpciones();

            char comando = scanner.next().toUpperCase().charAt(0);
            estaOperativo = procesarComando(comando);
        }

        System.out.println("Saliendo del programa.");
        scanner.close();
    }

    private void mostrarHoja() {
        
        imprimirEncabezadoColumna();
        for (int i = 0; i < viewport.getFilasViewport(); i++) {
            imprimirEncabezadoFila(i);
            for (int j = 0; j < viewport.getColumnasViewport(); j++) {
                String contenidoCelda = viewport.getCelda(i, j).getContenido();
                contenidoCelda = contenidoCelda.length() > 5 ? contenidoCelda.substring(0, 5) : String.format("%-5s", contenidoCelda);
                char separadorIzquierda = ' ';
                char separadorDerecha = ' ';
                if(i==viewport.getFilaCursorGlobal() && j == viewport.getColumnaCursorGlobal()){
                    separadorIzquierda = '[';
                    separadorDerecha = ']';
                }
                System.out.print(separadorIzquierda + contenidoCelda + separadorDerecha + "|");
            }

            System.out.println();
        }
    }

    private void imprimirEncabezadoFila(int i) {
        System.out.printf("%-5d|", viewport.getFilaInicio() + i + 1);
    }

    private void imprimirEncabezadoColumna() {
        System.out.print("      ");
        for (int j = 0; j < viewport.getColumnasViewport(); j++) {
            char letraColumna = (char) ('A' + viewport.getColumnaInicio() + j);
            System.out.printf("%-8s", letraColumna);
        }
        System.out.println();
    }

    private void mostrarOpciones() {

        int filaActual = viewport.getFilaCursorGlobal();
        int columnaActual = viewport.getColumnaCursorGlobal();
        char letraColumna = (char) ('A' + columnaActual);

        System.out.println("Posición actual: " + letraColumna + (filaActual + 1));
        System.out.println("Utilice las teclas W, A, S y D para moverse.");
        System.out.println("Presione 'E' para ingresar texto en la celda actual.");
        System.out.println("Presione 'Q' para salir.");
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
            default:
                System.out.println("Comando inválido. Intente nuevamente.");
        }
        return true;
    }

    private void editarCeldaActual() {
        Celda celdaActual = viewport.getCeldaCursor();
        System.out.println("Ingrese el texto para la celda seleccionada:");
        String texto = scanner.next();
        celdaActual.setContenido(texto);
    }
}
