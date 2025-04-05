package v04;

public class Rango {
  private final int inicio;
  private final int fin;
  private final int columna;

  public Rango(int columna, int inicio, int fin) {
    if (inicio > fin) {
      int temp = inicio;
      inicio = fin;
      fin = temp;
    }
    this.inicio = inicio;
    this.fin = fin;
    this.columna = columna;
  }

  public int getInicio() {
    return inicio;
  }

  public int getFin() {
    return fin;
  }

  public int getColumna() {
    return columna;
  }

  @Override
  public String toString() {
    char letraColumna = (char) ('A' + columna);
    return String.format("%c%d:%c%d", letraColumna, inicio + 1, letraColumna, fin + 1);
  }
}