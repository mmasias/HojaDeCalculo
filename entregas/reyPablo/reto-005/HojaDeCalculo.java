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
        for (int i = 1; i < FILAS; i++) {
            String valorActual = celdas[i][columna].getContenido();
            int j = i - 1;
            while (j >= 0 && celdas[j][columna].getContenido().compareTo(valorActual) > 0) {
                celdas[j + 1][columna].setContenido(celdas[j][columna].getContenido());
                j--;
            }
            celdas[j + 1][columna].setContenido(valorActual);
        }
    }
}
