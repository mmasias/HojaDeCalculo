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

    public void ordenarColumnaEnRango(Rango rango, boolean ascendente) {
        int columna = rango.getColumna();
        int inicio = rango.getInicio();
        int fin = rango.getFin();

        Celda[] columnaCeldas = new Celda[fin - inicio + 1];
        for (int i = 0; i < columnaCeldas.length; i++) {
            Celda original = celdas[inicio + i][columna];
            columnaCeldas[i] = new Celda();
            columnaCeldas[i].setContenido(original.getContenido());
        }

        for (int i = 0; i < columnaCeldas.length - 1; i++) {
            for (int j = 0; j < columnaCeldas.length - i - 1; j++) {
                try {
                    int valor1 = Integer.parseInt(columnaCeldas[j].getContenido());
                    int valor2 = Integer.parseInt(columnaCeldas[j + 1].getContenido());

                    if (ascendente ? valor1 > valor2 : valor1 < valor2) {
                        Celda temp = new Celda();
                        temp.setContenido(columnaCeldas[j].getContenido());
                        columnaCeldas[j].setContenido(columnaCeldas[j + 1].getContenido());
                        columnaCeldas[j + 1].setContenido(temp.getContenido());
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Valor no numérico en fila " + (inicio + j));
                }
            }
        }

        for (int i = 0; i < columnaCeldas.length; i++) {
            celdas[inicio + i][columna] = columnaCeldas[i];
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
