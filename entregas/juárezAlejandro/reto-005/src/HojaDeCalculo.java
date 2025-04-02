public class HojaDeCalculo {
    private int filas;
    private int columnas;
    private Celda[][] celdas;

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

    public int getNumeroDeFilas() {
        return this.filas;
    }

    public int getNumeroDeColumnas() {
        return this.columnas;
    }

    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }
}