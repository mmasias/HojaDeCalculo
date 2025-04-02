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

    public void ordenarValoresNumericos(boolean ascendente) {
        int[] valores = new int[FILAS * COLUMNAS];
        int totalValores = 0;

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                String contenido = celdas[fila][columna].getContenido();
                if (esNumero(contenido)) {
                    valores[totalValores++] = Integer.parseInt(contenido);
                }
            }
        }
        
        boolean intercambiado;
        int pasada = 0;
        do {
            intercambiado = false;
            for (int i = 0; i < totalValores - 1 - pasada; i++) {
                if ((ascendente && valores[i] > valores[i + 1]) ||
                    (!ascendente && valores[i] < valores[i + 1])) {
                    int temp = valores[i];
                    valores[i] = valores[i + 1];
                    valores[i + 1] = temp;
                    intercambiado = true;
                }
            }
            pasada++;
        } while (intercambiado);
        
        totalValores = 0;
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (esNumero(celdas[fila][columna].getContenido())) {
                    celdas[fila][columna].setContenido(String.valueOf(valores[totalValores++]));
                }
            }
        }
    }
    
    private boolean esNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}