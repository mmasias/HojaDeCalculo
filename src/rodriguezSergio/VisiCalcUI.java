import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class VisiCalcUI {
    private Viewport viewport;
    private HojaDeCalculo hoja;
    private Scanner scanner;

    public VisiCalcUI(HojaDeCalculo hoja) {
        this.hoja = hoja;
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
                
                if (i == viewport.getFilaCursorGlobal() - viewport.getFilaInicio() && 
                    j == viewport.getColumnaCursorGlobal() - viewport.getColumnaInicio()) {
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
        System.out.println("OPCIONES: desplazarse: wasd | editar: e | ordenar: o | salir: q");
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
            case 'O':
                ordenarCeldas();
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
        Consola.posicionarse(2, 1);
        System.out.print("Ingrese el texto:");
        String texto = scanner.next();
        celdaActual.setContenido(texto);
    }

    private void ordenarCeldas() {
        Consola.limpiarPantalla();
        System.out.println("╔══════════════════════════╗");
        System.out.println("║      ORDENAR CELDAS      ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║ 1. Ordenar fila actual   ║");
        System.out.println("║ 2. Ordenar columna actual║");
        System.out.println("║ 3. Ordenar rango         ║");
        System.out.println("║ 4. Cancelar              ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Seleccione opción (1-4): ");
        
        char opcion = scanner.next().charAt(0);
        switch (opcion) {
            case '1':
                ordenarFilaActual();
                break;
            case '2':
                ordenarColumnaActual();
                break;
            case '3':
                ordenarRango();
                break;
            case '4':
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void ordenarFilaActual() {
        int fila = viewport.getFilaCursorGlobal();
        int colInicio = viewport.getColumnaInicio();
        int colFin = colInicio + viewport.getColumnasViewport() - 1;
        
        for (int i = colInicio; i < colFin; i++) {
            for (int j = i + 1; j <= colFin; j++) {
                Celda celdaI = hoja.getCelda(fila, i);
                Celda celdaJ = hoja.getCelda(fila, j);
                if (celdaI.getContenido().compareTo(celdaJ.getContenido()) > 0) {
                    String temp = celdaI.getContenido();
                    celdaI.setContenido(celdaJ.getContenido());
                    celdaJ.setContenido(temp);
                }
            }
        }
    }

    private void ordenarColumnaActual() {
        int col = viewport.getColumnaCursorGlobal();
        int filaInicio = viewport.getFilaInicio();
        int filaFin = filaInicio + viewport.getFilasViewport() - 1;
        
        for (int i = filaInicio; i < filaFin; i++) {
            for (int j = i + 1; j <= filaFin; j++) {
                Celda celdaI = hoja.getCelda(i, col);
                Celda celdaJ = hoja.getCelda(j, col);
                if (celdaI.getContenido().compareTo(celdaJ.getContenido()) > 0) {
                    String temp = celdaI.getContenido();
                    celdaI.setContenido(celdaJ.getContenido());
                    celdaJ.setContenido(temp);
                }
            }
        }
    }

    private void ordenarRango() {
        Consola.limpiarPantalla();
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║         ORDENAR POR RANGO        ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. Definir rango manualmente     ║");
        System.out.println("║ 2. Usar selección actual        ║");
        System.out.println("║ 3. Cancelar                     ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Seleccione opción (1-3): ");
        
        char opcion = scanner.next().charAt(0);
        switch (opcion) {
            case '1':
                ordenarRangoManual();
                break;
            case '2':
                ordenarRangoActual();
                break;
            case '3':
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void ordenarRangoManual() {
        try {
            System.out.print("Fila inicial (1-" + hoja.getNumeroDeFilas() + "): ");
            int filaInicio = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.print("Columna inicial (A-" + (char)('A' + hoja.getNumeroDeColumnas() - 1) + "): ");
            char colInicioChar = scanner.next().toUpperCase().charAt(0);
            int colInicio = colInicioChar - 'A';
            
            System.out.print("Fila final: ");
            int filaFin = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.print("Columna final: ");
            char colFinChar = scanner.next().toUpperCase().charAt(0);
            int colFin = colFinChar - 'A';

            if (filaInicio < 0 || filaFin >= hoja.getNumeroDeFilas() || 
                colInicio < 0 || colFin >= hoja.getNumeroDeColumnas() ||
                filaInicio > filaFin || colInicio > colFin) {
                System.out.println("Rango inválido!");
                return;
            }
            
            ordenarRangoSeleccionado(filaInicio, filaFin, colInicio, colFin);
            
        } catch (Exception e) {
            System.out.println("Error en la entrada de datos!");
            scanner.nextLine();
        }
    }

    private void ordenarRangoActual() {
        int filaInicio = viewport.getFilaInicio();
        int filaFin = filaInicio + viewport.getFilasViewport() - 1;
        int colInicio = viewport.getColumnaInicio();
        int colFin = colInicio + viewport.getColumnasViewport() - 1;
        
        ordenarRangoSeleccionado(filaInicio, filaFin, colInicio, colFin);
    }

    private void ordenarRangoSeleccionado(int filaInicio, int filaFin, int colInicio, int colFin) {
        List<Celda> celdas = new ArrayList<>();
        for (int i = filaInicio; i <= filaFin; i++) {
            for (int j = colInicio; j <= colFin; j++) {
                celdas.add(hoja.getCelda(i, j));
            }
        }
        
        celdas.sort((c1, c2) -> c1.getContenido().compareTo(c2.getContenido()));
        
        int index = 0;
        for (int i = filaInicio; i <= filaFin; i++) {
            for (int j = colInicio; j <= colFin; j++) {
                hoja.getCelda(i, j).setContenido(celdas.get(index++).getContenido());
            }
        }
    }
}