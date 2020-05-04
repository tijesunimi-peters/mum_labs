
public class MergeSort {
    public static String firstPrint;

    public static void merge(int[] list1, int[] list2, int[] finalList) {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < list1.length; i++) {
            int pos = BinarySearch.seqGetPos(list2, list1[i]) + i;

            // accounting for duplicates
            if (finalList[pos] > 0 && pos + 1 < list1.length) {
                pos += 1;
            }

            if ((i + 1) % 500000 == 0) {

                Main.printOutput(name, ".".repeat((i + 1) / 500000), firstPrint);
            }

            finalList[pos] = list1[i];
        }
    }

    public static String getFirstPrinter() {
        return firstPrint;
    }

    public static synchronized void setPrinter() {
        firstPrint = Thread.currentThread().getName();
    }

    public static synchronized void setPrinter(Thread thread) {
        firstPrint = thread.getName();
    }
}