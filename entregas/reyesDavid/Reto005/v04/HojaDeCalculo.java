package entregas.reyesDavid.Reto005;

import java.util.Arrays;
import java.util.Comparator;

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

    public void ordenarColumna(int columna, boolean ascendente) {
        Arrays.sort(celdas, Comparator.comparing((Celda[] fila) -> {
            try {
                return Integer.parseInt(fila[columna].getContenido());
            } catch (NumberFormatException e) {
                return ascendente ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }));

        if (!ascendente) {
            for (int i = 0; i < FILAS / 2; i++) {
                Celda[] temp = celdas[i];
                celdas[i] = celdas[FILAS - 1 - i];
                celdas[FILAS - 1 - i] = temp;
            }
        }
    }
}
