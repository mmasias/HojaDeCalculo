package main.java.v05;

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

    public void ordenarCeldas(int filaInicio, int filaFin, int columnaActual, char ascendenteDescendente) {
        if (filaInicio < 0 || filaFin >= FILAS || filaInicio > filaFin) {
            return;
        }
        boolean ordenAscendente = (ascendenteDescendente == 'A');
        for (int i = filaInicio; i < filaFin - 1; i++) {
            for (int j = filaInicio; j < filaFin - 1; j++) {
                int valorActual = Integer.parseInt(celdas[j][columnaActual].getContenido());
                int valorSiguiente = Integer.parseInt(celdas[j + 1][columnaActual].getContenido());

                if (ordenAscendente) {
                    if (valorActual > valorSiguiente) {
                        for (int k = 0; k < COLUMNAS; k++) {
                            String temp = celdas[j][k].getContenido();
                            celdas[j][k].setContenido(celdas[j + 1][k].getContenido());
                            celdas[j + 1][k].setContenido(temp);
                        }
                    }
                } else {
                    if (valorActual < valorSiguiente) {
                        for (int k = 0; k < COLUMNAS; k++) {
                            String temp = celdas[j][k].getContenido();
                            celdas[j][k].setContenido(celdas[j + 1][k].getContenido());
                            celdas[j + 1][k].setContenido(temp);
                        }
                    }
                }
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
}
