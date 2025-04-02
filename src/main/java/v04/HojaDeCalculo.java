package v04;

import java.util.Scanner;

public class HojaDeCalculo {

    private Celda[][] celdas;
    private final int FILAS;
    private final int COLUMNAS;

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

    public void ordenarColumna(int columna) {
        for (int i = 0; i < FILAS; i++) {
            int minIndex = i;
            for (int j = i; j < FILAS - 1; j++) {
                try {
                    String celdaInicial = celdas[minIndex][columna].getContenido();
                    String celdaActual = celdas[j][columna].getContenido();

                    if (celdaInicial.isEmpty() || celdaActual.isEmpty()) {
                        continue;
                    }

                    int numeroInicial = Integer.parseInt(celdaInicial);
                    int numeroActual = Integer.parseInt(celdaActual);

                    if (numeroActual < numeroInicial) {
                        minIndex = j;
                    }

                } catch (Error e) {
                    continue;
                }
            }
            if (minIndex != i) {
                String temp = celdas[i][columna].getContenido();
                celdas[i][columna].setContenido(celdas[minIndex][columna].getContenido());
                celdas[minIndex][columna].setContenido(temp);
            }
        }
    }
}
