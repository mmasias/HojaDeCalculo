import java.util.Stack;

public class HojaCalculo {
    private Celda[][] datos;
    private Stack<String[][]> historial;

    public HojaCalculo(int filas, int columnas) {
        datos = new Celda[filas][columnas];
        historial = new Stack<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = new Celda("");
            }
        }
    }

    public void establecerValorCelda(int fila, int columna, String valor) {
        guardarEstado();
        datos[fila][columna].establecerValor(valor);
    }

    public String obtenerValorCelda(int fila, int columna) {
        return datos[fila][columna].obtenerValor();
    }

    public void ordenarColumna(int indiceColumna) {
        guardarEstado();
        for (int i = 0; i < datos.length - 1; i++) {
            for (int j = i + 1; j < datos.length; j++) {
                String valor1 = datos[i][indiceColumna].obtenerValor();
                String valor2 = datos[j][indiceColumna].obtenerValor();

                if (!valor1.isEmpty() && !valor2.isEmpty() && valor1.compareTo(valor2) > 0) {
                    String temp = valor1;
                    datos[i][indiceColumna].establecerValor(valor2);
                    datos[j][indiceColumna].establecerValor(temp);
                }
            }
        }
    }

    public void imprimirHoja(int filaCursor, int columnaCursor) {
        System.out.println("[D3] OPCIONES: desplazarse: wasd | editar: e | ordenar columna: o | salir: q");
        System.out.println("COMANDO >");

        System.out.print("      ");
        for (char col = 'A'; col < 'A' + datos[0].length; col++) {
            System.out.printf("%-8s", col);
        }
        System.out.println();
        for (int i = 0; i < datos.length; i++) {
            System.out.printf("%-4d |", i + 3);
            for (int j = 0; j < datos[i].length; j++) {
                if (i == filaCursor && j == columnaCursor) {
                    System.out.printf("[%-6s]", datos[i][j].obtenerValor());
                } else {
                    System.out.printf(" %-7s|", datos[i][j].obtenerValor());
                }
            }
            System.out.println();
        }
    }

    private void guardarEstado() {
        String[][] copia = new String[datos.length][datos[0].length];
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {
                copia[i][j] = datos[i][j].obtenerValor();
            }
        }
        historial.push(copia);
    }

    public void deshacer() {
        if (!historial.isEmpty()) {
            String[][] estadoAnterior = historial.pop();
            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < datos[i].length; j++) {
                    datos[i][j].establecerValor(estadoAnterior[i][j]);
                }
            }
        } else {
            System.out.println("No hay acciones para deshacer.");
        }
    }
}