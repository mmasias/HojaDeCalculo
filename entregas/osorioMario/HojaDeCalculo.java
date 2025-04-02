public class HojaDeCalculo {
    private Celda[][] celdas;
    private int filas;
    private int columnas;

    public HojaDeCalculo(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Celda[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    public Celda getCelda(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return celdas[fila][columna];
        }
        return null;
    }

    public int getNumeroDeFilas() {
        return filas;
    }

    public int getNumeroDeColumnas() {
        return columnas;
    }
}
