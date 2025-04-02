package v04;

public class Viewport {
    private HojaDeCalculo hoja;
    private int filaInicio;
    private int columnaInicio;
    private final int FILAS_VIEWPORT;
    private final int COLUMNAS_VIEWPORT;
    private int filaCursor;
    private int columnaCursor;

    public Viewport(HojaDeCalculo hoja, int filasViewport, int columnasViewport) {
        this.hoja = hoja;
        this.filaInicio = 0;
        this.columnaInicio = 0;
        this.FILAS_VIEWPORT = filasViewport;
        this.COLUMNAS_VIEWPORT = columnasViewport;
        this.filaCursor = 0;
        this.columnaCursor = 0;
    }

    public Celda getCelda(int fila, int columna) {
        return hoja.getCelda(filaInicio + fila, columnaInicio + columna);
    }

    public void ordenar(){
        int[] pos1=new int[2];
        int[] pos2=new int[2];

        for(int i=0;i<getColumnasViewport();i++){
            for(int j=0;j<getFilasViewport();j++){

                for (int k = 0; k < getFilasViewport() - i - 1; k++) {
                    String valor1 = getCelda(k,j).getContenido();
                    String valor2 = getCelda(k + 1,j).getContenido();
                    if (valor1.compareTo(valor2) > 0) {
                        String temp = valor1;
                        getCelda(k,j).setContenido(valor2);
                        getCelda(k + 1,j).setContenido(temp);
                    }
                }

            }


        }

    }
    public void desplazarVerticalmente(int cantidad) {
        int nuevaFilaInicio = filaInicio + cantidad;

        if (nuevaFilaInicio >= 0 && nuevaFilaInicio + FILAS_VIEWPORT <= hoja.getNumeroDeFilas()) {
            filaInicio = nuevaFilaInicio;
        }
    }

    public void desplazarHorizontalmente(int cantidad) {
        int nuevaColumnaInicio = columnaInicio + cantidad;

        if (nuevaColumnaInicio >= 0 && nuevaColumnaInicio + COLUMNAS_VIEWPORT <= hoja.getNumeroDeColumnas()) {
            columnaInicio = nuevaColumnaInicio;
        }
    }

    public void moverCursor(int desplazamientoFila, int desplazamientoColumna) {
        filaCursor += desplazamientoFila;
        columnaCursor += desplazamientoColumna;
    
        if (filaCursor < 0) {
            desplazarVerticalmente(-1);
            filaCursor = 0;
        } else if (filaCursor >= FILAS_VIEWPORT) {
            desplazarVerticalmente(1);
            filaCursor = FILAS_VIEWPORT - 1;
        }
    
        if (columnaCursor < 0) {
            desplazarHorizontalmente(-1);
            columnaCursor = 0;
        } else if (columnaCursor >= COLUMNAS_VIEWPORT) {
            desplazarHorizontalmente(1);
            columnaCursor = COLUMNAS_VIEWPORT - 1;
        }
    }
    
    public Celda getCeldaCursor() {
        return hoja.getCelda(filaInicio + filaCursor, columnaInicio + columnaCursor);
    }

    public int getFilaInicio() {
        return filaInicio;
    }

    public int getColumnaInicio() {
        return columnaInicio;
    }

    public int getFilasViewport() {
        return FILAS_VIEWPORT;
    }

    public int getColumnasViewport() {
        return COLUMNAS_VIEWPORT;
    }

    public int getFilaCursorGlobal() {
        return filaInicio + filaCursor;
    }
    
    public int getColumnaCursorGlobal() {
        return columnaInicio + columnaCursor;
    }

}
