package ThreeTwoEight;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Part A\nEnter N value of an arry: ");
        int n = scnr.nextInt();
        int[] arr = new int[n];
        for (int i =0; i <n ; i++){
            int num = rand.nextInt(201)-100;
            arr[i] = num;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("Enter the k value");
        int k = scnr.nextInt();
        int kleast;
        kleast = quick_select(arr,0,arr.length-1,k-1);
        System.out.println(Arrays.toString(arr));
        System.out.println("The kth least Element is: " + kleast + "\n\nPartB");

//Part B
        k = arr.length - k;
        quick_select(arr, 0, arr.length - 1, k );
        System.out.println(Arrays.toString(arr));
        String x = "";
        while (k <arr.length) {
            x = x + arr[k] + " ";
            k = k+1;
        }
        System.out.println("Kth max element: "+ x);
    }


    public static int quick_select(int[]arr, int low, int high, int k) {

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
        return quick_select(arr,low, high, k);
    }


    //Partition fixed

    public static int partition(int arr[], int low, int high) {

        pivot(arr,low,low+ (high-low)/2, high);
        int pivot = arr[high];
//        System.out.println("The pivot value" + pivot);
        int si = low;
        int ei = high-1;
        while (si<=ei){
            if(arr[si]>pivot && arr[ei] <pivot){
                int temp = arr[si];
                arr[si] = arr[ei];
                arr[ei] = temp;
                si++; ei--;
            }else if (arr[si]<pivot){
                si++;
            }else {
                ei--;
            }
        }
//        System.out.println(Arrays.toString(arr));
        // swap pivot in the middle and arr[high] (or pivot)
        int temp = arr[si];
        arr[si] = arr[high];
        arr[high] = temp;
        return si;
    }




    public static void pivot(int []arr, int x, int y, int z){
        int a = arr[x];
        int b = arr[y];
        int c = arr[z];
        int med;
        if ((a<b && a>c) ||(a>b && a<c) ){
            med = x;
        }else if((c<b && c>a) ||(c>b && c<a)){
            med = z;
        }else{
            med = y;
        }
        int temp = arr[med];
        arr[med] = arr[z];
        arr[z] = temp;
//        System.out.println("Array after pivot" + Arrays.toString(arr));

    }
}
