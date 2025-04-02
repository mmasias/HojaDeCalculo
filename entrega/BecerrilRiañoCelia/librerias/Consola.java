

package librerias;

public class Consola {
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void posicionarse(int fila, int columna) {
        System.out.printf("\033[%d;%dH", fila, columna);
    }
}

