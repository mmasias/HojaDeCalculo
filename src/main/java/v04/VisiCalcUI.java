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
                
                if (i == viewport.getFilaCursorGlobal() - viewport.getFilaInicio() && j == viewport.getColumnaCursorGlobal() - viewport.getColumnaInicio()) {
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
        System.out.println("OPCIONES: desplazarse: wasd | editar: e | salir: q | ordenar: o");
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
            case 'Q':
                return false;
            default:
                System.out.println("Comando inválido. Intente nuevamente.");
        }
        return true;
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

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            
            switch(opcion) {
                case 1:
                    ordenarFilaActual();
                    break;
                case 2:
                    ordenarColumnaActual();
                    break;
                case 3:
                    ordenarRango();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida. Presione Enter para continuar...");
                    scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Presione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private void ordenarFilaActual() {
        int filaViewport = viewport.getFilaCursorGlobal() - viewport.getFilaInicio();
    
        for (int col = 0; col < viewport.getColumnasViewport(); col++) {
            String contenido = viewport.getCelda(filaViewport, col).getContenido();
            if (!esNumero(contenido)) {
                System.out.println("Error: La fila contiene valores no numéricos. Presione Enter...");
                scanner.nextLine();
                return;
            }
        }
        
        System.out.print("¿Ordenar ascendente (A) o descendente (D)? ");
        char orden = scanner.next().toUpperCase().charAt(0);
        boolean ascendente = orden == 'A';
        Celda[] celdasFila = new Celda[viewport.getColumnasViewport()];

        for (int col = 0; col < viewport.getColumnasViewport(); col++) {
            celdasFila[col] = viewport.getCelda(filaViewport, col);
        }
        
        ordenarArrayCeldasNumericas(celdasFila, ascendente);
        
        System.out.println("Fila ordenada numéricamente. Presione Enter...");
        scanner.nextLine();

    }

    private void ordenarArrayCeldasNumericas(Celda[] celdas, boolean ascendente) {
        for (int i = 0; i < celdas.length - 1; i++) {
            for (int j = i + 1; j < celdas.length; j++) {
                double num1 = Double.parseDouble(celdas[i].getContenido());
                double num2 = Double.parseDouble(celdas[j].getContenido());
                
                if ((ascendente && num1 > num2) || (!ascendente && num1 < num2)) {
                    String temp = celdas[i].getContenido();
                    celdas[i].setContenido(celdas[j].getContenido());
                    celdas[j].setContenido(temp);
                }
            }
        }
    }

    private boolean esNumero(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void ordenarColumnaActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordenarColumnaActual'");
    }

    private void ordenarRango() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordenarRango'");
    }

    private void editarCeldaActual() {
        Celda celdaActual = viewport.getCeldaCursor();
        Consola.posicionarse(2, 1);
        System.out.print ("Ingrese el texto:");
        String texto = scanner.next();
        celdaActual.setContenido(texto);
    }
}
