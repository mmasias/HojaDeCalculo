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
        int filaReal = filaInicio + fila;
        int columnaReal = columnaInicio + columna;
        return hoja.getCelda(filaReal, columnaReal);
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
        int nuevaFilaCursor = filaCursor + desplazamientoFila;
        int nuevaColumnaCursor = columnaCursor + desplazamientoColumna;

        if (nuevaFilaCursor < 0) {
            desplazarVerticalmente(-1);
            filaCursor = 0;
        } else if (nuevaFilaCursor >= FILAS_VIEWPORT) {
            desplazarVerticalmente(1);
            filaCursor = FILAS_VIEWPORT - 1;
        } else {
            filaCursor = nuevaFilaCursor;
        }

        if (nuevaColumnaCursor < 0) {
            desplazarHorizontalmente(-1);
            columnaCursor = 0;
        } else if (nuevaColumnaCursor >= COLUMNAS_VIEWPORT) {
            desplazarHorizontalmente(1);
            columnaCursor = COLUMNAS_VIEWPORT - 1;
        } else {
            columnaCursor = nuevaColumnaCursor;
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
