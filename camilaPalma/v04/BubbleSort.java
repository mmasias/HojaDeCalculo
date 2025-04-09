package v04;

public class BubbleSort {
    public static void sort(String[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (shouldSwap(array[j], array[j + 1])) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    private static boolean shouldSwap(String a, String b) {
        if (a.equals("") && !b.equals("")) {
            return true;
        }
        if (!a.equals("") && b.equals("")) {
            return false;
        }
        return a.compareTo(b) > 0;
    }
}
