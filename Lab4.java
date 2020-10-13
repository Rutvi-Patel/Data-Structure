package ThreeTwoEight;
//Rutvi Patel
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/*
5) The time complexity of getting Median is O(n)
6) The time complexity of saving the median in a new array is O(n)
7) The time complexity of this part is O(n) because we are calling quick_Select once.
8) The time complexity in this part is O(k) because we are just adding median k times to the first k values of difference array.
10)  The total complexity of the code is O(n).


 */

public class Lab4 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter a number n:");
        int size = scnr.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter a K value:");
        int k = scnr.nextInt();

        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(201) - 100;
        }
        System.out.println(Arrays.toString(arr));


        // getting the median
        int median;
        if (arr.length % 2 == 0) {
            int m1 = quick_select(arr, 0, arr.length - 1, (arr.length - 1) / 2);
            int m2 = quick_select(arr, 0, arr.length - 1, (arr.length) / 2);
            median = (m1 + m2) / 2;

        } else {
            median = quick_select(arr, 0, arr.length - 1, (arr.length - 1) / 2);
        }
        System.out.println("The median: " + median);


        //saving the difference in another arr time Complexity is O(n)
        int[] diff = new int[size];
        int ans;
        for (int i = 0; i < size; i++) {
            ans = arr[i] - median;
            diff[i] = ans;
        }
        System.out.println("My array after Quick Select: " + Arrays.toString(arr));
        System.out.println("Difference array: " + Arrays.toString(diff));

        int [] ind = new int[k];
        //Getting the kth least differences tie complexity O(n)
        quick_selectabs(diff, 0, arr.length - 1,k-1 );
        System.out.println(Arrays.toString(diff));
        //getting final ans complexity O(k)
        for (int i = 0; i<k; i++){
            if (arr.length%2 == 0){
                ind[i] = diff[i] + median;
            }else {
                ind[i] = diff[i+1]+median;
            }
        }
        System.out.println("Final answer: " + Arrays.toString(ind));
    }


    public static int quick_selectabs(int[] arr, int low, int high, int k) {

        if (low == high) {
            return arr[low];
        }
        // Select a pivotIndex between left and right

        int pivotIndex = partitionabs(arr, low, high);
        if (k == pivotIndex) {
            return arr[pivotIndex];

        } else if (k < pivotIndex) {
            high = pivotIndex - 1;
        } else {
            low = pivotIndex + 1;
        }
        return quick_selectabs(arr, low, high, k);
    }

    public static int partitionabs(int arr[], int low, int high) {

        pivot(arr, low, low + (high - low) / 2, high);
        int pivot = Math.abs(arr[high]);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (Math.abs(arr[j]) <= Math.abs(pivot)) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void pivot(int[] arr, int x, int y, int z) {
        int a = Math.abs(arr[x]);
        int b = Math.abs(arr[y]);
        int c = Math.abs(arr[z]);
        int med;
        if ((a < b && a > c) || (a > b && a < c)) {
            med = x;
        } else if ((c < b && c > a) || (c > b && c < a)) {
            med = z;
        } else {
            med = y;
        }
        int temp = arr[med];
        arr[med] = arr[z];
        arr[z] = temp;
    }


    public static int partition(int arr[], int low, int high) {

        pivot(arr, low, low + (high - low) / 2, high);
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    public static int quick_select(int[] arr, int low, int high, int k) {

        if (low == high) {
            return arr[low];
        }
        // Select a pivotIndex between left and right

        int pivotIndex = partition(arr, low, high);
        if (k == pivotIndex) {
            return arr[k];

        } else if (k < pivotIndex) {
            high = pivotIndex - 1;
        } else {
            low = pivotIndex + 1;
        }
        return quick_select(arr, low, high, k);
    }
}