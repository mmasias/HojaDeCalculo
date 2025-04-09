import java.util.Scanner;

public class HojaDeCalculo {

    private Celda[][] celdas;
    private final int FILAS;
    private final int COLUMNAS;
    private Scanner scanner;

    public HojaDeCalculo(int numeroFilas, int numeroColumnas) {
        this.FILAS = numeroFilas;
        this.COLUMNAS = numeroColumnas;
        celdas = new Celda[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }

    public int getNumeroDeFilas() {
        return FILAS;
    }

    public int getNumeroDeColumnas() {
        return COLUMNAS;
    }
    
    public void bubleSort(){
        System.out.println("Que columna quieres ordenar: ");
        int columna = scanner.nextInt();

        if (columna < 0 || columna >= COLUMNAS) {
            System.out.println("Columna fuera de rango.");
            return;
        }

        for (int i = 0; i < FILAS - 1; i++) {
            for (int j = 0; j < FILAS - 1 - i; j++) {
                String contenidoActual = celdas[j][columna].getContenido();
                String contenidoSiguiente = celdas[j + 1][columna].getContenido();
                
                if (contenidoActual.compareTo(contenidoSiguiente) > 0) {
                    String temp = contenidoActual;
                    celdas[j][columna].setContenido(contenidoSiguiente);
                    celdas[j + 1][columna].setContenido(temp);
                }
            }
        }
    }
}
