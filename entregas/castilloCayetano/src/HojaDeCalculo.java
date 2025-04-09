package src;

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
            Celda clave = celdas[i][columna];
            String contenidoClave = clave.getContenido();
            int j = i - 1;

            while (j >= 0 && convertirAEntero(celdas[j][columna].getContenido()) > convertirAEntero(contenidoClave)) {
                celdas[j + 1][columna].setContenido(celdas[j][columna].getContenido());
                j--;
            }
            celdas[j + 1][columna].setContenido(contenidoClave);
        }
    }

    private int convertirAEntero(String valor) {
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
