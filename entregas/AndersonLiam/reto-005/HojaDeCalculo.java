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

    public void ordenarColumnas() {
        for (int j = 0; j < COLUMNAS; j++) {
            for (int i = 0; i < FILAS - 1; i++) {
                for (int k = 0; k < FILAS - i - 1; k++) {
                    String valor1 = celdas[k][j].getContenido();
                    String valor2 = celdas[k + 1][j].getContenido();
                    if (valor1.compareTo(valor2) > 0) {
                        String temp = valor1;
                        celdas[k][j].setContenido(valor2);
                        celdas[k + 1][j].setContenido(temp);
                    }
                }
            }
        }
    }
}