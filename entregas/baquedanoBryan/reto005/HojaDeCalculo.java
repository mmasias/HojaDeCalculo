package v04.HojaDeCalculo;


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
    
    
    public void ordenarRangoHorizontal(int fila, int columnaInicio, int columnaFin) {
        if (fila < 0 || fila >= FILAS || 
            columnaInicio < 0 || columnaInicio >= COLUMNAS ||
            columnaFin < columnaInicio || columnaFin >= COLUMNAS) {
            System.out.println("Rango inválido");
            return;
        }
        
        int longitud = columnaFin - columnaInicio + 1;
        Double[] valores = new Double[longitud];
        
        for (int j = 0; j < longitud; j++) {
            String contenido = celdas[fila][columnaInicio + j].getContenido().trim();
            try {
                valores[j] = Double.parseDouble(contenido);
            } catch (NumberFormatException e) {
                valores[j] = 0.0;
            }
        }
        
        for (int i = 0; i < longitud - 1; i++) {
            for (int j = 0; j < longitud - i - 1; j++) {
                if (valores[j] > valores[j + 1]) {
                    Double temp = valores[j];
                    valores[j] = valores[j + 1];
                    valores[j + 1] = temp;
                }
            }
        }

        for (int j = 0; j < longitud; j++) {
            celdas[fila][columnaInicio + j].setContenido(String.valueOf(valores[j]));
        }
    }
    

}
