package entregas.roseteEirik.v03;

public class BubbleSort {

    public static void ordenarColumna(HojaDeCalculo hoja, int columna) {
        int totalCeldas = hoja.getNumeroDeFilas();
        boolean cambiado;

        do {
            cambiado = false;
            for (int celda = 0; celda < totalCeldas - 1; celda++) {
                String valor1 = hoja.getCelda(celda, columna).getContenido();
                String valor2 = hoja.getCelda(celda + 1, columna).getContenido();

                if (valor1 == "" || valor2 == "") {
                    continue;
                }

                int numeroMenor = Integer.parseInt(valor1);
                int numeroMayor = Integer.parseInt(valor2);

                if (numeroMenor < numeroMayor) { // Orden descendente
                    hoja.getCelda(celda, columna).setContenido(String.valueOf(numeroMayor));
                    hoja.getCelda(celda + 1, columna).setContenido(String.valueOf(numeroMenor));
                    cambiado = true;
                }
            }
            totalCeldas--;
        } while (cambiado);
    }
}
