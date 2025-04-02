package v04;
import java.util.Scanner;
import librerias.Consola;

public class VisiCalcUI {
    private Viewport viewport;
    private Scanner scanner;
    private HojaDeCalculo hoja;

    public VisiCalcUI(HojaDeCalculo hoja) {
        this.hoja = hoja;
        this.viewport = new Viewport(hoja, 15, 10);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean estaOperativo = true;

        while (estaOperativo) {
            mostrarHoja();
            System.out.print("COMANDO > ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("q")) {
                estaOperativo = false;
            } else if (entrada.startsWith("o")) {
                procesarOrdenamiento(entrada);
            }
        }
    }

    private void procesarOrdenamiento(String entrada) {
        String[] partes = entrada.split(" ");
        if (partes.length == 3) {
            try {
                int columna = partes[1].toUpperCase().charAt(0) - 'A';
                boolean ascendente = partes[2].equalsIgnoreCase("asc");
                hoja.ordenarColumna(columna, ascendente);
            } catch (Exception e) {
                System.out.println("Error en el formato. Usa: o [columna] [asc/desc]");
            }
        } else {
            System.out.println("Formato incorrecto. Usa: o [columna] [asc/desc]");
        }
    }
}
