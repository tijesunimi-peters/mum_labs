public class ParallelMergeSort implements Runnable {
    private int[] arr1;
    private int[] arr2;
    private int[] finalList;

    ParallelMergeSort(int[] arr1, int[] arr2, int[] finalList) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.finalList = finalList;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        MergeSort.merge(arr1, arr2, finalList);
    }
    
}