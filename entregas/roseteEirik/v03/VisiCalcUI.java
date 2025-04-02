package entregas.roseteEirik.v03;

import java.util.Scanner;
import librerias.Consola;

public class VisiCalcUI {
    private HojaDeCalculo hoja;
    private Navegador navegador;
    private Scanner scanner;

    public VisiCalcUI(HojaDeCalculo hoja) {
        this.hoja = hoja;
        this.navegador = new Navegador(hoja);
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
        mostrarOpciones(navegador);
        System.out.print("      ");
        for (int j = 0; j < hoja.getNumeroDeColumnas(); j++) {
            char letraColumna = (char) ('A' + j);
            System.out.printf("%-8s", letraColumna);
        }
        System.out.println();

        for (int i = 0; i < hoja.getNumeroDeFilas(); i++) {
            System.out.printf("%-5d|", i + 1);

            for (int j = 0; j < hoja.getNumeroDeColumnas(); j++) {
                String celda = hoja.getCelda(i, j).getContenido();
                celda = celda.length() > 5 ? celda.substring(0, 5) : String.format("%-5s", celda);

                if (i == navegador.getFilaActual() && j == navegador.getColumaActual()) {
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

    private void mostrarOpciones(Navegador navegador) {
        System.out.print("[" + (char) ('A' + navegador.getColumaActual()) + (navegador.getFilaActual() + 1) + "] ");
        System.out.println("OPCIONES: desplazarse: wasd | editar: e | ordenar: b | salir: q");
        System.out.println("COMANDO >");
    }

    private boolean procesarComando(char comando) {
        switch (comando) {
            case 'W':
                navegador.moverArriba();
                break;
            case 'A':
                navegador.moverIzquierda();
                break;
            case 'S':
                navegador.moverAbajo();
                break;
            case 'D':
                navegador.moverDerecha();
                break;
            case 'E':
                editarCeldaActual();
                break;
            case 'B':
                ordenarColumnaActual();
                break;
            case 'Q':
                return false;
            default:
                System.out.println("Comando inválido. Intente nuevamente.");
        }
        return true;
    }

    private void editarCeldaActual() {
        Celda celdaActual = navegador.getCeldaActual();
        Consola.posicionarse(2, 1);
        System.out.print ("Ingrese el texto:");
        String texto = scanner.next();
        celdaActual.setContenido(texto);
    }

    private void ordenarColumnaActual() {
        BubbleSort.ordenarColumna(hoja, navegador.getColumaActual());
    }
}
