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
        for (int i = 0; i < FILAS - 1; i++) {
            for (int j = i + 1; j < FILAS; j++) {
                String contenidoI = celdas[i][columna].getContenido();
                String contenidoJ = celdas[j][columna].getContenido();
                
                try {
                    double numI = Double.parseDouble(contenidoI);
                    double numJ = Double.parseDouble(contenidoJ);
                    if (numI > numJ) {
                        intercambiarCeldas(i, columna, j, columna);
                    }
                } catch (NumberFormatException e) {
                    if (contenidoI.compareTo(contenidoJ) > 0) {
                        intercambiarCeldas(i, columna, j, columna);
                    }
                }
            }
        }
    }

    private void intercambiarCeldas(int fila1, int columna1, int fila2, int columna2) {
        Celda temp = celdas[fila1][columna1];
        celdas[fila1][columna1] = celdas[fila2][columna2];
        celdas[fila2][columna2] = temp;
    }
}
