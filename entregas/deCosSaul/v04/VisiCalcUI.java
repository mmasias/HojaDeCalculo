package v04;

import java.util.Arrays;
import java.util.Scanner;
import librerias.Consola;

public class VisiCalcUI {
  private Viewport viewport;
  private Scanner scanner;

  public VisiCalcUI(HojaDeCalculo hoja) {
    this.viewport = new Viewport(hoja, 15, 10);
    this.scanner = new Scanner(System.in);
  }

  public void iniciar() {
    boolean estaOperativo = true;

    while (estaOperativo) {
      mostrarHoja();
      char comando = scanner.next().toUpperCase().charAt(0);
      estaOperativo = procesarComando(comando);
    }

    System.out.println("Saliendo del programa.");
    scanner.close();
  }

  private void mostrarHoja() {
    Consola.limpiarPantalla();
    mostrarOpciones();
    System.out.print("      ");
    for (int j = 0; j < viewport.getColumnasViewport(); j++) {
      char letraColumna = (char) ('A' + viewport.getColumnaInicio() + j);
      System.out.printf("%-8s", letraColumna);
    }
    System.out.println();

    for (int i = 0; i < viewport.getFilasViewport(); i++) {
      System.out.printf("%-5d|", viewport.getFilaInicio() + i + 1);

      for (int j = 0; j < viewport.getColumnasViewport(); j++) {
        String celda = viewport.getCelda(i, j).getContenido();
        celda = celda.length() > 5 ? celda.substring(0, 5) : String.format("%-5s", celda);

        if (i == viewport.getFilaCursorGlobal() - viewport.getFilaInicio()
            && j == viewport.getColumnaCursorGlobal() - viewport.getColumnaInicio()) {
          System.out.print("[" + celda + "]");
        } else {
          System.out.print(" " + celda + " ");
        }
        System.out.print("|");
      }
      System.out.println();
    }
    Consola.posicionarse(2, 10);
  }

  private void mostrarOpciones() {

    int filaActual = viewport.getFilaCursorGlobal();
    int columnaActual = viewport.getColumnaCursorGlobal();
    char letraColumna = (char) ('A' + columnaActual);

    System.out.print("[" + letraColumna + (filaActual + 1) + "] ");
    System.out.println("OPCIONES: desplazarse: wasd | editar: e | ordenar:o | salir: q");
    System.out.println("COMANDO >");

  }

  private boolean procesarComando(char comando) {
    switch (comando) {
      case 'W':
        viewport.moverCursor(-1, 0);
        break;
      case 'A':
        viewport.moverCursor(0, -1);
        break;
      case 'S':
        viewport.moverCursor(1, 0);
        break;
      case 'D':
        viewport.moverCursor(0, 1);
        break;
      case 'E':
        editarCeldaActual();
        break;
      case 'O':
        bubbleSort();
        break;
      case 'Q':
        return false;
      default:
        System.out.println("Comando inválido. Intente nuevamente.");
    }
    return true;
  }

  private void editarCeldaActual() {
    Celda celdaActual = viewport.getCeldaCursor();
    Consola.posicionarse(2, 1);
    System.out.print("Ingrese el texto:");
    String texto = scanner.next();
    celdaActual.setContenido(texto);
  }

  public void bubbleSort() {
    int columna = viewport.getColumnaCursorGlobal();

    mostrarHoja();
    System.out.print("Introduce la primera fila desde la que se quiere ordenar (índice basado en 1): ");
    int filaInicio = scanner.nextInt() - 1;

    mostrarHoja();
    System.out.print("Introduce la última fila hasta la que se quiere ordenar (índice basado en 1): ");
    int filaFin = scanner.nextInt();

    mostrarHoja();
    System.out.print("¿Ordenar de menor a mayor (1) o de mayor a menor (2)?: ");
    int ordenTipo = scanner.nextInt();

    boolean ascendente = ordenTipo == 1;

    String[] valores = new String[filaFin - filaInicio];
    int[] numeros = new int[filaFin - filaInicio];

    for (int i = 0; i < valores.length; i++) {
      valores[i] = viewport.getCelda(filaInicio + i - viewport.getFilaInicio(), columna - viewport.getColumnaInicio())
          .getContenido();
      numeros[i] = Integer.parseInt(valores[i]);
    }

    for (int i = 0; i < numeros.length - 1; i++) {
      for (int j = 0; j < numeros.length - 1 - i; j++) {
        boolean debeIntercambiar = ascendente ? (numeros[j] > numeros[j + 1]) : (numeros[j] < numeros[j + 1]);

        if (debeIntercambiar) {
          int tempNum = numeros[j];
          numeros[j] = numeros[j + 1];
          numeros[j + 1] = tempNum;

          String tempVal = valores[j];
          valores[j] = valores[j + 1];
          valores[j + 1] = tempVal;
        }
      }
    }

    for (int i = 0; i < valores.length; i++) {
      viewport.getCelda(filaInicio + i - viewport.getFilaInicio(),
          columna - viewport.getColumnaInicio()).setContenido(valores[i]);
    }
  }
}
