import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HojaCalculo hoja = new HojaCalculo(15, 10);
        hoja.establecerValorCelda(3, 3, "89");
        hoja.establecerValorCelda(4, 3, "37");
        hoja.establecerValorCelda(5, 3, "25");
        hoja.establecerValorCelda(6, 3, "13");
        hoja.establecerValorCelda(7, 3, "5");
        hoja.establecerValorCelda(8, 3, "4");
        hoja.establecerValorCelda(9, 3, "2");

        int[] cursor = { 0, 3 };

        Map<String, Runnable> comandos = new HashMap<>();
        comandos.put("w", () -> {
            if (cursor[0] > 0)
                cursor[0]--;
        });
        comandos.put("s", () -> {
            if (cursor[0] < 14)
                cursor[0]++;
        });
        comandos.put("a", () -> {
            if (cursor[1] > 0)
                cursor[1]--;
        });
        comandos.put("d", () -> {
            if (cursor[1] < 9)
                cursor[1]++;
        });
        comandos.put("e", () -> {
            System.out.print("Nuevo valor: ");
            String valor = scanner.nextLine();
            hoja.establecerValorCelda(cursor[0], cursor[1], valor);
        });
        comandos.put("o", () -> {
            System.out.print("Columna a ordenar (0-9): ");
            int indiceColumna = scanner.nextInt();
            scanner.nextLine();
            hoja.ordenarColumna(indiceColumna);
        });
        comandos.put("q", () -> System.exit(0));

        boolean ejecutando = true;
        while (ejecutando) {
            hoja.imprimirHoja(cursor[0], cursor[1]);
            System.out.print("Comando > ");
            String comando = scanner.nextLine();
            Runnable accion = comandos.get(comando);
            if (accion != null) {
                accion.run();
            } else {
                System.out.println("Comando no reconocido.");
            }
        }

        scanner.close();
    }
}