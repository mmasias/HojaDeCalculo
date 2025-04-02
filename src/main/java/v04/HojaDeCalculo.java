package v04;

import java.util.Arrays;

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

    public void ordenarColumna(int columna, int filaInicio, int filaFin, boolean ascendente) {
        Integer[] valores = new Integer[filaFin - filaInicio + 1];
        for (int i = filaInicio, index = 0; i <= filaFin; i++, index++) {
            try {
                valores[index] = Integer.parseInt(celdas[i][columna].getContenido().trim());
            } catch (NumberFormatException e) {
                valores[index] = Integer.MAX_VALUE; 
            }
        }
        Arrays.sort(valores);
        if (!ascendente) {
            for (int i = 0, j = valores.length - 1; i < j; i++, j--) {
                int temp = valores[i];
                valores[i] = valores[j];
                valores[j] = temp;
            }
        }
        for (int i = filaInicio, index = 0; i <= filaFin; i++, index++) {
            celdas[i][columna].setContenido(valores[index] == Integer.MAX_VALUE ? "" : String.valueOf(valores[index]));
        }
    }
}