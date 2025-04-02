public class VisiCalc {

    private static final int FILAS = 30;
    private static final int COLUMNAS = 26;

    public static void main(String[] args) {

        HojaDeCalculo miHoja = new HojaDeCalculo(FILAS, COLUMNAS);
        VisiCalcUI ui = new VisiCalcUI(miHoja);
        ui.iniciar();
    }
}