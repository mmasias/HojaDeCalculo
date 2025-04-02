package librerias;

public class Consola {

  public static void limpiarPantalla() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void posicionarse(int fila, int columna) {
    char codigoDeEscape = 0x1B;
    System.out.print(String.format("%c[%d;%df", codigoDeEscape, fila, columna));
  }
}
