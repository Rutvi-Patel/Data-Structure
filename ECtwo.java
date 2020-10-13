package ThreeTwoEight;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ECtwo {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter N value of an array: ");
        int n = scnr.nextInt();
        int[] arr = new int[n];
        for (int i =0; i <n ; i++){
            int num = rand.nextInt(10001)-5000;
            arr[i] = num;
        }

        System.out.println(Arrays.toString(arr));
        insertionSort(arr,n);
        System.out.println(Arrays.toString(arr));

        long total1 = 0;
        long total2 =0;
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            QuickSort(arr,0, arr.length-1);
            long end = System.nanoTime();
            total1 = total1 + (end - start);

            long start1 = System.nanoTime();
            insertionSort(arr,arr.length);
            long end1 = System.nanoTime();
            total2 = total2 + (end1 - start1);
        }

    System.out.println("PART A");
    long avg = total1 / 100;
    System.out.println("Average-running time for Quick Sort is "+avg +" Nano seconds");
    long avg1 = total2 / 100;
    System.out.println("Average-running time for Insertion Sort is "+avg1 +" Nano seconds");

    }

    public static void insertionSort(int arr[], int n)
    {
        int i, key, j;
        for (i = 1; i < n; i++)
        {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] < key)
            {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }




    public static void QuickSort(int[] arr, int low,int high){
        if(low<high)  {
            int indPivot = partition(arr, low, high);

            QuickSort(arr, low, indPivot - 1);
            QuickSort(arr, indPivot + 1, high);
        }
}


    public static int partition(int arr[], int low, int high) {

        pivot(arr,low,low+ (high-low)/2, high);
        int pivot = arr[high];
        int si = low;
        int ei = high-1;
        while (si<ei){
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
        // swap pivot in the middle and arr[high] (or pivot)
        int temp = arr[si+1];
        arr[si+1] = arr[high];
        arr[high] = temp;

        return si+1;
    }

public static int part(int[] arr, int low, int high){
        pivot(arr,low,low+(high-low)/2,high);
        int pivot = arr[high];
        int ei = high-1;
        while(low<ei){
            if (arr[low]>pivot && arr[ei]<pivot){
                int temp = arr[low];
                arr[low] = arr[ei];
                arr[ei] = temp;
                low++;
                ei--;
            }
            else if(arr[ei]<pivot){
                low ++;
            }else {
                ei--;
            }
        }
         if(arr[low]< pivot){
             low = low+1;
         }else{
             low = low;
         }
        int temp = arr[low];
        arr[low]= arr[high];
        arr[high]= temp;
        return low;
}

public static void pivot(int []arr, int si, int mid, int ei){
    int a = arr[si];
    int b = arr[ei];
    int c = arr[mid];
    int med;
    if ((a<b && a>c) ||(a>b && a<c) ){
        med = si;
    }else if((c<b && c>a) ||(c>b && c<a)){
        med = mid;
    }else{
        med = ei;
    }
    int temp = arr[med];
    arr[med] = arr[ei];
    arr[ei] = temp;
    }


}

