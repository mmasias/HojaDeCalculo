import java.util.Scanner;

public class VisiCalcUI {
    private HojaDeCalculo hoja;
    private Viewport viewport;

    public VisiCalcUI(int filas, int columnas, int filasViewport, int columnasViewport) {
        this.hoja = new HojaDeCalculo(filas, columnas);
        this.viewport = new Viewport(hoja, filasViewport, columnasViewport);
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarViewport();
            System.out.println("Comandos: [w/a/s/d] mover cursor, [e] editar celda, [q] salir");
            System.out.print("Ingrese comando: ");
            String comando = scanner.nextLine();

            switch (comando) {
                case "w":
                    viewport.moverCursor(-1, 0);
                    break;
                case "s":
                    viewport.moverCursor(1, 0);
                    break;
                case "a":
                    viewport.moverCursor(0, -1);
                    break;
                case "d":
                    viewport.moverCursor(0, 1);
                    break;
                case "e":
                    editarCelda(scanner);
                    break;
                case "q":
                    salir = true;
                    break;
                default:
                    System.out.println("Comando no reconocido.");
            }
        }
        scanner.close();
    }

    private void mostrarViewport() {
        System.out.println("\n--- Hoja de Cálculo ---");
        for (int i = 0; i < viewport.getFilasViewport(); i++) {
            for (int j = 0; j < viewport.getColumnasViewport(); j++) {
                Celda celda = viewport.getCelda(i, j);
                if (i == viewport.getFilaCursorGlobal() - viewport.getFilaInicio() &&
                    j == viewport.getColumnaCursorGlobal() - viewport.getColumnaInicio()) {
                    System.out.print("[" + (celda != null ? celda.getContenido() : " ") + "]");
                } else {
                    System.out.print(" " + (celda != null ? celda.getContenido() : " ") + " ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    private void editarCelda(Scanner scanner) {
        Celda celda = viewport.getCeldaCursor();
        System.out.print("Ingrese nuevo contenido para la celda: ");
        String nuevoContenido = scanner.nextLine();
        celda.setContenido(nuevoContenido);
    }

    public static void main(String[] args) {
        VisiCalcUI aplicacion = new VisiCalcUI(10, 10, 5, 5);
        aplicacion.ejecutar();
    }
}
