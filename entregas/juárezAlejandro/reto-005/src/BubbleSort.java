public class BubbleSort {

    public static void ordenarLista(int[] lista) {
        int longitud = lista.length;
        boolean intercambio;

        for (int i = 0; i < longitud - 1; i++) {
            intercambio = false;

            for (int j = 0; j < longitud - i - 1; j++) {
                if (lista[j] > lista[j + 1]) {
                    int auxiliar = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = auxiliar;
                    intercambio = true;
                }
            }

            if (!intercambio) {
                break;
            }
        }
    }
}