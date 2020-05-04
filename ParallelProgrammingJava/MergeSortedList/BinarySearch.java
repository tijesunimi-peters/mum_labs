// import java.util.Arrays;

public class BinarySearch {

    public static int getInsertPosition(int[] arr, int num, Integer skipped) {
        // System.out.printf("\n\tEntry arr: %s\n\n", Arrays.toString(arr));
        if(skipped == null) skipped = 0;
        int mid = (int) Math.floor((arr.length) / 2);

        if(arr.length == 0) return skipped;
        
        if(arr[mid] > num) {
            int[] newArr = new int[mid];
            System.arraycopy(arr, 0, newArr, 0, mid);
            // System.out.printf("\t%s; L Searching: %d; Skipped: %d; Mid: %d;MidVal: %d\n", Arrays.toString(newArr), num, skipped, mid, arr[mid]);
            return getInsertPosition(newArr, num, skipped);
        } else if(arr[mid] < num) {
            int startIndex = mid + 1;
            int[] newRArr = new int[arr.length - startIndex];
            System.arraycopy(arr, startIndex, newRArr, 0, arr.length - startIndex);
            // System.out.printf("\t%s; R Searching: %d\n", Arrays.toString(newRArr), num);
            return getInsertPosition(newRArr, num, startIndex + skipped);
        } else if(arr[mid] == num) {
            return skipped += mid;
        }

        return skipped;
    }

    public static int seqGetPos(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;
        int skipped = left;
        while(left <= right) {
            int mid = (int) Math.floor((left + right)/2);
            if(arr[mid] < num) {
                skipped = mid + 1;
                left = mid + 1;
            } else if(arr[mid] > num) {
                right = mid - 1;
            } else {
                skipped = mid;
                break;
            }
        }

        return skipped;
    }

    // public static void main (String[] args) {
    //     int[] trial = {1,2,3,4,5,7,8,10,11,18,60,61,66,70};

    //     System.out.printf("12: %d\n", BinarySearch.seqGetPos(trial, 12));
    //     System.out.printf("9: %d\n", BinarySearch.seqGetPos(trial, 9));
    //     System.out.printf("59: %d\n", BinarySearch.seqGetPos(trial, 59));
    //     System.out.printf("62: %d\n", BinarySearch.seqGetPos(trial, 62));
    //     System.out.printf("1: %d\n", BinarySearch.seqGetPos(trial, 1));
    //     System.out.printf("71: %d\n", BinarySearch.seqGetPos(trial, 71));
    //     // int[] arr = new int[1];
    //     // System.arraycopy(trial, trial.length - 1, arr, 0, 1);
    //     // System.out.println(Arrays.toString(arr));
    // }
}