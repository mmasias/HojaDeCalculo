package v04;

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

    public void ordenarColumna(int columna, boolean ascendente) {
        if (columna < 0 || columna >= COLUMNAS) {
            return;
        }

        for (int i = 0; i < FILAS - 1; i++) {
            for (int j = 0; j < FILAS - i - 1; j++) {
                if (debeIntercambiar(j, j + 1, columna, ascendente)) {
                    intercambiarFilas(j, j + 1);
                }
            }
        }
    }

    private boolean debeIntercambiar(int fila1, int fila2, int columna, boolean ascendente) {
        String contenido1 = celdas[fila1][columna].getContenido();
        String contenido2 = celdas[fila2][columna].getContenido();

        try {
            double num1 = Double.parseDouble(contenido1);
            double num2 = Double.parseDouble(contenido2);
            return ascendente ? num1 > num2 : num1 < num2;
        } catch (NumberFormatException e) {
            return ascendente ? 
                contenido1.compareTo(contenido2) > 0 : 
                contenido1.compareTo(contenido2) < 0;
        }
    }

    private void intercambiarFilas(int fila1, int fila2) {
        for (int j = 0; j < COLUMNAS; j++) {
            Celda temp = celdas[fila1][j];
            celdas[fila1][j] = celdas[fila2][j];
            celdas[fila2][j] = temp;
        }
    }
}