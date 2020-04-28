// import java.util.Arrays;

public class BinarySearch {

    public static int getInsertPosition(int[] arr, int num, Integer skipped) {
        if(skipped == null) skipped = 0;
        int mid = (int) Math.floor((arr.length) / 2);

        if(arr.length == 0) return skipped;
        
        if(arr[mid] > num) {
            int[] newArr = new int[mid];
            System.arraycopy(arr, 0, newArr, 0, mid);
            // System.out.printf("Left:----Arr %s; New Arr: %s; Skipped: %d; Mid: %d\n",Arrays.toString(arr), Arrays.toString(newArr), skipped, mid);
            return getInsertPosition(newArr, num, skipped);
        } else if(arr[mid] < num) {
            int startIndex = mid + 1;
            int[] newRArr = new int[arr.length - startIndex];
            System.arraycopy(arr, startIndex, newRArr, 0, arr.length - startIndex);
            // System.out.printf("Right:----Arr %s; New Arr: %s; Skipped: %d; Mid: %d\n",Arrays.toString(arr), Arrays.toString(newRArr), skipped, mid);
            return getInsertPosition(newRArr, num, startIndex + skipped);
        }
        return skipped;
    }

    public static void main (String[] args) {
        int[] trial = {1,2,3,4,5,7,8,10,11,18,60,61,66,70};

        System.out.printf("12: %d\n", BinarySearch.getInsertPosition(trial, 12, 0));
        System.out.printf("9: %d\n", BinarySearch.getInsertPosition(trial, 9, 0));
        System.out.printf("59: %d\n", BinarySearch.getInsertPosition(trial, 59, 0));
        System.out.printf("62: %d\n", BinarySearch.getInsertPosition(trial, 62, 0));
        System.out.printf("1: %d\n", BinarySearch.getInsertPosition(trial, 1, 0));
        System.out.printf("71: %d\n", BinarySearch.getInsertPosition(trial, 71, 0));
        // int[] arr = new int[1];
        // System.arraycopy(trial, trial.length - 1, arr, 0, 1);
        // System.out.println(Arrays.toString(arr));
    }
}